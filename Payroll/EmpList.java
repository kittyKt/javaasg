package Payroll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Payroll.PayrollMain;

public class EmpList extends javax.swing.JPanel {

    private DefaultTableModel model;
    private static ArrayList<String> userInfo = new ArrayList<String> ();

    public EmpList () {
        initComponents ();
        model = (DefaultTableModel) jTable1.getModel ();
        setColumnWidths ();

    }

    private void setColumnWidths () {
        jTable1.setRowHeight (30);
        jTable1.getColumnModel ().getColumn (0).setPreferredWidth (100); // First Name
        jTable1.getColumnModel ().getColumn (1).setPreferredWidth (100); // Last Name
        jTable1.getColumnModel ().getColumn (2).setPreferredWidth (100); // NRIC
        jTable1.getColumnModel ().getColumn (3).setPreferredWidth (100); // Account Status
        jTable1.getColumnModel ().getColumn (4).setPreferredWidth (150); // Role
        jTable1.getColumnModel ().getColumn (5).setPreferredWidth (150); // Email
        jTable1.getColumnModel ().getColumn (6).setPreferredWidth (100); // HP No.
        jTable1.getColumnModel ().getColumn (7).setPreferredWidth (100); // Bank
        jTable1.getColumnModel ().getColumn (8).setPreferredWidth (100);
        jTable1.getColumnModel ().getColumn (9).setPreferredWidth (150); // Department
        jTable1.getColumnModel ().getColumn (10).setPreferredWidth (100); // EPF No.
        jTable1.getColumnModel ().getColumn (11).setPreferredWidth (100); // TIN
        jTable1.getColumnModel ().getColumn (12).setPreferredWidth (150); // Monthly Gross Salary
    }

    public EmpList (List<String[]> employeeData) {
        initComponents ();
        loadEmployeeData (employeeData);
        setColumnWidths ();

    }

    public void loadEmployeeData (List<String[]> employeeData) {
        if (model == null) {
            model = (DefaultTableModel) jTable1.getModel ();
        }
        model.setRowCount (0);

        for (String[] rowData : employeeData) {
            if (rowData.length >= 13) { 
                model.addRow (new Object[] {
                    rowData[0], // First Name
                    rowData[1], // Last Name
                    rowData[2], // NRIC
                    rowData[5], // Account Status
                    rowData[6], // Role
                    rowData[11], // Email
                    rowData[15], // HP No.
                    rowData[16], // Bank
                    rowData[17], //Bank no.
                    rowData[28], // Department
                    rowData[18], // EPF No.
                    rowData[19], // TIN
                    rowData[29] // Monthly Gross Salary
                });
            }
        }
    }

    public void updateEmployeeList (List<String[]> employeeData, String searchQuery) {
        javax.swing.SwingUtilities.invokeLater (() -> {
            for (String[] employee : employeeData) {
                System.out.println (Arrays.toString (employee));
            }

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel ();
            model.setRowCount (0);

            List<String[]> searchedData = new ArrayList<> ();
            List<String[]> nonSearchedData = new ArrayList<> ();

            for (String[] employee : employeeData) {
                if (employee.length < 30) {
                    System.err.println ("Skipping incomplete data: " + Arrays.toString (employee));
                    continue;
                }

                boolean matchesSearch = Arrays.asList (employee[0], employee[1], employee[2], employee[11])
                        .stream ()
                        .anyMatch (field -> {
                            boolean isMatch = field != null && field.toLowerCase ().contains (searchQuery.toLowerCase ());
                            if (isMatch) {
                                System.out.println ("Match found in field: " + field);
                            }
                            return isMatch;
                        });

                if (matchesSearch) {
                    searchedData.add (employee);
                } else {
                    nonSearchedData.add (employee);
                }
            }

            for (String[] employee : nonSearchedData) {
                model.addRow (new Object[] {
                    employee[0], // First Name
                    employee[1], // Last Name
                    employee[2], // NRIC
                    employee[5], // Account Status
                    employee[6], // Role
                    employee[11], // Email
                    employee[15], // HP No.
                    employee[16], // Bank
                    employee[17],
                    employee[28], // Department
                    employee[18], // EPF No.
                    employee[19], // TIN
                    employee[29] // Monthly Gross Salary
                });
            }

            for (String[] employee : searchedData) {
                model.addRow (new Object[] {
                    employee[0], // First Name
                    employee[1], // Last Name
                    employee[2], // NRIC
                    employee[5], // Account Status
                    employee[6], // Role
                    employee[11], // Email
                    employee[15], // HP No.
                    employee[16], // Bank
                    employee[17],
                    employee[28], // Department
                    employee[18], // EPF No.
                    employee[19], // TIN
                    employee[29] // Monthly Gross Salary
                });
            }

            jTable1.revalidate ();
            jTable1.repaint ();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "NRIC", "Account Status", "Role", "Email", "HP No.", "Bank Name", "Bank No.", "Department", "EPF no.", "TIN", "Monthly Gross Salary"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        int row = jTable1.getSelectedRow ();
        PayrollMain.userinfoList.clear ();
        PayrollMain.userinfoList.add (String.valueOf (jTable1.getValueAt (row, 0)));
        PayrollMain.userinfoList.add (String.valueOf (jTable1.getValueAt (row, 1)));
        PayrollMain.userinfoList.add (String.valueOf (jTable1.getValueAt (row, 2)));
        PayrollMain.userinfoList.add (String.valueOf (jTable1.getValueAt (row, 3)));
        PayrollMain.userinfoList.add (String.valueOf (jTable1.getValueAt (row, 4)));
        PayrollMain.userinfoList.add (String.valueOf (jTable1.getValueAt (row, 5)));
        PayrollMain.userinfoList.add (String.valueOf (jTable1.getValueAt (row, 6)));
        PayrollMain.userinfoList.add (String.valueOf (jTable1.getValueAt (row, 7)));
        PayrollMain.userinfoList.add (String.valueOf (jTable1.getValueAt (row, 8)));
        PayrollMain.userinfoList.add (String.valueOf (jTable1.getValueAt (row, 9)));
        PayrollMain.userinfoList.add (String.valueOf (jTable1.getValueAt (row, 10)));
        PayrollMain.userinfoList.add (String.valueOf (jTable1.getValueAt (row, 11)));
        PayrollMain.userinfoList.add (String.valueOf (jTable1.getValueAt (row, 12)));
    }//GEN-LAST:event_jTable1MouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
