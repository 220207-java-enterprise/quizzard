package com.revature.quizzard.screen;

import com.revature.quizzard.util.AppState;
import com.revature.quizzard.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class WelcomeScreen extends Screen{

    private ScreenRouter screenRouter;
    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router){
        super("/welcome", consoleReader);
        this.screenRouter = router;
    }

    @Override
    public void render() throws IOException {
        String welcomeMenu = "Welcome to Quizzard!\n" +
                "Please make a selection from the options below\n"+
                "1) Login\n"+
                "2) Register\n"+
                "3) Exit\n"+
                "> ";

        System.out.print(welcomeMenu);

        String userSelection = consoleReader.readLine();

        switch(userSelection) {
            case "1":
                System.out.println("You selected Login");
                screenRouter.navigate("/login");
                break;
            case "2":
                System.out.println("You selected Register");
                screenRouter.navigate("/register"); //TODO there are better ways
                break;
            case "3":
                System.out.println("You selected: Exit");
                AppState.shutdown();
                return;
            default:
                System.out.println("You have made an incorrect selection");
        }
    }

}

