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
            // SQL query to check if username and password match
            String sql = "SELECT * FROM staff WHERE username = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            // Set the values to the SQL query
            pst.setString(1, username);
            pst.setString(2, password);
            
            // Execute the query
            ResultSet rs = pst.executeQuery();
            
            // If a record is found, create a Staff object with database values
            if (rs.next()) {
                staff = new Staff();
                staff.setStaffId(String.valueOf(rs.getInt("staff_id")));
                staff.setUsername(rs.getString("username"));
                staff.setPassword(rs.getString("password"));
            }
            
        } catch (Exception e) {
            System.out.println("Error in StaffDAO: " + e.getMessage());
        }
        
        // Return the staff object (Will return null if login fails)
        return staff;
    }
}
