package ru.nsu.nikolotov.dbfrontend.app.doctorssubframes;

import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.dtos.DoctorDTO;
import ru.nsu.nikolotov.dbfrontend.layout.VerticalLayout;
import ru.nsu.nikolotov.dbfrontend.types.DoctorSciencePosition;
import ru.nsu.nikolotov.dbfrontend.types.DoctorScienceRank;
import ru.nsu.nikolotov.dbfrontend.types.DoctorType;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateDoctorFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;

    private final JFrame frame = new JFrame("Create doctor");
    private final JTextField nameTextField = new JTextField("Name");
    private final JTextField vacationStartTextField = new JTextField("Vacation start. Example: 01-Jan-2000");
    private final JTextField vacationEndTextField = new JTextField("Vacation end. Example: 01-Jan-2000");
    private final JTextField salaryCoefficientTextField = new JTextField("Salary coefficient");
    private final JComboBox<String> doctorTypeCombobox = new JComboBox<>(new String[]{
            "NONE",
            DoctorType.DENTIST.toString(),
            DoctorType.SURGEON.toString(),
            DoctorType.NEUROPATHOLOGIST.toString(),
            DoctorType.OPHTHALMOLOGIST.toString(),
            DoctorType.RADIOLOGIST.toString(),
            DoctorType.THERAPIST.toString()});

    private final JComboBox<String> doctorRankCombobox = new JComboBox<>(new String[]{"NONE",
            DoctorScienceRank.DOCTOR_OF_SCIENCE.toString(),
            DoctorScienceRank.CANDIDATE_OF_SCIENCE.toString()});

    private final JComboBox<String> doctorPositionCombobox = new JComboBox<>(new String[]{"NONE",
            DoctorSciencePosition.DOCENT.toString(),DoctorSciencePosition.PROFESSOR.toString()});

    private final JButton createButton = new JButton("Create");
    private final JButton cancelButton = new JButton("Cancel");
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public CreateDoctorFrame() {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(new VerticalLayout());
        addActionListeners();
        setSizes();
        addComponents();
    }

    public void callFrame() {
        frame.setVisible(true);
    }

    private void addActionListeners() {
        cancelButton.addActionListener(l-> frame.dispose());
        createButton.addActionListener(l -> createButtonAction());
    }
    private void setSizes() {
        nameTextField.setToolTipText("test");
        nameTextField.setPreferredSize(new Dimension(500, 25));
        vacationStartTextField.setPreferredSize(new Dimension(500, 25));
        vacationEndTextField.setPreferredSize(new Dimension(500, 25));
        salaryCoefficientTextField.setPreferredSize(new Dimension(500, 25));
        doctorTypeCombobox.setPreferredSize(new Dimension(500, 25));
        doctorRankCombobox.setPreferredSize(new Dimension(500, 25));
        doctorPositionCombobox.setPreferredSize(new Dimension(500, 25));
        createButton.setPreferredSize(new Dimension(100, 25));
        cancelButton.setPreferredSize(new Dimension(100, 25));

    }
    private void addComponents() {
        frame.add(nameTextField);
        frame.add(vacationStartTextField);
        frame.add(vacationEndTextField);
        frame.add(salaryCoefficientTextField);
        frame.add(doctorTypeCombobox);
        frame.add(doctorRankCombobox);
        frame.add(doctorPositionCombobox);
        frame.add(createButton);
        frame.add(cancelButton);
    }

    private void createButtonAction() {
        if (!isValidDoctorConfig()) {
            JOptionPane.showMessageDialog(null, "Wrong doctor configuration, check if rank and position set right");
            return;
        }
        var dto = createDTO();
        var created = Unirest.post("http://localhost:8080/api/v1/doctors")
                .header("Content-Type", "application/json")
                .body(dto).asObject(DoctorDTO.class).getBody();
        System.out.println(created.toString());
    }

    private DoctorDTO createDTO() {
        DoctorDTO dtoToSend = new DoctorDTO();
        dtoToSend.setName(nameTextField.getText());
        Date vacStart = null;

        try {
            vacStart = formatter.parse(vacationStartTextField.getText());
        } catch (ParseException e) {
            System.out.println("Wrong date format: " + vacationStartTextField.getText());
        }
        Date vacEnd = null;
        try {
            vacEnd = formatter.parse(vacationEndTextField.getText());
        } catch (ParseException e) {
            System.out.println("Wrong date format: " + vacationEndTextField.getText());
        }
        dtoToSend.setVacationStart(vacStart);
        dtoToSend.setVacationEnd(vacEnd);
        dtoToSend.setDoctorTypeString(doctorTypeCombobox.getItemAt(doctorTypeCombobox.getSelectedIndex()));
        dtoToSend.setDoctorSciencePositionString(doctorPositionCombobox.getItemAt(doctorPositionCombobox.getSelectedIndex()));
        dtoToSend.setDoctorScienceRankString(doctorRankCombobox.getItemAt(doctorRankCombobox.getSelectedIndex()));
        Double salaryCoeff = Double.valueOf(salaryCoefficientTextField.getText());
        dtoToSend.setSalaryCoefficient(salaryCoeff);
        return dtoToSend;
    }

    private boolean isValidDoctorConfig() {
        var rank = DoctorScienceRank.fromString(doctorRankCombobox.getItemAt(doctorRankCombobox.getSelectedIndex()));
        var position = DoctorSciencePosition.fromString(doctorPositionCombobox.getItemAt(doctorPositionCombobox.getSelectedIndex()));
        if (position == DoctorSciencePosition.PROFESSOR) {
            return rank != DoctorScienceRank.NONE;
        } else if (position == DoctorSciencePosition.DOCENT) {
            return rank == DoctorScienceRank.CANDIDATE_OF_SCIENCE;
        }
        return true;
    }
}
