package com.revature.com;

import java.util.Objects;

public class AppUser {
    private String username, password, firstName, lastName, email;

    public AppUser(){

    }

    public AppUser(String fName, String lname, String email, String uName, String password) {
        firstName=fName;
        lastName=lname;
        this.email=email;
        this.username=uName;
        this.password=password;
    }

    @Override
    public String toString() {
        return "\n" +
                "username='" + username + "\'\n" +
                "password='" + password + "\'\n" +
                "firstName='" + firstName + "\'\n" +
                "lastName='" + lastName + "\'\n" +
                "email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(username, appUser.username) && Objects.equals(password, appUser.password) && Objects.equals(firstName, appUser.firstName) && Objects.equals(lastName, appUser.lastName) && Objects.equals(email, appUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, firstName, lastName, email);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
