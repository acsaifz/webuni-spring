package hu.webuni.hr.acsaifz.service;

import hu.webuni.hr.acsaifz.model.Company;
import hu.webuni.hr.acsaifz.model.Employee;

import java.util.List;

public interface CompanyService {
    Company save(Company company);

    List<Company> findAll();

    Company findById(long id);

    Company update(Company company);

    void delete(long id);

    Company addEmployeeForCompany(Employee employee, long id);

    Company deleteEmployeeFromCompany(long employeeId, long companyId);

    Company replaceEmployeesOfCompany(List<Employee> employees, long companyId);

    long count();
}
