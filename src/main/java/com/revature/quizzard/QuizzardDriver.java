package com.revature.quizzard;

import com.revature.quizzard.screen.LoginScreen;
import com.revature.quizzard.screen.RegisterScreen;


import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

public class QuizzardDriver {

    private static int loopCounter = 0;

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
                    new LoginScreen().render();
                    break;
                case "2":
                    new RegisterScreen().render(); //TODO there are better ways
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

        loopCounter++;
        main(args); //TODO maybe dont use recursion
    }

}
