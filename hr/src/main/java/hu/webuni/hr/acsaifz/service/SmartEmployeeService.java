package hu.webuni.hr.acsaifz.service;

import hu.webuni.hr.acsaifz.config.EmployeeConfigProperties;
import hu.webuni.hr.acsaifz.model.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

@Service
public class SmartEmployeeService extends AbstractEmployeeService {

    private final EmployeeConfigProperties config;

    public SmartEmployeeService(EmployeeConfigProperties config) {
        this.config = config;
    }

    @Override
    public int getPayRaisePercent(Employee employee) {
        double yearsInWork = ChronoUnit.DAYS.between(employee.getEntryDate(), LocalDate.now())/365.25;

        config.salaryRaise().categories().sort(
                Comparator.comparingDouble(EmployeeConfigProperties.SalaryRaiseCategory::yearLimit).reversed()
        );

        for (EmployeeConfigProperties.SalaryRaiseCategory salaryRaiseCategory: config.salaryRaise().categories()){
            if (yearsInWork >= salaryRaiseCategory.yearLimit()){
                return salaryRaiseCategory.percent();
            }
        }

        return config.salaryRaise().base().percent();
    }
}
