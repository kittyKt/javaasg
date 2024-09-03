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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MonthlyReportPage extends JFrame {

    private Map<LocalDate, LocalTime> clockInTimes;
    private String[] employeeDetails;
    private JTextArea reportArea;
    private JTable reportTable;

    public MonthlyReportPage(Map<LocalDate, LocalTime> clockInTimes, String[] employeeDetails) {
        this.clockInTimes = clockInTimes;
        this.employeeDetails = employeeDetails;

        setTitle("Monthly Report - " + employeeDetails[0] + " " + employeeDetails[1]);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Month and Year Input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        JTextField monthField = new JTextField();
        monthField.setToolTipText("Enter the month (e.g., 01 for January)");
        JTextField yearField = new JTextField();
        yearField.setToolTipText("Enter the year (e.g., 2024)");

        inputPanel.add(new JLabel("Month (MM):"));
        inputPanel.add(monthField);
        inputPanel.add(new JLabel("Year (YYYY):"));
        inputPanel.add(yearField);

        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateMonthlyReport(monthField.getText(), yearField.getText());
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(generateReportButton, BorderLayout.SOUTH);
        
        String[] columnNames = {"Date", "Clock In Time", "Clock Out Time", "Total Hours Worked", "Notes"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        reportTable = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(reportTable);
        add(scrollPane, BorderLayout.CENTER);
        
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane reportScrollPane = new JScrollPane(reportArea);
        add(reportScrollPane, BorderLayout.EAST);

        setVisible(true);
    }

private void generateMonthlyReport(String month, String year) {
    DefaultTableModel tableModel = (DefaultTableModel) reportTable.getModel();
    tableModel.setRowCount(0);
        
    StringBuilder report = new StringBuilder();
    report.append("Monthly Report for ").append(month).append("/").append(year).append(":\n");

    // Add employee details
    report.append("Employee Name: ").append(employeeDetails[0]).append(" ").append(employeeDetails[1]).append("\n");
    report.append("Department: ").append(employeeDetails[28]).append("\n");
    report.append("Position: ").append(employeeDetails[27]).append("\n");
    
    double grossSalary = Double.parseDouble(employeeDetails[29]); // Monthly gross salary
    int lateCount = 0; // Counter for late occurrences
    
    Path filePath = Paths.get("C:\\Users\\4hana\\OneDrive - Asia Pacific University\\( JP ) Java Programming\\homepageGUI\\homepageGUI\\src\\attendance_records.csv");
    // Read and filter data from the CSV
    try (BufferedReader br = Files.newBufferedReader(filePath)) {
        String line;
        while ((line = br.readLine()) != null) {
            // Skip header
            if (line.startsWith("Username")) {
                continue;
            }
            String[] values = line.split(",");
            
            String recordUsername = values[0];
            LocalDate date = LocalDate.parse(values[1]);
            LocalTime clockInTime = LocalTime.parse(values[2]);
            LocalTime clockOutTime = LocalTime.parse(values[3]);
            
            if (recordUsername.equals(employeeDetails[3]) && 
                date.getMonthValue() == Integer.parseInt(month) && 
                date.getYear() == Integer.parseInt(year)) {

                long totalHours = java.time.Duration.between(clockInTime, clockOutTime).toHours();
                String note = (values.length > 5) ? values[5] : "";
                
                if (note.equalsIgnoreCase("Late")) {
                    lateCount++;
                }

                Object[] row = {date, clockInTime, clockOutTime, totalHours, note};
                tableModel.addRow(row);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        report.append("Error reading attendance records.");
    }
    
    // Check if there are more than 3 late occurrences
    if (lateCount > 3) {
        grossSalary -= 100; // Deduct $100 from the gross salary
    }

    // Display the adjusted salary and other report details
    report.append("Monthly gross salary: ").append(grossSalary).append("\n");
    report.append("Time Period: ").append(month).append("/").append(year).append("\n");
    report.append("Total 'Late' occurrences: ").append(lateCount).append("\n\n");

    reportArea.setText(report.toString());
}
}
