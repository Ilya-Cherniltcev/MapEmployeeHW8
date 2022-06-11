package pro.sky.map_employee_hw8.data;


import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Objects;

// +++++++++++++++++++++++  класс -  работник  +++++++++++++++++++++++
public class Employee {
    private final String firstName;
    private final String lastName;
    private int department;
    private int salary;

    public Employee(String firstName, String lastName, int department, int salary) {
        this.firstName = StringUtils.capitalize(StringUtils.lowerCase(firstName));
        this.lastName = StringUtils.capitalize(StringUtils.lowerCase(lastName));
        this.department = department;
        this.salary = salary;
    }

    // -------------------------- Геттеры  --------------------------
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    // -------------------------- Сеттеры  --------------------------
    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    // -------------------------- Метод toString  --------------------------

//***
    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + department +
                ", " + salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}