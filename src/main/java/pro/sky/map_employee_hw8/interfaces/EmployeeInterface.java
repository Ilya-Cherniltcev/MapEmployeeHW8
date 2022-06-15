package pro.sky.map_employee_hw8.interfaces;

import pro.sky.map_employee_hw8.data.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeInterface {
    Employee addNewEmployee(String firstName, String lastName, int department, int salary);

    Employee findEmployee(String firstName, String lastName);

    Employee deleteEmployee(String firstName, String lastName);

    int calcCostsPerMonth();

    int calcMiddleSalary();

    Map<String, Employee> getAllEmployees();
}
