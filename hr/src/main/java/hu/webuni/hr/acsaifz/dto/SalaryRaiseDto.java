package hu.webuni.hr.acsaifz.dto;

public class SalaryRaiseDto {
    private int salaryRaisePercent;

    public SalaryRaiseDto(int salaryRaisePercent) {
        this.salaryRaisePercent = salaryRaisePercent;
    }

    public int getSalaryRaisePercent() {
        return salaryRaisePercent;
    }

    public void setSalaryRaisePercent(int salaryRaisePercent) {
        this.salaryRaisePercent = salaryRaisePercent;
    }
}
