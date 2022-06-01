package ru.nsu.nikolotov.dbfrontend.app;

import ru.nsu.nikolotov.dbfrontend.api.DoctorWorksAtHospitalAPI;
import ru.nsu.nikolotov.dbfrontend.api.PatientAPI;
import ru.nsu.nikolotov.dbfrontend.app.doctorworksathospitalsubframes.CreateDoctorWorksAtHospitalFrame;
import ru.nsu.nikolotov.dbfrontend.app.patienttreatsathospitalsubframes.CreatePatientTreatsAtHospitalFrame;
import ru.nsu.nikolotov.dbfrontend.dtos.CreatePatientTreatsAtHospitalDTO;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorWorksAtHospitalFullInfoEntity;
import ru.nsu.nikolotov.dbfrontend.entities.PatientTreatsInHospitalFullInfoEntity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PatientTreatsInHospitalFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;


    private final JFrame frame = new JFrame("Doctor works at hospital");

    private final JTextField doctorNameTextField = new JTextField();
    private final JTextField hospitalNameTextField = new JTextField();

    private final JTextField depNameNameTextField = new JTextField();
    private final JTextField wardNameTextField = new JTextField();
    private final JTextField patientNameTextField = new JTextField();
    private final JTextField admissionDateTextField = new JTextField();
    private final JTextField currentMedsTextField = new JTextField();
    private final JTextField dateOfRecoveryTextField = new JTextField();
    private final JTextField admissionTemperatureTextField = new JTextField();
    private final JTextField currentDiseaseTextField = new JTextField();
    private final JTextField currentOperationTextField = new JTextField();


    private final JButton createEntityButton = new JButton("Create");

    private final JButton updateEntityButton = new JButton("Update");
    private final JButton deleteEntityButton = new JButton("Delete");

    private final JButton gotoEntityButton = new JButton("Refresh");
    private final JButton nextEntityButton = new JButton("Next");
    private final JButton prevEntityButton = new JButton("Prev");

    private List<PatientTreatsInHospitalFullInfoEntity> entities = new ArrayList<>();
    private boolean isRefresh = false;


    private  JComboBox<String> gotoCombobox = new JComboBox<>();
    private int listIndex = 0;

    public PatientTreatsInHospitalFrame() {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        addActionListeners();
        setSizes();
        addComponents();
    }

    private void addComponents() {

        frame.add(doctorNameTextField);
        frame.add(patientNameTextField);
        frame.add(hospitalNameTextField);
        frame.add(depNameNameTextField);
        frame.add(wardNameTextField);
        frame.add(admissionDateTextField);
        frame.add(dateOfRecoveryTextField);
        frame.add(admissionTemperatureTextField);
        frame.add(currentDiseaseTextField);
        frame.add(currentMedsTextField);
        frame.add(currentOperationTextField);



        frame.add(updateEntityButton);
        frame.add(createEntityButton);
        frame.add(deleteEntityButton);
        frame.add(nextEntityButton);
        frame.add(prevEntityButton);
        frame.add(gotoEntityButton);
        frame.add(gotoCombobox);
    }

    private void setSizes() {
        patientNameTextField.setPreferredSize(new Dimension(500, 25));
        doctorNameTextField.setPreferredSize(new Dimension(500, 25));
        depNameNameTextField.setPreferredSize(new Dimension(500, 25));
        wardNameTextField.setPreferredSize(new Dimension(500, 25));

        hospitalNameTextField.setPreferredSize(new Dimension(500, 25));
        admissionDateTextField.setPreferredSize(new Dimension(500, 25));
        dateOfRecoveryTextField.setPreferredSize(new Dimension(500, 25));
        admissionTemperatureTextField.setPreferredSize(new Dimension(500, 25));
        currentDiseaseTextField.setPreferredSize(new Dimension(500, 25));
        currentMedsTextField.setPreferredSize(new Dimension(500, 25));
        currentOperationTextField.setPreferredSize(new Dimension(500, 25));


        gotoCombobox.setPreferredSize(new Dimension(400, 25));
        updateEntityButton.setPreferredSize(new Dimension(100, 25));
        createEntityButton.setPreferredSize(new Dimension(100, 25));
        deleteEntityButton.setPreferredSize(new Dimension(100, 25));
        gotoEntityButton.setPreferredSize(new Dimension(100, 25));
        nextEntityButton.setPreferredSize(new Dimension(100, 25));
        prevEntityButton.setPreferredSize(new Dimension(100, 25));
    }

    private void addActionListeners() {
//        updateEntityButton.addActionListener(l -> System.out.println(idTextField.getText()));
        createEntityButton.addActionListener(l -> {
            CreatePatientTreatsAtHospitalFrame f = new CreatePatientTreatsAtHospitalFrame();
            f.callFrame();
        });
        gotoEntityButton.addActionListener(l -> {
            getAllWards();
            listIndex = 0;
            fillGotoCombobox();
            setTexts();
        });
        deleteEntityButton.addActionListener(l -> {
            PatientAPI.finishPatientTreatmentInHospital(entities.get(listIndex).getPatientId());
            entities.remove(listIndex);
            listIndex = 0;
            setTexts();
        });
        nextEntityButton.addActionListener(l -> nextButtonAction());
        gotoCombobox.addActionListener(l -> gotoComboboxAction());
        prevEntityButton.addActionListener(l -> prevButtonAction());
    }

    private void getAllWards() {
        entities = PatientAPI.getPatientsWhoTreatsInHospital();
    }

    private void nextButtonAction() {
        if (listIndex + 1 < entities.size()) {
            listIndex++;
            setTexts();
        }
    }

    private void gotoComboboxAction() {
        if (!isRefresh) {
            int idx = gotoCombobox.getSelectedIndex();
            listIndex = idx;
            setTexts();
        }
    }

    public void callFrame() {
        getAllWards();
        fillGotoCombobox();
        setTexts();
        frame.setVisible(true);
    }

    private void fillGotoCombobox() {
        isRefresh = true;
        listIndex = 0;
        gotoCombobox.removeAllItems();
        String[] idAndNames = new String[entities.size()];
        for (int i = 0; i < entities.size(); i++) {
            idAndNames[i] = entities.get(i).getDoctorName() + " " + entities.get(i).getHospitalName();
            gotoCombobox.addItem(idAndNames[i]);
        }
        isRefresh = false;
    }

    private void prevButtonAction() {
        if (listIndex - 1 >= 0) {
            listIndex--;
            setTexts();
        }
    }

    private void setTexts() {
        String recDate = "NONE";
        if (entities.get(listIndex).getDateOfRecovery() != null) {
            recDate = entities.get(listIndex).getDateOfRecovery().toString();
        }
        patientNameTextField.setText(String.valueOf("Patient Name: " + entities.get(listIndex).getPatientName()));
        doctorNameTextField.setText("Doctor Name: " + entities.get(listIndex).getDoctorName());
        depNameNameTextField.setText(String.valueOf("Department name ID: " + entities.get(listIndex).getDepartmentName()));
        hospitalNameTextField.setText("HospitalName: " + entities.get(listIndex).getHospitalName());
        wardNameTextField.setText(String.valueOf("Ward num.: " + entities.get(listIndex).getWardNumber()));
        admissionDateTextField.setText(String.valueOf("Admission date: " + entities.get(listIndex).getAdmissionDate()));
        dateOfRecoveryTextField.setText(String.valueOf("Date of recovery: " + recDate));
        admissionTemperatureTextField.setText(String.valueOf("Adm. temp: " + entities.get(listIndex).getAdmissionTemperature()));

        currentDiseaseTextField.setText(String.valueOf("Current disease: " + entities.get(listIndex).getCurrentDisease()));
        currentOperationTextField.setText(String.valueOf("Current operation: " + entities.get(listIndex).getCurrentOperation()));
        currentMedsTextField.setText(String.valueOf("Current meds: " + entities.get(listIndex).getCurrentMeds()));
    }
}
