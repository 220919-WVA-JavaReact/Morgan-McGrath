package com.revature.foundational_project_morgan.dao;

import com.revature.courses.util.ConnectionUtil;
import com.revature.foundational_project_morgan.models.Employee;
import com.revature.foundational_project_morgan.models.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImplPostgres implements EmployeeDAO {
    @Override
    public Employee getByUsername(String username) {
        Employee emp = new Employee();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM employee_database WHERE username = ?";
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
                boolean manager = rs.getBoolean("manager");

                emp = new Employee(id, first, last, receivedUsername, password, manager);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return emp;
    }

    @Override
    public Employee createEmployee(String first, String last, String username, String password) {
        Employee emp = new Employee();

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO employee_database (first, last, username, password, Manager) VALUES (?,?,?,?,?) RETURNING *";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, first);
            stmnt.setString(2, last);
            stmnt.setString(3, username);
            stmnt.setString(4, password);
            stmnt.setBoolean(5, false);

            ResultSet rs;

            if ((rs = stmnt.executeQuery()) != null) {
                rs.next();
                int id = rs.getInt("employee_id");
                String receivedFirst = rs.getString("first");
                String receivedLast = rs.getString("last");
                String receivedUsername = rs.getString("username");
                String receivedPassword = rs.getString("password");
                boolean manager = rs.getBoolean("Manager");

                emp = new Employee(id, receivedFirst, receivedLast, receivedUsername, receivedPassword, manager);
                return emp;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Sorry, that username already exists.");
        }
        return null;
    }

    @Override
    public Employee promoteEmployee(String username) {
        Employee emp = new Employee();

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "UPDATE employee_database SET Manager = true WHERE username = ? RETURNING *";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, username);

            ResultSet rs;
            if ((rs = stmnt.executeQuery()) != null){
                rs.next();
                int id = rs.getInt("employee_id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                String receivedUsername = rs.getString("username");
                //String password = rs.getString("password");
                boolean manager = rs.getBoolean("Manager");

                emp = new Employee(id, first, last, receivedUsername, manager);
                return emp;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Employee> viewAllEmployees() {
        Connection conn = ConnectionUtil.getConnection();
        List<Employee> employees = new ArrayList<>();

        try {
            Statement stmnt = conn.createStatement();
            String sql = "SELECT * FROM employee_database ORDER BY Manager";
            ResultSet rs = stmnt.executeQuery(sql);


            rs.next();

            while (rs.next()) {
                int id = rs.getInt("employee_id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                String username = rs.getString("username");
                boolean manager = rs.getBoolean("Manager");


                Employee emp = new Employee(id, first, last, username, manager);
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
