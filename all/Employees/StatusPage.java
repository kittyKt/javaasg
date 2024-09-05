package Employees;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatusPage {
    private String csvFile;
    
    public StatusPage(String csvFile){
        this.csvFile = csvFile;
    }
    
    public List<String[]> getUserLeaves(String userName){
        List<String[]> userLeaves = new ArrayList<>();
       
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))){
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; 
                }
                String[] fields = line.split(",");
                for(int i = 0 ; i< fields.length; i++){
                    fields[i] = fields[i].trim(); 
                }
            
                System.out.println("Read line: " + line); // Debug print
                if (fields[0].trim().equalsIgnoreCase(userName)) {
                    userLeaves.add(fields);
                    System.out.println("Match found for user: " + userName); // Debug print
                }
            }
            
            
        }catch (IOException e){
            e.printStackTrace();           
        }   
        
        System.out.println("Found " + userLeaves.size() + " leaves for user: " + userName);
        return userLeaves;
    }
    
    public void updateLeaveStatus(String leaveType, String startDate, String endDate, String userName, String newStatus) {
    File inputFile = new File(csvFile);
    File tempFile = new File("temp.csv");

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
        String line;
        boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            if (firstLine) {
                writer.write(line);
                writer.newLine();
                firstLine = false;
                continue;
            }
            String[] fields = line.split(",");
            if (fields[0].trim().equals(userName) && 
                fields[1].trim().equals(leaveType) && 
                fields[2].trim().equals(startDate) && 
                fields[3].trim().equals(endDate)) {

                // Update the status field
                fields[5] = newStatus;
                line = String.join(",", fields);
            }
            writer.write(line);
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    if (!inputFile.delete()) {
        System.out.println("Could not delete original file");
    }

    if (!tempFile.renameTo(inputFile)) {
        System.out.println("Could not rename temp file");
    }
}

    public void deleteLeave(String userName, String leaveType, String startDate, String endDate, String totalDays) {
        File inputFile = new File(csvFile);
        File tempFile = new File("temp.csv");    

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    writer.write(line);
                    writer.newLine();
                    firstLine = false;
                    continue;
                }
                String[] fields = line.split(",");
                for(int i = 0; i <fields.length; i++){
                    fields[i] = fields[i].trim(); 
                }
                if (!(fields[0].equals(userName) && 
                      fields[1].equals(leaveType) && 
                      fields[2].equals(startDate) && 
                      fields[3].equals(endDate) && 
                      fields[4].equals(totalDays))) {
                    writer.write(line);
                    writer.newLine();
                }               
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (!inputFile.delete()) {
            System.out.println("Could not delete file");
            return;
        }
        
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename file");
        }
    }
}