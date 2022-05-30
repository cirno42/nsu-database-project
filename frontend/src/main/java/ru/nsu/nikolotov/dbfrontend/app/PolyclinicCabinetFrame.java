package ru.nsu.nikolotov.dbfrontend.app;

import ru.nsu.nikolotov.dbfrontend.api.PatientAPI;
import ru.nsu.nikolotov.dbfrontend.api.PolyclinicCabinetAPI;
import ru.nsu.nikolotov.dbfrontend.app.patientsubframes.PatientsSpecialQueryMenu;
import ru.nsu.nikolotov.dbfrontend.app.polycliniccabinetssubframes.SpecialQueriesForPolyclinicCabinetsFrame;
import ru.nsu.nikolotov.dbfrontend.entities.PatientEntity;
import ru.nsu.nikolotov.dbfrontend.entities.PolyclinicCabinetEntity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PolyclinicCabinetFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;

    //private final CreateDoctorFrame createFrame = new CreateDoctorFrame();

    private final JFrame frame = new JFrame("Polyclinic Cabinets");

    private final JTextField idTextField = new JTextField();
    private final JTextField cabinetNameTextField = new JTextField();
    private final JTextField polyclinicIdTextField = new JTextField();
    private final JTextField polyclinicNameTextField = new JTextField();


    private final JButton createEntityButton = new JButton("Create");

    private final JButton updateEntityButton = new JButton("Update");
    private final JButton deleteEntityButton = new JButton("Delete");

    private final JButton gotoEntityButton = new JButton("Go to");
    private final JButton nextEntityButton = new JButton("Next");
    private final JButton prevEntityButton = new JButton("Prev");

    private final JButton selectEntityButton = new JButton("Select...");
    private List<PolyclinicCabinetEntity> cabinets = new ArrayList<>();

    private  JComboBox<String> gotoCombobox = new JComboBox<>();
    private int listIndex = 0;

    public PolyclinicCabinetFrame() {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        addActionListeners();
        setSizes();
        addComponents();
    }

    private void addComponents() {
        frame.add(idTextField);
        frame.add(cabinetNameTextField);
        frame.add(polyclinicIdTextField);
        frame.add(polyclinicNameTextField);
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
        cabinetNameTextField.setPreferredSize(new Dimension(500, 25));
        polyclinicIdTextField.setPreferredSize(new Dimension(500, 25));
        polyclinicNameTextField.setPreferredSize(new Dimension(500, 25));
        idTextField.setPreferredSize(new Dimension(500, 25));
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

    private void getAllCabinets() {
        cabinets = PolyclinicCabinetAPI.getAll();
    }

    private void nextButtonAction() {
        if (listIndex + 1 < cabinets.size()) {
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
        getAllCabinets();
        String[] idAndNames = new String[cabinets.size()];
        for (int i = 0; i < cabinets.size(); i++) {
            idAndNames[i] = cabinets.get(i).getPolyclinicName() + " " + cabinets.get(i).getCabinetName();
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

        idTextField.setText(String.valueOf(cabinets.get(listIndex).getCabinetId()));
        cabinetNameTextField.setText(cabinets.get(listIndex).getCabinetName());
        polyclinicIdTextField.setText(cabinets.get(listIndex).getPolyclinicId().toString());
        polyclinicNameTextField.setText(cabinets.get(listIndex).getPolyclinicName());
    }

    private void selectButtonAction() {
        SpecialQueriesForPolyclinicCabinetsFrame menu = new SpecialQueriesForPolyclinicCabinetsFrame();
        menu.callFrame();
    }

}
