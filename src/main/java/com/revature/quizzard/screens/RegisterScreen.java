package com.revature.quizzard.screens;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;

import java.io.*;

public class RegisterScreen extends Screen {

    private final UserService userService = new UserService();

    public RegisterScreen() {
        super("/register");
    }

    @Override
    public void render() throws IOException {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

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

        AppUser newUser = new AppUser(firstName, lastName, email, username, password);
        AppUser registeredUser = userService.register(newUser); // TODO should this return something?
        System.out.println(registeredUser);

    }

}
