package com.revature.quizzard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuizzardDriver {

    private static int loopCounter = 0;

    public static void main(String[] args) {

        if (loopCounter == 3) {
            throw new RuntimeException("Looped three times");
        }

        String welcomeMenu = "Welcome to Quizzard!\n" +
                             "Please make a selection from the options below:\n" +
                             "1) Login\n" +
                             "2) Register\n" +
                             "3) Exit\n" +
                             "> ";

        System.out.print(welcomeMenu);

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        try {

            String userSelection = consoleReader.readLine();
            System.out.println(userSelection);

            switch (userSelection) {
                case "1":
                    System.out.println("You selected: Login");
                    break;
                case "2":
                    System.out.println("You selected: Register");
                    System.out.println("Please provide some basic information to register an account:");

                    System.out.print("First name: ");
                    String firstName = consoleReader.readLine();

                    System.out.print("Last name: ");
                    String lastName = consoleReader.readLine();

                    System.out.print("Email: ");
                    String email = consoleReader.readLine();

                    System.out.print("Username: ");
                    String username = consoleReader.readLine();

                    System.out.print("Password: ");
                    String password = consoleReader.readLine();

                    System.out.printf("Registration info provided: %s %s %s %s %s", firstName, lastName, email, username, password);

                    break;
                case "3":
                    System.out.println("You selected: Exit");
                    break;
                default:
                    System.out.println("You have made an incorrect selection");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        loopCounter++;
        main(args); // TODO maybe don't use recursion here?

    }

}
