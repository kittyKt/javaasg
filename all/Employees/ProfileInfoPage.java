package Employees;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileInfoPage extends JFrame {
    
     private String[] employeeDetails;

    public ProfileInfoPage(String[] employeeDetails) {
        this.employeeDetails = employeeDetails;
        
        setTitle("Profile Info Page - " + employeeDetails[0] + " " + employeeDetails[1]);
        setSize(400, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Profile Info Panel
        JPanel profileInfoPanel = new JPanel();
        profileInfoPanel.setLayout(new BoxLayout(profileInfoPanel, BoxLayout.Y_AXIS));
        
        // Section Headers
        JLabel profileHeader = new JLabel("Profile Information", SwingConstants.CENTER);
        profileHeader.setFont(new Font("Arial", Font.BOLD, 16));
        profileHeader.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        profileInfoPanel.add(profileHeader);
        
        // Profile Information
        profileInfoPanel.add(createInfoLabel("Name: ", getValueOrDefault(0) + " " + getValueOrDefault(1)));
        profileInfoPanel.add(createInfoLabel("NRIC: ", getValueOrDefault(2)));
        profileInfoPanel.add(createInfoLabel("Username: ", getValueOrDefault(3)));
        profileInfoPanel.add(createInfoLabel("Role: ", getValueOrDefault(6)));
        profileInfoPanel.add(createInfoLabel("Date of Birth: ", getValueOrDefault(7)));
        profileInfoPanel.add(createInfoLabel("Race: ", getValueOrDefault(8)));
        profileInfoPanel.add(createInfoLabel("Religion: ", getValueOrDefault(9)));
        profileInfoPanel.add(createInfoLabel("Place of Birth: ", getValueOrDefault(10)));
        profileInfoPanel.add(createInfoLabel("Email: ", getValueOrDefault(11)));
        profileInfoPanel.add(createInfoLabel("Home Address: ", getValueOrDefault(12)));
        profileInfoPanel.add(createInfoLabel("Gender: ", getValueOrDefault(13)));
        profileInfoPanel.add(createInfoLabel("Marital Status: ", getValueOrDefault(14)));
        profileInfoPanel.add(createInfoLabel("HP Number: ", getValueOrDefault(15)));
        profileInfoPanel.add(createInfoLabel("Bank Name: ", getValueOrDefault(16)));
        profileInfoPanel.add(createInfoLabel("Bank Account Number: ", getValueOrDefault(17)));
        profileInfoPanel.add(createInfoLabel("EPF Number: ", getValueOrDefault(18)));
        profileInfoPanel.add(createInfoLabel("TIN Number: ", getValueOrDefault(19)));

        //Emergency Contact Header 
        JLabel emergencyContactHeader = new JLabel("Emergency Contact Information", SwingConstants.CENTER);
        emergencyContactHeader.setFont(new Font("Arial", Font.BOLD, 16));
        emergencyContactHeader.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        profileInfoPanel.add(emergencyContactHeader);
    
        // Emergency Contact Information
        profileInfoPanel.add(createInfoLabel("Emergency Contact Name: ", getValueOrDefault(20)));
        profileInfoPanel.add(createInfoLabel("Emergency Contact NRIC: ", getValueOrDefault(21)));
        profileInfoPanel.add(createInfoLabel("Emergency Contact Relation: ", getValueOrDefault(22)));
        profileInfoPanel.add(createInfoLabel("Emergency Contact HP: ", getValueOrDefault(23)));

        // Employment Details
         JLabel employmentHeader = new JLabel("Employment Details", SwingConstants.CENTER);
        employmentHeader.setFont(new Font("Arial", Font.BOLD, 16));
        employmentHeader.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        profileInfoPanel.add(employmentHeader);
        profileInfoPanel.add(createInfoLabel("Work Experience: ", getValueOrDefault(24)));
        profileInfoPanel.add(createInfoLabel("Date Joined: ", getValueOrDefault(25)));
        profileInfoPanel.add(createInfoLabel("Current Position: ", getValueOrDefault(27)));
        profileInfoPanel.add(createInfoLabel("Department: ", getValueOrDefault(28)));
        profileInfoPanel.add(createInfoLabel("Monthly Gross Salary: ", getValueOrDefault(29)));

        // Leave Entitlement
        JLabel leaveEntitlementHeader = new JLabel("Leave Entitlement", SwingConstants.CENTER);
        leaveEntitlementHeader.setFont(new Font("Arial", Font.BOLD, 16));
        leaveEntitlementHeader.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        profileInfoPanel.add(leaveEntitlementHeader);
        profileInfoPanel.add(createInfoLabel("Annual Leave: ", getValueOrDefault(30)));
        profileInfoPanel.add(createInfoLabel("Medical Leave: ", getValueOrDefault(31)));
        profileInfoPanel.add(createInfoLabel("Maternity Leave: ", getValueOrDefault(32)));
        profileInfoPanel.add(createInfoLabel("Unpaid Leave: ", getValueOrDefault(33)));

        // History
        JLabel historyHeader = new JLabel("History", SwingConstants.CENTER);
        historyHeader.setFont(new Font("Arial", Font.BOLD, 16));
        historyHeader.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        profileInfoPanel.add(historyHeader);
        profileInfoPanel.add(createInfoLabel("History of Position Change: ", getValueOrDefault(36)));
        profileInfoPanel.add(createInfoLabel("History of Salary Increment: ", getValueOrDefault(38)));
     
        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeePage employeePage = new EmployeePage(employeeDetails);
                employeePage.setVisible(true);
                dispose();
            }
        });
        
        profileInfoPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
        profileInfoPanel.add(backButton);
        
        add(new JScrollPane(profileInfoPanel), BorderLayout.CENTER); 
    }
    
    private JLabel createInfoLabel(String labelText, String valueText) {
        JLabel label = new JLabel(labelText + valueText, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return label;
    }

    private String getValueOrDefault(int index) {
        // Check if the index exists in the array and return its value or an empty string if it doesn't
        return (employeeDetails.length > index && employeeDetails[index] != null) ? employeeDetails[index] : "";
    }

    public static void main(String[] args) {
        // Test with dummy data
        String[] employeeDetails = new String[38];
        // Simulate missing data by leaving some fields as null

        ProfileInfoPage profileInfoPage = new ProfileInfoPage(employeeDetails);
        profileInfoPage.setVisible(true);
    }
}

