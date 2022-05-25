package ru.nsu.nikolotov.dbfrontend.app;

import ru.nsu.nikolotov.dbfrontend.api.PatientAPI;
import ru.nsu.nikolotov.dbfrontend.api.ServiceStaffAPI;
import ru.nsu.nikolotov.dbfrontend.app.patientsubframes.PatientsSpecialQueryMenu;
import ru.nsu.nikolotov.dbfrontend.app.servicestaffsubframes.SpecialQueriesForServiceStaffMenu;
import ru.nsu.nikolotov.dbfrontend.entities.PatientEntity;
import ru.nsu.nikolotov.dbfrontend.entities.ServiceStaffEntity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PatientsFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;

    //private final CreateDoctorFrame createFrame = new CreateDoctorFrame();

    private final JFrame frame = new JFrame("Service staff");

    private final JTextField idTextField = new JTextField();
    private final JTextField nameTextField = new JTextField();
    private final JTextField diseaseHistoryTextField = new JTextField();
    private final JTextField operationsHistoryTextField = new JTextField();
    private final JTextField medsHistoryTextField = new JTextField();


    private final JButton createEntityButton = new JButton("Create");

    private final JButton updateEntityButton = new JButton("Update");
    private final JButton deleteEntityButton = new JButton("Delete");

    private final JButton gotoEntityButton = new JButton("Go to");
    private final JButton nextEntityButton = new JButton("Next");
    private final JButton prevEntityButton = new JButton("Prev");

    private final JButton selectEntityButton = new JButton("Select...");
    private List<PatientEntity> patients = new ArrayList<>();

    private  JComboBox<String> gotoCombobox = new JComboBox<>();
    private int listIndex = 0;

    public PatientsFrame() {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        addActionListeners();
        setSizes();
        addComponents();
    }

    private void addComponents() {
        frame.add(idTextField);
        frame.add(nameTextField);
        frame.add(diseaseHistoryTextField);
        frame.add(operationsHistoryTextField);
        frame.add(medsHistoryTextField);
        frame.add(updateEntityButton);
        frame.add(createEntityButton);
        frame.add(deleteEntityButton);
        frame.add(nextEntityButton);
        frame.add(prevEntityButton);
        frame.add(gotoEntityButton);
        frame.add(gotoCombobox);
        frame.add(selectEntityButton);
    }

    private void setSizes() {
        diseaseHistoryTextField.setPreferredSize(new Dimension(500, 25));
        operationsHistoryTextField.setPreferredSize(new Dimension(500, 25));
        medsHistoryTextField.setPreferredSize(new Dimension(500, 25));
        idTextField.setPreferredSize(new Dimension(500, 25));
        nameTextField.setPreferredSize(new Dimension(500, 25));
        gotoCombobox.setPreferredSize(new Dimension(400, 25));
        updateEntityButton.setPreferredSize(new Dimension(100, 25));
        createEntityButton.setPreferredSize(new Dimension(100, 25));
        deleteEntityButton.setPreferredSize(new Dimension(100, 25));
        gotoEntityButton.setPreferredSize(new Dimension(100, 25));
        nextEntityButton.setPreferredSize(new Dimension(100, 25));
        prevEntityButton.setPreferredSize(new Dimension(100, 25));
        selectEntityButton.setPreferredSize(new Dimension(100, 25));
    }

    private void addActionListeners() {
        updateEntityButton.addActionListener(l -> System.out.println(idTextField.getText()));
        nextEntityButton.addActionListener(l -> nextButtonAction());
        gotoCombobox.addActionListener(l -> gotoComboboxAction());
        prevEntityButton.addActionListener(l -> prevButtonAction());
        selectEntityButton.addActionListener(l -> selectButtonAction());
    }

    private void getAllPatients() {
        patients = PatientAPI.getAll();
    }

    private void nextButtonAction() {
        if (listIndex + 1 < patients.size()) {
            listIndex++;
            setTexts();
        }
    }

    private void gotoComboboxAction() {
        int idx = gotoCombobox.getSelectedIndex();
        listIndex = idx;
        setTexts();
    }

    public void callFrame() {
        getAllPatients();
        String[] idAndNames = new String[patients.size()];
        for (int i = 0; i < patients.size(); i++) {
            idAndNames[i] = patients.get(i).getId().toString() + " " + patients.get(i).getName();
            gotoCombobox.addItem(idAndNames[i]);
        }
        setTexts();
        frame.setVisible(true);
    }

    private void prevButtonAction() {
        if (listIndex - 1 >= 0) {
            listIndex--;
            setTexts();
        }
    }

    private void setTexts() {

        var disH = patients.get(listIndex).getDiseaseHistory();
        if (disH == null) {
            disH = "-";
        }
        var opH = patients.get(listIndex).getOperationsHistory();
        if (opH == null) {
            opH = "-";
        }
        var medsH = patients.get(listIndex).getMedsHistory();
        if (medsH == null) {
            medsH = "-";
        }
        idTextField.setText(String.valueOf(patients.get(listIndex).getId()));
        nameTextField.setText(patients.get(listIndex).getName());
        diseaseHistoryTextField.setText(disH);
        operationsHistoryTextField.setText(opH);
        medsHistoryTextField.setText(medsH);
    }

    private void selectButtonAction() {
        PatientsSpecialQueryMenu menu = new PatientsSpecialQueryMenu();
        menu.callFrame();
    }
}
