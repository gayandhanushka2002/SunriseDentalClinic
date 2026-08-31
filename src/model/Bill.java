/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gayan
 */
// Class to hold bill details
public class Bill {
    
    private String billNo;
    private String appointmentNo;
    private double totalCost;

    // Default constructor
    public Bill() {
    }

    // Parameterized constructor
    public Bill(String billNo, String appointmentNo, double totalCost) {
        this.billNo = billNo;
        this.appointmentNo = appointmentNo;
        this.totalCost = totalCost;
    }

    // Getters and Setters
    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(String appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
