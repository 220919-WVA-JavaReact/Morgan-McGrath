package com.revature.foundational_project_morgan.dao;


import com.revature.foundational_project_morgan.models.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeDAOImpl implements EmployeeDAO{
    String line = "";
    String splitBy = ",";
    @Override
    public Employee getByUsername(String employeeUsername) {


        try{
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/employees.csv"));

            // This is allowing us to read through the entire file till the end
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {

                String[] info = line.split(splitBy);

                if (info[3].equals(employeeUsername)){

                    // We'll parse info[0] into an integer
                    // We'll cover wrapper classes another time but they basically give object abilities to primitive values
                    int id = Integer.valueOf(info[0]);
                    boolean manager = false;
                    if(info[5].equals("true")){
                        manager = true;
                    }

                    return new Employee(id, info[1], info[2], info[3], info[4], manager);
                }

            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee createEmployee(String employeeFirstname, String employeeLastname, String employeeUsername, String employeePassword) {
        return null;
    }
}

