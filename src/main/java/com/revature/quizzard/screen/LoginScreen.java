package com.revature.quizzard.screen;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class LoginScreen extends Screen{

    private final UserService userService;

    public LoginScreen(BufferedReader consoleReader, UserService userService){
        super("/login", consoleReader);
        this.userService = userService;
    }

    @Override
    public void render() throws IOException {
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
