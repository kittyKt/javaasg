package Payroll;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PaySlip extends javax.swing.JFrame {

    private String nric;

    public PaySlip (String nric) {
        this.nric = nric;
        initComponents ();
        customizeTable ();
        loadEmployeeData ();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void loadEmployeeData () {
        System.out.println ("initial:"+nric);
        loadEmployeeDetails (nric);
        loadSalaryDetails (nric);
    }

    private void customizeTable () {
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer ();
        leftRenderer.setHorizontalAlignment (JLabel.LEFT);
        jTable1.getColumnModel ().getColumn (1).setCellRenderer (leftRenderer);
        jTable1.getColumnModel ().getColumn (3).setCellRenderer (leftRenderer);
        jTable1.getColumnModel ().getColumn (5).setCellRenderer (leftRenderer);

        CustomHeaderRenderer headerRenderer = new CustomHeaderRenderer ();
        jTable1.getTableHeader ().setDefaultRenderer (headerRenderer);

        jTable1.getTableHeader ().setOpaque (false);
        jTable1.setShowGrid (true);
        jTable1.setGridColor (Color.LIGHT_GRAY); 

        jTable1.setShowHorizontalLines (false);
        jTable1.setShowVerticalLines (true);
        jTable1.setRowHeight (25);

        jTable1.getColumnModel ().getColumn (0).setPreferredWidth (130);
        jTable1.getColumnModel ().getColumn (1).setPreferredWidth (120);
        jTable1.getColumnModel ().getColumn (2).setPreferredWidth (130);
        jTable1.getColumnModel ().getColumn (3).setPreferredWidth (120);
        jTable1.getColumnModel ().getColumn (4).setPreferredWidth (150);
        jTable1.getColumnModel ().getColumn (5).setPreferredWidth (100);

        jTable1.getTableHeader ().revalidate ();
        jTable1.getTableHeader ().repaint ();
        jTable1.revalidate ();
        jTable1.repaint ();
    }

    private void loadEmployeeDetails (String nric) {
        try (BufferedReader br = new BufferedReader (new FileReader ("C:\\Users\\Hiew\\Documents\\NetBeansProjects\\Assignment1\\src\\main\\java\\csv\\Employees_DetailsCSV.csv"))) {
            String line;
            while ((line = br.readLine ()) != null) {
                String[] values = line.split (",");
                if (values[2].equals (nric)) { 
                    NameLabel.setText (values[0] + " " + values[1]);
                    NRICLabel.setText (values[2]);
                    HPNoLabel.setText (values[15]);
                    BankNameLabel.setText (values[16]);
                    BankAccLabel.setText (values[17]);
                    DepartmentLabel.setText (values[28]);
                    PositionLabel.setText (values[27]);
                    EPFNoLabel.setText (values[18]);
                    TINLabel.setText (values[19]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    private void loadSalaryDetails(String nric) {
        DecimalFormat df = new DecimalFormat("0.00");

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Hiew\\Documents\\NetBeansProjects\\Assignment1\\src\\main\\java\\csv\\PaySlipCSV.csv"))) {
            String line;
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 1; col < model.getColumnCount(); col += 2) {
                    model.setValueAt(null, row, col);
                }
            }

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values[2].equals(nric)) { 
                    DateLabel.setText(values[1] + " " + values[0]);
                    MonthLabel.setText(" "+values[1]);
                    MonthLabel.setFont(new java.awt.Font("Segoe UI Symbol", java.awt.Font.BOLD, 36));

                    model.setValueAt(df.format(Double.parseDouble(values[3])), 0, 1);
                    model.setValueAt(df.format(Double.parseDouble(values[3])), 7, 1);
                    model.setValueAt(df.format(Double.parseDouble(values[11])), 0, 3); 
                    model.setValueAt(df.format(Double.parseDouble(values[7])), 0, 5); 
                    model.setValueAt(df.format(Double.parseDouble(values[12])), 1, 3); 
                    model.setValueAt(df.format(Double.parseDouble(values[8])), 1, 5); 
                    model.setValueAt(df.format(Double.parseDouble(values[13])), 2, 3); 
                    model.setValueAt(df.format(Double.parseDouble(values[9])), 2, 5);
                    model.setValueAt(df.format(Double.parseDouble(values[14])), 3, 3);
                    model.setValueAt(df.format(Double.parseDouble(values[4])), 4, 3);
                    model.setValueAt(df.format(Double.parseDouble(values[5])), 7, 3);
                    model.setValueAt(df.format(Double.parseDouble(values[15])), 5, 3);
                    model.setValueAt(df.format(Double.parseDouble(values[6])), 8, 5);
                    double totalEmployerContri = Double.parseDouble(values[7]) + Double.parseDouble(values[8]) + Double.parseDouble(values[9]);
                    model.setValueAt(df.format(totalEmployerContri), 7, 5);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel18 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        MonthLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        NameLabel = new javax.swing.JLabel();
        NRICLabel = new javax.swing.JLabel();
        HPNoLabel = new javax.swing.JLabel();
        BankNameLabel = new javax.swing.JLabel();
        BankAccLabel = new javax.swing.JLabel();
        DateLabel = new javax.swing.JLabel();
        DepartmentLabel = new javax.swing.JLabel();
        PositionLabel = new javax.swing.JLabel();
        EPFNoLabel = new javax.swing.JLabel();
        TINLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel18.setText("jLabel14");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Hiew\\Documents\\SEM5\\Java\\Assignment\\NewLogo.png")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 36)); // NOI18N
        jLabel2.setText("Pay Slip for the Month of");

        jLabel3.setText("Name");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("NRIC");

        jLabel5.setText("Phone no.");

        jLabel6.setText("Bank");

        jLabel7.setText("Bank Acc no.");

        jLabel8.setText("Date");

        jLabel9.setText("EPF no.");

        jLabel10.setText("TIN");

        jLabel11.setText("Department");

        jLabel12.setText("Current Position");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Gross Salary", null, "Employee EPF", null, "Employer EPF", null},
                {null, null, "Employee SOCSO", null, "Employer SOCSO", null},
                {null, null, "Employee EIS", null, "Employer EIS", null},
                {null, null, "Income Tax (PCB)", null, "", null},
                {null, null, "Late/Unpaid Leave", null, null, null},
                {null, null, "Advance Salary", null, null, null},
                {null, null, null, null, null, null},
                {"Total", null, "Total", null, "Total", null},
                {"", null, "", null, "Nett Salary (RM)", null}
            },
            new String [] {
                "Income", "Amount (RM)", "Deduction", "Amount (RM)", "Employer Contribution", "Amount (RM)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setFocusable(false);
        jTable1.setShowVerticalLines(true);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MonthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NRICLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(HPNoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BankNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BankAccLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(235, 235, 235)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TINLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(DepartmentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PositionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EPFNoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(129, 129, 129))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(MonthLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(NameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(NRICLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(HPNoLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(BankNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(BankAccLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(DateLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(DepartmentLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(PositionLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(EPFNoLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(TINLabel))))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main (String args[]) {
        String testNric = "";
        javax.swing.SwingUtilities.invokeLater (new Runnable () {
            public void run () {
                new PaySlip (testNric).setVisible (true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BankAccLabel;
    private javax.swing.JLabel BankNameLabel;
    private javax.swing.JLabel DateLabel;
    private javax.swing.JLabel DepartmentLabel;
    private javax.swing.JLabel EPFNoLabel;
    private javax.swing.JLabel HPNoLabel;
    private javax.swing.JLabel MonthLabel;
    private javax.swing.JLabel NRICLabel;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JLabel PositionLabel;
    private javax.swing.JLabel TINLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
