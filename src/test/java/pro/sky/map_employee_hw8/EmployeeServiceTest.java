package pro.sky.map_employee_hw8;

import org.junit.jupiter.api.*;
import pro.sky.map_employee_hw8.data.Employee;
import pro.sky.map_employee_hw8.services.EmployeeService;

import java.util.Map;

import static pro.sky.map_employee_hw8.MapEmployeeTestConstants.*;

public class EmployeeServiceTest {

    private EmployeeService employeeService;

    // ===== заполняем мапу перед всеми тестами =====
    @BeforeEach
    public void setup() {
        employeeService = new EmployeeService();
        employeeService.addNewEmployee("Евгений", "Потапов", 1, 80000);
        employeeService.addNewEmployee("Алла", "Горева", 1, 53050);
        employeeService.addNewEmployee("Олег", "Крылов", 2, 74000);
        employeeService.addNewEmployee("Антон", "Тулупов", 3, 63700);
        employeeService.addNewEmployee("Семен", "Кузнецов", 3, 123000);
    }

    @Test
    public void shouldReturnAllEmployees() {
        Map<String, Employee> result = employeeService.getAllEmployees();
        Assertions.assertEquals(result, EMPLOYEE_MAP);
    }

    // ===== возвращает Employee, при успешном его добавлении в мапу =====
    @Test
    public void shouldReturnAddingEmployee() {
        Employee result = employeeService.addNewEmployee(NEW_EMPLOYEE_NAME,
                NEW_EMPLOYEE_LASTNAME, NEW_EMPLOYEE_DEPARTMENT, NEW_EMPLOYEE_SALARY);
        Assertions.assertEquals(result, NEW_EMPLOYEE);
    }

    // ===== выбрасывает исключение (ПРИ ПОПЫТКЕ ДОБАВИТЬ), если в имени некорректные символы =====
    @Test
    public void shouldReturnExceptionOfAddingEmployeeInCaseIncorrectWritingOfName() {
        Assertions.assertThrows(RuntimeException.class,
                () -> employeeService.addNewEmployee(INCORRECT_NAME,
                        NEW_EMPLOYEE_LASTNAME, NEW_EMPLOYEE_DEPARTMENT,
                        NEW_EMPLOYEE_SALARY));
    }

    // ===== выбрасывает исключение (ПРИ ПОПЫТКЕ ДОБАВИТЬ), если в фамилии некорректные символы =====
    @Test
    public void shouldReturnExceptionOfAddingEmployeeInCaseIncorrectWritingOfLastname() {
        Assertions.assertThrows(RuntimeException.class,
                () -> employeeService.addNewEmployee(NEW_EMPLOYEE_NAME,
                        INCORRECT_NAME, NEW_EMPLOYEE_DEPARTMENT,
                        NEW_EMPLOYEE_SALARY));
    }

    // ===== выбрасывает исключение (ПРИ ПОПЫТКЕ ДОБАВИТЬ), если сотрудник уже существует =====
    @Test
    public void shouldReturnExceptionOfAddingEmployeeIfEmployeeIsExist() {
        Assertions.assertThrows(RuntimeException.class,
                () -> employeeService.addNewEmployee(EXIST_EMPLOYEE_NAME,
                        EXIST_EMPLOYEE_LASTNAME, NEW_EMPLOYEE_DEPARTMENT,
                        NEW_EMPLOYEE_SALARY));
    }

    // ===== возвращает Employee, при успешном удалении сотрудника =====
    @Test
    public void shouldReturnDeletingEmployee() {
        Employee result = employeeService.deleteEmployee(EXIST_EMPLOYEE_NAME,
                EXIST_EMPLOYEE_LASTNAME);
        Assertions.assertEquals(result, EXIST_EMPLOYEE);
    }

    // ===== выбрасывает исключение (ПРИ ПОПЫТКЕ ПОИСКА), если в имени некорректные символы =====
    @Test
    public void shouldReturnExceptionOfFindEmployeeInCaseIncorrectWritingOfName() {
        Assertions.assertThrows(RuntimeException.class,
                () -> employeeService.findEmployee(INCORRECT_NAME, EXIST_EMPLOYEE_LASTNAME));
    }

    // ===== выбрасывает исключение (ПРИ ПОПЫТКЕ ПОИСКА), если в фамилии некорректные символы =====
    @Test
    public void shouldReturnExceptionOfFindEmployeeInCaseIncorrectWritingOfLastname() {
        Assertions.assertThrows(RuntimeException.class,
                () -> employeeService.findEmployee(EXIST_EMPLOYEE_NAME, INCORRECT_NAME));
    }

    // ===== выбрасывает исключение (ПРИ ПОПЫТКЕ УДАЛИТЬ), если сотрудник не существует =====
    @Test
    public void shouldReturnExceptionOfDeletingEmployeeIfEmployeeIsNotExist() {
        Assertions.assertThrows(RuntimeException.class,
                () -> employeeService.deleteEmployee(NEW_EMPLOYEE_NAME, NEW_EMPLOYEE_LASTNAME));

    }

    // ===== возвращаем сумму затрат на з/п в месяц =====
    @Test
    public void shouldReturnAllCostPerMonth() {
        int result = employeeService.calcCostsPerMonth();
        Assertions.assertEquals(result, COST_PER_MONTH);
    }

    // ===== возвращаем среднюю з/п сотрудников в месяц =====
    @Test
    public void shouldReturnMiddleSalaryPerMonth() {
        int result = employeeService.calcMiddleSalary();
        Assertions.assertEquals(result, MIDDLE_SALARY);
    }
}
