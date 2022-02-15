package com.revature.quizzard.screens;

import java.io.IOException;

// Abstract classes cannot be directly instantiated
// They can contain concrete and abstract methods
// The DO have constructors
public abstract class Screen {

    // protected members are only accessible to subclasses and other classes within the same package
    protected String route;

    protected Screen(String route) {
        this.route = route;
    }

    // concrete method; method with an implementation
    // also is final: cannot be overridden by subclasses
    public final String getRoute() {
        return route;
    }

    // abstract method aka method stub; to be implemented by concrete subclasses
    public abstract void render() throws IOException;

}