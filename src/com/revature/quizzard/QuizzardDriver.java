package com.revature.quizzard;

import java.io.*;
import java.util.Scanner;
import java.util.UUID;

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

                    // instantiate a user
                    AppUser newUser = new AppUser(firstName,lastName,email,username,password);
                    System.out.printf("Registration info provided: %s\n", newUser.toString());

                    // TODO validate the user input

                    // Validation criteria
                    // firstName --> trim the string, no integers, range, no strange characters
                    // and that data was entered

                    // TODO persist user info to a file
                    newUser.setId(UUID.randomUUID().toString());
                    String fileString = newUser.toFileString() + "\n";

                    // write to users.txt file
                    File usersDataFile = new File("data/users.txt");
                    FileWriter dataWriter = new FileWriter(usersDataFile, true); //append mode
                    dataWriter.write(fileString);
                    dataWriter.close();



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
