package Payroll;

import com.formdev.flatlaf.themes.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Homepage extends javax.swing.JFrame {

    public Homepage () {
        initComponents ();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDate = currentDate.format(formatter);
        dateLabel.setText(formattedDate);
        dateLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        SalaryButton = new javax.swing.JButton();
        SalaryReportButton = new javax.swing.JButton();
        ViewProfileButton = new javax.swing.JButton();
        dateLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Payroll Management System");

        SalaryButton.setText("Salary");
        SalaryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalaryButtonActionPerformed(evt);
            }
        });

        SalaryReportButton.setText("Salary Report");
        SalaryReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalaryReportButtonActionPerformed(evt);
            }
        });

        ViewProfileButton.setText("View Profile");
        ViewProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewProfileButtonActionPerformed(evt);
            }
        });

        dateLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(SalaryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(SalaryReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(ViewProfileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 257, Short.MAX_VALUE)
                .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(107, 107, 107)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SalaryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewProfileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SalaryReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(169, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SalaryReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalaryReportButtonActionPerformed
        SalaryReport salaryReport = new SalaryReport ();
        salaryReport.setVisible (true);
        this.dispose ();
    }//GEN-LAST:event_SalaryReportButtonActionPerformed

    private void SalaryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalaryButtonActionPerformed
        PayrollMain payrollMain = new PayrollMain ();
        payrollMain.setVisible (true);
        this.dispose ();
    }//GEN-LAST:event_SalaryButtonActionPerformed

    private void ViewProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewProfileButtonActionPerformed
        
    }//GEN-LAST:event_ViewProfileButtonActionPerformed

    public static void main (String args[]) {
        FlatMacDarkLaf.setup ();
        java.awt.EventQueue.invokeLater (new Runnable () {
            public void run () {
                new Homepage ().setVisible (true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SalaryButton;
    private javax.swing.JButton SalaryReportButton;
    private javax.swing.JButton ViewProfileButton;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
