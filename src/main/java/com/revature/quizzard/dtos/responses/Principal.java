package com.revature.quizzard.dtos.responses;

import com.revature.quizzard.models.AppUser;

public class Principal {

    private String id;
    private String username;
    private String role;

    public Principal() {
        super();
    }

    public Principal(AppUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole().getRoleName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
