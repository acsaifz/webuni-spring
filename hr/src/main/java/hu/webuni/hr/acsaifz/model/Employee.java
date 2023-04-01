package hu.webuni.hr.acsaifz.model;

import java.time.LocalDate;

public class Employee {
    private long id;
    private String name;
    private String jobTitle;
    private int monthlySalary;
    private LocalDate entryDate;
    private Company company;

    public Employee(){

    }

    public Employee(String name, String jobTitle, int monthlySalary, LocalDate entryDate){
        this.name = name;
        this.jobTitle = jobTitle;
        this.monthlySalary = monthlySalary;
        this.entryDate = entryDate;
    }

    public Employee(long id, String name, String jobTitle, int monthlySalary, LocalDate entryDate) {
        this(name, jobTitle, monthlySalary, entryDate);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", jobTitle='").append(jobTitle).append('\'');
        sb.append(", monthlySalary=").append(monthlySalary);
        sb.append(", entryDate=").append(entryDate);
        sb.append('}');
        return sb.toString();
    }
}
