package pro.sky.map_employee_hw8;

import pro.sky.map_employee_hw8.data.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapEmployeeTestConstants {
     public static final List<Employee> EMPLOYEE_LIST = new ArrayList(List.of (
             "Евгений", "Потапов", 1, 80_000,
             "Алла", "Горева", 1, 53_050,
             "Олег", "Крылов", 2, 74_000,
             "Илья", "Круглов", 2, 105_300,
             "Антон", "Тулупов", 3, 63_700,
             "Семен", "Кузнецов", 3, 123_000));
     // ----------------------------------------------------
     public static final Employee NEW_EMPLOYEE = new Employee(
             "Сергей", "Пахомов", 2, 82_082);
     public static final String NEW_EMPLOYEE_NAME = "Сергей";
     public static final String NEW_EMPLOYEE_LASTNAME = "Пахомов";
     public static final int NEW_EMPLOYEE_DEPARTMENT = 2;
     public static final int NEW_EMPLOYEE_SALARY = 82_082;
     // ----------------------------------------------------
     public static final String INCORRECT_NAME = "Иван777";
     public static final int COST_PER_MONTH = 80000 + 53050 + 74000 + 105300 + 63700 + 123000;
     public static final int MIDDLE_SALARY = (int)COST_PER_MONTH / 6;

     // ---------------------------------------------------
     public static final Employee EXIST_EMPLOYEE = new Employee(
             "Евгений", "Потапов", 1, 80_000);
     public static final String EXIST_EMPLOYEE_NAME = "Евгений";
     public static final String EXIST_EMPLOYEE_LASTNAME = "Потапов";
     // ----------------------------------------------------


}
