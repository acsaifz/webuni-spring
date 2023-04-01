package hu.webuni.hr.acsaifz.web;

import com.fasterxml.jackson.annotation.JsonView;
import hu.webuni.hr.acsaifz.dto.CompanyDto;
import hu.webuni.hr.acsaifz.dto.EmployeeDto;
import hu.webuni.hr.acsaifz.dto.Views;
import hu.webuni.hr.acsaifz.mapper.CompanyMapper;
import hu.webuni.hr.acsaifz.mapper.EmployeeMapper;
import hu.webuni.hr.acsaifz.model.Company;
import hu.webuni.hr.acsaifz.model.Employee;
import hu.webuni.hr.acsaifz.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
public record CompanyController(
        CompanyService companyService,
        CompanyMapper companyMapper,
        EmployeeMapper employeeMapper
) {

    @GetMapping
    @JsonView(Views.BaseCompanyData.class)
    public List<CompanyDto> getAll(){
        return new ArrayList<>(companyMapper.companiesToDto(companyService.findAll()));
    }

    @GetMapping(params = {"full=true"})
    public List<CompanyDto> getAllWithFullData(){
        return new ArrayList<>(companyMapper.companiesToDto(companyService.findAll()));
    }

    @GetMapping("/{id}")
    public CompanyDto getCompanyById(@PathVariable Long id){

        return companyMapper.companyToDto(companyService.findById(id));
    }

    @PostMapping
    public CompanyDto createCompany(@Valid @RequestBody CompanyDto companyDto){
        Company company = companyService.save(companyMapper.dtoToCompany(companyDto));

        return companyMapper.companyToDto(company);
    }

    @PutMapping("/{id}")
    public CompanyDto modifyCompany(@Valid @RequestBody CompanyDto companyDto, @PathVariable Long id){
        companyDto.setId(id);
        Company company = companyMapper.dtoToCompany(companyDto);


        return companyMapper.companyToDto(
                companyService.save(company)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id){
        companyService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/employees")
    public CompanyDto addEmployeeForCompany(@Valid @RequestBody EmployeeDto employeeDto, @PathVariable Long id){
        Employee employee = employeeMapper.dtoToEmployee(employeeDto);

        return companyMapper.companyToDto(
                companyService.addEmployeeForCompany(employee, id)
        );
    }

    @DeleteMapping("/{companyId}/employees/{employeeId}")
    public CompanyDto deleteEmployeeFromCompany(@PathVariable Long employeeId, @PathVariable Long companyId){

        return companyMapper.companyToDto(
                companyService.deleteEmployeeFromCompany(employeeId, companyId)
        );
    }

    @PutMapping("/{id}/employees")
    public CompanyDto replaceEmployeesOfCompany(@Valid @RequestBody List<EmployeeDto> employeeDtos, @PathVariable Long id){
        List<Employee> employees = employeeMapper.dtosToEmployees(employeeDtos);

        companyService.replaceEmployeesOfCompany(employees, id);

        return companyMapper.companyToDto(
                companyService.replaceEmployeesOfCompany(employees, id)
        );
    }
}
