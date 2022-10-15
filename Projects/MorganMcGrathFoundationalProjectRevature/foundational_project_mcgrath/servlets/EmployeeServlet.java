package com.revature.foundational_project_mcgrath.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundational_project_mcgrath.models.Employee;
import com.revature.foundational_project_mcgrath.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;



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
        // when we did our post request before, we basically parsed a Json string into a java object
        //now to do the reverse process

        // let's emulate a value that may come from some data source
        //AppUser someUser = new AppUser(123, "Jane", "Doe", "jd42@revature.com", "jd42", "password");
        Employee employee = new Employee();
        // We want to convert this Java obj into some sort of JSON string

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
        //POST requests are generally used for the creation of data in an application
        //System.out.println("[LOG] - UserServlet received a POST request at " + LocalDateTime.now());

        //To print out from input stream
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
//        String line;
//        while ((line = bufferedReader.readLine()) != null){
//            System.out.println(line);
//       }
        //ObjectMapper mapper = new ObjectMapper();
        if(req.getParameter("action").equals("login")) {
            Employee employee = mapper.readValue(req.getInputStream(), Employee.class);
            Employee emp = es.login(employee.getUsername(), employee.getPassword());
            String responsePayload = mapper.writeValueAsString(emp);
            if (responsePayload.equals("null")) {
                resp.getWriter().write("Sorry, that login was incorrect. Please check your credentials.");
            } else {
                resp.getWriter().write("Welcome back, " + emp.getFirst() + "! What would you like to do today?");
            }
        } else if (req.getParameter("action").equals("register")){
            Employee employee = mapper.readValue(req.getInputStream(), Employee.class);
            Employee emp = es.register(employee.getFirst(), employee.getLast(), employee.getUsername(), employee.getPassword());
            String responsePayload = mapper.writeValueAsString(emp); //+ "Your access level is " + emp.getLevel()
            if (responsePayload.equals("null")){
                resp.getWriter().write("Sorry, too many users with that name!");
            } else {
                resp.getWriter().write("Welcome, " + emp.getFirst() + "! Your access level is: " + emp.getLevel() + ". What would you like to do today?");
            }
//            if (!employee.getUsername().equals("")) {
//                String responsePayload = mapper.writeValueAsString("Thank you for registering " + emp.getFirst() + ". " + "Your access level is " + emp.getLevel());
//                resp.getWriter().write(responsePayload);
//            } else {
//                String responsePayload = mapper.writeValueAsString("Sorry, that username is taken. Try again");
//                resp.getWriter().write(responsePayload);
            }
//        } else if (req.getParameter("action").equals("logout")){
//
//        }



        //at this point newUser could be sent to a service layer for validation which would then send it to
        //the DAO layer to be created in the DB
//        System.out.println("Welcome, " + employee.getFirst());



//        Employee acc = es. updateEmployeeAccess(employee.getUsername(), employee.getLevel());
//        String rPayload = mapper.writeValueAsString(acc);
//        resp.getWriter().write(rPayload);
//        System.out.println(employee);


        //resp.setStatus(204);

    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
