package javaassignment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Employee_Details {
    
    private String gender;
    private Date dob;
    private String pob;
    private String race;
    private String religious;
    private String bank_nm;
    private String acc_num;
    private String epf_num;
    private String tax_num;
    private String hp_num;
    private String hm_addr;
    private String email;
    private String marital;
    private String fm_nm;
    private String fm_nric;
    private String relation;
    private String fm_hp;
    private String work_exp;
    private double salaryIncrement;
    private Employment_Details employmentDetails;
    private double currentGrossSalary;
    private boolean isInitialEntry = true; 

    
   
    
    public Employee_Details(){
        this.employmentDetails = new Employment_Details();
        
    }

    

    public void setDetails(String gender, String dob, String pob, String race, String religious,
                            String bank_nm, String acc_num, String epf_num, String tax_num, String hp_num,
                            String hm_addr, String email, String marital, String fm_nm, String fm_nric, 
                            String relation, String fm_hp, String work_exp, double salaryIncrement){
        
        

     
        setGender(gender);
        setDob(dob);
        setPob(pob);
        setRace(race);
        setReligious(religious);
        setBank_nm(bank_nm);
        setAcc_num(acc_num);
        setEpf_num(epf_num);
        setTax_num(tax_num);
        setHp_num(hp_num);
        setHm_addr(hm_addr);
        setEmail(email);
        setMarital(marital);
        setFm_detail(fm_nm, fm_nric, relation, fm_hp);
        setCompetencies(work_exp);
        finalizeInitialEntry();
        setSalaryIncrement(salaryIncrement);
    }
    


    private void finalizeInitialEntry() {
        this.isInitialEntry = true;
    }
    
    

        public Employment_Details getEmploymentDetails() {
            return employmentDetails;
    }

        public void setEmploymentDetails(Employment_Details employmentDetails) {
            this.employmentDetails = employmentDetails;
            
    }
        
        private boolean isValidNricFormat(String nric){
            String nricPattern = "\\d{12}"; //xxxxxx-xx-xxxx
            return nric.matches(nricPattern);   
    }
  
        public String getGender(){
            return gender;
        }
        
        public void setGender(String gender) {
            if (isInitialEntry) {
                this.gender = gender;
            } else {
                if (isValidGender(gender)) {
                    this.gender = gender;
                } else {
                    throw new IllegalArgumentException("Invalid Gender.");
                }
            }
        }

        private boolean isValidGender(String gender) {
            return gender != null && (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"));
        }

       
        public Date getDob(){ 
           return dob;
       }
       
        public void setDob(String dobStr) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
               if (dobStr != null && !dobStr.trim().isEmpty()) {
                    try {
                        Date effectiveDate = sdf.parse(dobStr);
                        this.dob = effectiveDate;
                    } catch (ParseException e) {
                        throw new IllegalArgumentException("Invalid date format for Date of Birth.");
                    }
                }
        }
        
        public String getPob(){
           return pob;
        }
                
        public void setPob(String pob) {
        if (isInitialEntry) {
            // Allow any value during the initial entry
            this.pob = pob;
        } else {
            // Apply validation rules for subsequent attempts
            if (pob != null && !pob.trim().isEmpty()) {
                this.pob = pob;
            } else {
                throw new IllegalArgumentException("Place of Birth cannot be empty.");
            }
        }
    }

        public String getRace(){
            return race;
        }
        
        public void setRace(String race) {
            if (isInitialEntry) {
                this.race = race;
            } else {
                if (isValidRace(race)) {
                    this.race = race;
                } else {
                    throw new IllegalArgumentException("Race cannot be empty.");
                }
            }
        }
        
        
        private boolean isValidRace(String race){
            String racePattern = "(Malay|Chinese|Indian)";
            return race != null && (race.matches(racePattern) || !race.trim().isEmpty()) ; //not empty string(Other race) is accepted 
    }
        
        public String getReligious(){
            return religious;
        }
        
        public void setReligious(String religious) {
            if (isInitialEntry) {
                this.religious = religious;
            } else {
                if (isValidReligious(religious)) {
                    this.religious = religious;
                } else {
                    throw new IllegalArgumentException("Invalid Religious.");
                }
            }
        }
        
        private boolean isValidReligious (String religious){
            String religiousPattern = "(Muslim|Buddhist|Hindu)";
            return religious != null && (religious.matches(religiousPattern) || !religious.trim().isEmpty()) ; //not empty string(Other religious) is accepted 
    }
        
        public String getBank_nm(){
            return bank_nm;
        }
        
        public void setBank_nm(String bank_nm) {
            if (isInitialEntry) {
                this.bank_nm = bank_nm;
            } else {
                if (bank_nm != null && !bank_nm.trim().isEmpty()) {
                    this.bank_nm = bank_nm;
                } else {
                    throw new IllegalArgumentException("Bank Name cannot be empty.");
                }
            }
        }
        
        public String getAcc_num(){
            return acc_num;
        }
        
        public void setAcc_num(String acc_num) {
            if (isInitialEntry) {
                this.acc_num = acc_num;
            } else {
                if (isValidAccNum(acc_num)) {
                    this.acc_num = acc_num;
                } else {
                    throw new IllegalArgumentException("Invalid Account Number format.");
                }
            }
        }
        
        private boolean isValidAccNum(String acc_num) {
            return acc_num != null && acc_num.matches("\\d{1,16}"); //not null and up to 16digits
    }
        
        public String getEpf_num(){
            return epf_num;
        }
        
        public void setEpf_num(String epf_num) {
            if (isInitialEntry) {
                this.epf_num = epf_num;
            } else {
                if (isValidEpf_num(epf_num)) {
                    this.epf_num = epf_num;
                } else {
                    throw new IllegalArgumentException("Invalid EPF Number format.");
                }
            }
        }

        private boolean isValidEpf_num(String epf_num) {
            return epf_num != null && epf_num.matches("\\d{8}"); //not null and only 8 digits
    }
        
        public String getTax_num(){
            return tax_num;
        }
        
            public void setTax_num(String tax_num) {
         if (isInitialEntry) {
             this.tax_num = tax_num;
         } else {
             if (isValidTax_num(tax_num)) {
                 this.tax_num = tax_num;
             } else {
                 throw new IllegalArgumentException("Invalid Tax Identification Number format.");
             }
         }
     }
        private boolean isValidTax_num(String tax_num){
            return tax_num != null && tax_num.matches("\\d{12}");
    }
        
        public String getHp_num(){
            return hp_num;
        }
        
        public void setHp_num(String hp_num) {
            if (isInitialEntry) {
                this.hp_num = hp_num;
            } else {
                if (isValidHp_num(hp_num)) {
                    this.hp_num = hp_num;
                } else {
                    throw new IllegalArgumentException("Invalid Phone Number format.");
                }
            }
        }
        
        private boolean isValidHp_num(String hp_num){
            String hp_num_Pattern = "\\d{3}[-\\s]?\\d{7}";
            return hp_num != null && hp_num.matches(hp_num_Pattern);//xxx-xxxxxxx
    }
        
        public String getHm_addr(){
            return hm_addr;
        }
        
        public void setHm_addr(String hm_addr) {
            if (isInitialEntry) {
                this.hm_addr = hm_addr;
            } else {
                if (hm_addr != null && !hm_addr.trim().isEmpty()) {
                    this.hm_addr = hm_addr;
                } else {
                    throw new IllegalArgumentException("Home address cannot be empty.");
                }
            }
        }
        
        public String getEmail(){
            return email;
        }
        
        public void setEmail(String email) {
            if (isInitialEntry) {
                this.email = email;
            } else {
                if (email != null && !email.trim().isEmpty()) {
                    if (isValidEmail(email)) {
                        this.email = email;
                    } else {
                        throw new IllegalArgumentException("Invalid email format.");
                    }
                } else {
                    throw new IllegalArgumentException("Email cannot be empty.");
                }
            }
        }

        private boolean isValidEmail(String email){
            String emailPattern = "^[a-z0-9_+&*-]+@(gmail|yahoo|hotmail|outlook)\\.com$";
            return email != null && email.matches(emailPattern);
    }
    
        public String getMarital() {
            return marital;
        }

        public void setMarital(String marital) {
            if (isInitialEntry) {
                this.marital = marital;
            } else {
                if (isMaritalValid(marital)) {
                    this.marital = marital;
                } else {
                    throw new IllegalArgumentException("Invalid Marital status.");
                }
            }
        }

    private boolean isMaritalValid(String marital) {
        String maritalPattern = "(Single|Married)"; // Adjust as needed
        return marital != null && marital.matches(maritalPattern);
    }

        //family detail
        public String getFm_nm(){
            return fm_nm;
        }
        
        public String getFm_nric(){
            return fm_nric;
        }
        
        public String getRelation(){
            return relation;
        }
        
        public String getFm_hp(){
            return fm_hp;
        }
        
        public void setFm_detail(String fm_nm, String fm_nric, String relation, String fm_hp) {
            if (isInitialEntry) {
                this.fm_nm = fm_nm;
                this.fm_nric = fm_nric;
                this.relation = relation;
                this.fm_hp = fm_hp;
            } else {
                if (fm_nm != null && !fm_nm.trim().isEmpty()) {
                    this.fm_nm = fm_nm;
                } else {
                    throw new IllegalArgumentException("Family name cannot be empty.");
                }

                if (isValidNricFormat(fm_nric)) {
                    this.fm_nric = fm_nric;
                } else {
                    throw new IllegalArgumentException("Invalid NRIC format for family member.");
                }

                if (relation != null && !relation.trim().isEmpty()) {
                    this.relation = relation;
                } else {
                    throw new IllegalArgumentException("Relation cannot be empty.");
                }

                if (isValidHp_num(fm_hp)) {
                    this.fm_hp = fm_hp;
                } else {
                    throw new IllegalArgumentException("Invalid Phone Number format for family member.");
                }
            }
        }

        public String getWork_exp(){
            return work_exp;
        }
    
       
        public void setCompetencies(String work_exp) {
            if (isInitialEntry) {
                this.work_exp = work_exp;
            } else {
                if (work_exp != null && !work_exp.trim().isEmpty()) {
                    this.work_exp = work_exp;
                } else {
                    throw new IllegalArgumentException("Working experience cannot be empty.");
                }
            }
        }
        
        public void setSalaryIncrement(double salaryIncrement) {
        System.out.println("Setting Salary Increment: " + salaryIncrement);
        this.salaryIncrement = salaryIncrement;
    }

    public double getSalaryIncrement() {
    System.out.println("Getting Salary Increment: " + this.salaryIncrement);

        return salaryIncrement;
    }
          
    

    public boolean isInitialEntry() {
        return isInitialEntry;
    }
   
}
  
    
    



        


        
        
            
        
       
        
        
     
        
        
        
        
        
       


        
        
        
        
        


