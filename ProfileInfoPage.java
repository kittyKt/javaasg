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

    public ProfileInfoPage() {
        setTitle("Profile Info Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Profile Info Panel
        JPanel profileInfoPanel = new JPanel();
        profileInfoPanel.setLayout(new GridLayout(5, 1));
        
        // Sample Profile Info
        JLabel nameLabel = new JLabel("Name: John Doe", SwingConstants.CENTER);
        JLabel idLabel = new JLabel("Employee ID: E001", SwingConstants.CENTER);
        JLabel positionLabel = new JLabel("Position: Software Engineer", SwingConstants.CENTER);
        JLabel departmentLabel = new JLabel("Department: IT", SwingConstants.CENTER);

        profileInfoPanel.add(nameLabel);
        profileInfoPanel.add(idLabel);
        profileInfoPanel.add(positionLabel);
        profileInfoPanel.add(departmentLabel);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeePage employeePage = new EmployeePage();
                employeePage.setVisible(true);
                dispose();
            }
        });
        profileInfoPanel.add(backButton);
        
        add(profileInfoPanel, BorderLayout.CENTER);
    }
}

