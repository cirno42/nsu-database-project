package ru.nsu.nikolotov.dbfrontend.app;

import ru.nsu.nikolotov.dbfrontend.api.LabsAPI;
import ru.nsu.nikolotov.dbfrontend.api.PatientAPI;
import ru.nsu.nikolotov.dbfrontend.app.labssubframe.SpecialQueriesForLabsMenu;
import ru.nsu.nikolotov.dbfrontend.app.patientsubframes.PatientsSpecialQueryMenu;
import ru.nsu.nikolotov.dbfrontend.entities.LaboratoryEntity;
import ru.nsu.nikolotov.dbfrontend.entities.PatientEntity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LabsFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;


    private final JFrame frame = new JFrame("Labs");

    private final JTextField idTextField = new JTextField();
    private final JTextField nameTextField = new JTextField();

    private final JButton createEntityButton = new JButton("Create");

    private final JButton updateEntityButton = new JButton("Update");
    private final JButton deleteEntityButton = new JButton("Delete");

    private final JButton gotoEntityButton = new JButton("Go to");
    private final JButton nextEntityButton = new JButton("Next");
    private final JButton prevEntityButton = new JButton("Prev");

    private final JButton selectEntityButton = new JButton("Select...");
    private List<LaboratoryEntity> labs = new ArrayList<>();

    private  JComboBox<String> gotoCombobox = new JComboBox<>();
    private int listIndex = 0;

    public LabsFrame() {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        addActionListeners();
        setSizes();
        addComponents();
    }

    private void addComponents() {
        frame.add(idTextField);
        frame.add(nameTextField);
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

    private void getAllLabs() {
        labs = LabsAPI.getAll();
    }

    private void nextButtonAction() {
        if (listIndex + 1 < labs.size()) {
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
        getAllLabs();
        String[] idAndNames = new String[labs.size()];
        for (int i = 0; i < labs.size(); i++) {
            idAndNames[i] = labs.get(i).getId().toString() + " " + labs.get(i).getName();
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
        idTextField.setText(String.valueOf(labs.get(listIndex).getId()));
        nameTextField.setText(labs.get(listIndex).getName());

    }

    private void selectButtonAction() {
        SpecialQueriesForLabsMenu menu = new SpecialQueriesForLabsMenu();
        menu.callFrame();
    }
}
