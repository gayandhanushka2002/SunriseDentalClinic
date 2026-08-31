/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Treatment;
import util.DBConnection;

/**
 *
 * @author Gayan
 */
public class TreatmentDAO {
    
    // Method to insert a new treatment into the database
    public boolean insertTreatment(Treatment treatment) {
        
        boolean isSuccess = false;
        Connection conn = DBConnection.getInstance().getConnection();
        
        try {
            // SQL query to insert treatment data
            String sql = "INSERT INTO treatment (treatment_id, appointment_no, treatment_type, treatment_cost) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            // Set the treatment details to the SQL query
            pst.setString(1, treatment.getTreatmentId());
            pst.setString(2, treatment.getAppointmentNo());
            pst.setString(3, treatment.getTreatmentType());
            pst.setDouble(4, treatment.getTreatmentCost());
            
            // Execute the insert query
            int rowsAffected = pst.executeUpdate();
            
            // Check if the data was successfully saved
            if (rowsAffected > 0) {
                isSuccess = true;
            }
            
        } catch (Exception e) {
            System.out.println("Error saving treatment in TreatmentDAO: " + e.getMessage());
        }
        
        // Return true if saved successfully, else false
        return isSuccess;
    }
}
