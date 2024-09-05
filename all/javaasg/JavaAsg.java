/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaasg;

import javaasg.login.Login;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class JavaAsg {
    private static final String FILE_PATH = "userinfo.csv";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        int chance = 0; //chance of login
        boolean lg = false;
        
        while (chance < 3) {
            System.out.println("Enter username:");
            String username = sc.nextLine();
        
            System.out.println("Enter password:");
            String password = sc.nextLine();
            
            
            if (Login.validate(username, password)){
                lg = true;
                System.out.println("Login Success!");
                break;
            }else{
                System.out.println("Invalid username or password");
                chance ++;
            } 
        }
            if(!lg){
            System.out.println("Account Locked. Please contact the admin.");
        }
    }
    
}
