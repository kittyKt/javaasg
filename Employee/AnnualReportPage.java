/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class AnnualReportPage extends JFrame {

    private String[] employeeDetails;
    private JTable reportTable;
    private int reportYear; //Store selected year

    public AnnualReportPage(String[] employeeDetails) {
        this.employeeDetails = employeeDetails;

        setTitle("Annual Report - " + employeeDetails[0] + " " + employeeDetails[1]);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Year Input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 2));

        JTextField yearField = new JTextField();
        yearField.setToolTipText("Enter the year (e.g., 2024)");

        inputPanel.add(new JLabel("Year (YYYY):"));
        inputPanel.add(yearField);

        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateAnnualReport(yearField.getText());
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(generateReportButton, BorderLayout.SOUTH);

        // Table setup
        String[] columnNames = {"Day", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 31);
        reportTable = new JTable(tableModel);

        // Setting up row numbers (days)
        for (int i = 0; i < 31; i++) {
            reportTable.setValueAt(i + 1, i, 0);
        }

        // Setting custom renderer for cell coloring
        reportTable.setDefaultRenderer(Object.class, new AttendanceCellRenderer());

        JScrollPane scrollPane = new JScrollPane(reportTable);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void generateAnnualReport(String year) {
        DefaultTableModel tableModel = (DefaultTableModel) reportTable.getModel();
        clearTable(tableModel);
        
        // Store the selected year for use in the renderer
        reportYear = Integer.parseInt(year);

        // Read and process data from the CSV file
        Path filePath = Paths.get("C:\\Users\\4hana\\OneDrive - Asia Pacific University\\( JP ) Java Programming\\homepageGUI\\homepageGUI\\src\\attendance_records.csv");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = br.readLine()) != null) {
                // Skip header
                if (line.startsWith("Username")) {
                    continue;
                }
                String[] values = line.split(",");
                
                String recordUsername = values[0];
                LocalDate date = LocalDate.parse(values[1], formatter);

                // Check if the record matches the current employee and year
                if (recordUsername.equals(employeeDetails[3]) && date.getYear() == Integer.parseInt(year)) {
                    int day = date.getDayOfMonth() - 1;
                    int month = date.getMonthValue();
                    
                    String note = (values.length > 5) ? values[5] : "";

                    // Set the note for the corresponding day and month
                    tableModel.setValueAt(note, day, month);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading attendance records.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearTable(DefaultTableModel tableModel) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 1; j < tableModel.getColumnCount(); j++) {
                tableModel.setValueAt("", i, j);
            }
        }
    }

    // Custom renderer to color cells based on leave type
    private class AttendanceCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Grey background for the date numbers in the first column
            if (column == 0) {
                setBackground(Color.LIGHT_GRAY);
                setHorizontalAlignment(CENTER);
            } else {
                // Check if the date exists
                int day = row + 1; // Days are stored as 0-indexed
                int month = column; // Months are 1-indexed in LocalDate

                if (month > 0 && !isValidDate(reportYear, month, day)) {
                    setBackground(Color.GRAY); // Grey for non-existent dates
                } else if (isWeekend(reportYear, month, day)) {
                    setBackground(Color.GRAY); // Grey for weekends
                } else {
                    // Color the attendance based on the notes
                    if (value != null && value.toString().matches("(?i)Medical Leave|Annual Leave|Unpaid Leave|Maternity Leave")) {
                        setBackground(Color.RED);
                    } else {
                        setBackground(Color.GREEN);
                    }
                }
                setHorizontalAlignment(CENTER);
            }
            return this;
        }
        
        private boolean isValidDate(int year, int month, int day) {
            YearMonth yearMonth = YearMonth.of(year, month);
            return day <= yearMonth.lengthOfMonth();
        }
        
        // Method to check if a given day is a weekend (Saturday or Sunday)
        private boolean isWeekend(int year, int month, int day) {
            if (!isValidDate(year, month, day)) {
                return false; // If the date doesn't exist, it's not a valid weekend
            }
            LocalDate date = LocalDate.of(year, month, day);
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
        }
    }
}
