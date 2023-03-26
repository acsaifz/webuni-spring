package hu.webuni.hr.acsaifz.web;

import hu.webuni.hr.acsaifz.dto.SalaryRaiseDto;
import hu.webuni.hr.acsaifz.model.Employee;
import hu.webuni.hr.acsaifz.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/salary-raise")
public class SalaryRaiseController {
    private final EmployeeService employeeService;

    public SalaryRaiseController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<SalaryRaiseDto> getSalaryRaiseOfEmployee(@RequestBody Employee employee) {
        SalaryRaiseDto salaryRaiseDto = new SalaryRaiseDto(employeeService.getPayRaisePercent(employee));
        return ResponseEntity.ok(salaryRaiseDto);
    }
}
