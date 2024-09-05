/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaasg;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.filechooser.FileSystemView;
/**
 *
 * @author acer
 */
public class viewcsv extends JFrame{
    private static final String FILE_PATH = "Employees_DetailsCSV.csv"; //change file name 
    private JTable table;
    private JFrame jf;
    private JScrollPane js;
    private String [] col;
    private Object [][] data;
    private static viewcsv instance;
    
    private viewcsv(){
        jf = new JFrame("User account");
        col = new String[]{"First name", "Last name", "NRIC", "username", "password", "Role"};
        data = getdata();
        table = new JTable(data, col);
        js = new JScrollPane(table);
        jf.add(js, BorderLayout.CENTER);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(800,600);
        jf.setVisible(true);
        
                
    }
    
    public static viewcsv getInstance(){
        if(instance == null){
            instance = new viewcsv();
        } return instance;
    }
    
    public void refreshTable(){
        data = getdata();
        table.setModel(new javax.swing.table.DefaultTableModel(data,col));
    }
    
    public JScrollPane getTableScrollPane() {
        return js;
    }

    // Alternative: Method to get the JTable directly
    public JTable getTable() {
        return table;
    }

    private Object[][] getdata() {
        try{
            BufferedReader r = new BufferedReader(new FileReader(FILE_PATH));
            ArrayList<String> list = new ArrayList();
            String read;
            while ((read = r.readLine())!= null){
                list.add(read);
            }
            
            // loop row then column
            int x = list.get(0).split(",").length;
            Object[][] data = new Object[list.size()][x];
            
            for(int row=0; row< list.size();row++){
                String[] rowd = list.get(row).split(",");
                for (int clm = 0; clm < col.length;clm++){
                    if (clm < rowd.length){
                        data[row][clm] = rowd[clm];
                    } else {
                        data [row][clm] = "";
                    }
                }
            }
            return data;
            
            
            
        }catch(IOException x){
            x.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            viewcsv csvView = viewcsv.getInstance();
            JFrame frame = new JFrame("User Account");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(csvView.getTableScrollPane());
        });
        
    }
    
}