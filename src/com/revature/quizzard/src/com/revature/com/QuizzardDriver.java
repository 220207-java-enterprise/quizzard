package com.revature.com;

import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

import java.util.Scanner;

public class QuizzardDriver {
    public static void main(String[] args) {
        String welcomeMenu = "Welcome to Quizzard!\n"+
                "Please make a selection form the options below\n" +
                "1) Login\n" +
                "2) Register\n" +
                "3) Exit\n" +
                "> ";
        System.out.println(welcomeMenu);
        Scanner ac = new Scanner(System.in);
        String input = ac.nextLine();
        switch(input){
            case "1":
                System.out.println();
                break;
            case "2":
                System.out.println("First name: ");
                String fName = ac.nextLine();

                System.out.println("Last name: ");
                String lname = ac.nextLine();

                System.out.println("Email: ");
                String email = ac.nextLine();

                System.out.println("Username: ");
                String uName = ac.nextLine();

                System.out.println("Password: ");
                String password = ac.nextLine();

                AppUser user = new AppUser(fName,lname,email, uName,password);
                System.out.println(user.toString());
                break;
            case "3":
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Command not recognized");
        }


    }
}
