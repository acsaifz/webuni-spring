package hu.webuni.hr.acsaifz.web;

import hu.webuni.hr.acsaifz.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
public class EmployeeTLController {
    private Long nextEmployeeId = 1L;
    private final Map<Long, Employee> employees = new HashMap<>();

    {
        employees.put(nextEmployeeId, new Employee(nextEmployeeId++, "John Doe", "Engineer", 10_000,
                LocalDate.of(2023,1,15)));
        employees.put(nextEmployeeId, new Employee(nextEmployeeId++, "Jane Doe", "Manager", 15_000,
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

    @GetMapping("/employees/{id}")
    public String showEmployee(@PathVariable Long id, Model model){
        Employee employee = employees.get(id);
        model.addAttribute("employee", employee);
        return "employees-edit";
    }

    @PostMapping("/employees/{id}")
    public String editEmployee(@PathVariable Long id, Employee employee){
        employees.put(id,employee);
        return "redirect:/employees/" + id + "?edit=success";
    }

    @PostMapping("/employees/{id}/delete")
    public String deleteEmployeeById(@PathVariable Long id){
        employees.remove(id);
        return "redirect:/employees";
    }
}
