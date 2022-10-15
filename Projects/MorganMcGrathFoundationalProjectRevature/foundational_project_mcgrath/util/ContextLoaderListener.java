package com.revature.foundational_project_mcgrath.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundational_project_mcgrath.servlets.EmployeeServlet;
import com.revature.foundational_project_mcgrath.servlets.ReimbursementServlet;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import java.time.LocalDateTime;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {



//        try{
//            Class.forName("org.postgresql.Driver");
//            conn = DriverManager.getConnection("jdbc:postgresql://database-1.c6ui37l0rhvg.us-east-1.rds.amazonaws.com:5432/postgres", "postgres", "f0xtr0t487");
//        } catch (SQLException |ClassNotFoundException e){
//            System.out.println("Couldn't establish connection");
//            e.printStackTrace();
//        }


        System.out.println("[LOG] - The servlet context was initialized at " + LocalDateTime.now());
    //we can also programmatically define/register our servlets with the container here
        ObjectMapper mapper = new ObjectMapper();
        EmployeeServlet employeeServlet = new EmployeeServlet(mapper);
        ReimbursementServlet reimbursementServlet = new ReimbursementServlet(mapper);

        //now I need to register it with the context itself
        ServletContext context = sce.getServletContext();
        ServletRegistration.Dynamic registeredEmployeeServlet = context.addServlet("EmployeeServlet", employeeServlet);
        ServletRegistration.Dynamic registeredReimbursementServlet = context.addServlet("ReimbursementServlet", reimbursementServlet);



        registeredEmployeeServlet.addMapping("/employee");
        registeredEmployeeServlet.setLoadOnStartup(2);
//        registeredEmployeeServlet.setInitParameter("employee-servlet-key", "employee-init-value");
//        registeredEmployeeServlet.setInitParameter("another-key", "another-value");


        registeredReimbursementServlet.addMapping("/reimbursement");
        registeredReimbursementServlet.setLoadOnStartup(3);
//        registeredReimbursementServlet.setInitParameter("reimbursement-servlet-key", "reimbursement-init-value");


        /*
        Example Servlet exampleServlet = new ExampleServlet(mapper);
        context.addServlet("Example Servlet", exampleServlet).addMapping("/examples")


         */

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was destroyed at " + LocalDateTime.now());
    }
}
