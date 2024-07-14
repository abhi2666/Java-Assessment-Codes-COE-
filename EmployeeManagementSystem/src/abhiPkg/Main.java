package abhiPkg;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {
		Employee emp = new Employee(101, "Suresh", "IT", 200);
		Employee emp2 = new Employee(102, "Ramesh", "IT", 169);
		
		LocalDateTime start = LocalDateTime.of(2024, 4, 12, 9, 1);
        LocalDateTime end = LocalDateTime.of(2024, 5, 12, 9, 1);
        emp.logWorkHours(start, end);
        System.out.println(emp.getName() + " worked for  " + emp.getHoursWorked() + " hours");
        double totalSalary = emp.calculatePay();
        System.out.println("Salary of "+emp.getName()+" is "+totalSalary);
		// adding employees to the department
		Department dp = new Department("IT");
		dp.addEmployee(emp);
		dp.addEmployee(emp2);
		
		// get all the employees details
		List<Employee> result = dp.getEmployees();
		System.out.println("Employees that works in " + dp.getName() + " are -");
		for(Employee ep : result) {
			System.out.println("name : "+ep.getName()); // can print other things as well
		}
//		
	}

}
