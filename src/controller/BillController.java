/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BillDAO;
import model.Bill;
import util.PrinterSpooler;

/**
 *
 * @author Gayan
 */
public class BillController {
    
    private BillDAO billDAO;

    // Constructor initializes the DAO
    public BillController() {
        this.billDAO = new BillDAO();
    }

    // Method to generate a new bill for a given appointment
    public Bill generateBill(String appointmentNo) {
        
        // 1. Call DAO to calculate total cost using the MySQL Stored Procedure
        double totalCost = billDAO.callCalculateBillTotal(appointmentNo);
        
        // 2. Create a new Bill object (Generating a simple random Bill Number)
        String billNo = "B-" + (System.currentTimeMillis() % 10000);
        Bill newBill = new Bill(billNo, appointmentNo, totalCost);
        
        // 3. Save the bill to the database
        boolean isSaved = billDAO.insertBill(newBill);
        
        if (isSaved) {
            return newBill; // Return the bill object if saved successfully
        }
        
        return null; // Return null if saving failed
    }

   // Method to send the bill to the printer spooler (Singleton Pattern)
    public void printBill(String billContent) {
        if (billContent != null && !billContent.isEmpty()) {
            // Using the Singleton pattern to get the printer instance
            util.PrinterSpooler.getInstance().print(billContent);
        } else {
            System.out.println("Error: Cannot print an empty bill.");
        }
    }
    
    // Method to fetch bill details for UI table
    public java.util.List<Object[]> searchBillDetails(String keyword) {
        dao.BillDAO billDAO = new dao.BillDAO();
        return billDAO.searchBillDetails(keyword);
    }
    
    // Method to save the generated bill to the database
    public boolean saveBill(String billNo, String apptNo, double totalCost) {
        model.Bill b = new model.Bill();
        // Using setter methods to set data to the Bill model
        b.setBillNo(billNo);
        b.setAppointmentNo(apptNo);
        b.setTotalCost(totalCost);
        
        dao.BillDAO billDAO = new dao.BillDAO();
        return billDAO.insertBill(b);
    }
}