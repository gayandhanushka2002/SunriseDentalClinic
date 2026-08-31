/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Dentist;
import util.DBConnection;

/**
 *
 * @author Gayan
 */
public class DentistDAO {
    
    // Method 1: Get all dentists (Returns List of Dentist objects)
    public List<Dentist> getAllDentists() {
        List<Dentist> dentistList = new ArrayList<>();
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM dentist";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                Dentist dentist = new Dentist();
                dentist.setDentistId(rs.getString("dentist_id"));
                dentist.setName(rs.getString("name"));
                dentist.setConsultationFee(rs.getDouble("consultation_fee"));
                
                dentistList.add(dentist);
            }
        } catch (Exception e) {
            System.out.println("Error getting dentists in DentistDAO: " + e.getMessage());
        }
        return dentistList;
    }
    
    // Method 2: Add a new dentist
    public boolean addDentist(model.Dentist dentist) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            String sql = "INSERT INTO dentist (dentist_id, name, consultation_fee) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, dentist.getDentistId());
            pst.setString(2, dentist.getName());
            pst.setDouble(3, dentist.getConsultationFee());
            
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method 3: Get all dentists for the Table (Renamed to avoid conflict)
    public List<Object[]> getDentistsForTable() {
        List<Object[]> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM dentist";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                list.add(new Object[]{
                    rs.getString("dentist_id"),
                    rs.getString("name"),
                    rs.getDouble("consultation_fee")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    } 
}