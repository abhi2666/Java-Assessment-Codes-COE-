package abhiPkg;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    private List<Employee> employees;

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    
    // returns a list of all the employees in the current department
    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee); // employee will be added to the current department
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }
}
