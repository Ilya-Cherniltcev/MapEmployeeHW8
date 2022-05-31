package pro.sky.map_employee_hw8.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.map_employee_hw8.data.Employee;
import pro.sky.map_employee_hw8.exceptions.AlreadyExistsException;
import pro.sky.map_employee_hw8.exceptions.EmployeeNotFoundException;
import pro.sky.map_employee_hw8.exceptions.EmptyBaseException;
import pro.sky.map_employee_hw8.exceptions.InvalidInputException;
import pro.sky.map_employee_hw8.interfaces.EmployeeInterface;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeService implements EmployeeInterface {
    private final Map<String, Employee> empl = new HashMap<>(Map.of(
            "Евгений Потапов", new Employee("Евгений", "Потапов", 1, 80_000),
            "Алла Горева", new Employee("Алла", "Горева", 1, 53_050),
            "Олег Крылов", new Employee("Олег", "Крылов", 2, 74_000),
            "Илья Круглов", new Employee("Илья", "Круглов", 2, 105_300),
            "Антон Тулупов", new Employee("Антон", "Тулупов", 3, 63_700),
            "Семен Кузнецов", new Employee("Семен", "Кузнецов", 3, 123_000)));

    // /////////// Внутренний метод проверки правильности ввода \\\\\\\\\\\\\\\\\\
    private void checkRightWriting(String name) {
        // --- проверяем на наличие только букв алфавита --------------
        boolean isAlpha = StringUtils.isAlpha(name);
        // =====   если в имени есть символы, кроме букв, вызываем ошибку 400 Bad Request =======
        if (!isAlpha) {
            throw new InvalidInputException();
        }
        // (1) ====== принудительный перевод всех символов в нижний регистр =======
        // (2) ====== и перевод первой буквы в Заглавный регистр =======
        // ===== осуществляется непосредственно в конструкторе объекта Employee
        // ========        ШИКАРНО !!! === (спасибо Кириллу Качалову :)) =======
    }


    // +++++++++++++++++++++++ Добавляем нового сотрудника +++++++++++++++++++++++++++
    @Override
    public Employee addNewEmployee(String firstName, String lastName, int department, int salary) throws AlreadyExistsException {
        checkRightWriting(firstName);
        checkRightWriting(lastName);
        String fullName = firstName + " " + lastName;
        // =====   если работник уже есть в базе, вызываем ошибку 400 Bad Request =======
        if (empl.containsKey(fullName)) {
            throw new AlreadyExistsException();
        }
        Employee employee = new Employee(firstName, lastName, department, salary);
        empl.put(fullName, employee);
        return employee;
    }

    // ----------------- Находим сотрудника по Ф.И.О. ------------------------------------
    @Override
    public Employee findEmployee(String firstName, String lastName) {
        checkRightWriting(firstName);
        checkRightWriting(lastName);
        String fullName = firstName + " " + lastName;
        // =====   если сотрудник не найден, вызываем ошибку 404 Not Found =======
        if (!empl.containsKey(fullName)) {
            throw new EmployeeNotFoundException();
        }
        return empl.get(fullName);
    }

    // ----------------- Удаляем сотрудника по Ф.И.О. ------------------------------------
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

    // **************************************************************************
    @Override
    // считаем сумму затрат на зарплаты в месяц
    public String calcCostsPerMonth() {
        final Collection<Employee> employees = empl.values();
        final Integer summa = employees.stream()
                .mapToInt(e -> e.getSalary())
                .sum();
        return summa.toString();
    }

    // определяем сотрудника с МАКСимальной з/п ===================
    @Override
    public Employee getEmployeeOfMaxSalary(int departmentId) {
        return empl.values()
                .stream()
                .filter(s -> s.getDepartment() == departmentId)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    // определяем сотрудника с минимальной з/п ===================
    @Override
    public Employee getEmployeeOfMinSalary(int departmentId) {
        return empl.values()
                .stream()
                .filter(s -> s.getDepartment() == departmentId)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }


    // =======   все сотрудники по отделу ===================
    @Override
    public List<Employee> getEmployeesOfDepartment(int departmentId) {
        return empl.values()
                .stream()
                .filter(s -> s.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    // +++++++++++++++++   ВСЕ СОТРУДНИКИ, ОТСОРТИРОВАННЫЕ ПО ОТДЕЛАМ +++++++++++++++++++++
    @Override
    public Map<Integer, List<Employee>> getAllEmployees() {
        Map<Integer, List<Employee>> employeeMap = empl
                .values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        return employeeMap;

//                .values()
//                .stream()
//                .sorted(Comparator.comparing(Employee::getDepartment))
//                .collect(Collectors.toList());
    }

    // считаем среднюю з/п =================================
    @Override
    public int calcMiddleSalary() {
        double asDouble = empl.values()
                .stream()
                .mapToInt(Employee::getSalary)
                .average().getAsDouble();

        return (int) asDouble;
    }
}

