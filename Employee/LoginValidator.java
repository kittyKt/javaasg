/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginValidator {
    private static final String CSV_FILE_PATH = "C:\\Users\\4hana\\OneDrive - Asia Pacific University\\( JP ) Java Programming\\homepageGUI\\homepageGUI\\src\\Employees_DetailsCSV.csv"; // Adjust the path accordingly

    public boolean validate(String username, String password) {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details[3].equals(username) && details[4].equals(password)) { // Assuming Username and Password are in 4th and 5th columns
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public String[] getEmployeeDetails(String username) {
        String line;
        String[] employeeDetails = null;

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details[3].equals(username)) { // Assuming Username is the 4th column (index 3)
                    employeeDetails = details;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employeeDetails;
    }
}