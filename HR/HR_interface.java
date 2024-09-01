package javaassignment;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import raven.popup.GlassPanePopup;
import raven.popup.GlassPopup;
import raven.popup.DefaultOption;
import raven.popup.component.SimplePopupBorder;
import javax.swing.UnsupportedLookAndFeelException;
import raven.toast.Notifications;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;



public class HR_interface extends javax.swing.JFrame {
    private EmployeeForm employeeForm;
    private DefaultTableModel model;
    

    
    public HR_interface() {
        initComponents();

        raven.popup.GlassPanePopup.install(this);
        raven.toast.Notifications.getInstance().setJFrame(this);
        jTable2 = new javax.swing.JTable();

        
        String[] columnName = {
            "FirstNm", "LastNm", "NRIC", "Username", "Password", "Acc_Status","Role", "DOB", "Race", "Rel", "POB",
            "Email", "HomeAdd", "Gender", "Marital", "Hp", "BankNm", "AccNum", "EPF",
            "TIN", "Fm_Nm", "Fm_NRIC", "Relation", "Fm_Hp", "Work_exp", "DateJoined", "DateResigned",
            "CurrentPosition", "Department", "MonthlyGrossSalary", "AnnualLeave", "MedLeave", "MaternityLeave",
            "UnpaidLeave", "NewPosition", "EffectiveDate","HistoryofPositionChange", "SalaryIncrement", "HistoryofSalaryIncrement" 
        };
        
 
        DefaultTableModel model = new DefaultTableModel(columnName, 0);
        jTable2 = new javax.swing.JTable(model);
        
        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        JScrollPane scrollPane = new JScrollPane(jTable2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        jScrollPane2.setViewportView(jTable2);  


        CSVUtil.loadTableDataFromCSV(model);

        setColumnWidths();
        setVisible(true);


        
    }
    
    

    private void setColumnWidths() {
            TableColumnModel columnModel = jTable2.getColumnModel();

            // Set preferred widths for each column
            for (int i = 0; i < columnModel.getColumnCount(); i++) {
                TableColumn column = columnModel.getColumn(i);
                column.setPreferredWidth(150); // Adjust width as needed
            }
        }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton1.setText("Update");
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebutton(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updatebutton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebutton
            
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        int selectedRow = jTable2.getSelectedRow();
        
        
        

        Object[] rowData = null;
        if (selectedRow >= 0) {
            // Get the data from the selected row
            rowData = new Object[model.getColumnCount()];
            for (int i = 0; i < model.getColumnCount(); i++) {
                rowData[i] = model.getValueAt(selectedRow, i);
            }
        }

        // Create the EmployeeForm instance with row data
        EmployeeForm form = new EmployeeForm(model, rowData);

        DefaultOption option = new DefaultOption() {
            public boolean closeWhenClickOutside() {
                return true;
            }
        };

        String actions[] = new String[]{"Cancel", "Save"};
        GlassPanePopup.showPopup(new SimplePopupBorder(form, "Form", actions, (pc, i) -> {
            if (i == 1) { // "Save" button clicked
                try {
                    // Attempt to get data and validate
                    Object[] formData = form.getData();
                    

                    // Update the existing row or add a new row
                    if (selectedRow >= 0) {
                        // Update the existing row
                        for (int j = 0; j < model.getColumnCount(); j++) {
                            model.setValueAt(formData[j], selectedRow, j);
                        }
                    } else {
                        // Add a new row
                        model.addRow(formData);
                    }

                    // Save data to CSV
                    CSVUtil.saveTableDataToCSV(model);

//                    form.clearFields();

                    pc.closePopup();

                } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
                }
                
            } else {
                pc.closePopup(); // Close the popup without saving
            }
        }), option);






    
// TODO add your handling code here:
    }//GEN-LAST:event_updatebutton

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HR_interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
