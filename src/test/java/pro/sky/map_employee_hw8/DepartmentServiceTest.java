package pro.sky.map_employee_hw8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.map_employee_hw8.data.Employee;
import pro.sky.map_employee_hw8.services.DepartmentService;
import pro.sky.map_employee_hw8.services.EmployeeService;

import java.util.List;

import static pro.sky.map_employee_hw8.MapEmployeeTestConstants.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {


    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentService departmentService;

    // =======   получить всех сотрудников конкретного отдела ===================
    @Test
    public void shouldGetAllEmployeesOfDepartment() {
        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(EMPLOYEE_MAP);
        // ---- передаем в метод номер отдела 2. Сотрудник в этом отделе один -----
        List<Employee> actual = departmentService.allDepartmentsEmployees(2);
        Assertions.assertEquals(EMPLOYEE_LIST, actual);
    }

    @Test
    public void ReturnEmptyListIfGetWrongDepartment() {
        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(EMPLOYEE_MAP);
        // ---- передаем в метод номер отдела 5. Сотрудников в этом отделе нет -----
        List<Employee> actual = departmentService.allDepartmentsEmployees(5);
        Assertions.assertEquals(EMPTY_LIST, actual);
    }

    // определяем сотрудника с МАКСимальной з/п ===================
    @Test
    public void shouldGetEmployeeWithMaxSalary() {
        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(EMPLOYEE_MAP);
        // ---- передаем в метод номер отдела 1 -----
        Employee actual = departmentService.whoHasMaxSalary(1);
        Assertions.assertEquals(EXIST_EMPLOYEE, actual);
    }

    // определяем сотрудника с минимальной з/п ===================
    @Test
    public void shouldGetEmployeeWithMinSalary() {
        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(EMPLOYEE_MAP);
        // ---- передаем в метод номер отдела 2. Сотрудник в этом отделе один -----
        Employee actual = departmentService.whoHasMinSalary(2);
        Assertions.assertEquals(MIN_SALARY_EMPLOYEE, actual);
    }
}
