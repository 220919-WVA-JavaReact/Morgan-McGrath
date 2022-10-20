package com.revature.foundational_project_mcgrath.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundational_project_mcgrath.models.Employee;
import com.revature.foundational_project_mcgrath.models.Level;
import com.revature.foundational_project_mcgrath.models.Request;
import com.revature.foundational_project_mcgrath.service.RequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = req.getSession(false);
        if (session != null) {
            Employee loggedInEmployee = (Employee) session.getAttribute("auth-emp");
            if (req.getParameter("action").equals("get-tickets-by-status")) {
                if (loggedInEmployee.getLevel() != Level.Manager && loggedInEmployee.getLevel() != Level.Supervisor) {
                    resp.setStatus(403);
                    resp.getWriter().write("Sorry, only Managers and Supervisors have access to all tickets.");
                } else {
                    if (req.getParameter("status").equals("pending")) {
                        resp.setStatus(200);
                        resp.getWriter().write("The current pending tickets are: ");
                        List<Request> requests = rs.getAllManager("pending");
                        if (requests.size() > 0) {
                            for (Request r : requests) {
                                resp.getWriter().write(" / " + r.getReimbursement_id() + ": " + r.getTitle() + " for $" + r.getAmount() + " from " + r.getUsername());
                            }
                        }
                    } else if (req.getParameter("status").equals("approved")) {
                        resp.setStatus(200);
                        resp.getWriter().write("The current approved tickets are: ");
                        List<Request> requests = rs.getAllManager("approved");
                        if (requests.size() > 0) {
                            for (Request r : requests) {
                                resp.getWriter().write(" / " + r.getReimbursement_id() + ": " + r.getTitle() + " for $" + r.getAmount() + " from " + r.getUsername());
                            }
                        }
                    } else if (req.getParameter("status").equals("denied")) {
                        resp.setStatus(200);
                        resp.getWriter().write("The current denied tickets are: ");
                        List<Request> requests = rs.getAllManager("denied");
                        if (requests.size() > 0) {
                            for (Request r : requests) {
                                resp.getWriter().write(" / " + r.getReimbursement_id() + ": " + r.getTitle() + " for $" + r.getAmount() + " from " + r.getUsername());
                            }
                        }
                    }
                }
            } else if (req.getParameter("action").equals("get-tickets-associate")) {
                if (req.getParameter("status").equals("pending")) {
                    resp.setStatus(200);
                    resp.getWriter().write("The current pending tickets are: ");
                    List<Request> requests = rs.getAllUser(loggedInEmployee.getUsername(), "pending");
                    if (requests.size() > 0) {
                        for (Request r : requests) {
                            resp.getWriter().write(" / " + r.getReimbursement_id() + ": " + r.getTitle() + " for $" + r.getAmount() + " from " + r.getUsername());
                        }
                    } else {
                        resp.setStatus(200);
                        resp.getWriter().write("Looks like there aren't any tickets here");
                    }
                } else if (req.getParameter("status").equals("approved")) {
                    resp.setStatus(200);
                    resp.getWriter().write("The current approved tickets are: ");
                    List<Request> requests = rs.getAllUser(loggedInEmployee.getUsername(), "approved");
                    if (requests.size() > 0) {
                        for (Request r : requests) {
                            resp.getWriter().write(" / " + r.getReimbursement_id() + ": " + r.getTitle() + " for $" + r.getAmount() + " from " + r.getUsername());
                        }
                    } else {
                        resp.setStatus(200);
                        resp.getWriter().write("Looks like there aren't any tickets here");
                    }
                } else if (req.getParameter("status").equals("denied")) {
                    resp.setStatus(200);
                    resp.getWriter().write("The current denied tickets are: ");
                    List<Request> requests = rs.getAllUser(loggedInEmployee.getUsername(), "denied");
                    if (requests.size() > 0) {
                        for (Request r : requests) {
                            resp.getWriter().write(" / " + r.getReimbursement_id() + ": " + r.getTitle() + " for $" + r.getAmount() + " from " + r.getUsername());
                        }
                    } else {
                        resp.setStatus(200);
                        resp.getWriter().write("Looks like there aren't any tickets here");
                    }
                }
            }
        } else {
            resp.setStatus(401);
            resp.getWriter().write("Sorry, you must be logged in to use this feature");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Employee loggedInEmployee = (Employee) session.getAttribute("auth-emp");
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
            }
//            } else if (req.getParameter("action").equals("process")) {
//                Request request = mapper.readValue(req.getInputStream(), Request.class);
//                if (request.getUsername().equals("")) {
//                    resp.setStatus(400);
//                    resp.getWriter().write("Sorry, please make sure all information is correct.");
//                } else if (request.isApproval().equals("pending")){
//                        resp.setStatus(403);
//                        resp.getWriter().write("Sorry, please either deny or approve request");
//                } else {
//                    if (loggedInEmployee.getLevel() != Level.Manager && loggedInEmployee.getLevel() != Level.Supervisor) {
//                        resp.setStatus(403);
//                        resp.getWriter().write("Sorry, only Managers and Supervisors may process tickets.");
//                    } else {
//                        String responsePayload = mapper.writeValueAsString(request);
//                        boolean r = rs.updateRequest(request.getReimbursement_id(), request.isApproval());
//                        if (r) {
//                            resp.setStatus(200);
//                            resp.getWriter().write("Thank you, Request " + request.getReimbursement_id() + " has been processed and it's now " + request.isApproval());
//                        } else {
//                            resp.setStatus(403);
//                            resp.getWriter().write("Sorry, this ticket has already been processed");
//                        }
//                    }
//                }
//            }
        } else {
            resp.setStatus(401);
            resp.getWriter().write("Please make sure you are logged in");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Employee loggedInEmployee = (Employee) session.getAttribute("auth-emp");
            if (req.getParameter("action").equals("process")) {
                Request request = mapper.readValue(req.getInputStream(), Request.class);
                if (request.getUsername().equals("")) {
                    resp.setStatus(400);
                    resp.getWriter().write("Sorry, please make sure all information is correct.");
                } else if (request.isApproval().equals("pending")) {
                    resp.setStatus(403);
                    resp.getWriter().write("Sorry, please either deny or approve request");
                } else {
                    if (loggedInEmployee.getLevel() != Level.Manager && loggedInEmployee.getLevel() != Level.Supervisor) {
                        resp.setStatus(403);
                        resp.getWriter().write("Sorry, only Managers and Supervisors may process tickets.");
                    } else {
                        String responsePayload = mapper.writeValueAsString(request);
                        boolean r = rs.updateRequest(request.getReimbursement_id(), request.isApproval());
                        if (r) {
                            resp.setStatus(200);
                            resp.getWriter().write("Thank you, Request " + request.getReimbursement_id() + " has been processed and it's now " + request.isApproval());
                        } else {
                            resp.setStatus(403);
                            resp.getWriter().write("Sorry, this ticket has already been processed");
                        }
                    }
                }
            }
        } else {
            resp.setStatus(401);
            resp.getWriter().write("Sorry, you must be logged in to use this feature.");
        }
    }
}



