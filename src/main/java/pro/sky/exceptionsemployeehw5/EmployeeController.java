package pro.sky.exceptionsemployeehw5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeInterface employeeService;

    public EmployeeController(EmployeeInterface employeeService) {
        this.employeeService = employeeService;
    }
// ----------------------------------------------------------------------------

    @GetMapping(path = "/add")  // ++++++++  добавить сотрудника
    public Employee addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.addNewEmployee(firstName, lastName);
    }

    @GetMapping(path = "/find")  // ???????????  найти сотрудника
    public Employee findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping(path = "/remove")  // ----------  удалить сотрудника
    public Employee deleteEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.deleteEmployee(firstName, lastName);
    }

}