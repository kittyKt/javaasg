/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package departmentmanager;

import designcomponent.buttonlogin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author acer
 */
public class leavepanel extends javax.swing.JPanel {

    
    
   
    public leavepanel() {
        initComponents();
        setOpaque(false);
        name.setEditable(false);
        leave.setEditable(false);
        sdate.setEditable(false);
        edate.setEditable(false);
        duration.setEditable(false);
        
    }
    public void statusr(String user, String ltype, String sd, String ed, String td){
        String inqcsv = "C:\\Users\\acer\\Documents\\NetBeansProjects\\JavaAsg\\src\\csv\\leave_applications.csv";
        
        List<String[]> leave = new ArrayList<>();
        boolean coms = false;
        
         try (BufferedReader br = new BufferedReader(new FileReader(inqcsv))) {
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("Read line: " + line); // Debug
            String[] leavep = line.split(",");
            if (leavep.length > 5) {
                if (leavep[0].equals(user) && leavep[1].equalsIgnoreCase(ltype) &&
                    leavep[2].equalsIgnoreCase(sd) && leavep[3].equalsIgnoreCase(ed) && leavep[4].equalsIgnoreCase(td)) {
                    leavep[5] = "Rejected";
                    coms = true;
                }
                leave.add(leavep);
            } else {
                System.err.println("Invalid inquiry line: " + line);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    if (coms) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(inqcsv))) {
            for (String[] leavepa : leave) {
                String line = String.join(",", leavepa);
                pw.println(line);
                System.out.println("Written line: " + line); // Debug
            }
            System.out.println("CSV file updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("No matching ticket found or no update made.");
    
          }
}
    
    public void statusa(String user, String ltype, String sd, String ed, String td){
        String inqcsv = "C:\\Users\\acer\\Documents\\NetBeansProjects\\JavaAsg\\src\\csv\\leave_applications.csv";
        
        List<String[]> leave = new ArrayList<>();
        boolean coms = false;
        
         try (BufferedReader br = new BufferedReader(new FileReader(inqcsv))) {
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("Read line: " + line); // Debug
            String[] leavep = line.split(",");
            if (leavep.length > 5){
                if (leavep[0].equals(user) && leavep[1].equalsIgnoreCase(ltype) &&
                    leavep[2].equalsIgnoreCase(sd) && leavep[3].equalsIgnoreCase(ed) && leavep[4].equalsIgnoreCase(td) ) {
                    leavep[5] = "Approved";
                    coms = true;
                }
                leave.add(leavep);
            } else {
                System.err.println("Invalid inquiry line: " + line);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    if (coms) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(inqcsv))) {
            for (String[] leavepa : leave) {
                String line = String.join(",", leavepa);
                pw.println(line);
                System.out.println("Written line: " + line); // Debug
            }
            System.out.println("CSV file updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("No matching ticket found or no update made.");
    
          }
}
    
    

    public buttonlogin approveb(){
        return approve;
    }
    
    public buttonlogin rejectb(){
        return reject;
    }
    
    public void setnm(String nm){
        name.setText(nm);
    }
    
  
  public void setleave (String leavetype){
      leave.setText(leavetype);
  }

  public void setsdate(String start){
      sdate.setText(start);
  }
    
  
  public void setedate(String end){
      edate.setText(end);
  }
    
  public void setduration(String du){
      duration.setText(du);
  }
  
 
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundcornerlayer1 = new designcomponent.roundcornerlayer();
        jLabel1 = new javax.swing.JLabel();
        name = new designcomponent.roundtext();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        duration = new designcomponent.roundtext();
        leave = new designcomponent.roundtext();
        sdate = new designcomponent.roundtext();
        jLabel7 = new javax.swing.JLabel();
        edate = new designcomponent.roundtext();
        reject = new designcomponent.buttonlogin();
        approve = new designcomponent.buttonlogin();

        roundcornerlayer1.setBackground(new java.awt.Color(255, 255, 255));
        roundcornerlayer1.setRoundBottomLeft(50);
        roundcornerlayer1.setRoundBottomRight(50);
        roundcornerlayer1.setRoundTopLeft(50);
        roundcornerlayer1.setRoundTopRight(50);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Username");
        roundcornerlayer1.add(jLabel1);
        jLabel1.setBounds(40, 40, 100, 23);

        name.setBackground(new java.awt.Color(225, 228, 242));
        name.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        name.setRound(20);
        roundcornerlayer1.add(name);
        name.setBounds(160, 30, 270, 44);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("End Date");
        roundcornerlayer1.add(jLabel2);
        jLabel2.setBounds(10, 220, 130, 23);

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Leave Type");
        roundcornerlayer1.add(jLabel5);
        jLabel5.setBounds(10, 90, 130, 23);

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Duration");
        roundcornerlayer1.add(jLabel6);
        jLabel6.setBounds(60, 260, 80, 23);

        duration.setBackground(new java.awt.Color(225, 228, 242));
        duration.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        duration.setRound(20);
        roundcornerlayer1.add(duration);
        duration.setBounds(160, 260, 160, 44);

        leave.setBackground(new java.awt.Color(225, 228, 242));
        leave.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        leave.setRound(20);
        roundcornerlayer1.add(leave);
        leave.setBounds(160, 90, 270, 44);

        sdate.setBackground(new java.awt.Color(225, 228, 242));
        sdate.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        sdate.setRound(20);
        roundcornerlayer1.add(sdate);
        sdate.setBounds(160, 150, 270, 44);

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Start Date");
        roundcornerlayer1.add(jLabel7);
        jLabel7.setBounds(10, 160, 130, 23);

        edate.setBackground(new java.awt.Color(225, 228, 242));
        edate.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        edate.setRound(20);
        roundcornerlayer1.add(edate);
        edate.setBounds(160, 210, 270, 44);

        reject.setBackground(new java.awt.Color(84, 24, 24));
        reject.setText("Reject");
        reject.setColor(new java.awt.Color(84, 24, 24));
        reject.setColorClick(new java.awt.Color(66, 9, 9));
        reject.setColorOver(new java.awt.Color(110, 29, 29));
        reject.setRadius(30);
        roundcornerlayer1.add(reject);
        reject.setBounds(190, 320, 110, 40);

        approve.setBackground(new java.awt.Color(87, 119, 83));
        approve.setText("Approve");
        approve.setColor(new java.awt.Color(87, 119, 83));
        approve.setColorClick(new java.awt.Color(66, 98, 62));
        approve.setColorOver(new java.awt.Color(67, 119, 53));
        approve.setRadius(30);
        roundcornerlayer1.add(approve);
        approve.setBounds(320, 320, 110, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundcornerlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundcornerlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private designcomponent.buttonlogin approve;
    private designcomponent.roundtext duration;
    private designcomponent.roundtext edate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private designcomponent.roundtext leave;
    private designcomponent.roundtext name;
    private designcomponent.buttonlogin reject;
    private designcomponent.roundcornerlayer roundcornerlayer1;
    private designcomponent.roundtext sdate;
    // End of variables declaration//GEN-END:variables
}
