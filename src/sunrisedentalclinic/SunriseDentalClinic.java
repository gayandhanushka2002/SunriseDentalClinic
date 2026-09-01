/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sunrisedentalclinic;

/**
 *
 * @author Gayan
 */

// Import the LoginUI from the view package
import view.LoginUI;

public class SunriseDentalClinic {

    public static void main(String[] args) {
        
        // Start the application by showing the Login UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Create an object of LoginUI and make it visible
                new LoginUI().setVisible(true);
            }
        });
        
    }
}