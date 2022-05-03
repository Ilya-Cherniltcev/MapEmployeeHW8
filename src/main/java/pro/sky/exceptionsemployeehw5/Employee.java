package pro.sky.exceptionsemployeehw5;


import java.util.Objects;

// +++++++++++++++++++++++  класс -  работник  +++++++++++++++++++++++
public class Employee {
    private String firstName;
    private String lastName;


    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // -------------------------- Геттеры  --------------------------
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // -------------------------- Метод toString  --------------------------
    @Override
    public String toString() {
        return "{" + firstName + '\'' +
                " " + lastName + '\'' +
                '}';
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