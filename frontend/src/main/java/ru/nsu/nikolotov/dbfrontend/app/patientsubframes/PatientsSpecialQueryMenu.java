package ru.nsu.nikolotov.dbfrontend.app.patientsubframes;

import ru.nsu.nikolotov.dbfrontend.api.*;
import ru.nsu.nikolotov.dbfrontend.app.utilframes.TableFrame;
import ru.nsu.nikolotov.dbfrontend.entities.DoneOperationsForPatientEntity;
import ru.nsu.nikolotov.dbfrontend.entities.PolyclinicEntity;
import ru.nsu.nikolotov.dbfrontend.entities.ServiceStaffPositionEntity;
import ru.nsu.nikolotov.dbfrontend.layout.VerticalLayout;
import ru.nsu.nikolotov.dbfrontend.types.DoctorType;
import ru.nsu.nikolotov.dbfrontend.types.MedicineInstitutionType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PatientsSpecialQueryMenu {
    private final JFrame menuFrame = new JFrame("Menu");
    private final JButton getListOfPatientsTreatsInHospButton = new JButton("... people, who treats in such hospital");
    private final JButton getListOfPatientsTreatedInHospButton = new JButton("... people, who treated in such hospital");
    private final JButton getListOfPatientsTreatedByDoctorButton = new JButton("... people, who treated by such doctor");
    private final JButton getListOfPatientsTreatsInSuchPolyclinicButton = new JButton("... people, who treated in such polyclinic");
    private final JButton getListOfPatientsWhoHadOperationsButton = new JButton("... patients who had operations");


    private final JButton backButton = new JButton("Back");

    public PatientsSpecialQueryMenu() {
        menuFrame.setSize(500, 400);
        menuFrame.setLayout(new VerticalLayout());
        getListOfPatientsTreatedInHospButton.setPreferredSize(new Dimension(450, 50));
        getListOfPatientsTreatsInHospButton.setPreferredSize(new Dimension(450, 50));
        getListOfPatientsTreatedByDoctorButton.setPreferredSize(new Dimension(450, 50));
        getListOfPatientsTreatsInSuchPolyclinicButton.setPreferredSize(new Dimension(450, 50));
        getListOfPatientsWhoHadOperationsButton.setPreferredSize(new Dimension(450, 50));
        backButton.setPreferredSize(new Dimension(300, 50));

        menuFrame.add(getListOfPatientsTreatedInHospButton);
        menuFrame.add(getListOfPatientsTreatsInHospButton);
        menuFrame.add(getListOfPatientsTreatedByDoctorButton);
        menuFrame.add(getListOfPatientsTreatsInSuchPolyclinicButton);
        menuFrame.add(getListOfPatientsWhoHadOperationsButton);
        menuFrame.add(backButton);
        addActionListeners();
    }

    public void callFrame() {
        menuFrame.setVisible(true);
    }

    private void addActionListeners() {
        backButton.addActionListener(l -> menuFrame.dispose());
        getListOfPatientsTreatedInHospButton.addActionListener(l -> getListOfPatientsTreatedInHospButtonAction());
        getListOfPatientsTreatsInHospButton.addActionListener(l -> getListOfPatientsTreatsInHospButtonAction());
        getListOfPatientsTreatsInSuchPolyclinicButton.addActionListener(l -> getListOfPatientsTreatsInSuchPolyclinicButtonAction());
        getListOfPatientsTreatedByDoctorButton.addActionListener(l -> getListOfPatientsTreatedByDoctorButton());
        getListOfPatientsWhoHadOperationsButton.addActionListener(l -> getListOfPatientsWhoHadOperationsAction());
    }

    private void getListOfPatientsTreatedInHospButtonAction() {
        JFrame queryFrame = new JFrame("Set parameters");

        JButton getButton = new JButton("Get");
        JButton backButton = new JButton("Back");
        queryFrame.setSize(500, 500);
        queryFrame.setLayout(new VerticalLayout());

        JComboBox<String> medicineInstitutions = new JComboBox<>();

        var hospitals = HospitalAPI.getAll();
        hospitals.forEach(el -> {
            medicineInstitutions.addItem(el.getName());
        });

        JTextField admissionDate = new JTextField("Admission date in dd-MM-yyyy");
        JTextField recoveryDate = new JTextField("Recovery date in dd-MM-yyyy");

        getButton.addActionListener(l -> {
            var patients = PatientAPI.getPatientsWhoTreatedInHospital(
                    hospitals.get(medicineInstitutions.getSelectedIndex()).getId(),
                    admissionDate.getText(),
                    recoveryDate.getText()
            );
            TableFrame.showTable(patients);
        });
        backButton.addActionListener(l -> queryFrame.dispose());

        medicineInstitutions.setPreferredSize(new Dimension(300, 50));
        admissionDate.setPreferredSize(new Dimension(300, 50));
        recoveryDate.setPreferredSize(new Dimension(300, 50));
        getButton.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));


        queryFrame.add(medicineInstitutions);
        queryFrame.add(admissionDate);
        queryFrame.add(recoveryDate);
        queryFrame.add(getButton);
        queryFrame.add(backButton);
        queryFrame.setVisible(true);

    }

    private void getListOfPatientsTreatsInHospButtonAction () {
        JFrame queryFrame = new JFrame("Set parameters");

        JButton getButton = new JButton("Get");
        JButton backButton = new JButton("Back");
        queryFrame.setSize(500, 500);
        queryFrame.setLayout(new VerticalLayout());

        JComboBox<String> hospitalsCombobox = new JComboBox<>();
        
        JComboBox<String> depsCombobox = new JComboBox<>();
        JComboBox<String> wardsCombobox = new JComboBox<>();

        List<Integer> departmentIndexes = new ArrayList<>();
        List<Integer> wardsIndexes = new ArrayList<>();

        var hospitals = HospitalAPI.getAll();
        hospitals.forEach(el -> {
            hospitalsCombobox.addItem(el.getName());
        });

        hospitalsCombobox.addActionListener(l -> {
            if (depsCombobox.getItemCount() != 0) {
                depsCombobox.removeAllItems();
                departmentIndexes.clear();
            }
            System.out.println("department: " + hospitals.get(hospitalsCombobox.getSelectedIndex()).getId());
            var departments = HospitalDepartmentsAPI.getDepartmentsInHospital(hospitals.get(hospitalsCombobox.getSelectedIndex()).getId());
            departments.forEach(el -> {
                depsCombobox.addItem(el.getName());
                departmentIndexes.add(el.getId());
            });
        });

        depsCombobox.addActionListener(l -> {
            /*if (wardsCombobox.getItemCount() != 0) {
                wardsCombobox.removeAllItems();
                wardsIndexes.clear();

            }*/

            //System.out.println("wards: " + hospitals.get(departmentIndexes.get(depsCombobox.getSelectedIndex())));

            var departments = HospitalWardsAPI.getWardsInDepartment(1);
            departments.forEach(el -> {
                wardsCombobox.addItem(el.getWardNumber());
                wardsIndexes.add(el.getId());
            });


        });

        getButton.addActionListener(l -> {
            Integer wardId = null;
            if (wardsCombobox.getSelectedIndex() > 0) {
                wardId = wardsIndexes.get(wardsCombobox.getSelectedIndex());
            }
            Integer depId = null;
            if (depsCombobox.getSelectedIndex() > 0) {
                depId = departmentIndexes.get(depsCombobox.getSelectedIndex());
            }
            Integer hospitalID = hospitals.get(hospitalsCombobox.getSelectedIndex()).getId();
            var patients = PatientAPI.getPatientsWhoTreatsInSuchPlace(hospitalID, depId, wardId);
            TableFrame.showTable(patients);
        });
        backButton.addActionListener(l -> queryFrame.dispose());

        hospitalsCombobox.setPreferredSize(new Dimension(300, 50));
        depsCombobox.setPreferredSize(new Dimension(300, 50));
        wardsCombobox.setPreferredSize(new Dimension(300, 50));
        getButton.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));

        queryFrame.add(hospitalsCombobox);
        queryFrame.add(depsCombobox);
        queryFrame.add(wardsCombobox);
        queryFrame.add(getButton);
        queryFrame.add(backButton);

        queryFrame.setVisible(true);
    }

    public void getListOfPatientsTreatsInSuchPolyclinicButtonAction() {
        JFrame queryFrame = new JFrame("Set parameters");

        JButton getButton = new JButton("Get");
        JButton backButton = new JButton("Back");
        queryFrame.setSize(500, 500);
        queryFrame.setLayout(new VerticalLayout());

        JComboBox<String> polyclinicsCombobox = new JComboBox<>();

        List<PolyclinicEntity> polyclinics = PolyclinicAPI.getAll();
        polyclinics.forEach(el -> polyclinicsCombobox.addItem(el.getName()));

        JComboBox<String> doctorTypeCombobox = new JComboBox<>(new String[] {DoctorType.THERAPIST.toString(), DoctorType.RADIOLOGIST.toString(),
                DoctorType.OPHTHALMOLOGIST.toString(), DoctorType.NEUROPATHOLOGIST.toString(),
                DoctorType.SURGEON.toString(), DoctorType.DENTIST.toString()});

        backButton.addActionListener(l -> queryFrame.dispose());

        getButton.addActionListener(l -> {
            var patients = PatientAPI.getPatientsWhoTreatedInSuchPolyclinic(
                    doctorTypeCombobox.getItemAt(doctorTypeCombobox.getSelectedIndex()),
                    polyclinics.get(polyclinicsCombobox.getSelectedIndex()).getId());
            TableFrame.showTable(patients);
        });

        polyclinicsCombobox.setPreferredSize(new Dimension(300, 50));
        doctorTypeCombobox.setPreferredSize(new Dimension(300, 50));
        getButton.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));

        queryFrame.add(polyclinicsCombobox);
        queryFrame.add(doctorTypeCombobox);
        queryFrame.add(getButton);
        queryFrame.add(backButton);

        queryFrame.setVisible(true);
    }

    private void getListOfPatientsTreatedByDoctorButton() {
        JFrame queryFrame = new JFrame("Set parameters");

        JButton getButton = new JButton("Get");
        JButton backButton = new JButton("Back");
        queryFrame.setSize(500, 500);
        queryFrame.setLayout(new VerticalLayout());

        JComboBox<String> doctorsCombobox = new JComboBox<>();

        var doctors = DoctorsAPI.getAllDTO();
        doctors.forEach(el -> {
            doctorsCombobox.addItem(el.getId() + " " + el.getName());
        });

        JTextField admissionDate = new JTextField("Admission date in dd-MM-yyyy");
        JTextField recoveryDate = new JTextField("Recovery date in dd-MM-yyyy");

        getButton.addActionListener(l -> {
            var patients = PatientAPI.getPatientsWhoTreatedByDoctor(
                    doctors.get(doctorsCombobox.getSelectedIndex()).getId(),
                    admissionDate.getText(),
                    recoveryDate.getText()
            );
            TableFrame.showTable(patients);
        });
        backButton.addActionListener(l -> queryFrame.dispose());

        doctorsCombobox.setPreferredSize(new Dimension(300, 50));
        admissionDate.setPreferredSize(new Dimension(300, 50));
        recoveryDate.setPreferredSize(new Dimension(300, 50));
        getButton.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));


        queryFrame.add(doctorsCombobox);
        queryFrame.add(admissionDate);
        queryFrame.add(recoveryDate);
        queryFrame.add(getButton);
        queryFrame.add(backButton);
        queryFrame.setVisible(true);

    }

    private void getListOfPatientsWhoHadOperationsAction() {
        JFrame queryFrame = new JFrame("Set parameters");

        JButton getButton = new JButton("Get");
        JButton backButton = new JButton("Back");
        queryFrame.setSize(500, 500);
        queryFrame.setLayout(new VerticalLayout());
        JTextField beginDateTextField = new JTextField("Begin date in dd-MM-yyyy");
        JTextField endDateTextField = new JTextField("End date in dd-MM-yyyy");


        JComboBox<String> doctorsCombobox = new JComboBox<>();
        doctorsCombobox.addItem("");
        var doctors = DoctorsAPI.getAllDTO();
        doctors.forEach(el -> {
            doctorsCombobox.addItem(el.getId() + " " + el.getName());
        });


        JComboBox<String> medicineInstTypeCombobox = new JComboBox<>(new String[]{MedicineInstitutionType.HOSPITAL.toString(), MedicineInstitutionType.POLYCLINIC.toString()});
        JComboBox<String> medicineInstitutionsCombobox = new JComboBox<>();
        medicineInstitutionsCombobox.addItem("");

        medicineInstTypeCombobox.addActionListener(l -> {
            medicineInstitutionsCombobox.removeAllItems();
            medicineInstitutionsCombobox.addItem("");
            if (medicineInstTypeCombobox.getSelectedIndex() == 0) {
                var hosp = HospitalAPI.getAll();
                hosp.forEach(el -> medicineInstitutionsCombobox.addItem(el.getName()));
            } else {
                var polyclinics = PolyclinicAPI.getAll();
                polyclinics.forEach(el -> medicineInstitutionsCombobox.addItem(el.getName()));
            }
        });

        getButton.addActionListener(l -> {
            List<DoneOperationsForPatientEntity> resp;
            if (doctorsCombobox.getSelectedIndex() != 0) {
                resp = PatientAPI.getPatientsWhoHadOperatedByDoctor(doctors.get(doctorsCombobox.getSelectedIndex()-1).getId(),
                        beginDateTextField.getText(),
                        endDateTextField.getText());
            } else if (medicineInstTypeCombobox.getSelectedIndex() == 0) {
                var hosp = HospitalAPI.getAll();
                hosp.forEach(el -> medicineInstitutionsCombobox.addItem(el.getName()));
                resp = PatientAPI.getPatientsWhoHadOperationInHospital(
                        hosp.get(medicineInstitutionsCombobox.getSelectedIndex()-1).getId(),
                        beginDateTextField.getText(),
                        endDateTextField.getText());

            } else {
                var poly = PolyclinicAPI.getAll();
                poly.forEach(el -> medicineInstitutionsCombobox.addItem(el.getName()));
                resp = PatientAPI.getPatientsWhoHadOperationInHospital(
                        poly.get(medicineInstitutionsCombobox.getSelectedIndex()-1).getId(),
                        beginDateTextField.getText(),
                        endDateTextField.getText());
            }
            TableFrame.showTable(resp);
        });

        backButton.addActionListener(l -> queryFrame.dispose());

        medicineInstTypeCombobox.setPreferredSize(new Dimension(300, 50));
        doctorsCombobox.setPreferredSize(new Dimension(300, 50));
        medicineInstitutionsCombobox.setPreferredSize(new Dimension(300, 50));

        beginDateTextField.setPreferredSize(new Dimension(300, 50));
        endDateTextField.setPreferredSize(new Dimension(300, 50));
        getButton.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));


        queryFrame.add(medicineInstTypeCombobox);
        queryFrame.add(medicineInstitutionsCombobox);
        queryFrame.add(doctorsCombobox);
        queryFrame.add(beginDateTextField);
        queryFrame.add(endDateTextField);
        queryFrame.add(getButton);
        queryFrame.add(backButton);
        queryFrame.setVisible(true);
    }
}
