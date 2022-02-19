package com.revature.quizzard.screens;

import com.revature.quizzard.util.AppState;
import com.revature.quizzard.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class WelcomeScreen extends Screen {

    private ScreenRouter screenRouter;

    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("/welcome", consoleReader);
        this.screenRouter = router;
    }

    @Override
    public void render() {

        String welcomeMenu = "Welcome to Quizzard!\n" +
                "Please make a selection from the options below:\n" +
                "1) Login\n" +
                "2) Register\n" +
                "3) Exit\n" +
                "> ";

        System.out.print(welcomeMenu);

        try {

            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    screenRouter.navigate("/login");
                    break;
                case "2":
                    screenRouter.navigate("/register");
                    break;
                case "3":
                    AppState.shutdown();
                    return;

                default:
                    System.out.println("You have made an incorrect selection");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
