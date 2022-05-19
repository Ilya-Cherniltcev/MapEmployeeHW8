package pro.sky.map_employee_hw8.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping
    public String helloController() {
        return "Добро пожаловать в нашу базу данных работников!";
    }
}
