package com.revature.foundational_project_morgan.dao;

import com.revature.courses.util.ConnectionUtil;
import com.revature.foundational_project_morgan.models.Request;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAOImplPostgres implements RequestDAO {
    @Override
    public Request createRequest(String username, String title, int amount, String location) {
        Request req = new Request();


        try (Connection conn = ConnectionUtil.getConnection()) {
            //Statement stmnt = conn.createStatement();
            String sql = "INSERT INTO reimbursement_requests (username, title, amount, location, approval_status) VALUES (?,?,?,?,?) RETURNING *";
            //ResultSet rs = stmnt.executeQuery(sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, title);
            ps.setInt(3, amount);
            ps.setString(4, location);
            ps.setString(5, "pending");
            ResultSet rs;


            //rs.next();

            if ((rs = ps.executeQuery()) != null) {
                rs.next();
                int id = rs.getInt("reimbursement_id");
                String receivedUsername = rs.getString("username");
                String receivedTitle = rs.getString("title");
                int receivedAmount = rs.getInt("amount");
                String receivedLocation = rs.getString("location");
                String receivedStatus = rs.getString("approval_status");

//                req.setUsername(username);
//                req.setTitle(title);
//                req.setAmount(amount);
//                req.setLocation(location);

                req = new Request(id, receivedUsername, receivedTitle, receivedAmount, receivedLocation, receivedStatus);
                return req;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<Request> getAllRequest() {
        Connection conn = ConnectionUtil.getConnection();
        List<Request> requests = new ArrayList<>();

        try {
            Statement stmnt = conn.createStatement();
            String sql = "SELECT * FROM reimbursement_requests";
            ResultSet rs = stmnt.executeQuery(sql);


            rs.next();

            while (rs.next()) {
                int id = rs.getInt("reimbursement_id");
                String username = rs.getString("username");
                String title = rs.getString("title");
                int amount = rs.getInt("amount");
                String location = rs.getString("location");
                String approval = rs.getString("approval_status");

                Request req = new Request(id, username, title, amount, location, approval);
                System.out.println(req);
                requests.add(req);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public List<Request> getRequestByUsername(String username) {
        List<Request> requests = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM reimbursement_requests WHERE username = ?";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, username);
            ResultSet rs;


            if((rs = stmnt.executeQuery()) != null){
                while(rs.next()) {

                    int id = rs.getInt("reimbursement_id");
                    String receivedUsername = rs.getString("username");
                    String title = rs.getString("title");
                    int amount = rs.getInt("amount");
                    String location = rs.getString("location");
                    String approval_status = rs.getString("approval_status");

                    Request req = new Request(id, receivedUsername, title, amount, location, approval_status);
                    requests.add(req);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
        public Request updateRequest (int reimbursement_id, String approval_status){

        Request req = new Request();
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "UPDATE reimbursement_requests SET approval_status = ? WHERE reimbursement_id = ? RETURNING *";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, approval_status);
            stmnt.setInt(2, reimbursement_id);

            ResultSet rs;
            if((rs = stmnt.executeQuery()) != null){
                rs.next();
                int receivedId = rs.getInt("reimbursement_id");
                String receivedUsername = rs.getString("username");
                String title = rs.getString("title");
                int amount = rs.getInt("amount");
                String location = rs.getString("location");
                String receivedApproval = rs.getString("approval_status");

                req = new Request(receivedId, receivedUsername, title, amount, location, receivedApproval);
                return req;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Sorry, there was an error.");
        }
        return null;
        }
    }

