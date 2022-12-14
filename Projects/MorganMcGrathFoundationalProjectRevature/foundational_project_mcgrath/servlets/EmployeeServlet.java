package com.revature.foundational_project_mcgrath.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundational_project_mcgrath.models.Employee;
import com.revature.foundational_project_mcgrath.models.Level;
import com.revature.foundational_project_mcgrath.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;


public class EmployeeServlet extends HttpServlet {

    EmployeeService es = new EmployeeService();

    private final ObjectMapper mapper;
    public EmployeeServlet(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void init() throws ServletException {
        System.out.println("[LOG] - EmployeeServlet Instantiated");
        System.out.println("[LOG] - Init param employee-servlet-key: " + this.getServletConfig().getInitParameter("employee-servlet-key"));
        System.out.println("[LOG] - Context param test-init-key: " + this.getServletContext().getInitParameter("test-context-key"));
    }

    @Override
    public void destroy() {
        System.out.println("[LOG] - EmployeeServlet Destroyed");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - EmployeeServlet received a GET request at " + LocalDateTime.now());
        Employee employee = new Employee();


        String respPayload = mapper.writeValueAsString(employee);

        resp.setStatus(200); //default status
        resp.setContentType("application/json");
        resp.getWriter().write(respPayload);
        resp.setHeader("Content-type", "text/plain");
        resp.setHeader("example-response-header", "some-example-value");
        resp.getWriter().write("Welcome, please log in to continue.");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(req.getParameter("action").equals("login")) {
            if (session != null){
                resp.setStatus(403);
                resp.getWriter().write("Sorry, you are already logged in");
            } else {
                Employee employee = mapper.readValue(req.getInputStream(), Employee.class);
                Employee emp = es.login(employee.getUsername(), employee.getPassword());
                session = req.getSession();
                session.setAttribute("auth-emp", emp);
                String responsePayload = mapper.writeValueAsString(emp);
                if (responsePayload.equals("null")) {
                    resp.setStatus(400);
                    resp.getWriter().write("Sorry, that login was incorrect. Please check your credentials.");
                }  else {
                    resp.setStatus(200);
                    resp.getWriter().write("Welcome back, " + emp.getFirst() + "! What would you like to do today?");
                }
            }
        } else if (req.getParameter("action").equals("register")) {
            if (session != null) {
                resp.setStatus(403);
                resp.getWriter().write("Sorry, you are already logged in");
            } else {
                Employee employee = mapper.readValue(req.getInputStream(), Employee.class);
                Employee emp = es.register(employee.getFirst(), employee.getLast(), employee.getUsername(), employee.getPassword());
                String responsePayload = mapper.writeValueAsString(emp);
                if (responsePayload.equals("null")) {
                    resp.setStatus(409);
                    resp.getWriter().write("Sorry, too many users with that name!");
                } else if (employee.getFirst().equals("") || employee.getLast().equals("") || employee.getPassword().equals("") || employee.getUsername().equals("")) {
                    resp.setStatus(400);
                    resp.getWriter().write("Please make sure you have entered the required information");
                } else if (employee.getUsername().equals("coffee") && employee.getPassword().equals("teapots")) {
                    resp.setStatus(418);
                    resp.getWriter().write("Sorry, teapots can't be used for coffee");
                } else {
                    resp.setStatus(201);
                    session = req.getSession();
                    session.setAttribute("auth-emp", emp);
                    resp.getWriter().write("Welcome, " + emp.getFirst() + "! Your access level is: " + emp.getLevel() + ". What would you like to do today?");
                }
            }
        }
    }





    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Employee loggedInEmployee = (Employee) session.getAttribute("auth-emp");
            if (req.getParameter("action").equals("promotion")) {
                HashMap<String, Object> jsonInput = mapper.readValue(req.getInputStream(), HashMap.class);
                Employee employee = es.getEmployee((String) jsonInput.get("username"));
                if (!loggedInEmployee.getLevel().equals(Level.Manager) && !loggedInEmployee.getLevel().equals(Level.Supervisor)){
                    resp.setStatus(403);
                    resp.getWriter().write("Sorry, only Managers and Supervisors may adjust access level.");
                } else if (!employee.getLevel().equals(Level.valueOf((String) jsonInput.get("level")))) {
                    Employee emp = es.updateEmployeeAccess((String) jsonInput.get("username"), Level.valueOf((String) jsonInput.get("level")));
                    String responsePayload = mapper.writeValueAsString((emp));
                    if (responsePayload.equals("null")) {
                        resp.setStatus(400);
                        resp.getWriter().write("Sorry, please make sure the username and access level is correct");
                    } else {
                        resp.setStatus(200);
                        resp.getWriter().write("Thank you, " + emp.getFirst() + "'s access has been updated to " + emp.getLevel());
                    }
                } else {
                    resp.setStatus(400);
                    resp.getWriter().write("Sorry, " + employee.getFirst() + "'s access level is already " + employee.getLevel());
                }
            }
        } else {
            resp.setStatus(401);
            resp.getWriter().write("Sorry, you must be logged in to use this feature.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null){
            session.invalidate();
            resp.getWriter().write("Thanks for logging in! See you next time!");
        } else {
            resp.setStatus(401);
        }
    }
}
