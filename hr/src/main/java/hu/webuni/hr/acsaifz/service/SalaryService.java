package hu.webuni.hr.acsaifz.service;

import hu.webuni.hr.acsaifz.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {
    private final EmployeeService employeeService;

    public SalaryService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void raiseSalary(Employee employee){
        int newSalary = (int)(employee.getMonthlySalary()/100.0 *
                (100 + employeeService.getPayRaisePercent(employee)));
        employee.setMonthlySalary(newSalary);
    }
}
