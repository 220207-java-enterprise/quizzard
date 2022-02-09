package com.revature.quizzard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class QuizzardDriver {

    public static void main(String[] args) {

        String welcomeMenu = "Welcome to Quizzard!\n" +
                             "Please make a selection from the options below:\n" +
                             "1) Login\n" +
                             "2) Register\n" +
                             "3) Exit\n" +
                             "> ";

        System.out.print(welcomeMenu);

        // Scanner and BufferedReader

        // Scanner works well when parsing text for strings, ints, etc.
        // BufferedReader works especially well when you only expect to deal with strings

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        // try, to do something that might result in an exception
        try {

            // Throwables...can be caught.
            // Two main types: Errors and Exceptions
            // Exceptions come in two flavors: Checked and Unchecked
            String userSelection = consoleReader.readLine();
            System.out.println(userSelection);

            switch (userSelection) {
                case "1":
                    System.out.println("You selected: Login");
                    break;
                case "2":
                    System.out.println("You selected: Register");
                    break;
                case "3":
                    System.out.println("You selected: Exit");
                    break;
                default:
                    System.out.println("You have made an incorrect selection");

            }

        }
        // if the exception occurs, do something about it
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            // always executes regardless of whether or not an exception was thrown
            System.out.println("and finally!");
        }




    }

}
