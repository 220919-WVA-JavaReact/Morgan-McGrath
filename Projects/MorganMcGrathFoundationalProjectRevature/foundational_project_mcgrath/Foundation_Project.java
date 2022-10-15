package com.revature.foundational_project_mcgrath;

import com.revature.foundational_project_mcgrath.models.Employee;
import com.revature.foundational_project_mcgrath.service.EmployeeService;
import com.revature.foundational_project_mcgrath.service.RequestService;

import java.util.Scanner;

import static com.revature.foundational_project_mcgrath.models.Level.*;

//Look into enum replacement for the manager instead of boolean, look into hashing for basic encryption for your password

public class Foundation_Project {

    public static EmployeeService es = new EmployeeService();
    public static RequestService rs = new RequestService();


    public static void main(String[] args) {
        Employee loggedInEmployee = null;
        Scanner scanner = new Scanner(System.in);
        while (loggedInEmployee == null) {
            System.out.println("Press 1 to log in, press 2 to register.");
            //EmployeeService es = new EmployeeService();

            int choice = scanner.nextInt();
            scanner.nextLine();


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
        }
        while (loggedInEmployee != null) {
            if (loggedInEmployee.getLevel().equals(Supervisor)) {
                //System.out.println("Press 1 to create a reimbursement request, press 2 view all requests, press 3 to approve requests, press 4 to view all employees, press 5 to promote employees, press 6 to demote employees, press 7 to logout.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Press 1 to create a reimbursement request");
                System.out.println("Press 2 view all requests");
                System.out.println("Press 3 to view all pending requests");
                System.out.println("Press 4 to approve requests");
                System.out.println("Press 5 to view all employees");
                System.out.println("Press 6 to edit employee access");
                //System.out.println("Press 7 to demote employees");
                System.out.println("Press 7 to logout");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


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
                        rs.getAllPending();
                        break;

                    case "4":
                        rs.updateRequest();
                        break;

                    case "5":
                        es.viewAllEmployees();
                        break;

                    case "6":
                        es.updateEmployeeAccess();
                        break;

//                    case "7":
//                        es.demoteEmployee();
//                        break;

                    case "7":
                        System.out.println("Thanks for logging in!");
                        loggedInEmployee = null;
                        break;

                }
            } else if (loggedInEmployee.getLevel().equals(Associate)) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Press 1 to create a reimbursement request");
                System.out.println("Press 2 view your requests");
                System.out.println("Press 3 to view sort your requests by type");
                System.out.println("Press 4 to logout");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                String subchoice = scanner.nextLine();

                switch (subchoice) {
                    case "1":
                        rs.createRequest(loggedInEmployee);
                        break;

                    case "2":
                        rs.getRequestByUsername(loggedInEmployee.getUsername());
                        break;

                    case "3":
                        rs.getRequestByType(loggedInEmployee.getUsername());

                    case "4":
                        System.out.println("See you next time!");
                        loggedInEmployee = null;
                        break;

                    default:
                        System.out.println("Invalid Input.");
                }
            } else {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Press 1 to create a reimbursement request");
                    System.out.println("Press 2 view all requests");
                    System.out.println("Press 3 to view all pending requests");
                    System.out.println("Press 4 to approve requests");
                    System.out.println("Press 5 to view all employees");
                    System.out.println("Press 6 to logout");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


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
                            rs.getAllPending();

                        case "4":
                            rs.updateRequest();
                            break;

                        case "5":
                            es.viewAllEmployees();
                            break;

                        case "6":
                            System.out.println("Thanks for logging in!");
                            loggedInEmployee = null;
                            break;

                    }
                }
            }
        }
    }
