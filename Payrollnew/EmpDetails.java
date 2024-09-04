package Payroll;

import java.util.ArrayList;
import javax.swing.*;

public class EmpDetails extends javax.swing.JPanel {

    private CSVReader csvReader;
    private JPanel resultPanel;
    private String currentNRIC;

    public EmpDetails (ArrayList<String> infoList) {
        initComponents ();
        clearFields ();
        loadEmployeeData (infoList);
    }

    public EmpDetails (String employeeNRIC) {
        initComponents ();
        clearFields ();
        if (employeeNRIC != null && !employeeNRIC.trim ().isEmpty ()) {
            loadEmployeeData (employeeNRIC);
        }
    }

    private void loadEmployeeData (ArrayList<String> infoList) {
        FirstName.setText (infoList.get (0));
        LastName.setText (infoList.get (1));
        NRIC.setText (infoList.get (2));
        currentNRIC = infoList.get(2);
        Status.setText (infoList.get (3));
        Role.setText (infoList.get (4));
        Email.setText (infoList.get (5));
        HpNo.setText (infoList.get (6));

        String bankName = infoList.get (7);
        String accountNumber = infoList.get (8);
        Bank.setText (bankName + " - " + accountNumber);

        Department.setText (infoList.get (9));
    }

    private void loadEmployeeData (String employeeNRIC) {
        CSVReader csvReader = new CSVReader ();
        String[] employeeData = csvReader.getEmployeeDetails (employeeNRIC);

        if (employeeData != null) {
            FirstName.setText (employeeData[0]);
            LastName.setText (employeeData[1]);
            NRIC.setText (employeeData[2]);
            currentNRIC = employeeData[2];
            Status.setText (employeeData[5]);
            Role.setText (employeeData[6]);
            Email.setText (employeeData[11]);
            HpNo.setText (employeeData[15]);

            String bankName = employeeData[16];
            String accountNumber = employeeData[17];
            Bank.setText (bankName + " - " + accountNumber);

            Department.setText (employeeData[28]);
        } else {
            JOptionPane.showMessageDialog (this, "Employee not found!");
            clearFields ();
        }
    }

    private void clearFields () {
        FirstName.setText ("");
        LastName.setText ("");
        NRIC.setText ("");
        Status.setText ("");
        Role.setText ("");
        Email.setText ("");
        HpNo.setText ("");
        Bank.setText ("");
        Department.setText ("");
    }

    public String[] getEmployeeData () {
        return new String[] {
            NRIC.getText (),};

    }

    public String getNRIC() {
        return currentNRIC;
    }
    
    public void updateNRIC() {
        currentNRIC = NRIC.getText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        FirstName = new javax.swing.JTextField();
        LastName = new javax.swing.JTextField();
        NRIC = new javax.swing.JTextField();
        Status = new javax.swing.JTextField();
        Role = new javax.swing.JTextField();
        Email = new javax.swing.JTextField();
        HpNo = new javax.swing.JTextField();
        Bank = new javax.swing.JTextField();
        Department = new javax.swing.JTextField();

        jLabel1.setText("First Name:");

        jLabel2.setText("Last Name:");

        jLabel3.setText("NRIC:");

        jLabel4.setText("Account Status:");

        jLabel5.setText("Role:");

        jLabel6.setText("Email:");

        jLabel7.setText("H.P no.:");

        jLabel8.setText("Bank:");

        jLabel9.setText("Department:");

        FirstName.setEditable(false);
        FirstName.setFocusable(false);

        LastName.setEditable(false);
        LastName.setFocusable(false);

        NRIC.setEditable(false);
        NRIC.setFocusable(false);

        Status.setEditable(false);
        Status.setFocusable(false);

        Role.setEditable(false);
        Role.setFocusable(false);

        Email.setEditable(false);
        Email.setFocusable(false);

        HpNo.setEditable(false);
        HpNo.setFocusable(false);

        Bank.setEditable(false);
        Bank.setFocusable(false);

        Department.setEditable(false);
        Department.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Status, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(NRIC, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LastName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FirstName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Role)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Bank, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(HpNo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Email, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Department))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(FirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(LastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(NRIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(HpNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Bank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Department, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Bank;
    private javax.swing.JTextField Department;
    private javax.swing.JTextField Email;
    private javax.swing.JTextField FirstName;
    private javax.swing.JTextField HpNo;
    private javax.swing.JTextField LastName;
    private javax.swing.JTextField NRIC;
    private javax.swing.JTextField Role;
    private javax.swing.JTextField Status;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
