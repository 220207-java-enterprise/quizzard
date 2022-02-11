package com.revature.quizzard;

import com.revature.quizzard.screens.RegisterScreen;

import java.io.*;

public class QuizzardDriver {

    private static int loopCounter = 0;

    public static void main(String[] args) {

        // TODO: at the start of the program, read in our users file, and store them in some sort of container, or Data Structure

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
                    loginScreen(consoleReader);
                    break;
                case "2":
                    new RegisterScreen().render(); // TODO there are better ways, all in due time
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

    public static void loginScreen(BufferedReader consoleReader) throws IOException {
        // UI logic
        System.out.println("Please provide your account credentials to login:");

        System.out.print("Username: ");
        String loginUsername = consoleReader.readLine();

        System.out.print("Password: ");
        String loginPassword = consoleReader.readLine();

        // Business/Validation logic
//        if (!isUsernameValid(loginUsername) || !isPasswordValid(loginPassword)) {
//            throw new RuntimeException("Invalid credentials provided!");
//        }

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
    }

}
