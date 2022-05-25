package ru.nsu.nikolotov.dbfrontend.app.servicestaffsubframes;

import ru.nsu.nikolotov.dbfrontend.api.*;
import ru.nsu.nikolotov.dbfrontend.app.utilframes.TableFrame;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorTypeStatisticEntity;
import ru.nsu.nikolotov.dbfrontend.entities.ServiceStaffPositionEntity;
import ru.nsu.nikolotov.dbfrontend.entities.ServiceStaffTypeStatistic;
import ru.nsu.nikolotov.dbfrontend.layout.VerticalLayout;
import ru.nsu.nikolotov.dbfrontend.types.MedicineInstitutionType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SpecialQueriesForServiceStaffMenu {
    private final JFrame menuFrame = new JFrame("Menu");
    private final JButton getListOfServiceStaffButton = new JButton("... people, who work in such inst.");
    private final JButton backButton = new JButton("Back");
    public SpecialQueriesForServiceStaffMenu() {
        menuFrame.setSize(400, 150);
        menuFrame.setLayout(new VerticalLayout());
        getListOfServiceStaffButton.setPreferredSize(new Dimension(300, 50));
        backButton.setPreferredSize(new Dimension(300, 50));

        menuFrame.add(getListOfServiceStaffButton);
        menuFrame.add(backButton);
        addActionListeners();
    }

    public void callFrame() {
        menuFrame.setVisible(true);
    }

    private void addActionListeners() {
        backButton.addActionListener(l -> menuFrame.dispose());
        getListOfServiceStaffButton.addActionListener(l -> getListOfServiceStaffAction());
    }

    private void getListOfServiceStaffAction() {
        JFrame queryFrame = new JFrame("Set parameters");

        JButton getButton = new JButton("Get");
        JButton backButton = new JButton("Back");
        JCheckBox checkBox = new JCheckBox("Count");
        queryFrame.setSize(500, 500);
        queryFrame.setLayout(new VerticalLayout());

        List<Integer> instIndexes = new ArrayList<>();
        List<ServiceStaffPositionEntity> positions = ServiceStaffPositionsAPI.getAll();


        JComboBox<String> medicineInstitutions = new JComboBox<>();
        JComboBox<String> medicineInstitutionTypeCombobox = new JComboBox<>(new String[] {
                MedicineInstitutionType.HOSPITAL.toString(),
                MedicineInstitutionType.POLYCLINIC.toString(),
                MedicineInstitutionType.ALL.toString()});

        JComboBox<String> positionCombobox = new JComboBox<>();
        positions.forEach(el -> positionCombobox.addItem(el.getPositionName()));

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
                var staff = ServiceStaffAPI.getStaffWhoWorksInSuchInst(finalId,
                        medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()),
                        positionCombobox.getItemAt(positionCombobox.getSelectedIndex()));
                TableFrame.showTable(staff);
            } else {
                var cnt = ServiceStaffAPI.getStaffStatistic(finalId,
                        medicineInstitutionTypeCombobox.getItemAt(medicineInstitutionTypeCombobox.getSelectedIndex()),
                        positionCombobox.getItemAt(positionCombobox.getSelectedIndex()));
                List<ServiceStaffTypeStatistic> list = new ArrayList<>(1);
                list.add(cnt);
                TableFrame.showTable(list);
            }
        });

        medicineInstitutions.setPreferredSize(new Dimension(300, 50));
        medicineInstitutionTypeCombobox.setPreferredSize(new Dimension(300, 50));
        positionCombobox.setPreferredSize(new Dimension(300, 50));
        getButton.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));
        checkBox.setPreferredSize(new Dimension(100, 50));
        queryFrame.add(positionCombobox);
        queryFrame.add(medicineInstitutionTypeCombobox);
        queryFrame.add(medicineInstitutions);
        queryFrame.add(checkBox);
        queryFrame.add(getButton);
        queryFrame.add(backButton);
        queryFrame.setVisible(true);

    }
}
