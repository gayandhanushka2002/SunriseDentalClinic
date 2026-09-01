/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Gayan
 */

public class ValidationUtil {

    // Method to check if a text field is empty
    public static boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }

    // Method to validate if a name contains only letters and spaces
    public static boolean isValidName(String name) {
        return name != null && name.matches("^[a-zA-Z\\s]+$");
    }

    // Method to validate if a contact number has exactly 10 digits
    public static boolean isValidContactNumber(String number) {
        return number != null && number.matches("^\\d{10}$");
    }

    // Method to validate if an amount (cost/fee) is a positive number
    public static boolean isValidAmount(double amount) {
        return amount >= 0;
    }
    
    // Method to validate if a password has a minimum of 6 characters
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}