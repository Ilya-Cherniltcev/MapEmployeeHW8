package pro.sky.MapEmployeeHW8.services;

import org.springframework.stereotype.Service;
import pro.sky.MapEmployeeHW8.data.Employee;
import pro.sky.MapEmployeeHW8.exceptions.AlreadyExistsException;
import pro.sky.MapEmployeeHW8.exceptions.EmployeeNotFoundException;
import pro.sky.MapEmployeeHW8.exceptions.EmptyBaseException;
import pro.sky.MapEmployeeHW8.interfaces.EmployeeInterface;

import java.util.HashMap;
import java.util.Map;


@Service
public class EmployeeService implements EmployeeInterface {
    private int id = 0;
    Map<Employee, Integer> empl = new HashMap<>(Map.of(
            new Employee("Евгений", "Потапов"), getNewId(),
            new Employee("Алла", "Горева"), getNewId(),
            new Employee("Олег", "Крылов"), getNewId()));

    // +++++++++++++++++++++++ Добавляем нового сотрудника +++++++++++++++++++++++++++
    @Override
    public Employee addNewEmployee(String firstName, String lastName) throws AlreadyExistsException {
        Employee employee = new Employee(firstName, lastName);
        // =====   если работник уже есть в базе, вызываем ошибку 400 Bad Request =======
        if (empl.containsKey(employee)) {
            throw new AlreadyExistsException("Такой работник уже существует");
        }
        empl.put(employee, getNewId());
        return employee;
    }

    // ----------------- Находим сотрудника по Ф.И.О. ------------------------------------
    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        // =====   если сотрудник не найден, вызываем ошибку 404 Not Found =======
        if (!empl.containsKey(employee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }

    // ----------------- Удаляем сотрудника по Ф.И.О. ------------------------------------
    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        if (empl.isEmpty()) {
            throw new EmptyBaseException("Список сотрудников пустой");
        }
        Employee employee = findEmployee(firstName, lastName);
        empl.remove(employee);
        return employee;
    }

    // ========= внутренний метод для присваивания id каждому сотруднику ======
    private Integer getNewId() {
        int result = id;
        id++;
        return result;
    }

    // ----------------- Печатаем всех сотрудников ------------------------------------
    @Override
    public Map<Employee, Integer> printAllEmployees() {
        return empl;
    }
}
