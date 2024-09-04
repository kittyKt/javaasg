package Payroll;

public class DataSearch {

    private String text; // Could be used for full name or NRIC
    private String nric;
    private String firstName;
    private String lastName;

    // Constructors
    public DataSearch (String nric, String firstName, String lastName) {
        this.nric = nric;
        this.firstName = firstName;
        this.lastName = lastName;
        this.text = firstName + " " + lastName; // Example
    }

    public DataSearch (String text) {
        this.text = text;
    }

    // Getters and Setters
    public String getText () {
        return text;
    }

    public void setText (String text) {
        this.text = text;
    }

    public String getNric () {
        return nric;
    }

    public void setNric (String nric) {
        this.nric = nric;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString () {
        return firstName + " " + lastName + " (" + nric + ")";
    }
}
