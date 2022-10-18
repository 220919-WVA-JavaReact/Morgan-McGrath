package com.revature.foundational_project_mcgrath.service;


import com.revature.foundational_project_mcgrath.dao.RequestDAO;
import com.revature.foundational_project_mcgrath.dao.RequestDAOImplPostgres;
import com.revature.foundational_project_mcgrath.models.Employee;
import com.revature.foundational_project_mcgrath.models.Request;

import java.util.List;
import java.util.Scanner;

public class RequestService {
    Scanner scanner = new Scanner(System.in);

    RequestDAO rd = new RequestDAOImplPostgres();


    public void createRequest(Employee loggedinEmployee){
//        System.out.println("Enter Request ID: ");
//        int reimbursementID = scanner.nextInt();
//        String space = scanner.nextLine();

//        System.out.println("Enter Username associated with the Request: ");
//        String username = scanner.nextLine();


        System.out.println("Please enter a title of the request");
        String title = scanner.nextLine();
        while (title.equals("")){
            System.out.println("Please enter a title of the request");
            title = scanner.nextLine();
        }
        System.out.println("Enter amount requested for Reimbursement: ");
        int amount = scanner.nextInt();
        while (amount <= 0) {
            System.out.println("Enter amount requested for Reimbursement: ");
            amount = scanner.nextInt();
        }
        scanner.nextLine();

        System.out.println("Enter location transaction occurred at (ex. website name, restaurant): ");
        String location = scanner.nextLine();
        while (location.equals("")) {
            System.out.println("Enter location transaction occurred at (ex. website name, restaurant): ");
            location = scanner.nextLine();
        }



        System.out.println("Please enter type of request: ");
        System.out.println("   1) Travel");
        System.out.println("   2) Lodging");
        System.out.println("   3) Food");
        System.out.println("   4) Other");
        String choice = scanner.nextLine();

        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")) {
            System.out.println("Please enter type of request: ");
            System.out.println("   1) Travel");
            System.out.println("   2) Lodging");
            System.out.println("   3) Food");
            System.out.println("   4) Other");
            choice = scanner.nextLine();
            System.out.println("Please input one of the above options.");

        }
            String type;
            if (choice.equals("1")) {
                type = "Travel";
            } else if (choice.equals("2")) {
                type = "Lodging";
            } else if (choice.equals("3")) {
                type = "Food";
            } else {
                type = "Other";
            }

            rd.createRequest(loggedinEmployee.getUsername(), title, amount, location, type);


    }

    public Request createRequest(String username, String title, int amount, String location, String type){
        return rd.createRequest(username, title, amount, location, type);
    }

    public void getRequestByType(String username){
        //System.out.println(rd.getRequestByUsername(username));
        System.out.println("Which type of request?");
        String choice = null;
        while (choice.equals(null) || !choice.equals("1") || !choice.equals("2") || !choice.equals("3") || !choice.equals("4")) {
            System.out.println("Please enter type of of request: ");
            System.out.println("   1) Travel");
            System.out.println("   2) Lodging");
            System.out.println("   3) Food");
            System.out.println("   4) Other");
            choice = scanner.nextLine();
            if (choice.equals(null) || !choice.equals("1") || !choice.equals("2") || !choice.equals("3") || !choice.equals("4")){
                System.out.println("Please input one of the above options.");
            }
        }
        String type;
        if (choice.equals("1")) {
            type = "Travel";
        } else if (choice.equals("2")) {
            type = "Lodging";
        } else if (choice.equals("3")) {
            type = "Food";
        } else {
            type = "Other";
        }


        List<Request> requests = rd.getRequestByType(username, type);
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

    public List<Request> getRequestByApproval(String approval){
        List<Request> requests = rd.getRequestByApproval(approval);
        return requests;
    }



    public void getAllPending(){
        List<Request> requests = rd.getAllPending();
        if (requests.size() > 0) {
            for (Request request : requests) {
                System.out.println(request);
            }
        } else {
            System.out.println("Sorry, there are no pending requests.");
        }
    }

    public List<Request> getAllPending(String username){
        List<Request> requests  = rd.getAllPending();
        return requests;
    }

    public void updateRequest(){
        String approval_status;
        System.out.println("Please enter the username you'd like to check: ");
        String username = scanner.nextLine();

        System.out.println("Please enter the id of the request: ");
        int reimbursement_id = scanner.nextInt();
        scanner.nextLine();
        Request request = rd.getRequestById(reimbursement_id);
//        int request = scanner.nextInt();
//        //note: requests will be the array of pending requests
//        getRequestByID
//        requests.get(request);
//        System.out.println(requests.get(request));

//
        if (request.isApproval().equals("pending")) {
            System.out.println("Press 1 to approve this request, press 2 to deny it.");
            String choice = scanner.nextLine();
            //SetRequestmanager
            if (choice.equals("1")) {
                approval_status = "approved";
                rd.updateRequest(reimbursement_id, approval_status);
                System.out.println("This request has been approved.");
            } else if (choice.equals("2")) {
                approval_status = "denied";
                rd.updateRequest(reimbursement_id, approval_status);
                System.out.println("This request has been denied.");
            } else {
                System.out.println("Please enter a valid choice.");
            }
        } else {
            System.out.println("A verdict has been reached.");
        }

    }

    public Request updateRequest(int reimbursement_id, String approval){
        return rd.updateRequest(reimbursement_id, approval);
    }
    public void getRequestByUsername (String username){
        List<Request> requests = rd.getRequestByUsername(username);
        for (Request request : requests){
            System.out.println(request);
        }
    }

    public Request getRequestById (int reimbursement_id){
        Request request = rd.getRequestById(reimbursement_id);

            return request;

    }

}
