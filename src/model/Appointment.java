/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gayan
 */
// Class to hold appointment details
public class Appointment {
    
    private String appointmentNo;
    private String patientId;
    private String dentistId;
    private String apptDate;
    private String apptTime;

    // Default constructor
    public Appointment() {
    }

    // Parameterized constructor
    public Appointment(String appointmentNo, String patientId, String dentistId, String apptDate, String apptTime) {
        this.appointmentNo = appointmentNo;
        this.patientId = patientId;
        this.dentistId = dentistId;
        this.apptDate = apptDate;
        this.apptTime = apptTime;
    }

    // Getters and Setters
    public String getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(String appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDentistId() {
        return dentistId;
    }

    public void setDentistId(String dentistId) {
        this.dentistId = dentistId;
    }

    public String getApptDate() {
        return apptDate;
    }

    public void setApptDate(String apptDate) {
        this.apptDate = apptDate;
    }

    public String getApptTime() {
        return apptTime;
    }

    public void setApptTime(String apptTime) {
        this.apptTime = apptTime;
    }
}
