package pro.sky.MapEmployeeHW8;

import java.util.Map;

public interface EmployeeInterface {
    Employee addNewEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Employee deleteEmployee(String firstName, String lastName);


    Map<Employee, Integer> printAllEmployees();

}
