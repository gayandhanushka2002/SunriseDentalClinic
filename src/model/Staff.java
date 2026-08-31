/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gayan
 */
// Staff class inherits from Person
public class Staff extends Person {
    
    private String staffId;
    private String username;
    private String password;

    // Default constructor
    public Staff() {
    }

    // Parameterized constructor
    public Staff(String staffId, String name, String address, String contactNumber, String username, String password) {
        // Call the superclass (Person) constructor
        super(name, address, contactNumber);
        this.staffId = staffId;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}