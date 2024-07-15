package javaassignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.util.Arrays;

public class EmployeeSearch {
    private static double parsePromotionRate(String promotionRateStr) {
        if (promotionRateStr == null || promotionRateStr.trim().isEmpty()) {
            return 0.0; // Default to 0.0 if empty
        }
        promotionRateStr = promotionRateStr.trim().replace("%", ""); 
        try {
            double rate = Double.parseDouble(promotionRateStr);
            return rate / 100.0; // Convert to decimal
        } catch (NumberFormatException e) {
            return 0.0; // Default to 0.0 on error
        }
    }

    public static Employee_Details searchEmployee(String fileName, String searchQuery) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    String line;

    try {
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue;
            }
            String[] details = line.split(",");
            System.out.println("Parsed details: " + Arrays.toString(details)); // Debug output

            if (details.length >= 36 && details[2].trim().equalsIgnoreCase(searchQuery.trim())) {
                Employee_Details employee = new Employee_Details();

                double initialGrossSalary = Double.parseDouble(details[30]);
                double currentGrossSalary = Double.parseDouble(details[31]);
                String promotionRateStr = details[36]; 
                double promotionRate = parsePromotionRate(promotionRateStr);
                
               

                SalaryIncrementHistory incrementHistory = new SalaryIncrementHistory();
                String[] increments = details[32].split("; ");
                for (String increment : increments) {
                    String[] parts = increment.split(": \\+");
                    if (parts.length == 2) {
                        Date date = parseDate(parts[0]);
                        double amount = Double.parseDouble(parts[1].replace(";","").trim());
                        incrementHistory.addIncrementHistory(date, amount);
                    }
                }

                employee.setFirstNm(details[0]);
                employee.setLastNm(details[1]);
                employee.setNric(details[2]);
                employee.setDob(details[3]);
                employee.setRace(details[4]);
                employee.setReligious(details[5]);
                employee.setPob(details[6]);
                employee.setEmail(details[7]);
                employee.setHm_addr(details[8]);
                employee.setGender(details[9]);
                employee.setMarital(details[10]);
                employee.setHp_num(details[11]);
                employee.setBank_nm(details[12]);
                employee.setAcc_num(details[13]);
                employee.setEpf_num(details[14]);
                employee.setTax_num(details[15]);
                employee.setFm_detail(details[16], details[17], details[18], details[19]);
                employee.setCompetencies(details[20]);

                Employment_Details employmentDetails = new Employment_Details();
                employmentDetails.setDateJoined(details[21]);
                employmentDetails.setDateResigned(details[22]);
                employmentDetails.setRoles(details[23]);
                employmentDetails.setPositions(details[24]);
                employmentDetails.setDepartment(details[25]);
                employmentDetails.setInitialGrossSalary(initialGrossSalary);
                employee.setEmploymentDetails(employmentDetails);

                employee.getEmploymentDetails().calculateLeaves();
                employee.setCurrentGrossSalary(currentGrossSalary);
                employee.setIncrementHistory(incrementHistory);
                String previousPosition = employmentDetails.getPositions();
                String newPosition = details[33];
                String effectiveDateStr = details[34];
                Date effectiveDate = parseDate(effectiveDateStr);

                employmentDetails.setNewPosition(newPosition);
                employmentDetails.setEffectiveDate(formatDate(effectiveDate));
                employmentDetails.setPromotionRate(promotionRate);
                employmentDetails.setPromotionRate(promotionRate); 
                PositionChange positionChange = new PositionChange(previousPosition, newPosition, effectiveDateStr, promotionRate);
                List<PositionChange> positionChanges = new ArrayList<>();
                positionChanges.add(positionChange);
                employmentDetails.setPositionChangeHistory(positionChanges);
                employmentDetails.setPromotionRate(promotionRate);

                return employee;
            }
        }
    } finally {
        reader.close();
    }
    return null; // Return null if no employee is found
}

    public static void updateEmployee(String fileName, Employee_Details updatedEmployee) throws IOException {

        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] details = line.split(",");
            if (details[2].equalsIgnoreCase(updatedEmployee.getNric())) {
                String dateJoinedStr = updatedEmployee.getEmploymentDetails().getDateJoined() != null
                        ? formatDate(updatedEmployee.getEmploymentDetails().getDateJoined())
                        : "";

                String dateResignedStr = updatedEmployee.getEmploymentDetails().getDateResigned() != null
                        ? formatDate(updatedEmployee.getEmploymentDetails().getDateResigned())
                        : "";

                int maternityLeave = 0; //Default value
                if ("Female".equalsIgnoreCase(updatedEmployee.getGender())
                        && "Married".equalsIgnoreCase(updatedEmployee.getMarital())) {
                    maternityLeave = 98;
                } else {
                    maternityLeave = 0;
                }

                StringBuilder incrementHistoryBuilder = new StringBuilder();
                for (SalaryIncrementHistory.SalaryIncrement increment : updatedEmployee.getIncrementHistory().getIncrementHistory()) {
                    incrementHistoryBuilder.append(formatDate(increment.getIncrementDate())).append(": +")
                            .append(String.format("%.2f", increment.getIncrementAmount())).append("; ");
                }

                double initialGrossSalary = updatedEmployee.getEmploymentDetails().getInitialGrossSalary();
                double currentGrossSalary = updatedEmployee.getIncrementHistory().calculateCurrentGrossSalary(initialGrossSalary, updatedEmployee.getEmploymentDetails());
                String promotionRateStr = updatedEmployee.getEmploymentDetails().getPromotionRateString(); // Get the string input
                double promotionRateDecimal = parsePromotionRate(promotionRateStr); // Convert to decimal
                double promotionAmount = currentGrossSalary * promotionRateDecimal;
                
                
                currentGrossSalary += promotionAmount; // Update the gross salary
                                // Log the promotion increment in history
                Date effectiveDate = updatedEmployee.getEmploymentDetails().getEffectiveDate();
                updatedEmployee.getIncrementHistory().addIncrementHistory(effectiveDate, promotionAmount); // Log the increment amount
                
                incrementHistoryBuilder.append(formatDate(effectiveDate)).append(": +")
                       .append(String.format("%.2f", promotionAmount)).append("; ");
                System.out.println("Adding promotion increment: Effective Date: " + formatDate(effectiveDate) + ", Amount: " + promotionAmount);
                updatedEmployee.getIncrementHistory().addIncrementHistory(effectiveDate, promotionAmount);
                System.out.println("Increment history after adding: " + updatedEmployee.getIncrementHistory().getIncrementHistory());

                



                // Use promotionRateDecimal in salary calculation
                
                String oldPosition = updatedEmployee.getEmploymentDetails().getPositions();
                String newPosition = updatedEmployee.getEmploymentDetails().getNewPosition();
                String effectiveDateStr = updatedEmployee.getEmploymentDetails().getEffectiveDate() != null
                                       ? formatDate(updatedEmployee.getEmploymentDetails().getEffectiveDate())
                                       : "";                
                String updatedPositions = oldPosition.replace(oldPosition, newPosition);
                updatedEmployee.getEmploymentDetails().setPositions(updatedPositions);
                

                StringBuilder positionChangeHistoryBuilder = new StringBuilder();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                for (PositionChange positionChange : updatedEmployee.getEmploymentDetails().getPositionChangeHistory()) {
                    String formattedEffectiveDate = dateFormat.format(updatedEmployee.getEmploymentDetails().getEffectiveDate());
                    positionChangeHistoryBuilder.append("Previous Position: ")
                                                 .append(positionChange.getPreviousPosition())
                                                 .append(" promoted to ")
                                                 .append(updatedEmployee.getEmploymentDetails().getNewPosition())
                                                 .append(" (EffectiveDate: ")
                                                 .append(formattedEffectiveDate)
                                                 .append("); ");
                }
                
                String positionChanges = positionChangeHistoryBuilder.length() > 0 ? positionChangeHistoryBuilder.toString().trim() : "N/A";
                




                line = String.join(",",
                        updatedEmployee.getFirstNm(),
                        updatedEmployee.getLastNm(),
                        updatedEmployee.getNric(),
                        formatDate(updatedEmployee.getDob()),
                        updatedEmployee.getRace(),
                        updatedEmployee.getReligious(),
                        updatedEmployee.getPob(),
                        updatedEmployee.getEmail(),
                        updatedEmployee.getHm_addr(),
                        updatedEmployee.getGender(),
                        updatedEmployee.getMarital(),
                        updatedEmployee.getHp_num(),
                        updatedEmployee.getBank_nm(),
                        updatedEmployee.getAcc_num(),
                        updatedEmployee.getEpf_num(),
                        updatedEmployee.getTax_num(),
                        updatedEmployee.getFm_nm(),
                        updatedEmployee.getFm_nric(),
                        updatedEmployee.getRelation(),
                        updatedEmployee.getFm_hp(),
                        updatedEmployee.getWork_exp(),
                        dateJoinedStr,
                        dateResignedStr,
                        updatedEmployee.getEmploymentDetails().getRoles(),
                        updatedEmployee.getEmploymentDetails().getPositions(),
                        updatedEmployee.getEmploymentDetails().getDepartment(),
                        String.valueOf(updatedEmployee.getEmploymentDetails().getAnnualLeave()),
                        String.valueOf(updatedEmployee.getEmploymentDetails().getMedLeave()),
                        String.valueOf(maternityLeave),
                        String.valueOf(updatedEmployee.getEmploymentDetails().getUnpaidLeave()),
                        String.valueOf(updatedEmployee.getEmploymentDetails().getInitialGrossSalary()),
                        String.valueOf(currentGrossSalary),
                        incrementHistoryBuilder.toString(),
                        updatedEmployee.getEmploymentDetails().getNewPosition(),
                        effectiveDateStr,
                        positionChanges,
                        String.valueOf(promotionRateDecimal)

                );

            }
            lines.add(line);
        }
        reader.close();

        try (FileWriter writer = new FileWriter(fileName)) {
            for (String updatedLine : lines) {
                // Write each updated line to the file
                writer.write(updatedLine + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle or log the exception as needed
        }
    }

    private static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    private static Date parseDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()){
            return null;
        }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return dateFormat.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter IC or Name to search:");
        String searchQuery = scanner.nextLine();

        try {
            Employee_Details result = searchEmployee("Employee_Details.csv", searchQuery);
            if (result != null) {
                System.out.println("Employee Found. Opening the form...");
                SwingUtilities.invokeLater(() -> {
                    EmployeeDetailsForm form = new EmployeeDetailsForm();
                    form.populateForm(result); //populate the form with found employee details
                    form.setVisible(true);
                    form.addUpdateListener(updatedEmployee -> {
                        try {
                            updateEmployee("Employee_Details.csv", updatedEmployee);
                            System.out.println("Employee details updated successfully.");
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Error updating employee details.");
                        }
                    });
                });

            } else {
                System.out.println("Employee Not found");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading CSV file.");
        }
        scanner.close();
    }


    
}

