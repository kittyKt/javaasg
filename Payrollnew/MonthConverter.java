package Payroll;

import java.util.HashMap;
import java.util.Map;

public class MonthConverter {

    private static final Map<String, String> monthMap = new HashMap<>();

    static {
        // Initialize the month mapping
        monthMap.put("January", "01");
        monthMap.put("February", "02");
        monthMap.put("March", "03");
        monthMap.put("April", "04");
        monthMap.put("May", "05");
        monthMap.put("June", "06");
        monthMap.put("July", "07");
        monthMap.put("August", "08");
        monthMap.put("September", "09");
        monthMap.put("October", "10");
        monthMap.put("November", "11");
        monthMap.put("December", "12");
    }

    public static String getMonthNumber(String monthName) {
        return monthMap.getOrDefault(monthName, "Invalid month");
    }

    public static void main(String[] args) {
        String monthName = "September";
        String monthNumber = getMonthNumber(monthName);

        System.out.println("The month number for " + monthName + " is " + monthNumber);
    }
}
