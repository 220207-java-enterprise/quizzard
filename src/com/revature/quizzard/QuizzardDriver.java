package com.revature.quizzard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
// import java.util.Scanner;

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

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        // Scanner condoleScanner = new Scanner(System.in);
        String userSelection = consoleReader.readLine();
        System.out.println(userSelection);
        switch (userSelection) {
            case "1";
            System.out.println("You selected: Login");
            break;
            Case "2";
            
        }


        
    }

}