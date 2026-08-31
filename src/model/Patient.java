/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gayan
 */
public class Patient extends Person {
    
    private String patientId;

    // Default constructor
    public Patient() {
    }

    // Parameterized constructor
    public Patient(String patientId, String name, String address, String contactNumber) {
        // Call the superclass (Person) constructor
        super(name, address, contactNumber);
        this.patientId = patientId;
    }

    // Getter and Setter for Patient ID
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}