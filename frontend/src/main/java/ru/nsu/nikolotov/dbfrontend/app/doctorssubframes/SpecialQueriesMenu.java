package ru.nsu.nikolotov.dbfrontend.app.doctorssubframes;

import ru.nsu.nikolotov.dbfrontend.api.DoctorsAPI;
import ru.nsu.nikolotov.dbfrontend.api.HospitalAPI;
import ru.nsu.nikolotov.dbfrontend.api.PolyclinicAPI;
import ru.nsu.nikolotov.dbfrontend.app.utilframes.TableFrame;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorStatisticEntity;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorTypeStatisticEntity;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorWorksAtInstitutionEntity;
import ru.nsu.nikolotov.dbfrontend.entities.EntityForInsertIntoJTable;
import ru.nsu.nikolotov.dbfrontend.layout.VerticalLayout;
import ru.nsu.nikolotov.dbfrontend.types.DoctorSciencePosition;
import ru.nsu.nikolotov.dbfrontend.types.DoctorScienceRank;
import ru.nsu.nikolotov.dbfrontend.types.DoctorType;
import ru.nsu.nikolotov.dbfrontend.types.MedicineInstitutionType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SpecialQueriesMenu {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private final JFrame frame = new JFrame("Menu");
    private final JButton doctorsWorksButton = new JButton("... doctors, who works at such hospital/polyclinic");
    private final JButton doctorsWithSuchCountOfOperationsButton = new JButton("... doctors, who made more operations than");
    private final JButton doctorsWithSuchExperienceButton = new JButton("... doctors with experience more than");
    private final JButton doctorsWithSuchRankAndPosition = new JButton("... doctors with such rank/position");
    private final JButton backButton = new JButton("Back");

    public SpecialQueriesMenu() {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(new VerticalLayout());
        setSizes();
        addActionListeners();
        addComponents();
    }

    public void callFrame() {
        frame.setVisible(true);
    }

    private void addComponents() {
        frame.add(doctorsWorksButton);
        frame.add(doctorsWithSuchCountOfOperationsButton);
        frame.add(doctorsWithSuchExperienceButton);
        frame.add(doctorsWithSuchRankAndPosition);
        frame.add(backButton);
    }

    private void setSizes() {
        doctorsWorksButton.setPreferredSize(new Dimension(390, 50));
        doctorsWithSuchCountOfOperationsButton.setPreferredSize(new Dimension(390, 50));
        doctorsWithSuchExperienceButton.setPreferredSize(new Dimension(390, 50));
        doctorsWithSuchRankAndPosition.setPreferredSize(new Dimension(390, 50));
        backButton.setPreferredSize(new Dimension(390, 50));
    }

    private void addActionListeners() {
        backButton.addActionListener(l -> frame.dispose());
        doctorsWorksButton.addActionListener(l -> doctorWorksButtonAction());
        doctorsWithSuchCountOfOperationsButton.addActionListener(l -> doctorsWithSuchCountOfOperationsButtonAction());
        doctorsWithSuchExperienceButton.addActionListener(l -> doctorsWithSuchExperienceButtonAction());
        doctorsWithSuchRankAndPosition.addActionListener(l -> doctorsWithSuchRankAndPositionAction());
    }

    private void doctorWorksButtonAction() {
        JFrame queryFrame = new JFrame("Set parameters");
        JButton getButton = new JButton("Get");
        JButton backButton = new JButton("Back");
        JCheckBox checkBox = new JCheckBox("Count");
        queryFrame.setSize(500, 500);
        queryFrame.setLayout(new VerticalLayout());
        List<Integer> instIndexes = new ArrayList<>();
        JComboBox<String> medicineInstitutions = new JComboBox<>();
        JComboBox<String> medicineInstitutionTypeCombobox = new JComboBox<>(new String[] {
                MedicineInstitutionType.HOSPITAL.toString(),
                MedicineInstitutionType.POLYCLINIC.toString(),
                MedicineInstitutionType.ALL.toString()});
        JComboBox<String> doctorTypeCombobox = new JComboBox<>(new String [] {"NONE",
                DoctorType.THERAPIST.toString(), DoctorType.RADIOLOGIST.toString(),
                DoctorType.OPHTHALMOLOGIST.toString(), DoctorType.NEUROPATHOLOGIST.toString(),
                DoctorType.SURGEON.toString(), DoctorType.DENTIST.toString()});
        medicineInstitutionTypeCombobox.addActionListener(l -> {
            medicineInstitutions.removeAllItems();
            var type = MedicineInstitutionType.fromString(medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()));
            switch (type) {
                case HOSPITAL -> {
                    var hospitals = HospitalAPI.getAll();
                    hospitals.forEach(el -> {
                        medicineInstitutions.addItem(el.toString());
                        instIndexes.add(el.getId());
                    });
                }
                case POLYCLINIC -> {
                    var polyclinics = PolyclinicAPI.getAll();
                    polyclinics.forEach(el -> {
                        medicineInstitutions.addItem(el.toString());
                        instIndexes.add(el.getId());
                    });
                }

            }
        });

        backButton.addActionListener(l -> queryFrame.dispose());

        getButton.addActionListener(l -> {

            Integer id = 0;
            if (MedicineInstitutionType.fromString(medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()))
                    != MedicineInstitutionType.ALL) {
                id = instIndexes.get(medicineInstitutions.getSelectedIndex());
            }

            Integer finalId = id;
            if (!checkBox.isSelected()) {
                var doctors = DoctorsAPI.getDoctorWorksAtInstitution(
                        doctorTypeCombobox.getItemAt(doctorTypeCombobox.getSelectedIndex()),
                        medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()),
                        finalId);
                showFrameWIthDoctorWorks(doctors);
            } else {
                var doctorsCount = DoctorsAPI.getDoctorWorksAtInstitutionCount(doctorTypeCombobox.getItemAt(doctorTypeCombobox.getSelectedIndex()),
                        medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()),
                        finalId);
                List<DoctorTypeStatisticEntity> list = new ArrayList<>(1);
                list.add(doctorsCount);
                showFrameWIthDoctorWorks(list);
            }

        });

        medicineInstitutions.setPreferredSize(new Dimension(300, 50));
        medicineInstitutionTypeCombobox.setPreferredSize(new Dimension(300, 50));
        doctorTypeCombobox.setPreferredSize(new Dimension(300, 50));
        getButton.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));
        checkBox.setPreferredSize(new Dimension(100, 50));
        queryFrame.add(doctorTypeCombobox);
        queryFrame.add(medicineInstitutionTypeCombobox);
        queryFrame.add(medicineInstitutions);
        queryFrame.add(checkBox);
        queryFrame.add(getButton);
        queryFrame.add(backButton);
        queryFrame.setVisible(true);
    }


    private void showFrameWIthDoctorWorks(List<? extends EntityForInsertIntoJTable> data) {
        TableFrame tf = new TableFrame(data);
        tf.callFrame();
    }

    private void doctorsWithSuchCountOfOperationsButtonAction() {
        JFrame queryFrame = new JFrame("Set parameters");
        JButton getButton = new JButton("Get");
        JButton backButton = new JButton("Back");
        JCheckBox checkBox = new JCheckBox("Count");

        JTextField operationsCountTextField = new JTextField("operations count");

        queryFrame.setSize(500, 500);
        queryFrame.setLayout(new VerticalLayout());
        List<Integer> instIndexes = new ArrayList<>();
        JComboBox<String> medicineInstitutions = new JComboBox<>();
        JComboBox<String> medicineInstitutionTypeCombobox = new JComboBox<>(new String[] {
                MedicineInstitutionType.HOSPITAL.toString(),
                MedicineInstitutionType.POLYCLINIC.toString(),
                MedicineInstitutionType.ALL.toString()});
        JComboBox<String> doctorTypeCombobox = new JComboBox<>(new String [] {DoctorType.SURGEON.toString(), DoctorType.DENTIST.toString()});
        medicineInstitutionTypeCombobox.addActionListener(l -> {
            medicineInstitutions.removeAllItems();
            var type = MedicineInstitutionType.fromString(medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()));
            switch (type) {
                case HOSPITAL -> {
                    var hospitals = HospitalAPI.getAll();
                    hospitals.forEach(el -> {
                        medicineInstitutions.addItem(el.toString());
                        instIndexes.add(el.getId());
                    });
                }
                case POLYCLINIC -> {
                    var polyclinics = PolyclinicAPI.getAll();
                    polyclinics.forEach(el -> {
                        medicineInstitutions.addItem(el.toString());
                        instIndexes.add(el.getId());
                    });
                }

            }
        });

        backButton.addActionListener(l -> queryFrame.dispose());

        getButton.addActionListener(l -> {

            int operationsCount = Integer.parseInt(operationsCountTextField.getText());
            if (operationsCount == 0) {
                JOptionPane.showMessageDialog(null, "Wrong operations count!");
            }
            Integer id = 0;
            if (MedicineInstitutionType.fromString(medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()))
                    != MedicineInstitutionType.ALL) {
                id = instIndexes.get(medicineInstitutions.getSelectedIndex());
            }

            Integer finalId = id;
            if (!checkBox.isSelected()) {
                var doctors = DoctorsAPI.getDoctorsWhoMadeSuchCountOfOperations(
                        doctorTypeCombobox.getItemAt(doctorTypeCombobox.getSelectedIndex()),
                        medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()),
                        finalId, operationsCount);
                showFrameWIthDoctorWorks(doctors);
            } else {
                var doctorsCount = DoctorsAPI.getCountOfDoctorsWhoMadeSuchCountOfOperations(doctorTypeCombobox.getItemAt(doctorTypeCombobox.getSelectedIndex()),
                        medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()),
                        finalId, operationsCount);
                List<DoctorTypeStatisticEntity> list = new ArrayList<>(1);
                list.add(doctorsCount);
                showFrameWIthDoctorWorks(list);
            }

        });

        medicineInstitutions.setPreferredSize(new Dimension(300, 50));
        medicineInstitutionTypeCombobox.setPreferredSize(new Dimension(300, 50));
        doctorTypeCombobox.setPreferredSize(new Dimension(300, 50));
        getButton.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));
        checkBox.setPreferredSize(new Dimension(100, 50));
        operationsCountTextField.setPreferredSize(new Dimension(200, 25));
        queryFrame.add(doctorTypeCombobox);
        queryFrame.add(medicineInstitutionTypeCombobox);
        queryFrame.add(medicineInstitutions);
        queryFrame.add(operationsCountTextField);
        queryFrame.add(checkBox);
        queryFrame.add(getButton);
        queryFrame.add(backButton);

        queryFrame.setVisible(true);
    }


    private void doctorsWithSuchExperienceButtonAction() {
        JFrame queryFrame = new JFrame("Set parameters");
        JButton getButton = new JButton("Get");
        JButton backButton = new JButton("Back");
        JCheckBox checkBox = new JCheckBox("Count");

        JTextField operationsCountTextField = new JTextField("Experience (in days)");

        queryFrame.setSize(500, 500);
        queryFrame.setLayout(new VerticalLayout());
        List<Integer> instIndexes = new ArrayList<>();
        JComboBox<String> medicineInstitutions = new JComboBox<>();
        JComboBox<String> medicineInstitutionTypeCombobox = new JComboBox<>(new String[] {
                MedicineInstitutionType.ALL.toString(),
                MedicineInstitutionType.HOSPITAL.toString(),
                MedicineInstitutionType.POLYCLINIC.toString()});
        JComboBox<String> doctorTypeCombobox = new JComboBox<>(new String [] {DoctorType.THERAPIST.toString(), DoctorType.RADIOLOGIST.toString(),
                DoctorType.OPHTHALMOLOGIST.toString(), DoctorType.NEUROPATHOLOGIST.toString(),
                DoctorType.SURGEON.toString(), DoctorType.DENTIST.toString()});
        medicineInstitutionTypeCombobox.addActionListener(l -> {
            medicineInstitutions.removeAllItems();
            var type = MedicineInstitutionType.fromString(medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()));
            switch (type) {
                case HOSPITAL -> {
                    var hospitals = HospitalAPI.getAll();
                    hospitals.forEach(el -> {
                        medicineInstitutions.addItem(el.toString());
                        instIndexes.add(el.getId());
                    });
                }
                case POLYCLINIC -> {
                    var polyclinics = PolyclinicAPI.getAll();
                    polyclinics.forEach(el -> {
                        medicineInstitutions.addItem(el.toString());
                        instIndexes.add(el.getId());
                    });
                }

            }
        });

        backButton.addActionListener(l -> queryFrame.dispose());

        getButton.addActionListener(l -> {

            int experience = Integer.parseInt(operationsCountTextField.getText());
            if (experience == 0) {
                JOptionPane.showMessageDialog(null, "Wrong operations count!");
            }
            Integer id = 0;
            if (MedicineInstitutionType.fromString(medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()))
                    != MedicineInstitutionType.ALL) {
                id = instIndexes.get(medicineInstitutions.getSelectedIndex());
            }

            Integer finalId = id;
            if (!checkBox.isSelected()) {
                var doctors = DoctorsAPI.getDoctorsWithSuchExperience(
                        doctorTypeCombobox.getItemAt(doctorTypeCombobox.getSelectedIndex()),
                        medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()),
                        finalId, experience);
                showFrameWIthDoctorWorks(doctors);
            } else {
                var doctorsCount = DoctorsAPI.getCountDoctorsWithSuchExperience(doctorTypeCombobox.getItemAt(doctorTypeCombobox.getSelectedIndex()),
                        medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()),
                        finalId, experience);
                List<DoctorTypeStatisticEntity> list = new ArrayList<>(1);
                list.add(doctorsCount);
                showFrameWIthDoctorWorks(list);
            }

        });

        medicineInstitutions.setPreferredSize(new Dimension(300, 50));
        medicineInstitutionTypeCombobox.setPreferredSize(new Dimension(300, 50));
        doctorTypeCombobox.setPreferredSize(new Dimension(300, 50));
        getButton.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));
        checkBox.setPreferredSize(new Dimension(100, 50));
        operationsCountTextField.setPreferredSize(new Dimension(200, 25));
        queryFrame.add(doctorTypeCombobox);
        queryFrame.add(medicineInstitutionTypeCombobox);
        queryFrame.add(medicineInstitutions);
        queryFrame.add(operationsCountTextField);
        queryFrame.add(checkBox);
        queryFrame.add(getButton);
        queryFrame.add(backButton);

        queryFrame.setVisible(true);

    }

    private void doctorsWithSuchRankAndPositionAction() {
        JFrame queryFrame = new JFrame("Set parameters");
        JButton getButton = new JButton("Get");
        JButton backButton = new JButton("Back");
        JCheckBox checkBox = new JCheckBox("Count");


        queryFrame.setSize(500, 500);
        queryFrame.setLayout(new VerticalLayout());
        List<Integer> instIndexes = new ArrayList<>();
        JComboBox<String> medicineInstitutions = new JComboBox<>();

        JComboBox<String> medicineInstitutionTypeCombobox = new JComboBox<>(new String[] {
                MedicineInstitutionType.ALL.toString(),
                MedicineInstitutionType.HOSPITAL.toString(),
                MedicineInstitutionType.POLYCLINIC.toString()});

        JComboBox<String> doctorTypeCombobox = new JComboBox<>(new String [] {DoctorType.THERAPIST.toString(), DoctorType.RADIOLOGIST.toString(),
                DoctorType.OPHTHALMOLOGIST.toString(), DoctorType.NEUROPATHOLOGIST.toString(),
                DoctorType.SURGEON.toString(), DoctorType.DENTIST.toString()});

        JComboBox<String> doctorRankCombobox = new JComboBox<>(new String[] {"None",
                DoctorScienceRank.CANDIDATE_OF_SCIENCE.toString(),
                DoctorScienceRank.DOCTOR_OF_SCIENCE.toString()});

        JComboBox<String> doctorPositionCombobox = new JComboBox<>(new String[] {"None", DoctorSciencePosition.PROFESSOR.toString(), DoctorSciencePosition.DOCENT.toString()});

        medicineInstitutionTypeCombobox.addActionListener(l -> {
            medicineInstitutions.removeAllItems();
            var type = MedicineInstitutionType.fromString(medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()));
            switch (type) {
                case HOSPITAL -> {
                    var hospitals = HospitalAPI.getAll();
                    hospitals.forEach(el -> {
                        medicineInstitutions.addItem(el.toString());
                        instIndexes.add(el.getId());
                    });
                }
                case POLYCLINIC -> {
                    var polyclinics = PolyclinicAPI.getAll();
                    polyclinics.forEach(el -> {
                        medicineInstitutions.addItem(el.toString());
                        instIndexes.add(el.getId());
                    });
                }

            }
        });

        backButton.addActionListener(l -> queryFrame.dispose());

        getButton.addActionListener(l -> {
            Integer id = 0;
            if (MedicineInstitutionType.fromString(medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()))
                    != MedicineInstitutionType.ALL) {
                id = instIndexes.get(medicineInstitutions.getSelectedIndex());
            }

            Integer finalId = id;
            if (!checkBox.isSelected()) {
                var doctors = DoctorsAPI.getDoctorsWithSuchRankAndPosition(
                        doctorTypeCombobox.getItemAt(doctorTypeCombobox.getSelectedIndex()),
                        medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()),
                        finalId, doctorRankCombobox.getItemAt(doctorRankCombobox.getSelectedIndex()),
                        doctorPositionCombobox.getItemAt(doctorPositionCombobox.getSelectedIndex()));
                showFrameWIthDoctorWorks(doctors);
            } else {
                var doctorsCount = DoctorsAPI.getCountOfDoctorsWithSuchRankAndPosition(
                        doctorTypeCombobox.getItemAt(doctorTypeCombobox.getSelectedIndex()),
                        medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()),
                        finalId, doctorRankCombobox.getItemAt(doctorRankCombobox.getSelectedIndex()),
                        doctorPositionCombobox.getItemAt(doctorPositionCombobox.getSelectedIndex()));
                List<DoctorTypeStatisticEntity> list = new ArrayList<>(1);
                list.add(doctorsCount);
                showFrameWIthDoctorWorks(list);
            }

        });

        medicineInstitutions.setPreferredSize(new Dimension(300, 50));
        medicineInstitutionTypeCombobox.setPreferredSize(new Dimension(300, 50));
        doctorTypeCombobox.setPreferredSize(new Dimension(300, 50));
        getButton.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));
        checkBox.setPreferredSize(new Dimension(100, 50));
        doctorPositionCombobox.setPreferredSize(new Dimension(300, 50));
        doctorRankCombobox.setPreferredSize(new Dimension(300, 50));

        queryFrame.add(doctorTypeCombobox);
        queryFrame.add(doctorRankCombobox);
        queryFrame.add(doctorPositionCombobox);
        queryFrame.add(medicineInstitutionTypeCombobox);
        queryFrame.add(medicineInstitutions);
        queryFrame.add(checkBox);
        queryFrame.add(getButton);
        queryFrame.add(backButton);

        queryFrame.setVisible(true);
    }

}
