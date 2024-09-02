

package javaassignment;

import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import java.text.ParseException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;


public class EmployeeForm extends javax.swing.JPanel {
    
    private DefaultTableModel tableModel;
    private Employee_Details employeeDetails;
    private Employment_Details employmentDetails;
    private boolean isInitialEntry;
    private int maternityLeave;
    private List<String> positionHistory = new ArrayList<>();
    private String oldPosition = "";
    private HR_interface Testing;
    private Object[] rowData;
    private String profile;


    public EmployeeForm(DefaultTableModel model, Object[] rowData, String profile) {
        this.tableModel = model;
        this.rowData = rowData;
        this.profile = profile;
        initComponents();
        initializeTextFields();
        
        employeeDetails = new Employee_Details();
        employmentDetails = new Employment_Details();

        
//        System.out.println("Created Employment Details Instance: " + System.identityHashCode(employmentDetails));

        initializeForm();

        
        isInitialEntry = (rowData == null);
        firstAttempt = isInitialEntry;
        
        if (!isInitialEntry) {
            populateEmployeeDetails(rowData);
            populateFields();
        }

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Male", "Female" })); // Gender
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Single", "Married" })); // Marital Status
        

    }
    
    
    

    public JTextField getPositionField() {
        return jTextField21;
    }

    public JTextField getNewPositionField() {
        return jTextField27;
    }

    public JTextField getEffectiveDateField() {
        return jTextField26;
    }
    
    public JTextField getInitialGrossSalary(){
        return jTextField23;
    }
    

    
    private void initializeForm() {
        oldPosition = employmentDetails.getPositions();
        System.out.println("Initialized Old Position: " + oldPosition);

        jTextField21.setText(oldPosition);
        
        
        jTextField27.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // Get the new position entered by the user
                String newPosition = jTextField27.getText();
                System.out.println("Key Released - New Position: " + newPosition);

                // Only update position if the new position is different from oldPosition
                if (!newPosition.equals(oldPosition)) {
                    // Call updatePositionField and get the promotion detail
                    String promotionDetail = updatePositionField();

                    // If updatePositionField returned a non-empty promotionDetail, update oldPosition
                    if (!promotionDetail.isEmpty()) {
                        oldPosition = jTextField21.getText(); // Update oldPosition to the new position displayed in jTextField21
                        System.out.println("Updated Old Position to: " + oldPosition);
                    }
                }
            }
        });
        

    }
    
  

    private void updatePositionHistory(String promotionDetail) {
        if (!promotionDetail.isEmpty()) {
            positionHistory.add(promotionDetail); // Add the new promotion detail to the history list
            System.out.println("Updated Position History: " + positionHistory); // Debug statement to verify
        }
    }


    private String updatePositionField() {
        String oldposition = employmentDetails.getPositions(); // Use previousPosition to get the current position text
        String newPosition = jTextField27.getText(); // Get the new position entered by the user
        String effectiveDate = jTextField26.getText(); // Get the effective date
        
                
        if (!newPosition.isEmpty() && !oldposition.equals(newPosition)) {
            String promotionDetail = "From position: " + oldposition + " promoted to " + newPosition + " (effective on " + effectiveDate + ") ";

            jTextField21.setText(newPosition);

            oldPosition = newPosition;

            updatePositionHistory(promotionDetail);

            return promotionDetail; 
        }

        return ""; 
    }
    


    private String updateSalaryHistory(String incrementDetail) {
        if (!incrementDetail.isEmpty()) {
        // Get the existing salary increment history
        String existingHistory = employmentDetails.getSalaryIncrementHistory();
        
        // Append the new increment detail with a newline
        String updatedHistory = (existingHistory != null && !existingHistory.isEmpty()) 
            ? existingHistory + "\n" + incrementDetail 
            : incrementDetail;
        
        // Update the employment details with the new history
        employmentDetails.setSalaryIncrementHistory(updatedHistory);
        System.out.println("Updated Salary History: " + updatedHistory);
        
        return incrementDetail; 
    }
    return ""; 
}




    
    private void initializeTextFields() {
        // Initialize all text fields to empty
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField14.setText("");
        jTextField15.setText("");
        jTextField16.setText("");
        jTextField17.setText("");
        jTextField18.setText("");
        jTextField19.setText("");
        jTextField20.setText("");
        jTextField21.setText("");
        jTextField22.setText("");
        jTextField23.setText("");
        jTextField24.setText("");
        jTextField25.setText("");
        jTextField26.setText("");
        jTextField27.setText("");
        jTextField28.setText("");
        jTextField29.setText("");
    }

    private String formatDate(Date date) {
        if (date == null) {
            return ""; // Return empty string for null dates
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            return sdf.format(date);
        }
}
    
    private boolean isIncrementApplied = false;

    private void populateEmployeeDetails(Object[] rowData) {
        // Populate Employee_Details from rowData
        jTextField1.setText((String) rowData[0]); // FirstName
        jTextField2.setText((String) rowData[1]); // LastName
        jTextField3.setText((String) rowData[2]); // NRIC
        jTextField4.setText((String) rowData[3]); // Username
        jTextField6.setText((String) rowData[4]); // Password
        jTextField28.setText((String) rowData[5]); // AccStatus
        jTextField5.setText((String) rowData[6]); // Roles
        employeeDetails.setDob((String) rowData[7]);
        employeeDetails.setRace((String) rowData[8]);
        employeeDetails.setReligious((String) rowData[9]);
        employeeDetails.setPob((String) rowData[10]);
        employeeDetails.setEmail((String) rowData[11]);
        employeeDetails.setHm_addr((String) rowData[12]);
        employeeDetails.setGender((String) rowData[13]);
        employeeDetails.setMarital((String) rowData[14]);
        employeeDetails.setHp_num((String) rowData[15]);
        employeeDetails.setBank_nm((String) rowData[16]);
        employeeDetails.setAcc_num((String) rowData[17]);
        employeeDetails.setEpf_num((String) rowData[18]);
        employeeDetails.setTax_num((String) rowData[19]);
        employeeDetails.setFm_detail((String) rowData[20], (String) rowData[21], (String) rowData[22], (String) rowData[23]);
        employeeDetails.setCompetencies((String) rowData[24]);

        employmentDetails.setNewPosition((String) rowData[34]);
        employmentDetails.setEffectiveDate((String) rowData[35]);
        

        try {
            employmentDetails.setDateJoined((String) rowData[25]);
            employmentDetails.setDateResigned((String) rowData[26]);
            employmentDetails.setPositions((String) rowData[27]);
            employmentDetails.setDepartment((String) rowData[28]);

            // Retrieve the value from rowData
        Object grossSalaryObj = rowData[29];

        // Initialize initialGrossSalary
        double initialGrossSalary = 0.00;

        if (grossSalaryObj != null) {
            if (grossSalaryObj instanceof String) {
                // Handle the case where the value is a String
                String grossSalaryStr = (String) grossSalaryObj;
                if (!grossSalaryStr.trim().isEmpty()) {
                    try {
                        initialGrossSalary = Double.parseDouble(grossSalaryStr);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid gross salary format: " + grossSalaryStr);
                    }
                }
            } else if (grossSalaryObj instanceof Double) {
                // Handle the case where the value is a Double
                initialGrossSalary = (Double) grossSalaryObj;
            } else {
                // Handle unexpected type
                System.err.println("Unexpected type for gross salary: " + grossSalaryObj.getClass());
            }
        }

// Set the value in employmentDetails
        employmentDetails.setInitialGrossSalary(initialGrossSalary);

            Object salaryIncrementObj = rowData[37];
            if (salaryIncrementObj == null) {
                // Handle null case
                System.err.println("salaryIncrementObj is null");
                employmentDetails.setSalaryIncrement(0.00); // Default or error value
            } else {
                switch (salaryIncrementObj) {
                    case Double salaryIncrement -> {
                        employmentDetails.setSalaryIncrement(salaryIncrement);
                    }
                    case String salaryIncrementStr -> {
                        try {
                            double salaryIncrement = Double.parseDouble(salaryIncrementStr);
                            employmentDetails.setSalaryIncrement(salaryIncrement);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid salary increment format: " + salaryIncrementStr);
                            employmentDetails.setSalaryIncrement(0.00); // Default or error value
                        }
                    }
                    default -> {
                        System.err.println("Unknown type for salaryIncrementObj: " + salaryIncrementObj.getClass().getName());
                        employmentDetails.setSalaryIncrement(0.00); // Default if unknown type
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void populateFields() {
        SwingUtilities.invokeLater(() -> {
            if (employeeDetails != null && employmentDetails != null) {
                // Debug: Check if salaryIncrement is set before populateFields
                System.out.println("Before Populate Fields - Salary Increment Set To: " + employmentDetails.getSalaryIncrement());

                // Update combo boxes
                if (jComboBox1.getModel().getSize() == 0) {
                    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Male", "Female" }));
                }
                if (jComboBox2.getModel().getSize() == 0) {
                    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Single", "Married" }));
                }
                jComboBox1.setSelectedItem(employeeDetails.getGender());
                jComboBox2.setSelectedItem(employeeDetails.getMarital());

                // Update text fields
                jTextField25.setText(formatDate(employeeDetails.getDob()));
                jTextField7.setText(employeeDetails.getRace());
                jTextField8.setText(employeeDetails.getReligious());
                jTextField9.setText(employeeDetails.getPob());
                jTextField10.setText(employeeDetails.getEmail());
                jTextField11.setText(employeeDetails.getHp_num());
                jTextArea1.setText(employeeDetails.getHm_addr());
                jTextField12.setText(employeeDetails.getBank_nm());
                jTextField13.setText(employeeDetails.getAcc_num());
                jTextField14.setText(employeeDetails.getTax_num());
                jTextField15.setText(employeeDetails.getEpf_num());
                jTextField16.setText(employeeDetails.getFm_nm());
                jTextField17.setText(employeeDetails.getFm_nric());
                jTextField18.setText(employeeDetails.getRelation());
                jTextField19.setText(employeeDetails.getFm_hp());
                jTextArea2.setText(employeeDetails.getWork_exp());

                // Set employment details
                jTextField20.setText(formatDate(employmentDetails.getDateJoined()));
                jTextField21.setText(employmentDetails.getPositions());
                jTextField22.setText(employmentDetails.getDepartment());
                jTextField24.setText(formatDate(employmentDetails.getDateResigned()));
                jTextField27.setText(employmentDetails.getNewPosition());
                jTextField26.setText(formatDate(employmentDetails.getEffectiveDate()));

                double salaryIncrementValue = employmentDetails.getSalaryIncrement();
                double initialGrossSalary = employmentDetails.getInitialGrossSalary();
                double updatedSalary = initialGrossSalary + salaryIncrementValue;
                jTextField23.setText(String.format("%.2f", updatedSalary));
                
                if (isIncrementApplied) {
                // Use default increment value if an increment has already been applied
                    salaryIncrementValue = 0.0; // Or any default value you'd like
                    updatedSalary = initialGrossSalary;
                } else {
                    // Apply increment if it's been set
                    if (salaryIncrementValue > 0.0) {
                        updatedSalary = initialGrossSalary + salaryIncrementValue;
                    }
                    isIncrementApplied = true; // Mark that increment has been applied

                jTextField29.setText(String.format("%.2f", salaryIncrementValue));
                jTextField23.setText(String.format("%.2f", updatedSalary));


                }

            }
        });
    }

    
    private boolean firstAttempt = true; // Flag to track whether it's the first attempt
    
    


    // Method to save data to the JTable
    public Object[] getData() throws IllegalArgumentException {
        
        
        
        String firstName = jTextField1.getText();
        String lastName = jTextField2.getText();
        String nric = jTextField3.getText();
        String username = jTextField4.getText();
        String roles = jTextField5.getText();
        String password = jTextField6.getText();
        String accStatus = jTextField28.getText();
        String dob = jTextField25.getText();
        String race = jTextField7.getText();
        String religion = jTextField8.getText();
        String pob = jTextField9.getText();
        String email = jTextField10.getText();
        String hmAdr = jTextArea1.getText();
        String gender = (String) jComboBox1.getSelectedItem();
        String marital = (String) jComboBox2.getSelectedItem();
        String hp = jTextField11.getText();
        String bankNm = jTextField12.getText();
        String accNo = jTextField13.getText();
        String epf = jTextField15.getText();
        String tax = jTextField14.getText();
        String fmNm = jTextField16.getText();
        String fmNric = jTextField17.getText();
        String relation = jTextField18.getText();
        String fmHp = jTextField19.getText();
        String work = jTextArea2.getText(); // Updated to use getCompetencies()
        String dateJoined = jTextField20.getText();
        String dateResigned = jTextField24.getText();
        String currentPosition = jTextField21.getText();
        String department = jTextField22.getText();
        String grossSalary = jTextField23.getText();
        String newPosition = jTextField27.getText();
        String effectiveDate = jTextField26.getText();
        String updatedPositionDetail = updatePositionField();
        String salaryIncrement = jTextField29.getText();
//        System.out.println("jTextField29 incrementbla: " + jTextField29.getText());

        

        // Initial validation for required fields and fields that should not be empty
        validateNotEmpty(firstName, "First Name");
        validateNotEmpty(lastName, "Last Name");
        validateNotEmpty(nric, "NRIC");
        validateNotEmpty(username, "Username");
        validateNotEmpty(roles, "Roles");
        validateNotEmpty(password, "Password");
        validateNotEmpty(accStatus, "Account Status");
        updatePositionField();
        
        if (firstAttempt) {
            validateNotEmpty(firstName, "First Name");
            validateNotEmpty(lastName, "Last Name");
            validateNotEmpty(nric, "NRIC");
            validateNotEmpty(username, "Username");
            validateNotEmpty(roles, "Roles");
            validateNotEmpty(password, "Password");
            validateNotEmpty(accStatus, "Account Status");
        } else {
            validateNotEmpty(firstName, "First Name");
            validateNotEmpty(lastName, "Last Name");
            validateNotEmpty(nric, "NRIC");
            validateNotEmpty(username, "Username");
            validateNotEmpty(roles, "Roles");
            validateNotEmpty(password, "Password");
            validateNotEmpty(dob, "DOB");
            validateNotEmpty(race, "Race");
            validateNotEmpty(religion, "Religion");
            validateNotEmpty(pob, "Place of Birth");
            validateNotEmpty(email, "Email");
            validateNotEmpty(hmAdr, "Home Address");
            validateNotEmpty(gender, "Gender");
            validateNotEmpty(marital, "Marital Status");
            validateNotEmpty(hp, "Contact Number");
            validateNotEmpty(bankNm, "Bank Name");
            validateNotEmpty(accNo, "Bank Account Number");
            validateNotEmpty(epf, "EPF Number");
            validateNotEmpty(tax, "Tax Number");
            validateNotEmpty(fmNm, "Family Member Name");
            validateNotEmpty(fmNric, "Family Member NRIC");
            validateNotEmpty(relation, "Relation");
            validateNotEmpty(fmHp, "Family Member Phone Number");
            validateNotEmpty(work, "Competencies");
            validateNotEmpty(dateJoined, "Date Joined");
            validateNotEmpty(currentPosition, "Position");
            validateNotEmpty(department, "Department");
            validateNotEmpty(grossSalary, "Gross Salary");
            calculateMaternityLeave();
            try {
                employmentDetails.setDateJoined(dateJoined);
                employmentDetails.setDateResigned(dateResigned);

                employmentDetails.calculateLeaves();
                
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Error calculating leave entitlements: " + e.getMessage());
            }
            
            validateDate(dob, "DOB", true);
            validateRace(race);
            validateReligion(religion);
            validatePob(pob);
            validateEmail(email);
            validateMarital(marital);
            validateGender(gender);
            validatePhone(hp);
            validateBankName(bankNm);
            validateAccountNumber(accNo);
            validateHmAdr(hmAdr);
            validateEPFNumber(epf);
            validateTaxNumber(tax);
            validateFmNric(fmNric);
            validatePosition(currentPosition);
            validateDepartment(department);
            validateWork(work);
            validateDate(dateJoined, "Date Joined", true);
            validateDate(dateResigned, "Date Resigned", false);
            validateDate(effectiveDate, "EffectiveDate", false);
            validateDouble(grossSalary, "Gross Salary");
            validateDouble(salaryIncrement, "Salary Increment");
    }      
           
        double initialGrossSalary = 0.0;
        double salaryIncrementValue = 0.0;
        boolean isSalaryUpdated = false;

        if (!grossSalary.trim().isEmpty() || !firstAttempt) {
            validateDouble(grossSalary, "Gross Salary");
            try {
                initialGrossSalary = Double.parseDouble(grossSalary);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid format for Gross Salary.");
            }
        }

        if (!salaryIncrement.trim().isEmpty()) {
            try {
                salaryIncrementValue = Double.parseDouble(salaryIncrement);
                isSalaryUpdated = true; // Salary is updated
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid format for Salary Increment.");
            }
        }

        double updatedSalary = initialGrossSalary; 
        if (isSalaryUpdated) {
            updatedSalary = initialGrossSalary + salaryIncrementValue;

            if (updatedSalary >= initialGrossSalary) {
                salaryIncrementValue = 0.0; 
            }

            jTextField23.setText(String.valueOf(updatedSalary)); // Update the text field
        }






    
        
       

        // Set values in Employee_Details and Employment_Details
        employeeDetails.setDob(dob);
        employeeDetails.setRace(race);
        employeeDetails.setReligious(religion);
        employeeDetails.setPob(pob);
        employeeDetails.setEmail(email);
        employeeDetails.setHm_addr(hmAdr);
        employeeDetails.setGender(gender);
        employeeDetails.setMarital(marital);
        employeeDetails.setHp_num(hp);
        employeeDetails.setBank_nm(bankNm);
        employeeDetails.setAcc_num(accNo);
        employeeDetails.setEpf_num(epf);
        employeeDetails.setTax_num(tax);
        employeeDetails.setFm_detail(fmNm, fmNric, relation, fmHp);
        employeeDetails.setCompetencies(work);
        employmentDetails.setDateJoined(dateJoined);
        employmentDetails.setDateResigned(dateResigned);
        employmentDetails.setPositions(currentPosition);
        employmentDetails.setDepartment(department);
        employmentDetails.setInitialGrossSalary(initialGrossSalary);
        employmentDetails.setNewPosition(newPosition);
        employmentDetails.setEffectiveDate(effectiveDate);
        
        if (isSalaryUpdated) {
            employeeDetails.setSalaryIncrement(salaryIncrementValue);
        } else {
            employeeDetails.setSalaryIncrement(0.0); // Or handle as needed
        }
        updatePositionField();
        
        jTextField23.setText(String.valueOf(updatedSalary));
        
        
        
         String historyDetail = null; // Default to null
        if (isSalaryUpdated && updatedSalary > initialGrossSalary) {
            String incrementDetail = "Salary increment from RM " + initialGrossSalary + " to RM " + updatedSalary + " (effective on " + effectiveDate + ")";
            historyDetail = updateSalaryHistory(incrementDetail); // Capture the increment detail
        }

            // Debugging: Print the value set in jTextField23
        
        employmentDetails.setInitialGrossSalary(updatedSalary);
      
        firstAttempt = false;
        
        return new Object[] {
            firstName, lastName, nric, username, password, accStatus, roles, dob, race, religion, pob, email, hmAdr, gender, marital,
            hp, bankNm, accNo, epf, tax, fmNm, fmNric, relation, fmHp, work, dateJoined, dateResigned, currentPosition, department, updatedSalary,
            employmentDetails.getAnnualLeave(), employmentDetails.getMedLeave(), maternityLeave, employmentDetails.getUnpaidLeave(), newPosition, effectiveDate,
            updatedPositionDetail, salaryIncrementValue, historyDetail, profile, 
       
        };
        
    }
    
    
    

   
    private void validateNotEmpty(String value, String fieldName) throws IllegalArgumentException {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
    }
 
    private void validateGender(String gender) throws IllegalArgumentException {
        if (gender.trim().isEmpty()) {
            throw new IllegalArgumentException("Gender cannot be empty.");
        }
        if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")) {
            throw new IllegalArgumentException("Gender must be 'Male' or 'Female'.");
        }
    }
    
    
    private void validateRace(String race) throws IllegalArgumentException {
        if (race.trim().isEmpty()) {
            throw new IllegalArgumentException("Race cannot be empty.");
        }
    }
    
    private void validateReligion(String religion) throws IllegalArgumentException {
        if (religion.trim().isEmpty()) {
            throw new IllegalArgumentException("Religion cannot be empty.");
        }
    }

    private void validatePob(String pob) throws IllegalArgumentException {
        if (pob.trim().isEmpty()) {
            throw new IllegalArgumentException("Place of Birth cannot be empty.");
        }
    }


    private void validateEmail(String email) throws IllegalArgumentException {
        if (!email.matches("^[a-z0-9_+&*-]+@(gmail|yahoo|hotmail|outlook)\\.com$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    private void validatePhone(String phone) throws IllegalArgumentException {
        if (!phone.matches("\\d{3}[-\\s]?\\d{7}")) {
            throw new IllegalArgumentException("Invalid phone number format.");
        }
    }

    private void validateBankName(String bankName) throws IllegalArgumentException {
        if (bankName.trim().isEmpty()) {
            throw new IllegalArgumentException("Bank Name cannot be empty.");
        }
    }
    
    private void validateHmAdr(String hmAdr) throws IllegalArgumentException {
        if (hmAdr.trim().isEmpty()) {
            throw new IllegalArgumentException("Home Address cannot be empty.");
        }
        // Additional checks can be added based on address validation rules
    }

    private void validateAccountNumber(String accountNumber) throws IllegalArgumentException {
        if (!accountNumber.matches("\\d{1,16}")) {
            throw new IllegalArgumentException("Invalid Account Number format.");
        }
    }

    private void validateEPFNumber(String epfNumber) throws IllegalArgumentException {
        if (!epfNumber.matches("\\d{8}")) {
            throw new IllegalArgumentException("Invalid EPF Number format.");
        }
    }

    private void validateTaxNumber(String taxNumber) throws IllegalArgumentException {
        if (!taxNumber.matches("\\d{12}")) {
            throw new IllegalArgumentException("Invalid Tax Number format.");
        }
    }
    
    private void validateWork(String work) throws IllegalArgumentException {
        if (work.trim().isEmpty()) {
            throw new IllegalArgumentException("Work experience cannot be empty.");
        }
    }

    private void validatePosition(String position) throws IllegalArgumentException {
        if (position.trim().isEmpty()) {
            throw new IllegalArgumentException("Position cannot be empty.");
        }
    }

    private void validateDepartment(String department) throws IllegalArgumentException {
        if (department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty.");
        }
    }
    
    private void validateFmNric(String fmNric) throws IllegalArgumentException {
        if (!fmNric.matches("\\d{12}")) {
            throw new IllegalArgumentException("Invalid Family IC Number format.");
        }
    }
    
    private void validateMarital(String marital) throws IllegalArgumentException {
        if (marital.trim().isEmpty()) {
            throw new IllegalArgumentException("Marital Status cannot be empty.");
        }
        if (!marital.equalsIgnoreCase("single") && !marital.equalsIgnoreCase("married")) {
            throw new IllegalArgumentException("Marital Status must be 'Single' or 'Married'.");
        }
    }

    private void validateDate(String date, String fieldName, boolean isRequired) throws IllegalArgumentException {
        if (isRequired) {
            if (date == null || date.trim().isEmpty()) {
                throw new IllegalArgumentException(fieldName + " cannot be empty.");
            }
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                sdf.parse(date);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format for " + fieldName + ". Expected format is yyyy-MM-dd.");
            }
        } else {
            if (date != null && !date.trim().isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    sdf.setLenient(false);
                    sdf.parse(date);
                } catch (ParseException e) {
                    throw new IllegalArgumentException("Invalid date format for " + fieldName + ". Expected format is yyyy-MM-dd.");
                }
            }
        }
    }

    private void validateDouble(String value, String fieldName) throws IllegalArgumentException {
        if (value.trim().isEmpty()) {
        // If the value is empty, do not validate it as a double
        return;
        }
        try {
            Double.valueOf(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid format for " + fieldName + ".");
        }
    }
    
    private void calculateMaternityLeave() {
    // Retrieve values from combo boxes
        String gender = (String) jComboBox1.getSelectedItem();
        String maritalStatus = (String) jComboBox2.getSelectedItem();

        // Set maternity leave based on gender and marital status
        if ("Female".equalsIgnoreCase(gender) && "Married".equalsIgnoreCase(maritalStatus)) {
            maternityLeave = 98; // Number of days for maternity leave
        } else {
            maternityLeave = 0; // No maternity leave for others
        }
    }


        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();

        jTextField1.setBackground(new java.awt.Color(246, 246, 246));
        jTextField1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(17, 16, 16));
        jTextField1.setText("jTextField1");
        jTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField1.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel1.setText("First Name");

        jTextField2.setBackground(new java.awt.Color(246, 246, 246));
        jTextField2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(17, 16, 16));
        jTextField2.setText("jTextField1");
        jTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField2.setDisabledTextColor(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Last Name");

        jTextField3.setBackground(new java.awt.Color(246, 246, 246));
        jTextField3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(17, 16, 16));
        jTextField3.setText("jTextField1");
        jTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField3.setDisabledTextColor(new java.awt.Color(51, 51, 51));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("NRIC");

        jTextField4.setBackground(new java.awt.Color(246, 246, 246));
        jTextField4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(17, 16, 16));
        jTextField4.setText("jTextField1");
        jTextField4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField4.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("Username");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setText("Roles");

        jTextField5.setBackground(new java.awt.Color(246, 246, 246));
        jTextField5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(17, 16, 16));
        jTextField5.setText("jTextField1");
        jTextField5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField5.setDisabledTextColor(new java.awt.Color(51, 51, 51));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel6.setText("Password");

        jTextField6.setBackground(new java.awt.Color(246, 246, 246));
        jTextField6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(17, 16, 16));
        jTextField6.setText("jTextField1");
        jTextField6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField6.setDisabledTextColor(new java.awt.Color(51, 51, 51));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel7.setText("Gender");

        jComboBox1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTextField7.setBackground(new java.awt.Color(246, 246, 246));
        jTextField7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(17, 16, 16));
        jTextField7.setText("jTextField1");
        jTextField7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField7.setDisabledTextColor(new java.awt.Color(51, 51, 51));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel8.setText("Race");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel9.setText("Religion");

        jTextField8.setBackground(new java.awt.Color(246, 246, 246));
        jTextField8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(17, 16, 16));
        jTextField8.setText("jTextField1");
        jTextField8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField8.setDisabledTextColor(new java.awt.Color(51, 51, 51));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel10.setText("Place of Birth");

        jTextField9.setBackground(new java.awt.Color(246, 246, 246));
        jTextField9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(17, 16, 16));
        jTextField9.setText("jTextField1");
        jTextField9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField9.setDisabledTextColor(new java.awt.Color(51, 51, 51));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel11.setText("Marital Status");

        jComboBox2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel12.setText("E-Mail");

        jTextField10.setBackground(new java.awt.Color(246, 246, 246));
        jTextField10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField10.setForeground(new java.awt.Color(17, 16, 16));
        jTextField10.setText("jTextField1");
        jTextField10.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField10.setDisabledTextColor(new java.awt.Color(51, 51, 51));

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel13.setText("Contact NO.");

        jTextField11.setBackground(new java.awt.Color(246, 246, 246));
        jTextField11.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField11.setForeground(new java.awt.Color(17, 16, 16));
        jTextField11.setText("jTextField1");
        jTextField11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField11.setDisabledTextColor(new java.awt.Color(51, 51, 51));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel14.setText("Home Address");

        jTextArea1.setBackground(new java.awt.Color(246, 246, 246));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(17, 16, 16));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(jTextArea1);

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel15.setText("Bank  Name");

        jTextField12.setBackground(new java.awt.Color(246, 246, 246));
        jTextField12.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField12.setForeground(new java.awt.Color(17, 16, 16));
        jTextField12.setText("jTextField1");
        jTextField12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField12.setDisabledTextColor(new java.awt.Color(51, 51, 51));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel16.setText("Bank  NO.");

        jTextField13.setBackground(new java.awt.Color(246, 246, 246));
        jTextField13.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField13.setForeground(new java.awt.Color(17, 16, 16));
        jTextField13.setText("jTextField1");
        jTextField13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField13.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel17.setText("T.I.N NO.");

        jTextField14.setBackground(new java.awt.Color(246, 246, 246));
        jTextField14.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField14.setForeground(new java.awt.Color(17, 16, 16));
        jTextField14.setText("jTextField1");
        jTextField14.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField14.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel18.setText("BANK DETAILS");

        jTextField15.setBackground(new java.awt.Color(246, 246, 246));
        jTextField15.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField15.setForeground(new java.awt.Color(17, 16, 16));
        jTextField15.setText("jTextField1");
        jTextField15.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField15.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel19.setText("EPF NO.");

        jLabel20.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel20.setText("EMERGENCY CONTACT");

        jLabel21.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel21.setText("Name");

        jTextField16.setBackground(new java.awt.Color(246, 246, 246));
        jTextField16.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField16.setForeground(new java.awt.Color(17, 16, 16));
        jTextField16.setText("jTextField1");
        jTextField16.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField16.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel22.setText("NRIC");

        jTextField17.setBackground(new java.awt.Color(246, 246, 246));
        jTextField17.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField17.setForeground(new java.awt.Color(17, 16, 16));
        jTextField17.setText("jTextField1");
        jTextField17.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField17.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel23.setText("Relationship");

        jTextField18.setBackground(new java.awt.Color(246, 246, 246));
        jTextField18.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField18.setForeground(new java.awt.Color(17, 16, 16));
        jTextField18.setText("jTextField1");
        jTextField18.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField18.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel24.setText("Contact NO.");

        jTextField19.setBackground(new java.awt.Color(246, 246, 246));
        jTextField19.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField19.setForeground(new java.awt.Color(17, 16, 16));
        jTextField19.setText("jTextField1");
        jTextField19.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField19.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel25.setText("EMPLOYMENT HISTORY");

        jLabel26.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel26.setText("Working Exp.");

        jTextArea2.setBackground(new java.awt.Color(246, 246, 246));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextArea2.setForeground(new java.awt.Color(17, 16, 16));
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jScrollPane3.setViewportView(jTextArea2);

        jLabel27.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel27.setText("EMPLOYEMENT DETAILS");

        jLabel28.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel28.setText("Date Joined.");

        jTextField20.setBackground(new java.awt.Color(246, 246, 246));
        jTextField20.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField20.setForeground(new java.awt.Color(17, 16, 16));
        jTextField20.setText("jTextField1");
        jTextField20.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField20.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel29.setText("Position");

        jTextField21.setBackground(new java.awt.Color(246, 246, 246));
        jTextField21.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField21.setForeground(new java.awt.Color(17, 16, 16));
        jTextField21.setText("jTextField1");
        jTextField21.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField21.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField21ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel30.setText("Department");

        jTextField22.setBackground(new java.awt.Color(246, 246, 246));
        jTextField22.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField22.setForeground(new java.awt.Color(17, 16, 16));
        jTextField22.setText("jTextField1");
        jTextField22.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField22.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField22ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel31.setText("Monthly Gross (RM)");

        jTextField23.setBackground(new java.awt.Color(246, 246, 246));
        jTextField23.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField23.setForeground(new java.awt.Color(17, 16, 16));
        jTextField23.setText("jTextField1");
        jTextField23.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField23.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel32.setText("Date Resigned (if any)");

        jTextField24.setBackground(new java.awt.Color(246, 246, 246));
        jTextField24.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField24.setForeground(new java.awt.Color(17, 16, 16));
        jTextField24.setText("jTextField1");
        jTextField24.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField24.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField24ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel33.setText("ROLE ADVANCEMENT");

        jLabel34.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel34.setText("New Position");

        jLabel35.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel35.setText("Effective Date");

        jTextField26.setBackground(new java.awt.Color(246, 246, 246));
        jTextField26.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField26.setForeground(new java.awt.Color(17, 16, 16));
        jTextField26.setText("jTextField1");
        jTextField26.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField26.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField26ActionPerformed(evt);
            }
        });

        jTextField27.setBackground(new java.awt.Color(246, 246, 246));
        jTextField27.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField27.setForeground(new java.awt.Color(17, 16, 16));
        jTextField27.setText("jTextField1");
        jTextField27.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField27.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField27ActionPerformed(evt);
            }
        });

        jTextField25.setBackground(new java.awt.Color(246, 246, 246));
        jTextField25.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField25.setForeground(new java.awt.Color(17, 16, 16));
        jTextField25.setText("jTextField1");
        jTextField25.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField25.setDisabledTextColor(new java.awt.Color(51, 51, 51));

        jLabel36.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel36.setText("D.O.B");

        jTextField28.setBackground(new java.awt.Color(246, 246, 246));
        jTextField28.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField28.setForeground(new java.awt.Color(17, 16, 16));
        jTextField28.setText("jTextField1");
        jTextField28.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField28.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField28ActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel37.setText("ACC_Status");

        jTextField29.setBackground(new java.awt.Color(246, 246, 246));
        jTextField29.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField29.setForeground(new java.awt.Color(17, 16, 16));
        jTextField29.setText("jTextField1");
        jTextField29.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField29.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jTextField29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField29ActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel38.setText("Salary Increment (RM)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel3)
                                                    .addGap(30, 30, 30)))
                                            .addGap(24, 24, 24)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                                .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                                .addComponent(jTextField28)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel15)
                                                        .addComponent(jLabel16))
                                                    .addGap(21, 21, 21)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                            .addGap(31, 31, 31)
                                                            .addComponent(jLabel17)
                                                            .addGap(28, 28, 28))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(jLabel19)
                                                            .addGap(31, 31, 31)))
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(jLabel18)
                                                .addComponent(jLabel20)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel7)
                                                            .addComponent(jLabel8)
                                                            .addComponent(jLabel9)
                                                            .addComponent(jLabel10)
                                                            .addComponent(jLabel14))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(26, 26, 26)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addComponent(jLabel11)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jLabel12)
                                                                            .addComponent(jLabel13)
                                                                            .addComponent(jLabel36))
                                                                        .addGap(2, 2, 2)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel21)
                                                            .addComponent(jLabel22))
                                                        .addGap(52, 52, 52)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(35, 35, 35)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel23)
                                                            .addComponent(jLabel24))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(jTextField18, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                                            .addComponent(jTextField19))))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel4)
                                                            .addComponent(jLabel5)
                                                            .addComponent(jLabel6))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLabel37)))))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel26)
                                            .addGap(18, 18, 18)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel25))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel31)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField23))
                                    .addComponent(jLabel27)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel30)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel32)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel33)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel38))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField29)
                                    .addComponent(jTextField27, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                                .addGap(21, 21, 21)
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField26)))))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel13)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel26))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel33)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>                        

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            

    }                                           

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField21ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField26ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField27ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField28ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField29ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            


    // Variables declaration - do not modify                     
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration                   
}
