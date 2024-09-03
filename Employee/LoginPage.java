/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.formdev.flatlaf.FlatDarkLaf;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private LoginValidator loginValidator;

    public LoginPage() {
        loginValidator = new LoginValidator();

        // Set up the frame
        setTitle("Login Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        // Username field
        JPanel usernamePanel = new JPanel(new FlowLayout());
        usernamePanel.add(new JLabel("Username:"));
        usernameField = new JTextField(20);
        usernamePanel.add(usernameField);
        add(usernamePanel);

        // Password field
        JPanel passwordPanel = new JPanel(new FlowLayout());
        passwordPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        passwordPanel.add(passwordField);
        add(passwordPanel);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
        add(loginButton);

        // Error message label
        add(new JLabel(""));

        // Set visibility
        setVisible(true);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (loginValidator.validate(username, password)) {
            String[] employeeDetails = loginValidator.getEmployeeDetails(username);
            
            if (employeeDetails != null && "Employee".equals(employeeDetails[6])) { // Check if the role is "Employee"
                EmployeePage employeePage = new EmployeePage(employeeDetails);
                employeePage.setVisible(true);
                this.dispose(); // Close the login page
            } else {
                JOptionPane.showMessageDialog(this, "You do not have access to the Employee Page.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
}
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
