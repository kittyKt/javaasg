package javaassignment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class SalaryIncrementHistory {
    private Employment_Details employmentDetails;
    private List<SalaryIncrement> incrementHistory;
    private static final double INCREMENT_PERCENTAGE = 0.05; // 5% annual increment

    public SalaryIncrementHistory() {
        this.incrementHistory = new ArrayList<>();

    }


    
    
    public void setEmploymentDetails(Employment_Details employmentDetails) {
    this.employmentDetails = employmentDetails;
}

    // Getter for incrementHistory
    public List<SalaryIncrement> getIncrementHistory() {
        return incrementHistory;
    }

    // Method to calculate salary increment history
    public void calculateIncrementHistory(Date dateJoined, double initialGrossSalary) {
        if (dateJoined != null) {
            Date currentDate = new Date();
            long diffMillisSecond = currentDate.getTime() - dateJoined.getTime();
            long diffYears = diffMillisSecond / (1000L * 60 * 60 * 24 * 365);

            double currentSalary = initialGrossSalary;

            for (int i = 0; i < diffYears; i++) {
                // Calculate increment based on current salary
                double incrementAmount = currentSalary * INCREMENT_PERCENTAGE;

                // Calculate new current salary after increment
                currentSalary += incrementAmount;

                // Add increment record to history
                addIncrementHistory(addYearsToDate(dateJoined, i + 1), incrementAmount);
            }
        }
    }

    // Helper method to add years to a date
    private Date addYearsToDate(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    // Method to add an increment record to history
    public void addIncrementHistory(Date incrementDate, double incrementAmount) {
            this.incrementHistory.add(new SalaryIncrement(incrementDate, incrementAmount));
    }

    public String getIncrementHistory(Date dateJoined, double initialGrossSalary) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    StringBuilder incrementHistoryStr = new StringBuilder();

    for (SalaryIncrement increment : incrementHistory) {
        incrementHistoryStr.append(dateFormat.format(increment.getIncrementDate()))
                           .append(": +")
                           .append(String.format("%.2f", increment.getIncrementAmount()))
                           .append("; ");
    }

    return incrementHistoryStr.toString().trim();
}


    // Method to calculate current gross salary based on history
    public double calculateCurrentGrossSalary(double initialGrossSalary, Employment_Details employmentDetails) {

        if (employmentDetails == null) {
            throw new IllegalStateException("Employment_Details is not set.");
        }

        // Start with the initial gross salary
        double currentGrossSalary = initialGrossSalary;

        if (incrementHistory == null){
            incrementHistory = new ArrayList<>();
        }

        // Apply all increments from the increment history
        for (SalaryIncrement increment : incrementHistory) {
            currentGrossSalary += increment.getIncrementAmount(); // Add each increment amount
        }

        // Promotion rate is applied only after calculating increments
        Double promotionRate = employmentDetails.getPromotionRate();
        if (promotionRate != null) {
            double promotionIncrement = currentGrossSalary * promotionRate; // Calculate promotion increment
                        currentGrossSalary += promotionIncrement; // Apply promotion increment
                        // Optionally log the promotion increment
                        addIncrementHistory(new Date(), promotionIncrement);        
        }
        return currentGrossSalary; // Return the calculated current gross salary
    }



    // Nested class for salary increment details
    public static class SalaryIncrement {
        private Date incrementDate;
        private double incrementAmount;

        public SalaryIncrement(Date incrementDate, double incrementAmount) {
            this.incrementDate = incrementDate;
            this.incrementAmount = incrementAmount;
        }

        // Getters and setters for SalaryIncrement fields
        public Date getIncrementDate() {
            return incrementDate;
        }

        public void setIncrementDate(Date incrementDate) {
            this.incrementDate = incrementDate;
        }

        public double getIncrementAmount() {
            return incrementAmount;
        }

        public void setIncrementAmount(double incrementAmount) {
            this.incrementAmount = incrementAmount;
        }
        
        public String toString() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(incrementDate) + ": +" + String.format("%.2f", incrementAmount);
    }

    }
    
}
