package ru.nsu.nikolotov.dbfrontend.app;


import kong.unirest.GenericType;
import kong.unirest.Unirest;
import lombok.SneakyThrows;
import ru.nsu.nikolotov.dbfrontend.app.doctorssubframes.CreateDoctorFrame;
import ru.nsu.nikolotov.dbfrontend.app.doctorssubframes.SpecialQueriesMenu;
import ru.nsu.nikolotov.dbfrontend.dtos.DoctorDTO;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalEntity;
import ru.nsu.nikolotov.dbfrontend.layout.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DoctorsFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;

    //private final CreateDoctorFrame createFrame = new CreateDoctorFrame();

    private final JFrame frame = new JFrame("Doctors");

    private final JTextField idTextField = new JTextField();
    private final JTextField nameTextField = new JTextField();

    private final JTextField vacationStartTextField = new JTextField();
    private final JTextField vacationEndTextField = new JTextField();
    private final JTextField salaryCoefficientTextField = new JTextField();
    private final JButton createEntityButton = new JButton("Create");

    private final JButton updateEntityButton = new JButton("Update");
    private final JButton deleteEntityButton = new JButton("Delete");

    private final JButton gotoEntityButton = new JButton("Go to");
    private final JButton nextEntityButton = new JButton("Next");
    private final JButton prevEntityButton = new JButton("Prev");

    private final JButton selectEntityButton = new JButton("Select...");
    private  List<DoctorDTO> doctors = new ArrayList<>();

    private  JComboBox<String> gotoCombobox = new JComboBox<>();
    private int listIndex = 0;


    public DoctorsFrame() {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        addActionListeners();
        setSizes();
        addComponents();
      //  setTexts();
    }

    public void callFrame(boolean visible) {
        getAllDoctors();
        setTexts();
        String[] doctorsIdAndNames = new String[doctors.size()];
        for (int i = 0; i < doctors.size(); i++) {
            doctorsIdAndNames[i] = doctors.get(i).getId().toString() + " " + doctors.get(i).getName();
            gotoCombobox.addItem(doctorsIdAndNames[i]);
        }
        frame.setVisible(visible);
    }

    private void addComponents() {
        frame.add(idTextField);
        frame.add(nameTextField);
        frame.add(vacationStartTextField);
        frame.add(vacationEndTextField);
        frame.add(salaryCoefficientTextField);
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
        vacationStartTextField.setPreferredSize(new Dimension(500, 25));
        gotoCombobox.setPreferredSize(new Dimension(400, 25));
        vacationEndTextField.setPreferredSize(new Dimension(500, 25));
        salaryCoefficientTextField.setPreferredSize(new Dimension(500, 25));
        updateEntityButton.setPreferredSize(new Dimension(100, 25));
        createEntityButton.setPreferredSize(new Dimension(100, 25));
        deleteEntityButton.setPreferredSize(new Dimension(100, 25));
        gotoEntityButton.setPreferredSize(new Dimension(100, 25));
        nextEntityButton.setPreferredSize(new Dimension(100, 25));
        prevEntityButton.setPreferredSize(new Dimension(100, 25));
        selectEntityButton.setPreferredSize(new Dimension(100, 25));
    }

    private void addActionListeners() {
        //idTextField.setText("Hello!");
        updateEntityButton.addActionListener(l -> System.out.println(idTextField.getText()));
        nextEntityButton.addActionListener(l -> nextButtonAction());
        prevEntityButton.addActionListener(l -> prevButtonAction());
        gotoCombobox.addActionListener(l -> gotoComboboxAction());
        createEntityButton.addActionListener(l -> createButtonAction());
        selectEntityButton.addActionListener(l -> selectButtonAction());
    }

    private void nextButtonAction() {
        if (listIndex + 1 < doctors.size()) {
            listIndex++;
            setTexts();
        }
    }


    private void prevButtonAction() {
        if (listIndex - 1 >= 0) {
            listIndex--;
            setTexts();
        }
    }

    private void getAllDoctors() {
        try {
            doctors = Unirest.get("http://localhost:8080/api/v1/doctors/getall").asObjectAsync(new GenericType<List<DoctorDTO>>(){}).get().getBody();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTexts() {
        idTextField.setText(String.valueOf(doctors.get(listIndex).getId()));
        nameTextField.setText(doctors.get(listIndex).getName());
        if (doctors.get(listIndex).getVacationStart() != null) {
            vacationStartTextField.setText(doctors.get(listIndex).getVacationStart().toString());
        }
        if (doctors.get(listIndex).getVacationEnd() != null) {
            vacationEndTextField.setText(doctors.get(listIndex).getVacationEnd().toString());
        }
        salaryCoefficientTextField.setText(doctors.get(listIndex).getSalaryCoefficient().toString());
    }


    private void gotoComboboxAction() {
        int idx = gotoCombobox.getSelectedIndex();
        listIndex = idx;
        setTexts();
    }

    private void createButtonAction() {
        CreateDoctorFrame createFrame = new CreateDoctorFrame();
        createFrame.callFrame();
    }

    private void selectButtonAction() {
        SpecialQueriesMenu menu = new SpecialQueriesMenu();
        menu.callFrame();
    }
}
