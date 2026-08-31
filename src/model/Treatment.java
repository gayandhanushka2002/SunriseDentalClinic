/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gayan
 */
// Class to hold treatment details
public class Treatment {
    
    private String treatmentId;
    private String appointmentNo;
    private String treatmentType;
    private double treatmentCost;

    // Default constructor
    public Treatment() {
    }

    // Parameterized constructor
    public Treatment(String treatmentId, String appointmentNo, String treatmentType, double treatmentCost) {
        this.treatmentId = treatmentId;
        this.appointmentNo = appointmentNo;
        this.treatmentType = treatmentType;
        this.treatmentCost = treatmentCost;
    }

    // Getters and Setters
    public String getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(String appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public double getTreatmentCost() {
        return treatmentCost;
    }

    public void setTreatmentCost(double treatmentCost) {
        this.treatmentCost = treatmentCost;
    }
}
