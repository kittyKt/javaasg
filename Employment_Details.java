

//change position

package javaassignment;

import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Employment_Details {
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
    private double currentGrossSalary;
    private String newPosition;
    private List<PositionChange> positionChangeHistory;
    private Date effectiveDate;
    private Double promotionRate;
    private String promotionRateString;
    private SalaryIncrementHistory salaryIncrementHistory;
    private Employee_Details employee; 
    private static final double INCREMENT_PERCENTAGE = 0.05; // 5% annual increment
    
    public Employment_Details() {
        
        this.salaryIncrementHistory = new SalaryIncrementHistory();
        this.positionChangeHistory = new ArrayList<>();
    }
    

    public Employment_Details(String dateJoined, String dateResigned, String roles, String positions,
                              String department, int annualLeave, int medLeave, int maternityLeave, int unpaidLeave,
                              double initialGrossSalary) {
        this();
        setDateJoined(dateJoined);
        setDateResigned(dateResigned);
        setRoles(roles);
        setPositions(positions);
        setDepartment(department);
        setInitialGrossSalary(initialGrossSalary);

        // Calculate leaves and current gross salary if dateJoined is set and dateResigned is null
        if (this.dateJoined != null && this.dateResigned == null) {
            salaryIncrementHistory.calculateIncrementHistory(this.dateJoined, this.initialGrossSalary);
            calculateLeaves();
            calculateCurrentGrossSalary();
        }
    }

    // Getters and setters for dateJoined, dateResigned, roles, positions, department

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoinedStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            Date dateJoined = sdf.parse(dateJoinedStr);
            this.dateJoined = dateJoined;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format for date joined.");
        }
}
    
     public Date getDateResigned() {
        return dateResigned;
    }


    public void setDateResigned(String dateResignedStr) {
        if (dateResignedStr == null || dateResignedStr.trim().isEmpty()) {
            this.dateResigned = null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);

            try {
                this.dateResigned = sdf.parse(dateResignedStr);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format for date resigned.");
            }
    }
}
    // Other getters and setters

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        if (roles != null && !roles.trim().isEmpty()) {
            this.roles = roles;
        } else {
            throw new IllegalArgumentException("Roles cannot be empty.");
        }
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        if (positions != null && !positions.trim().isEmpty()) {
            this.positions = positions;
        } else {
            throw new IllegalArgumentException("Position cannot be empty.");
        }
    
 }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department != null && !department.trim().isEmpty()) {
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

    public double getCurrentGrossSalary() {
        return currentGrossSalary;
    }

    public void calculateCurrentGrossSalary() {
        if (salaryIncrementHistory != null) {
            this.currentGrossSalary = salaryIncrementHistory.calculateCurrentGrossSalary(this.initialGrossSalary, this);
        } else {
            throw new IllegalStateException("SalaryIncrementHistory is not set.");
    }
}
    
    public SalaryIncrementHistory getSalaryIncrementHistory() {
        return salaryIncrementHistory;
    }

    public void setSalaryIncrementHistory(SalaryIncrementHistory salaryIncrementHistory) {
        this.salaryIncrementHistory = salaryIncrementHistory;
    }

    private Date addYearsToDate(Date date, int years) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        cal.add(java.util.Calendar.YEAR, years);
        return cal.getTime();
    }

    public void calculateLeaves() {
        if (dateJoined != null) {
            Date currentDate = new Date();
            long diffMillisSecond = currentDate.getTime() - dateJoined.getTime();
            long diffYears = diffMillisSecond / (1000L * 60 * 60 * 24 * 365);

            if (dateResigned == null || dateResigned.after(currentDate)) {
                // Calculate annual leave based on years of service
                if (diffYears <= 2) {
                    this.annualLeave = 8;
                } else if (diffYears >= 2 && diffYears <= 5) {
                    this.annualLeave = 12;
                } else {
                    this.annualLeave = 16;
                }

                // Calculate medical leave
                if (diffYears < 2) {
                    this.medLeave = 14;
                } else {
                    this.medLeave = 18;
                }

                // Calculate maternity leave for married female employees
                

            // Calculate maternity leave for married female employees
                if (employee != null && "Female".equalsIgnoreCase(employee.getGender()) && "Married".equalsIgnoreCase(employee.getMarital())) {
                    maternityLeave = 98;
                } else {
                    maternityLeave = 0;
                }

                // Set unpaid leave to 8 days (assuming company policy)
                this.unpaidLeave = 8;

            } else {
                throw new IllegalArgumentException("Employee has resigned, leave entitlement cannot be set.");
            }
        } else {
            throw new IllegalArgumentException("Date joined is not set.");
        }
    }


    public String getNewPosition(){
        return newPosition != null ? newPosition : "N/A"; // Return "N/A" if newPosition is null
    }
    
    public void setNewPosition(String newPosition){
        this.newPosition = newPosition;
    }
        
    
    public Date getEffectiveDate(){
        return effectiveDate; 
    }
    
    public void setEffectiveDate(String effectiveDateStr){
        if (effectiveDateStr == null || effectiveDateStr.trim().isEmpty()) {
            this.effectiveDate = null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);

            try {
                this.effectiveDate = sdf.parse(effectiveDateStr);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format for effective date.");
            }
        }
    }
        
    public Double getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(Double promotionRate) {
        this.promotionRate = promotionRate; // Accepts any rate, including null
    }
    
    public String getPromotionRateString() {
        return promotionRateString;
    }

    public void setPromotionRateString(String promotionRateString) {
        this.promotionRateString = promotionRateString;
    }



    
    
    public List<PositionChange> getPositionChangeHistory(){
        return positionChangeHistory;
        
    }
    
    public void setPositionChangeHistory(List<PositionChange> positionChangeHistory) {
        this.positionChangeHistory = positionChangeHistory; // Setter for position changes
    }
    
    
    public void addPositionChange(PositionChange positionChange) {
    this.positionChangeHistory.add(positionChange);
    this.positions = positionChange.getNewPosition(); // Update current position
}
    
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    
    public void setCurrentGrossSalary(double newSalary) {
        this.currentGrossSalary = newSalary;
    }
    
    public void updatePosition(String newPosition, String effectiveDateStr) {
        String previousPosition = this.positions;
        this.positions = newPosition;

        // Create a new PositionChange
        PositionChange positionChange = new PositionChange(previousPosition, newPosition, effectiveDateStr, getPromotionRate());
        
        // Add to position change history
        if (positionChangeHistory == null) {
            positionChangeHistory = new ArrayList<>();
        }
        positionChangeHistory.add(positionChange);
    }
}

    
        
    
    
    
    

    

    


    