package Payroll;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.miginfocom.swing.MigLayout;

public class PayrollMain extends javax.swing.JFrame {

    private EmpList empListPanel;
    private EmpSalaryDetails empSalaryDetailsPanel;
    private EmpDetails empDetails;
    private Calculations calculations;
    private List<String[]> emplist = new ArrayList<> ();
    public static ArrayList<String> userinfoList = new ArrayList<String> ();
    private String currentMonth;
    private String selectedYear;
    private String selectedMonthNumber;

    public PayrollMain () {
        initComponents ();
        initializeMonthYear ();

        loadEmpData ();

        List<String[]> emptyData = new ArrayList<> ();
        empListPanel = new EmpList (emptyData);

        CSVReader csvReader = new CSVReader ();
        List<String[]> allEmployees = csvReader.findEmployeeByNameOrNRIC ("");
        empListPanel.loadEmployeeData (allEmployees);

        empSalaryDetailsPanel = new EmpSalaryDetails ("", this);
        calculations = new Calculations ();

        EmployeeListPanel.setLayout (new MigLayout ());
        EmployeeListPanel.add (empListPanel, "wrap");
        SalaryDetailsPanel.setLayout (new BorderLayout ());
        SalaryDetailsPanel.add (empSalaryDetailsPanel, BorderLayout.CENTER);
        setupListeners ();
        initializePage ();
    }

    private void initializeMonthYear () {
        Calendar now = Calendar.getInstance ();
        int currentMonthIndex = now.get (Calendar.MONTH);
        int currentYear = now.get (Calendar.YEAR);

        String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };

        String[] years = {
            "2023", "2024"
        };

        DefaultComboBoxModel<String> monthModel = new DefaultComboBoxModel<> ();
        DefaultComboBoxModel<String> yearModel = new DefaultComboBoxModel<> ();

         selectedMonthNumber = MonthConverter.getMonthNumber(months[currentMonthIndex]);
        selectedYear = String.valueOf(currentYear);

        // Populate JComboBox models
        for (String month : months) {
            monthModel.addElement(month);
        }

        for (String year : years) {
            yearModel.addElement(year);
        }

        // Set default selections
        Month.setModel(monthModel);
        Month.setSelectedItem(months[currentMonthIndex]);
        Year.setModel(yearModel);
        Year.setSelectedItem(String.valueOf(currentYear));

        // Add action listeners
        Month.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentMonth = (String) Month.getSelectedItem();
                selectedMonthNumber = MonthConverter.getMonthNumber(currentMonth);
                System.out.println("Selected Month: " + currentMonth);
                System.out.println("Selected Month Number: " + selectedMonthNumber);
            }
        });

        Year.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedYear = (String) Year.getSelectedItem();
                System.out.println("Selected Year: " + selectedYear);
            }
        });
    }

    private void loadEmpData () {
        try {
            List<String> lines = Files.readAllLines (Paths.get ("C:\\Users\\Hiew\\Documents\\NetBeansProjects\\Assignment1\\src\\main\\java\\csv\\Employees_DetailsCSV.csv"));
            if (lines.isEmpty ()) {
                System.out.println ("CSV file is empty.");
                return;
            }

            if (lines.size () > 0) {
                lines.remove (0);
            }

            for (String line : lines) {
                String[] emp = line.split (",");

                if (emp.length >= 6) {
                    emplist.add (emp);
                } else {
                    System.out.println ("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println ("Error reading CSV file: " + e.getMessage ());
            e.printStackTrace ();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EmpDetailsPanel = new javax.swing.JPanel();
        EmployeeListPanel = new javax.swing.JPanel();
        SearchEmp = new javax.swing.JTextField();
        SearchButton = new javax.swing.JButton();
        SalaryDetailsPanel = new javax.swing.JPanel();
        HomeButton = new javax.swing.JButton();
        SalaryReportButton = new javax.swing.JButton();
        Month = new javax.swing.JComboBox<>();
        Year = new javax.swing.JComboBox<>();
        LoadButton = new javax.swing.JButton();
        GeneratePaySlipButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout EmpDetailsPanelLayout = new javax.swing.GroupLayout(EmpDetailsPanel);
        EmpDetailsPanel.setLayout(EmpDetailsPanelLayout);
        EmpDetailsPanelLayout.setHorizontalGroup(
            EmpDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );
        EmpDetailsPanelLayout.setVerticalGroup(
            EmpDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout EmployeeListPanelLayout = new javax.swing.GroupLayout(EmployeeListPanel);
        EmployeeListPanel.setLayout(EmployeeListPanelLayout);
        EmployeeListPanelLayout.setHorizontalGroup(
            EmployeeListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        EmployeeListPanelLayout.setVerticalGroup(
            EmployeeListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        SearchEmp.setText("Search Employee Name or NRIC");
        SearchEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchEmpKeyPressed(evt);
            }
        });

        SearchButton.setText("Search");
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SalaryDetailsPanelLayout = new javax.swing.GroupLayout(SalaryDetailsPanel);
        SalaryDetailsPanel.setLayout(SalaryDetailsPanelLayout);
        SalaryDetailsPanelLayout.setHorizontalGroup(
            SalaryDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        SalaryDetailsPanelLayout.setVerticalGroup(
            SalaryDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        HomeButton.setText("Home");
        HomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeButtonActionPerformed(evt);
            }
        });

        SalaryReportButton.setText("Salary Report");
        SalaryReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalaryReportButtonActionPerformed(evt);
            }
        });

        Month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        Year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2024" }));

        LoadButton.setText("Load");
        LoadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadButtonActionPerformed(evt);
            }
        });

        GeneratePaySlipButton.setText("Generate PaySlip");
        GeneratePaySlipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GeneratePaySlipButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(EmpDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SearchEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SearchButton)
                                .addGap(18, 18, 18)
                                .addComponent(Month, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addComponent(SalaryReportButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HomeButton))
                            .addComponent(SalaryDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE))
                    .addComponent(EmployeeListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(LoadButton)
                        .addGap(18, 18, 18)
                        .addComponent(GeneratePaySlipButton)
                        .addGap(39, 39, 39)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SearchButton, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(SearchEmp)
                            .addComponent(Year)
                            .addComponent(Month)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(SalaryReportButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(HomeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))))
                        .addGap(12, 12, 12)
                        .addComponent(SalaryDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(EmpDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoadButton)
                    .addComponent(GeneratePaySlipButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EmployeeListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SearchEmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchEmpKeyPressed
        if (evt.getKeyCode () == KeyEvent.VK_ENTER) {
            searchEmployee ();
        }
    }//GEN-LAST:event_SearchEmpKeyPressed

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        searchEmployee ();
    }//GEN-LAST:event_SearchButtonActionPerformed

    private void SalaryReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalaryReportButtonActionPerformed
        SalaryReport salaryReport = new SalaryReport ();
        salaryReport.setVisible (true);
        this.dispose ();
    }//GEN-LAST:event_SalaryReportButtonActionPerformed

    private void LoadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadButtonActionPerformed
        if (userinfoList.isEmpty ()) {
            JOptionPane.showMessageDialog (this, "Please select a row before loading details.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        EmpDetails empDetailsPanel = new EmpDetails (userinfoList);
        EmpSalaryDetails empSalaryDetailsPanel = new EmpSalaryDetails (userinfoList, this);

        try {
            double grossSalary = Double.parseDouble (userinfoList.get (12));
            empSalaryDetailsPanel.getCalculations ().setGsalary (grossSalary);
            empSalaryDetailsPanel.updateSalaryDetails ();
            empSalaryDetailsPanel.updateEmpData (userinfoList);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog (this, "Invalid salary data for the user.");
        }
        EmpDetailsPanel.removeAll ();
        EmpDetailsPanel.setLayout (new BorderLayout ());
        EmpDetailsPanel.add (empDetailsPanel, BorderLayout.CENTER);
        EmpDetailsPanel.revalidate ();
        EmpDetailsPanel.repaint ();

        SalaryDetailsPanel.removeAll ();
        SalaryDetailsPanel.setLayout (new BorderLayout ());
        SalaryDetailsPanel.add (empSalaryDetailsPanel, BorderLayout.CENTER);
        SalaryDetailsPanel.revalidate ();
        SalaryDetailsPanel.repaint ();

        empSalaryDetailsPanel.recalculate ();
    }//GEN-LAST:event_LoadButtonActionPerformed

    private void HomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeButtonActionPerformed
        Homepage homepage = new Homepage ();
        homepage.setVisible (true);
        this.dispose ();
    }//GEN-LAST:event_HomeButtonActionPerformed

    private void GeneratePaySlipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GeneratePaySlipButtonActionPerformed
        EmpDetails empDetailsPanel = (EmpDetails) EmpDetailsPanel.getComponent (0);

        if (empDetailsPanel != null) {

            String nric = empDetailsPanel.getNRIC ();

            if (nric != null && !nric.trim ().isEmpty ()) {
                saveDetailsToCSV (empDetailsPanel);
                PaySlip paySlip = new PaySlip (nric);
                paySlip.setVisible (true);
            } else {
                JOptionPane.showMessageDialog (this, "No employee selected. Please search and select an employee first.");
            }
        } else {
            JOptionPane.showMessageDialog (this, "Employee details not loaded. Please search and select an employee first.");
        }
    }//GEN-LAST:event_GeneratePaySlipButtonActionPerformed

    private void showEmployeeDetails (String nric) {

        EmpDetails empDetailsPanel = new EmpDetails (nric);

        EmpDetailsPanel.removeAll ();
        EmpDetailsPanel.setLayout (new BorderLayout ());
        EmpDetailsPanel.add (empDetailsPanel, BorderLayout.CENTER);
        EmpDetailsPanel.revalidate ();
        EmpDetailsPanel.repaint ();
    }

    private List<DataSearch> search (String search) {
        int limitData = 7;
        List<DataSearch> result = new ArrayList<> ();
        Set<String> uniqueIdentifiers = new HashSet<> ();

        if (search == null || search.trim ().isEmpty ()) {
            return result;
        }

        String lowerCaseText = search.trim ().toLowerCase ();

        for (String[] emp : emplist) {
            String firstName = emp[0].toLowerCase ();
            String lastName = emp[1].toLowerCase ();
            String nric = emp[2].toLowerCase ();

            if (firstName.contains (lowerCaseText)
                    || lastName.contains (lowerCaseText)
                    || nric.contains (lowerCaseText)) {

                String uniqueIdentifier = nric;
                if (!uniqueIdentifiers.contains (uniqueIdentifier)) {
                    uniqueIdentifiers.add (uniqueIdentifier);
                    result.add (new DataSearch (nric, firstName, lastName));
                    if (result.size () == limitData) {
                        break;
                    }
                }
            }
        }

        return result;
    }

    private void setupListeners () {
        SearchButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent evt) {
                searchEmployee ();
            }
        });
    }

    private void initializePage () {
        SearchEmp.setText ("");
        EmpDetailsPanel.removeAll ();
        EmpDetailsPanel.setLayout (new BorderLayout ());
        EmpDetailsPanel.add (new EmpDetails (""), BorderLayout.CENTER);
        EmpDetailsPanel.revalidate ();
        EmpDetailsPanel.repaint ();
    }

    private void searchEmployee () {
        String query = SearchEmp.getText ().trim ();
        CSVReader csvReader = new CSVReader ();
        List<String[]> results = csvReader.findEmployeeByNameOrNRIC (query);
        String searchText = SearchEmp.getText ();

        if (!searchText.isEmpty ()) {
            List<DataSearch> matches = search (searchText);
            if (matches.size () > 0) {
                DataSearch firstMatch = matches.get (0);
                showEmployeeDetails (firstMatch.getNric ());
            }
        }

        if (!results.isEmpty ()) {
            empListPanel.updateEmployeeList (results, query);

            if (results.size () == 1) {
                String[] result = results.get (0);
                EmpDetails empDetailsPanel = new EmpDetails (result[2]);
                EmpDetailsPanel.removeAll ();
                EmpDetailsPanel.setLayout (new BorderLayout ());
                EmpDetailsPanel.add (empDetailsPanel, BorderLayout.CENTER);
                EmpDetailsPanel.revalidate ();
                EmpDetailsPanel.repaint ();

                try {
                    double grossSalary = Double.parseDouble (result[29]);
                    empSalaryDetailsPanel.getCalculations ().setGsalary (grossSalary);
                    empSalaryDetailsPanel.updateSalaryDetails ();
                    empSalaryDetailsPanel.updateEmpData (result[2]);

                    SalaryDetailsPanel.removeAll ();
                    SalaryDetailsPanel.setLayout (new BorderLayout ());
                    SalaryDetailsPanel.add (empSalaryDetailsPanel, BorderLayout.CENTER);
                    SalaryDetailsPanel.revalidate ();
                    SalaryDetailsPanel.repaint ();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog (this, "Invalid salary data for the user.");
                }
            }
        } else {
            JOptionPane.showMessageDialog (this, "Employee not found!");
            empListPanel.revalidate ();
            empListPanel.repaint ();
            EmpDetailsPanel.revalidate ();
            EmpDetailsPanel.repaint ();
        }
    }

    private void saveDetailsToCSV (EmpDetails empDetailsPanel) {
        String filePath = "C:\\Users\\Hiew\\Documents\\NetBeansProjects\\Assignment1\\src\\main\\java\\csv\\PaySlipCSV.csv";
        String month = (String) Month.getSelectedItem ();
        String year = (String) Year.getSelectedItem ();

        try (FileWriter writer = new FileWriter (filePath, true)) {
            String[] empData = empDetailsPanel.getEmployeeData ();

            EmpSalaryDetails empSalaryDetailsPanel = (EmpSalaryDetails) SalaryDetailsPanel.getComponent (0);
            String[] salaryData = empSalaryDetailsPanel != null ? empSalaryDetailsPanel.getSalaryData () : new String[0];

            String[] combinedData = new String[empData.length + salaryData.length];
            System.arraycopy (empData, 0, combinedData, 0, empData.length);
            System.arraycopy (salaryData, 0, combinedData, empData.length, salaryData.length);

            if (combinedData.length > 0) {
                String csvLine = year + "," + month + "," + String.join (",", combinedData);
                writer.append (csvLine).append ("\n");
            }

            writer.flush ();
            JOptionPane.showMessageDialog (this, "Pay slip generated and data saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog (this, "Error saving data: " + e.getMessage ());
            e.printStackTrace ();
        }
    }

    public String getCurrentMonth () {
        return currentMonth;
    }

    public String getCurrentMonthNumber () {
        System.out.println ("month no: "+selectedMonthNumber);
        return selectedMonthNumber;
    }

    public String getCurrentYear () {
        System.out.println ("get current year: "+selectedYear);
        return selectedYear;
    }

    public static void main (String args[]) {

        java.awt.EventQueue.invokeLater (new Runnable () {
            public void run () {
                new PayrollMain ().setVisible (true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EmpDetailsPanel;
    private javax.swing.JPanel EmployeeListPanel;
    private javax.swing.JButton GeneratePaySlipButton;
    private javax.swing.JButton HomeButton;
    private javax.swing.JButton LoadButton;
    private javax.swing.JComboBox<String> Month;
    private javax.swing.JPanel SalaryDetailsPanel;
    private javax.swing.JButton SalaryReportButton;
    private javax.swing.JButton SearchButton;
    private javax.swing.JTextField SearchEmp;
    private javax.swing.JComboBox<String> Year;
    // End of variables declaration//GEN-END:variables
}
