package pro.sky.map_employee_hw8;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import pro.sky.map_employee_hw8.data.Employee;
import pro.sky.map_employee_hw8.exceptions.InvalidInputException;
import pro.sky.map_employee_hw8.services.DepartmentService;
import pro.sky.map_employee_hw8.services.EmployeeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pro.sky.map_employee_hw8.MapEmployeeTestConstants.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
   private DepartmentService dep;
   private List<Employee> employeesList = new ArrayList(List.of (
            "Евгений", "Потапов", 1, 80_000,
            "Алла", "Горева", 1, 53_050,
            "Олег", "Крылов", 2, 74_000,
            "Илья", "Круглов", 2, 105_300,
            "Антон", "Тулупов", 3, 63_700,
            "Семен", "Кузнецов", 3, 123_000));

    @MockBean
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentService departmentService;

    // ===== заполняем мапу перед всеми тестами =====
    @BeforeEach
    public void setup() {
        Map<String, Employee> empl = new HashMap(Map.of (
                "Евгений Потапов", new Employee("Евгений", "Потапов", 1, 80000),
                "Алла Горева", new Employee("Алла", "Горева", 1, 53050),
                "Олег Крылов", new Employee("Олег", "Крылов", 2, 74000),
                "Илья Круглов", new Employee("Илья", "Круглов", 2, 105300),
                "Антон Тулупов", new Employee("Антон", "Тулупов", 3, 63700),
                "Семен Кузнецов", new Employee("Семен", "Кузнецов", 3, 123000)));
        employeeService = new EmployeeService(empl);
        departmentService = new DepartmentService(employeeService);
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
        Assertions.assertEquals(result,COST_PER_MONTH);
    }
    // ===== возвращаем среднюю з/п сотрудников в месяц =====
    @Test
    public void shouldReturnMiddleSalaryPerMonth() {
        int result = employeeService.calcMiddleSalary();
        Assertions.assertEquals(result,MIDDLE_SALARY);
    }

    @Test
    public void getEmployeeWithMaxSalary() {
        Mockito.when(departmentService.whoHasMaxSalary(1))
                .thenReturn(NEW_EMPLOYEE);
        Employee mockMaxSalary = departmentService.whoHasMaxSalary(1);
        System.out.println(mockMaxSalary);
        Assertions.assertEquals(mockMaxSalary, NEW_EMPLOYEE);


    }

}
