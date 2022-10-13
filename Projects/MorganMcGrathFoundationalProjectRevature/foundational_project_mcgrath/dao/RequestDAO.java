package com.revature.foundational_project_morgan.dao;

import com.revature.foundational_project_morgan.models.Request;

import java.util.List;

public interface RequestDAO {
    Request createRequest(String username, String title, int amount, String type, String location);

    List<Request> getAllRequest();

    List<Request> getRequestByType(String username, String Type);

    Request updateRequest(int reimbursement_id, String approval_status);

    List<Request> getAllPending();

    List<Request> getRequestByUsername(String username);

    Request getRequestById(int reimbursement_id);
}
