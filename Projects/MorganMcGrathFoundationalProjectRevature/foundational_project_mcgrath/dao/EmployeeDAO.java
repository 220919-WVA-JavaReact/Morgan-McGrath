package com.revature.foundational_project_morgan.dao;

import com.revature.foundational_project_morgan.models.Employee;

import java.util.List;

public interface EmployeeDAO {
    Employee getByUsername(String employeeUsername);


    Employee createEmployee(String employeeFirstname, String employeeLastname, String employeeUsername, String employeePassword);

    Employee promoteEmployee(String username);

    List<Employee> viewAllEmployees();

    Employee demoteEmployee(String username);


}
