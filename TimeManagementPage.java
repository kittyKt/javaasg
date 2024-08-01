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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class TimeManagementPage extends JFrame {

    private Map<LocalDate, LocalTime> clockInTimes = new HashMap<>();
    private JTextArea reportArea;
    private static final LocalTime LATE_THRESHOLD = LocalTime.of(8, 0); // 8:00 AM

    public TimeManagementPage() {
        // Set up the frame
        setTitle("Time Management");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel for actions
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(6, 1, 10, 10));

        // Clock In Button
        JButton clockInButton = new JButton("Clock In");
        clockInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockIn();
            }
        });
        actionPanel.add(clockInButton);

        // Clock Out Button
        JButton clockOutButton = new JButton("Clock Out");
        clockOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockOut();
            }
        });
        actionPanel.add(clockOutButton);

        // Check Monthly Report Button
        JButton monthlyReportButton = new JButton("Check Monthly Report");
        monthlyReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMonthlyReport();
            }
        });
        actionPanel.add(monthlyReportButton);

        // Check Annual Report Button
        JButton annualReportButton = new JButton("Check Annual Report");
        annualReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAnnualReport();
            }
        });
        actionPanel.add(annualReportButton);

        // Calculate Penalty Button
        JButton calculatePenaltyButton = new JButton("Calculate Penalty");
        calculatePenaltyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatePenalty();
            }
        });
        actionPanel.add(calculatePenaltyButton);

        // Back Button
        JButton backButton = new JButton("Back to Main Page");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeePage employeePage = new EmployeePage();
                employeePage.setVisible(true);
                TimeManagementPage.this.dispose(); // Close the TimeManagementPage window
            }
        });
        actionPanel.add(backButton);

        // Add the action panel to the frame
        add(actionPanel, BorderLayout.CENTER);

        // Report area
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        add(scrollPane, BorderLayout.SOUTH);

        // Set visibility
        setVisible(true);
    }

    private void clockIn() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        if (!clockInTimes.containsKey(today)) {
            clockInTimes.put(today, now);
            reportArea.setText("Clocked in at " + now);
        } else {
            reportArea.setText("Already clocked in today at " + clockInTimes.get(today));
        }
    }

    private void clockOut() {
        LocalDate today = LocalDate.now();
        if (clockInTimes.containsKey(today)) {
            reportArea.setText("Clocked out. Total hours worked: " + clockInTimes.get(today).until(LocalTime.now(), java.time.temporal.ChronoUnit.HOURS) + " hours.");
        } else {
            reportArea.setText("You have not clocked in today.");
        }
    }

    private void showMonthlyReport() {
        StringBuilder report = new StringBuilder("Monthly Report:\n");
        LocalDate now = LocalDate.now();

        for (Map.Entry<LocalDate, LocalTime> entry : clockInTimes.entrySet()) {
            LocalDate date = entry.getKey();
            LocalTime time = entry.getValue();
            if (date.getMonth() == now.getMonth() && date.getYear() == now.getYear()) {
                report.append(date).append(" - Clocked In: ").append(time)
                      .append(time.isAfter(LATE_THRESHOLD) ? " (Late)" : "").append("\n");
            }
        }

        reportArea.setText(report.toString());
    }

    private void showAnnualReport() {
        StringBuilder report = new StringBuilder("Annual Report:\n");

        for (Map.Entry<LocalDate, LocalTime> entry : clockInTimes.entrySet()) {
            LocalDate date = entry.getKey();
            LocalTime time = entry.getValue();
            report.append(date).append(" - Clocked In: ").append(time)
                  .append(time.isAfter(LATE_THRESHOLD) ? " (Late)" : "").append("\n");
        }

        reportArea.setText(report.toString());
    }

    private void calculatePenalty() {
        LocalDate now = LocalDate.now();
        int lateDays = 0;

        for (Map.Entry<LocalDate, LocalTime> entry : clockInTimes.entrySet()) {
            LocalDate date = entry.getKey();
            LocalTime time = entry.getValue();
            if (date.getMonth() == now.getMonth() && date.getYear() == now.getYear() && time.isAfter(LATE_THRESHOLD)) {
                lateDays++;
            }
        }

        int penalty = (lateDays >= 3) ? 100 : 0;
        reportArea.setText("Penalty for the month: RM" + penalty);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TimeManagementPage();
            }
        });
    }
}





