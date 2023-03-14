package hu.webuni.hr.acsaifz.web;

import hu.webuni.hr.acsaifz.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
public class EmployeeTLController {
    private long nextEmployeeId = 1L;
    private final Map<Long, Employee> employees = new HashMap<>();

    {
        employees.put(nextEmployeeId, new Employee(nextEmployeeId++, "John Doe", 10_000,
                LocalDate.of(2023,1,15)));
        employees.put(nextEmployeeId, new Employee(nextEmployeeId++, "Jane Doe", 15_000,
                LocalDate.of(2018,5,19)));
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/employees";
    }

    @GetMapping("/employees")
    public String listEmployees(Model model){

        model.addAttribute("employees", employees.values());
        model.addAttribute("newEmployee", new Employee());

        return "employees";
    }

    @PostMapping("/employees")
    public String createEmployee(Employee employee){
        employee.setId(nextEmployeeId);
        employees.put(nextEmployeeId++, employee);

        return "redirect:/employees";
    }
}
