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

    public EmployeePage() {
        // Set up the frame
        setTitle("Employee Page");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Profile Picture
        JLabel profilePicLabel = new JLabel();
        profilePicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        String imagePath = "images/profile.jpeg";
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
                ProfileInfoPage profileInfoPage = new ProfileInfoPage();
                profileInfoPage.setVisible(true);
                EmployeePage.this.dispose();
            }
        });
        
        add(profilePicLabel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1, 10, 10));
        add(buttonsPanel, BorderLayout.CENTER);

        // Time Management Button
        JButton timeManagementButton = new JButton("Time Management");
        timeManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeManagementPage timeManagementPage = new TimeManagementPage();
                timeManagementPage.setVisible(true);
                EmployeePage.this.dispose();
            }
        });
        buttonsPanel.add(timeManagementButton);

        // Apply Leave + Status Application Button
        JButton applyLeaveButton = new JButton("Apply Leave + Status Application");
        applyLeaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeaveApplicationPage leaveApplicationPage = new LeaveApplicationPage();
                leaveApplicationPage.setVisible(true);
                EmployeePage.this.dispose();
            }
        });
        buttonsPanel.add(applyLeaveButton);

        // Set visibility
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeePage();
            }
        });
    }
}




    

