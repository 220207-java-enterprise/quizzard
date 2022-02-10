package com.revature.quizzard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class QuizzardDriver{
	public static void main(String[] args) {
		String welcomeMenu = "Welcome to Quizzard! \n"   +
							 "Please make a selection"   + 
                             "from the options below:\n" +
							 "1) Login \n"               +
							 "2) Register \n"            + 
							 "3) Exit \n"                + 
							 ">";
		System.out.print(welcomeMenu);	

		BufferedReader consoleReader = 
		new BufferedReader(new InputStreamReader(System.in));


		try {
			String userSelection = consoleReader.readLine();
			System.out.println(userSelection);
			
			switch(userSelection) {
				case "1":
							System.out.println("You selected: Login");
							break;
				case "2": 
							System.out.println("You selected: Register");
							break;
				default:
							System.out.println("You have made an incorrect" +
											   " selection.");
			}		
		} catch (IOException e) {
			e.printStackTrace();
		} //try-catch
		
		main(args);	
	}//main
}//class
