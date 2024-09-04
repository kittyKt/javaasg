package Payroll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class SalaryReport extends javax.swing.JFrame {

    public SalaryReport () {
        initComponents ();
        initializeMonthYear ();
        loadInitialData ();
        setupFilterButton ();
        setColumnWidths ();

        ReportList.addMouseListener (new java.awt.event.MouseAdapter () {
            @Override
            public void mouseClicked (java.awt.event.MouseEvent e) {
                if (e.getClickCount () == 2) {
                    int selectedRow = ReportList.getSelectedRow ();
                    if (selectedRow != -1) {
                        String nric = (String) ReportList.getValueAt (selectedRow, 2);

                        PaySlip paySlip = new PaySlip (nric);
                        paySlip.setVisible (true);
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        MonthComboBox = new javax.swing.JComboBox<>();
        YearComboBox = new javax.swing.JComboBox<>();
        FilterButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ReportList = new javax.swing.JTable();
        HomeButton = new javax.swing.JButton();
        SalaryButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Salary Report");

        MonthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        YearComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2024" }));

        FilterButton.setText("Filter");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        ReportList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Year", "Month", "NRIC", "GrossSalary", "NettSalary", "LateLeaveDeduction", "TotalDeduction", "EPF (Employee)", "EPF (Employer)", "SOCSO (Employee)", "SOCSO (Employer)", "EIS (Employee)", "EIS (Employer)", "PCB"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ReportList.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(ReportList);

        HomeButton.setText("Home");
        HomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeButtonActionPerformed(evt);
            }
        });

        SalaryButton.setText("Salary");
        SalaryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalaryButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(MonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(YearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(FilterButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 601, Short.MAX_VALUE)
                        .addComponent(SalaryButton)
                        .addGap(18, 18, 18)
                        .addComponent(HomeButton)))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1019, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(HomeButton)
                    .addComponent(SalaryButton))
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(YearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FilterButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void HomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeButtonActionPerformed
        Homepage homepage = new Homepage ();
        homepage.setVisible (true);
        this.dispose ();
    }//GEN-LAST:event_HomeButtonActionPerformed

    private void SalaryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalaryButtonActionPerformed
        PayrollMain payrollMain = new PayrollMain ();
        payrollMain.setVisible (true);
        this.dispose ();
    }//GEN-LAST:event_SalaryButtonActionPerformed

    private void initializeMonthYear () {
        Calendar now = Calendar.getInstance ();
        int currentMonth = now.get (Calendar.MONTH);
        int currentYear = now.get (Calendar.YEAR);

        String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };

        YearComboBox.setModel (new DefaultComboBoxModel<> (new String[] {String.valueOf (currentYear)}));

        DefaultComboBoxModel<String> monthModel = new DefaultComboBoxModel<> ();

        for (int i = 0; i <= currentMonth; i++) {
            monthModel.addElement (months[i]);
        }

        if (currentMonth < 11) {
            monthModel.addElement (months[currentMonth + 1]);
        }

        MonthComboBox.setModel (monthModel);

        MonthComboBox.setSelectedItem (months[currentMonth]);
    }

    private void setColumnWidths () {
        TableColumnModel columnModel = ReportList.getColumnModel ();
        ReportList.setRowHeight (30);
        columnModel.getColumn (0).setPreferredWidth (100); // Year
        columnModel.getColumn (1).setPreferredWidth (100); // Month
        columnModel.getColumn (2).setPreferredWidth (150); // NRIC
        columnModel.getColumn (3).setPreferredWidth (150); // Gross Salary
        columnModel.getColumn (4).setPreferredWidth (150); // Nett Salary
        columnModel.getColumn (5).setPreferredWidth (150); // Late Leave Deduction
        columnModel.getColumn (6).setPreferredWidth (150); // Total Deduction
        columnModel.getColumn (7).setPreferredWidth (150); // EPF (Employee)
        columnModel.getColumn (8).setPreferredWidth (150); // EPF (Employer)
        columnModel.getColumn (9).setPreferredWidth (150); // SOCSO (Employee)
        columnModel.getColumn (10).setPreferredWidth (150); // SOCSO (Employer)
        columnModel.getColumn (11).setPreferredWidth (150); // EIS (Employee)
        columnModel.getColumn (12).setPreferredWidth (150); // EIS (Employer)
        columnModel.getColumn (13).setPreferredWidth (150); // PCB // Monthly Gross Salary
    }

    private void loadInitialData () {
        String filePath = "C:\\Users\\Hiew\\Documents\\NetBeansProjects\\Assignment1\\src\\main\\java\\csv\\PaySlipCSV.csv";
        DefaultTableModel model = (DefaultTableModel) ReportList.getModel ();

        try (BufferedReader br = new BufferedReader (new FileReader (filePath))) {
            String line;
            br.readLine ();
            while ((line = br.readLine ()) != null) {
                String[] data = line.split (",");
                String[] rowData = new String[14];
                rowData[0] = data[0]; // Year
                rowData[1] = data[1]; // Month
                rowData[2] = data[2]; // NRIC
                rowData[3] = data[3]; // GrossSalary
                rowData[4] = data[6]; // NettSalary
                rowData[5] = data[4]; // LateLeaveDeduction
                rowData[6] = data[5]; // Total Deduction
                rowData[7] = data[11]; // EPF (Employee)
                rowData[8] = data[7]; // EPF (Employer)
                rowData[9] = data[12]; // SOCSO (Employee)
                rowData[10] = data[8]; // SOCSO (Employer)
                rowData[11] = data[13]; // EIS (Employee)
                rowData[12] = data[9]; // EIS (Employer)
                rowData[13] = data[14];//PCB
                model.addRow (rowData);
            }
        } catch (IOException e) {
            e.printStackTrace ();
            JOptionPane.showMessageDialog (this, "Error loading data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupFilterButton () {
        FilterButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                filterSalaryReport ();
            }
        });
    }

    private void filterSalaryReport () {
        String selectedMonth = MonthComboBox.getSelectedItem ().toString ();

        DefaultTableModel model = (DefaultTableModel) ReportList.getModel ();
        model.setRowCount (0);
        String filePath = "C:\\Users\\Hiew\\Documents\\NetBeansProjects\\Assignment1\\src\\main\\java\\csv\\PaySlipCSV.csv";

        try (BufferedReader br = new BufferedReader (new FileReader (filePath))) {
            String line;
            br.readLine (); // Skip the header line
            while ((line = br.readLine ()) != null) {
                String[] data = line.split (",");
                if (data[1].equals (selectedMonth)) {
                    String[] rowData = new String[14];
                    rowData[0] = data[0]; // Year
                    rowData[1] = data[1]; // Month
                    rowData[2] = data[2]; // NRIC
                    rowData[3] = data[3]; // GrossSalary
                    rowData[4] = data[6]; // NettSalary
                    rowData[5] = data[4]; // LateLeaveDeduction
                    rowData[6] = data[5]; // Total Deduction
                    rowData[7] = data[11]; // EPF (Employee)
                    rowData[8] = data[7]; // EPF (Employer)
                    rowData[9] = data[12]; // SOCSO (Employee)
                    rowData[10] = data[8]; // SOCSO (Employer)
                    rowData[11] = data[13]; // EIS (Employee)
                    rowData[12] = data[9]; // EIS (Employer)
                    rowData[13] = data[14]; // PCB
                    model.addRow (rowData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace ();
            JOptionPane.showMessageDialog (this, "Error filtering data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (() -> {
            JFrame salaryReportFrame = new SalaryReport ();
            salaryReportFrame.setVisible (true);
            JFrame paySlipFrame = new PaySlip ("");
            paySlipFrame.setVisible (true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton FilterButton;
    private javax.swing.JButton HomeButton;
    private javax.swing.JComboBox<String> MonthComboBox;
    private javax.swing.JTable ReportList;
    private javax.swing.JButton SalaryButton;
    private javax.swing.JComboBox<String> YearComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
