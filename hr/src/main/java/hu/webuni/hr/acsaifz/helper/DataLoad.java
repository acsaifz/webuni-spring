package hu.webuni.hr.acsaifz.helper;

import hu.webuni.hr.acsaifz.model.Company;
import hu.webuni.hr.acsaifz.model.Employee;
import hu.webuni.hr.acsaifz.service.CompanyService;
import hu.webuni.hr.acsaifz.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public record DataLoad(
        EmployeeService employeeService,
        CompanyService companyService
) implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        generateCompaniesData();
        generateEmployeesData();
    }

    private void generateCompaniesData() {
        Company firstCompany = companyService.save(new Company("01-10-123456", "ABC Kft.",
                "1111, Budapest. Futrinka u. 5."));
        Company secondCompany = companyService.save( new Company("01-10-654321", "DEF Kft.",
                "1111, Budapest. Futrinka u. 6."));


        companyService.addEmployeeForCompany(new Employee( "Beverley Mannin", "Data Coordinator",
                510_000, LocalDate.of(2018,8,2)), firstCompany.getId());
        companyService.addEmployeeForCompany(new Employee( "John Doe", "Assistant",
                150_000, LocalDate.of(2022,5,23)), firstCompany.getId());
        companyService.addEmployeeForCompany(new Employee("Nora Glede", "Recruiting Manager",
                1_124_000, LocalDate.of(2019,6,25)), secondCompany.getId());
        companyService.addEmployeeForCompany(new Employee("Franny Arrighi", "Executive Secretary",
                710_000, LocalDate.of(2020,8,6)), secondCompany.getId());
    }

    private void generateEmployeesData(){
        employeeService.save(new Employee( "Beverley Mannin", "Data Coordinator",
                510_000, LocalDate.of(2018,8,2)));
        employeeService.save(new Employee( "John Doe", "Assistant",
                150_000, LocalDate.of(2022,5,23)));
        employeeService.save(new Employee("Nora Glede", "Recruiting Manager",
                1_124_000, LocalDate.of(2019,6,25)));
        employeeService.save(new Employee("Franny Arrighi", "Executive Secretary",
                710_000, LocalDate.of(2020,8,6)));
    }
}
