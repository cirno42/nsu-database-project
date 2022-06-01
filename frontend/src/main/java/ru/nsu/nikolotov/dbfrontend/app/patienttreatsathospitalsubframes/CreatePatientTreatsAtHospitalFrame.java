package ru.nsu.nikolotov.dbfrontend.app.patienttreatsathospitalsubframes;

import ru.nsu.nikolotov.dbfrontend.api.*;
import ru.nsu.nikolotov.dbfrontend.app.utilframes.TableFrame;
import ru.nsu.nikolotov.dbfrontend.dtos.CreateDoctorWorksAtHospitalDTO;
import ru.nsu.nikolotov.dbfrontend.dtos.CreatePatientTreatsAtHospitalDTO;
import ru.nsu.nikolotov.dbfrontend.dtos.DoctorDTO;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorWorksAtHospitalFullInfoEntity;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalEntity;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalWardFullInfoEntity;
import ru.nsu.nikolotov.dbfrontend.entities.PatientEntity;
import ru.nsu.nikolotov.dbfrontend.layout.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreatePatientTreatsAtHospitalFrame {
    private final JFrame frame = new JFrame("Create");
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;

    private final List<DoctorDTO> doctors = DoctorsAPI.getAllDTO();
    private final List<HospitalWardFullInfoEntity> wards = HospitalWardsAPI.getAll();
    private final List<PatientEntity> patients = PatientAPI.getAll();

    private final JComboBox<String> doctorsCombobox = new JComboBox<>();
    private final JComboBox<String> wardsCombobox = new JComboBox<>();

    private final JComboBox<String> patientsCombobox = new JComboBox<>();
    private final JTextField admDateTextField = new JTextField("Admission date in dd-MMM-yyyy");
    private final JTextField recDateTextField = new JTextField("Recovery date in dd-MMM-yyyy");


    private final JTextField tempTextField = new JTextField("Temperature");
    private final JTextField currentDiseaseTextField = new JTextField("Current disease");
    private final JTextField currentMedsTextField = new JTextField("Current meds");
    private final JTextField currentOperationTextField = new JTextField("Current operation");

    private final JButton createButton = new JButton("Create");
    private final JButton cancelButton = new JButton("Cancel");

    public CreatePatientTreatsAtHospitalFrame() {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(new VerticalLayout());
        doctors.forEach(el -> doctorsCombobox.addItem(el.getId() + " " + el.getName()));
        wards.forEach(el -> wardsCombobox.addItem(el.getHospitalName() + " " + el.getDepartmentName()+ el.getWardNumber()));
        patients.forEach(el -> patientsCombobox.addItem(el.getName()));
        addActionListeners();
        setSizes();
        addComponents();
    }

    public void callFrame() {
        frame.setVisible(true);
    }
    private void addActionListeners() {
        cancelButton.addActionListener(l -> frame.dispose());
        createButton.addActionListener(l -> {
            var dto = new CreatePatientTreatsAtHospitalDTO();

            dto.setDoctorId(doctors.get(doctorsCombobox.getSelectedIndex()).getId());
            dto.setWardId(wards.get(wardsCombobox.getSelectedIndex()).getId());
            dto.setPatientId(patients.get(patientsCombobox.getSelectedIndex()).getId());
            dto.setAdmissionDate(admDateTextField.getText());
            dto.setDateOfRecovery(recDateTextField.getText());
            dto.setAdmissionTemperature(Double.valueOf(tempTextField.getText()));
            dto.setCurrentDisease(currentDiseaseTextField.getText());
            dto.setCurrentMeds(currentMedsTextField.getText());
            dto.setCurrentOperation(currentOperationTextField.getText());

            PatientAPI.addNewPatientToHospital(dto);
        });
    }

    private void setSizes() {
        doctorsCombobox.setPreferredSize(new Dimension(550, 25));
        wardsCombobox.setPreferredSize(new Dimension(550, 25));
        patientsCombobox.setPreferredSize(new Dimension(550, 25));
        admDateTextField.setPreferredSize(new Dimension(550, 25));
        recDateTextField.setPreferredSize(new Dimension(550, 25));
        tempTextField.setPreferredSize(new Dimension(550, 25));
        currentDiseaseTextField.setPreferredSize(new Dimension(550, 25));
        currentMedsTextField.setPreferredSize(new Dimension(550, 25));
        currentOperationTextField.setPreferredSize(new Dimension(550, 25));

        createButton.setPreferredSize(new Dimension(550, 25));
        cancelButton.setPreferredSize(new Dimension(550, 25));
    }

    private void addComponents() {
        frame.add(doctorsCombobox);
        frame.add(patientsCombobox);
        frame.add(wardsCombobox);
        frame.add(admDateTextField);
        frame.add(recDateTextField);
        frame.add(tempTextField);
        frame.add(currentDiseaseTextField);
        frame.add(currentMedsTextField);
        frame.add(currentOperationTextField);

        frame.add(createButton);
        frame.add(cancelButton);
    }
}
