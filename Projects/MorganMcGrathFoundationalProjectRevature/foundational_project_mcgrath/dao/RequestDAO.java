package com.revature.foundational_project_morgan.dao;

import com.revature.foundational_project_morgan.models.Request;

import java.util.List;

public interface RequestDAO {
    Request createRequest(String username, String title, int amount, String location);

    List<Request> getAllRequest();

    List<Request> getRequestByUsername(String username);

    Request updateRequest(int reimbursement_id, String approval_status);


}
