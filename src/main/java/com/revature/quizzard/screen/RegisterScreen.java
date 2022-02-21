package com.revature.quizzard.screen;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;

import java.io.*;

public class RegisterScreen extends Screen{

    private final UserService userService;

    public RegisterScreen(BufferedReader consoleReader, UserService userService){
        super("/register", consoleReader);
        this.userService = userService;
    }
    @Override
    public void render() throws IOException {

        System.out.println("You selected: Register");
        System.out.println("Please provide some basic information to register an account:");

        System.out.println("First name: ");
        String firstName = consoleReader.readLine();

        System.out.println("Last name: ");
        String lastName = consoleReader.readLine();

        System.out.println("Email: ");
        String email = consoleReader.readLine();

        System.out.println("Username: ");
        String username = consoleReader.readLine();

        System.out.println("Password: ");
        String password = consoleReader.readLine();

        // TODO validate the user input --> static method isValidUser() (DONE)

        AppUser newUser = new AppUser(firstName,lastName,email,username,password);

        AppUser registeredUser = userService.register(newUser);
        System.out.println(registeredUser);
    }


}
