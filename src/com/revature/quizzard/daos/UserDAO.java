package com.revature.quizzard.daos;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.exceptions.ResourcePersistenceException;

import java.io.*;

public class UserDAO implements CrudDAO<AppUser> {
    public AppUser findUserByUsername(String username){
        return null;
    }

    public AppUser findUserByEmail(String email){
        return null;
    }

    public AppUser findUserByUsernameAndPassword(String username, String password) throws IOException {
        // Persistence logic
        try {
            BufferedReader dataReader = new BufferedReader(new FileReader("data/users.txt"));
            String dataCursor;

            while ((dataCursor = dataReader.readLine()) != null) {
                String[] recordFragments = dataCursor.split(":");
                if (recordFragments[4].equals(username) && recordFragments[5].equals(password)) {
                    AppUser authUser = new AppUser();
                    authUser.setId(recordFragments[0]);
                    authUser.setFirstName(recordFragments[1]);
                    authUser.setLastName(recordFragments[2]);
                    authUser.setEmail(recordFragments[3]);
                    authUser.setUsername(recordFragments[4]);
                    authUser.setPassword(recordFragments[5]);
                    return authUser;
                }
            }
        } catch (IOException e){
            throw new ResourcePersistenceException("An error occured when accessing the data file.");
        }

        System.out.println("User not found");
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
