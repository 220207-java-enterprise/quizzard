package com.revature.quizzard;

import com.revature.quizzard.screens.LoginScreen;
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
                    new LoginScreen().render();
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

}
