package hu.webuni.hr.acsaifz.web;

import com.fasterxml.jackson.annotation.JsonView;
import hu.webuni.hr.acsaifz.dto.CompanyDto;
import hu.webuni.hr.acsaifz.dto.EmployeeDto;
import hu.webuni.hr.acsaifz.dto.Views;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private AtomicLong companyIdGenerator = new AtomicLong();
    private AtomicLong employeeIdGenerator = new AtomicLong();
    private final Map<Long, CompanyDto> companies = new HashMap<>();

    @GetMapping
    @JsonView(Views.BaseCompanyData.class)
    public List<CompanyDto> getAll(){
        return new ArrayList<>(companies.values());
    }

    @GetMapping(params = {"full=true"})
    public List<CompanyDto> getAllWithFullData(){
        return new ArrayList<>(companies.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id){
        CompanyDto companyDto = companies.get(id);

        if (companyDto == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(companyDto);
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto){
        companyDto.setId(companyIdGenerator.incrementAndGet());
        companies.put(companyDto.getId(), companyDto);

        return ResponseEntity.ok(companyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> modifyEmployee(@RequestBody CompanyDto companyDto, @PathVariable Long id){
        if (!companies.containsKey(id)){
            return ResponseEntity.notFound().build();
        }

        companyDto.setId(id);
        companies.put(id, companyDto);

        return ResponseEntity.ok(companyDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        if (companies.remove(id) == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/employees")
    public ResponseEntity<CompanyDto> addEmployeeForCompany(@RequestBody EmployeeDto employeeDto, @PathVariable Long id){
        CompanyDto companyDto = companies.get(id);

        if (companyDto == null) {
            return ResponseEntity.notFound().build();
        }

        employeeDto.setId(employeeIdGenerator.incrementAndGet());
        companyDto.getEmployees().add(employeeDto);

        return ResponseEntity.ok(companyDto);
    }

    @DeleteMapping("/{companyId}/employees/{employeeId}")
    public ResponseEntity<CompanyDto> deleteEmployeeFromCompany(@PathVariable Long employeeId, @PathVariable Long companyId){
        CompanyDto companyDto = companies.get(companyId);

        if (companyDto == null) {
            return ResponseEntity.notFound().build();
        }

        Optional<EmployeeDto> employeeDto = companyDto.getEmployees()
                .stream()
                .filter(e -> e.getId() == employeeId)
                .findFirst();

        if (employeeDto.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        companyDto.getEmployees().remove(employeeDto.get());

        return ResponseEntity.ok(companyDto);
    }

    @PutMapping("/{id}/employees")
    public ResponseEntity<CompanyDto> replaceEmployeesOfCompany(@RequestBody List<EmployeeDto> employees, @PathVariable Long id){
        CompanyDto companyDto = companies.get(id);

        if (companyDto == null) {
            return ResponseEntity.notFound().build();
        }

        companyDto.setEmployees(employees);

        return ResponseEntity.ok(companyDto);
    }

    {
        CompanyDto firstCompany = new CompanyDto(
                companyIdGenerator.incrementAndGet(),
                "01-10-123456",
                "ABC Kft.",
                "1111, Budapest. Futrinka u. 5."
        );

        CompanyDto secondCompany = new CompanyDto(
                companyIdGenerator.incrementAndGet(),
                "01-10-654321",
                "DEF Kft.",
                "1111, Budapest. Futrinka u. 6."
        );

        companies.put(firstCompany.getId(), firstCompany);
        companies.put(secondCompany.getId(), secondCompany);

        firstCompany.getEmployees().add(new EmployeeDto(
                employeeIdGenerator.incrementAndGet(),
                "John Doe",
                "Assistant",
                150_000,
                LocalDate.of(2022,5,23)
        ));

        firstCompany.getEmployees().add(new EmployeeDto(
                employeeIdGenerator.incrementAndGet(),
                "Carline Annell",
                "General Manager",
                1_500_000,
                LocalDate.of(2017,8,19)
        ));

        secondCompany.getEmployees().add(new EmployeeDto(
                employeeIdGenerator.incrementAndGet(),
                "Nora Glede",
                "Recruiting Manager",
                1_124_000,
                LocalDate.of(2019,6,25)
        ));

        secondCompany.getEmployees().add(new EmployeeDto(
                employeeIdGenerator.incrementAndGet(),
                "Franny Arrighi",
                "Executive Secretary",
                710_000,
                LocalDate.of(2020,8,6)
        ));
    }
}
