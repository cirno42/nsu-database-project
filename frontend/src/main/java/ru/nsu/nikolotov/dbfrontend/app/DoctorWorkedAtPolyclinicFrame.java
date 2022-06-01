package ru.nsu.nikolotov.dbfrontend.app;

import ru.nsu.nikolotov.dbfrontend.api.DoctorWorksAtPolyclinicAPI;
import ru.nsu.nikolotov.dbfrontend.app.doctorworksatpolyclinicsubframes.CreateDoctorWorksAtPolyclinicFrame;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorWorksAtPolyclinicFullInfo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorWorkedAtPolyclinicFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;


    private final JFrame frame = new JFrame("Doctor worked at polyclinic");

    private final JTextField doctorIdTextField = new JTextField();
    private final JTextField doctorNameTextField = new JTextField();
    private final JTextField hospitalIdTextField = new JTextField();
    private final JTextField hospitalNameTextField = new JTextField();
    private final JTextField salaryTextField = new JTextField();
    private final JTextField contractStartTextField = new JTextField();
    private final JTextField contractEndTextField = new JTextField();
    private final JTextField vacationStartTextField = new JTextField();
    private final JTextField vacationEndTextField = new JTextField();
    private final JTextField salaryCoeffTextField = new JTextField();


    private final JButton createEntityButton = new JButton("Create");

    private final JButton updateEntityButton = new JButton("Update");
    private final JButton deleteEntityButton = new JButton("Delete");

    private final JButton gotoEntityButton = new JButton("Refresh");
    private final JButton nextEntityButton = new JButton("Next");
    private final JButton prevEntityButton = new JButton("Prev");
    private boolean isRefresh = false;


    private List<DoctorWorksAtPolyclinicFullInfo> entities = new ArrayList<>();

    private  JComboBox<String> gotoCombobox = new JComboBox<>();
    private int listIndex = 0;

    public DoctorWorkedAtPolyclinicFrame() {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        addActionListeners();
        setSizes();
        addComponents();
    }

    private void addComponents() {
        frame.add(doctorIdTextField);
        frame.add(doctorNameTextField);
        frame.add(hospitalIdTextField);
        frame.add(hospitalNameTextField);
        frame.add(salaryTextField);
        frame.add(contractStartTextField);
        frame.add(contractEndTextField);
        frame.add(vacationStartTextField);
        frame.add(vacationEndTextField);
        frame.add(salaryCoeffTextField);


        frame.add(updateEntityButton);
        frame.add(createEntityButton);
        frame.add(deleteEntityButton);
        frame.add(nextEntityButton);
        frame.add(prevEntityButton);
        frame.add(gotoEntityButton);
        frame.add(gotoCombobox);
    }

    private void setSizes() {
        doctorIdTextField.setPreferredSize(new Dimension(500, 25));
        doctorNameTextField.setPreferredSize(new Dimension(500, 25));
        hospitalIdTextField.setPreferredSize(new Dimension(500, 25));
        hospitalNameTextField.setPreferredSize(new Dimension(500, 25));
        salaryTextField.setPreferredSize(new Dimension(500, 25));
        vacationStartTextField.setPreferredSize(new Dimension(500, 25));
        vacationEndTextField.setPreferredSize(new Dimension(500, 25));
        contractStartTextField.setPreferredSize(new Dimension(500, 25));
        contractEndTextField.setPreferredSize(new Dimension(500, 25));
        salaryCoeffTextField.setPreferredSize(new Dimension(500, 25));


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
            CreateDoctorWorksAtPolyclinicFrame f = new CreateDoctorWorksAtPolyclinicFrame();
            f.callFrame();
        });
        gotoEntityButton.addActionListener(l -> {
            getAllWards();
            listIndex = 0;
            fillGotoCombobox();
            setTexts();
        });

        deleteEntityButton.addActionListener(l -> {
            DoctorWorksAtPolyclinicAPI.fireDoctor(entities.get(listIndex).getDoctorId());
            entities.remove(listIndex);
            listIndex = 0;
            setTexts();
        });
        nextEntityButton.addActionListener(l -> nextButtonAction());
        gotoCombobox.addActionListener(l -> gotoComboboxAction());
        prevEntityButton.addActionListener(l -> prevButtonAction());
    }

    private void getAllWards() {
        entities = DoctorWorksAtPolyclinicAPI.getHistory();
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
        listIndex=0;
        gotoCombobox.removeAllItems();
        String[] idAndNames = new String[entities.size()];
        for (int i = 0; i < entities.size(); i++) {
            idAndNames[i] = entities.get(i).getDoctorName() + " " + entities.get(i).getPolyclinicName();
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
        doctorIdTextField.setText(String.valueOf("Doctor ID: " + entities.get(listIndex).getDoctorId()));
        doctorNameTextField.setText("Doctor Name: " + entities.get(listIndex).getDoctorName());
        hospitalIdTextField.setText(String.valueOf("Polyclinic ID: " + entities.get(listIndex).getPolyclinicId()));
        hospitalNameTextField.setText("Polyclinic Name: " + entities.get(listIndex).getPolyclinicName());
        salaryTextField.setText(String.valueOf("Salary: " + entities.get(listIndex).getSalary()));
        contractStartTextField.setText(String.valueOf("Contract start date: " + entities.get(listIndex).getContractStartDate()));
        contractEndTextField.setText(String.valueOf("Contract end date: " + entities.get(listIndex).getContractEndDate()));
        vacationStartTextField.setText(String.valueOf("Vacation start date: " + entities.get(listIndex).getVacationStart()));
        vacationEndTextField.setText(String.valueOf("Vacation start date: " + entities.get(listIndex).getVacationEnd()));
        salaryCoeffTextField.setText(String.valueOf("Salary coeff.: " + entities.get(listIndex).getSalaryCoefficient()));
    }
}
