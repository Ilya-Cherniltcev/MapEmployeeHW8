package pro.sky.map_employee_hw8.services;

import org.springframework.stereotype.Service;
import pro.sky.map_employee_hw8.data.Employee;
import pro.sky.map_employee_hw8.exceptions.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService  {
    // === сервис по работе с методами по отделам ====

    // =======   получить всех сотрудников отдела ===================
    public List<Employee> allDepartmentsEmployees(int departmentId, Map<String, Employee> empl) {
        return empl.values()
                .stream()
                .filter(s -> s.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    // определяем сотрудника с МАКСимальной з/п ===================
    public Employee whoHasMaxSalary(int departmentId, Map<String, Employee> empl) {
        return empl.values()
                .stream()
                .filter(s -> s.getDepartment() == departmentId)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    // определяем сотрудника с минимальной з/п ===================
    public Employee whoHasMinSalary(int departmentId, Map<String, Employee> empl) {
        return empl.values()
                .stream()
                .filter(s -> s.getDepartment() == departmentId)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }


}
