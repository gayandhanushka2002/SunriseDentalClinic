/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DentistDAO;
import model.Dentist;
import java.util.List;

/**
 *
 * @author Gayan
 */
public class DentistController {
    
    private DentistDAO dentistDAO;

    public DentistController() {
        this.dentistDAO = new DentistDAO();
    }

    public boolean addNewDentist(String id, String name, double fee) {
        // Create an empty Dentist object first
        Dentist d = new Dentist();
        
        // Use Setter methods to set the data one by one
        d.setDentistId(id);
        d.setName(name);
        d.setConsultationFee(fee);
        
        // Pass the object to DAO
        return dentistDAO.addDentist(d);
    }

    public List<Object[]> getDentistsList() {
        return dentistDAO.getDentistsForTable();
    }
}