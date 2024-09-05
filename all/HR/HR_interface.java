package HR;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import designcomponent.ImageAvatar;
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
import javaasg.Profile;
import javaasg.login.logintest;
import javax.swing.ImageIcon;



public class HR_interface extends javax.swing.JFrame {
    private EmployeeForm employeeForm;
    private DefaultTableModel model;
    

    
    public HR_interface() {
        initComponents();
        seticonmain();
        raven.popup.GlassPanePopup.install(this);
        raven.toast.Notifications.getInstance().setJFrame(this);
        icon = new ImageAvatar();
        
        jTable2 = new javax.swing.JTable();

        
        String[] columnName = {
            "FirstNm", "LastNm", "NRIC", "Username", "Password", "Acc_Status","Role", "DOB", "Race", "Rel", "POB",
            "Email", "HomeAdd", "Gender", "Marital", "Hp", "BankNm", "AccNum", "EPF",
            "TIN", "Fm_Nm", "Fm_NRIC", "Relation", "Fm_Hp", "Work_exp", "DateJoined", "DateResigned",
            "CurrentPosition", "Department", "MonthlyGrossSalary", "AnnualLeave", "MedLeave", "MaternityLeave",
            "UnpaidLeave", "NewPosition", "EffectiveDate","HistoryofPositionChange", "SalaryIncrement", "HistoryofSalaryIncrement" ,"Profile"
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
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        icon = new designcomponent.ImageAvatar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(242, 237, 229));

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
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jLayeredPane1.setBackground(new java.awt.Color(93, 12, 29));
        jLayeredPane1.setOpaque(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaasg/image/newlogo - Copy.png"))); // NOI18N

        icon.setBorderSize(0);
        icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconMouseClicked(evt);
            }
        });

        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(icon, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void updatebutton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebutton
            
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        int selectedRow = jTable2.getSelectedRow();
        
        
        

        Object[] rowData = null;
        String profile  = null;
        if (selectedRow >= 0) {
            // Get the data from the selected row
            rowData = new Object[model.getColumnCount()];
            for (int i = 0; i < model.getColumnCount(); i++) {
                rowData[i] = model.getValueAt(selectedRow, i);
            }
            profile = (String) rowData[39];
        }

        // Create the EmployeeForm instance with row data
        EmployeeForm form = new EmployeeForm(model, rowData, profile);

        DefaultOption option = new DefaultOption() {
            public boolean closeWhenClickOutside() {
                return true;
            }
        };

        String actions[] = new String[]{"Cancel", "Save"};
        String finalProfilePath = profile; 
        
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

    
    
    public void setIconFromCSV() {
        String[] adminDetails = logintest.getuserDetails();

        if (adminDetails != null && adminDetails.length > 0) {
            String iconPath = adminDetails[39];

            if (iconPath != null && !iconPath.isEmpty()) {
                ImageIcon icon = new ImageIcon(iconPath);
                this.setIconImage(icon.getImage());
            }else {
                System.err.println("Icon path is empty or invalid.");
            }
        }
    }
    
    private void seticonmain(){
        ImageAvatar aicon = new ImageAvatar();
        Profile prof = new Profile();
        
        String[] adminDetails = logintest.getuserDetails();

        updateProfilemain(adminDetails[39]);
        
        //String fullName = adminDetails[0] + " " + adminDetails[1];
    
        //adminnm.setText(fullName);
    }
    
     public void updateProfilemain(String imagePath) {
        ImageIcon profileIcon = new ImageIcon(imagePath);
         if (profileIcon.getImage() != null) {
                icon.setImage(profileIcon); 
                revalidate();
                repaint();
            } else {
                System.err.println("Failed to load image from path: " + imagePath);
            }
    }
     
     
    private void iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconMouseClicked
         ProfileHR userprof = new ProfileHR();
       
       String[] adminDetails = logintest.getuserDetails();
       userprof.updateProfileImage(adminDetails[39]);
       userprof.setuserprofile(adminDetails[3], adminDetails[4]);
       
       
       
       userprof.setVisible(true);
       this.dispose();
        
    }//GEN-LAST:event_iconMouseClicked

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
    private designcomponent.ImageAvatar icon;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
