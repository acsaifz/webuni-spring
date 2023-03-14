package hu.webuni.hr.acsaifz.web;

import hu.webuni.hr.acsaifz.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private long nextEmployeeId = 1L;
    private final Map<Long, EmployeeDto> employees = new HashMap<>();

    {
        employees.put(nextEmployeeId, new EmployeeDto(nextEmployeeId++, "John Doe", 10_000,
                LocalDate.of(2023,1,15)));
        employees.put(nextEmployeeId, new EmployeeDto(nextEmployeeId++, "Jane Doe", 15_000,
                LocalDate.of(2018,5,19)));
    }

    @GetMapping
    public List<EmployeeDto> getAll(@RequestParam(required = false) Optional<Integer> minSalary){
        return minSalary.map(min -> employees.values().stream()
                        .filter(e -> e.getMonthlySalary() > min)
                        .collect(Collectors.toList()))
                .orElseGet(() -> new ArrayList<>(employees.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long id){
        EmployeeDto employeeDto = employees.get(id);

        if (employeeDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employeeDto);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        employeeDto.setId(nextEmployeeId);
        employees.put(nextEmployeeId++, employeeDto);

        return ResponseEntity.ok(employeeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long id){
        if (!employees.containsKey(id)){
            return ResponseEntity.notFound().build();
        }

        employeeDto.setId(id);
        employees.put(id, employeeDto);

        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        if (employees.remove(id) == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
