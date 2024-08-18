
//fix the gui

package assignment;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ApplyLeaveGUI {
    private JFrame frame;
    private JTextField userTextField;
    private JTextArea displayArea;
    private JButton medicalLeaveButton;
    private JButton unpaidLeaveButton;
    private JButton annualLeaveButton;
    private JButton maternityLeaveButton;
    private JRadioButton femaleRadioButton;
    private JRadioButton maleRadioButton;
    private JCheckBox marriedCheckBox;
    private JTextField medicalLeaveField;
    private JTextField unpaidLeaveField;
    private JTextField annualLeaveField;
    private JTextField maternityLeaveField;
    private ApplyLeave leaveHandler;
    private List<ApplyLeave.User> users;
    
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField totalDaysField;
    private JButton confirmApplyButton;
    private JPanel datePanel;
    private ApplyLeave.User currentUser;
    private String currentLeaveType;
    private leaveApplicationCSVCreate csvFileWriter;
    
    public void createAndShowGUI(){
        leaveHandler = new ApplyLeave();
        users = CSVFileReader.readCSV("dummy_data.csv");
        
        frame = new JFrame("Apply for Leave");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 576);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        addComponents(panel);
        frame.add(panel);

        // Display the window
        frame.setVisible(true);
    }
        private void addComponents(JPanel panel) {
            JPanel mainPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);  // Padding between components
            

            JLabel nameLabel = new JLabel("Username:");
            gbc.gridx = 0;
            gbc.gridy = 0;
            mainPanel.add(nameLabel, gbc);

            userTextField = new JTextField(15);
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 2; // Span two columns
            mainPanel.add(userTextField, gbc);

            JButton loadButton = new JButton("Load User");
            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            loadButton.addActionListener(e -> loadUserDetails());
            mainPanel.add(loadButton, gbc);

            
            JLabel genderLabel = new JLabel("Gender:");
            gbc.gridx = 0;
            gbc.gridy = 1;
            mainPanel.add(genderLabel, gbc);

            femaleRadioButton = new JRadioButton("Female");
            gbc.gridx = 1;
            gbc.gridy = 1;
            mainPanel.add(femaleRadioButton, gbc);

            maleRadioButton = new JRadioButton("Male");
            gbc.gridx = 2;
            gbc.gridy = 1;
            mainPanel.add(maleRadioButton, gbc);

            ButtonGroup genderGroup = new ButtonGroup();
            genderGroup.add(femaleRadioButton);
            genderGroup.add(maleRadioButton);

            JLabel statusLabel = new JLabel("Marital Status:");
            gbc.gridx = 3;
            gbc.gridy = 1;
            mainPanel.add(statusLabel, gbc);

            marriedCheckBox = new JCheckBox("Married");
            gbc.gridx = 4;
            gbc.gridy = 1;
            mainPanel.add(marriedCheckBox, gbc);

            
            JLabel medicalLeaveLabel = new JLabel("Medical Leave Balance:");
            gbc.gridx = 0;
            gbc.gridy = 2;
            mainPanel.add(medicalLeaveLabel, gbc);

            medicalLeaveField = new JTextField(10);
            gbc.gridx = 1;
            gbc.gridy = 2;
            mainPanel.add(medicalLeaveField, gbc);

            JLabel unpaidLeaveLabel = new JLabel("Unpaid Leave Balance:");
            gbc.gridx = 3;
            gbc.gridy = 2;
            mainPanel.add(unpaidLeaveLabel, gbc);

            unpaidLeaveField = new JTextField(10);
            gbc.gridx = 4;
            gbc.gridy = 2;
            mainPanel.add(unpaidLeaveField, gbc);
            
            JLabel annualLeaveLabel = new JLabel("Annual Leave Balance:");
            gbc.gridx = 0;
            gbc.gridy = 3;
            mainPanel.add(annualLeaveLabel, gbc);

            annualLeaveField = new JTextField(10);
            gbc.gridx = 1;
            gbc.gridy = 3;
            mainPanel.add(annualLeaveField, gbc);

            JLabel maternityLeaveLabel = new JLabel("Maternity Leave Balance:");
            gbc.gridx = 3;
            gbc.gridy = 3;
            mainPanel.add(maternityLeaveLabel, gbc);

            maternityLeaveField = new JTextField(10);
            gbc.gridx = 4;
            gbc.gridy = 3;
            mainPanel.add(maternityLeaveField, gbc);

            // Add the mainPanel to the main container
            panel.add(mainPanel, BorderLayout.NORTH);

            // Center section of display
            displayArea = new JTextArea();
            displayArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(displayArea);
            panel.add(scrollPane, BorderLayout.CENTER);

            JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            medicalLeaveButton = new JButton("Apply for Medical Leave");
            unpaidLeaveButton = new JButton("Apply for Unpaid Leave");
            annualLeaveButton = new JButton("Apply for Annual Leave");
            maternityLeaveButton = new JButton("Apply for Maternity Leave");

            // Initially, all buttons are disabled
            medicalLeaveButton.setEnabled(false);
            unpaidLeaveButton.setEnabled(false);
            annualLeaveButton.setEnabled(false);
            maternityLeaveButton.setEnabled(false);

            southPanel.add(medicalLeaveButton);
            southPanel.add(unpaidLeaveButton);
            southPanel.add(annualLeaveButton);
            southPanel.add(maternityLeaveButton);

            JButton backButton = new JButton("Back");
            backButton.addActionListener(e -> goBackToHomepage());
            southPanel.add(backButton);

            panel.add(southPanel, BorderLayout.SOUTH);

            medicalLeaveButton.addActionListener(e -> showDateInputFields("Medical"));
            unpaidLeaveButton.addActionListener(e -> showDateInputFields("Unpaid"));
            annualLeaveButton.addActionListener(e -> showDateInputFields("Annual"));
            maternityLeaveButton.addActionListener(e -> showDateInputFields("Maternity"));

            datePanel = new JPanel(new GridLayout(4, 2));
            startDateField = new JTextField(10);
            endDateField = new JTextField(10);
            totalDaysField = new JTextField(10);
            confirmApplyButton = new JButton("Confirm Apply");

            datePanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
            datePanel.add(startDateField);
            datePanel.add(new JLabel("End Date (YYYY-MM-DD):"));
            datePanel.add(endDateField);
            datePanel.add(new JLabel("Total Days:"));
            datePanel.add(totalDaysField);
            datePanel.add(confirmApplyButton);

            confirmApplyButton.addActionListener(e -> confirmApplyLeave());
        }
                      
        
        private void loadUserDetails() {
            String userName = userTextField.getText();
            for (ApplyLeave.User user : users) {
                if (user.getUsername().equalsIgnoreCase(userName)) {
                    populateFields(user);
                    currentUser = user;
                    break;
                }
            }
        }
        
        private void populateFields(ApplyLeave.User user) {
            if (user.getGender().equalsIgnoreCase("Female")) {
                femaleRadioButton.setSelected(true);
            } else if (user.getGender().equalsIgnoreCase("Male")) {
                maleRadioButton.setSelected(true);
            }
            marriedCheckBox.setSelected(user.isMarried());
            medicalLeaveField.setText(String.valueOf(user.getMedicalLeaveBalance()));
            unpaidLeaveField.setText(String.valueOf(user.getUnpaidLeaveBalance()));
            annualLeaveField.setText(String.valueOf(user.getAnnualLeaveBalance()));
            maternityLeaveField.setText(String.valueOf(user.getMaternityLeaveBalance()));
            updateButtonStates(user);
        }
        
        private void updateButtonStates(ApplyLeave.User user) {
            medicalLeaveButton.setEnabled(user.getMedicalLeaveBalance() > 0);
            unpaidLeaveButton.setEnabled(user.getUnpaidLeaveBalance() > 0);
            annualLeaveButton.setEnabled(user.getAnnualLeaveBalance() > 0);
            maternityLeaveButton.setEnabled(user.canApplyForMaternityLeave());
        }
        
         private void showDateInputFields(String leaveType) {
            currentLeaveType = leaveType;
            // Remove datePanel if already added to panel
            frame.getContentPane().remove(datePanel);            

            // Add datePanel to the frame and refresh
            frame.getContentPane().add(datePanel, BorderLayout.SOUTH);
            frame.revalidate();
            frame.repaint();
        }
         
        private void confirmApplyLeave(){
            String userName = userTextField.getText();
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();
            String totalDaysStr = totalDaysField.getText();
            
            if (!isValidDate(startDate) || !isValidDate(endDate)) {
                displayArea.append("Invalid date format. Please use YYYY-MM-DD.\n");
                return;
            }
            
            int totalDays;
            try {
                totalDays = Integer.parseInt(totalDaysStr);
            } catch (NumberFormatException e) {
                displayArea.append("Please check your input and total days must be an integer.\n");
                return;
            }
            
            int leaveBalance = 0;
            switch (currentLeaveType) {
                case "Medical":
                    leaveBalance = currentUser.getMedicalLeaveBalance();
                    break;
                case "Unpaid":
                    leaveBalance = currentUser.getUnpaidLeaveBalance();
                    break;
                case "Annual":
                    leaveBalance = currentUser.getAnnualLeaveBalance();
                    break;
                case "Maternity":
                    leaveBalance = currentUser.getMaternityLeaveBalance();
                    break;
            }

        if (totalDays > leaveBalance) {
            displayArea.append("Total days applied cannot exceed the leave balance!\n");
            return;
        }
        
        csvFileWriter.writeLeaveApplication(userName, currentLeaveType, startDate, endDate, totalDays);
       
        displayArea.append("\n");
        displayArea.append("Leave applied! Check status page to know applied leave status.\n");
        displayArea.append("User: " + userName + "\n");
        displayArea.append("Leave Type: " + currentLeaveType + " Leave" + "\n");
        displayArea.append("From: " + startDate + " to " + endDate + "\n");
        displayArea.append("Total Days: " + totalDays + "\n");
        displayArea.append("\n");

        // Reset date fields and remove date panel
        startDateField.setText("");
        endDateField.setText("");
        totalDaysField.setText("");
        frame.getContentPane().remove(datePanel);
        frame.revalidate();
        frame.repaint();
        }
        
        private boolean isValidDate(String dateStr) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            try {
                sdf.parse(dateStr);
                return true;
            } catch (ParseException e) {
                return false;
            }
        }
        
        
        
        public ApplyLeaveGUI() {
            // Initialize CSVFileWriter with the path to CSV file
            csvFileWriter = new leaveApplicationCSVCreate("leave_applications.csv");
        }
        
       
        private void goBackToHomepage() {
            frame.dispose();
            new HomepageGUI().setVisible(true); //ltr change to actual homepage name
        }
        
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            // Initialize and display the GUI
            ApplyLeaveGUI gui = new ApplyLeaveGUI();
            gui.createAndShowGUI();
            });
        }
}
    
    
