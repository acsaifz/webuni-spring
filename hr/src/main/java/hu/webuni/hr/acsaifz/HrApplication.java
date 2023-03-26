package hu.webuni.hr.acsaifz;

import hu.webuni.hr.acsaifz.model.Employee;
import hu.webuni.hr.acsaifz.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@ConfigurationPropertiesScan
public class HrApplication implements CommandLineRunner {
    @Autowired
    private SalaryService salaryService;

    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Employee> employees = List.of(
                new Employee(1L, "John Doe", "Engineer", 10_000, LocalDate.of(2013,3,6)),
                new Employee(2L, "Jack Doe", "Engineer", 10_000, LocalDate.of(2018,1,15)),
                new Employee(3L, "Sarah Doe", "Manager", 10_000, LocalDate.of(2020,7,1)),
                new Employee(4L, "Jane Doe", "Technician", 10_000, LocalDate.of(2022,11,15))
        );

        System.out.println("\nBefore:");
        employees.forEach(System.out::println);

        employees.forEach(e -> salaryService.raiseSalary(e));

        System.out.println("\nAfter:");
        employees.forEach(System.out::println);
    }
}
