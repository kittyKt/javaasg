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

public class EmployeePage extends JFrame {
    
    private String[] employeeDetails;
    private String loggedInUsername;

    public EmployeePage(String[] employeeDetails) {
        
        this.employeeDetails = employeeDetails;
        this.loggedInUsername = employeeDetails[3];
        
        // Set up the frame
        setTitle("Employee Page - " + employeeDetails[0] + " " + employeeDetails[1]);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Profile Picture
        JLabel profilePicLabel = new JLabel();
        profilePicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        //load picture from csv
        String imagePath = employeeDetails.length > 39 && employeeDetails[39] != null && !employeeDetails[39].isEmpty()
                ? employeeDetails[39]
                : "images/default_profile.jpeg"; 
        
        ImageIcon profilePic = new ImageIcon(imagePath);
        
        // Debug: Check if image is loaded
        if (profilePic.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.err.println("Error Loading "+imagePath);
        } else {
            profilePicLabel.setIcon(new ImageIcon(profilePic.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        }

        // Make profile picture clickable
        profilePicLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProfileInfoPage profileInfoPage = new ProfileInfoPage(employeeDetails);
                profileInfoPage.setVisible(true);
                EmployeePage.this.dispose();
            }
        });
        
        add(profilePicLabel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 1, 10, 10));
        add(buttonsPanel, BorderLayout.CENTER);

        // Time Management Button
        JButton timeManagementButton = new JButton("Time Management");
        timeManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeManagementPage timeManagementPage = new TimeManagementPage(employeeDetails);
                timeManagementPage.setVisible(true);
                EmployeePage.this.dispose();
            }
        });
        buttonsPanel.add(timeManagementButton);

        // Apply Leave
        JButton applyLeaveButton = new JButton("Apply Leave");
        applyLeaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplyLeave applyLeavePage = new ApplyLeave(employeeDetails);
                applyLeavePage.setVisible(true);
                EmployeePage.this.dispose();
            }
        });
        buttonsPanel.add(applyLeaveButton);
        
        // Status Page Button
        JButton statusPageButton = new JButton("Status Page");
        statusPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatusPageGUI frame = new StatusPageGUI(loggedInUsername,employeeDetails);
                frame.setVisible(true);
                EmployeePage.this.dispose();
            }
        });
        buttonsPanel.add(statusPageButton);

        // Set visibility
        setVisible(true);
    }

    public static void main(String[] args) {
         SwingUtilities.invokeLater(() -> {
            // Sample data for testing
            String[] sampleEmployeeDetails = {
                "John", "Doe", "123456", "jdoe", "password", "Active", "Employee",
                "01/01/1980", "Caucasian", "Christianity", "New York", "jdoe@example.com",
                "123 Main St", "Male", "Single", "123-456-7890", "Bank XYZ", "12345678",
                "EPF12345", "TIN12345", "Jane Doe", "12345678", "Spouse", "123-456-7890",
                "5 years", "01/01/2015", "", "Software Engineer", "IT", "5000", "12",
                "6", "0", "0", "Senior Software Engineer", "01/01/2020", "Position History",
                "100", "Increment History"
            };
            new EmployeePage(sampleEmployeeDetails).setVisible(true);
        });
    }
}




    

