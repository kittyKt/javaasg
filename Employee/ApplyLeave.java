/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */

//ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL ORIGINALL
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class ApplyLeave extends JFrame {
    private JTextField txtUsername, txtMedicalLeave, txtUnpaidLeave, txtAnnualLeave, txtMaternityLeave;
    private JRadioButton rbFemale, rbMale;
    private JCheckBox cbMarried;
    private JButton btnApplyMedical, btnApplyUnpaid, btnApplyAnnual, btnApplyMaternity, btnBack;
    private JLabel lblMessage;
    private JTextField txtStartDate, txtEndDate, txtTotalDays;
    private JTextArea txtAreaMessage;

    private String[] employeeDetails;
    private String gender, maritalStatus, name;
    private int medicalLeaveBalance, unpaidLeaveBalance, annualLeaveBalance, maternityLeaveBalance;

    public ApplyLeave(String[] employeeDetails) {
        this.employeeDetails = employeeDetails;
        setTitle("Apply for Leave");
        setSize(1024, 576);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel for user information
        JPanel userInfoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        userInfoPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        txtUsername = new JTextField(15);
        userInfoPanel.add(txtUsername, gbc);

        gbc.gridx = 2;
        userInfoPanel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 3;
        JPanel genderPanel = new JPanel(new GridLayout(1, 2));
        rbFemale = new JRadioButton("Female");
        rbMale = new JRadioButton("Male");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbFemale);
        genderGroup.add(rbMale);
        genderPanel.add(rbFemale);
        genderPanel.add(rbMale);
        userInfoPanel.add(genderPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        userInfoPanel.add(new JLabel("Medical Leave Balance:"), gbc);
        gbc.gridx = 1;
        txtMedicalLeave = new JTextField(15);
        userInfoPanel.add(txtMedicalLeave, gbc);

        gbc.gridx = 2;
        userInfoPanel.add(new JLabel("Marital Status:"), gbc);
        gbc.gridx = 3;
        cbMarried = new JCheckBox("Married");
        userInfoPanel.add(cbMarried, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        userInfoPanel.add(new JLabel("Annual Leave Balance:"), gbc);
        gbc.gridx = 1;
        txtAnnualLeave = new JTextField(15);
        userInfoPanel.add(txtAnnualLeave, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        userInfoPanel.add(new JLabel("Unpaid Leave Balance:"), gbc);
        gbc.gridx = 1;
        txtUnpaidLeave = new JTextField(15);
        userInfoPanel.add(txtUnpaidLeave, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        userInfoPanel.add(new JLabel("Maternity Leave Balance:"), gbc);
        gbc.gridx = 1;
        txtMaternityLeave = new JTextField(15);
        userInfoPanel.add(txtMaternityLeave, gbc);

        // Panel for applying leave
        JPanel leavePanel = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        leavePanel.add(new JLabel("Start Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        txtStartDate = new JTextField(10);
        leavePanel.add(txtStartDate, gbc);

        gbc.gridx = 2;
        leavePanel.add(new JLabel("End Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 3;
        txtEndDate = new JTextField(10);
        leavePanel.add(txtEndDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        leavePanel.add(new JLabel("Total Days:"), gbc);
        gbc.gridx = 1;
        txtTotalDays = new JTextField(10);
        leavePanel.add(txtTotalDays, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        btnApplyMedical = new JButton("Apply for Medical Leave");
        leavePanel.add(btnApplyMedical, gbc);
        gbc.gridx = 1;
        btnApplyUnpaid = new JButton("Apply for Unpaid Leave");
        leavePanel.add(btnApplyUnpaid, gbc);
        gbc.gridx = 2;
        btnApplyAnnual = new JButton("Apply for Annual Leave");
        leavePanel.add(btnApplyAnnual, gbc);
        gbc.gridx = 3;
        btnApplyMaternity = new JButton("Apply for Maternity Leave");
        leavePanel.add(btnApplyMaternity, gbc);
        
        // Center message area
        txtAreaMessage = new JTextArea(8, 40);
        txtAreaMessage.setEditable(false);
        txtAreaMessage.setLineWrap(true);
        txtAreaMessage.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtAreaMessage);

        // Back Button at the bottom
        JPanel bottomPanel = new JPanel();
        btnBack = new JButton("Back");
        bottomPanel.add(btnBack);

        // Center message area
        /*lblMessage = new JLabel("", SwingConstants.CENTER);
        lblMessage.setForeground(Color.BLUE);*/

        // Add panels to frame
        add(userInfoPanel, BorderLayout.NORTH);
        add(leavePanel, BorderLayout.CENTER);
        //add(lblMessage, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.PAGE_END);

        // Load user data from CSV
        loadUserData();

        // Apply Leave Button Actions
        btnApplyMedical.addActionListener(e -> applyLeave("Medical"));
        btnApplyUnpaid.addActionListener(e -> applyLeave("Unpaid"));
        btnApplyAnnual.addActionListener(e -> applyLeave("Annual"));
        btnApplyMaternity.addActionListener(e -> applyLeave("Maternity"));

        btnBack.addActionListener(e -> {
            dispose();
            EmployeePage employeePage = new EmployeePage(employeeDetails);  // Assuming EmployeePage accepts employeeDetails as a parameter
            employeePage.setVisible(true);
        });

        // Enable/Disable Buttons based on Leave Balance
        btnApplyMedical.setEnabled(medicalLeaveBalance > 0);
        btnApplyUnpaid.setEnabled(unpaidLeaveBalance > 0);
        btnApplyAnnual.setEnabled(annualLeaveBalance > 0);
        btnApplyMaternity.setEnabled(
            gender != null &&
            maritalStatus != null &&
            gender.equals("Female") &&
            maritalStatus.equals("Married") &&
            maternityLeaveBalance > 0
        );
    }
    
    private void loadUserData() {
        // Load user data from the employeeDetails array
        name = employeeDetails.length > 0 ? employeeDetails[0] : "";
        gender = employeeDetails.length > 13 ? employeeDetails[13] : "";  // Assuming gender is stored at index 13
        maritalStatus = employeeDetails.length > 14 ? employeeDetails[14] : "";  // Assuming marital status is stored at index 14
        maternityLeaveBalance = employeeDetails.length > 32 && !employeeDetails[32].isEmpty() 
                                ? Integer.parseInt(employeeDetails[32]) 
                                : 0;  // Assuming maternity leave balance is stored at index 32, set default to 0
        annualLeaveBalance = employeeDetails.length > 30 && !employeeDetails[30].isEmpty() 
                             ? Integer.parseInt(employeeDetails[30]) 
                             : 0;  // Assuming annual leave balance is stored at index 33
        medicalLeaveBalance = employeeDetails.length > 31 && !employeeDetails[31].isEmpty() 
                              ? Integer.parseInt(employeeDetails[31]) 
                              : 0;  // Assuming medical leave balance is stored at index 34
        unpaidLeaveBalance = employeeDetails.length > 33 && !employeeDetails[33].isEmpty() 
                             ? Integer.parseInt(employeeDetails[33]) 
                             : 0;  // Assuming unpaid leave balance is stored at index 35

        // Set fields
        txtUsername.setText(employeeDetails[3]);  // Assuming username is at index 3
        rbFemale.setSelected(gender.equals("Female"));
        rbMale.setSelected(gender.equals("Male"));
        cbMarried.setSelected(maritalStatus.equals("Married"));
        txtMedicalLeave.setText(String.valueOf(medicalLeaveBalance));
        txtAnnualLeave.setText(String.valueOf(annualLeaveBalance));
        txtUnpaidLeave.setText(String.valueOf(unpaidLeaveBalance));
        txtMaternityLeave.setText(String.valueOf(maternityLeaveBalance));
    }

    /*private void loadUserData() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\OneDrive - Asia Pacific University\\work\\SDP2\\Dummy_Data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].equals(loggedInUserID)) {  // Compare using username
                    name = values[2];
                    gender = values[3];
                    maritalStatus = values[4];
                    maternityLeaveBalance = Integer.parseInt(values[5]);
                    annualLeaveBalance = Integer.parseInt(values[6]);
                    medicalLeaveBalance = Integer.parseInt(values[7]);
                    unpaidLeaveBalance = Integer.parseInt(values[8]);

                    // Set fields
                    txtUsername.setText(loggedInUserID);
                    rbFemale.setSelected(gender.equals("Female"));
                    rbMale.setSelected(gender.equals("Male"));
                    cbMarried.setSelected(maritalStatus.equals("Married"));
                    txtMedicalLeave.setText(String.valueOf(medicalLeaveBalance));
                    txtAnnualLeave.setText(String.valueOf(annualLeaveBalance));
                    txtUnpaidLeave.setText(String.valueOf(unpaidLeaveBalance));
                    txtMaternityLeave.setText(String.valueOf(maternityLeaveBalance));

                    break;
                }
            }
        } catch (IOException ex) {
            lblMessage.setText("Error loading user data.");
            ex.printStackTrace();
        }
    }*/

   private void applyLeave(String leaveType) {
        String startDate = txtStartDate.getText().trim();
        String endDate = txtEndDate.getText().trim();
        String totalDaysStr = txtTotalDays.getText().trim();

        txtAreaMessage.setText("");

        if (!isValidDate(startDate) || !isValidDate(endDate)) {
            txtAreaMessage.setText("Error: Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        int totalDays;
        try {
            totalDays = Integer.parseInt(totalDaysStr);
        } catch (NumberFormatException e) {
            txtAreaMessage.setText("Error: Total days must be a number.");
            return;
        }

        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);

        if (end.isBefore(start)) {
            txtAreaMessage.setText("Error: End date cannot be before start date.");
            return;
        }

        // Validate leave balance and type-specific rules
        if (leaveType.equals("Medical") && totalDays > medicalLeaveBalance) {
            txtAreaMessage.setText("Error: Insufficient medical leave balance.");
            return;
        } else if (leaveType.equals("Unpaid") && totalDays > unpaidLeaveBalance) {
            txtAreaMessage.setText("Error: Insufficient unpaid leave balance.");
            return;
        } else if (leaveType.equals("Annual") && totalDays > annualLeaveBalance) {
            txtAreaMessage.setText("Error: Insufficient annual leave balance.");
            return;
        } else if (leaveType.equals("Maternity")) {
            if (!gender.equals("Female")) {
                txtAreaMessage.setText("Error: Maternity leave is only applicable to females.");
                return;
            }
            if (!maritalStatus.equals("Married")) {
                txtAreaMessage.setText("Error: Maternity leave is only applicable to married females.");
                return;
            }
            if (totalDays > maternityLeaveBalance) {
                txtAreaMessage.setText("Error: Insufficient maternity leave balance.");
                return;
            }
        }

        // Save leave application to CSV
        saveLeaveApplication(leaveType, startDate, endDate, totalDays);
    }

    private void saveLeaveApplication(String leaveType, String startDate, String endDate, int totalDays) {
        String filename = "C:\\Users\\4hana\\OneDrive - Asia Pacific University\\( JP ) Java Programming\\homepageGUI\\homepageGUI\\src\\leave_application.csv";

        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.append(employeeDetails[3]).append(",");
            writer.append(leaveType).append(",");
            writer.append(startDate).append(",");
            writer.append(endDate).append(",");
            writer.append(String.valueOf(totalDays)).append(",");
            writer.append("Pending").append("\n");

            txtAreaMessage.setText("Leave application saved successfully.");

            // Reset form after saving
            txtStartDate.setText("");
            txtEndDate.setText("");
            txtTotalDays.setText("");
        } catch (IOException e) {
            txtAreaMessage.setText("Error saving leave application: " + e.getMessage());
        }
    }

    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
        
        /*File file = new File("leave_application.csv");
        boolean fileExists = file.exists();
        
        try (FileWriter writer = new FileWriter(file, true)) {
            // If the file doesn't exist, write the header
            if (!fileExists) {
                writer.append("Username,Leave Type,Start Date,End Date,Total Days,Status\n");
            }

            // Append the leave application data
            writer.append(employeeDetails[3])
                  .append(",")
                  .append(leaveType)
                  .append(",")
                  .append(startDate)
                  .append(",")
                  .append(endDate)
                  .append(",")
                  .append(totalDays)
                  .append(",Pending\n");

            // Set the success flag to true after successful writing
            applicationSuccess = true;

            // Clear the fields
            txtStartDate.setText("");
            txtEndDate.setText("");
            txtTotalDays.setText("");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        
        try (FileWriter writer = new FileWriter("leave_application.csv", true)) {
            writer.append(employeeDetails[3])
                  .append(",")
                  .append(leaveType)
                  .append(",")
                  .append(startDate)
                  .append(",")
                  .append(endDate)
                  .append(",")
                  .append(totalDays)
                  .append(",Pending\n");
            
             // Set the success flag to true after successful writing
            applicationSuccess = true;

            // Clear the fields
            txtStartDate.setText("");
            txtEndDate.setText("");
            txtTotalDays.setText("");
            
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Display the applied details and success/failure message in the message area
               if(applicationSuccess) {
                    lblMessage.setText(

                        "Leave applied! Check status page to know applied leave status." +"\n" +
                        "User : " + employeeDetails[3] + "\n"+
                        "Leave Type: " + leaveType + "\n"+
                        "From: " + startDate + " to " + endDate + "\n"+
                        "Total Days: " + totalDays
                    );
                } else {
                    lblMessage.setText("Leave application failed!");
                }

            // Display the applied details in the message area
            lblMessage.setText("Leave applied successfully!\n" +
                "Start Date: " + startDate + "\n" +
                "End Date: " + endDate + "\n" +
                "Total Days: " + totalDays);

            // Clear the fields
            txtStartDate.setText("");
            txtEndDate.setText("");
            txtTotalDays.setText("");

        } catch (IOException ex) {
            lblMessage.setText("Leave application failed!");
            ex.printStackTrace();
        //}*/
        
public static void main(String[] args) {
        String[] sampleEmployeeDetails = {
            "John", "Doe", "123456", "jdoe", "password", "Active", "Employee",
            "01/01/1980", "Caucasian", "Christianity", "New York", "jdoe@example.com",
            "123 Main St", "Male", "Single", "123-456-7890", "Bank XYZ", "12345678",
            "EPF12345", "TIN12345", "Jane Doe", "12345678", "Spouse", "123-456-7890",
            "5 years", "01/01/2015", "", "Software Engineer", "IT", "5000", "12",
            "6", "0", "0", "Senior Software Engineer", "01/01/2020", "Position History",
            "100", "Increment History"
        };
        //String loggedInUserID = "user123"; // Replace this with the actual logged-in user ID
        SwingUtilities.invokeLater(() -> {
            ApplyLeave frame = new ApplyLeave(sampleEmployeeDetails);
            frame.setVisible(true);
            //new ApplyLeave(sampleEmployeeDetails).setVisible(true);
    });
    }
    }


   
