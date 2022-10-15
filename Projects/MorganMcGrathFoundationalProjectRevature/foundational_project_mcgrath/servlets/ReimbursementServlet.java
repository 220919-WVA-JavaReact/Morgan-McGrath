package com.revature.foundational_project_mcgrath.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class ReimbursementServlet extends HttpServlet {
    private final ObjectMapper mapper;

    public ReimbursementServlet(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setStatus(200); //200 by default
        resp.setHeader("Content-type", "text/plain");
        resp.setHeader("example-response-header", "some-example-value");
        resp.getWriter().write("Welcome to the project!\n");
        resp.getWriter().write("This site is under construction right now, but we will have it up and running soon!\n");
        resp.getWriter().write("Expect this to transform into a log in page, which will lead into a profile depending on access level\n");
        resp.getWriter().write("\nCurrent time: " + LocalDateTime.now());
    }
}
