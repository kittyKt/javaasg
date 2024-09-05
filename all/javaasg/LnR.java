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
public class LnR {
    private static final String FILE_PATH = "userinfo.csv";
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int choice = 0; //register or login
        while (true) {
            System.out.println("Please select an option:\n1.Register\n2.Login\n3.Exit");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1 -> register(sc);
                case 2 -> login(sc);
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        }
    }
    
    static boolean uexist(String username){
        try (Scanner filescanner = new Scanner(new File(FILE_PATH))){
            while (filescanner.hasNextLine()){
                String line = filescanner.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals(username)){
                    return true;
                }
               
            }
        } catch (IOException e){
            System.out.println("An error has occur...");
            e.printStackTrace();
        } return false;
    }
    
    
    
    static void register(Scanner scanner){
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        
        System.out.println("Enter user role:");
        String role = scanner.nextLine();
        
        if(uexist(username)){
            System.out.println("User already exist. Please proceed to login.");
        } else {
            try(FileWriter writer = new FileWriter(FILE_PATH, true)){
                writer.write(username + "," + password +  "," + role + "\n");
                writer.flush();
                System.out.println("Register Success!");
                
                viewcsv instance = viewcsv.getInstance();
                instance.refreshTable();
                instance.setVisible(true);
                
            } catch (IOException e){
                System.out.println("An error has occured while registering...");
                e.printStackTrace();
            }
        }  
}
        
    static void login(Scanner scanner){
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        
        if (validate(username, password)){
            System.out.println("Login Success!");
        } else {
            System.out.println("Invalid username or password");
        }
        
}
    
    static boolean validate(String username, String password){
        try (Scanner filescanner = new Scanner(new File(FILE_PATH))){
            while (filescanner.hasNextLine()){
                String line = filescanner.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals(username)&& parts[1].equals(password)){
                    return true;
                }
            }
        } catch (IOException e){
            System.out.println("An error occurred while validating credentials.");
            e.printStackTrace();
        } return false;
    }

    
}

