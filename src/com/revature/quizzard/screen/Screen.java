package com.revature.quizzard.screen;

import java.io.IOException;

// abstract classes cannot be instantiated
// they do have constructors
public abstract class Screen {

    //protected members are only accessible to subclasses and other classes within the same package
    protected String route;

    // why do abstract classes have constructor?
        // because subclasses need to be instantiated
    protected Screen(){
    }

    protected Screen(String route){
        this.route = route;
    }

    public final String getRoute() {
        return route;
    }

    // to be implemented by concrete subclasses
    public abstract void render() throws IOException;
}
