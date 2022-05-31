package ru.nsu.nikolotov.dbfrontend.app.labssubframe;

import ru.nsu.nikolotov.dbfrontend.api.HospitalAPI;
import ru.nsu.nikolotov.dbfrontend.api.LabsAPI;
import ru.nsu.nikolotov.dbfrontend.api.PolyclinicAPI;
import ru.nsu.nikolotov.dbfrontend.app.utilframes.TableFrame;
import ru.nsu.nikolotov.dbfrontend.layout.VerticalLayout;
import ru.nsu.nikolotov.dbfrontend.types.LaboratoryTypes;
import ru.nsu.nikolotov.dbfrontend.types.MedicineInstitutionType;

import javax.swing.*;
import java.awt.*;

public class SpecialQueriesForLabsMenu {

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 150;
    private final JFrame menuFrame = new JFrame("Menu");
    private final JButton labsWorkLoadButton = new JButton("... labs workload");
    private final JButton backButton = new JButton("Back");
    public SpecialQueriesForLabsMenu() {
        menuFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        menuFrame.setLayout(new VerticalLayout());
        labsWorkLoadButton.setPreferredSize(new Dimension(450, 50));

        backButton.setPreferredSize(new Dimension(450, 50));

        menuFrame.add(labsWorkLoadButton);
        menuFrame.add(backButton);
        addActionListeners();
    }

    public void callFrame() {
        menuFrame.setVisible(true);
    }

    private void addActionListeners() {
        backButton.addActionListener(l -> menuFrame.dispose());
        labsWorkLoadButton.addActionListener(l -> labsWorkLoadButtonAction());
    }


    private void labsWorkLoadButtonAction() {
        JFrame queryFrame = new JFrame("Set parameters");

        JButton getButton = new JButton("Get");
        JButton backButton = new JButton("Back");
        queryFrame.setSize(500, 500);
        queryFrame.setLayout(new VerticalLayout());


        JTextField beginDateTextField = new JTextField("Begin date in dd-MM-yyyy");
        JTextField endDateTextField = new JTextField("End date in dd-MM-yyyy");


        String[] instTypeStrings = new String[] {MedicineInstitutionType.HOSPITAL.toString(), MedicineInstitutionType.POLYCLINIC.toString()};
        JComboBox<String> instTypeCombobox = new JComboBox<>(instTypeStrings);

        String[] labTypeStrings = new String[] {LaboratoryTypes.BIOCHEMICAL.toString(), LaboratoryTypes.BIOLOGICAL.toString(), LaboratoryTypes.PHYSIOLOGICAL.toString()};
        JComboBox<String> labTypeCombobox = new JComboBox<>(labTypeStrings);

        JComboBox<String> labsCombobox = new JComboBox<>();
        labTypeCombobox.addActionListener(l -> {
            labsCombobox.removeAllItems();
            var labs = LabsAPI.getLabsWithSuchType(labTypeCombobox.getItemAt(labTypeCombobox.getSelectedIndex()));
            labs.forEach(el -> labsCombobox.addItem(el.getName()));
        });

        JComboBox<String> instsCombobox = new JComboBox<>();
        instsCombobox.addItem("");
        instTypeCombobox.addActionListener(l -> {
            instsCombobox.removeAllItems();
            instsCombobox.addItem("");
            if (instsCombobox.getSelectedIndex() == 0) {
                var hosp = HospitalAPI.getAll();
                hosp.forEach(el -> instsCombobox.addItem(el.getName()));
            } else {
                var polyclinics = PolyclinicAPI.getAll();
                polyclinics.forEach(el -> instsCombobox.addItem(el.getName()));
            }
        });

        getButton.addActionListener(l -> {
            Integer instId = null;
            if (instsCombobox.getSelectedIndex() != 0) {
                instId = instsCombobox.getSelectedIndex();
            }
            var labs = LabsAPI.getLabsWithSuchType(labTypeCombobox.getItemAt(labTypeCombobox.getSelectedIndex()));
            Integer labId = labs.get(labsCombobox.getSelectedIndex()).getId();
            String labType = labTypeCombobox.getItemAt(labTypeCombobox.getSelectedIndex());
            String instType = instTypeCombobox.getItemAt(instTypeCombobox.getSelectedIndex());

            String beginDate = beginDateTextField.getText();
            String endDate = endDateTextField.getText();

            var resp = LabsAPI.getLabServicesStatistic(labId, labType, beginDate, endDate, instId, instType);
            TableFrame.showTable(resp);
        });

        instTypeCombobox.setPreferredSize(new Dimension(300, 50));
        instsCombobox.setPreferredSize(new Dimension(300, 50));
        labTypeCombobox.setPreferredSize(new Dimension(300, 50));
        labsCombobox.setPreferredSize(new Dimension(300, 50));

        beginDateTextField.setPreferredSize(new Dimension(300, 50));
        endDateTextField.setPreferredSize(new Dimension(300, 50));

        getButton.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));


        backButton.addActionListener(l -> queryFrame.dispose());
        queryFrame.add(instTypeCombobox);
        queryFrame.add(instsCombobox);
        queryFrame.add(labTypeCombobox);
        queryFrame.add(labsCombobox);


        queryFrame.add(beginDateTextField);
        queryFrame.add(endDateTextField);
        queryFrame.add(getButton);
        queryFrame.add(backButton);
        queryFrame.setVisible(true);
    }
}
