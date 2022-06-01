package ru.nsu.nikolotov.dbfrontend.app;

import ru.nsu.nikolotov.dbfrontend.api.HospitalDepartmentsAPI;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalDepartmentFullInfo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalDepartmentsFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;


    private final JFrame frame = new JFrame("Departments");

    private final JTextField idTextField = new JTextField();
    private final JTextField nameTextField = new JTextField();
    private final JTextField disGroupIdTextField = new JTextField();
    private final JTextField disGroupNameTextField = new JTextField();
    private final JTextField hospitalIdTextField = new JTextField();
    private final JTextField hospitalNameTextField = new JTextField();

    private final JButton createEntityButton = new JButton("Create");

    private final JButton updateEntityButton = new JButton("Update");
    private final JButton deleteEntityButton = new JButton("Delete");

    private final JButton gotoEntityButton = new JButton("Go to");
    private final JButton nextEntityButton = new JButton("Next");
    private final JButton prevEntityButton = new JButton("Prev");

    private List<HospitalDepartmentFullInfo> deps = new ArrayList<>();

    private  JComboBox<String> gotoCombobox = new JComboBox<>();
    private int listIndex = 0;

    public HospitalDepartmentsFrame() {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        addActionListeners();
        setSizes();
        addComponents();
    }

    private void addComponents() {
        frame.add(idTextField);
        frame.add(nameTextField);
        frame.add(disGroupIdTextField);
        frame.add(disGroupNameTextField);
        frame.add(hospitalIdTextField);
        frame.add(hospitalNameTextField);
        frame.add(updateEntityButton);
        frame.add(createEntityButton);
        frame.add(deleteEntityButton);
        frame.add(nextEntityButton);
        frame.add(prevEntityButton);
        frame.add(gotoEntityButton);
        frame.add(gotoCombobox);
    }

    private void setSizes() {
        idTextField.setPreferredSize(new Dimension(500, 25));
        nameTextField.setPreferredSize(new Dimension(500, 25));
        disGroupIdTextField.setPreferredSize(new Dimension(500, 25));
        disGroupNameTextField.setPreferredSize(new Dimension(500, 25));
        hospitalIdTextField.setPreferredSize(new Dimension(500, 25));
        hospitalNameTextField.setPreferredSize(new Dimension(500, 25));

        gotoCombobox.setPreferredSize(new Dimension(400, 25));
        updateEntityButton.setPreferredSize(new Dimension(100, 25));
        createEntityButton.setPreferredSize(new Dimension(100, 25));
        deleteEntityButton.setPreferredSize(new Dimension(100, 25));
        gotoEntityButton.setPreferredSize(new Dimension(100, 25));
        nextEntityButton.setPreferredSize(new Dimension(100, 25));
        prevEntityButton.setPreferredSize(new Dimension(100, 25));
    }

    private void addActionListeners() {
        updateEntityButton.addActionListener(l -> System.out.println(idTextField.getText()));
        nextEntityButton.addActionListener(l -> nextButtonAction());
        gotoCombobox.addActionListener(l -> gotoComboboxAction());
        prevEntityButton.addActionListener(l -> prevButtonAction());
    }

    private void getAllWards() {
        deps = HospitalDepartmentsAPI.getAll();
    }

    private void nextButtonAction() {
        if (listIndex + 1 < deps.size()) {
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
        String[] idAndNames = new String[deps.size()];
        for (int i = 0; i < deps.size(); i++) {
            idAndNames[i] = deps.get(i).getHospitalName() + " " + deps.get(i).getName();
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
        idTextField.setText(String.valueOf("ID: " + deps.get(listIndex).getId()));
        nameTextField.setText("Name: " + deps.get(listIndex).getName());
        disGroupIdTextField.setText(String.valueOf("Dis. group ID: " + deps.get(listIndex).getDiseaseGroupSpecializationId()));
        disGroupNameTextField.setText(String.valueOf("Dis. group name: " + deps.get(listIndex).getDiseaseGroupSpecializationName()));
        hospitalIdTextField.setText(String.valueOf("Hospital ID: " + deps.get(listIndex).getHospitalId()));
        hospitalNameTextField.setText("HospitalName: " + deps.get(listIndex).getHospitalName());

    }

}
