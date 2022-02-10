package com.revature.quizzard;

import java.io.*;
import java.util.UUID;

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

                    System.out.print("Password Again: ");
                    String password2 = consoleReader.readLine();




                    // TODO validate that good info was given
                    AppUser newUser = new AppUser(firstName, lastName, email, username, password);
                    if(isValidUser(newUser)){
                        System.out.printf("Registration info provided: %s\n", newUser);
                        newUser.setId(UUID.randomUUID().toString());
                        String fileString = newUser.toString() + "\n";

                        File usersDataFile = new File("data/users.txt");
                        FileWriter dataWriter = new FileWriter(usersDataFile, true);
                        dataWriter.write(fileString);
                        dataWriter.close();
                    }
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


    public static boolean isValidUser(AppUser user){
        // Validation Criteria
        // firstName -> trim the string, no integers, range, no strange characters, check for capital
        // and that data was entered(if length != 0)
        String firstName = user.getFirstName();
        firstName = firstName.trim();

        if(firstName.length() < 2 || firstName.length() > 15){
            System.out.println("First name needs to be between 2 and 15 characters.");
            return false;
        }

        for(int i = 0; i < firstName.length(); i++){
            char c = firstName.charAt(i);
            if(!Character.isAlphabetic(c)){
                System.out.println("Name can not contain non-alpha characters");
                return false;
            }
        }

        // brandon -> b
        String firstLetter = String.valueOf(firstName.charAt(0));
        // b = B
        firstLetter = firstLetter.toUpperCase();

        // brandon = B + randon
        firstName = firstLetter + firstName.substring(1);
        System.out.println(firstName);





        // lastName -> trim the string, no integers, range, no strange characters, check for capital
        // and that data was entered(if length != 0)
        // email -> check if exists, no more than 1 @ followed a character sequence followed by at least one .
        // followed by some character sequence, check if uniqueness
        // username -> begins with (char, underscore, or $), check for uniqueness(tabled until further notice)
        // password -> exists, with no spaces, range?, check against "bad" pass, enter it twice
        // password2 -> check that it matches password (this should happen first to trigger short-circuiting)
        return false;
    }
}
