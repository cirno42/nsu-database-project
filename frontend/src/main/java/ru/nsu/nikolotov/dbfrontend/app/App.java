package ru.nsu.nikolotov.dbfrontend.app;

import ru.nsu.nikolotov.dbfrontend.api.PatientAPI;
import ru.nsu.nikolotov.dbfrontend.layout.VerticalLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class App {
    private final JFrame frame = new JFrame("Medicine database");
    private static final int FRAME_WIDTH = 350;
    private static final int FRAME_HEIGHT = 1000;
    private final JButton buttonToHospitalsFrame = new JButton("Hospitals");

    private final JButton buttonToPatientsFrame = new JButton("Patients");

    private final JButton buttonToDoctorsFrame = new JButton("Doctors");

    private final JButton buttonToServiceStaffFrame = new JButton("Service staff");
    private final JButton buttonToPolyclinics = new JButton("Polyclinics");
    private final JButton buttonToPolyclinicCabinets = new JButton("Polyclinic cabinets");

    private final JButton buttonToLabs = new JButton("Labs");

    private final JButton buttonToWards = new JButton("Hospital wards");
    private final JButton buttonToDeps = new JButton("Hospital departments");
    private final JButton buttonToDoctorWorksHospital = new JButton("Doctor works at hospital");

    private final JButton buttonToDoctorWorksPolyclinic = new JButton("Doctor works at polyclinic");
    private final JButton buttonToDoctorWorkedHospital = new JButton("Doctor worked at hospital");
    private final JButton buttonToDoctorWorkedPolyclinic = new JButton("Doctor worked at polyclinic");


    private final JButton buttonToPatientTreatsAtHospital = new JButton("Patient treats at hospital");
    private final JButton buttonToPatientTreatsAtPolyclinic = new JButton("Patient treats at polyclinic");
    private final JButton buttonToPatientTreatedAtHospital = new JButton("Patient treated at hospital");
    private final JButton buttonToPatientTreatedAtPolyclinic = new JButton("Patient treated at polyclinic");


    private final HospitalsFrame hospitalsFrame = new HospitalsFrame();
    private final DoctorsFrame doctorsFrame = new DoctorsFrame();


    public App()  {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);
        frame.setLayout(new VerticalLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSizes();
        addActionListeners();
        addComponents();
    }
    public void start() {
        frame.setVisible(true);
    }

    private void addComponents() {
        try {
            var sanitarPicture = ImageIO.read(new File(getClass().getClassLoader().getResource("sanitar.png").toURI()));
            JLabel picLabel = new JLabel(new ImageIcon(sanitarPicture));
            picLabel.setSize(100, 100);
            frame.add(picLabel);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        frame.add(buttonToHospitalsFrame);
        frame.add(buttonToPatientsFrame);
        frame.add(buttonToDoctorsFrame);
        frame.add(buttonToServiceStaffFrame);
        frame.add(buttonToPolyclinics);
        frame.add(buttonToPolyclinicCabinets);
        frame.add(buttonToLabs);
        frame.add(buttonToWards);
        frame.add(buttonToDeps);
        frame.add(buttonToDoctorWorksHospital);
        frame.add(buttonToDoctorWorksPolyclinic);
        frame.add(buttonToDoctorWorkedHospital);
        frame.add(buttonToDoctorWorkedPolyclinic);
        frame.add(buttonToPatientTreatsAtHospital);
        frame.add(buttonToPatientTreatsAtPolyclinic);
        frame.add(buttonToPatientTreatedAtHospital);
        frame.add(buttonToPatientTreatedAtPolyclinic);
    }

    private void setSizes() {
        Dimension buttonSize = new Dimension(300, 30);
        buttonToHospitalsFrame.setPreferredSize(buttonSize);
        buttonToPatientsFrame.setPreferredSize(buttonSize);
        buttonToDoctorsFrame.setPreferredSize(buttonSize);
        buttonToServiceStaffFrame.setPreferredSize(buttonSize);
        buttonToPolyclinics.setPreferredSize(buttonSize);
        buttonToPolyclinicCabinets.setPreferredSize(buttonSize);
        buttonToLabs.setPreferredSize(buttonSize);
        buttonToWards.setPreferredSize(buttonSize);
        buttonToDeps.setPreferredSize(buttonSize);
        buttonToDoctorWorksHospital.setPreferredSize(buttonSize);
        buttonToDoctorWorksPolyclinic.setPreferredSize(buttonSize);
        buttonToPatientTreatsAtHospital.setPreferredSize(buttonSize);
        buttonToPatientTreatsAtPolyclinic.setPreferredSize(buttonSize);
        buttonToPatientTreatedAtHospital.setPreferredSize(buttonSize);
        buttonToPatientTreatedAtPolyclinic.setPreferredSize(buttonSize);
        buttonToDoctorWorkedHospital.setPreferredSize(buttonSize);
        buttonToDoctorWorkedPolyclinic.setPreferredSize(buttonSize);
    }

    private void addActionListeners() {
        buttonToHospitalsFrame.addActionListener(l -> hospitalsFrame.setFrameVisible(true));
        buttonToDoctorsFrame.addActionListener(l -> doctorsFrame.callFrame(true));
        buttonToServiceStaffFrame.addActionListener(l ->  {
            ServiceStaffFrame serviceStaffFrame = new ServiceStaffFrame();
            serviceStaffFrame.callFrame();
        });
        buttonToPatientsFrame.addActionListener(l -> {
            PatientsFrame patientsFrame = new PatientsFrame();
            patientsFrame.callFrame();
        });
        buttonToPolyclinics.addActionListener(l -> {
            PolyclinicsFrame polyclinicsFrame = new PolyclinicsFrame();
            polyclinicsFrame.callFrame();
        });

        buttonToPolyclinicCabinets.addActionListener(l -> {
            PolyclinicCabinetFrame polyclinicCabinetFrame = new PolyclinicCabinetFrame();
            polyclinicCabinetFrame.callFrame();
        });

        buttonToLabs.addActionListener(l -> {
            LabsFrame labsFrame = new LabsFrame();
            labsFrame.callFrame();
        });

        buttonToWards.addActionListener(l -> {
            HospitalWardsFrame wardsFrame = new HospitalWardsFrame();
            wardsFrame.callFrame();
        });
        buttonToDeps.addActionListener(l -> {
            HospitalDepartmentsFrame departmentsFrame = new HospitalDepartmentsFrame();
            departmentsFrame.callFrame();
        });
        buttonToDoctorWorksHospital.addActionListener(l -> {
            DoctorWorksAtHospitalFrame doctorWorksAtHospitalFrame = new DoctorWorksAtHospitalFrame();
            doctorWorksAtHospitalFrame.callFrame();
        });
        buttonToDoctorWorksPolyclinic.addActionListener(l -> {
            DoctorWorksAtPolyclinicFrame doctorWorksAtPolyclinicFrame = new DoctorWorksAtPolyclinicFrame();
            doctorWorksAtPolyclinicFrame.callFrame();
        });
        buttonToPatientTreatsAtHospital.addActionListener(l -> {
            PatientTreatsInHospitalFrame patientTreatsInHospitalFrame = new PatientTreatsInHospitalFrame();
            patientTreatsInHospitalFrame.callFrame();;
        });
        buttonToPatientTreatsAtPolyclinic.addActionListener(l -> {
            PatientTreatsAtPolyclinicFrame patientTreatsAtPolyclinicFrame = new PatientTreatsAtPolyclinicFrame();
            patientTreatsAtPolyclinicFrame.callFrame();
        });
        buttonToPatientTreatedAtHospital.addActionListener(l -> {
            PatientTreatedInHospitalFrame f = new PatientTreatedInHospitalFrame();
            f.callFrame();
        });
        buttonToPatientTreatedAtPolyclinic.addActionListener(l -> {
            PatientTreatedInPolyclinicFrame f = new PatientTreatedInPolyclinicFrame();
            f.callFrame();
        });
        buttonToDoctorWorkedHospital.addActionListener(l -> {
            DoctorWorkedAtHospitalFrame f = new DoctorWorkedAtHospitalFrame();
            f.callFrame();
        });
        buttonToDoctorWorkedPolyclinic.addActionListener(l -> {
            DoctorWorkedAtPolyclinicFrame f = new DoctorWorkedAtPolyclinicFrame();
            f.callFrame();
        });
    }
}
