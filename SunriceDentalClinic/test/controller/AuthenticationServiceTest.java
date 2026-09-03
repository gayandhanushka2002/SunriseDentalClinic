/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gayan
 */
public class AuthenticationServiceTest {
    
   private AuthenticationService authService;

    // This method runs before every test case to set up the environment
    @Before
    public void setUp() {
        authService = new AuthenticationService();
    }

    // This method runs after every test case to clean up data
    @After
    public void tearDown() {
        util.SessionManager.getInstance().endSession();
    }


    // TC_01: Valid User Login (Positive Test)

    @Test
    public void testValidLogin() {
        System.out.println("Executing TC_01: testValidLogin...");
        
        // 1. Test Data (Valid credentials from the database)
        String validUsername = "staff1"; 
        String validPassword = "s123";   
        
        // 2. Action (Attempt to login)
        boolean actualResult = authService.login(validUsername, validPassword);
        
        // 3. Assertion (Check if login is successful)
        assertTrue("Test Failed: Login should be successful with valid credentials.", actualResult);
        
        // Verify if the session is properly created
        assertNotNull("Session should be created", util.SessionManager.getInstance().getLoggedInUser());
        
        System.out.println("TC_01: PASS - User logged in successfully!");
    }


    // TC_02: Invalid Password Login (Negative Test)

    @Test
    public void testInvalidPasswordLogin() {
        System.out.println("\nExecuting TC_02: testInvalidPasswordLogin...");
        
        // 1. Test Data (Provide a valid username but a wrong password)
        String validUsername = "staff1";
        String invalidPassword = "wrongPassword999";
        
        // 2. Action (Attempt to login)
        boolean actualResult = authService.login(validUsername, invalidPassword);
        
        // 3. Assertion (Check if login is rejected - Expected: false)
        assertFalse("Test Failed: Login should fail for an incorrect password.", actualResult);
        
        // Verify that no session is created for invalid users
        assertNull("Session should NOT be created for invalid login", util.SessionManager.getInstance().getLoggedInUser());
        
        System.out.println("TC_02: PASS - Invalid password correctly rejected!");
    }


    // TC_03: Session Management and Logout (Integration Test)
 
    @Test
    public void testSessionLogout() {
        System.out.println("\nExecuting TC_03: testSessionLogout...");
        
        // 1. Login and start a session
        authService.login("staff1", "s123");
        assertTrue("User should be logged in", util.SessionManager.getInstance().isLoggedIn());
        assertEquals("staff1", util.SessionManager.getInstance().getLoggedInUser());
        
        // 2. Call the logout function
        authService.logout();
        
        // 3. Check if the session is cleared after logout
        assertFalse("User should not be logged in after logout", util.SessionManager.getInstance().isLoggedIn());
        assertNull("Logged-in user should be null after logout", util.SessionManager.getInstance().getLoggedInUser());
        
        System.out.println("TC_03: PASS - Session state cleared successfully on logout!");
    }
}
