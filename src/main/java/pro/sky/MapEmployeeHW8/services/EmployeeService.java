package pro.sky.MapEmployeeHW8.services;

import org.springframework.stereotype.Service;
import pro.sky.MapEmployeeHW8.data.Employee;
import pro.sky.MapEmployeeHW8.exceptions.AlreadyExistsException;
import pro.sky.MapEmployeeHW8.exceptions.EmployeeNotFoundException;
import pro.sky.MapEmployeeHW8.exceptions.EmptyBaseException;
import pro.sky.MapEmployeeHW8.interfaces.EmployeeInterface;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class EmployeeService implements EmployeeInterface {
    private int id = 0;
    Map<String, Employee> empl = new HashMap<>(Map.of(
            "Евгений Потапов", new Employee("Евгений", "Потапов", 1, 80_000),
            "Алла Горева", new Employee("Алла", "Горева", 1, 53_050),
            "Олег Крылов", new Employee("Олег", "Крылов", 2, 74_000),
            "Илья Круглов", new Employee("Илья", "Круглов", 2, 105_300),
            "Антон Тулупов", new Employee("Антон", "Тулупов", 3, 63_700),
            "Семен Кузнецов", new Employee("Семен", "Кузнецов", 3, 123_000)));

    // +++++++++++++++++++++++ Добавляем нового сотрудника +++++++++++++++++++++++++++
    @Override
    public Employee addNewEmployee(String firstName, String lastName, int department, int salary) throws AlreadyExistsException {
        Employee employee = new Employee(firstName, lastName, department, salary);
        String fullname = firstName + " " + lastName;
        // =====   если работник уже есть в базе, вызываем ошибку 400 Bad Request =======
        if (empl.containsKey(fullname)) {
            throw new AlreadyExistsException("Такой работник уже существует");
        }
        empl.put(fullname, employee);
        return employee;
    }

    // ----------------- Находим сотрудника по Ф.И.О. ------------------------------------
    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String fullname = firstName + " " + lastName;
        // =====   если сотрудник не найден, вызываем ошибку 404 Not Found =======
        if (!empl.containsKey(fullname)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return empl.get(fullname);
    }

    // ----------------- Удаляем сотрудника по Ф.И.О. ------------------------------------
    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        if (empl.isEmpty()) {
            throw new EmptyBaseException("Список сотрудников пустой");
        }
        Employee employee = findEmployee(firstName, lastName);
        String fullname = firstName + " " + lastName;
        empl.remove(fullname);
        return employee;
    }

//    // ========= внутренний метод для присваивания id каждому сотруднику ======
//    private Integer getNewId() {
//        int result = id;
//        id++;
//        return result;
//    }

    // ----------------- Печатаем всех сотрудников ------------------------------------
    @Override
    public Collection<Employee> printAllEmployees() {
        return empl.values();
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
                .filter((s) -> s.getDepartment() == departmentId)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    // определяем сотрудника с минимальной з/п ===================
    @Override
    public Employee getEmployeeOfMinSalary(int departmentId) {
        return empl.values()
                .stream()
                .filter((s) -> s.getDepartment() == departmentId)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }


    // =======   все сотрудники по отделу ===================
    @Override
    public List<Employee> getEmployeesOfDepartment(int departmentId) {
        return empl.values()
                .stream()
                .filter((s) -> s.getDepartment() == departmentId)
                .collect(Collectors.toList());

        //(() -> new EmployeeNotFoundException("Отдел не найден"));
    }

    // +++++++++++++++++   ВСЕ СОТРУДНИКИ, ОТСОРТИРОВАННЫЕ ПО ОТДЕЛАМ +++++++++++++++++++++
    @Override
    public List<Employee> getAllEmployees() {
        return empl
                .values()
                .stream()
                .sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toList());
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

