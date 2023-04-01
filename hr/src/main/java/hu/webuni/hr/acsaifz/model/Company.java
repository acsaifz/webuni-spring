package hu.webuni.hr.acsaifz.model;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private long id;
    private String registrationNumber;

    private String name;

    private String address;
    private List<Employee> employees = new ArrayList<>();

    public Company() {
    }

    public Company(String registrationNumber, String name, String address) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.address = address;
    }

    public Company(long id, String registrationNumber, String name, String address) {
        this(registrationNumber, name, address);
        this.id = id;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}