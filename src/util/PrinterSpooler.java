/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

// Import these classes to work with files
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Gayan
 */
public class PrinterSpooler {
    
    // Singleton instance
    private static PrinterSpooler instance;

    // Private constructor to prevent creating multiple objects
    private PrinterSpooler() {
    }

    // Method to get the single instance of the class
    public static PrinterSpooler getInstance() {
        if (instance == null) {
            instance = new PrinterSpooler();
        }
        return instance;
    }

    // Old method to print to the terminal (Kept this to avoid errors in other pages)
    public void print(String content) {
        System.out.println(content);
    }
    
    // New method to save the bill as a Text (.txt) file
    public void printReceipt(String billNo, String billContent) {
        try {
            // Create a new text file using the bill number (Example: Bill_B8350.txt)
            FileWriter writer = new FileWriter("Bill_" + billNo + ".txt");
            
            // Write the bill details into the text file
            writer.write(billContent);
            
            // Close the file to save it properly
            writer.close();
            
            // Print a success message in the terminal
            System.out.println("Bill Successfully Saved as TXT File!");
            
        } catch (IOException e) {
            // Print an error message if something goes wrong
            System.out.println("Error printing bill: " + e.getMessage());
        }
    }
}