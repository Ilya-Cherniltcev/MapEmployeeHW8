package pro.sky.MapEmployeeHW8.interfaces;

import pro.sky.MapEmployeeHW8.data.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeInterface {
    Employee addNewEmployee(String firstName, String lastName, int department, int salary);

    Employee findEmployee(String firstName, String lastName);

    Employee deleteEmployee(String firstName, String lastName);

    Collection<Employee> printAllEmployees();

    String calcCostsPerMonth();

    Employee getEmployeeOfMaxSalary(int departmentId);

    Employee getEmployeeOfMinSalary(int departmentId);

    int calcMiddleSalary();

    List<Employee> getEmployeesOfDepartment(int departmentId);

    List<Employee> getAllEmployees();
}
