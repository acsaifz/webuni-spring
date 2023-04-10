package hu.webuni.hr.acsaifz.service;

import hu.webuni.hr.acsaifz.config.EmployeeConfigProperties;
import hu.webuni.hr.acsaifz.model.Employee;
import hu.webuni.hr.acsaifz.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmployeeService extends AbstractEmployeeService {
    private final EmployeeConfigProperties config;

    public DefaultEmployeeService(EmployeeConfigProperties config, EmployeeRepository employeeRepository) {
        super(employeeRepository);
        this.config = config;
    }

    @Override
    public int getPayRaisePercent(Employee employee) {
        return config.salaryRaise().defaultRaise().percent();
    }
}
