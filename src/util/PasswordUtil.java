/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
/**
 *
 * @author Gayan
 */
public class PasswordUtil {
    
      private static final int SALT_LENGTH_BYTES = 16;
 
    // Generates a new salted hash for a plain-text password.
    // Call this once when creating/updating a staff account.
    public static String hashPassword(String plainPassword) {
        try {
            byte[] salt = new byte[SALT_LENGTH_BYTES];
            new SecureRandom().nextBytes(salt);
 
            byte[] hash = hashWithSalt(plainPassword, salt);
 
            String saltStr = Base64.getEncoder().encodeToString(salt);
            String hashStr = Base64.getEncoder().encodeToString(hash);
 
            return saltStr + ":" + hashStr;
 
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
 
    // Verifies a plain-text password (entered at login) against the
    // stored "salt:hash" value retrieved from the database.
    public static boolean verifyPassword(String plainPassword, String storedValue) {
        try {
            if (storedValue == null || !storedValue.contains(":")) {
                return false;
            }
 
            String[] parts = storedValue.split(":");
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] storedHash = Base64.getDecoder().decode(parts[1]);
 
            byte[] testHash = hashWithSalt(plainPassword, salt);
 
            return Arrays.equals(storedHash, testHash);
 
        } catch (Exception e) {
            return false;
        }
    }
 
    private static byte[] hashWithSalt(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        return md.digest(password.getBytes());
    }
}
    
