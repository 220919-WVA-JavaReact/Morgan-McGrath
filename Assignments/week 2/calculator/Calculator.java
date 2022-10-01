package com.revature.calculator;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hi! Feel free to use me for all your math needs!");
        System.out.println("Which operator would you like to use? *, /, +, or -");
        String option = input.nextLine();
        while (!option.equals("*") && !option.equals("/") && !option.equals("+") && !option.equals("-")){
            System.out.println("Sorry, that was an invalid choice! Try using the options listed ;)");
            System.out.println("Which operator would you like to use? *, /, +, or -");
            option = input.nextLine();
        }
        System.out.println("Which number would you like to use?");
        double x = input.nextDouble();


        System.out.println("And your second number?");

        double y = input.nextDouble();



        if (option.equals("*")) {
            System.out.println("Your answer for " + x + " " + option + " " + y + " is: " + (x * y));
        } else if (option.equals("-")){
            System.out.println("Your answer for " + x + " " + option + " " + y + " is: " + (x - y));
        } else if (option.equals("/")){
            System.out.println("Your answer for " + x + " " + option + " " + y + " is: " + (x / y));
        } else if (option.equals("+")) {
            System.out.println("Your answer for " + x + " " + option + " " + y + " is: " + (x + y));
        }


    }

}


