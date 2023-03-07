package hu.webuni.hr.acsaifz.service;

import hu.webuni.hr.acsaifz.config.EmployeeConfigProperties;
import hu.webuni.hr.acsaifz.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmployeeService implements EmployeeService {
    @Autowired
    private EmployeeConfigProperties config;

    @Override
    public int getPayRaisePercent(Employee employee) {
        return config.salaryRaise().defaultRaise().percent();
    }
}
