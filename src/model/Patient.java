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
    private String email;
    

    // Default constructor
    public Patient() {
    }

    // Parameterized constructor
    public Patient(String patientId, String name, String address, String contactNumber,String email) {
        // Call the superclass (Person) constructor
        super(name, address, contactNumber);
        this.patientId = patientId;
        this.email = email;
    }

    // Getter and Setter for Patient ID
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}