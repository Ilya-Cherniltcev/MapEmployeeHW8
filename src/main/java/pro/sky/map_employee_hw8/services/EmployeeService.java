package pro.sky.map_employee_hw8.services;

import org.springframework.stereotype.Service;
import pro.sky.map_employee_hw8.data.Employee;
import pro.sky.map_employee_hw8.exceptions.AlreadyExistsException;
import pro.sky.map_employee_hw8.exceptions.EmployeeNotFoundException;
import pro.sky.map_employee_hw8.exceptions.EmptyBaseException;
import pro.sky.map_employee_hw8.interfaces.EmployeeInterface;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeService implements EmployeeInterface {
    // ==== сервис по работе с общими методами ===========================================
    private final CheckRightWritingOfName check = new CheckRightWritingOfName();
    private final DepartmentService departmentService;
    private final Map<String, Employee> empl = new HashMap<>();

    public EmployeeService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

// ****************  Основные (общие) методы по работе с сотрудниками ****************
    // *********************************************************************************

    // ----- получаем всех сотрудников, отсортированных по отделам -----
    @Override
    public List<Employee> getAllEmployees() {
//    public Map<Integer, List<Employee>> getAllEmployees() {
         List<Employee> employeeMap = empl
//                .values()
//                .stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment));
//        return employeeMap;

                .values()
                .stream()
                .sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toList());
        return employeeMap;
    }

    // ----- Добавляем нового сотрудника -----
    @Override
    public Employee addNewEmployee(String firstName, String lastName, int department, int salary) throws AlreadyExistsException {
        // ******* проверяем корректность имени и фамилии *******
        check.isRightWriting(firstName);
        check.isRightWriting(lastName);
        String fullName = firstName + " " + lastName;
        // *******   если работник уже есть в базе, вызываем ошибку 400 Bad Request *******
        if (empl.containsKey(fullName)) {
            throw new AlreadyExistsException();
        }
        Employee employee = new Employee(firstName, lastName, department, salary);
        empl.put(fullName, employee);
        return employee;
    }

    // ----- Находим сотрудника по Ф.И.О. -----
    @Override
    public Employee findEmployee(String firstName, String lastName) {
        // ******* проверяем корректность имени и фамилии *******
        check.isRightWriting(firstName);
        check.isRightWriting(lastName);
        String fullName = firstName + " " + lastName;
        // *******  если сотрудник не найден, вызываем ошибку 404 Not Found *******
        if (!empl.containsKey(fullName)) {
            throw new EmployeeNotFoundException();
        }
        return empl.get(fullName);
    }

    // ----- Удаляем сотрудника по Ф.И.О. -----
    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        if (empl.isEmpty()) {
            throw new EmptyBaseException();
        }
        Employee employee = findEmployee(firstName, lastName);
        String fullName = firstName + " " + lastName;
        empl.remove(fullName);
        return employee;
    }

    // -----  считаем сумму затрат на зарплаты в месяц по всем сотрудникам -----
    @Override
    public int calcCostsPerMonth() {
        final Collection<Employee> employees = empl.values();
        final Integer summa = employees.stream()
                .mapToInt(e -> e.getSalary())
                .sum();
        return summa;
    }

    // -----  считаем среднюю з/п -----
    @Override
    public int calcMiddleSalary() {
        double asDouble = empl.values()
                .stream()
                .mapToInt(Employee::getSalary)
                .average().getAsDouble();
        return (int) asDouble;
    }

    // ****************  Методы по работе с сотрудниками ПО ОТДЕЛУ ****************
    // *********************************************************************************

    // -----  получаем список всех сотрудников конкретного отдела -----
    @Override
    public List<Employee> getEmployeesOfDepartment(int departmentId) {
        List<Employee> employeesOfDep = departmentService.
                allDepartmentsEmployees(departmentId,empl);
        return employeesOfDep;
    }

    // ----- определяем сотрудника с МАКСимальной з/п -----
    @Override
    public Employee getEmployeeOfMaxSalary(int departmentId) {
        Employee employeeWithMaxSalary = departmentService.
                whoHasMaxSalary(departmentId, empl);
        return employeeWithMaxSalary;
    }

    // ----- определяем сотрудника с минимальной з/п -----
    @Override
    public Employee getEmployeeOfMinSalary(int departmentId) {
        Employee employeeWithMinSalary = departmentService.
                whoHasMinSalary(departmentId, empl);
        return employeeWithMinSalary;
    }

}

