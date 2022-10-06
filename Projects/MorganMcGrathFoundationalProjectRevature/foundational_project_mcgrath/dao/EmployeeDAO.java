package com.revature.foundational_project_morgan.dao;

import com.revature.foundational_project_morgan.models.Employee;

public interface EmployeeDAO {
    Employee getByUsername(String employeeUsername);


    Employee createEmployee(String employeeFirstname, String employeeLastname, String employeeUsername, String employeePassword);


}
