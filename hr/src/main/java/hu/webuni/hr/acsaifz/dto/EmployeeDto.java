package hu.webuni.hr.acsaifz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class EmployeeDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String jobTitle;
    @Positive
    private int monthlySalary;
    @PastOrPresent
    private LocalDate entryDate;

    public EmployeeDto() {
    }

    public EmployeeDto(String name, String jobTitle, int monthlySalary, LocalDate entryDate) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.monthlySalary = monthlySalary;
        this.entryDate = entryDate;
    }

    public EmployeeDto(Long id, String name, String jobTitle, int monthlySalary, LocalDate entryDate) {
        this(name, jobTitle, monthlySalary, entryDate);
        this.id = id;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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
