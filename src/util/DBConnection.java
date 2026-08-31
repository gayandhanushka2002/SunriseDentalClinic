/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Gayan
 */
public class DBConnection {
    
    // 1. Create a private static instance of the class (Singleton pattern)
    private static DBConnection instance;
    private Connection connection;

    // 2. Make the constructor private so objects cannot be created from outside
    private DBConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Connect to the database (WAMP default: username "root", empty password)
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunrise_dental_clinic", "root", "");
            System.out.println("Database Connected Successfully!");
            
        } catch (Exception e) {
            System.out.println("Database Connection Error: " + e.getMessage());
        }
    }

    // 3. Provide a public static method to access the single instance
    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    // Return the active database connection
    public Connection getConnection() {
        return connection;
    }
}