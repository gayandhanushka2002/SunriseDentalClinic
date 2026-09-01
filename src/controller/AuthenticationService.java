/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.StaffDAO;
import model.Staff;
import util.SessionManager;

/**
 *
 * @author Gayan
 */
public class AuthenticationService {
    
    private StaffDAO staffDAO;

    // Constructor initializes the DAO
    public AuthenticationService() {
        this.staffDAO = new StaffDAO();
    }

    // Method to handle the login process
    public boolean login(String username, String password) {
        
        // 1. Check credentials using DAO
        Staff staff = staffDAO.authenticateUser(username, password);
        
        // 2. If valid, start a session and return true
        if (staff != null) {
            SessionManager.getInstance().startSession(username);
            return true;
        }
        
        // 3. If invalid, return false
        return false;
    }

    // Method to handle the logout process
    public void logout() {
        SessionManager.getInstance().endSession();
    }
}