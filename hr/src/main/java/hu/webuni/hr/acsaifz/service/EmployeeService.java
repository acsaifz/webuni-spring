package hu.webuni.hr.acsaifz.service;

import hu.webuni.hr.acsaifz.model.Employee;

import java.util.List;

public interface EmployeeService {

    int getPayRaisePercent(Employee employee);

    Employee save(Employee employee);

    List<Employee> findAll();

    List<Employee> findAllBySalaryGreaterThan(int minSalary);

    Employee findById(long id);

    void delete(long id);

}
