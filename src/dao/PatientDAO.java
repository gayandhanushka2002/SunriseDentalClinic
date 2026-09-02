/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Patient;
import util.DBConnection;

/**
 *
 * @author Gayan
 */
public class PatientDAO {
    
    // Method to insert a new patient into the database
    public boolean insertPatient(Patient patient) {
        
        boolean isSuccess = false;
        // Get the active database connection
        Connection conn = DBConnection.getInstance().getConnection();
        
        try {
            // SQL query to insert patient data
            String sql = "INSERT INTO patient (patient_id, name, address, contact_number, email) VALUES (?, ?, ?, ?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            // Set the patient details to the SQL query
            pst.setString(1, patient.getPatientId());
            pst.setString(2, patient.getName());
            pst.setString(3, patient.getAddress());
            pst.setString(4, patient.getContactNumber());
            pst.setString(5, patient.getEmail());
            
            // Execute the insert query
            int rowsAffected = pst.executeUpdate();
            
            // Check if the data was successfully saved
            if (rowsAffected > 0) {
                isSuccess = true;
            }
            
        } catch (Exception e) {
            System.out.println("Error saving patient in PatientDAO: " + e.getMessage());
        }
        
        // Return true if saved successfully, else false
        return isSuccess;
    }
}
