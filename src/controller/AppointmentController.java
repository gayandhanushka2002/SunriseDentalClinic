/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AppointmentDAO;
import dao.PatientDAO;
import model.Appointment;
import model.Patient;
import util.Notification;
import util.NotificationFactory;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Gayan
 */
public class AppointmentController {
    
    private PatientDAO patientDAO;
    private AppointmentDAO appointmentDAO;

    // Constructor initializes the DAOs
    public AppointmentController() {
        this.patientDAO = new PatientDAO();
        this.appointmentDAO = new AppointmentDAO();
    }

    // Method to register a patient and their appointment together
    public boolean processAppointment(Patient patient, Appointment appointment) {
        
        // 1. First, save the patient details
        boolean isPatientSaved = patientDAO.insertPatient(patient);
        
        if (isPatientSaved) {
            // 2. If patient is saved, save the appointment details
            boolean isApptSaved = appointmentDAO.insertAppointment(appointment);
            
            if (isApptSaved) {
                // 3. ADVANCED FEATURE: Send an SMS notification using Factory Pattern (Design Pattern Marks වලට මේක තියාගමු)
                Notification smsAlert = NotificationFactory.createNotification("SMS");
                smsAlert.send("Dear " + patient.getName() + ", your appointment (" + appointment.getAppointmentNo() + ") is successfully booked!");
                
                // 4. Send REAL Email notification in a Background Thread
                new Thread(() -> {
                    sendConfirmationEmail(patient, appointment);
                }).start();
                
                return true;
            }
        }
        
        // Return false if any step fails
        return false;
    }
    
    // sending email method
    private void sendConfirmationEmail(Patient patient, Appointment appt) {
        //  Gmail and, 2-Step Verification included App Password in here 
        final String senderEmail = "sunrisedentalclinic97@gmail.com"; 
        final String senderPassword = "YOUR_APP_PASSWORD_HERE"; 

        // Email Settings
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
          });

        try {
            // create Message 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(patient.getEmail()));
            message.setSubject("Appointment Confirmation - Sunrise Dental Clinic");
            
            String emailText = "Dear " + patient.getName() + ",\n\n"
                    + "Your appointment is successfully booked!\n\n"
                    + "--- Appointment Details ---\n"
                    + "Appointment No : " + appt.getAppointmentNo() + "\n"
                    + "Date : " + appt.getApptDate() + "\n"
                    + "Time : " + appt.getApptTime() + "\n\n"
                    + "Thank you,\nSunrise Dental Clinic";
                    
            message.setText(emailText);

            // send Message 
            Transport.send(message);
            
            System.out.println("Email successfully sent to: " + patient.getEmail());
            System.out.println("=================================");
            System.out.println(emailText);
            System.out.println("=================================");

        } catch (MessagingException e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
    // ----------------------------------------------
    
    // Method to pass search keyword to DAO and return data for the Table
    public java.util.List<Object[]> getAppointmentsList(String keyword) {
        dao.AppointmentDAO apptDAO = new dao.AppointmentDAO();
        return apptDAO.searchAppointments(keyword);
    }
        
    // Method to pass delete request to DAO
    public boolean deleteAppointment(String apptNo) {
        dao.AppointmentDAO apptDAO = new dao.AppointmentDAO();
        return apptDAO.deleteAppointment(apptNo);
    }
    
    // Method to pass update request to DAO
    public boolean updateAppointment(String apptNo, String newDate, String newTime) {
        dao.AppointmentDAO apptDAO = new dao.AppointmentDAO();
        return apptDAO.updateAppointment(apptNo, newDate, newTime);
    }
    
    // Method to fetch patient history from DAO
    public java.util.List<Object[]> getPatientHistory(String patientId) {
        dao.AppointmentDAO apptDAO = new dao.AppointmentDAO();
        return apptDAO.getPatientHistory(patientId);
    }
}