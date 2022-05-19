package pro.sky.MapEmployeeHW8.data;


import java.util.Objects;

// +++++++++++++++++++++++  класс -  работник  +++++++++++++++++++++++
public class Employee {
    private String firstName;
    private String lastName;
    private int department;
    private int salary;

    public Employee(String firstName, String lastName, int department, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
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


    @Override
    public String toString() {
        return firstName + " " + lastName + ", отдел " + department +
                ", з/п " + salary + " руб./мес";
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