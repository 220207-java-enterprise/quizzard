package com.revature.quizzard.screen;

import com.revature.quizzard.AppUser;
import com.revature.quizzard.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginScreen extends Screen{

    private  UserService userService = new UserService();
    public LoginScreen(){
        super("/login");
    }
    @Override
    public void render() throws IOException {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please provide your account credentials to login to your account:");

        // UI logic
        System.out.println("Username: ");
        String username = consoleReader.readLine();

        System.out.println("Password: ");
        String password = consoleReader.readLine();

        AppUser authenticatedUser = userService.login(username, password);
        System.out.println(authenticatedUser);

    }
}
