package pro.sky.map_employee_hw8.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.map_employee_hw8.data.Employee;
import pro.sky.map_employee_hw8.interfaces.EmployeeInterface;
import pro.sky.map_employee_hw8.services.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeInterface employeeService;
    private final DepartmentService departmentService;
    public EmployeeController(EmployeeInterface employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    // ++++++++  добавить сотрудника
    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                                @RequestParam("department") int department, @RequestParam("salary") int salary) {
        return employeeService.addNewEmployee(firstName, lastName, department, salary);
    }

    // ???????????  найти сотрудника
    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    // ----------  удалить сотрудника
    @GetMapping(path = "/remove")
    public Employee deleteEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.deleteEmployee(firstName, lastName);
    }

    // * * * * * * *  сумма ежемесячных затрат на зарплату * * * * * * * *
    @GetMapping(path = "/allcost")
    public String allCostsPerMonth() {
        return "Общая сумма затрат на зарплаты сотрудникам в месяц "
                + employeeService.calcCostsPerMonth() + " руб.";
    }

    // * * * * * * *  сотрудник с МАКСимальной з/п * * * * * * * *
    @GetMapping(path = "/max-salary")
    public Employee getEmployeeOfMaxSalary(@RequestParam("departmentId") int departmentId) {
        return departmentService.whoHasMaxSalary(departmentId);
    }

    // * * * * * * *  сотрудник с минимальной з/п * * * * * * * *
    @GetMapping(path = "/min-salary")
    public Employee getEmployeeOfMinSalary(@RequestParam("departmentId") int departmentId) {
        return departmentService.whoHasMinSalary(departmentId);
    }

    // * * * * * * *  средняя  з/п * * * * * * * *
    @GetMapping(path = "/middlesalary")
    public String calcMiddleSalary() {
        return "Средняя зарплата сотрудников " + employeeService.calcMiddleSalary()
                + " руб./мес.";
    }

    // * * * * * * *  все сотрудники отдела* * * * * * * *
    @GetMapping(path = "/all", params = {"departmentId"})
    public List<Employee> getEmployeesOfDepartment(@RequestParam("departmentId") int departmentId) {
        return departmentService.allDepartmentsEmployees(departmentId);
    }

    // + + + + + + + +      ВСЕ СОТРУДНИКИ ПО ОТДЕЛАМ   + + + + + + + +
    @GetMapping(path = "/all")
    public Map<String, Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}