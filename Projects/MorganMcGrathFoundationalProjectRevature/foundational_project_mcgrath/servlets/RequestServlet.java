package com.revature.foundational_project_mcgrath.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundational_project_mcgrath.models.Request;
import com.revature.foundational_project_mcgrath.service.RequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class RequestServlet extends HttpServlet {
    private final ObjectMapper mapper;
    RequestService rs = new RequestService();

    public RequestServlet(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        resp.setStatus(200); //200 by default
//        resp.setHeader("Content-type", "text/plain");
//        resp.setHeader("example-response-header", "some-example-value");
//        resp.getWriter().write("Welcome to the project!\n");
//        resp.getWriter().write("This site is under construction right now, but we will have it up and running soon!\n");
//        resp.getWriter().write("Expect this to transform into a log in page, which will lead into a profile depending on access level\n");
//        resp.getWriter().write("\nCurrent time: " + LocalDateTime.now());

//        if (req.getParameter("action").equals("pending")){
//            Request request = mapper.readValue(req.getInputStream(), Request.class);
//            if (request.getUsername().equals("")){
//                resp.setStatus(400);
//                resp.getWriter().write("Sorry, make sure all information is entered correctly");
//            } else {
//                resp.setStatus(200);
//                Request r = rs.getAllPending(request.getUsername());
//                String responsePayload = mapper.writeValueAsString(r);
//                resp.getWriter().write("Your pending tickets are: ");
//                resp.getWriter().write(r.getReimbursement_id() + ": " + r.getTitle() + " for " + r.getAmount());
//            }
//
//        } else if (req.getParameter("action").equals("all")){
//            System.out.println("Pending, ignore this");
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equals("submit")) {
            Request request = mapper.readValue(req.getInputStream(), Request.class);
            if (request.getTitle().equals("") | request.getAmount() <= 0) {
                resp.setStatus(400);
                resp.getWriter().write("Sorry, please make sure that you've correctly entered all necessary information");
            } else {
                resp.setStatus(201);
                Request r = rs.createRequest(request.getUsername(), request.getTitle(), request.getAmount(), request.getLocation(), request.getType());
                String responsePayload = mapper.writeValueAsString(r);
                resp.getWriter().write("Thanks for your submission! " + r.getReimbursement_id() + " has been created and is currently " + r.isApproval());
            }
        } else if (req.getParameter("action").equals("pending")) {
            Request request = mapper.readValue(req.getInputStream(), Request.class);
            if (request.getUsername().equals("")) {
                resp.setStatus(400);
                resp.getWriter().write("Sorry, make sure all information is entered correctly");
            } else {
                resp.setStatus(200);
                resp.getWriter().write("Your pending tickets are: ");
                List<Request> requests = rs.getAllPending(request.getUsername());
                if (requests.size() > 0) {
                    for (Request r : requests) {
                        String responsePayload = mapper.writeValueAsString(request);
                        resp.getWriter().write(r.getReimbursement_id() + ": " + r.getTitle() + " for $" + r.getAmount() + "/ ");
                    }

                } else {
                    resp.getWriter().write("Looks like all your requests have been processed");

                }
            }
        } else if (req.getParameter("action").equals("all")) {
            Request request = mapper.readValue(req.getInputStream(), Request.class);
            if (request.isApproval().equals("")) {
                resp.setStatus(400);
                resp.getWriter().write("Sorry, make sure all information is entered correctly");
            } else {
                resp.setStatus(200);
                resp.getWriter().write("The requested tickets are: ");
                List<Request> requests = rs.getRequestByApproval(request.isApproval());
                for (Request r : requests) {
                    String responsePayload = mapper.writeValueAsString(r);
                    resp.getWriter().write(r.getReimbursement_id() + ": " + r.getTitle() + " for $" + r.getAmount() + " from " + r.getUsername() + " / ");
                }
            }
        } else if (req.getParameter("action").equals("process")){
            Request request = mapper.readValue(req.getInputStream(), Request.class);
            if (request.getUsername().equals("")){
                resp.setStatus(400);
                resp.getWriter().write("Sorry, please make sure all information is correct.");
            } else {
                resp.setStatus(200);
                String responsePayload = mapper.writeValueAsString(request);
                Request r = rs.updateRequest(request.getReimbursement_id(), request.isApproval());
                resp.getWriter().write("Thank you, Request " + request.getReimbursement_id() + " has been processed and it's now " + request.isApproval());
            }
        }
    }
}

