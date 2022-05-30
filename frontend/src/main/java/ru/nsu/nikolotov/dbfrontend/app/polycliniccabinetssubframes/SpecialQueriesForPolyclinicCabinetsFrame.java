package ru.nsu.nikolotov.dbfrontend.app.polycliniccabinetssubframes;

import ru.nsu.nikolotov.dbfrontend.api.PolyclinicAPI;
import ru.nsu.nikolotov.dbfrontend.api.PolyclinicCabinetAPI;
import ru.nsu.nikolotov.dbfrontend.app.utilframes.TableFrame;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorStatisticEntity;
import ru.nsu.nikolotov.dbfrontend.entities.PolyclinicEntity;
import ru.nsu.nikolotov.dbfrontend.layout.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SpecialQueriesForPolyclinicCabinetsFrame {
    private final JFrame menuFrame = new JFrame("Menu");
    private final JButton getCabinetCountButton = new JButton("... count of cabinets in such polyclinic");
    private final JButton getVisitsCountButton = new JButton("... count of visits for such cabinet");
    private final JButton backButton = new JButton("Back");


    public SpecialQueriesForPolyclinicCabinetsFrame() {
        menuFrame.setSize(500, 400);
        menuFrame.setLayout(new VerticalLayout());
        getCabinetCountButton.setPreferredSize(new Dimension(450, 50));
        getVisitsCountButton.setPreferredSize(new Dimension(450, 50));

        backButton.setPreferredSize(new Dimension(300, 50));

        menuFrame.add(getCabinetCountButton);
        menuFrame.add(getVisitsCountButton);
        menuFrame.add(backButton);
        addActionListeners();

    }

    public void callFrame() {
        menuFrame.setVisible(true);
    }

    private void addActionListeners() {
        backButton.addActionListener(l -> menuFrame.dispose());
        getCabinetCountButton.addActionListener(l -> getCabinetsCountAction());
        getVisitsCountButton.addActionListener(l -> getVisitsCountAction());
    }

    private void getCabinetsCountAction() {
        JFrame queryFrame = new JFrame("Set parameters");

        JButton getButton = new JButton("Get");
        JButton backButton = new JButton("Back");
        queryFrame.setSize(500, 500);
        queryFrame.setLayout(new VerticalLayout());

        JComboBox<String> polyclinicsCombobox = new JComboBox<>();

        var polyclinics = PolyclinicAPI.getAll();

        polyclinics.forEach(el -> polyclinicsCombobox.addItem(el.getName()));

        getButton.addActionListener(l -> {
            var stats = PolyclinicAPI.getCountOfCabinets(polyclinics.get(polyclinicsCombobox.getSelectedIndex()).getId());
            List<DoctorStatisticEntity> list = new ArrayList<>();
            list.add(stats);
            TableFrame.showTable(list);
        });

        backButton.addActionListener(l -> queryFrame.dispose());

        polyclinicsCombobox.setPreferredSize(new Dimension(300, 50));
        getButton.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));

        queryFrame.add(polyclinicsCombobox);
        queryFrame.add(getButton);
        queryFrame.add(backButton);
        queryFrame.setVisible(true);

    }

    private void getVisitsCountAction() {
        JFrame queryFrame = new JFrame("Set parameters");

        JButton getButton = new JButton("Get");
        JButton backButton = new JButton("Back");
        queryFrame.setSize(500, 500);
        queryFrame.setLayout(new VerticalLayout());

        JComboBox<String> polyclinicsCombobox = new JComboBox<>();
        var polyclinics = PolyclinicAPI.getAll();

        polyclinics.forEach(el -> polyclinicsCombobox.addItem(el.getName()));

        JTextField beginDateTextField = new JTextField("Begin date in dd-MM-yyyy");
        JTextField endDateTextField = new JTextField("End date in dd-MM-yyyy");

        getButton.addActionListener(l -> {
            var statistic = PolyclinicCabinetAPI.getCabinetVisitStats(
                    polyclinics.get(polyclinicsCombobox.getSelectedIndex()).getId(),
                    beginDateTextField.getText(),
                    endDateTextField.getText()
            );
            TableFrame.showTable(statistic);
        });



        backButton.addActionListener(l -> queryFrame.dispose());

        polyclinicsCombobox.setPreferredSize(new Dimension(300, 50));
        beginDateTextField.setPreferredSize(new Dimension(300, 50));
        endDateTextField.setPreferredSize(new Dimension(300, 50));
        getButton.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));

        queryFrame.add(polyclinicsCombobox);
        queryFrame.add(beginDateTextField);
        queryFrame.add(endDateTextField);
        queryFrame.add(getButton);
        queryFrame.add(backButton);
        queryFrame.setVisible(true);
    }
}
