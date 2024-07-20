//gender gui need to rearrange
// need to add user must type in for that 3 column if not cannot continue (done but now need to change output msg when this happen

// now oni need to check if back button can go back to homepage or not

package assignment;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.List;
//import java.util.Map;
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.io.FileWriter;
//import java.io.IOException;

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
        frame.setSize(400, 500);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);
        
        addComponents(panel);

        // Display the window
        frame.setVisible(true);
    }
        private void addComponents(JPanel panel) {
            // section for user details
            JPanel northPanel = new JPanel(new GridLayout(5, 2));

            JLabel nameLabel = new JLabel("Username:");
            userTextField = new JTextField(15);
            northPanel.add(nameLabel);
            northPanel.add(userTextField);
            
            // button to load user detail after typing username
            JButton loadButton = new JButton("Load User");
            loadButton.addActionListener(e -> loadUserDetails());
            northPanel.add(loadButton);
            
            JLabel genderLabel = new JLabel("Gender:");
            ButtonGroup genderGroup = new ButtonGroup();
            femaleRadioButton = new JRadioButton("Female");
            maleRadioButton = new JRadioButton("Male");
            genderGroup.add(femaleRadioButton);
            genderGroup.add(maleRadioButton);
            northPanel.add(genderLabel);
            northPanel.add(femaleRadioButton);
            northPanel.add(maleRadioButton);
        
            JLabel statusLabel = new JLabel("Marital Status:");
            marriedCheckBox = new JCheckBox("Married");
            northPanel.add(statusLabel);
            northPanel.add(marriedCheckBox);

            JLabel medicalLeaveLabel = new JLabel("Medical Leave Balance:");
            medicalLeaveField = new JTextField(5);
            northPanel.add(medicalLeaveLabel);
            northPanel.add(medicalLeaveField);

            JLabel unpaidLeaveLabel = new JLabel("Unpaid Leave Balance:");
            unpaidLeaveField = new JTextField(5);
            northPanel.add(unpaidLeaveLabel);
            northPanel.add(unpaidLeaveField);

            JLabel annualLeaveLabel = new JLabel("Annual Leave Balance:");
            annualLeaveField = new JTextField(5);
            northPanel.add(annualLeaveLabel);
            northPanel.add(annualLeaveField);

            JLabel maternityLeaveLabel = new JLabel("Maternity Leave Balance:");
            maternityLeaveField = new JTextField(5);
            northPanel.add(maternityLeaveLabel);
            northPanel.add(maternityLeaveField);
            
            panel.add(northPanel, BorderLayout.NORTH);
            
            //center section of display
            displayArea = new JTextArea();
            displayArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(displayArea);
            panel.add(scrollPane, BorderLayout.CENTER);
            
            //button at the bottom
            medicalLeaveButton = new JButton("Apply for Medical Leave");
            unpaidLeaveButton = new JButton("Apply for Unpaid Leave");
            annualLeaveButton = new JButton("Apply for Annual Leave");
            maternityLeaveButton = new JButton("Apply for Maternity Leave");
            
            //Initially all button is not available
            medicalLeaveButton.setEnabled(false);
            unpaidLeaveButton.setEnabled(false);
            annualLeaveButton.setEnabled(false);
            maternityLeaveButton.setEnabled(false);
            
            JPanel southPanel = new JPanel(new GridLayout(3, 2));
            southPanel.add(medicalLeaveButton);
            southPanel.add(unpaidLeaveButton);
            southPanel.add(annualLeaveButton);
            southPanel.add(maternityLeaveButton);
            
            JButton backButton = new JButton("Back");
            //backButton.addActionListener(e -> goBackToHomepage());
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
            //maternityLeaveButton.setEnabled(user.canApplyForMaternityLeave(user));
            //original ver
            //maternityLeaveButton.setEnabled(leaveHandler.canApplyForMaternityLeave(user));
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
            // Initialize CSVFileWriter with the path to your CSV file
            csvFileWriter = new leaveApplicationCSVCreate("leave_applications.csv");
        }
        
        
        //for now homepage doesnt work yet
        //to go back to homepage
        private void goBackToHomepage() {
            frame.dispose();
            new HomepageGUI();
        }
        
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            // Initialize and display the GUI
            ApplyLeaveGUI gui = new ApplyLeaveGUI();
            gui.createAndShowGUI();
            });
        }
}
    
    
