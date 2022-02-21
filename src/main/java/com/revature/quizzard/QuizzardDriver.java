package com.revature.quizzard;

import com.revature.quizzard.screen.LoginScreen;
import com.revature.quizzard.screen.RegisterScreen;
import com.revature.quizzard.util.AppState;


import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

public class QuizzardDriver {

    private static int loopCounter = 0;

    public static void main(String[] args) {
        AppState app = new AppState();
        app.startup();
    }

}
