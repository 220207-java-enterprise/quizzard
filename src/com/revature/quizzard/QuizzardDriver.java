package com.revature.quizzard;

import java.io.*;
import java.util.Scanner;
import java.util.UUID;

public class QuizzardDriver {

    public static void main(String[] args) {
        //System.out.println("And so it begins...");

        String welcomeMenu = "Welcome to Quizzard!\n" +
                             "Please make a selection from the options below: \n" +
                "1) Login\n" +
                "2) Register \n" +
                "3) Exit \n" +
                "> ";

        System.out.println(welcomeMenu);

        // Scanner and BufferedReader
        // Scanner works well when parsing text for strings, ints, etc.
        // BufferedReader works especially well when you only expect to deal with strings

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        // try, to do something that might result in an exception
        try {
            // Throwables ...can be caught.
            // Two main types: Errors and Exceptions..
            // Exceptions come in two flavors: Checked and Unchecked.
            String userSelection = consoleReader.readLine();
            System.out.println(userSelection);

            switch (userSelection) {
                case "1":
                    System.out.println("You selected: Login");
                    break;
                case "2":
                    System.out.println("You selected: Register");
                    System.out.println("Please provide some basic information to register an account: ");

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

                    //Allows the use of multiple args out.
                    AppUser newUser = new AppUser(firstName, lastName, email, username, password);
                    System.out.printf("Registration info provided: %s\n", newUser);

                    //TODO validate that good info given


                    //TODO persis user info to a file
                    newUser.setId(UUID.randomUUID().toString());
                    String fileString = newUser.toFileString() + "\n";

                    File usersDataFile = new File("data/users.txt");

                    FileWriter dataWriter = new FileWriter(usersDataFile, true);
                    dataWriter.write(fileString);
                    dataWriter.close();

                    break;
                case "3":
                    System.out.println("You selected: Exit");
                    break;
                default:
                    System.out.println("You have made an incorrect selection");
                    break;
            }

        }
        // if the exception occurs, do something about it.
        catch (IOException e) {
            e.printStackTrace();
        }

        main(args); // TODO maybe don't use recursion

    }

}
