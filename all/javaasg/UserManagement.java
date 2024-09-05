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
import java.util.ArrayList;


public class UserManagement {
    private static final String FILE_PATH = "userinfo.csv";
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int choice = 0; //register or login
        while (true) {
            System.out.println("Please select an option:\n1.Register\n2.Retrieve\n3.Update\n4.Delete\n5.Exit");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1 -> register(sc);
                case 2 -> retrieve(sc);
                case 3 -> update(sc);
                case 4 -> delete(sc);
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        }
    }
    
    //register new user
    public static void register(Scanner scanner){
        
        System.out.println("Enter user First Name:");
        String f_name = scanner.nextLine();
        
        System.out.println("Enter user Last Name:");
        String l_name = scanner.nextLine();
        
        System.out.println("Enter user NRIC:");
        String ic = scanner.nextLine();
        
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        
        //assign role
        System.out.println("Assign user role:");
        String role = scanner.nextLine();
        
        if(uexist(username)){
            System.out.println("User already exist. Please proceed to login.");
        } else {
            try(FileWriter writer = new FileWriter(FILE_PATH, true)){
                writer.write(f_name + "," +l_name + ","+ ic + "," + username + "," + password +  "," + role + "\n");
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
    public static void retrieve (Scanner scanner){
            System.out.println("Enter user name:");
            String name = scanner.nextLine();
            
            if (!uexist(name)){
                System.out.println("User does not exist.");
                return;
            }
            
            try{
            BufferedReader r = new BufferedReader(new FileReader(FILE_PATH));
            String readuser;
            while ((readuser = r.readLine())!= null){
                String[] user = readuser.split(",");
                if (user[0].equals(name)){
                    System.out.println("Name: " + user[0] + user[1]);
                    System.out.println("NRIC: " + user[2]);
                    System.out.println("Username: " + user[3]);
                    System.out.println("Password: " + user[4]);
                    System.out.println("Role: " + user[5]);
                    return;
                }
            
            }
            } catch (IOException e){
                    System.out.println("An error occur while retrieving user");
                    e.printStackTrace();
                }
        }
    
    public static void update(Scanner scanner){
        System.out.println("Enter user name:");
            String name = scanner.nextLine();
            
            if (!uexist(name)){
                System.out.println("User does not exist.");
                return;
            }
            
        //flag to choose section update
        boolean upPsw = false;
        boolean upUnm = false;
        boolean upR = false;
        
        //set to empty
        String newpassword = "";
        String newusername = "";
        String newrole = "";
        
        //select one to update
        int upchoice = 0;
        System.out.println("Choose which to update\n1.psw\n2.username\n3.role");
        upchoice = scanner.nextInt();
        scanner.nextLine();
        if (upchoice == 1){
            
        }
            
        
        //pssw
        System.out.println("Update password?");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
        System.out.println("Enter new password:");
        newpassword = scanner.nextLine();
        upPsw = true;
        }
        
        //username
        System.out.println("Update Username?");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
        System.out.println("Enter new username:");
        newusername = scanner.nextLine();
        upUnm = true;
        }
        
        //email
        System.out.println("Update email?");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
        System.out.println("Enter new email:");
        newrole = scanner.nextLine();
        upR = true;
        }
        
        
        try{
            BufferedReader r = new BufferedReader(new FileReader(FILE_PATH));
            ArrayList<String> list = new ArrayList();
            String updated;
            while ((updated = r.readLine())!= null){
                
                String[] newd = updated.split(",");
                
                if (newd[0].equals(name)){
                    if (upUnm) newd[1] = newusername;
                    if (upPsw) newd[2] = newpassword;
                    if (upR) newd[3] = newrole;
                } 
                list.add(String.join(",", newd));
            } r.close();
            
            //append
            FileWriter writer = new FileWriter(FILE_PATH);
            for (String updatedata : list){
                writer.write(updatedata + "\n");
                writer.flush();
            }
            writer.close();
            viewcsv instance = viewcsv.getInstance();
            instance.refreshTable();
            instance.setVisible(true);
        
        } catch (IOException e){
                    System.out.println("An error occur while retrieving user");
                    e.printStackTrace();
        }
    }
    
    public static void delete(Scanner scanner){
        System.out.println("Enter username to delete: ");
        String user = scanner.nextLine();
        
        if(!uexist(user)){
            System.out.println("User does not exist");
            return;
        }
        try{
            BufferedReader r = new BufferedReader(new FileReader(FILE_PATH));
            ArrayList<String> list = new ArrayList();
            String du;
            while ((du = r.readLine())!= null){
                String[] dltu = du.split(",");
                if(!dltu[1].equals(user)){
                    list.add(du);
                }
                
            } r.close();
            
            //delete
            FileWriter writer = new FileWriter(FILE_PATH);
            for (String ruser : list){
                writer.write(ruser + "\n");
                writer.flush();
            }
            writer.close();
            viewcsv instance = viewcsv.getInstance();
            instance.refreshTable();
            instance.setVisible(true);
            
            
            
            
        } catch (IOException e){
                    System.out.println("An error occur while retrieving user");
                    e.printStackTrace();
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
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

