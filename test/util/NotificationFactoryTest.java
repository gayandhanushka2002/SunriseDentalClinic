/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gayan
 */
public class NotificationFactoryTest {
    

    // TC_08: Factory Pattern Notification Creation (Positive Test)

    @Test
    public void testValidNotificationCreation() {
        System.out.println("Executing TC_08: testValidNotificationCreation...");
        
        // 1. Action (Request an SMS notification object from the Factory)
        Notification smsAlert = NotificationFactory.createNotification("SMS");
        
        // 2. Assertion (Check if the object is successfully created and not null)
        assertNotNull("Test Failed: Factory should return a valid object, not null.", smsAlert);
        
        // Check if the returned object is actually of type 'SmsNotification'
        assertEquals("Test Failed: Object should be of type SmsNotification.", 
                     "SmsNotification", smsAlert.getClass().getSimpleName());
        
        System.out.println("TC_08: PASS - Factory correctly instantiated the SMS notification object!");
    }


    // TC_09: Invalid Notification Type Exception Handling (Exception Test)

    @Test
    public void testInvalidNotificationType() {
        System.out.println("\nExecuting TC_09: testInvalidNotificationType...");
        
        try {
            // 1. Action (Request an unsupported notification type like "WHATSAPP")
            // This line should trigger an error (Exception) intentionally.
            NotificationFactory.createNotification("WHATSAPP");
            
            // 2. If the code reaches this line, it means NO exception was thrown, so the test MUST fail.
            fail("Test Failed: Factory should have thrown an IllegalArgumentException for unknown types.");
            
        } catch (IllegalArgumentException e) {
            // 3. Assertion (Exception was successfully caught as expected)
            System.out.println("TC_09: PASS - Exception caught successfully -> " + e.getMessage());
            assertTrue(true); // Test passes
        }
        
    }
    
  
    // TC_10: Factory Pattern Notification Creation - EMAIL (Positive Test)
 
    @Test
    public void testEmailNotificationCreation() {
        System.out.println("\nExecuting TC_10: testEmailNotificationCreation...");
        
        // 1. Action (Request an EMAIL notification object from the Factory)
        Notification emailAlert = NotificationFactory.createNotification("EMAIL");
        
        // 2. Assertion (Check if the object is successfully created and not null)
        assertNotNull("Test Failed: Factory should return a valid object, not null.", emailAlert);
        
        // Check if the returned object is actually of type 'EmailNotification'
        assertEquals("Test Failed: Object should be of type EmailNotification.", 
                     "EmailNotification", emailAlert.getClass().getSimpleName());
        
        System.out.println("TC_10: PASS - Factory correctly instantiated the EMAIL notification object!");
    }
    
}
