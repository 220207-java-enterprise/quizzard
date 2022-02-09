package com.revature.com;

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
        System.out.println(input);

    }
}
