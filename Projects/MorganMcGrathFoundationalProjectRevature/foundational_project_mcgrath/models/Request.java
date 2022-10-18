package com.revature.foundational_project_mcgrath.models;

import java.util.Objects;

public class Request {
    private int reimbursement_id;
    private String username;
    private String title;
    private int amount;
    private String location;
    private String type;
    private String approval;

    public Request(int reimbursement_id, String username, String title, int amount, String type, String location) {
        this.reimbursement_id = reimbursement_id;
        this.username = username;
        this.title = title;
        this.amount = amount;
        this.type = type;
        this.location = location;
    }

    public Request(int reimbursement_id, String username, String title, int amount, String location, String type, String approval) {
        this.reimbursement_id = reimbursement_id;
        this.username = username;
        this.title = title;
        this.amount = amount;
        this.location = location;
        this.type = type;
        this.approval = approval;
    }

    public Request(String approval){
        this.approval = approval;
    }

    public Request() {
    }

    public int getReimbursement_id() {
        return reimbursement_id;
    }

    public void setReimbursement_id(int reimbursement_id) {
        this.reimbursement_id = reimbursement_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String isApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return reimbursement_id == request.reimbursement_id && amount == request.amount && approval == request.approval && username.equals(request.username) && title.equals(request.title) && location.equals(request.location) && type.equals(request.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbursement_id, username, title, amount, location, type, approval);
    }

    @Override
    public String toString() {
        return "Request{" +
                "reimbursement_id=" + reimbursement_id +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", location='" + location + '\'' +
                ", type=," + type + '\'' +
                ", approval=" + approval +
                '}';
    }
}
