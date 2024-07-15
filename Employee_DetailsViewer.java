
package javaassignment;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Employee_DetailsViewer extends JFrame {

    public Employee_DetailsViewer(DefaultTableModel model) {
        setTitle("Employee Details CSV Data Viewer");

        // Set the size of the JFrame to full screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
}
