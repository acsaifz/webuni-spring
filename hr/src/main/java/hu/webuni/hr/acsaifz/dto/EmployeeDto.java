package hu.webuni.hr.acsaifz.dto;

import java.time.LocalDate;

public class EmployeeDto {
    private Long id;
    private String name;
    private int monthlySalary;
    private LocalDate entryDate;

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String name, int monthlySalary, LocalDate entryDate) {
        this.id = id;
        this.name = name;
        this.monthlySalary = monthlySalary;
        this.entryDate = entryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(int monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }
}
