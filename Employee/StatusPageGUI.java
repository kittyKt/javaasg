
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatusPageGUI extends JFrame {
    private JFrame frame;
    private JTextField userTextField;
    private JTable leaveTable;
    private StatusPage statusPage;
    private DefaultTableModel tableModel;
    private String loggedInUsername;
    private JTextField usernameField; 
    private String[] employeeDetails;
    
    public StatusPageGUI(String loggedInUsername,String[] employeeDetails) {
        this.loggedInUsername = loggedInUsername;
        this.employeeDetails = employeeDetails;
        frame = new JFrame("Leave Status Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 576);

        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);

        addComponents(panel);

        loadUserLeaves();

        frame.setVisible(true);
    }
    
    private void addComponents(JPanel panel) {
        JPanel northPanel = new JPanel(new GridLayout(1, 2)); 

        JLabel nameLabel = new JLabel("Username:");
        userTextField = new JTextField(15);
        userTextField.setText(loggedInUsername); 
        userTextField.setEditable(false); 

        northPanel.add(nameLabel);
        northPanel.add(userTextField);
        
        JButton backButton = new JButton("Back to Homepage");
        backButton.addActionListener(e -> goBackToHomePage());
        northPanel.add(backButton);
        
        panel.add(northPanel, BorderLayout.NORTH);
    
        String[] columnNames = {"Leave Type", "Start Date", "End Date", "Total Days", "Status", "Cancel"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // only cancel button editable 
            }
        };
        
        leaveTable = new JTable(tableModel);
        leaveTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        leaveTable.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));
        
        leaveTable.getTableHeader().setBackground(Color.LIGHT_GRAY);
        leaveTable.getTableHeader().setForeground(Color.BLACK);
        leaveTable.getTableHeader().setOpaque(true);

        JScrollPane scrollPane = new JScrollPane(leaveTable);
        panel.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void loadUserLeaves() {
        List<String[]> leaveRecords = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\4hana\\OneDrive - Asia Pacific University\\( JP ) Java Programming\\homepageGUI\\homepageGUI\\src\\leave_application.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] record = line.split(",");
                if (record.length > 0 && record[0].equals(loggedInUsername)) {  
                    leaveRecords.add(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        tableModel.setRowCount(0); // Clear previous data

        if (leaveRecords.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No leave records found for user: " + loggedInUsername);
        } else {
            for (String[] record : leaveRecords) {
                if (record.length >= 6) {
                    tableModel.addRow(new Object[]{record[1], record[2], record[3], record[4], record[5], "Cancel"});
                }
            }
        }
    }
    
    private void goBackToHomePage() {
        frame.dispose();
if (employeeDetails != null) {
        EmployeePage employeePage = new EmployeePage(employeeDetails);  
        employeePage.setVisible(true);
    }else{
        JOptionPane.showMessageDialog(frame, "Employee details are missing!");
    }
}

    private class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
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
                if (row >= 0 && row < leaveTable.getRowCount()) { 
                    String leaveType = (String) leaveTable.getValueAt(row, 0);
                    String startDate = (String) leaveTable.getValueAt(row, 1);
                    String endDate = (String) leaveTable.getValueAt(row, 2);
                    String totalDays = (String) leaveTable.getValueAt(row, 3);

                    tableModel.removeRow(row);
                    statusPage.deleteLeave(usernameField.getText(), leaveType, startDate, endDate, totalDays);
                    
                    JOptionPane.showMessageDialog(button, "Leave has been canceled successfully");
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
        String loggedInUsername = "actual_username"; 
    String[] employeeDetails = getEmployeeDetails(loggedInUsername); 
        SwingUtilities.invokeLater(() -> new StatusPageGUI(loggedInUsername, employeeDetails));
 }
    private static String[] getEmployeeDetails(String username) {

    return new String[] {"John Doe", "Developer", "Department X", "Other Info"};
}
}
