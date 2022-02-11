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
                    // if the passwords don't match, break immediately before doing any other validation
                    if(!password.equals(password2)){
                        System.out.println("Your passwords must match.");
                        break;
                    }
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


        // check if firstName is valid
        if(!isValidName(user.getFirstName())) return false;
        // auto capitalize their first letter
        String firstCapitalized = capitalize(user.getFirstName());
        user.setFirstName(firstCapitalized);

        // lastName -> trim the string, no integers, range, no strange characters, check for capital
        // and that data was entered(if length != 0)

        // check if lastName is valid
        if(!isValidName(user.getLastName())) return false;
        String lastCapitalized = capitalize(user.getLastName());
        user.setLastName(lastCapitalized);

        // email -> check if exists, no more than 1 @ followed a character sequence followed by at least one .
        // followed by some character sequence, check if uniqueness
        if(!isValidEmail(user.getEmail())) return false;

        // username -> begins with (char, underscore, or $), check for uniqueness(tabled until further notice)
        if(!isValidUsername(user.getUsername())) return false;

        // password -> exists, with no spaces, range?, check against "bad" pass, enter it twice
        // password2 -> check that it matches password (this should happen first to trigger short-circuiting)
        if(!isValidPassword(user.getPassword())) return false;

        // if they pass all the other checks, it is valid
        return true;
    }

    private static boolean isValidPassword(String password) {
        password = password.trim();

        // check the range first
        if(password.length() < 8 || password.length() > 24){
            System.out.println("Invalid Password: Password must be between 8 and 24 characters.");
            return false;
        }

        // check for spaces
        for(int i = 0; i < password.length(); i++){
            if(password.charAt(i) == ' '){
                System.out.println("Invalid Password: Password must not contain spaces.");
                return false;
            }
        }

        return true;
    }

    private static boolean isValidUsername(String username) {
        char firstLetter = username.charAt(0);

        // check that it's a character or underscore, or $
        if(!(Character.isAlphabetic(firstLetter) || firstLetter == '_' || firstLetter == '$')){
            System.out.println("Invalid username: must start with a character, underscore, or dollar sign.");
            return false;
        }

        return true;
    }

    private static boolean isValidName(String name){
        name = name.trim();

        if(name.length() < 2 || name.length() > 15){
            System.out.println("Invalid Name: Name must be between 2 and 15 characters");
            return false;
        }

        for(int i = 0; i < name.length(); i++){
            char c = name.charAt(i);
            if(!Character.isAlphabetic(c)){
                System.out.println("Invalid Name: Name can not contain non-alpha characters");
                return false;
            }
        }

        return true;
    }

    private static boolean isValidEmail(String email){
        email = email.trim();

        // check that there is one and only one @ symbol
        // take the length of the initial string, and subtract the length of the substring with all the @'s removed
        //                                          replace all instances of @ with nothing
        int symbolCount = email.length() - email.replaceAll("@", "").length();
        if(symbolCount != 1){
            System.out.println("Invalid email: Cannot contain more than one @ symbol.");
            return false;
        }

        // now that we know there is only one @ symbol, we need to check that it is not the first character
        if(email.indexOf("@") == 0){
            System.out.println("Invalid email: Cannot start with \"@\" symbol.");
            return false;
        }

        // now we need to check that there is a period occurring after the @ symbol
        // so we check the substring following the @ index to see if it contains a period
        String afterAtSymbol = email.substring(email.indexOf("@"));
        if(!afterAtSymbol.contains(".")){
            System.out.println("Invalid email: Must contain a domain following the \"@\" symbol");
            return false;
        }

        // We should also check that the . does not occur as the first index in the afterAtSymbol substring
        if(afterAtSymbol.indexOf(".") == 1){
            System.out.println("Invalid email: \"@\" symbol cannot be directly followed by a \".\"");
            return false;
        }

        // if all the above pass, it is valid
        return true;
    }

    private static String capitalize(String string){
        // brandon -> b
        String firstLetter = String.valueOf(string.charAt(0));
        // b = B
        firstLetter = firstLetter.toUpperCase();

        // brandon = B + randon
        string = firstLetter + string.substring(1);
        return string;
    }
}
