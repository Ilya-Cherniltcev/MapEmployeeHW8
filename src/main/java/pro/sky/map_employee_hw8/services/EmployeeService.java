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


@Service
public class EmployeeService implements EmployeeInterface {
 //   EmployeeService employeeService;
    // ==== сервис по работе с общими методами ===========================================
    //  private CheckRightWritingOfName check = new CheckRightWritingOfName();
    private Map<String, Employee> empl = new HashMap<>();

 //   public EmployeeService() {
//        this.employeeService = employeeService;
//    }


    // ******  метод получения (склеивания полного ФИО) имени и фамилии из Employee
//    private String getFullName (Employee e) {
//        return e.getFirstName() + " " + e.getLastName();
//    }

    private void isRightWriting(String name) {
        // --- проверяем на наличие только букв алфавита --------------
        boolean isAlpha = StringUtils.isAlpha(name);
        // =====   если в имени есть символы, кроме букв, вызываем ошибку 400 Bad Request =======
        if (!isAlpha) {
            throw new InvalidInputException();
        }
    }

// ****************  Основные (общие) методы по работе с сотрудниками ****************
    // *********************************************************************************

    // ----- получаем всех сотрудников, отсортированных по отделам -----
    @Override
    public Map<String, Employee> getAllEmployees() {
        //   public Map<Integer, List<Employee>> getAllEmployees() {
//        Map <String, Employee> employeeMap = empl
//                .values()
//                .stream()
//                .sorted(Comparator.comparing(Employee::getDepartment))
//                .collect(Collectors.toMap(emp -> emp.getFullName(), Function::identity));
//                //.collect(Collectors.groupingBy(Employee::getDepartment));
//        return employeeMap;
//                .values()
//                .stream()
//                .sorted(Comparator.comparing(Employee::getDepartment))
//                .collect(Collectors.toList());
//        return employeeList;
        return empl;
    }


    // ----- Добавляем нового сотрудника -----
    @Override
    public Employee addNewEmployee(String firstName, String lastName, int department, int salary) throws AlreadyExistsException {
        // ******* проверяем корректность имени и фамилии *******
        isRightWriting(firstName);
        isRightWriting(lastName);
        String fullName = firstName + " " + lastName;
        // *******   если работник уже есть в базе, вызываем ошибку 400 Bad Request *******
        if (empl != null && empl.containsKey(fullName)) {
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
        isRightWriting(firstName);
        isRightWriting(lastName);
        String fullName = firstName + " " + lastName;
        // *******  если сотрудник не найден, вызываем ошибку 404 Not Found *******
        if (empl != null && !empl.containsKey(fullName)) {
            throw new EmployeeNotFoundException();
        }
        return empl.get(fullName);
    }

    // ----- Удаляем сотрудника по Ф.И.О. -----
    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        if (empl == null) {
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
}

