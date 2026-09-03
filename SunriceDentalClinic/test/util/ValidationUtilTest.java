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
public class ValidationUtilTest {

    // TC_04: Valid Contact Number Boundary Check (BVA - Positive)

    @Test
    public void testValidContactNumber() {
        System.out.println("Executing TC_04: testValidContactNumber...");
        
        // 1. Test Data (Exactly 10 digits)
        String validNumber = "0771234567";
        
        // 2. Action (Call the validation method)
        boolean actualResult = ValidationUtil.isValidContactNumber(validNumber);
        
        // 3. Assertion (Expected: true)
        assertTrue("Test Failed: 10-digit number should be valid.", actualResult);
        
        System.out.println("TC_04: PASS - 10-digit boundary correctly accepted!");
    }


    // TC_05: Invalid Contact Number Limit Check (BVA - Negative)

    @Test
    public void testInvalidContactNumber() {
        System.out.println("\nExecuting TC_05: testInvalidContactNumber...");
        
        // 1. Test Data (9 digits and 11 digits to test boundaries)
        String nineDigitNumber = "077123456";
        String elevenDigitNumber = "07712345678";
        
        // 2. Action & 3. Assertion (Expected: false for both)
        assertFalse("Test Failed: 9-digit number should be invalid.", ValidationUtil.isValidContactNumber(nineDigitNumber));
        assertFalse("Test Failed: 11-digit number should be invalid.", ValidationUtil.isValidContactNumber(elevenDigitNumber));
        
        System.out.println("TC_05: PASS - 9-digit and 11-digit boundaries correctly rejected!");
    }

 
    // TC_06: Patient Name Formatting (Negative Test)
 
    @Test
    public void testInvalidNameFormat() {
        System.out.println("\nExecuting TC_06: testInvalidNameFormat...");
        
        // 1. Test Data (Name containing numbers and special characters)
        String invalidName = "Kamal 123!";
        
        // 2. Action (Call the validation method)
        boolean actualResult = ValidationUtil.isValidName(invalidName);
        
        // 3. Assertion (Expected: false because names should only have letters and spaces)
        assertFalse("Test Failed: Names with numbers/symbols should be invalid.", actualResult);
        
        System.out.println("TC_06: PASS - Name with special characters/numbers correctly rejected!");
    }

   
    // TC_07: Treatment Cost Amount Validation (EP - Negative Test)
    
    @Test
    public void testNegativeAmount() {
        System.out.println("\nExecuting TC_07: testNegativeAmount...");
        
        // 1. Test Data (Negative value for treatment fee)
        double negativeFee = -500.00;
        
        // 2. Action (Call the validation method)
        boolean actualResult = ValidationUtil.isValidAmount(negativeFee);
        
        // 3. Assertion (Expected: false because fee cannot be less than 0)
        assertFalse("Test Failed: Negative amounts should be invalid.", actualResult);
        
        System.out.println("TC_07: PASS - Negative amount correctly rejected!");
    }

    // TC_11: Required Fields Empty Check (Negative Test)
    
    @Test
    public void testEmptyRequiredFields() {
        System.out.println("\nExecuting TC_11: testEmptyRequiredFields...");
        
        // 1. Test Data (Empty string, string with only spaces, and null data)
        String emptyString = "";
        String spaceString = "   ";
        String nullString = null;
        
        // 2. Action & 3. Assertion (All these should be rejected by the system - Expected: false)
        assertFalse("Test Failed: Empty string should be invalid.", ValidationUtil.isNotEmpty(emptyString));
        assertFalse("Test Failed: String with only spaces should be invalid.", ValidationUtil.isNotEmpty(spaceString));
        assertFalse("Test Failed: Null string should be invalid.", ValidationUtil.isNotEmpty(nullString));
        
        System.out.println("TC_11: PASS - Empty and null fields correctly rejected!");
    }
    
}
