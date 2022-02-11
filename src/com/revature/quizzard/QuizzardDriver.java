package com.revature.quizzard;

import java.io.*;
import java.util.UUID;

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

                    // UI logic
                    System.out.println("Please provide your account credentials to login:");

                    System.out.print("Username: ");
                    String loginUsername = consoleReader.readLine();

                    System.out.print("Password: ");
                    String loginPassword = consoleReader.readLine();

                    // Business/Validation logic
                    if (!isUsernameValid(loginUsername) || !isPasswordValid(loginPassword)) {
                        throw new RuntimeException("Invalid credentials provided!");
                    }

                    // Persistence logic
                    BufferedReader dataReader = new BufferedReader(new FileReader("data/users.txt"));
                    String dataCursor;
                    while ((dataCursor = dataReader.readLine()) != null) {
                        String[] recordFragments = dataCursor.split(":");
                        if (recordFragments[4].equals(loginUsername) && recordFragments[5].equals(loginPassword)) {
                            System.out.println("User found with matching credentials: " + dataCursor);
                            return; // TODO remove this later
                        }
                    }

                    throw new RuntimeException("No user found with the provided credentials"); // TODO handle better


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

                    AppUser newUser = new AppUser(firstName, lastName, email, username, password);
                    System.out.printf("Registration info provided: %s\n", newUser);

                    // TODO validate that the provided username and email are not already taken

                    if (!isUserValid(newUser)) {
                        throw new RuntimeException("Bad registration details given."); // this will halt the app
                    }

                    // TODO persist user info to a file
                    newUser.setId(UUID.randomUUID().toString());
                    String fileString = newUser.toFileString() + "\n";

                    File usersDataFile = new File("data/users.txt");
                    FileWriter dataWriter = new FileWriter(usersDataFile, true);
                    dataWriter.write(fileString);
                    dataWriter.close();
                    break;

                case "3":
                    System.out.println("You selected: Exit");
                    return;

                default:
                    System.out.println("You have made an incorrect selection");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        loopCounter++;
        main(args); // TODO maybe don't use recursion here?

    }

    private static boolean isUserValid(AppUser appUser) {

        // First name and last name are not just empty strings or filled with whitespace
        if (appUser.getFirstName().trim().equals("") || appUser.getLastName().trim().equals("")) {
            System.out.println("Bad first or last name");
            return false;
        }

        // Usernames must be a minimum of 8 and a max of 25 characters in length, and only contain alphanumeric characters.
        if (!isUsernameValid(appUser.getUsername())) {
            System.out.println("Bad username");
            return false;
        }

        // Passwords require a minimum eight characters, at least one uppercase letter, one lowercase
        // letter, one number and one special character
        if (!isPasswordValid(appUser.getPassword())) {
            System.out.println("Bad password");
            return false;
        }

        // Basic email validation
        if (!appUser.getEmail().matches("^[^@\\s]+@[^@\\s\\.]+\\.[^@\\.\\s]+$")) {
            System.out.println("Bad email");
            return false;
        }

        return true;

    }

    public static boolean isUsernameValid(String username) {
        return username.matches("^[a-zA-Z0-9]{8,25}");
    }

    public static boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }



}
