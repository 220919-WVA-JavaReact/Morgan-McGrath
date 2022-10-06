package com.revature.foundational_project_morgan.dao;

import com.revature.courses.dao.CourseDAO;
import com.revature.foundational_project_morgan.models.Request;


import java.util.List;

public class RequestDAOImpl implements RequestDAO {


    @Override
    public Request createRequest(String username, String title, int amount, String location) {
        System.out.println("Called Create Request method");
        return null;
    }

    @Override
    public List<Request> getAllRequest() {
        System.out.println("Called get all Requests method");
        return null;
    }

    @Override
    public List<Request> getRequestByUsername(String username) {
        System.out.println("Called get request by username");
        return null;
    }

    @Override
    public Request updateRequest(int reimbursement_id, String approval_status) {
        System.out.println("Called update Request");
        return null;
    }
}
