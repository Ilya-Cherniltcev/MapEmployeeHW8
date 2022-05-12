package pro.sky.MapEmployeeHW8.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.MapEmployeeHW8.data.Employee;
import pro.sky.MapEmployeeHW8.interfaces.EmployeeInterface;

import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeInterface employeeService;

    public EmployeeController(EmployeeInterface employeeService) {
        this.employeeService = employeeService;
    }

// ++++++++  добавить сотрудника
    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.addNewEmployee(firstName, lastName);
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


    @GetMapping(path = "/printall")
    public Map<Employee, Integer> printAllEmployees() {
        return employeeService.printAllEmployees();
    }

}