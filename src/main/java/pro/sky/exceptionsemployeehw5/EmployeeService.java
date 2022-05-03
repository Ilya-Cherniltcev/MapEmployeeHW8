package pro.sky.exceptionsemployeehw5;

import org.springframework.stereotype.Service;
import pro.sky.exceptionsemployeehw5.exceptions.AlreadyExistsException;
import pro.sky.exceptionsemployeehw5.exceptions.EmployeeNotFoundException;
import pro.sky.exceptionsemployeehw5.exceptions.OverArrayException;


@Service
public class EmployeeService implements EmployeeInterface {
    Employee[] empl = new Employee[3];
    private int id;
//    Employee[] empl = {
//            new Employee("Евгений", "Потапов"),
//            new Employee("Алла", "Горева"),
//            new Employee("Олег", "Крылов")
//    };

//    private String modifyToJSON(Employee employee) { // ======= переделываем представление в JSON формат
//        Gson gson = new Gson();
//        String eDescript = gson.toJson(employee);
//        return eDescript;
//    }

//    public Employee getEmployee(Integer num) throws OverArrayException { // ======   склеиваем имя с фамилией и возвращаем по номеру ===========
//        final Employee employee;
//        if (num >= empl.length || num < 0) {
//            //      return null;
//            throw new OverArrayException();
//        }
//        employee = empl[num];
//        //  String employeeDescription = modifyToJSON(employee);
//        return employee;
//    }

    // +++++++++++++++++++++++ Добавляем нового сотрудника +++++++++++++++++++++++++++
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
    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = findEmployee(firstName, lastName);
        empl[id] = null;
        return employee;
    }
}
