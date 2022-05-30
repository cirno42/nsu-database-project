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
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 800;
    private final JButton buttonToHospitalsFrame = new JButton("Hospitals");

    private final JButton buttonToPatientsFrame = new JButton("Patients");

    private final JButton buttonToDoctorsFrame = new JButton("Doctors");

    private final JButton buttonToServiceStaffFrame = new JButton("Service staff");
    private final JButton buttonToPolyclinics = new JButton("Polyclinics");
    private final JButton buttonToPolyclinicCabinets = new JButton("Polyclinic cabinets");

    private final JButton buttonToLabs = new JButton("Labs");

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
    }

    private void setSizes() {
        buttonToHospitalsFrame.setPreferredSize(new Dimension(300, 50));
        buttonToPatientsFrame.setPreferredSize(new Dimension(300, 50));
        buttonToDoctorsFrame.setPreferredSize(new Dimension(300, 50));
        buttonToServiceStaffFrame.setPreferredSize(new Dimension(300, 50));
        buttonToPolyclinics.setPreferredSize(new Dimension(300, 50));
        buttonToPolyclinicCabinets.setPreferredSize(new Dimension(300, 50));
        buttonToLabs.setPreferredSize(new Dimension(300, 50));
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
    }
}
