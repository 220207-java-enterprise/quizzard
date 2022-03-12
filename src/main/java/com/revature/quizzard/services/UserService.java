package com.revature.quizzard.services;

import com.revature.quizzard.dtos.requests.LoginRequest;
import com.revature.quizzard.dtos.requests.NewUserRequest;
import com.revature.quizzard.dtos.responses.AppUserResponse;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.UserRole;
import com.revature.quizzard.repos.UserRepository;
import com.revature.quizzard.util.exceptions.AuthenticationException;
import com.revature.quizzard.util.exceptions.InvalidRequestException;
import com.revature.quizzard.util.exceptions.ResourceConflictException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDAO userDAO;
    private UserRepository userRepo;

    @Autowired
    public UserService(UserDAO userDAO, UserRepository userRepo) {
        this.userDAO = userDAO;
        this.userRepo = userRepo;
    }
    
    /* setter injection: less readable, but allows you to change
     * the value of the dependency later in the code if needed
     */
//    @Autowired
//    public void setUserDao(UserDAO userDao) {
//    	this.userDAO = userDao;
//    }

    public List<AppUserResponse> getAllUsers() {
        return userDAO.getAll()
                      .stream()
                      .map(AppUserResponse::new) // intermediate operation
                      .collect(Collectors.toList()); // terminal operation
    }

    public AppUser register(NewUserRequest newUserRequest) {

        AppUser newUser = newUserRequest.extractUser();

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Bad registration details given.");
        }

        boolean usernameAvailable = isUsernameAvailable(newUser.getUsername());
        boolean emailAvailable = isEmailAvailable(newUser.getEmail());

        if (!usernameAvailable || !emailAvailable) {
            String msg = "The values provided for the following fields are already taken by other users: ";
            if (!usernameAvailable) msg += "username ";
            if (!emailAvailable) msg += "email";
            throw new ResourceConflictException(msg);
        }

        // TODO encrypt provided password before storing in the database

        newUser.setId(UUID.randomUUID().toString());
        newUser.setRole(new UserRole("7c3521f5-ff75-4e8a-9913-01d15ee4dc97", "BASIC_USER")); // All newly registered users start as BASIC_USER
        userDAO.save(newUser);

        return newUser;
    }

    public AppUser login(LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (!isUsernameValid(username) || !isPasswordValid(password)) {
            throw new InvalidRequestException("Invalid credentials provided!");
        }

        // TODO encrypt provided password (assumes password encryption is in place) to see if it matches what is in the DB

        AppUser authUser = userRepo.findAppUserByUsernameAndPassword(username, password);

        if (authUser == null) {
            throw new AuthenticationException();
        }

        return authUser;

    }

    public boolean isUserValid(AppUser appUser) {

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

    public boolean isUsernameAvailable(String username) {
        if (username == null || !isUsernameValid(username)) return false;
        return userDAO.findUserByUsername(username) == null;
    }

    public boolean isEmailAvailable(String email) {
        if (email == null || !isEmailValid(email)) return false;
        return userDAO.findUserByEmail(email) == null;
    }

}
