package com.revature.foundational_project_mcgrath.models;

import java.util.Objects;

public class Employee {


    private int employee_id;
    private String first;
    private String last;
    private String username;
    private String password;
    private Level level;


    public Employee(int id, String first, String last, String username, Level level) {
        this.employee_id = id;
        this.first = first;
        this.last = last;
        this.username = username;
        this.level = level;
    }




    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Employee(int employee_id, String first, String last, String username, String password, Level level) {
        this.employee_id = employee_id;
        this.first = first;
        this.last = last;
        this.username = username;
        this.password = password;
        this.level = level;
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

    public Employee(int id, String first, String last, String username, String password, String access) {
    }
//    public Employee(int employee_id, String first, String last, String username, Level level) {
//        this.employee_id = employee_id;
//        this.first = first;
//        this.last = last;
//        this.username = username;
//        this.level = level;
//        this.password = "Information Withheld";
//        }

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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employee_id == employee.employee_id && first.equals(employee.first) && last.equals(employee.last) && username.equals(employee.username) && password.equals(employee.password) && level.equals(employee.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_id, first, last, username, password, level);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
                ", level='" + level + '\'' +
                '}';
    }



}
