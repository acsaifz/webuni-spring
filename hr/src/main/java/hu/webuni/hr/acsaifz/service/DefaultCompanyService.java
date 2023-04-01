package hu.webuni.hr.acsaifz.service;

import hu.webuni.hr.acsaifz.model.Company;
import hu.webuni.hr.acsaifz.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DefaultCompanyService implements CompanyService {
    private final AtomicLong employeeIdGenerator = new AtomicLong();
    private final AtomicLong companyIdGenerator = new AtomicLong();
    private final Map<Long, Company> companies = new HashMap<>();

    @Override
    public Company save(Company company) {
        if (company.getId() < 1){
            company.setId(companyIdGenerator.incrementAndGet());
        } else if (!companies.containsKey(company.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        companies.put(company.getId(), company);

        return company;
    }

    @Override
    public List<Company> findAll() {
        return new ArrayList<>(companies.values());
    }

    @Override
    public Company findById(long id) {
        Company company = companies.get(id);

        if (company == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return company;
    }

    @Override
    public void delete(long id) {
        if (companies.remove(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Company addEmployeeForCompany(Employee employee, long id) {
        Company company = findById(id);
        employee.setId(employeeIdGenerator.incrementAndGet());
        company.addEmployee(employee);

        return company;
    }

    @Override
    public Company deleteEmployeeFromCompany(long employeeId, long companyId) {
        Company company = findById(companyId);

        if(!company.getEmployees().removeIf(e -> e.getId() == employeeId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return company;
    }

    @Override
    public Company replaceEmployeesOfCompany(List<Employee> employees, long companyId) {
        Company company = findById(companyId);

        employees.forEach(e -> e.setId(employeeIdGenerator.incrementAndGet()));

        company.setEmployees(employees);

        return company;
    }
}
