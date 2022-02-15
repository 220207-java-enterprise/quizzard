package com.revature.quizzard.daos;

import com.revature.quizzard.AppUser;
import com.revature.quizzard.util.exceptions.ResourcePersistenceException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UserDAO implements CrudDAO<AppUser> {
    public AppUser findUserByUsername(String username){
        return null;
    }

    public AppUser findUserByEmail(String email){
        return null;
    }

    public AppUser findUserByUsernameAndPassword(String username, String password){
        return null;
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
