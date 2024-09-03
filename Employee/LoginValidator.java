
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
                if (details[3].equals(username) && details[4].equals(password)) { 
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
                if (details[3].equals(username)) { 
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