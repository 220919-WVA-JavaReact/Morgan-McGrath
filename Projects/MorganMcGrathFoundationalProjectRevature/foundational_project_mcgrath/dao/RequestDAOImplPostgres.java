package com.revature.foundational_project_mcgrath.dao;

import com.revature.foundational_project_mcgrath.util.ConnectionUtil;
import com.revature.foundational_project_mcgrath.models.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAOImplPostgres implements RequestDAO {
    @Override
    public Request createRequest(String username, String title, int amount, String type, String location) {
        Request req = new Request();


        try (Connection conn = ConnectionUtil.getConnection()) {
            //Statement stmnt = conn.createStatement();
            String sql = "INSERT INTO reimbursement_requests (username, title, amount, location, type, approval_status) VALUES (?,?,?,?,?,?) RETURNING *";
            //ResultSet rs = stmnt.executeQuery(sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, title);
            ps.setInt(3, amount);
            ps.setString(4, location);
            ps.setString(5, type);
            ps.setString(6, "pending");
            ResultSet rs;


            //rs.next();

            if ((rs = ps.executeQuery()) != null) {
                rs.next();
                int id = rs.getInt("reimbursement_id");
                String receivedUsername = rs.getString("username");
                String receivedTitle = rs.getString("title");
                int receivedAmount = rs.getInt("amount");
                String receivedLocation = rs.getString("location");
                String receivedType = rs.getString("type");
                String receivedStatus = rs.getString("approval_status");

//                req.setUsername(username);
//                req.setTitle(title);
//                req.setAmount(amount);
//                req.setLocation(location);

                req = new Request(id, receivedUsername, receivedTitle, receivedAmount, receivedLocation, receivedType, receivedStatus);
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
            String sql = "SELECT * FROM reimbursement_requests ORDER BY reimbursement_id";
            ResultSet rs = stmnt.executeQuery(sql);



            while (rs.next()) {
                int id = rs.getInt("reimbursement_id");
                String username = rs.getString("username");
                String title = rs.getString("title");
                int amount = rs.getInt("amount");
                String location = rs.getString("location");
                String type = rs.getString("type");
                String approval = rs.getString("approval_status");

                Request req = new Request(id, username, title, amount, location, type, approval);
                requests.add(req);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public List<Request> getRequestByType(String username, String type) {
        List<Request> requests = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM reimbursement_requests WHERE username = ? && type = ? ORDER BY reimbursement_id";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, username);
            stmnt.setString(2, type);
            ResultSet rs;


            if ((rs = stmnt.executeQuery()) != null) {
                while (rs.next()) {

                    int id = rs.getInt("reimbursement_id");
                    String receivedUsername = rs.getString("username");
                    String title = rs.getString("title");
                    int amount = rs.getInt("amount");
                    String location = rs.getString("location");
                    String newType = rs.getString("type");
                    String approval_status = rs.getString("approval_status");

                    Request req = new Request(id, receivedUsername, title, amount, location, newType, approval_status);
                    requests.add(req);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public List<Request> getRequestByApproval(String approval){
        List<Request> requests = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM reimbursement_requests WHERE approval_status = ? ORDER BY reimbursement_id";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, approval);
            ResultSet rs;


            if ((rs = stmnt.executeQuery()) != null) {
                while (rs.next()) {

                    int id = rs.getInt("reimbursement_id");
                    String username = rs.getString("username");
                    String title = rs.getString("title");
                    int amount = rs.getInt("amount");
                    String location = rs.getString("location");
                    String newType = rs.getString("type");
                    String receivedApproval = rs.getString("approval_status");

                    Request req = new Request(id, username, title, amount, location, newType, receivedApproval);
                    requests.add(req);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public Request updateRequest(int reimbursement_id, String approval_status) {

        Request req = new Request();
        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "UPDATE reimbursement_requests SET approval_status = ? WHERE reimbursement_id = ? RETURNING *";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, approval_status);
            stmnt.setInt(2, reimbursement_id);

            ResultSet rs;
            if ((rs = stmnt.executeQuery()) != null) {
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

    @Override
    public List<Request> getAllPending() {
        List<Request> requests = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM reimbursement_requests WHERE approval_status = 'pending' ORDER BY reimbursement_id";
            Statement stmnt = conn.createStatement();

            ResultSet rs;
            if ((rs = stmnt.executeQuery(sql)) != null) {


                while (rs.next()) {
                    int id = rs.getInt("reimbursement_id");
                    String username = rs.getString("username");
                    String title = rs.getString("title");
                    int amount = rs.getInt("amount");
                    String location = rs.getString("location");
                    String type = rs.getString("type");
                    String approval = rs.getString("approval_status");

                    Request req = new Request(id, username, title, amount, location, type, approval);
                    requests.add(req);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public List<Request> getRequestByUsername(String username) {
        List<Request> requests = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM reimbursement_requests WHERE username = ? ORDER BY reimbursement_id";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, username);
            ResultSet rs;


            if ((rs = stmnt.executeQuery()) != null) {
                while (rs.next()) {

                    int id = rs.getInt("reimbursement_id");
                    String receivedUsername = rs.getString("username");
                    String title = rs.getString("title");
                    int amount = rs.getInt("amount");
                    String location = rs.getString("location");
                    String type = rs.getString("type");
                    String approval_status = rs.getString("approval_status");

                    Request req = new Request(id, receivedUsername, title, amount, location, type, approval_status);
                    requests.add(req);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public Request getRequestById(int reimbursement_id) {
        Request request = new Request();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM reimbursement_requests WHERE reimbursement_id = ? ORDER BY reimbursement_id";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setInt(1, reimbursement_id);
            ResultSet rs;


            if ((rs = stmnt.executeQuery()) != null) {
                while (rs.next()) {

                    int receivedId = rs.getInt("reimbursement_id");
                    String username = rs.getString("username");
                    String title = rs.getString("title");
                    int amount = rs.getInt("amount");
                    String location = rs.getString("location");
                    String type = rs.getString("type");
                    String approval_status = rs.getString("approval_status");

                    request = new Request(receivedId, username, title, amount, location, type, approval_status);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return request;
    }
    public List<Request> getAllManager(String approval){
        List<Request> requests = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM reimbursement_requests WHERE approval_status = ? ORDER BY reimbursement_id";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, approval);
            ResultSet rs;


            if ((rs = stmnt.executeQuery()) != null) {
                while (rs.next()) {

                    int reimbursement_id = rs.getInt("reimbursement_id");
                    String username = rs.getString("username");
                    String title = rs.getString("title");
                    int amount = rs.getInt("amount");
                    String location = rs.getString("location");
                    String type = rs.getString("type");
                    String receivedApproval = rs.getString("approval_status");

                    Request req = new Request(reimbursement_id, username, title, amount, location, type, receivedApproval);
                    requests.add(req);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return requests;
    }

    public List<Request> getAllUser(String username, String approval){
        List<Request> requests = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM reimbursement_requests WHERE username = ? AND approval_status = ? ORDER BY reimbursement_id";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, username);
            stmnt.setString(2, approval);
            ResultSet rs;


            if ((rs = stmnt.executeQuery()) != null) {
                while (rs.next()) {

                    int reimbursement_id = rs.getInt("reimbursement_id");
                    String receivedUsername = rs.getString("username");
                    String title = rs.getString("title");
                    int amount = rs.getInt("amount");
                    String location = rs.getString("location");
                    String type = rs.getString("type");
                    String receivedApproval = rs.getString("approval_status");

                    Request req = new Request(reimbursement_id, receivedUsername, title, amount, location, type, receivedApproval);
                    requests.add(req);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return requests;
    }
}