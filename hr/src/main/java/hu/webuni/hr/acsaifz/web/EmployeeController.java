package hu.webuni.hr.acsaifz.web;

import hu.webuni.hr.acsaifz.dto.EmployeeDto;
import hu.webuni.hr.acsaifz.mapper.EmployeeMapper;
import hu.webuni.hr.acsaifz.model.Employee;
import hu.webuni.hr.acsaifz.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<EmployeeDto> getAll(@RequestParam(required = false) Optional<Integer> minSalary){

        return employeeMapper.employeesToDtos(
                minSalary.map(employeeService::findAllBySalaryGreaterThan).orElseGet(employeeService::findAll)
        );
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable Long id){

        return employeeMapper.employeeToDto(
                employeeService.findById(id)
        );
    }

    @PostMapping
    public EmployeeDto createEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        Employee employee = employeeMapper.dtoToEmployee(employeeDto);

        return employeeMapper.employeeToDto(
                employeeService.save(employee)
        );
    }

    @PutMapping("/{id}")
    public EmployeeDto modifyEmployee(@Valid @RequestBody EmployeeDto employeeDto, @PathVariable Long id){
        employeeDto.setId(id);
        Employee employee = employeeMapper.dtoToEmployee(employeeDto);

        return employeeMapper.employeeToDto(
                employeeService.update(employee)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
