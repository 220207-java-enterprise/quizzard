package com.revature.quizzard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class QuizzardDriver {

    public static void main(String[] args) {
        String welcomeMenu = "Welcome to Quizzard!\n" +
                "Please make a selection from the options below\n"+
                "1) Login\n"+
                "2) Register\n"+
                "3) Exit\n"+
                "> ";

        System.out.print(welcomeMenu);


        // Scanner and BufferedReader

        // BufferedReader is more flexible in taking user input
        BufferedReader consoleReader = new BufferedReader((new InputStreamReader(System.in)));

        // Throwables can be caught.
        // Two main types: Errors and Exceptions

        // Checked exceptions are checked before compilation. Unchecked are runtime exceptions


        // try to do something that might result in an exception
        try {
            String userSelection = consoleReader.readLine();
            System.out.println(userSelection);

            switch(userSelection) {
                case "1":
                    System.out.println("You selected: Login");
                    break;
                case "2":
                    System.out.println("You selected: Register");
                    System.out.println("Please provide some basic information to register an account:");

                    System.out.println("First name: ");
                    String firstName = consoleReader.readLine();

                    System.out.println("Last name: ");
                    String lastName = consoleReader.readLine();

                    System.out.println("Email: ");
                    String email = consoleReader.readLine();

                    System.out.println("Username: ");
                    String username = consoleReader.readLine();

                    System.out.println("Password: ");
                    String password = consoleReader.readLine();

                    System.out.printf("Registration info provided: %s, %s, %s, %s, %s", firstName,lastName,email,username,password);

                    break;
                case "3":
                    System.out.println("You selected: Exit");
                    break;
                default:
                    System.out.println("You have made an incorrect selection");
            }
        }
        // if the IOException occurs, do something about it
        catch (IOException e) {
            e.printStackTrace();
        }


        //  Scanner may be better if you want to get specific datatype from user
        // Syntax
//        Scanner consoleScanner = new Scanner(System.in);
//        String userInput = consoleScanner.next(); // only grabs string before whitespace
//        System.out.println(userInput);

    }

}
