
package assignment;

import java.io.*;
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
                    continue; // Skip header line
                }
                String[] fields = line.split(",");
                for(int i = 0 ; i< fields.length; i++){
                    fields[i] = fields[i].trim(); // ensure value trested as string
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
                    fields[i] = fields[i].trim(); // ensure value treated as string
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
        
        //renaming tmp file to ori file name (leave_application)
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename file");
        }       
    }      
}
