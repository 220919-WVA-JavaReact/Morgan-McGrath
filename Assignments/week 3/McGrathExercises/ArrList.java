package com.revature.collections.exercises;

import java.util.ArrayList;
import java.util.*;

public class ArrList {

    /*
    Below is a list of exercises to help you get familiar with working with the ArrayList Collection
     */
    public static void main(String[] args) {

        // 1. Write a Java program to create a new array list, add some colors (string) and print out the collection.

        ArrayList<String> colors = new ArrayList<String>();
        colors.add("Green");
        colors.add("Blue");
        colors.add("Red");
        colors.add("Purple");
        colors.add("Cyan");
        colors.add("Magenta");
        System.out.println(colors);
        System.out.println("+-------------------------------------------------+");



        // 2. Write a Java program to iterate through all elements in an array list

        //temp variable color is the color in the array and measures it by the length of the array itself

        for(String color : colors){
            System.out.println(color);
        }

        System.out.println("+------------------------------------------------+");

        // 3. Write a Java program to insert an element into the array list at the first position

        colors.add(0,"Orange");
        System.out.println(colors);
        System.out.println("+-----------------------------------------+");



        // 4. Write a Java program to retrieve an element (at a specified index) from a given array list

        colors.get(4);
        System.out.println(colors.get(4));
        System.out.println("+---------------------------------------+");

        // 5. Write a Java program to remove the third element from an array list.

        colors.remove(2);
        System.out.println(colors);
        System.out.println("+---------------------------------------------+");
        // 6. Write a Java program to search an element in an array list.

        System.out.println(colors.indexOf("Purple"));
        System.out.println(colors);
        System.out.println("+-------------------------------------+");

        // 7. Write a Java program to sort a given array list.

        colors.sort(Comparator.naturalOrder());
        System.out.println(colors);
        System.out.println("+-------------------------------------+");



    }
}