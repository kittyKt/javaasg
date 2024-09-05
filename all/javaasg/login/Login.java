/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaasg.login;

import java.util.Scanner;
import java.io.File; 
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author acer
 */
public class Login {
    private static final String FILE_PATH = "C:\\Users\\acer\\Documents\\NetBeansProjects\\JavaAsg\\src\\csv\\Employees_DetailsCSV.csv";
    
        
    public static boolean validate(String username, String password){
        String trimUsername = username.trim();
        String trimPassword = password.trim();
        
        
        try (Scanner filescanner = new Scanner(new File(FILE_PATH))){
            if (filescanner.hasNextLine()) {
            filescanner.nextLine();
        }
            while (filescanner.hasNextLine()){
                String line = filescanner.nextLine();
                String[] field = line.split(",");
                
                 
            for (int i = 0; i < field.length; i++) {
                System.out.println("parts[" + i + "]: " + field[i].trim());
            }
                
                if (field[3].trim().equals(trimUsername) && field[4].equals(trimPassword)) {
                    String[] adminDetails = { 
                        field[0], field[1], field[2], field[3], field[4], 
                        field[5], field[6], field[7], field[8], field[9], field[10],
                        field[11], field[12], field[13], field[14], field[15],
                        field[16], field[17], field[18], field[19], field[20],
                        field[21], field[22], field[23], field[24], field[25],
                        field[26], field[27], field[28], field[29] , field[30],
                        field[31], field[32], field[33], field[34], field[35],
                        field[36], field[37], field[38], field[39]
                    };
                    logintest.setuserDetails(adminDetails);
                    return true;
                }
            }
        } catch (IOException e){
            System.out.println("An error occurred while validating credentials.");
            e.printStackTrace();
        } return false;
    }

    
    public String checkrole(String username) {
    String trimUsername = username.trim();
    try (Scanner filescanner = new Scanner(new File(Login.FILE_PATH))) {
        while (filescanner.hasNextLine()) {
            String line = filescanner.nextLine();
            String[] parts = line.split(",");
            if (parts[3].trim().equals(trimUsername)) {
                return parts[6].trim(); 
            }
        }
    } catch (IOException e) {
        System.out.println("An error occurred while fetching the role.");
        e.printStackTrace();
    }
    return null; 
}
    
    public static String checkstatus(String username){
        String trimUsername = username.trim();
        try (Scanner filescanner = new Scanner(new File(Login.FILE_PATH))) {
            while (filescanner.hasNextLine()) {
                String line = filescanner.nextLine();
                String[] parts = line.split(",");
                if (parts[3].trim().equals(trimUsername)) {
                    return parts[5].trim(); 
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while fetching the role.");
            e.printStackTrace();
        }
        return null;
    }
    
    
     public static boolean lockacc(String username) {
        StringBuilder fileContent = new StringBuilder();
        
        String trimUsername = username.trim();
        boolean userFound = false;
        
        
        try (Scanner filescanner = new Scanner(new File(FILE_PATH))) {
            while (filescanner.hasNextLine()) {
                String line = filescanner.nextLine();
                String[] parts = line.split(",");
                if (parts[3].trim().equals(trimUsername)) {
                    parts[5] = "Locked"; // Change status to "Locked"
                    userFound = true;
                }
                fileContent.append(String.join(",", parts)).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while locking the user.");
            e.printStackTrace();
            return false;
        }

        if (userFound) {
            try (FileWriter writer = new FileWriter(new File(FILE_PATH))) {
                writer.write(fileContent.toString());
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
                return false;
            }
        }
        return userFound;
    }
     
     
}


