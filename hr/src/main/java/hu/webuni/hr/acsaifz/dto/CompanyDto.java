package hu.webuni.hr.acsaifz.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class CompanyDto {
    @JsonView(Views.BaseCompanyData.class)
    private Long id;
    @JsonView(Views.BaseCompanyData.class)
    @NotBlank
    private String registrationNumber;
    @JsonView(Views.BaseCompanyData.class)
    @NotBlank
    private String name;
    @JsonView(Views.BaseCompanyData.class)
    @NotBlank
    private String address;
    private List<EmployeeDto> employees = new ArrayList<>();

    public CompanyDto() {
    }

    public CompanyDto(Long id, String registrationNumber, String name, String address) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.address = address;
    }

    public CompanyDto(Long id, String registrationNumber, String name, String address, List<EmployeeDto> employees) {
        this(id, registrationNumber, name, address);
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }
}
