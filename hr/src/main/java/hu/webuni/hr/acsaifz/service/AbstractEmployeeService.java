package hu.webuni.hr.acsaifz.service;

import hu.webuni.hr.acsaifz.exception.ResourceNotFoundException;
import hu.webuni.hr.acsaifz.model.Employee;
import hu.webuni.hr.acsaifz.repository.EmployeeRepository;

import java.util.List;

public abstract class AbstractEmployeeService implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    protected AbstractEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> saveAll(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    @Override
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findAllBySalaryGreaterThan(int minSalary){
        return employeeRepository.findAllByMonthlySalaryGreaterThan(minSalary);
    }

    @Override
    public Employee findById(long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }

    @Override
    public void delete(long id) {
        if (employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Employee", "id", id);
        }
    }

    @Override
    public Employee update(Employee employee) {
        if (employeeRepository.existsById(employee.getId())){
            return save(employee);
        } else {
            throw new ResourceNotFoundException("Employee", "id", employee.getId());
        }
    }
}
