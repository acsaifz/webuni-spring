package hu.webuni.hr.acsaifz.repository;

import hu.webuni.hr.acsaifz.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByMonthlySalaryGreaterThan(int salary);

}
