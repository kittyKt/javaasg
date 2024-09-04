package Payroll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EmpSalaryDetails extends javax.swing.JPanel {

    private Calculations calculations;
    private CSVReader csvReader;
    private PayrollMain payrollMain;
    private double dailyRate;

    public EmpSalaryDetails (ArrayList<String> infoList, PayrollMain payrollMain) {
        initComponents ();
        initCustomListeners ();
        this.calculations = new Calculations ();
        this.payrollMain = payrollMain;
        updateEmpData (infoList);
    }

    public EmpSalaryDetails (String employeeNRIC, PayrollMain payrollMain) {
        initComponents ();
        initCustomListeners ();
        this.calculations = new Calculations ();
        this.payrollMain = payrollMain;
        if (employeeNRIC != null && !employeeNRIC.trim ().isEmpty ()) {
            updateEmpData (employeeNRIC);
        }
    }

    public Calculations getCalculations () {
        return calculations;
    }

    public void updateEmpData (ArrayList<String> infoList) {

        clearTextFields ();

        String employeeNRIC = infoList.get (2);
        CSVReader csvReader = new CSVReader ();
        String[] employeeData = csvReader.getEmployeeDetails (employeeNRIC);

        if (employeeData != null) {
            updateSalaryDetails ();
            EpfAccNo.setText (infoList.get (10));
            TIN.setText (infoList.get (11));
            GrossSalary.setText (String.format ("%.2f", Double.parseDouble (infoList.get (12))));
            UnpaidLeaveCount.setText (employeeData[33]);

            String username = getUsernameByNRIC (employeeNRIC);
            if (username != null) {
                System.out.println ("count 2:"+payrollMain.getCurrentMonthNumber ());
                int lateDaysCount = csvReader.countLateDays (username, payrollMain.getCurrentMonthNumber (), payrollMain.getCurrentYear ());
                LateDays.setText(String.valueOf(lateDaysCount));
                System.out.println ("Late Days Count 1: " + lateDaysCount);
                System.out.println ("count 2:"+payrollMain.getCurrentMonthNumber ());
                // Display or use lateDaysCount as needed
            } else {
                System.out.println ("Username not found for NRIC: " + employeeNRIC);
            }

            calculateAndSetDailyRate ();

        }
    }

    public void updateEmpData (String employeeNRIC) {

        clearTextFields ();
        CSVReader csvReader = new CSVReader ();
        String[] employeeData = csvReader.getEmployeeDetails (employeeNRIC);

        if (employeeData != null) {
            updateSalaryDetails ();
            EpfAccNo.setText (employeeData[18]);
            TIN.setText (employeeData[19]);
            GrossSalary.setText (employeeData[29]);
            UnpaidLeaveCount.setText (employeeData[33]);
            String username = getUsernameByNRIC (employeeNRIC);
            

            if (username != null) {
                int lateDaysCount = csvReader.countLateDays (username, payrollMain.getCurrentMonthNumber (), payrollMain.getCurrentYear ());
                LateDays.setText(String.valueOf(lateDaysCount));
                System.out.println ("count 2:"+payrollMain.getCurrentMonthNumber ());
                System.out.println ("Late Days Count 2: " + lateDaysCount);
                // Display or use lateDaysCount as needed
            } else {
                System.out.println ("Username not found for NRIC: " + employeeNRIC);
            }

            calculateAndSetDailyRate ();
        }
    }

    private void clearTextFields () {
        EpfAccNo.setText ("");
        TIN.setText ("");
        GrossSalary.setText ("");
        UnpaidLeaveCount.setText ("");
        LateDays.setText ("");
        UnpaidLeaveDeduct.setText ("");
        LateDeduct.setText ("");
        HoursDaysDeduction.setText ("");
        TotalDeduction.setText ("");
        NettIncome.setText ("");
        EmployerEPF.setText ("");
        EmployerSocso.setText ("");
        EmployerEIS.setText ("");
        EmployerAnnTax.setText ("");
        EmployeeEPF.setText ("");
        EmployeeSocso.setText ("");
        EmployeeEIS.setText ("");
        EmployeeAnnTax.setText ("");
        PCB.setText ("");
    }

    void updateSalaryDetails () {
        double yeeepf = calculations.calculateEPFEmployeeContribution ();
        double yeesocso = calculations.calculateSOCSOEmployeeContribution ();
        double yeeeis = calculations.calculateEISEmployeeContribution ();
        double yerepf = calculations.calculateEPFEmployerContribution ();
        double yersocso = calculations.calculateSOCSOEmployerContribution ();
        double yereis = calculations.calculateEISEmployerContribution ();
        double annualTax = calculations.calculateAnnualTax ();
        double pcb = calculations.calculateAnnualTax () / 12;
        double netSalary = calculations.calculateNetSalary ();
        double totalDeduction = calculations.calculateTotalDeduction ();

        // Update the JTextFields with calculated values
        EmployerEPF.setText (String.format ("%.2f", yerepf));
        EmployerSocso.setText (String.format ("%.2f", yersocso));
        EmployerEIS.setText (String.format ("%.2f", yereis));
        EmployerAnnTax.setText ("0.00");
        EmployeeEPF.setText (String.format ("%.2f", yeeepf));
        EmployeeSocso.setText (String.format ("%.2f", yeesocso));
        EmployeeEIS.setText (String.format ("%.2f", yeeeis));
        EmployeeAnnTax.setText (String.format ("%.2f", annualTax));
        PCB.setText (String.format ("%.2f", pcb));
        NettIncome.setText (String.format ("%.2f", netSalary));
        TotalDeduction.setText (String.format ("%.2f", totalDeduction));
    }

    private void initCustomListeners () {
        UnpaidLeaveCount.addActionListener (evt -> calculateDeductions ());
        LateDays.addActionListener (evt -> calculateDeductions ());
        addDocumentListeners (UnpaidLeaveCount, LateDays);
    }

    private void calculateDeductions () {
        try {
            int unpaidLeaveDays = Integer.parseInt (UnpaidLeaveCount.getText ());
            int lateDays = getFieldValue (LateDays);

            String currentMonth = payrollMain.getCurrentMonth ();
            int numberOfDaysInMonth = getDaysInMonth (currentMonth);

            String currentYear = payrollMain.getCurrentYear ();

            double unpaidLeaveDeduction = calculateUnpaidLeaveDeduction (unpaidLeaveDays, numberOfDaysInMonth);
            double lateDeduction = (lateDays >= 3) ? 100.0 : 0.0;
            double totalHDDeduction = unpaidLeaveDeduction + lateDeduction;

            UnpaidLeaveDeduct.setText (String.format ("%.2f", unpaidLeaveDeduction));
            LateDeduct.setText (String.format ("%.2f", lateDeduction));
            HoursDaysDeduction.setText (String.format ("%.2f", totalHDDeduction));

            double totalDeduction = totalHDDeduction + calculations.calculateTotalDeduction ();
            calculateNetIncome (totalDeduction);
        } catch (NumberFormatException e) {
            System.out.println ("Invalid input, please enter valid numbers for leave and late days.");
        }
    }

    private int getDaysInMonth (String month) {
        Map<String, Integer> monthDays = new HashMap<> ();
        monthDays.put ("January", 31);
        monthDays.put ("February", 28);
        monthDays.put ("March", 31);
        monthDays.put ("April", 30);
        monthDays.put ("May", 31);
        monthDays.put ("June", 30);
        monthDays.put ("July", 31);
        monthDays.put ("August", 31);
        monthDays.put ("September", 30);
        monthDays.put ("October", 31);
        monthDays.put ("November", 30);
        monthDays.put ("December", 31);

        return monthDays.getOrDefault (month, 30);
    }

    private double calculateUnpaidLeaveDeduction (int unpaidLeaveDays, int numberOfDaysInMonth) {
        double dailyRate = Double.parseDouble (GrossSalary.getText ()) / numberOfDaysInMonth;
        return dailyRate * unpaidLeaveDays;
    }

    private void calculateAndSetDailyRate () {
        try {
            double grossSalary = Double.parseDouble (GrossSalary.getText ());
            String currentMonth = payrollMain.getCurrentMonth ();
            int daysInMonth = getDaysInMonth (currentMonth);

            dailyRate = grossSalary / daysInMonth;

            DailyRateLabel.setText (String.format ("%.2f /day", dailyRate));
        } catch (NumberFormatException e) {
            System.out.println ("Invalid input for gross salary.");
            DailyRateLabel.setText ("N/A");
            dailyRate = 0;
        }
    }

    private int getFieldValue (JTextField textField) {
        String text = textField.getText ().trim ();
        return text.isEmpty () ? 0 : Integer.parseInt (text);
    }

    private void calculateNetIncome (double totalDeduction) {
        try {
            double grossSalary = Double.parseDouble (GrossSalary.getText ());

            double netIncome = grossSalary - totalDeduction;

            NettIncome.setText (String.format ("%.2f", netIncome));

            TotalDeduction.setText (String.format ("%.2f", totalDeduction));
        } catch (NumberFormatException e) {
            System.out.println ("Invalid input for gross salary.");
        }
    }

    public void recalculate () {
        updateSalaryDetails ();
    }

    public String getUsernameByNRIC (String employeeNRIC) {
        CSVReader csvReader = new CSVReader ();
        String[] employeeData = csvReader.getEmployeeDetails (employeeNRIC);
        System.out.println (employeeNRIC);

        if (employeeData != null && employeeData.length > 0) {
            String nric = employeeData[2];
            System.out.println (nric);
            if (nric.equals (employeeNRIC)) {
                return employeeData[3];
            }
        }
        return null;
    }

    public void updateLateDaysCount (String username) {
        String selectedMonthNumber = payrollMain.getCurrentMonthNumber ();
        String currentYear = payrollMain.getCurrentYear ();
        int lateDaysCount = csvReader.countLateDays (username, selectedMonthNumber, currentYear);
        System.out.println ("count 0:"+payrollMain.getCurrentMonthNumber ());
        System.out.println ("Late Days Count: " + lateDaysCount);
    }

    private void addDocumentListeners (JTextField... textFields) {
        for (JTextField textField : textFields) {
            textField.getDocument ().addDocumentListener (new DocumentListener () {
                @Override
                public void insertUpdate (DocumentEvent e) {
                    calculateDeductions ();
                }

                @Override
                public void removeUpdate (DocumentEvent e) {
                    calculateDeductions ();
                }

                @Override
                public void changedUpdate (DocumentEvent e) {
                }
            });
        }
    }

    public String[] getSalaryData () {
        return new String[] {
            GrossSalary.getText (),
            HoursDaysDeduction.getText (),
            TotalDeduction.getText (),
            NettIncome.getText (),
            EmployerEPF.getText (),
            EmployerSocso.getText (),
            EmployerEIS.getText (),
            EmployerAnnTax.getText (),
            EmployeeEPF.getText (),
            EmployeeSocso.getText (),
            EmployeeEIS.getText (),
            PCB.getText (),
        };
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel23 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        UnpaidLeaveCount = new javax.swing.JTextField();
        UnpaidLeaveDeduct = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        LateDays = new javax.swing.JTextField();
        LateDeduct = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        HoursDaysDeduction = new javax.swing.JTextField();
        DailyRateLabel = new javax.swing.JLabel();
        GrossSalary = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        EmployerEPF = new javax.swing.JTextField();
        EmployerSocso = new javax.swing.JTextField();
        EmployeeEPF = new javax.swing.JTextField();
        EmployeeSocso = new javax.swing.JTextField();
        EpfAccNo = new javax.swing.JTextField();
        TIN = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        EmployerAnnTax = new javax.swing.JTextField();
        EmployerEIS = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        EmployeeAnnTax = new javax.swing.JTextField();
        EmployeeEIS = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        PCB = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        TotalDeduction = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        NettIncome = new javax.swing.JTextField();

        jLabel23.setText("jLabel23");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Gross Salary:");

        jLabel2.setText("EPF Acc. No. :");

        jLabel7.setText("TIN: ");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hours / Days Deduction", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel10.setText("Unpaid Leave:");

        UnpaidLeaveCount.setEditable(false);
        UnpaidLeaveCount.setFocusable(false);
        UnpaidLeaveCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UnpaidLeaveCountActionPerformed(evt);
            }
        });

        UnpaidLeaveDeduct.setEditable(false);
        UnpaidLeaveDeduct.setForeground(new java.awt.Color(255, 51, 51));
        UnpaidLeaveDeduct.setFocusable(false);

        jLabel9.setText("Late: ");

        LateDays.setEditable(false);
        LateDays.setFocusable(false);
        LateDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LateDaysActionPerformed(evt);
            }
        });

        LateDeduct.setEditable(false);
        LateDeduct.setForeground(new java.awt.Color(255, 51, 51));
        LateDeduct.setFocusable(false);

        jLabel24.setText("Total Deduction:");

        HoursDaysDeduction.setEditable(false);
        HoursDaysDeduction.setForeground(new java.awt.Color(255, 51, 51));
        HoursDaysDeduction.setFocusable(false);

        DailyRateLabel.setText("____ /day");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HoursDaysDeduction))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(LateDays, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(UnpaidLeaveCount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LateDeduct, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                                    .addComponent(UnpaidLeaveDeduct)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(DailyRateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(LateDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LateDeduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UnpaidLeaveCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UnpaidLeaveDeduct))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DailyRateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(HoursDaysDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        GrossSalary.setEditable(false);
        GrossSalary.setForeground(new java.awt.Color(0, 204, 0));
        GrossSalary.setFocusable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "EPF & SOCSO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel12.setText("Employer Contribution");

        jLabel13.setText("EPF:");

        jLabel14.setText("SOCSO:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel15.setText("Employee Contribution");

        jLabel16.setText("EPF:");

        jLabel17.setText("SOCSO:");

        EmployerEPF.setEditable(false);
        EmployerEPF.setFocusable(false);

        EmployerSocso.setEditable(false);
        EmployerSocso.setFocusable(false);

        EmployeeEPF.setEditable(false);
        EmployeeEPF.setForeground(new java.awt.Color(255, 51, 51));
        EmployeeEPF.setFocusable(false);

        EmployeeSocso.setEditable(false);
        EmployeeSocso.setForeground(new java.awt.Color(255, 51, 51));
        EmployeeSocso.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(32, 32, 32)
                        .addComponent(EmployerEPF))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel12))
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(13, 13, 13)
                        .addComponent(EmployerSocso))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(32, 32, 32)
                        .addComponent(EmployeeEPF))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(13, 13, 13)
                        .addComponent(EmployeeSocso)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(EmployerEPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(EmployerSocso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(EmployeeEPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(EmployeeSocso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        EpfAccNo.setEditable(false);
        EpfAccNo.setFocusable(false);

        TIN.setEditable(false);
        TIN.setFocusable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "EIS & Annual Tax", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel3.setText("Employer Contribution");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel4.setText("Employee Contribution");

        jLabel5.setText("EIS: ");

        jLabel6.setText("Annual Tax:");

        EmployerAnnTax.setEditable(false);
        EmployerAnnTax.setFocusable(false);

        EmployerEIS.setEditable(false);
        EmployerEIS.setFocusable(false);

        jLabel8.setText("EIS: ");

        jLabel18.setText("Annual Tax:");

        EmployeeAnnTax.setEditable(false);
        EmployeeAnnTax.setForeground(new java.awt.Color(255, 51, 51));
        EmployeeAnnTax.setFocusable(false);

        EmployeeEIS.setEditable(false);
        EmployeeEIS.setForeground(new java.awt.Color(255, 51, 51));
        EmployeeEIS.setFocusable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EmployerEIS, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(EmployerAnnTax)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EmployeeEIS)
                            .addComponent(EmployeeAnnTax))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(EmployerEIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(EmployerAnnTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(EmployeeEIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(EmployeeAnnTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PCB", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel19.setText("PCB: ");

        PCB.setEditable(false);
        PCB.setForeground(new java.awt.Color(255, 51, 51));
        PCB.setFocusable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PCB)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel19)
                .addComponent(PCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Total Deduction: ");

        TotalDeduction.setEditable(false);
        TotalDeduction.setForeground(new java.awt.Color(255, 51, 51));
        TotalDeduction.setFocusable(false);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Nett Income: ");

        NettIncome.setEditable(false);
        NettIncome.setForeground(new java.awt.Color(0, 204, 0));
        NettIncome.setFocusable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TotalDeduction)
                    .addComponent(NettIncome))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(TotalDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(NettIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GrossSalary))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TIN)
                            .addComponent(EpfAccNo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(EpfAccNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(TIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(GrossSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void LateDaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LateDaysActionPerformed
        calculateDeductions ();
    }//GEN-LAST:event_LateDaysActionPerformed

    private void UnpaidLeaveCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UnpaidLeaveCountActionPerformed
        calculateDeductions ();
    }//GEN-LAST:event_UnpaidLeaveCountActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DailyRateLabel;
    private javax.swing.JTextField EmployeeAnnTax;
    private javax.swing.JTextField EmployeeEIS;
    private javax.swing.JTextField EmployeeEPF;
    private javax.swing.JTextField EmployeeSocso;
    private javax.swing.JTextField EmployerAnnTax;
    private javax.swing.JTextField EmployerEIS;
    private javax.swing.JTextField EmployerEPF;
    private javax.swing.JTextField EmployerSocso;
    private javax.swing.JTextField EpfAccNo;
    private javax.swing.JTextField GrossSalary;
    private javax.swing.JTextField HoursDaysDeduction;
    private javax.swing.JTextField LateDays;
    private javax.swing.JTextField LateDeduct;
    private javax.swing.JTextField NettIncome;
    private javax.swing.JTextField PCB;
    private javax.swing.JTextField TIN;
    private javax.swing.JTextField TotalDeduction;
    private javax.swing.JTextField UnpaidLeaveCount;
    private javax.swing.JTextField UnpaidLeaveDeduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
