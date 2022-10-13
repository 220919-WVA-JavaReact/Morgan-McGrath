package com.revature.foundational_project_morgan.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundational_project_morgan.dao.EmployeeDAOImplPostgres;
import com.revature.foundational_project_morgan.models.Employee;
import com.revature.foundational_project_morgan.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;



public class EmployeeServlet extends HttpServlet {

    EmployeeDAOImplPostgres ed;
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
        System.out.println("[LOG] - UserServlet received a POST request at " + LocalDateTime.now());

        //To print out from input stream
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
//        String line;
//        while ((line = bufferedReader.readLine()) != null){
//            System.out.println(line);
//       }
        //ObjectMapper mapper = new ObjectMapper();
        Employee employee = mapper.readValue(req.getInputStream(), Employee.class);
        Employee emp = es.login(employee.getUsername(), employee.getPassword());
        String responsePayload = mapper.writeValueAsString(emp);
        resp.getWriter().write(responsePayload);
        //at this point newUser could be sent to a service layer for validation which would then send it to
        //the DAO layer to be created in the DB
        System.out.println(employee);


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
