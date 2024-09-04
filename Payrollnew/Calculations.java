package Payroll;

import java.util.*;
import java.text.SimpleDateFormat;
import java.io.FileWriter;

public class Calculations {

    private double NetSal;
    private double Gsalary;
    private float epf;
    private float socso;
    private float eis;
    private float a_tax;
    private float pcb;

    public double getpcb () {
        return pcb;
    }

    public void setpcb (float pcb) {
        this.pcb = pcb;
    }

    public double getNetSal () {
        return NetSal;
    }

    public void setNetSal (double NetSal) {
        this.NetSal = NetSal;
    }

    public float getEpf () {
        return epf;
    }

    public void setEpf (float epf) {
        this.epf = epf;
    }

    public float getSocso () {
        return socso;
    }

    public void setSocso (float socso) {
        this.socso = socso;
    }

    public float getEis () {
        return eis;
    }

    public void setEis (float eis) {
        this.eis = eis;
    }

    public float getA_tax () {
        return a_tax;
    }

    public void setA_tax (float a_tax) {
        this.a_tax = a_tax;
    }

    public double getGsalary () {
        return Gsalary;
    }

    public void setGsalary (double Gsalary) {
        this.Gsalary = Gsalary;
    }

    public double calculateEPFEmployeeContribution () {
        return Gsalary * 0.11;
    }

    public double calculateEPFEmployerContribution () {
        return Gsalary * 0.13;
    }

    public double calculateSOCSOEmployeeContribution () {
        return Gsalary * 0.005;
    }

    public double calculateSOCSOEmployerContribution () {
        return Gsalary * 0.018;
    }

    public double calculateEISEmployeeContribution () {
        return Gsalary * 0.002;
    }

    public double calculateEISEmployerContribution () {
        return Gsalary * 0.002;
    }

    public double calculateAnnualTax () {
        return Gsalary * 0.05;
    }

    public double calculateTotalDeduction () {
        double epf = calculateEPFEmployeeContribution ();
        double socso = calculateSOCSOEmployeeContribution ();
        double eis = calculateEISEmployeeContribution ();
        double pcb = calculateAnnualTax () / 12;
        return epf + socso + eis + pcb;
    }
  
    public double calculateNetSalary () {
        double totalDeductions = calculateTotalDeduction();
        return Gsalary - totalDeductions;
    }
}
