package Employees;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class TimeManagementPage extends JFrame {

    private Map<LocalDate, LocalTime> clockInTimes = new HashMap<>();
    private JTextArea reportArea;
    private static final LocalTime USUAL_START_TIME = LocalTime.of(9, 0); // 9:00 AM
    private static final LocalTime LATE_THRESHOLD = LocalTime.of(9, 30); // 9:30 AM
    private String[] employeeDetails;
    private String csvFilePath = "C:\\Users\\acer\\Documents\\NetBeansProjects\\JavaAsg\\src\\csv\\attendance_records.csv"; // CSV file path
    private Map<LocalDate, String> notesMap = new HashMap<>(); // To store notes like "Late"

    public TimeManagementPage(String[] employeeDetails) {
        this.employeeDetails = employeeDetails;
  
        setTitle("Time Management - " + employeeDetails[0] + " " + employeeDetails[1]);
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

      
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(5, 1, 10, 10));


        JButton clockInButton = new JButton("Clock In");
        clockInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockIn();
            }
        });
        actionPanel.add(clockInButton);


        JButton clockOutButton = new JButton("Clock Out");
        clockOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockOut();
            }
        });
        actionPanel.add(clockOutButton);

 
        JButton monthlyReportButton = new JButton("Check Monthly Report");
        monthlyReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMonthlyReport();
            }
        });
        actionPanel.add(monthlyReportButton);

 
        JButton annualReportButton = new JButton("Check Annual Report");
        annualReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAnnualReport();
            }
        });
        actionPanel.add(annualReportButton);

 
        JButton backButton = new JButton("Back to Main Page");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeePage employeePage = new EmployeePage(employeeDetails);
                employeePage.setVisible(true);
                TimeManagementPage.this.dispose();
            }
        });
        actionPanel.add(backButton);


        add(actionPanel, BorderLayout.CENTER);


        reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        add(scrollPane, BorderLayout.SOUTH);


        setVisible(true);
    }

    private void clockIn() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        if (!clockInTimes.containsKey(today)) {
            clockInTimes.put(today, now);


            if (now.isAfter(LATE_THRESHOLD)) {
                notesMap.put(today, "Late");
                reportArea.setText("Clocked in at " + now + " (Late)");
            } else {
                reportArea.setText("Clocked in at " + now);
            }
        } else {
            reportArea.setText("Already clocked in today at " + clockInTimes.get(today));
        }
    }

    private void clockOut() {
        LocalDate today = LocalDate.now();
        if (clockInTimes.containsKey(today)) {
            LocalTime clockInTime = clockInTimes.get(today);
            LocalTime now = LocalTime.now();
            long totalHoursWorked = clockInTime.until(now, java.time.temporal.ChronoUnit.HOURS);

            reportArea.setText("Clocked out. Total hours worked: " + totalHoursWorked + " hours.");


            saveAttendanceRecord(today, clockInTime, now, totalHoursWorked);
        } else {
            reportArea.setText("You have not clocked in today.");
        }
    }
    
    private void saveAttendanceRecord(LocalDate date, LocalTime clockInTime, LocalTime clockOutTime, long totalHoursWorked) {
        String username = employeeDetails[3]; 
        String note = notesMap.getOrDefault(date, "");

        try (FileWriter writer = new FileWriter(csvFilePath, true)) {
            
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");


            String record = String.format("%s,%s,%s,%s,%d,%s\n",
                    username,
                    date.format(dateFormatter),
                    clockInTime.format(timeFormatter),
                    clockOutTime.format(timeFormatter),
                    totalHoursWorked,
                    note);


            writer.write(record);


            reportArea.append("\nAttendance record saved.");
        } catch (IOException e) {
            e.printStackTrace();
            reportArea.append("\nError saving attendance record.");
        }
    }

    private void showMonthlyReport() {
        new MonthlyReportPage(clockInTimes, employeeDetails);
    }

    private void showAnnualReport() {
        new AnnualReportPage(employeeDetails);
    }


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

        SwingUtilities.invokeLater(() -> new TimeManagementPage(sampleEmployeeDetails));
    }
}





