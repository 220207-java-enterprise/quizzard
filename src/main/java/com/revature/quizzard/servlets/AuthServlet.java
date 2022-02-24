package com.revature.quizzard.servlets;

import com.revature.quizzard.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private UserService userService;

    public AuthServlet(UserService userService) {
        this.userService = userService;
    }

    // Login endpoint
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("<h1>post to /auth works!</h1>");
    }

}
