/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileInfoPage extends JFrame {
    
    private String[] employeeDetails;

    public ProfileInfoPage(String[] employeeDetails) {
        this.employeeDetails = employeeDetails;
        
        setTitle("Profile Info Page - " + employeeDetails[0] + " " + employeeDetails[1]);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Profile Info Panel
        JPanel profileInfoPanel = new JPanel();
        profileInfoPanel.setLayout(new GridLayout(5, 1));
        
        // Sample Profile Info
        JLabel nameLabel = new JLabel("Name: " + employeeDetails[0] + " " + employeeDetails[1], SwingConstants.CENTER);
        JLabel nricLabel = new JLabel("NRIC: " + employeeDetails[2], SwingConstants.CENTER);
        JLabel positionLabel = new JLabel("Position: " + employeeDetails[27], SwingConstants.CENTER); // Assuming CurrentPosition is in column 28
        JLabel departmentLabel = new JLabel("Department: " + employeeDetails[28], SwingConstants.CENTER);

        profileInfoPanel.add(nameLabel);
        profileInfoPanel.add(nricLabel);
        profileInfoPanel.add(positionLabel);
        profileInfoPanel.add(departmentLabel);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeePage employeePage = new EmployeePage(employeeDetails);
                employeePage.setVisible(true);
                dispose();
            }
        });
        profileInfoPanel.add(backButton);
        
        add(profileInfoPanel, BorderLayout.CENTER);
    }
}

