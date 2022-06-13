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
import java.util.List;

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
        employeeService = new EmployeeService();
        employeeService.addNewEmployee("Евгений", "Потапов", 1, 80000);
        employeeService.addNewEmployee("Алла", "Горева", 1, 53050);
        employeeService.addNewEmployee("Олег", "Крылов", 2, 74000);
        employeeService.addNewEmployee("Илья", "Круглов", 2, 105300);
        employeeService.addNewEmployee("Антон", "Тулупов", 3, 63700);
        employeeService.addNewEmployee("Семен", "Кузнецов", 3, 123000);
    }

    @Test
    public void shouldReturnAllEmployees() {
   //     List<Employee> resultList = new ArrayList(employeeService.getAllEmployees());
       // List<Employee> result = new ArrayList(List.of(resultMap.values()));
   //    Assertions.assertEquals(resultList, EMPLOYEE_LIST);
    //   Assertions.assertTrue(resultList.contains(EMPLOYEE_LIST));
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
   //     Assertions.assertNotNull(employeeService);
        Employee result = departmentService.whoHasMaxSalary(1);
        //   Mockito.when(employeeService.getEmployeeOfMaxSalary(3).getSalary()).thenReturn(50000);

//        int maxSalary = employeeService.getEmployeeOfMaxSalary(3).getSalary();
//        System.out.println(maxSalary);
        //  int maxSalary = 50000;
    //    Assertions.assertEquals(maxSalary, 50000);


    }

}
