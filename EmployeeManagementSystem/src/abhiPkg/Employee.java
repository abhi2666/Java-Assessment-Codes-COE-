package abhiPkg;

import java.time.Duration;
import java.time.LocalDateTime;

public class Employee implements Payable { // payable is an interface that this class will use  
    private int id;
    private String name;
    private String department;
    private double Salary; // per hour salary
    private double hoursWorked; // per day // salary will be calculated based on the hours a person has worked

   public Employee(int id, String name, String department, double hourlyRate) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.Salary = hourlyRate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double hourlyRate) {
        this.Salary = hourlyRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }
    
    // using java date time api to calculate the exact number of hours an employee has worked
    public void logWorkHours(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        hoursWorked += duration.toHours();
    }

    @Override
    // this method comes from payable interface
    public double calculatePay() {
        return hoursWorked * Salary ; // assuming hoursworked is same for all day 
    }
}