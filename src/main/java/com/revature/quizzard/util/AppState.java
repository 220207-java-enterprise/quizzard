package com.revature.quizzard.util;

// this class will create all dependencies we expect to use, wire them together and minimize the number of objects we
// create

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.screen.LoginScreen;
import com.revature.quizzard.screen.RegisterScreen;
import com.revature.quizzard.screen.WelcomeScreen;
import com.revature.quizzard.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

// dependency injection will allow us to couple classes together in this class!!!
public class AppState {

    private static boolean appRunning;

    private final ScreenRouter router;

    public AppState(){
        System.out.printf("Application initialization started at %s\n", LocalDateTime.now());

        appRunning = true;
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        router = new ScreenRouter();

        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);

        router.addScreen(new WelcomeScreen(consoleReader, router));
        router.addScreen(new LoginScreen(consoleReader, userService)); // TODO use router
        router.addScreen(new RegisterScreen(consoleReader, userService)); // TODO use router

        System.out.printf("Application initialization ended at %s\n", LocalDateTime.now());
    }

    public void startup(){
        while (appRunning) {
            router.navigate("/welcome");
        }
    }

    public static void shutdown(){
        appRunning = false;
    }

}
