package hu.webuni.hr.acsaifz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "employee")
public record EmployeeConfigProperties(
        SalaryRaise salaryRaise
) {
    public record SalaryRaise(
            List<SalaryRaiseCategory> categories,
            Base base,
            Base defaultRaise

    ){}

    public record SalaryRaiseCategory(
            double yearLimit,
            int percent
    ){}

    public record Base(
            int percent
    ){}
}
