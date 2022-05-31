package ru.nsu.nikolotov.dbfrontend.app;

import ru.nsu.nikolotov.dbfrontend.api.HospitalWardsAPI;
import ru.nsu.nikolotov.dbfrontend.api.LabsAPI;
import ru.nsu.nikolotov.dbfrontend.app.labssubframe.SpecialQueriesForLabsMenu;
import ru.nsu.nikolotov.dbfrontend.app.wardssubframes.SpecialQueriesForWardsMenu;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalWardFullInfoEntity;
import ru.nsu.nikolotov.dbfrontend.entities.LaboratoryEntity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalWardsFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;


    private final JFrame frame = new JFrame("Wards");

    private final JTextField idTextField = new JTextField();
    private final JTextField wardNumberTextField = new JTextField();
    private final JTextField totalPlacesTextField = new JTextField();
    private final JTextField departmentIdTextField = new JTextField();
    private final JTextField departmentNameTextField = new JTextField();
    private final JTextField hospitalIdTextField = new JTextField();
    private final JTextField hospitalNameTextField = new JTextField();

    private final JButton createEntityButton = new JButton("Create");

    private final JButton updateEntityButton = new JButton("Update");
    private final JButton deleteEntityButton = new JButton("Delete");

    private final JButton gotoEntityButton = new JButton("Go to");
    private final JButton nextEntityButton = new JButton("Next");
    private final JButton prevEntityButton = new JButton("Prev");

    private final JButton selectEntityButton = new JButton("Select...");
    private List<HospitalWardFullInfoEntity> wards = new ArrayList<>();

    private  JComboBox<String> gotoCombobox = new JComboBox<>();
    private int listIndex = 0;

    public HospitalWardsFrame() {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        addActionListeners();
        setSizes();
        addComponents();
    }

    private void addComponents() {
        frame.add(idTextField);
        frame.add(wardNumberTextField);
        frame.add(totalPlacesTextField);
        frame.add(departmentIdTextField);
        frame.add(departmentNameTextField);
        frame.add(hospitalIdTextField);
        frame.add(hospitalNameTextField);
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
        wardNumberTextField.setPreferredSize(new Dimension(500, 25));
        totalPlacesTextField.setPreferredSize(new Dimension(500, 25));
        departmentIdTextField.setPreferredSize(new Dimension(500, 25));
        departmentNameTextField.setPreferredSize(new Dimension(500, 25));
        hospitalIdTextField.setPreferredSize(new Dimension(500, 25));
        hospitalNameTextField.setPreferredSize(new Dimension(500, 25));

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

    private void getAllWards() {
        wards = HospitalWardsAPI.getAll();
    }

    private void nextButtonAction() {
        if (listIndex + 1 < wards.size()) {
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
        getAllWards();
        String[] idAndNames = new String[wards.size()];
        for (int i = 0; i < wards.size(); i++) {
            idAndNames[i] = wards.get(i).getHospitalName() + " " + wards.get(i).getDepartmentName() + " " + wards.get(i).getWardNumber() ;
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
        idTextField.setText(String.valueOf("ID: " + wards.get(listIndex).getId()));
        wardNumberTextField.setText("Name: " + wards.get(listIndex).getWardNumber());
        totalPlacesTextField.setText(String.valueOf("total places: " + wards.get(listIndex).getTotalPlaces()));

        departmentIdTextField.setText(String.valueOf("Dep. ID: " + wards.get(listIndex).getDepartmentId()));
        departmentNameTextField.setText("Dep. name: " + wards.get(listIndex).getDepartmentName());
        hospitalIdTextField.setText(String.valueOf("Hospital ID: " + wards.get(listIndex).getHospitalId()));
        hospitalNameTextField.setText("HospitalName: " + wards.get(listIndex).getHospitalName());

    }

    private void selectButtonAction() {
        SpecialQueriesForWardsMenu menu = new SpecialQueriesForWardsMenu();
        menu.callFrame();
    }
}
