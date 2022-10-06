package com.revature.foundational_project_morgan.service;


import com.revature.foundational_project_morgan.dao.RequestDAO;
import com.revature.foundational_project_morgan.dao.RequestDAOImplPostgres;
import com.revature.foundational_project_morgan.models.Employee;
import com.revature.foundational_project_morgan.models.Request;

import java.util.List;
import java.util.Scanner;

import static com.revature.foundational_project_morgan.Foundation_Project.es;

public class RequestService {
    Scanner scanner = new Scanner(System.in);

    RequestDAO rd = new RequestDAOImplPostgres();


    public void createRequest(Employee loggedinEmployee){
//        System.out.println("Enter Request ID: ");
//        int reimbursementID = scanner.nextInt();
//        String space = scanner.nextLine();

//        System.out.println("Enter Username associated with the Request: ");
//        String username = scanner.nextLine();

        System.out.println("Enter Title of Reimbursement Request: ");
        String title = scanner.nextLine();

        System.out.println("Enter amount requested for Reimbursement: ");
        int amount = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter location transaction occurred at (ex. website name, restaurant): ");
        String location = scanner.nextLine();

        rd.createRequest(loggedinEmployee.getUsername(), title, amount, location);

    }

    public void getRequestByUsername(String username){
        //System.out.println(rd.getRequestByUsername(username));
        List<Request> requests = rd.getRequestByUsername(username);
        for (Request request : requests){
            System.out.println(request);
        }
    }



    public void getAllRequest(){
        List<Request> requests = rd.getAllRequest();
        for (Request request : requests){
            System.out.println(request);
        }

    }

    public void updateRequest(){
        String approval_status;
        System.out.println("Please enter the username you'd like to check: ");
        String username = scanner.nextLine();

        System.out.println("Please enter the id of the request: ");
        int reimbursement_id = scanner.nextInt();
        scanner.nextLine();

//        int request = scanner.nextInt();
//        //note: requests will be the array of pending requests
//        getRequestByID
//        requests.get(request);
//        System.out.println(requests.get(request));
//
        System.out.println("Press 1 to approve this request, press 2 to deny it.");
        String choice = scanner.nextLine();
        //SetRequestmanager
        if(choice.equals("1")){
            approval_status = "approved";
            rd.updateRequest(reimbursement_id, approval_status);
            System.out.println("This request has been approved.");
        } else if(choice.equals("2")){
            approval_status = "denied";
            rd.updateRequest(reimbursement_id, approval_status);
            System.out.println("This request has been denied.");
        } else {
            System.out.println("Please enter a valid choice.");
        }

    }

}
