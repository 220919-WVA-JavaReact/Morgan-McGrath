package com.revature.foundational_project_morgan.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    // To make this class a Singleton, We'll need the following things
    // private static instance
    private static Connection conn = null;

    // private constructor
    private ConnectionUtil() {

    }

    // public static Connection getInstance() method
    // this will create a new connection or allow us to reuse an existing connection
    public static Connection getConnection() {

        try {
            if (conn != null && !conn.isClosed()) {

                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        // This is the least secure way
        // jdbc:postgresql://hostname:port/databaseName
        // host: database-1.cdqpiicocrng.us-west-2.rds.amazonaws.com
        //
//        String url = "jdbc:postgresql://database-1.cdqpiicocrng.us-west-2.rds.amazonaws.com:5432/postgres";
//        String username = "postgres";
//        String password = "f0xtr0t487";
//        try {
//            conn = DriverManager.getConnection(url, username, password);
//            System.out.println("Established new connection");
//        } catch (SQLException e) {
//            System.out.println("Could not establish connection");
//            e.printStackTrace();
//        }

        // add these to application.properties file
//        String url = "";
//        String username = "";
//        String password = "";
//
//        Properties prop = new Properties();
//
//
//        try {
//            prop.load(new FileReader("src/main/resources/application.properties"));
//            url = prop.getProperty("url");
//            username = prop.getProperty("username");
//            password = prop.getProperty("password");
//            conn = DriverManager.getConnection(url, username, password);
//            System.out.println("Established new connection");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Could not find properties file!");
//        } catch (SQLException e) {
//            System.out.println("Could not establish connection!");
//            e.printStackTrace();
//        }
        //Most secure method

        // we are going to store our connection into our environmental variables

        //now that we've stored them in our environ variables

        //now we need to pull them from environment variables

//        String url = System.getenv("url");
//        String username = System.getenv("username");
//        String password = System.getenv("password");
        String url = "jdbc:postgresql://database-1.c6ui37l0rhvg.us-east-1.rds.amazonaws.com:5432/postgres";
        String username = "postgres";
        String password = "f0xtr0t487";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, username, password);

            } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Could not establish connection");
            e.printStackTrace();
        }

        return conn;
    }
}
