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
    private static final String FILE_PATH = "userinfo.csv";
    private JTable table;
    private JFrame jf;
    private JScrollPane js;
    private String [] col;
    private Object [][] data;
    private static viewcsv instance;
    
    public viewcsv(){
        jf = new JFrame("User account");
        col = new String[]{"username", "password"};
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

    private Object[][] getdata() {
        try{
            
            BufferedReader r = new BufferedReader(new FileReader(FILE_PATH));
            ArrayList<String> list = new ArrayList();
            String read;
            while ((read = r.readLine())!= null){
                list.add(read);
            }
            
            int x = list.get(0).split(",").length;
            Object[][] data = new Object[list.size()][x];
            for(int i=0; i< list.size();i++){
                data[i] = list.get(i).split(",");
            }
            return data;
            
            
            
        }catch(Exception x){
            x.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> viewcsv.getInstance().setVisible(true));
        
    }
    
}