/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Staff;
import util.DBConnection;
import util.PasswordUtil;
        
/**
 *
 * @author Gayan
 */
        
public class StaffDAO {
    
     // Method to verify login credentials from the database
    public Staff authenticateUser(String username, String password) {
 
        Staff staff = null;
        // Get the active database connection from Singleton class
        Connection conn = DBConnection.getInstance().getConnection();
 
        try {
            // SQL query now only looks up the record by username.
            // The password itself is never compared inside SQL, since it is
            // stored as a salted hash (see PasswordUtil).
            String sql = "SELECT * FROM staff WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
 
            pst.setString(1, username);
 
            // Execute the query
            ResultSet rs = pst.executeQuery();
 
            // If a record is found, verify the entered password against the stored hash
            if (rs.next()) {
                String storedHash = rs.getString("password");
 
                // Compare the plain-text password entered at login against the
                // salted hash stored in the database.
                if (PasswordUtil.verifyPassword(password, storedHash)) {
                    staff = new Staff();
                    staff.setStaffId(String.valueOf(rs.getInt("staff_id")));
                    staff.setUsername(rs.getString("username"));
                    staff.setPassword(storedHash);
                }
            }
 
        } catch (Exception e) {
            System.out.println("Error in StaffDAO: " + e.getMessage());
        }
 
        // Return the staff object (Will return null if login fails, or password is wrong)
        return staff;
    }
 
    // Method to register a new staff member with a securely hashed password.
    // Use this whenever a new staff account is created.
    public boolean registerStaff(String username, String plainPassword) {
 
        Connection conn = DBConnection.getInstance().getConnection();
        boolean isSuccess = false;
 
        try {
            String hashedPassword = PasswordUtil.hashPassword(plainPassword);
 
            String sql = "INSERT INTO staff (username, password) VALUES (?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, hashedPassword);
 
            int rowsAffected = pst.executeUpdate();
            isSuccess = rowsAffected > 0;
 
        } catch (Exception e) {
            System.out.println("Error registering staff: " + e.getMessage());
        }
 
        return isSuccess;
    }
}