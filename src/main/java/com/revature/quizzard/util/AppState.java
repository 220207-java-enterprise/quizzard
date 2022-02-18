package com.revature.quizzard.util;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.screens.RegisterScreen;
import com.revature.quizzard.screens.WelcomeScreen;
import com.revature.quizzard.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class AppState {

    private static boolean appRunning;
    private final ScreenRouter router;


    public AppState() {
        System.out.printf("Application initialization started at %s\n", LocalDateTime.now());

        appRunning = true;
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        router = new ScreenRouter();

        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        router.addScreen(new WelcomeScreen(consoleReader, router));
        router.addScreen(new RegisterScreen(consoleReader, userService)); // TODO probably will use the router in the future
        router.addScreen(new LoginScreen(consoleReader, userService)); // TODO probably will use the router in the future

        System.out.printf("Application initialization completed at %s\n", LocalDateTime.now());

    }

    public void startup() {
        while (appRunning) {
            router.navigate("/welcome");
        }
    }

    public static void shutdown() {
        appRunning = false;
    }

}
