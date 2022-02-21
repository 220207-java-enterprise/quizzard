package com.revature.quizzard.screen;

import java.io.BufferedReader;
import java.io.IOException;

// abstract classes cannot be instantiated
// they do have constructors
public abstract class Screen {

    //protected members are only accessible to subclasses and other classes within the same package
    protected String route;
    // all screens will have a consoleReader (for Dependency Injection using constructor)
    protected final BufferedReader consoleReader;

    // why do abstract classes have constructor?
        // because subclasses need to be instantiated


    protected Screen(String route, BufferedReader consoleReader){
        this.route = route;
        this.consoleReader = consoleReader;
    }

    public final String getRoute() {
        return route;
    }

    // to be implemented by concrete subclasses
    public abstract void render() throws IOException;
}
