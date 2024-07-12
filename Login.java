/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaasg;

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
    private static final String FILE_PATH = "userinfo.csv";
    
        
    public static boolean validate(String username, String password){
        String trimUsername = username.trim();
        try (Scanner filescanner = new Scanner(new File(FILE_PATH))){
            while (filescanner.hasNextLine()){
                String line = filescanner.nextLine();
                String[] parts = line.split(",");
                if (parts[0].trim().equals(trimUsername) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e){
            System.out.println("An error occurred while validating credentials.");
            e.printStackTrace();
        } return false;
    }

    
}

