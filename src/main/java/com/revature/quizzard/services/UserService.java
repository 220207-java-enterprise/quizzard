package com.revature.quizzard.services;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.daos.UserDAO;

import java.io.IOException;
import java.util.UUID;

public class UserService {

    private UserDAO userDAO; // a dependency of UserService

    // Constructor injection
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // Setter injection
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public AppUser register(AppUser newUser) throws IOException {

        if (!isUserValid(newUser)) {
            throw new RuntimeException("Bad registration details given."); // this will halt the app
        }

        // TODO validate that the provided username and email are not already taken

        newUser.setId(UUID.randomUUID().toString());
        userDAO.save(newUser);

        return newUser;
    }

    public AppUser login(String username, String password) {

        if (!isUsernameValid(username) || !isPasswordValid(password)) {
            throw new RuntimeException("Invalid credentials provided!");
        }

        AppUser authUser = userDAO.findUserByUsernameAndPassword(username, password);

        if (authUser == null) {
            throw new RuntimeException("No user found with the provided credentials");
        }

        return authUser;

    }

    private boolean isUserValid(AppUser appUser) {

        // First name and last name are not just empty strings or filled with whitespace
        if (appUser.getFirstName().trim().equals("") || appUser.getLastName().trim().equals("")) {
            System.out.println("Bad first or last name");
            return false;
        }

        // Usernames must be a minimum of 8 and a max of 25 characters in length, and only contain alphanumeric characters.
        if (!isUsernameValid(appUser.getUsername())) {
            System.out.println("Bad username");
            return false;
        }

        // Passwords require a minimum eight characters, at least one uppercase letter, one lowercase
        // letter, one number and one special character
        if (!isPasswordValid(appUser.getPassword())) {
            System.out.println("Bad password");
            return false;
        }

        // Basic email validation
        if (!appUser.getEmail().matches("^[^@\\s]+@[^@\\s\\.]+\\.[^@\\.\\s]+$")) {
            System.out.println("Bad email");
            return false;
        }

        return true;

    }

    public boolean isUsernameValid(String username) {
        if (username == null) return false;
        return username.matches("^[a-zA-Z0-9]{8,25}");
    }

    public boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }

}
