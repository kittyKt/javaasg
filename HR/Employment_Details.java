package javaassignment;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Employment_Details {
    private Date dateJoined;
    private Date dateResigned;
    private String roles;
    private String positions;
    private String department;
    private int annualLeave;
    private int medLeave; // sick leave
    private int maternityLeave;
    private int unpaidLeave;
    private double initialGrossSalary;
    private String newPosition;
    private Date effectiveDate;
    private String promotionDetail; // New field to store promotion detail
    private double salaryIncrement;
    private String salaryIncrementHistory = "";
    private double currentGrossSalary;

    private Employee_Details employee; 
    private boolean isInitialEntry = true; // Flag to indicate if it's initial entry

    public Employment_Details() {
    }
    
    public void completeInitialEntry() {
        this.isInitialEntry = false; // Mark the initial entry as complete
    }

    public Employment_Details(String dateJoined, String dateResigned, String positions,
                              String department, int annualLeave, int medLeave, int maternityLeave, int unpaidLeave,
                              double initialGrossSalary, double salaryIncrement) {
        this();
        setDateJoined(dateJoined);
        setDateResigned(dateResigned);
        setPositions(positions);
        setDepartment(department);
        setInitialGrossSalary(initialGrossSalary);
        setSalaryIncrement(salaryIncrement);
        this.effectiveDate = null;
        completeInitialEntry();
    }

    public String getNewPosition(){
        return newPosition;
    }

    public void setNewPosition(String newPosition) {
        this.newPosition = newPosition;
    }

    public Date getEffectiveDate(){
        return effectiveDate;
    }
    
    public void setEffectiveDate(String effectiveDateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        if (effectiveDateStr != null && !effectiveDateStr.trim().isEmpty()) {
            try {
                this.effectiveDate = sdf.parse(effectiveDateStr);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format for Effective Date.");
            }
        }
    }
        
    public String getPromotionDetail() {
        return promotionDetail;
    }
    
    private String formatPromotionDetail(String oldPosition, String newPosition, Date effectiveDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Choose your desired date format
        String effectiveDateStr = (effectiveDate != null) ? sdf.format(effectiveDate) : "N/A";
        if (oldPosition != null && newPosition != null) {
            return "From " + oldPosition + " promoted to " + newPosition + " (effective on: " + effectiveDateStr + ")";
        }
        return "Promotion details incomplete";
    }

    public void setPromotionDetail() {
        if (newPosition == null || newPosition.trim().isEmpty()) {
            this.promotionDetail = ""; // or "No promotion details available"
        } else {
            String oldPosition = this.positions;
            this.promotionDetail = formatPromotionDetail(oldPosition, this.newPosition, this.effectiveDate);
            this.positions = this.newPosition;
        }
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoinedStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        if (dateJoinedStr != null && !dateJoinedStr.trim().isEmpty()) {
            try {
                this.dateJoined = sdf.parse(dateJoinedStr);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format for date joined.");
            }
        } else if (!isInitialEntry) {
            throw new IllegalArgumentException("Date joined cannot be null or empty.");
        }
    }

    public Date getDateResigned(){
        return dateResigned;
    }

    public void setDateResigned(String dateResignedStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        if (dateResignedStr != null && !dateResignedStr.trim().isEmpty()) {
            try {
                this.dateResigned = sdf.parse(dateResignedStr);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format for date resigned.");
            }
        } else if (!isInitialEntry) {
            throw new IllegalArgumentException("Date resigned cannot be null or empty.");
        }
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        if (isInitialEntry || positions != null && !positions.trim().isEmpty()) {
            this.positions = positions;
        } else {
            throw new IllegalArgumentException("Position cannot be empty.");
        }
    }

    public String getDepartment() {
            System.out.println("Getting department: " + this.department);

        return department;
    }

    public void setDepartment(String department) {
        if (isInitialEntry || department != null && !department.trim().isEmpty()) {
            this.department = department;
        } else {
            throw new IllegalArgumentException("Department cannot be empty.");
        }
    }

    public int getAnnualLeave() {
        return annualLeave;
    }

    public int getMedLeave() {
        return medLeave;
    }

    public int getMaternityLeave() {
        return maternityLeave;
    }

    public int getUnpaidLeave() {
        return unpaidLeave;
    }

    public double getInitialGrossSalary() {

        return initialGrossSalary;
    }

    public void setInitialGrossSalary(double initialGrossSalary) {
        this.initialGrossSalary = initialGrossSalary;
    }

    public void setSalaryIncrement(double salaryIncrement) {
        System.out.println("Setting Salary Increment: " + salaryIncrement);
        this.salaryIncrement = salaryIncrement;
    }

    public double getSalaryIncrement() {
    System.out.println("Getting Salary Increment: " + this.salaryIncrement);

        return salaryIncrement;
    }
    
    public String getSalaryIncrementHistory() {
        return salaryIncrementHistory;
    }
   
    public void addSalaryIncrementHistory(String incrementDetail) {
        // This method can be used to update history, or you can directly use setSalaryIncrementHistory
        if (salaryIncrementHistory.isEmpty()) {
            salaryIncrementHistory = incrementDetail;
        } else {
            salaryIncrementHistory += "\n" + incrementDetail;
        }
    }
    
    public void setSalaryIncrementHistory(String history) {
        this.salaryIncrementHistory = history;
    }


    public double getCurrentGrossSalary() {
        return currentGrossSalary;
    }
    
   
    public void calculateLeaves() {
        if (dateJoined != null) {
            Date currentDate = new Date();
            Calendar joinCal = Calendar.getInstance();
            joinCal.setTime(dateJoined);

            Calendar currentCal = Calendar.getInstance();
            currentCal.setTime(currentDate);

            int diffYears = currentCal.get(Calendar.YEAR) - joinCal.get(Calendar.YEAR);

            if (joinCal.get(Calendar.MONTH) > currentCal.get(Calendar.MONTH) || 
                (joinCal.get(Calendar.MONTH) == currentCal.get(Calendar.MONTH) && joinCal.get(Calendar.DAY_OF_MONTH) > currentCal.get(Calendar.DAY_OF_MONTH))) {
                diffYears--;
            }

            if (dateResigned == null || dateResigned.after(currentDate)) {
                if (diffYears < 2) {
                    this.annualLeave = 8;
                } else if (diffYears <= 5) {
                    this.annualLeave = 12;
                } else {
                    this.annualLeave = 16;
                }

                if (diffYears < 2) {
                    this.medLeave = 14;
                } else {
                    this.medLeave = 18;
                }
                this.unpaidLeave = 8;
            } else {
                throw new IllegalArgumentException("Employee has resigned, leave entitlement cannot be set.");
            }
        } else {
            throw new IllegalArgumentException("Date joined is not set.");
        }
    }
    

    
   

}