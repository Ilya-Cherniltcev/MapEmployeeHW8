package pro.sky.MapEmployeeHW8.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping
    public String HelloController() {
        return "Добро пожаловать в нашу базу данных работников!";
    }
}
