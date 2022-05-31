package ru.nsu.nikolotov.dbfrontend.app.wardssubframes;

import ru.nsu.nikolotov.dbfrontend.api.HospitalAPI;
import ru.nsu.nikolotov.dbfrontend.api.HospitalDepartmentsAPI;
import ru.nsu.nikolotov.dbfrontend.api.HospitalWardsAPI;
import ru.nsu.nikolotov.dbfrontend.app.utilframes.TableFrame;
import ru.nsu.nikolotov.dbfrontend.layout.VerticalLayout;
import ru.nsu.nikolotov.dbfrontend.types.MedicineInstitutionType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SpecialQueriesForWardsMenu {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 150;
    private final JFrame menuFrame = new JFrame("Menu");
    private final JButton countOfPlacesAndWardsButton = new JButton("... count of free/total places/wards");
    private final JButton backButton = new JButton("Back");

    public SpecialQueriesForWardsMenu() {
        menuFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        menuFrame.setLayout(new VerticalLayout());
        countOfPlacesAndWardsButton.setPreferredSize(new Dimension(450, 50));

        backButton.setPreferredSize(new Dimension(450, 50));

        menuFrame.add(countOfPlacesAndWardsButton);
        menuFrame.add(backButton);
        addActionListeners();
    }

    public void callFrame() {
        menuFrame.setVisible(true);
    }

    private void addActionListeners() {
        backButton.addActionListener(l -> menuFrame.dispose());
        countOfPlacesAndWardsButton.addActionListener(l -> countOfPlacesAndWardsButtonAction());
    }

    private void countOfPlacesAndWardsButtonAction() {
        JFrame queryFrame = new JFrame("Set parameters");

        JButton getButton = new JButton("Get");
        JButton backButton = new JButton("Back");
        queryFrame.setSize(500, 500);
        queryFrame.setLayout(new VerticalLayout());


        String[] statTypeStrings = new String[] {"All", "Free"};
        JComboBox<String> statTypeCombobox = new JComboBox<>(statTypeStrings);


        String[] placeTypeStrings = new String[] {"Wards", "Places"};
        JComboBox<String> placeTypeCombobox = new JComboBox<>(placeTypeStrings);


        String[] unitTypeStrings = new String[] {"Hospital", "Department"};
        JComboBox<String> unitTypeCombobox = new JComboBox<>(unitTypeStrings);

        List<Integer> unitsId = new ArrayList<>();
        JComboBox<String> unitsCombobox = new JComboBox<>();

        unitTypeCombobox.addActionListener(l -> {
            unitsCombobox.removeAllItems();
            unitsId.clear();
            if (unitTypeCombobox.getSelectedIndex() == 0) {
                var hospitals = HospitalAPI.getAll();
                hospitals.forEach(el -> {
                    unitsCombobox.addItem(el.getName());
                    unitsId.add(el.getId());
                });
            } else {
                var deps = HospitalDepartmentsAPI.getAll();
                deps.forEach(el -> {
                    unitsCombobox.addItem(el.getName() + " " + el.getHospitalName());
                    unitsId.add(el.getId());
                });
            }
        });

        getButton.addActionListener(l -> {
            var resp = HospitalWardsAPI.getPlacesStats(placeTypeCombobox.getItemAt(placeTypeCombobox.getSelectedIndex()).toLowerCase(),
                    statTypeCombobox.getItemAt(statTypeCombobox.getSelectedIndex()).toLowerCase(),
                    unitTypeCombobox.getItemAt(unitTypeCombobox.getSelectedIndex()).toLowerCase(),
                    unitsId.get(unitsCombobox.getSelectedIndex()));
            TableFrame.showTable(resp);
        });

        backButton.addActionListener(l -> queryFrame.dispose());

        statTypeCombobox.setPreferredSize(new Dimension(450, 50));
        placeTypeCombobox.setPreferredSize(new Dimension(450, 50));
        unitTypeCombobox.setPreferredSize(new Dimension(450, 50));
        unitsCombobox.setPreferredSize(new Dimension(450, 50));

        getButton.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));

        queryFrame.add(statTypeCombobox);
        queryFrame.add(placeTypeCombobox);
        queryFrame.add(unitTypeCombobox);
        queryFrame.add(unitsCombobox);

        queryFrame.add(getButton);
        queryFrame.add(backButton);
        queryFrame.setVisible(true);

    }
}
