/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.time.LocalDateTime;

/**
 *
 * @author Gayan
 */
public class SessionManager {
    
    // 1. Create a private static instance of the class (Singleton pattern)
    private static SessionManager instance;
    
    private String loggedInUser;
    private LocalDateTime loginTime;

    // 2. Make the constructor private
    private SessionManager() {
    }

    // 3. Provide a public static method to access the single instance
    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    // Method to start a user session after a successful login
    public void startSession(String username) {
        this.loggedInUser = username;
        this.loginTime = LocalDateTime.now();
        System.out.println("Session started for user: " + username + " at " + loginTime);
    }

    // Method to check if a user is currently logged in
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    // Method to get the username of the logged-in user
    public String getLoggedInUser() {
        return loggedInUser;
    }

    // Method to end the session (Logout)
    public void endSession() {
        System.out.println("Session ended for user: " + loggedInUser);
        this.loggedInUser = null;
        this.loginTime = null;
    }
}
