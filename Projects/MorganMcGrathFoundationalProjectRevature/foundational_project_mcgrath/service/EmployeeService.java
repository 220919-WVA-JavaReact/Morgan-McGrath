package com.revature.foundational_project_mcgrath.service;

import com.revature.foundational_project_mcgrath.dao.EmployeeDAO;
import com.revature.foundational_project_mcgrath.dao.EmployeeDAOImplPostgres;
import com.revature.foundational_project_mcgrath.models.Employee;
import com.revature.foundational_project_mcgrath.models.Level;

import java.util.List;
import java.util.Scanner;

import static com.revature.foundational_project_mcgrath.models.Level.*;
//import org.jetbrains.annotations.NotNull;

public class EmployeeService {
    EmployeeDAO ed = new EmployeeDAOImplPostgres();
    Scanner scanner = new Scanner(System.in);

    public Employee login() {
        //Employee emp = ed.getByUsername(username);
        System.out.println("Please enter your Username: ");
        String username = scanner.nextLine();

        System.out.println("Please enter your Password: ");
        String password = scanner.nextLine();

        Employee emp = ed.getByUsername(username);


            if (emp.getPassword().equals(password)) {
                System.out.println("Thank you for logging in.");
                System.out.println("Welcome, " + emp.getFirst() + "!");

                return emp;
                } else {
                System.out.println("Sorry, that information is incorrect.");
                return null;
            }
    }

    public Employee login(String username, String password) throws NullPointerException{
        Employee employee = ed.getByUsername(username);
        if (employee.getPassword().equals(password)) {
//            System.out.println("Thank you for logging in.");
//            System.out.println("Welcome, " + employee.getFirst() + "!");

            return employee;
        } else {
//            System.out.println("Sorry, that information is incorrect.");
            return null;
        }
    }



    public Employee register(){

        System.out.println("Please enter Firstname: ");
        String first = scanner.nextLine();
        while (first.equals("")){
            System.out.println("Please enter your first name");
            first = scanner.nextLine();
        }


        System.out.println("Please enter Lastname: ");
        String last = scanner.nextLine();
        while (last.equals("")) {
            System.out.println("Please enter Lastname: ");
            last = scanner.nextLine();
        }
        System.out.println("Please enter Username (max of 8 characters): ");
        String username = scanner.nextLine();
        while (username.equals("")) {
            System.out.println("Please enter Username (max of 8 characters): ");
            username = scanner.nextLine();
        }

        System.out.println("Please enter Password (max of 20 characters): ");
        String password = scanner.nextLine();
        while (password.equals("")) {
            System.out.println("Please enter Password (max of 20 characters): ");
            password = scanner.nextLine();
        }

        Employee emp = ed.createEmployee(first, last, username, password);
        return emp;

    }

    public Employee register(String first, String last, String username, String password) {
        return ed.createEmployee(first, last, username, password);
//        if (!employee.getUsername().equals("")){
//            return employee;
//        }
//        return null;
    }

//    public Employee register (String first, String last, String username, String password){
//        Employee employee = ed.createEmployee(first, last, username, password);
//        return employee;
//    }

    public void updateEmployeeAccess() {

        System.out.println("Please enter employee you wish to edit ");
        String username = scanner.nextLine();
        while (username.equals("")) {
            System.out.println("Please enter the username of the employee you wish to edit: ");
            // starting to put together a demotion tool
            username = scanner.nextLine();
        }
        System.out.println("Which access level are you granting the employee?: ");
        System.out.println("Press 1 for Associate");
        System.out.println("Press 2 for Manager");
        System.out.println("Press 3 for Supervisor");
        String choice = scanner.nextLine();

        Level newAccess;
        if (choice.equals("1")) {
            newAccess = Associate;
        } else if (choice.equals("2")) {
            newAccess = Manager;
        } else {
            newAccess = Supervisor;
        }



        Employee emp = ed.updateEmployeeAccess(username, newAccess);

    }

    public Employee updateEmployeeAccess(String username, Level access){
        System.out.println("Please input the employee you wish to edit and the new level of access");
        Employee emp = ed.updateEmployeeAccess(username, access);
//        if (emp.getLevel().equals(Associate)){
//            System.out.println("Sorry, only managers and supervisors can promote employees");
//        }
        return emp;
    }



    public void viewAllEmployees(){
        System.out.println("+--------------------+\n" + "List of Employees" + "\n+--------------------+");
        List<Employee> employees = ed.viewAllEmployees();
        for(Employee employee : employees) {
            System.out.println("ID #: " + employee.getEmployee_id() + "\n Username: " + employee.getUsername() + "\n Name: " + employee.getFirst() + " " + employee.getLast() + "\n Access Level: " + employee.getLevel() + "\n+---------------+");
            //System.out.println(employee);
        }
    }
    public Employee getEmployee(String username){
        return ed.getByUsername(username);
    }




}
