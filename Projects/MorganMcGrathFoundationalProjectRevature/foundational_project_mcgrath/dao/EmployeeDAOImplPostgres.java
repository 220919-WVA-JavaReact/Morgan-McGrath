package com.revature.foundational_project_morgan.dao;

import com.revature.foundational_project_morgan.util.ConnectionUtil;
import com.revature.foundational_project_morgan.models.Employee;
import com.revature.foundational_project_morgan.models.Level;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.revature.foundational_project_morgan.models.Level.Associate;

public class EmployeeDAOImplPostgres implements EmployeeDAO {
    @Override
    public Employee getByUsername(String username) {
        Employee emp = new Employee();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM employee_database WHERE username = ?";
            assert conn != null;
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, username);
            ResultSet rs;

            if ((rs = stmnt.executeQuery()) != null) {
                rs.next();

                int id = rs.getInt("employee_id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                String receivedUsername = rs.getString("username");
                String password = rs.getString("password");
                String access = rs.getString("access");
                Level level = null;
                switch(access){
                    case "Associate":
                        level = Associate;
                        break;
                    case "Manager":
                        level = Level.Manager;
                        break;
                    case "Supervisor":
                        level = Level.Supervisor;
                }

                emp = new Employee(id, first, last, receivedUsername, password, level);
            }

        } catch (SQLException e) {
            System.out.println("Sorry, that was the wrong information");
            e.printStackTrace();

        }
        return emp;
    }

    @Override
    public Employee createEmployee(String first, String last, String username, String password) {
        Employee emp = new Employee();

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO employee_database (first, last, username, password, access) VALUES (?,?,?,?,CAST(? AS access_level)) RETURNING *";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, first);
            stmnt.setString(2, last);
            stmnt.setString(3, username);
            stmnt.setString(4, password);
            stmnt.setString(5, Associate.toString());

            ResultSet rs;

            if ((rs = stmnt.executeQuery()) != null) {
                rs.next();
                int id = rs.getInt("employee_id");
                String receivedFirst = rs.getString("first");
                String receivedLast = rs.getString("last");
                String receivedUsername = rs.getString("username");
                String receivedPassword = rs.getString("password");
                Level receivedAccess = Level.valueOf(rs.getString("access"));
//                Level level = null;
//                switch(receivedAccess){
//                    case "Associate":
//                        level = Associate;
//                        break;
//                    case "Manager":
//                        level = Level.Manager;
//                        break;
//                    case "Supervisor":
//                        level = Level.Supervisor;
//                }

                emp = new Employee(id, receivedFirst, receivedLast, receivedUsername, receivedPassword, receivedAccess);
                return emp;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Sorry, that username already exists.");
        }
        return null;
    }

    @Override
    public Employee updateEmployeeAccess(String username, Level newAccess) {
        Employee emp = new Employee();

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "UPDATE employee_database SET access = ?::access_level WHERE username = ? RETURNING *";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, newAccess.toString());
            stmnt.setString(2, username);

            ResultSet rs;
            if ((rs = stmnt.executeQuery()) != null){
                rs.next();
                int id = rs.getInt("employee_id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                String receivedUsername = rs.getString("username");
                //String password = rs.getString("password");
                Level access = Level.valueOf(rs.getString("access"));
//                Level level = Level.valueOf(rs.getString(newAccess));
//                switch(newAccess){
//                    case "Associate":
//                        level = Associate;
//                        break;
//                    case "Manager":
//                        level = Level.Manager;
//                        break;
//                    case "Supervisor":
//                        level = Level.Supervisor;
//                }

                emp = new Employee(id, first, last, receivedUsername, access);
                return emp;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }

    @Override
    public List<Employee> viewAllEmployees() {
        Connection conn = ConnectionUtil.getConnection();
        List<Employee> employees = new ArrayList<>();

        try {
            Statement stmnt = conn.createStatement();
            String sql = "SELECT * FROM employee_database ORDER BY employee_id";
            ResultSet rs = stmnt.executeQuery(sql);



            while (rs.next()) {
                int id = rs.getInt("employee_id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                String username = rs.getString("username");
//                String password = "Information Withheld";
                Level access = Level.valueOf(rs.getString("access"));
//


                Employee emp = new Employee(id, first, last, username, access);
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

//    @Override
//    public Employee demoteEmployee(String username) {
//        Employee emp = new Employee();
//
//        try(Connection conn = ConnectionUtil.getConnection()){
//
//            String sql = "UPDATE employee_database SET Manager = false WHERE username = ? RETURNING *";
//            PreparedStatement stmnt = conn.prepareStatement(sql);
//            stmnt.setString(1, username);
//
//            ResultSet rs;
//            if ((rs = stmnt.executeQuery()) != null){
//                rs.next();
//                int id = rs.getInt("employee_id");
//                String first = rs.getString("first");
//                String last = rs.getString("last");
//                String receivedUsername = rs.getString("username");
//                //String password = rs.getString("password");
//                boolean manager = rs.getBoolean("Manager");
//
//                emp = new Employee(id, first, last, receivedUsername, manager);
//                return emp;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return emp;
//    }
}
