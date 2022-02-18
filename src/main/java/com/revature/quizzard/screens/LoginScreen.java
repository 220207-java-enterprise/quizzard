package com.revature.quizzard.screens;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginScreen extends Screen {

    private final UserService userService = new UserService();

    public LoginScreen() {
        super("/login");
    }

    @Override
    public void render() throws IOException {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please provide your account credentials to login:");

        System.out.print("Username: ");
        String username = consoleReader.readLine();

        System.out.print("Password: ");
        String password = consoleReader.readLine();

        AppUser authenticatedUser = userService.login(username, password);
        System.out.println(authenticatedUser);

    }
}
