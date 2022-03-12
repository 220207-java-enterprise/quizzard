package com.revature.quizzard.controllers;

import com.fasterxml.jackson.core.JacksonException;
import com.revature.quizzard.dtos.requests.LoginRequest;
import com.revature.quizzard.dtos.responses.Principal;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.exceptions.AuthenticationException;
import com.revature.quizzard.util.exceptions.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Principal authenticate(@RequestBody LoginRequest loginRequest) {
        return new Principal(userService.login(loginRequest));
    }

    // TODO centralize exception handlers using an aspect

    @ExceptionHandler(value = {
        InvalidRequestException.class,
        JacksonException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleBadRequests(Exception e) {

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handleFailedAuthentication(AuthenticationException e) {

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleServerError(Throwable t) {
        t.printStackTrace();
    }

}
