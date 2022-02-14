package com.revature.quizzard;

import java.io.*;
import java.util.Locale;
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
                    loginScreen(consoleReader);
                    break;
                case "2":
                    registerScreen(consoleReader);
                    break;
                case "3":
                    System.out.println("You selected: Exit");
                    return;
                default:
                    System.out.println("You have made an incorrect selection");
            }
        }
        // if the IOException occurs, do something about it
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loginScreen(BufferedReader consoleReader) throws IOException {

        System.out.println("Please provide your account credentials to login to your account:");

        // UI logic
        System.out.println("Username: ");
        String loginUsername = consoleReader.readLine();

        System.out.println("Password: ");
        String loginPassword = consoleReader.readLine();

        // Business logic
        if(!isUsernameValid(loginUsername) || !isPasswordValid(loginPassword)){
            throw new RuntimeException("Invalid credentials provided");
        }

        // Persistence logic
        BufferedReader dataReader = new BufferedReader(new FileReader("data/users.txt"));
        String dataCursor;

        while((dataCursor = dataReader.readLine()) != null){
            String[] recordFragments = dataCursor.split(":");
            if(recordFragments[4].equals(loginUsername) && recordFragments[5].equals(loginPassword)){
                System.out.println("User found with matching credentials: " + dataCursor + "\n Login Successful!");
                return; // TODO remove this later
            }
        }

        throw new RuntimeException("No user found with the provided credentials"); // TODO handle better

    }

    public static void registerScreen(BufferedReader consoleReader) throws IOException{

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

        // TODO validate the user input --> static method isValidUser() (DONE)

        AppUser newUser = new AppUser(firstName,lastName,email,username,password);
        System.out.printf("Registration info provided: %s\n", newUser.toString());
        System.out.println("checking validations...");
        if (!isValidUser(newUser)){
            throw new RuntimeException("Invalid Registration information provided.");
        }
        // TODO persist user info to a file
        newUser.setId(UUID.randomUUID().toString());
        String fileString = newUser.toFileString() + "\n";

        // write to users.txt file
        File usersDataFile = new File("data/users.txt");
        FileWriter dataWriter = new FileWriter(usersDataFile, true); //append mode
        dataWriter.write(fileString);
        dataWriter.close();
    }

    public static boolean isValidUser(AppUser appUser){

        // check firstname and last name -- not empty or filled with whitespace
        if (appUser.getFirstName().trim().equals("") || appUser.getLastName().trim().equals("")){
            System.out.println("Bad first/last name");
            return false;
        }
        // usernames must be 8-25 characters in length and only contain alphanumeric characters
        if (!appUser.getUsername().matches("^[a-zA-Z0-9]{8,25}")){
            System.out.println("Bad username");
            return false;
        }
        // password
        if(!appUser.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")){
            System.out.println("Bad Password");
            return false;
        }

        if (!appUser.getEmail().matches("^[^@\\s]+@[^@\\s\\.]+\\.[^@\\.\\s]+$")){
            System.out.println("Bad Email");
            return false;
        }

        System.out.println("Valid information was provided. Creating user..."+appUser);
        return true;
    }

    public static Boolean isUsernameValid(String username){
        return username.matches("^[a-zA-Z0-9]{8,25}");
    }

    public static Boolean isPasswordValid(String password){
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
    }
}
