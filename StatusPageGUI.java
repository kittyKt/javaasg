
package assignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public class StatusPageGUI {
    private JFrame frame;
    private JTextField userTextField;
    private JTable leaveTable;
    private DefaultTableModel tableModel;
    private StatusPage statusPage;
    
    public StatusPageGUI() {
        statusPage = new StatusPage("leave_applications.csv");
        frame = new JFrame("Leave Status Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 576);

        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);

        addComponents(panel);

        frame.setVisible(true);
    }
    
    private void addComponents(JPanel panel) {
        
        JPanel northPanel = new JPanel(new GridLayout(1, 3));

        JLabel nameLabel = new JLabel("Username:");
        userTextField = new JTextField(15);
        JButton loadButton = new JButton("Load Leaves");
        loadButton.addActionListener(e -> loadUserLeaves());
        JButton backButton = new JButton("Back To Homepage");
        backButton.addActionListener(e -> goBackToHomePage());

        northPanel.add(nameLabel);
        northPanel.add(userTextField);
        northPanel.add(loadButton);
        northPanel.add(backButton);

        panel.add(northPanel, BorderLayout.NORTH);
    
        String[] columnNames = {"Leave Type", "Start Date", "End Date", "Total Days", "Status", "Cancel"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Only the cancel button column is editable
            }
        };
        
        leaveTable = new JTable(tableModel);
        leaveTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        leaveTable.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));
        
        leaveTable.getTableHeader().setBackground(Color.LIGHT_GRAY);
        leaveTable.getTableHeader().setOpaque(true);

        JScrollPane scrollPane = new JScrollPane(leaveTable);
        panel.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void loadUserLeaves() {
        String userName = userTextField.getText();
        System.out.println("Loading leaves for user: " + userName); // Debug print
        
        if (leaveTable.isEditing()){
            leaveTable.getCellEditor().stopCellEditing();
        }
        
        List<String[]> leaveRecords = statusPage.getUserLeaves(userName);
        tableModel.setRowCount(0);
        
        // Debug print to inspect leaveRecords
        if (leaveRecords == null) {
            System.out.println("leaveRecords is null");
        } else {
            System.out.println("leaveRecords size: " + leaveRecords.size());
            for (String[] record : leaveRecords) {
                System.out.println("Record: " + String.join(", ", record));
            }
        }

        if (leaveRecords.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No leave records found for user: " + userName);
        } else {
            for (String[] record : leaveRecords) {
                if (record.length >= 6) {
                    System.out.println("Adding record to table: " + String.join(", ", record)); // Debug print
                    tableModel.addRow(new Object[]{record[1], record[2], record[3], record[4], record[5], "Cancel"});
                }else{
                    System.out.println("Invalid record: " + String.join(", ", record)); // Debug print
                }
                    
            }
        }
    }
    
    private void goBackToHomePage(){
        frame.dispose(); 
        new HomepageGUI().setVisible(true); // ltr change to actual homepage name
    }
    
    private class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if(isSelected){
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            }else{
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
                       
            setText((value == null) ? "Cancel" : value.toString());
            return this;
        }
    }
    
    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }
        
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "Cancel" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }
        
        public Object getCellEditorValue() {
            if (isPushed) {
                int row = leaveTable.getSelectedRow();
                if (row >= 0 && row < leaveTable.getRowCount()) { //ensure valid row
                    String userName = userTextField.getText();
                    String leaveType = (String) leaveTable.getValueAt(row, 0);
                    String startDate = (String) leaveTable.getValueAt(row, 1);
                    String endDate = (String) leaveTable.getValueAt(row, 2);
                    String totalDays = (String) leaveTable.getValueAt(row, 3);
                    
                    //debug print
                    System.out.println("Deleting leave for user: " + userName + ", leaveType: " + leaveType + ", startDate: " + startDate + ", endDate: " + endDate + ", totalDays: " + totalDays);
                    
                    //more debug
                    if (leaveTable.isEditing()) {
                        leaveTable.getCellEditor().stopCellEditing();
                    }


                    tableModel.removeRow(row);
                    statusPage.deleteLeave(userName, leaveType, startDate, endDate, totalDays);

                    JOptionPane.showMessageDialog(button, "Leave has been canceled successfully");
                }else{
                    System.out.println("Invalid row selected: " + row); //debuig 
                }
            }
            isPushed = false;
            return label;
        }
        
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StatusPageGUI::new);
    }   
    
}
