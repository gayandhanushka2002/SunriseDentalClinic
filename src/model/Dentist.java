/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gayan
 */
// Dentist class inherits from Person
public class Dentist extends Person {
    
    private String dentistId;
    private double consultationFee;

    // Default constructor
    public Dentist() {
    }

    // Parameterized constructor
    public Dentist(String dentistId, String name, String address, String contactNumber, double consultationFee) {
        // Call the superclass (Person) constructor
        super(name, address, contactNumber);
        this.dentistId = dentistId;
        this.consultationFee = consultationFee;
    }

    // Getters and Setters
    public String getDentistId() {
        return dentistId;
    }

    public void setDentistId(String dentistId) {
        this.dentistId = dentistId;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }
}