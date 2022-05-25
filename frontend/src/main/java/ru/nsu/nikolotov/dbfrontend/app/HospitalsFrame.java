package ru.nsu.nikolotov.dbfrontend.app;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import lombok.SneakyThrows;
import ru.nsu.nikolotov.dbfrontend.apiutils.HttpUtils;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalEntity;
import ru.nsu.nikolotov.dbfrontend.layout.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HospitalsFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;


    private final JFrame frame = new JFrame("Hospitals");

    private final JTextField idTextField = new JTextField();
    private final JTextField nameTextField = new JTextField();

    private final JButton createEntityButton = new JButton("Create");

    private final JButton updateEntityButton = new JButton("Update");
    private final JButton deleteEntityButton = new JButton("Delete");

    private final JButton gotoEntityButton = new JButton("Go to");
    private final JButton nextEntityButton = new JButton("Next");
    private final JButton prevEntityButton = new JButton("Prev");

    private final List<HospitalEntity> hospitals;
    private int listIndex = 0;
    private final URI uriToGetAll;

    {
        try {
            uriToGetAll = new URI("http://localhost:8080/api/v1/hospitals/getall");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public HospitalsFrame() {
        try {
            hospitals = Unirest.get("http://localhost:8080/api/v1/hospitals/getall").asObjectAsync(new GenericType<List<HospitalEntity>>(){}).get().getBody();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        addActionListeners();
        setSizes();
        addComponents();
        setTexts();
    }

    public void setFrameVisible(boolean visible) {
        frame.setVisible(visible);
    }

    private void addComponents() {
        frame.add(idTextField);
        frame.add(nameTextField);
        frame.add(updateEntityButton);
        frame.add(createEntityButton);
        frame.add(deleteEntityButton);
        frame.add(nextEntityButton);
        frame.add(prevEntityButton);
        frame.add(gotoEntityButton);

    }

    private void setSizes() {
        idTextField.setPreferredSize(new Dimension(500, 25));
        nameTextField.setPreferredSize(new Dimension(500, 25));
        updateEntityButton.setPreferredSize(new Dimension(100, 25));
        createEntityButton.setPreferredSize(new Dimension(100, 25));
        deleteEntityButton.setPreferredSize(new Dimension(100, 25));
        gotoEntityButton.setPreferredSize(new Dimension(100, 25));
        nextEntityButton.setPreferredSize(new Dimension(100, 25));
        prevEntityButton.setPreferredSize(new Dimension(100, 25));
    }

    private void addActionListeners() {
        //idTextField.setText("Hello!");
        updateEntityButton.addActionListener(l -> System.out.println(idTextField.getText()));
        nextEntityButton.addActionListener(l -> nextButtonAction());
        prevEntityButton.addActionListener(l -> prevButtonAction());
    }

    private void nextButtonAction() {
        if (listIndex + 1 < hospitals.size()) {
            listIndex++;
            setTexts();
        }
    }


    private void prevButtonAction() {
        if (listIndex - 1 >= 0) {
            listIndex--;
            setTexts();
        }
    }

    private void setTexts() {
        idTextField.setText(String.valueOf(hospitals.get(listIndex).getId()));
        nameTextField.setText(hospitals.get(listIndex).getName());
    }
}
