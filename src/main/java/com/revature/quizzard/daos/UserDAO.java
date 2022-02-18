package com.revature.quizzard.daos;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.ConnectionFactory;
import com.revature.quizzard.util.exceptions.ResourcePersistenceException;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements CrudDAO<AppUser> {

    public AppUser findUserByUsername(String username) {
        return null;
    }

    public AppUser findUserByEmail(String email) {
        return null;
    }

    public AppUser findUserByUsernameAndPassword(String username, String password) {

        System.out.println("findUserByUsernameAndPassword was invoked!!!!!");

        AppUser authUser = null;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM app_users WHERE username = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                authUser = new AppUser();
                authUser.setId(rs.getString("id"));
                authUser.setFirstName(rs.getString("first_name"));
                authUser.setLastName(rs.getString("last_name"));
                authUser.setEmail(rs.getString("email"));
                authUser.setUsername(rs.getString("username"));
                authUser.setPassword(rs.getString("password"));
                // TODO fix AppUser to include role
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authUser;
    }

    @Override
    public void save(AppUser newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO app_users VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, newUser.getId());
            pstmt.setString(2, newUser.getFirstName());
            pstmt.setString(3, newUser.getLastName());
            pstmt.setString(4, newUser.getEmail());
            pstmt.setString(5, newUser.getUsername());
            pstmt.setString(6, newUser.getPassword());
            pstmt.setString(7, "7c3521f5-ff75-4e8a-9913-01d15ee4dc97"); // TODO fix with Role enum

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                conn.rollback();
                throw new RuntimeException("Failed to persist user to data source");
            }

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AppUser getById(String id) {
        return null;
    }

    @Override
    public AppUser[] getAll() {
        return new AppUser[0];
    }

    @Override
    public void update(AppUser updatedObject) {

    }

    @Override
    public void deleteById(String id) {

    }
}
