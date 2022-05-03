package pro.sky.exceptionsemployeehw5;

public interface EmployeeInterface {
    Employee addNewEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Employee deleteEmployee(String firstName, String lastName);

}
