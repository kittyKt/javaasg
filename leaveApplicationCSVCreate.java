
package assignment;

/*import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class leaveApplicationCSVCreate {
    private String csvFile;
    
    public leaveApplicationCSVCreate(String csvFile){
        this.csvFile = csvFile;
    }
    
    public void writeLeaveApplication(String userName, String leaveType, String startDate, String endDate, int totalDays){
        boolean fileExists = new java.io.File(csvFile).exists();
        
        try(FileWriter fileWriter = new FileWriter(csvFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){
            
            if(!fileExists){
                bufferedWriter.write("Username,Leave Type,Start Date,End Date,Total Days,Status");
                bufferedWriter.newLine();
            }
            
            bufferedWriter.write(userName + " , " + leaveType + " , " + startDate + " , " + endDate + " , " + totalDays + " , Pending");
            bufferedWriter.newLine();
            
            System.out.println("Leave application sucess!");
                      
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Error saving application. Please try again.");
            
        }
    }  
    
    public static void main(String[] args) {
        String csvFile = "leave_applications.csv"; 
        leaveApplicationCSVCreate leaveApplication = new leaveApplicationCSVCreate(csvFile);
        System.out.println("Done, check if file is created");
    }
}*/
