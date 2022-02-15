package com.revature.quizzard.services;

import com.revature.quizzard.AppUser;
import com.revature.quizzard.daos.UserDAO;

import java.io.IOException;
import java.util.UUID;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public AppUser register(AppUser newUser) throws IOException {
        System.out.printf("Registration info provided: %s\n", newUser.toString());
        System.out.println("checking validations...");
        if (!isValidUser(newUser)){
            throw new RuntimeException("Invalid Registration information provided.");
        }

        // TODO pass newUser to UserDAO#save
        newUser.setId(UUID.randomUUID().toString());
        userDAO.save(newUser);
        return newUser;
    }

    public void login(String username, String password){

    }

    public boolean isValidUser(AppUser appUser){

        // check firstname and last name -- not empty or filled with whitespace
        if (appUser.getFirstName().trim().equals("") || appUser.getLastName().trim().equals("")){
            System.out.println("Bad first/last name");
            return false;
        }
        // usernames must be 8-25 characters in length and only contain alphanumeric characters
        if (!appUser.getUsername().matches("^[a-zA-Z0-9]{8,25}")){
            System.out.println("Bad username");
            return false;
        }
        // password
        if(!appUser.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")){
            System.out.println("Bad Password");
            return false;
        }

        if (!appUser.getEmail().matches("^[^@\\s]+@[^@\\s\\.]+\\.[^@\\.\\s]+$")){
            System.out.println("Bad Email");
            return false;
        }

        System.out.println("Valid information was provided. Creating user..."+appUser);
        return true;
    }


    public Boolean isUsernameValid(String username){
        return username.matches("^[a-zA-Z0-9]{8,25}");
    }

    public Boolean isPasswordValid(String password){
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
    }
}
