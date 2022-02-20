package com.revature.quizzard.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// This class uses
// Factory Design Pattern - abstraction of object creation code into one class
// Singleton Design Pattern - ONLY ONE INSTANCE NEEDED (this class is responsible for establishing
// connection to database)
public class ConnectionFactory { // Design Patterns implemented - Factory and Singleton

    // Singleton types -- eager loading and lazy loading

    // eager loading:
    // instantiate class when the application begins
    // create getInstance() method which returns that instance
    // call getInstance() instead of new ConnectionFactory()
    private static ConnectionFactory connectionFactory = new ConnectionFactory();

    // try lazy loading for ERS project - watch lecture @ 2/17/2022 4:02:54 minute 55 of recording

    // static initialization block -- // Force JVM to preload the PostgreSQL JDBC driver when application begins
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Properties props = new Properties();

    // pass props from application.properties file to hide database url,username and password
    private ConnectionFactory(){
        try {
            props.load(new FileReader("resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ConnectionFactory getInstance(){
        return connectionFactory;
    }

    public Connection getConnection() throws SQLException {

        // create connection logic
        Connection conn = DriverManager.getConnection(props.getProperty("db-url"), props.getProperty("db-username"),
                props.getProperty("db-password"));

        // if connection fails
        if (conn == null) {
            throw new RuntimeException("Could not establish connection with the database!");
        }

        return conn;
    }
}
