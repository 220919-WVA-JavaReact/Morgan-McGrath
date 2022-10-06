package com.revature.foundational_project_morgan;

import com.revature.foundational_project_morgan.models.Employee;
import com.revature.foundational_project_morgan.service.EmployeeService;
import com.revature.foundational_project_morgan.service.RequestService;

import java.util.Scanner;

public class Foundation_Project {

    public static EmployeeService es = new EmployeeService();
    public static RequestService rs = new RequestService();


    public static void main(String[] args) {
        System.out.println("Press 1 to log in, press 2 to register.");
        //EmployeeService es = new EmployeeService();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        Employee loggedInEmployee = null;

        if (choice == 1) {
//            System.out.println("Please enter your Username: ");
//            String employeeUsername = scanner.nextLine();
//
//            System.out.println("Please enter your Password: ");
//            String employeePassword = scanner.nextLine();

//            es.login(employeeUsername, employeePassword);
            loggedInEmployee = es.login();

        } else if (choice == 2) {
//            System.out.println("Please enter first name: ");
//            String employeeFirstname = scanner.nextLine();
//
//            System.out.println("Please enter Lastname: ");
//            String employeeLastname = scanner.nextLine();
//
//            System.out.println("Please enter Username (max of 8 characters): ");
//            String employeeUsername = scanner.nextLine();
//
//            System.out.println("Please enter Password (max of 20 characters): ");
//            String employeePassword = scanner.nextLine();
            loggedInEmployee = es.register();
        }
        while (loggedInEmployee != null) {
            if (loggedInEmployee.isManager()) {
                System.out.println("Press 1 to create a reimbursement request, press 2 view all requests, press 3 to approve requests, press 4 to logout.");
                //swap 2 to view pending requests, 3 will allow you to complete (approve/deny) pending requests, matching via request ID
                //maybe have option 4 view all requests, sorted by user
                String subchoice = scanner.nextLine();
                switch (subchoice) {
                    case "1":
                    rs.createRequest(loggedInEmployee);
                    break;

                    case "2":
                        rs.getAllRequest();
                        break;

                    case "3":
                        rs.updateRequest();
                        break;

                    case "4":
                        System.out.println("Thanks for logging in!");
                        loggedInEmployee = null;
                        break;

                }
            } else if(!loggedInEmployee.isManager()) {
                System.out.println("Press 1 to create a reimbursement request, press 2 view your requests, press 3 to logout.");

                String subchoice = scanner.nextLine();

                switch (subchoice) {
                    case "1":
                        rs.createRequest(loggedInEmployee);
                        break;

                    case "2":
                        rs.getRequestByUsername(loggedInEmployee.getUsername());
                        break;

                    case "3":
                        System.out.println("See you next time!");
                        loggedInEmployee = null;
                        break;

                    default:
                        System.out.println("Invalid Input.");
                }
            }
        }
    }
}
