// increment missing
//why promotion rate is 0.0 not N/A


package javaassignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.text.ParseException;
import java.util.Date;

public class Employee_DetailsCSV {

    private static final String CSV_HEADER = "FirstNm, LastNm, NRIC, DOB, Race, Rel, POB, "
            + "Email, HomeAdd, Gender, Marital, Hp, BankNm, AccNum, EPF, "
            + "TIN, Fm_Nm, Fm_NRIC, Relation, Fm_hp, "
            + "Work_exp, DateJoined, DateResigned, Roles, Positions, Department, "
            + "AnnualLeave, MedLeave, MaternityLeave, UnpaidLeave, InitGrossSalary, CurrGrossSalary, IncrementHistory, "
            + "NewPosition, EffectiveD, PositionChangeHistory,  PromotionRate";

    private static final SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final List<SimpleDateFormat> dateFormat = Arrays.asList(
        new SimpleDateFormat("yyyy-MM-dd"),
        new SimpleDateFormat("MM/dd/yyyy")
    );    
    
    
    private static String formatDate(Date date) {
        return date != null ? outputDateFormat.format(date) : ""; //**
    }

    private static Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }

        for (SimpleDateFormat format : dateFormat) {
            try {
                return format.parse(dateStr);
            } catch (ParseException e) {
                // Continue to the next format
            }
        }

        System.err.println("Error parsing date: " + dateStr);
        throw new IllegalArgumentException("Invalid date format: " + dateStr);
    }


    
    //saving
    public static void saveEmployeeDetails(String fileName, List<Employee_Details> employeeDetailsList) throws IOException {
        boolean fileExists = new File(fileName).exists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            if (!fileExists){
                writer.write(CSV_HEADER);
                writer.newLine();
            }

            for (Employee_Details details : employeeDetailsList) {
                writer.append(details.getFirstNm()).append(",");
                writer.append(details.getLastNm()).append(",");
                writer.append(details.getNric()).append(",");
                writer.append(formatDate(details.getDob())).append(",");
                writer.append(details.getRace()).append(",");
                writer.append(details.getReligious()).append(",");
                writer.append(details.getPob()).append(",");
                writer.append(details.getEmail()).append(",");
                writer.append(details.getHm_addr()).append(",");
                writer.append(details.getGender()).append(",");
                writer.append(details.getMarital()).append(",");
                writer.append(details.getHp_num()).append(",");
                writer.append(details.getBank_nm()).append(",");
                writer.append(details.getAcc_num()).append(",");
                writer.append(details.getEpf_num()).append(",");
                writer.append(details.getTax_num()).append(",");
                writer.append(details.getFm_nm()).append(",");
                writer.append(details.getFm_nric()).append(",");
                writer.append(details.getRelation()).append(",");
                writer.append(details.getFm_hp()).append(",");
                writer.append(details.getWork_exp()).append(",");

                Employment_Details empDetails = details.getEmploymentDetails();
                if (empDetails != null) {

                    writer.append(formatDate(empDetails.getDateJoined())).append(",");
                    writer.append(formatDate(empDetails.getDateResigned())).append(",");
                    writer.append(empDetails.getRoles()).append(",");
                    writer.append(empDetails.getPositions()).append(",");
                    writer.append(empDetails.getDepartment()).append(",");
                    
                    empDetails.calculateLeaves();

                    writer.append(String.valueOf(empDetails.getAnnualLeave())).append(",");
                    writer.append(String.valueOf(empDetails.getMedLeave())).append(",");
                    
                    if ("Female".equalsIgnoreCase(details.getGender()) && "Married".equalsIgnoreCase(details.getMarital())) {
                        writer.append("98").append(","); // Set maternity leave to 98
                    } else {
                        writer.append(String.valueOf(empDetails.getMaternityLeave())).append(",");
                    }
                    writer.append(String.valueOf(empDetails.getUnpaidLeave())).append(",");
                    writer.append(String.valueOf(empDetails.getInitialGrossSalary())).append(",");

                    SalaryIncrementHistory incrementHistory = new SalaryIncrementHistory();
                    incrementHistory.calculateIncrementHistory(empDetails.getDateJoined(), empDetails.getInitialGrossSalary());
                    double currentGrossSalary = incrementHistory.calculateCurrentGrossSalary(empDetails.getInitialGrossSalary(), empDetails);


                    writer.append(String.valueOf(currentGrossSalary)).append(",");
                    
                    String incrementHistoryStr = incrementHistory.getIncrementHistory(empDetails.getDateJoined(), empDetails.getInitialGrossSalary());
                    writer.append(incrementHistoryStr).append(",");
                    
                    
                    String newPosition = empDetails.getNewPosition(); // Get the new position
                    Date effectiveDate = empDetails.getEffectiveDate(); // Get the effective date (make sure this method exists)
                    writer.append(newPosition != null ? newPosition : "N/A").append(","); // Write new position
                    writer.append(formatDate(effectiveDate)).append(",");

                    StringBuilder positionChangeHistoryBuilder = new StringBuilder();
                    if (empDetails.getPositionChangeHistory() != null && !empDetails.getPositionChangeHistory().isEmpty()) {
                        for (PositionChange change : empDetails.getPositionChangeHistory()) {
                            positionChangeHistoryBuilder.append(change.getChangeRecord()).append("; ");
                        }
                    } else {
                        positionChangeHistoryBuilder.append("N/A"); // Append N/A if no position changes
                    }
                    writer.append(positionChangeHistoryBuilder.toString().trim()).append(",");

                    // Handle PromotionRate similarly
                    Double promotionRate = empDetails.getPromotionRate();
                    if (promotionRate != null) {
                        writer.append(String.valueOf(promotionRate)).append(",");
                    } else {
                        writer.append("N/A").append(","); // Append N/A if promotion rate is null
                    }


                        
                        
                }

                writer.newLine(); // Ensure each employee's details are on a new line
            }
        }
    }
    
  
    
    
    //read
    public static List<Employee_Details> loadEmployeeDetails(String fileName) throws IOException {
        List<Employee_Details> employeeDetailsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Skip header line assuming the first line is header
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1);

                Employee_Details details = new Employee_Details();
                details.setFirstNm(fields[0]);
                details.setLastNm(fields[1]);
                details.setNric(fields[2]);
                String dobStr = parseDate(fields[3]) != null ? formatDate(parseDate(fields[3])) : null;
                details.setDob(dobStr);                
                details.setReligious(fields[5]);
                details.setPob(fields[6]);
                details.setEmail(fields[7]);
                details.setHm_addr(fields[8]);
                details.setGender(fields[9]);
                details.setMarital(fields[10]);
                details.setHp_num(fields[11]);
                details.setBank_nm(fields[12]);
                details.setAcc_num(fields[13]);
                details.setEpf_num(fields[14]);
                details.setTax_num(fields[15]);
                String fm_nm = fields[16];
                String fm_nric = fields[17];
                String relation = fields[18];
                String fm_hp = fields[19];
                details.setFm_detail(fm_nm, fm_nric, relation, fm_hp);
                String work_exp = fields[20];
                details.setCompetencies(work_exp);

                Employment_Details empDetails = new Employment_Details();
                //if (empDetails != null) {
                
                    String dateJoinedStr = parseDate(fields[21]) != null ? formatDate(parseDate(fields[21])) : null;
                    empDetails.setDateJoined(dateJoinedStr);

                    String dateResignedStr = parseDate(fields[22]) != null ? formatDate(parseDate(fields[22])) : null;
                    empDetails.setDateResigned(dateResignedStr);
                    empDetails.setRoles(fields[23]);
                    empDetails.setPositions(fields[24]);
                    empDetails.setDepartment(fields[25]);

                    empDetails.calculateLeaves();

                    empDetails.setInitialGrossSalary(Double.parseDouble(fields[30]));
                    
                    String incrementHistoryStr = fields[32];
                    if (!"N/A".equals(incrementHistoryStr.trim())) {
                        String[] increments = incrementHistoryStr.split("; ");
                        SalaryIncrementHistory incrementHistory = new SalaryIncrementHistory();
                        for (String increment : increments) {
                            String[] parts = increment.split(": \\+");
                            if (parts.length == 2) {
                                Date date = parseDate(parts[0]);
                                double amount = Double.parseDouble(parts[1]);
                                incrementHistory.addIncrementHistory(date, amount);
                            }
                        }
                        empDetails.setSalaryIncrementHistory(incrementHistory);
                    }
                    
                    empDetails.setNewPosition(fields[33]);
                    empDetails.setEffectiveDate(fields[34]);

                    String positionChangeStr = fields[35];
                    if ("N/A".equals(positionChangeStr.trim())) {
                        empDetails.setPositionChangeHistory(new ArrayList<>());
                    } else {
                        String[] changes = positionChangeStr.split("; ");
                        for (String changeStr : changes) {
                            String[] parts = changeStr.split(" to | \\(Effective: ");
                            String previousPosition = parts[0].substring(5);
                            String newPosition = parts[1];
                            String effectiveDateStr = parts[2].replace(")", "");
                            PositionChange positionChange = new PositionChange(previousPosition, newPosition, effectiveDateStr, null);
                            empDetails.addPositionChange(positionChange);
                        }
                    }
                               
                    String promotionRateStr = fields[36]; 
                    if ("N/A".equals(promotionRateStr.trim())) {
                        empDetails.setPromotionRate(null); // Set as null if N/A
                    } else {
                        empDetails.setPromotionRate(Double.parseDouble(promotionRateStr)); // Convert string to Double
                    }
                    
                    details.setEmploymentDetails(empDetails);

                    employeeDetailsList.add(details);
                //}**/
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        return employeeDetailsList;
    }
    
    
    
   
}


                
                
        
    
    
