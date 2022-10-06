package com.revature.foundational_project_morgan.service;

import com.revature.foundational_project_morgan.dao.EmployeeDAO;
import com.revature.foundational_project_morgan.dao.EmployeeDAOImpl;
import com.revature.foundational_project_morgan.dao.EmployeeDAOImplPostgres;
import com.revature.foundational_project_morgan.models.Employee;

import java.util.Scanner;
//import org.jetbrains.annotations.NotNull;

public class EmployeeService {
    EmployeeDAO ed = new EmployeeDAOImplPostgres();
    Scanner scanner = new Scanner(System.in);

    public Employee login(){
        //Employee emp = ed.getByUsername(username);
        System.out.println("Please enter your Username: ");
        String username = scanner.nextLine();

        System.out.println("Please enter your Password: ");
        String password = scanner.nextLine();

        Employee emp = ed.getByUsername(username);

        if(emp.getPassword().equals(password)){
            System.out.println("Thank you for logging in.");
            System.out.println(emp);

            return emp;
        } else {
            System.out.println("Sorry, that information is incorrect.");
            return null;
        }

    }

    public Employee register(){
        System.out.println("Please enter Firstname: ");
        String first = scanner.nextLine();

        System.out.println("Please enter Lastname: ");
        String last = scanner.nextLine();

        System.out.println("Please enter Username (max of 8 characters): ");
        String username = scanner.nextLine();

        System.out.println("Please enter Password (max of 20 characters): ");
        String password = scanner.nextLine();

//        System.out.println("If you are a manager press 1, if you are not please press 2: ");
//        int manager = scanner.nextInt();
//        boolean Manager = manager == 1;
        Employee emp = ed.createEmployee(first, last, username, password);
        return emp;

    }


}
