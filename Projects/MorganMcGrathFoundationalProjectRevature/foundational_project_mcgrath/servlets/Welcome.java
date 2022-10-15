package com.revature.foundational_project_mcgrath.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class Welcome extends HttpServlet {
    @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    //we'll just add some print line statements to check some info about the
    //request
    System.out.println("[LOG] - WelcomeServlet received a request at " + LocalDateTime.now());
    System.out.println("[LOG] - Request URI: " + req.getRequestURI());
    System.out.println("[LOG] - Request Method: " + req.getMethod());
    System.out.println("[LOG] - Request Header, example: " + req.getHeader("example"));


    //We'll formulate a basic response to send back
    resp.setStatus(200); //200 by default
    resp.setHeader("Content-type", "text/plain");
    resp.setHeader("example-response-header", "some-example-value");
    resp.getWriter().write("Welcome to the project!\n");
    resp.getWriter().write("This site is under construction right now, but we will have it up and running soon!\n");
    resp.getWriter().write("Expect this to transform into a log in page, which will lead into a profile depending on access level\n");
    resp.getWriter().write("\nCurrent time: " + LocalDateTime.now());

    resp.setContentType("text/html; charset=UTF-8");

    String url = ("");

//    = new Login();

}
}
