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

    public List<String[]> findEmployeeByNameOrNRIC (String searchQuery) {
        String line = "";
        String csvFile = "C:\\Users\\Hiew\\Documents\\NetBeansProjects\\Assignment1\\src\\main\\java\\csv\\Employees_DetailsCSV.csv";
        String cvsSplitBy = ",";
        List<String[]> matchingEmployees = new ArrayList<> ();
        String searchQueryLower = searchQuery.trim ().toLowerCase ();

        try (BufferedReader br = new BufferedReader (new FileReader (csvFile))) {
            String[] headers = br.readLine ().split (cvsSplitBy);
            while ((line = br.readLine ()) != null) {
                line = line.trim ();
                if (line.isEmpty ()) {
                    System.err.println ("Empty line encountered");
                    continue;
                }

                String[] employeeData = line.split (cvsSplitBy);

                if (employeeData.length < 30) {
                    System.err.println ("Line does not have expected number of columns: " + Arrays.toString (employeeData));
                    continue;
                }

                String firstName = employeeData[0].trim ().toLowerCase ();
                String lastName = employeeData[1].trim ().toLowerCase ();
                String nric = employeeData[2].trim ().toLowerCase ();

                if (firstName.contains (searchQueryLower) || lastName.contains (searchQueryLower) || nric.equals (searchQueryLower)) {
                    matchingEmployees.add (employeeData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return matchingEmployees;
    }

    public String[] getEmployeeDetails (String employeeNRIC) {
        String line = "";
        String csvFile = "C:\\Users\\Hiew\\Documents\\NetBeansProjects\\Assignment1\\src\\main\\java\\csv\\Employees_DetailsCSV.csv";
        String cvsSplitBy = ",";
        String employeeNRICLower = employeeNRIC.trim ().toLowerCase ();

        try (BufferedReader br = new BufferedReader (new FileReader (csvFile))) {
            while ((line = br.readLine ()) != null) {
                line = line.trim ();
                if (line.isEmpty ()) {
                    System.err.println ("Empty line encountered");
                    continue;
                }

                String[] employeeData = line.split (cvsSplitBy);

                if (employeeData.length < 3) {
                    System.err.println ("Line does not have enough columns: " + Arrays.toString (employeeData));
                    continue;
                }

                String nric = employeeData[2].trim ().toLowerCase ();

                if (nric.equals (employeeNRICLower)) {
                    return employeeData;
                }
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }

        return null;
    }

    public List<String[]> readAllEmployees () {
        String line = "";
        String csvFile = "C:\\Users\\Hiew\\Documents\\NetBeansProjects\\Assignment1\\src\\main\\java\\csv\\Employees_DetailsCSV.csv";
        String cvsSplitBy = ",";
        List<String[]> allEmployees = new ArrayList<> ();

        try (BufferedReader br = new BufferedReader (new FileReader (csvFile))) {
            String[] headers = br.readLine ().split (cvsSplitBy);

            while ((line = br.readLine ()) != null) {
                line = line.trim ();

                if (line.isEmpty ()) {
                    System.err.println ("Empty line encountered");
                    continue;
                }
                String[] employeeData = line.split (cvsSplitBy);

                if (employeeData.length < headers.length) {
                    System.err.println ("Line does not have expected number of columns: " + Arrays.toString (employeeData));
                    continue;
                }
                allEmployees.add (employeeData);
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return allEmployees;
    }

    public List<String[]> getAttendanceRecords (String username, String selectedMonthNumber, String currentYear) {
        List<String[]> attendanceRecords = new ArrayList<> ();
        String csvFile = "C:\\Users\\Hiew\\Documents\\NetBeansProjects\\Assignment1\\src\\main\\java\\csv\\attendance_records.csv"; // Path to your attendance CSV file
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader (new FileReader (csvFile))) {
            br.readLine ();
            while ((line = br.readLine ()) != null) {
                String[] record = line.split (cvsSplitBy);

                System.out.println ("Record length: " + record.length);
                System.out.println ("Record: " + Arrays.toString (record));

                if (record.length < 6) {
                    System.out.println ("Skipping record due to insufficient columns: " + line);
                    continue;
                }

                String date = record[1];
                String recordUsername = record[0];

                String recordMonth = date.substring (5, 7); // MM
                String recordYear = date.substring (0, 4); // YYYY

                // Check if the record matches the given username and date
                if (recordUsername.equals (username) && recordMonth.equals (selectedMonthNumber) && recordYear.equals (currentYear)) {
                    attendanceRecords.add (record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }
        System.out.println (attendanceRecords);
        return attendanceRecords;
    }

    public int countLateDays (String username, String selectedMonthNumber, String currentYear) {
        int lateDaysCount = 0;
        String csvFile = "C:\\Users\\Hiew\\Documents\\NetBeansProjects\\Assignment1\\src\\main\\java\\csv\\attendance_records.csv"; // Path to your attendance CSV file
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader (new FileReader (csvFile))) {
            // Read and skip the header line if present
            br.readLine ();

            while ((line = br.readLine ()) != null) {
                String[] record = line.split (cvsSplitBy);

                // Check if the record length is sufficient
                if (record.length < 6) {
                    System.out.println ("Skipping record due to insufficient columns: " + line);
                    continue;
                }

                // Assuming the columns are as follows:
                // Username, Date, ClockInTime, ClockOutTime, TotalHoursWorked, Notes
                String date = record[1];
                String recordUsername = record[0];
                String notes = record[5];

                // Extract month and year from the date string
                String recordMonth = date.substring (5, 7);
                String recordYear = date.substring (0, 4);
                
                
                
                System.out.println ("username: "+username);
                System.out.println ("month: "+selectedMonthNumber);
                System.out.println ("year"+ currentYear);

                if (recordUsername.equals (username) && recordMonth.equals (selectedMonthNumber) && recordYear.equals (currentYear)) {
                    if ("Late".equalsIgnoreCase (notes)) {
                        lateDaysCount++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }

        System.out.println ("Late Days Count here: " + lateDaysCount);
        return lateDaysCount;
    }
}
