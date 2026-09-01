/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Gayan
 */


// Class to handle Email notifications
class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        // Simulating sending an email
        System.out.println(">>> EMAIL ALERT SENT: " + message);
    }
}

// Class to handle SMS notifications
class SmsNotification implements Notification {
    @Override
    public void send(String message) {
        // Simulating sending an SMS
        System.out.println(">>> SMS ALERT SENT: " + message);
    }
}

// Factory class to create the required notification object (Factory Method pattern)
public class NotificationFactory {
    
    // Method to generate the specific notification type
    public static Notification createNotification(String type) {
        if (type.equalsIgnoreCase("EMAIL")) {
            return new EmailNotification();
        } else if (type.equalsIgnoreCase("SMS")) {
            return new SmsNotification();
        } else {
            throw new IllegalArgumentException("Unknown notification type");
        }
    }
}