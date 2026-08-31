/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Appointment;
import util.DBConnection;

/**
 *
 * @author Gayan
 */
public class AppointmentDAO {
    
    // Method to insert a new appointment into the database
    public boolean insertAppointment(Appointment appt) {
        
        boolean isSuccess = false;
        Connection conn = DBConnection.getInstance().getConnection();
        
        try {
            // SQL query to insert appointment data
            String sql = "INSERT INTO appointment (appointment_no, patient_id, dentist_id, appt_date, appt_time) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            // Set the appointment details to the SQL query
            pst.setString(1, appt.getAppointmentNo());
            pst.setString(2, appt.getPatientId());
            pst.setString(3, appt.getDentistId());
            pst.setString(4, appt.getApptDate());
            pst.setString(5, appt.getApptTime());
            
            // Execute the insert query
            int rowsAffected = pst.executeUpdate();
            
            // Check if the data was successfully saved
            if (rowsAffected > 0) {
                isSuccess = true;
            }
            
        } catch (Exception e) {
            System.out.println("Error saving appointment in AppointmentDAO: " + e.getMessage());
        }
        
        // Return true if saved successfully, else false
        return isSuccess;
    }
    // Method to search appointments by Patient ID or Patient Name using INNER JOIN
    public java.util.List<Object[]> searchAppointments(String keyword) {
        java.util.List<Object[]> list = new java.util.ArrayList<>();
        try {
            java.sql.Connection conn = util.DBConnection.getInstance().getConnection();
            
            // SQL query with JOIN to get patient name and search by ID or Name
            String sql = "SELECT a.appointment_no, a.patient_id, p.name AS patient_name, a.dentist_id, a.appt_date, a.appt_time " +
                         "FROM appointment a " +
                         "JOIN patient p ON a.patient_id = p.patient_id " +
                         "WHERE a.patient_id LIKE ? OR p.name LIKE ?";
                         
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");
            
            java.sql.ResultSet rs = pst.executeQuery();
            
            // Loop through result set and add to the list
            while (rs.next()) {
                Object[] row = {
                    rs.getString("appointment_no"),
                    rs.getString("patient_id"),
                    rs.getString("patient_name"), // Added Patient Name to the table
                    rs.getString("dentist_id"),
                    rs.getString("appt_date"),
                    rs.getString("appt_time")
                };
                list.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Method to delete/cancel an appointment
    public boolean deleteAppointment(String apptNo) {
        try {
            java.sql.Connection conn = util.DBConnection.getInstance().getConnection();
            String sql = "DELETE FROM appointment WHERE appointment_no = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, apptNo);
            
            // executeUpdate returns the number of affected rows. If > 0, it was successful.
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Method to update the date and time of an existing appointment
    public boolean updateAppointment(String apptNo, String newDate, String newTime) {
        try {
            java.sql.Connection conn = util.DBConnection.getInstance().getConnection();
            String sql = "UPDATE appointment SET appt_date = ?, appt_time = ? WHERE appointment_no = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, newDate);
            pst.setString(2, newTime);
            pst.setString(3, apptNo);
            
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
   // Method to get appointment history by Patient ID or Name
    public java.util.List<Object[]> getPatientHistory(String keyword) {
        java.util.List<Object[]> list = new java.util.ArrayList<>();
        try {
            java.sql.Connection conn = util.DBConnection.getInstance().getConnection();
            
            // SQL query using JOIN to get both Patient Name and Dentist Name
            String sql = "SELECT a.appointment_no, p.name AS patient_name, a.appt_date, a.appt_time, d.name AS dentist_name " +
                         "FROM appointment a " +
                         "JOIN dentist d ON a.dentist_id = d.dentist_id " +
                         "JOIN patient p ON a.patient_id = p.patient_id " +
                         "WHERE a.patient_id LIKE ? OR p.name LIKE ? " +
                         "ORDER BY a.appt_date DESC";
                         
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");
            java.sql.ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                list.add(new Object[]{
                    rs.getString("appointment_no"),
                    rs.getString("patient_name"), // Display patient's name in table
                    rs.getString("appt_date"),
                    rs.getString("appt_time"),
                    " " + rs.getString("dentist_name")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}