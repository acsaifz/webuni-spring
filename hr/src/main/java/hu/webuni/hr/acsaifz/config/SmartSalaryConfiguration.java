package hu.webuni.hr.acsaifz.config;

import hu.webuni.hr.acsaifz.repository.EmployeeRepository;
import hu.webuni.hr.acsaifz.service.EmployeeService;
import hu.webuni.hr.acsaifz.service.SmartEmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("smart")
public class SmartSalaryConfiguration {

    @Bean
    public EmployeeService employeeService(EmployeeConfigProperties config, EmployeeRepository employeeRepository){
        return new SmartEmployeeService(config, employeeRepository);
    }
}
