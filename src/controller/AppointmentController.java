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
                // 3. ADVANCED FEATURE: Send an SMS notification using Factory Pattern
                Notification smsAlert = NotificationFactory.createNotification("SMS");
                smsAlert.send("Dear " + patient.getName() + ", your appointment (" + appointment.getAppointmentNo() + ") is successfully booked!");
                
                return true;
            }
        }
        
        // Return false if any step fails
        return false;
    }
    
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