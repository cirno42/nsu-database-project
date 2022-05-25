package ru.nsu.nikolotov.dbfrontend.app.patientsubframes;

import ru.nsu.nikolotov.dbfrontend.api.HospitalAPI;
import ru.nsu.nikolotov.dbfrontend.api.PatientAPI;
import ru.nsu.nikolotov.dbfrontend.api.ServiceStaffPositionsAPI;
import ru.nsu.nikolotov.dbfrontend.app.utilframes.TableFrame;
import ru.nsu.nikolotov.dbfrontend.entities.ServiceStaffPositionEntity;
import ru.nsu.nikolotov.dbfrontend.layout.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PatientsSpecialQueryMenu {
    private final JFrame menuFrame = new JFrame("Menu");
    private final JButton getListOfPatientsTreatsInHospButton = new JButton("... people, who treats in such hospital");
    private final JButton getListOfPatientsTreatedInHospButton = new JButton("... people, who treated in such hospital");
    private final JButton getListOfPatientsTreatedByDoctorButton = new JButton("... people, who treated by such doctor");
    private final JButton getListOfPatientsTreatsInSuchPolyclinicButton = new JButton("... people, who treated in such polyclinic");


    private final JButton backButton = new JButton("Back");

    public PatientsSpecialQueryMenu() {
        menuFrame.setSize(500, 400);
        menuFrame.setLayout(new VerticalLayout());
        getListOfPatientsTreatedInHospButton.setPreferredSize(new Dimension(450, 50));
        getListOfPatientsTreatsInHospButton.setPreferredSize(new Dimension(450, 50));
        getListOfPatientsTreatedByDoctorButton.setPreferredSize(new Dimension(450, 50));
        getListOfPatientsTreatsInSuchPolyclinicButton.setPreferredSize(new Dimension(450, 50));

        backButton.setPreferredSize(new Dimension(300, 50));

        menuFrame.add(getListOfPatientsTreatedInHospButton);
        menuFrame.add(getListOfPatientsTreatsInHospButton);
        menuFrame.add(getListOfPatientsTreatedByDoctorButton);
        menuFrame.add(getListOfPatientsTreatsInSuchPolyclinicButton);
        menuFrame.add(backButton);
        addActionListeners();
    }

    public void callFrame() {
        menuFrame.setVisible(true);
    }

    private void addActionListeners() {
        backButton.addActionListener(l -> menuFrame.dispose());
        getListOfPatientsTreatedInHospButton.addActionListener(l -> getListOfPatientsTreatedInHospButtonAction());
    }

    private void getListOfPatientsTreatedInHospButtonAction() {
        JFrame queryFrame = new JFrame("Set parameters");

        JButton getButton = new JButton("Get");
        JButton backButton = new JButton("Back");
        queryFrame.setSize(500, 500);
        queryFrame.setLayout(new VerticalLayout());

        JComboBox<String> medicineInstitutions = new JComboBox<>();

        var hospitals = HospitalAPI.getAll();
        hospitals.forEach(el -> {
            medicineInstitutions.addItem(el.getName());
        });

        JTextField admissionDate = new JTextField("Admission date in dd-MM-yyyy");
        JTextField recoveryDate = new JTextField("Recovery date in dd-MM-yyyy");

        getButton.addActionListener(l -> {
            var patients = PatientAPI.getPatientsWhoTreatedInHospital(
                    hospitals.get(medicineInstitutions.getSelectedIndex()).getId(),
                    admissionDate.getText(),
                    recoveryDate.getText()
            );
            TableFrame.showTable(patients);
        });

        medicineInstitutions.setPreferredSize(new Dimension(300, 50));
        admissionDate.setPreferredSize(new Dimension(300, 50));
        recoveryDate.setPreferredSize(new Dimension(300, 50));
        getButton.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));


        queryFrame.add(medicineInstitutions);
        queryFrame.add(admissionDate);
        queryFrame.add(recoveryDate);
        queryFrame.add(getButton);
        queryFrame.add(backButton);
        queryFrame.setVisible(true);

    }
}
