package pro.sky.MapEmployeeHW8;

import org.springframework.stereotype.Service;
import pro.sky.MapEmployeeHW8.exceptions.AlreadyExistsException;
import pro.sky.MapEmployeeHW8.exceptions.EmployeeNotFoundException;
import pro.sky.MapEmployeeHW8.exceptions.OverArrayException;

import java.util.HashMap;
import java.util.Map;


@Service
public class EmployeeService implements EmployeeInterface {
    private int id = 0;
    Map<Employee, Integer> empl = new HashMap<>(
            new Employee("Евгений", "Потапов"), getNewId(),
            new Employee("Алла", "Горева"), getNewId(),
            new Employee("Олег", "Крылов"), getNewId());

    // +++++++++++++++++++++++ Добавляем нового сотрудника +++++++++++++++++++++++++++
    @Override
    public Employee addNewEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < empl.length; i++) {
            if (empl[i] == null) {
                empl[i] = employee;  // ===  добавляем нового работника в массив, и
                return employee;     // ===  завершаем метод, возвращая новуу запись
            } else {
                if (empl[i].getFirstName().equals(firstName) && empl[i].getLastName().equals(lastName)) {
                    // =====   если работник уже есть в базе, вызываем ошибку 400 Bad Request =======
                    throw new AlreadyExistsException();
                }
            }
        }
        // =====   если программа дошла до этой строки, значит массив переполнен,
        // =====   вызываем ошибку 500 Internal Server Error =======
        throw new OverArrayException();
    }

    // ----------------- Находим сотрудника по Ф.И.О. ------------------------------------
@Override
    public Employee findEmployee(String firstName, String lastName) {
        final Employee employee;
        for (int i = 0; i < empl.length; i++) {
            if (empl[i] != null && empl[i].getFirstName().equals(firstName) && empl[i].getLastName().equals(lastName)) {
                employee = empl[i];
                id = i;
                return employee;
            }
        }
// =====   если программа дошла до этой строки, значит сотрудник не найден,
// =====   вызываем ошибку 404 Not Found =======
        throw new EmployeeNotFoundException();
    }

    // ----------------- Удаляем сотрудника по Ф.И.О. ------------------------------------
    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = findEmployee(firstName, lastName);
        empl[id] = null;
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
