package com.revature.foundational_project_morgan.dao;

import com.revature.courses.util.ConnectionUtil;
import com.revature.foundational_project_morgan.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public Employee createEmployee(String first, String last, String username, String password, boolean Manager) {
        Employee emp = new Employee();

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO employee_database (first, last, username, password, Manager) VALUES (?,?,?,?,?) RETURNING *";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, first);
            stmnt.setString(2, last);
            stmnt.setString(3, username);
            stmnt.setString(4, password);
            stmnt.setBoolean(5, Manager);

            ResultSet rs;

            if ((rs = stmnt.executeQuery()) != null) {
                rs.next();
                int id = rs.getInt("employee_id");
                String receivedFirst = rs.getString("first");
                String receivedLast = rs.getString("last");
                String receivedUsername = rs.getString("username");
                String receivedPassword = rs.getString("password");
                boolean receivedManager = rs.getBoolean("Manager");

                emp = new Employee(id, receivedFirst, receivedLast, receivedUsername, receivedPassword, receivedManager);
                return emp;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong.");
        }
        return null;
    }
}
