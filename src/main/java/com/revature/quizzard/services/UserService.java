package com.revature.quizzard.services;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.util.exceptions.AuthenticationException;
import com.revature.quizzard.util.exceptions.InvalidRequestException;

import java.io.IOException;
import java.util.UUID;

public class UserService {

    private UserDAO userDAO; // a dependency of UserService

    // Constructor injection
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public AppUser register(AppUser newUser) throws IOException {

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Bad registration details given."); // this will halt the app
        }

        // TODO validate that the provided username and email are not already taken

        // TODO encrypt provided password before storing in the database

        newUser.setId(UUID.randomUUID().toString());
        userDAO.save(newUser);

        return newUser;
    }

    public AppUser login(String username, String password) {

        if (!isUsernameValid(username) || !isPasswordValid(password)) {
            throw new InvalidRequestException("Invalid credentials provided!");
        }

        // TODO encrypt provided password (assumes password encryption is in place) to see if it matches what is in the DB

        AppUser authUser = userDAO.findUserByUsernameAndPassword(username, password);

        if (authUser == null) {
            throw new AuthenticationException();
        }

        return authUser;

    }

    private boolean isUserValid(AppUser appUser) {

        // First name and last name are not just empty strings or filled with whitespace
        if (appUser.getFirstName().trim().equals("") || appUser.getLastName().trim().equals("")) {
            return false;
        }

        // Usernames must be a minimum of 8 and a max of 25 characters in length, and only contain alphanumeric characters.
        if (!isUsernameValid(appUser.getUsername())) {
            return false;
        }

        // Passwords require a minimum eight characters, at least one uppercase letter, one lowercase
        // letter, one number and one special character
        if (!isPasswordValid(appUser.getPassword())) {
            return false;
        }

        // Basic email validation
        return isEmailValid(appUser.getEmail());

    }

    public boolean isEmailValid(String email) {
        if (email == null) return false;
        return email.matches("^[^@\\s]+@[^@\\s.]+\\.[^@.\\s]+$");
    }

    public boolean isUsernameValid(String username) {
        if (username == null) return false;
        return username.matches("^[a-zA-Z0-9]{8,25}");
    }

    public boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }

}
