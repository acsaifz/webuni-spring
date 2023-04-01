package hu.webuni.hr.acsaifz.service;

import hu.webuni.hr.acsaifz.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractEmployeeService implements EmployeeService {
    protected AtomicLong employeeIdGenerator = new AtomicLong();
    protected final Map<Long, Employee> employees = new HashMap<>();

    @Override
    public Employee save(Employee employee){
        if (employee.getId() < 1){
            employee.setId(employeeIdGenerator.incrementAndGet());
        } else if (!employees.containsKey(employee.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        employees.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public List<Employee> findAll(){
        return new ArrayList<>(employees.values());
    }

    @Override
    public List<Employee> findAllBySalaryGreaterThan(int minSalary){
        return findAll().stream()
                .filter(e -> e.getMonthlySalary() > minSalary)
                .toList();
    }

    @Override
    public Employee findById(long id) {
        Employee employee = employees.get(id);

        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return employee;
    }

    @Override
    public void delete(long id) {
        if (employees.remove(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
