/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ReportDAO;
import java.util.List;

/**
 *
 * @author Gayan
 */
public class ReportController {
    
    private ReportDAO reportDAO;

    // Constructor initializes the DAO
    public ReportController() {
        this.reportDAO = new ReportDAO();
    }

    // Method to fetch the revenue report data for the UI
    public List<String> generateRevenueReport() {
        return reportDAO.getRevenueByDentist();
    }
}