package Payroll;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
    
    private static final int EPF_COLUMN_INDEX = 18;
    private static final int TIN_COLUMN_INDEX = 19;
    
    public List<String[]> findEmployeeByNameOrNRIC(String searchQuery) {
        String line = "";
        String csvFile = "C:\\Users\\Hiew\\Documents\\NetBeansProjects\\Assignment1\\src\\main\\java\\csv\\Employees_DetailsCSV.csv";
        String cvsSplitBy = ",";
        List<String[]> matchingEmployees = new ArrayList<>();
        String searchQueryLower = searchQuery.trim().toLowerCase();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String[] headers = br.readLine().split(cvsSplitBy); 
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    System.err.println("Empty line encountered");
                    continue;
                }

                String[] employeeData = line.split(cvsSplitBy);

                if (employeeData.length < 30) {
                    System.err.println("Line does not have expected number of columns: " + Arrays.toString(employeeData));
                    continue;
                }

                String firstName = employeeData[0].trim().toLowerCase();
                String lastName = employeeData[1].trim().toLowerCase();
                String nric = employeeData[2].trim().toLowerCase();

                if (firstName.contains(searchQueryLower) || lastName.contains(searchQueryLower) || nric.equals(searchQueryLower)) {
                    matchingEmployees.add(employeeData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchingEmployees;
    }


    
    public String[] getEmployeeDetails(String employeeNRIC) {
        String line = "";
        String csvFile = "C:\\Users\\Hiew\\Documents\\NetBeansProjects\\Assignment1\\src\\main\\java\\csv\\Employees_DetailsCSV.csv";
        String cvsSplitBy = ",";
        String employeeNRICLower = employeeNRIC.trim().toLowerCase();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    System.err.println("Empty line encountered");
                    continue;
                }

                String[] employeeData = line.split(cvsSplitBy);

                if (employeeData.length < 3) {
                    System.err.println("Line does not have enough columns: " + Arrays.toString(employeeData));
                    continue;
                }

                String nric = employeeData[2].trim().toLowerCase();

                if (nric.equals(employeeNRICLower)) { 
                    return employeeData;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    
    public List<String[]> readAllEmployees() {
        String line = "";
        String csvFile = "C:\\Users\\Hiew\\Documents\\NetBeansProjects\\Assignment1\\src\\main\\java\\csv\\Employees_DetailsCSV.csv";
        String cvsSplitBy = ",";
        List<String[]> allEmployees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String[] headers = br.readLine().split(cvsSplitBy);

            while ((line = br.readLine()) != null) {
                line = line.trim(); 

                if (line.isEmpty()) {
                    System.err.println("Empty line encountered");
                    continue;
                }
                String[] employeeData = line.split(cvsSplitBy);

                if (employeeData.length < headers.length) {
                    System.err.println("Line does not have expected number of columns: " + Arrays.toString(employeeData));
                    continue;
                }
                allEmployees.add(employeeData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allEmployees;
    }
}
