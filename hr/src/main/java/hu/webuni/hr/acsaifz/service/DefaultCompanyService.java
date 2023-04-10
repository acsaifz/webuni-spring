package hu.webuni.hr.acsaifz.service;

import hu.webuni.hr.acsaifz.exception.ResourceNotFoundException;
import hu.webuni.hr.acsaifz.model.Company;
import hu.webuni.hr.acsaifz.model.Employee;
import hu.webuni.hr.acsaifz.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultCompanyService implements CompanyService {

    private final CompanyRepository companyRepository;
    private final EmployeeService employeeService;

    public DefaultCompanyService(CompanyRepository companyRepository,
                                 EmployeeService employeeService) {
        this.companyRepository = companyRepository;
        this.employeeService = employeeService;
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findById(long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
    }

    @Override
    public Company update(Company company){
        if (!companyRepository.existsById(company.getId())){
            throw new ResourceNotFoundException("Company", "id", company.getId());
        }

        return save(company);
    }

    @Override
    public void delete(long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Company", "id", id);
        }
    }

    @Override
    @Transactional
    public Company addEmployeeForCompany(Employee employee, long id) {
        Company company = findById(id);
        company.addEmployee(employee);

        return save(company);
    }

    @Override
    @Transactional
    public Company deleteEmployeeFromCompany(long employeeId, long companyId) {
        Company company = findById(companyId);
        Employee employee = employeeService.findById(employeeId);

        if (!company.removeEmployee(employee)) {
            throw new ResourceNotFoundException("Employee", "companyId and employeeId", companyId + " and " + employeeId);
        }

        return save(company);
    }

    @Override
    @Transactional
    public Company replaceEmployeesOfCompany(List<Employee> employees, long companyId) {
        Company company = findById(companyId);
        company.clearEmployees();
        //If not saving employees before company then save them twice
        company.addEmployees(employeeService.saveAll(employees));
        //company.addEmployees(employees);
        return companyRepository.save(company);
    }

    @Override
    public long count(){
        return companyRepository.count();
    }
}
