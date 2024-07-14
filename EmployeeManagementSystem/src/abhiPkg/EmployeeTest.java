package abhiPkg;

import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class EmployeeTest {
    private Employee employee;
    private Department department;

    @Before
    // this is to create the necessary objects that will be used later in the test
    public void setUp() {
        department = Mockito.mock(Department.class);
        employee = new Employee(1, "John Doe", "Engineering", 50.0);
    }

    @Test
    public void testCalculatePay() {
        employee.logWorkHours(LocalDateTime.of(2023, 1, 1, 9, 0),
                              LocalDateTime.of(2023, 1, 1, 17, 0));
        assertEquals(400.0, employee.calculatePay(), 0.001);
    }

    @Test
    public void testAddEmployeeToDepartment() {
        department.addEmployee(employee);
        Mockito.verify(department).addEmployee(employee);
    }

    @Test
    public void testRemoveEmployeeFromDepartment() {
        department.removeEmployee(employee);
        Mockito.verify(department).removeEmployee(employee);
    }
}

