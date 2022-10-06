package com.revature.foundational_project_morgan.models;

import java.util.Objects;

public class Employee {
    private int employee_id;
    private String first;
    private String last;
    private String username;
    private String password;
    private boolean manager = false;

//    public Employee(int employeeID, String employeeFirstname, String employeeLastname, String employeeUsername, String employeePassword) {
//        this.EmployeeID = employeeID;
//        this.employeeFirstname = employeeFirstname;
//        this.employeeLastname = employeeLastname;
//        this.employeeUsername = employeeUsername;
//        this.employeePassword = employeePassword;
//    }

    public Employee(int employee_id, String first, String last, String username, String password, boolean manager) {
        this.employee_id = employee_id;
        this.first = first;
        this.last = last;
        this.username = username;
        this.password = password;
        this.manager = manager;
    }

    public Employee() {

    }

    public Employee(int employee_id, String first, String last, String username, String password) {
        this.employee_id = employee_id;
        this.first = first;
        this.last = last;
        this.username = username;
        this.password = password;
    }
    public Employee(int employee_id, String first, String last, String username, boolean manager) {
        this.employee_id = employee_id;
        this.first = first;
        this.last = last;
        this.username = username;
        this.manager = manager;
        this.password = "Information Withheld";

    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employee_id == employee.employee_id && manager == employee.manager && first.equals(employee.first) && last.equals(employee.last) && username.equals(employee.username) && password.equals(employee.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_id, first, last, username, password, manager);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", manager=" + manager +
                '}';
    }
}
