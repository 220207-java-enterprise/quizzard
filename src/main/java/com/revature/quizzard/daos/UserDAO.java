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
    public AppUser findUserByUsername(String username){
        return null;
    }

    public AppUser findUserByEmail(String email){
        return null;
    }


    public AppUser findUserByUsernameAndPassword(String username, String password) {
        System.out.println("findUserByUsernameAndPassword was invoked!");
        // store user after executing query
        AppUser authUser =null;

        // create a connection with syntactic sugar for the try block (no need to close the connection inside "finally")
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            // new PreparedStatement that accepts SQL query
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM app_users WHERE username=? AND password=?");
            // load values for username and password to query
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            // ResultSet iteration using next()
            while(rs.next()){
                authUser = new AppUser();
                authUser.setId(rs.getString("id"));
                authUser.setFirstName(rs.getString("first_name"));
                authUser.setLastName(rs.getString("last_name"));
                authUser.setEmail(rs.getString("email"));
                authUser.setUsername(rs.getString("username"));
                authUser.setPassword(rs.getString("password"));
                //todo fix appUser to include role
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return authUser;
    }


    // we will use generics here to pass the proper Object Type
    @Override
    public void save(AppUser newUser) {
        try{
            File usersDataFile = new File("data/users.txt");
            FileWriter dataWriter = null;
            dataWriter = new FileWriter(usersDataFile, true);
            dataWriter.write(newUser.toFileString());
            dataWriter.close();
        } catch (IOException e){
           throw new ResourcePersistenceException("An error occured when accessing the data file.");
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
    public void update(AppUser updatedUser) {

    }

    @Override
    public void deleteById(String id) {

    }
}
