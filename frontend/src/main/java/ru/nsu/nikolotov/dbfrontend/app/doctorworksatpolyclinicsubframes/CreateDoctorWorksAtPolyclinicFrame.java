package ru.nsu.nikolotov.dbfrontend.app.doctorworksatpolyclinicsubframes;

import ru.nsu.nikolotov.dbfrontend.api.DoctorWorksAtPolyclinicAPI;
import ru.nsu.nikolotov.dbfrontend.api.DoctorsAPI;
import ru.nsu.nikolotov.dbfrontend.api.PolyclinicAPI;
import ru.nsu.nikolotov.dbfrontend.app.utilframes.TableFrame;
import ru.nsu.nikolotov.dbfrontend.dtos.CreateDoctorWorksAtPolyclinicDTO;
import ru.nsu.nikolotov.dbfrontend.dtos.DoctorDTO;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorWorksAtPolyclinicFullInfo;
import ru.nsu.nikolotov.dbfrontend.entities.PolyclinicEntity;
import ru.nsu.nikolotov.dbfrontend.layout.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreateDoctorWorksAtPolyclinicFrame {
    private final JFrame frame = new JFrame("Create");
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;

    private final List<DoctorDTO> doctors = DoctorsAPI.getAllDTO();
    private final List<PolyclinicEntity> polyclinics = PolyclinicAPI.getAll();
    private final JComboBox<String> doctorsCombobox = new JComboBox<>();
    private final JComboBox<String> hospitalsCombobox = new JComboBox<>();
    private final JTextField salaryTextField = new JTextField("Salary");
    private final JTextField contractStartDateTextField = new JTextField("Contract start date in dd-MMM-yyyy");
    private final JTextField contractEndDateTextField = new JTextField("Contract end date in dd-MMM-yyyy");
    private final JButton createButton = new JButton("Create");
    private final JButton cancelButton = new JButton("Cancel");

    public CreateDoctorWorksAtPolyclinicFrame() {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(new VerticalLayout());
        doctors.forEach(el -> doctorsCombobox.addItem(el.getId() + " " + el.getName()));
        polyclinics.forEach(el -> hospitalsCombobox.addItem(el.getId() + " " + el.getName()));
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
            var dto = new CreateDoctorWorksAtPolyclinicDTO();
            dto.setDoctorId(doctors.get(doctorsCombobox.getSelectedIndex()).getId());
            dto.setPolyclinicId(polyclinics.get(hospitalsCombobox.getSelectedIndex()).getId());
            dto.setSalary(Integer.parseInt(salaryTextField.getText()));
            dto.setContractStartDate(contractStartDateTextField.getText());
            dto.setContractEndDate(contractEndDateTextField.getText());

            var response = DoctorWorksAtPolyclinicAPI.create(dto);
            List<DoctorWorksAtPolyclinicFullInfo> list = new ArrayList<>();
            list.add(response);
            TableFrame.showTable(list);
        });
    }

    private void setSizes() {
        doctorsCombobox.setPreferredSize(new Dimension(550, 25));
        hospitalsCombobox.setPreferredSize(new Dimension(550, 25));
        salaryTextField.setPreferredSize(new Dimension(550, 25));
        contractStartDateTextField.setPreferredSize(new Dimension(550, 25));
        contractEndDateTextField.setPreferredSize(new Dimension(550, 25));
        createButton.setPreferredSize(new Dimension(550, 25));
        cancelButton.setPreferredSize(new Dimension(550, 25));
    }

    private void addComponents() {
        frame.add(doctorsCombobox);
        frame.add(hospitalsCombobox);
        frame.add(salaryTextField);
        frame.add(contractStartDateTextField);
        frame.add(contractEndDateTextField);
        frame.add(createButton);
        frame.add(cancelButton);
    }
}
