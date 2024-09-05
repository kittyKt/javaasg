/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaasg;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class adduserv {
    private static final String FILE_PATH = "C:\\Users\\acer\\Documents\\NetBeansProjects\\JavaAsg\\src\\csv\\Employees_DetailsCSV.csv";
    
        
    public static boolean validateu(String NRIC){
        String trimNRIC = NRIC.trim();
        
        try (Scanner filescanner = new Scanner(new File(FILE_PATH))){
            while (filescanner.hasNextLine()){
                String line = filescanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length > 2 && parts[2].trim().equals(trimNRIC)) {
                    return true;
                }
            }
        } catch (IOException e){
            System.out.println("An error occurred while validating credentials.");
            e.printStackTrace();
        } return false;
    }
    
    public static boolean validatefp(String txtNRIC){
        String trimtxtNRIC = txtNRIC.trim();
        
        try (Scanner filescanner = new Scanner(new File(FILE_PATH))){
            while (filescanner.hasNextLine()){
                String line = filescanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length > 2 && parts[2].trim().equals(trimtxtNRIC)) {
                    return true;
                }
            }
        } catch (IOException e){
            System.out.println("An error occurred while validating credentials.");
            e.printStackTrace();
        } return false;
    }

    
    public static boolean vusernm(String username){
        String trimunm = username.trim();
        
        try (Scanner filescanner = new Scanner(new File(FILE_PATH))){
            while (filescanner.hasNextLine()){
                String line = filescanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length > 3 && parts[3].trim().equals(trimunm)) {
                    return true;
                }
            }
        } catch (IOException e){
            System.out.println("An error occurred while validating credentials.");
            e.printStackTrace();
        } return false;
    }
    
    
}

