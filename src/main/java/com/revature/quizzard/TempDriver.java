package com.revature.quizzard;

import com.revature.quizzard.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TempDriver {

    public static void main(String[] args){

        // Singleton eager loading
        ConnectionFactory connFactory = ConnectionFactory.getInstance();
        ConnectionFactory connFactory2 = ConnectionFactory.getInstance();

        //returns true because both instances point to same object in memory. Hence, Singleton Design Pattern
        //System.out.println(connFactory==connFactory2);

        Connection conn = null;
        try {
            conn = connFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (conn == null){
            System.out.println("Error");
        } else {
            System.out.println("Success");
        }

    }
}