
package assignment;

import java.util.Map;

public class ApplyLeave { 
    
    public static class User{
       
        private String username; 
        private String gender;
        private boolean married;
        private Map<String, Integer> leaveBalance;
        
        public User(String username, String gender, boolean married, Map<String, Integer> leaveBalance) {
            this.username = username;
            this.gender = gender;
            this.married = married;
            this.leaveBalance = leaveBalance;   
        }

        public String getUsername() {
                return username;
            }

            public String getGender() {
                return gender;
            }

            public boolean isMarried() {
                return married;
            }
            

            public int getAnnualLeaveBalance() {
                return leaveBalance.getOrDefault("annual",0);
            }

            public int getMedicalLeaveBalance() {
                return leaveBalance.getOrDefault("medical",0);
            }

            public int getMaternityLeaveBalance() {
                return leaveBalance.getOrDefault("maternity",0);
            }

            public int getUnpaidLeaveBalance() {
                return leaveBalance.getOrDefault("unpaid",0);
            }

            public String toString() {
                return "Username='" + username + "', gender='" + gender + "', married=" + married + 
                       ", medicalLeaveBalance=" + getMedicalLeaveBalance() + 
                       ", unpaidLeaveBalance=" + getUnpaidLeaveBalance() + 
                       ", annualLeaveBalance=" + getAnnualLeaveBalance() + 
                       ", maternityLeaveBalance=" + getMaternityLeaveBalance() + "}";
            }
            
            public boolean canApplyForMaternityLeave() {
                return this.getMaternityLeaveBalance() > 0 && "Female".equalsIgnoreCase(this.getGender()) && this.isMarried();
            }            
    }
}

    

   
            
         

