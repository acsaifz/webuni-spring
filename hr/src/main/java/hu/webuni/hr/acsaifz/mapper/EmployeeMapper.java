package hu.webuni.hr.acsaifz.mapper;

import hu.webuni.hr.acsaifz.dto.EmployeeDto;
import hu.webuni.hr.acsaifz.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto employeeToDto(Employee employee);

    List<EmployeeDto> employeesToDtos(List<Employee> employees);

    Employee dtoToEmployee(EmployeeDto employeeDto);

    List<Employee> dtosToEmployees(List<EmployeeDto> employeeDtos);
}
