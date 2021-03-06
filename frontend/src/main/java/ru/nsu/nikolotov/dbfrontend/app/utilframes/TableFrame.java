package ru.nsu.nikolotov.dbfrontend.app.utilframes;

import ru.nsu.nikolotov.dbfrontend.entities.DoctorWorksAtInstitutionEntity;
import ru.nsu.nikolotov.dbfrontend.entities.EntityForInsertIntoJTable;
import ru.nsu.nikolotov.dbfrontend.layout.VerticalLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TableFrame {
    private final JFrame frame = new JFrame("Result");
    private final JButton backButton = new JButton("Back");
    private final DefaultTableModel tableModel = new DefaultTableModel();

    private final JTable table = new JTable(tableModel);
    private final JScrollPane scrollPane = new JScrollPane(table);

    public static void showTable(List<? extends EntityForInsertIntoJTable> data) {
        var frame = new TableFrame(data);
        frame.callFrame();
    }

    public TableFrame(List<? extends EntityForInsertIntoJTable> data) {

        if ((data == null) || (data.size() == 0)) {
            JOptionPane.showMessageDialog(null, "No data with spec. options");
        }

        backButton.addActionListener(l -> frame.dispose());
        frame.setSize(1000, 600);
        frame.setLayout(new VerticalLayout());
        if ((data != null) && (data.size() != 0)) {
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.setColumnIdentifiers(data.get(0).getHeaders());
            for (EntityForInsertIntoJTable entity : data) {
                tableModel.addRow(entity.getValues());
            }
            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(950, 500));
            frame.add(scrollPane);
        }
        frame.add(backButton);
        frame.setVisible(true);
    }

    public void callFrame() {
        frame.setVisible(true);
    }
}
