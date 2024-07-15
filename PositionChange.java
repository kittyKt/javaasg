package javaassignment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PositionChange {
    private String previousPosition;
    private String newPosition;
    private Date effectiveDate;
    private Double promotionRate;
    private Employment_Details employmentDetails;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PositionChange(String previousPosition, String newPosition, String effectiveDateStr, Double promotionRate) {
        this.previousPosition = previousPosition;
        this.newPosition = newPosition;
        this.effectiveDate = effectiveDate;
        setEffectiveDate(effectiveDateStr);
        setPromotionRate(promotionRate);
        this.employmentDetails = employmentDetails;
    
    
    if (this.employmentDetails != null && newPosition != null) {
            this.employmentDetails.setPositions(newPosition); // Update the position
        }
    }

    public String getPreviousPosition() {
        return previousPosition;
    }

    public String getNewPosition() {
        return newPosition != null ? newPosition : "N/A"; // Return "N/A" if newPosition is null
    }

    public void setNewPosition(String newPosition) {
        this.newPosition = newPosition; // Set the newPosition directly
    }
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDateStr) {
        if (effectiveDateStr == null || effectiveDateStr.trim().isEmpty()) {
            this.effectiveDate = null;
        } else {
            try {
                this.effectiveDate = sdf.parse(effectiveDateStr);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format for effective date.");
            }
        }
    }

    public Double getPromotionRate() {
        if (this.employmentDetails != null) {
            return this.employmentDetails.getPromotionRate();
            // Proceed with using promotionRate
        } else {
            // Handle the case where employmentDetails is null
            System.err.println("Employment details are not set.");
            return null;
        }
    }


        

   public void setPromotionRate(String promotionRateStr) {
        if (promotionRateStr == null || promotionRateStr.trim().isEmpty()) {
            this.promotionRate = null; // Set to null if the input is empty
        } else {
            try {
                this.promotionRate = Double.parseDouble(promotionRateStr); // Convert String to Double
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid format for promotion rate: " + promotionRateStr);
            }
        }
    }
   
   public void setPromotionRate(Double promotionRate) {
        this.promotionRate = promotionRate; // Accepts a Double directly
    }
    
    public String getChangeRecord() {
        return "From " + previousPosition + " to " + newPosition + " (Effective: " + formatDate(effectiveDate) + ")";
    }
    
    private String formatDate(Date date) {
        return sdf.format(date);
    }
}

