/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package departmentmanager;

import javaasg.*;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javaasg.login.logintest;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.toast.Notifications;

/**
 *
 * @author acer
 */
public class Profiledepart extends javax.swing.JFrame {

    /**
     * Creates new form Profile
     */
    public Profiledepart() {
        initComponents();
        raven.popup.GlassPanePopup.install(this);
        raven.toast.Notifications.getInstance().setJFrame(this);
    }
    
        public boolean setuserprofile(String username, String password) {
            
        String file="C:\\Users\\acer\\Documents\\NetBeansProjects\\JavaAsg\\src\\csv\\Employees_DetailsCSV.csv";
        
        boolean isprofileloaded = false;

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] field = line.split(",");
            if (field.length > 20) {
                // Check if the username and password match
                if (field[3].equals(username) && field[4].equals(password)) {
                    // Set the profile details
                    setfn(field[0]);
                    setln(field[1]);
                    setrole(field[6]);
                    setdepart(field[27]);
                    setusername(field[3]);
                    //setpassword(field[4]);
                    setdob(field[7]);
                    setemail(field[11]);
                    setgender(field[13]);
                    setaddress(field[12]);
                    setmarital(field[14]);
                    setphone(field[15]);
                    setrace(field[8]);
                    setposition(field[27]);
                    setworkexp(field[24]); 
                    setdatejoin(field[25]); 

                    // Set header
                    setname(field[0] + " " + field[1]);
                    setdepartsmall(field[28]);
                    setrolehuge(field[6]);
                    
                    
                    setbnm(field[16]); 
                    setbno(field[17]);  
                    setepf(field[18]);  
                    settin(field[19]);
                    setsalary(field[29]);
                    
                    setename(field[20]); 
                    setenric(field[21]); 
                    seterelation(field[22]); 
                    setephone(field[23]); 
                    
                    setannual(field[30]); 
                    setmedical(field[31]);  
                    setmaternity(field[32]);  
                    setunpaid(field[33]); 
                    
                    sethpos(field[36]);
                    seths(field[38]);
                    

                    isprofileloaded = true;
                    break;
                
                }
                } else {
                    // Handle case where line doesn't have enough field
                    System.err.println("Line does not have enough fieldfield: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isprofileloaded;
        
    }
    
   public void updateProfileImage(String imagePath) {
        ImageIcon profileIcon = new ImageIcon(imagePath);
        profilepic.setIcon(profileIcon);
        revalidate();
        repaint();
    }

   
        
        
 public String getfn(){
     return fnm.getText();
 }
 
 public String getln(){
     return Lastnm.getText();
 }
    
    public String getusername(){
        return usernamefield.getText();
    }
    
   
    
    public void setfn(String fn){
        fnm.setText(fn);
    }
    
    public void setworkexp(String exp){
        workexp.setText(exp);
    }
        
    public void setln(String ln){
        Lastnm.setText(ln);
    }
    
    public void setrole(String arole){
        role.setText(arole);
    }
    
    public void setdepart(String depart){
        department.setText(depart);
    }
    
    public void setusername(String Username){
        usernamefield.setText(Username);
    }
    
    public void setdob(String DOB){
        dob.setText(DOB);
    }
    
    public void setemail(String Email){
        email.setText(Email);
    }
    
    public void setgender(String gen){
        gender.setText(gen);
    }
    
    public void setaddress(String addr){
        address.setText(addr);
    }
    
    public void setmarital(String mari){
        marital.setText(mari);
    }
    
    public void setdepartsmall(String departm){
        depart.setText(departm);
    }
    
    public void setrolehuge(String rolet){
        Role.setText(rolet);
    }
    
    public void setname(String nm){
        name.setText(nm);
    }
    
    public void setphone(String ph){
        phone.setText(ph);
    }
    
    public void setrace(String rac){
        race.setText(rac);
    }
    
    public void setposition(String pos){
        position.setText(pos);
    }
    
    public void setdatejoin(String date){
        datejoin.setText(date);
    }
    
    public void setename(String name){
        ename.setText(name);
    }
    
    public void setenric(String nric){
        enric.setText(nric);
    }
     
    public void seterelation(String relation){
        erelation.setText(relation);
    }

    public void setephone(String phone){
        ephone.setText(phone);
    }
    
    public void setbnm(String bank){
        banknm.setText(bank);
    }
    
    public void setbno(String bankn){
        bankno.setText(bankn);
    }
    
    public void setepf(String bepf){
        epf.setText(bepf);
    }
    
    public void settin(String btin){
        tin.setText(btin);
    }
    
    public void setsalary(String sal){
        msalary.setText(sal);
    }
    
    public void setannual(String annual){
        annuall.setText(annual);
    }
    
    public void setunpaid(String unpaid){
        unpaidl.setText(unpaid);
    }
    
    public void setmedical(String med){
        medicall.setText(med);
    }
    
    public void setmaternity(String mater){
        maternityl.setText(mater);
    }
    
    public void seths(String hsal){
        hsalary.setText(hsal);
    }
    
    public void sethpos(String hpos){
        hposition.setText(hpos);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        roundcornerlayer1 = new designcomponent.roundcornerlayer();
        profilepic = new javax.swing.JLabel();
        datejoin = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        department = new javax.swing.JLabel();
        Role = new javax.swing.JLabel();
        roundcornerlayer2 = new designcomponent.roundcornerlayer();
        jLabel3 = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        lnm = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Lastnm = new designcomponent.roundtext();
        race = new designcomponent.roundtext();
        fnm = new designcomponent.roundtext();
        phone = new designcomponent.roundtext();
        gender = new designcomponent.roundtext();
        dob = new designcomponent.roundtext();
        marital = new designcomponent.roundtext();
        address = new designcomponent.roundtext();
        email = new designcomponent.roundtext();
        jLabel14 = new javax.swing.JLabel();
        workexp = new designcomponent.roundtext();
        roundcornerlayer3 = new designcomponent.roundcornerlayer();
        jLabel11 = new javax.swing.JLabel();
        usernamefield = new designcomponent.roundtext();
        label1 = new javax.swing.JLabel();
        changepsw = new javax.swing.JButton();
        back = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        roundcornerlayer4 = new designcomponent.roundcornerlayer();
        jLabel12 = new javax.swing.JLabel();
        depart = new designcomponent.roundtext();
        label2 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        role = new designcomponent.roundtext();
        label5 = new javax.swing.JLabel();
        position = new designcomponent.roundtext();
        roundcornerlayer5 = new designcomponent.roundcornerlayer();
        jLabel13 = new javax.swing.JLabel();
        ename = new designcomponent.roundtext();
        label3 = new javax.swing.JLabel();
        label6 = new javax.swing.JLabel();
        enric = new designcomponent.roundtext();
        ephone = new designcomponent.roundtext();
        label7 = new javax.swing.JLabel();
        label29 = new javax.swing.JLabel();
        erelation = new designcomponent.roundtext();
        roundcornerlayer9 = new designcomponent.roundcornerlayer();
        jLabel18 = new javax.swing.JLabel();
        banknm = new designcomponent.roundtext();
        label17 = new javax.swing.JLabel();
        label18 = new javax.swing.JLabel();
        bankno = new designcomponent.roundtext();
        epf = new designcomponent.roundtext();
        label19 = new javax.swing.JLabel();
        label30 = new javax.swing.JLabel();
        tin = new designcomponent.roundtext();
        label31 = new javax.swing.JLabel();
        msalary = new designcomponent.roundtext();
        roundcornerlayer13 = new designcomponent.roundcornerlayer();
        jLabel22 = new javax.swing.JLabel();
        hsalary = new designcomponent.roundtext();
        label32 = new javax.swing.JLabel();
        hposition = new designcomponent.roundtext();
        label34 = new javax.swing.JLabel();
        roundcornerlayer17 = new designcomponent.roundcornerlayer();
        jLabel26 = new javax.swing.JLabel();
        annuall = new designcomponent.roundtext();
        label46 = new javax.swing.JLabel();
        label47 = new javax.swing.JLabel();
        medicall = new designcomponent.roundtext();
        unpaidl = new designcomponent.roundtext();
        label48 = new javax.swing.JLabel();
        roundcornerlayer18 = new designcomponent.roundcornerlayer();
        jLabel27 = new javax.swing.JLabel();
        roundtext59 = new designcomponent.roundtext();
        label49 = new javax.swing.JLabel();
        label50 = new javax.swing.JLabel();
        roundtext60 = new designcomponent.roundtext();
        roundtext61 = new designcomponent.roundtext();
        label51 = new javax.swing.JLabel();
        roundcornerlayer19 = new designcomponent.roundcornerlayer();
        jLabel28 = new javax.swing.JLabel();
        roundtext62 = new designcomponent.roundtext();
        label52 = new javax.swing.JLabel();
        label53 = new javax.swing.JLabel();
        roundtext63 = new designcomponent.roundtext();
        roundtext64 = new designcomponent.roundtext();
        label54 = new javax.swing.JLabel();
        roundcornerlayer20 = new designcomponent.roundcornerlayer();
        jLabel29 = new javax.swing.JLabel();
        roundtext65 = new designcomponent.roundtext();
        label55 = new javax.swing.JLabel();
        label56 = new javax.swing.JLabel();
        roundtext66 = new designcomponent.roundtext();
        roundtext67 = new designcomponent.roundtext();
        label57 = new javax.swing.JLabel();
        label58 = new javax.swing.JLabel();
        maternityl = new designcomponent.roundtext();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLayeredPane1.setBackground(new java.awt.Color(224, 220, 203));
        jLayeredPane1.setOpaque(true);

        roundcornerlayer1.setBackground(new java.awt.Color(255, 255, 255));
        roundcornerlayer1.setRoundBottomRight(50);
        roundcornerlayer1.setRoundTopRight(50);

        profilepic.setOpaque(true);
        roundcornerlayer1.add(profilepic);
        profilepic.setBounds(0, 0, 180, 180);

        datejoin.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        datejoin.setForeground(new java.awt.Color(153, 153, 153));
        datejoin.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        datejoin.setText("Date joined");
        roundcornerlayer1.add(datejoin);
        datejoin.setBounds(860, 130, 280, 40);

        name.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        name.setForeground(new java.awt.Color(51, 51, 51));
        name.setText("Name");
        roundcornerlayer1.add(name);
        name.setBounds(200, 20, 880, 40);

        department.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        department.setForeground(new java.awt.Color(153, 153, 153));
        department.setText("Department");
        roundcornerlayer1.add(department);
        department.setBounds(200, 70, 860, 40);

        Role.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        Role.setForeground(new java.awt.Color(153, 153, 153));
        Role.setText("Position");
        roundcornerlayer1.add(Role);
        Role.setBounds(230, 120, 480, 40);

        roundcornerlayer2.setBackground(new java.awt.Color(255, 255, 255));
        roundcornerlayer2.setRoundBottomLeft(50);
        roundcornerlayer2.setRoundBottomRight(50);
        roundcornerlayer2.setRoundTopLeft(50);
        roundcornerlayer2.setRoundTopRight(50);

        jLabel3.setFont(new java.awt.Font("Helvetica", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Personal Details");
        roundcornerlayer2.add(jLabel3);
        jLabel3.setBounds(30, 20, 300, 40);

        label.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label.setForeground(new java.awt.Color(102, 102, 102));
        label.setText("First Name");
        roundcornerlayer2.add(label);
        label.setBounds(40, 70, 120, 30);

        lnm.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        lnm.setForeground(new java.awt.Color(102, 102, 102));
        lnm.setText("Last Name");
        roundcornerlayer2.add(lnm);
        lnm.setBounds(380, 70, 160, 30);

        jLabel6.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("DOB");
        roundcornerlayer2.add(jLabel6);
        jLabel6.setBounds(50, 170, 90, 30);

        jLabel8.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Gender");
        roundcornerlayer2.add(jLabel8);
        jLabel8.setBounds(280, 170, 120, 30);

        jLabel7.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Race");
        roundcornerlayer2.add(jLabel7);
        jLabel7.setBounds(500, 170, 80, 30);

        jLabel10.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Marital");
        roundcornerlayer2.add(jLabel10);
        jLabel10.setBounds(50, 270, 140, 30);

        jLabel4.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Email");
        roundcornerlayer2.add(jLabel4);
        jLabel4.setBounds(50, 360, 130, 30);

        jLabel5.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Phone No.");
        roundcornerlayer2.add(jLabel5);
        jLabel5.setBounds(380, 360, 170, 30);

        jLabel9.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Home Address");
        roundcornerlayer2.add(jLabel9);
        jLabel9.setBounds(40, 450, 160, 30);

        Lastnm.setEditable(false);
        Lastnm.setBackground(new java.awt.Color(237, 238, 242));
        Lastnm.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Lastnm.setRound(25);
        Lastnm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LastnmActionPerformed(evt);
            }
        });
        roundcornerlayer2.add(Lastnm);
        Lastnm.setBounds(380, 100, 300, 41);

        race.setEditable(false);
        race.setBackground(new java.awt.Color(237, 238, 242));
        race.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        race.setRound(25);
        race.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                raceActionPerformed(evt);
            }
        });
        roundcornerlayer2.add(race);
        race.setBounds(490, 200, 190, 41);

        fnm.setEditable(false);
        fnm.setBackground(new java.awt.Color(237, 238, 242));
        fnm.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        fnm.setRound(25);
        fnm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnmActionPerformed(evt);
            }
        });
        roundcornerlayer2.add(fnm);
        fnm.setBounds(40, 100, 300, 41);

        phone.setEditable(false);
        phone.setBackground(new java.awt.Color(237, 238, 242));
        phone.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        phone.setRound(25);
        phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneActionPerformed(evt);
            }
        });
        roundcornerlayer2.add(phone);
        phone.setBounds(370, 390, 310, 41);

        gender.setEditable(false);
        gender.setBackground(new java.awt.Color(237, 238, 242));
        gender.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        gender.setRound(25);
        gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderActionPerformed(evt);
            }
        });
        roundcornerlayer2.add(gender);
        gender.setBounds(270, 200, 190, 41);

        dob.setEditable(false);
        dob.setBackground(new java.awt.Color(237, 238, 242));
        dob.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        dob.setRound(25);
        dob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dobActionPerformed(evt);
            }
        });
        roundcornerlayer2.add(dob);
        dob.setBounds(40, 200, 190, 41);

        marital.setEditable(false);
        marital.setBackground(new java.awt.Color(237, 238, 242));
        marital.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        marital.setRound(25);
        marital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maritalActionPerformed(evt);
            }
        });
        roundcornerlayer2.add(marital);
        marital.setBounds(40, 300, 310, 41);

        address.setEditable(false);
        address.setBackground(new java.awt.Color(237, 238, 242));
        address.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        address.setRound(25);
        address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressActionPerformed(evt);
            }
        });
        roundcornerlayer2.add(address);
        address.setBounds(40, 480, 640, 70);

        email.setEditable(false);
        email.setBackground(new java.awt.Color(237, 238, 242));
        email.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        email.setRound(25);
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        roundcornerlayer2.add(email);
        email.setBounds(40, 390, 310, 41);

        jLabel14.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Working Experience");
        roundcornerlayer2.add(jLabel14);
        jLabel14.setBounds(380, 270, 220, 30);

        workexp.setEditable(false);
        workexp.setBackground(new java.awt.Color(237, 238, 242));
        workexp.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        workexp.setRound(25);
        workexp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workexpActionPerformed(evt);
            }
        });
        roundcornerlayer2.add(workexp);
        workexp.setBounds(370, 300, 310, 41);

        roundcornerlayer3.setBackground(new java.awt.Color(255, 255, 255));
        roundcornerlayer3.setRoundBottomLeft(50);
        roundcornerlayer3.setRoundBottomRight(50);
        roundcornerlayer3.setRoundTopLeft(50);
        roundcornerlayer3.setRoundTopRight(50);

        jLabel11.setFont(new java.awt.Font("Helvetica", 1, 30)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Account Details");
        roundcornerlayer3.add(jLabel11);
        jLabel11.setBounds(30, 20, 300, 40);

        usernamefield.setEditable(false);
        usernamefield.setBackground(new java.awt.Color(237, 238, 242));
        usernamefield.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        usernamefield.setRound(25);
        usernamefield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernamefieldActionPerformed(evt);
            }
        });
        roundcornerlayer3.add(usernamefield);
        usernamefield.setBounds(30, 100, 380, 40);

        label1.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label1.setForeground(new java.awt.Color(102, 102, 102));
        label1.setText("Username");
        roundcornerlayer3.add(label1);
        label1.setBounds(30, 70, 120, 30);

        changepsw.setText("Change Password");
        changepsw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changepswActionPerformed(evt);
            }
        });
        roundcornerlayer3.add(changepsw);
        changepsw.setBounds(30, 160, 380, 30);

        back.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        back.setForeground(new java.awt.Color(28, 15, 9));
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaasg/image/left-arrow.png"))); // NOI18N
        back.setText("Back");
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(76, 8, 8));
        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton1.setText("Log Out");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        roundcornerlayer4.setBackground(new java.awt.Color(255, 255, 255));
        roundcornerlayer4.setRoundBottomLeft(50);
        roundcornerlayer4.setRoundBottomRight(50);
        roundcornerlayer4.setRoundTopLeft(50);
        roundcornerlayer4.setRoundTopRight(50);

        jLabel12.setFont(new java.awt.Font("Helvetica", 1, 30)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Job Details");
        roundcornerlayer4.add(jLabel12);
        jLabel12.setBounds(30, 20, 300, 40);

        depart.setEditable(false);
        depart.setBackground(new java.awt.Color(237, 238, 242));
        depart.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        depart.setRound(25);
        depart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departActionPerformed(evt);
            }
        });
        roundcornerlayer4.add(depart);
        depart.setBounds(30, 100, 380, 40);

        label2.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label2.setForeground(new java.awt.Color(102, 102, 102));
        label2.setText("Department");
        roundcornerlayer4.add(label2);
        label2.setBounds(30, 70, 120, 30);

        label4.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label4.setForeground(new java.awt.Color(102, 102, 102));
        label4.setText("Role");
        roundcornerlayer4.add(label4);
        label4.setBounds(30, 160, 120, 30);

        role.setEditable(false);
        role.setBackground(new java.awt.Color(237, 238, 242));
        role.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        role.setRound(25);
        role.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleActionPerformed(evt);
            }
        });
        roundcornerlayer4.add(role);
        role.setBounds(30, 190, 380, 40);

        label5.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label5.setForeground(new java.awt.Color(102, 102, 102));
        label5.setText("Position");
        roundcornerlayer4.add(label5);
        label5.setBounds(30, 250, 120, 30);

        position.setEditable(false);
        position.setBackground(new java.awt.Color(237, 238, 242));
        position.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        position.setRound(25);
        position.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                positionActionPerformed(evt);
            }
        });
        roundcornerlayer4.add(position);
        position.setBounds(30, 280, 380, 40);

        roundcornerlayer5.setBackground(new java.awt.Color(255, 255, 255));
        roundcornerlayer5.setRoundBottomLeft(50);
        roundcornerlayer5.setRoundBottomRight(50);
        roundcornerlayer5.setRoundTopLeft(50);
        roundcornerlayer5.setRoundTopRight(50);

        jLabel13.setFont(new java.awt.Font("Helvetica", 1, 30)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Emergency Contact");
        roundcornerlayer5.add(jLabel13);
        jLabel13.setBounds(30, 20, 300, 40);

        ename.setEditable(false);
        ename.setBackground(new java.awt.Color(237, 238, 242));
        ename.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ename.setRound(25);
        ename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enameActionPerformed(evt);
            }
        });
        roundcornerlayer5.add(ename);
        ename.setBounds(30, 100, 380, 40);

        label3.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label3.setForeground(new java.awt.Color(102, 102, 102));
        label3.setText("Name");
        roundcornerlayer5.add(label3);
        label3.setBounds(30, 70, 120, 30);

        label6.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label6.setForeground(new java.awt.Color(102, 102, 102));
        label6.setText("NRIC");
        roundcornerlayer5.add(label6);
        label6.setBounds(30, 150, 120, 30);

        enric.setEditable(false);
        enric.setBackground(new java.awt.Color(237, 238, 242));
        enric.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        enric.setRound(25);
        enric.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enricActionPerformed(evt);
            }
        });
        roundcornerlayer5.add(enric);
        enric.setBounds(30, 180, 250, 40);

        ephone.setEditable(false);
        ephone.setBackground(new java.awt.Color(237, 238, 242));
        ephone.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ephone.setRound(25);
        ephone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ephoneActionPerformed(evt);
            }
        });
        roundcornerlayer5.add(ephone);
        ephone.setBounds(30, 340, 380, 40);

        label7.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label7.setForeground(new java.awt.Color(102, 102, 102));
        label7.setText("Phone No.");
        roundcornerlayer5.add(label7);
        label7.setBounds(30, 310, 120, 30);

        label29.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label29.setForeground(new java.awt.Color(102, 102, 102));
        label29.setText("Relation");
        roundcornerlayer5.add(label29);
        label29.setBounds(30, 230, 140, 30);

        erelation.setEditable(false);
        erelation.setBackground(new java.awt.Color(237, 238, 242));
        erelation.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        erelation.setRound(25);
        erelation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                erelationActionPerformed(evt);
            }
        });
        roundcornerlayer5.add(erelation);
        erelation.setBounds(30, 260, 250, 40);

        roundcornerlayer9.setBackground(new java.awt.Color(255, 255, 255));
        roundcornerlayer9.setRoundBottomLeft(50);
        roundcornerlayer9.setRoundBottomRight(50);
        roundcornerlayer9.setRoundTopLeft(50);
        roundcornerlayer9.setRoundTopRight(50);

        jLabel18.setFont(new java.awt.Font("Helvetica", 1, 30)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Bank & Salary Details");
        roundcornerlayer9.add(jLabel18);
        jLabel18.setBounds(30, 20, 380, 40);

        banknm.setEditable(false);
        banknm.setBackground(new java.awt.Color(237, 238, 242));
        banknm.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        banknm.setRound(25);
        banknm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                banknmActionPerformed(evt);
            }
        });
        roundcornerlayer9.add(banknm);
        banknm.setBounds(30, 100, 310, 40);

        label17.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label17.setForeground(new java.awt.Color(102, 102, 102));
        label17.setText("Bank Name");
        roundcornerlayer9.add(label17);
        label17.setBounds(30, 70, 120, 30);

        label18.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label18.setForeground(new java.awt.Color(102, 102, 102));
        label18.setText("Bank Number");
        roundcornerlayer9.add(label18);
        label18.setBounds(380, 70, 120, 30);

        bankno.setEditable(false);
        bankno.setBackground(new java.awt.Color(237, 238, 242));
        bankno.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        bankno.setRound(25);
        bankno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                banknoActionPerformed(evt);
            }
        });
        roundcornerlayer9.add(bankno);
        bankno.setBounds(380, 100, 310, 40);

        epf.setEditable(false);
        epf.setBackground(new java.awt.Color(237, 238, 242));
        epf.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        epf.setRound(25);
        epf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                epfActionPerformed(evt);
            }
        });
        roundcornerlayer9.add(epf);
        epf.setBounds(30, 220, 250, 40);

        label19.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label19.setForeground(new java.awt.Color(102, 102, 102));
        label19.setText("EPF");
        roundcornerlayer9.add(label19);
        label19.setBounds(30, 190, 120, 30);

        label30.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label30.setForeground(new java.awt.Color(102, 102, 102));
        label30.setText("T.I.N");
        roundcornerlayer9.add(label30);
        label30.setBounds(310, 190, 120, 30);

        tin.setEditable(false);
        tin.setBackground(new java.awt.Color(237, 238, 242));
        tin.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tin.setRound(25);
        tin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tinActionPerformed(evt);
            }
        });
        roundcornerlayer9.add(tin);
        tin.setBounds(310, 220, 380, 40);

        label31.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label31.setForeground(new java.awt.Color(102, 102, 102));
        label31.setText("Monthly Gross Salary");
        roundcornerlayer9.add(label31);
        label31.setBounds(30, 310, 210, 30);

        msalary.setEditable(false);
        msalary.setBackground(new java.awt.Color(237, 238, 242));
        msalary.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        msalary.setRound(25);
        msalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msalaryActionPerformed(evt);
            }
        });
        roundcornerlayer9.add(msalary);
        msalary.setBounds(30, 340, 380, 40);

        roundcornerlayer13.setBackground(new java.awt.Color(255, 255, 255));
        roundcornerlayer13.setRoundBottomLeft(50);
        roundcornerlayer13.setRoundBottomRight(50);
        roundcornerlayer13.setRoundTopLeft(50);
        roundcornerlayer13.setRoundTopRight(50);

        jLabel22.setFont(new java.awt.Font("Helvetica", 1, 30)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("History");
        roundcornerlayer13.add(jLabel22);
        jLabel22.setBounds(30, 20, 380, 40);

        hsalary.setEditable(false);
        hsalary.setBackground(new java.awt.Color(237, 238, 242));
        hsalary.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        hsalary.setRound(25);
        hsalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hsalaryActionPerformed(evt);
            }
        });
        roundcornerlayer13.add(hsalary);
        hsalary.setBounds(30, 100, 420, 40);

        label32.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label32.setForeground(new java.awt.Color(102, 102, 102));
        label32.setText("Salary Increment");
        roundcornerlayer13.add(label32);
        label32.setBounds(30, 70, 270, 30);

        hposition.setEditable(false);
        hposition.setBackground(new java.awt.Color(237, 238, 242));
        hposition.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        hposition.setRound(25);
        hposition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hpositionActionPerformed(evt);
            }
        });
        roundcornerlayer13.add(hposition);
        hposition.setBounds(30, 220, 420, 40);

        label34.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label34.setForeground(new java.awt.Color(102, 102, 102));
        label34.setText("Change of Position");
        roundcornerlayer13.add(label34);
        label34.setBounds(30, 190, 170, 30);

        roundcornerlayer17.setBackground(new java.awt.Color(255, 255, 255));
        roundcornerlayer17.setRoundBottomLeft(50);
        roundcornerlayer17.setRoundBottomRight(50);
        roundcornerlayer17.setRoundTopLeft(50);
        roundcornerlayer17.setRoundTopRight(50);

        jLabel26.setFont(new java.awt.Font("Helvetica", 1, 30)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setText("Leave Entitlement");
        roundcornerlayer17.add(jLabel26);
        jLabel26.setBounds(30, 20, 380, 40);

        annuall.setEditable(false);
        annuall.setBackground(new java.awt.Color(237, 238, 242));
        annuall.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        annuall.setRound(25);
        annuall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annuallActionPerformed(evt);
            }
        });
        roundcornerlayer17.add(annuall);
        annuall.setBounds(30, 100, 300, 40);

        label46.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label46.setForeground(new java.awt.Color(102, 102, 102));
        label46.setText("Annual Leave");
        roundcornerlayer17.add(label46);
        label46.setBounds(30, 70, 120, 30);

        label47.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label47.setForeground(new java.awt.Color(102, 102, 102));
        label47.setText("Medical Leave");
        roundcornerlayer17.add(label47);
        label47.setBounds(350, 70, 120, 30);

        medicall.setEditable(false);
        medicall.setBackground(new java.awt.Color(237, 238, 242));
        medicall.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        medicall.setRound(25);
        medicall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicallActionPerformed(evt);
            }
        });
        roundcornerlayer17.add(medicall);
        medicall.setBounds(350, 100, 310, 40);

        unpaidl.setEditable(false);
        unpaidl.setBackground(new java.awt.Color(237, 238, 242));
        unpaidl.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        unpaidl.setRound(25);
        unpaidl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unpaidlActionPerformed(evt);
            }
        });
        roundcornerlayer17.add(unpaidl);
        unpaidl.setBounds(30, 220, 300, 40);

        label48.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label48.setForeground(new java.awt.Color(102, 102, 102));
        label48.setText("Unpaid Leave");
        roundcornerlayer17.add(label48);
        label48.setBounds(30, 190, 120, 30);

        roundcornerlayer18.setBackground(new java.awt.Color(255, 255, 255));
        roundcornerlayer18.setRoundBottomLeft(50);
        roundcornerlayer18.setRoundBottomRight(50);
        roundcornerlayer18.setRoundTopLeft(50);
        roundcornerlayer18.setRoundTopRight(50);

        jLabel27.setFont(new java.awt.Font("Helvetica", 1, 30)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 102, 102));
        jLabel27.setText("Emergency Contact");
        roundcornerlayer18.add(jLabel27);
        jLabel27.setBounds(30, 20, 300, 40);

        roundtext59.setEditable(false);
        roundtext59.setBackground(new java.awt.Color(237, 238, 242));
        roundtext59.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        roundtext59.setRound(25);
        roundtext59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundtext59ActionPerformed(evt);
            }
        });
        roundcornerlayer18.add(roundtext59);
        roundtext59.setBounds(30, 100, 380, 40);

        label49.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label49.setForeground(new java.awt.Color(102, 102, 102));
        label49.setText("Name");
        roundcornerlayer18.add(label49);
        label49.setBounds(30, 70, 120, 30);

        label50.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label50.setForeground(new java.awt.Color(102, 102, 102));
        label50.setText("Name");
        roundcornerlayer18.add(label50);
        label50.setBounds(30, 150, 120, 30);

        roundtext60.setEditable(false);
        roundtext60.setBackground(new java.awt.Color(237, 238, 242));
        roundtext60.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        roundtext60.setRound(25);
        roundtext60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundtext60ActionPerformed(evt);
            }
        });
        roundcornerlayer18.add(roundtext60);
        roundtext60.setBounds(30, 180, 380, 40);

        roundtext61.setEditable(false);
        roundtext61.setBackground(new java.awt.Color(237, 238, 242));
        roundtext61.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        roundtext61.setRound(25);
        roundtext61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundtext61ActionPerformed(evt);
            }
        });
        roundcornerlayer18.add(roundtext61);
        roundtext61.setBounds(30, 260, 380, 40);

        label51.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label51.setForeground(new java.awt.Color(102, 102, 102));
        label51.setText("Name");
        roundcornerlayer18.add(label51);
        label51.setBounds(30, 230, 120, 30);

        roundcornerlayer17.add(roundcornerlayer18);
        roundcornerlayer18.setBounds(0, 0, 0, 0);

        roundcornerlayer19.setBackground(new java.awt.Color(255, 255, 255));
        roundcornerlayer19.setRoundBottomLeft(50);
        roundcornerlayer19.setRoundBottomRight(50);
        roundcornerlayer19.setRoundTopLeft(50);
        roundcornerlayer19.setRoundTopRight(50);

        jLabel28.setFont(new java.awt.Font("Helvetica", 1, 30)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setText("Emergency Contact");
        roundcornerlayer19.add(jLabel28);
        jLabel28.setBounds(30, 20, 300, 40);

        roundtext62.setEditable(false);
        roundtext62.setBackground(new java.awt.Color(237, 238, 242));
        roundtext62.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        roundtext62.setRound(25);
        roundtext62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundtext62ActionPerformed(evt);
            }
        });
        roundcornerlayer19.add(roundtext62);
        roundtext62.setBounds(30, 100, 380, 40);

        label52.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label52.setForeground(new java.awt.Color(102, 102, 102));
        label52.setText("Name");
        roundcornerlayer19.add(label52);
        label52.setBounds(30, 70, 120, 30);

        label53.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label53.setForeground(new java.awt.Color(102, 102, 102));
        label53.setText("Name");
        roundcornerlayer19.add(label53);
        label53.setBounds(30, 150, 120, 30);

        roundtext63.setEditable(false);
        roundtext63.setBackground(new java.awt.Color(237, 238, 242));
        roundtext63.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        roundtext63.setRound(25);
        roundtext63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundtext63ActionPerformed(evt);
            }
        });
        roundcornerlayer19.add(roundtext63);
        roundtext63.setBounds(30, 180, 380, 40);

        roundtext64.setEditable(false);
        roundtext64.setBackground(new java.awt.Color(237, 238, 242));
        roundtext64.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        roundtext64.setRound(25);
        roundtext64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundtext64ActionPerformed(evt);
            }
        });
        roundcornerlayer19.add(roundtext64);
        roundtext64.setBounds(30, 260, 380, 40);

        label54.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label54.setForeground(new java.awt.Color(102, 102, 102));
        label54.setText("Name");
        roundcornerlayer19.add(label54);
        label54.setBounds(30, 230, 120, 30);

        roundcornerlayer20.setBackground(new java.awt.Color(255, 255, 255));
        roundcornerlayer20.setRoundBottomLeft(50);
        roundcornerlayer20.setRoundBottomRight(50);
        roundcornerlayer20.setRoundTopLeft(50);
        roundcornerlayer20.setRoundTopRight(50);

        jLabel29.setFont(new java.awt.Font("Helvetica", 1, 30)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("Emergency Contact");
        roundcornerlayer20.add(jLabel29);
        jLabel29.setBounds(30, 20, 300, 40);

        roundtext65.setEditable(false);
        roundtext65.setBackground(new java.awt.Color(237, 238, 242));
        roundtext65.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        roundtext65.setRound(25);
        roundtext65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundtext65ActionPerformed(evt);
            }
        });
        roundcornerlayer20.add(roundtext65);
        roundtext65.setBounds(30, 100, 380, 40);

        label55.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label55.setForeground(new java.awt.Color(102, 102, 102));
        label55.setText("Name");
        roundcornerlayer20.add(label55);
        label55.setBounds(30, 70, 120, 30);

        label56.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label56.setForeground(new java.awt.Color(102, 102, 102));
        label56.setText("Name");
        roundcornerlayer20.add(label56);
        label56.setBounds(30, 150, 120, 30);

        roundtext66.setEditable(false);
        roundtext66.setBackground(new java.awt.Color(237, 238, 242));
        roundtext66.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        roundtext66.setRound(25);
        roundtext66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundtext66ActionPerformed(evt);
            }
        });
        roundcornerlayer20.add(roundtext66);
        roundtext66.setBounds(30, 180, 380, 40);

        roundtext67.setEditable(false);
        roundtext67.setBackground(new java.awt.Color(237, 238, 242));
        roundtext67.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        roundtext67.setRound(25);
        roundtext67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundtext67ActionPerformed(evt);
            }
        });
        roundcornerlayer20.add(roundtext67);
        roundtext67.setBounds(30, 260, 380, 40);

        label57.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label57.setForeground(new java.awt.Color(102, 102, 102));
        label57.setText("Name");
        roundcornerlayer20.add(label57);
        label57.setBounds(30, 230, 120, 30);

        roundcornerlayer19.add(roundcornerlayer20);
        roundcornerlayer20.setBounds(0, 0, 0, 0);

        roundcornerlayer17.add(roundcornerlayer19);
        roundcornerlayer19.setBounds(0, 0, 0, 0);

        label58.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        label58.setForeground(new java.awt.Color(102, 102, 102));
        label58.setText("Maternity Leave");
        roundcornerlayer17.add(label58);
        label58.setBounds(350, 190, 170, 30);

        maternityl.setEditable(false);
        maternityl.setBackground(new java.awt.Color(237, 238, 242));
        maternityl.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        maternityl.setRound(25);
        maternityl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maternitylActionPerformed(evt);
            }
        });
        roundcornerlayer17.add(maternityl);
        maternityl.setBounds(350, 220, 310, 40);

        jLayeredPane1.setLayer(roundcornerlayer1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(roundcornerlayer2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(roundcornerlayer3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(back, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(roundcornerlayer4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(roundcornerlayer5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(roundcornerlayer9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(roundcornerlayer13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(roundcornerlayer17, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(roundcornerlayer17, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(roundcornerlayer13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(roundcornerlayer1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(roundcornerlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(roundcornerlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roundcornerlayer4, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(roundcornerlayer5, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(roundcornerlayer9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(19, 19, 19))))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(16, 16, 16)
                .addComponent(roundcornerlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(roundcornerlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(roundcornerlayer4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(roundcornerlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundcornerlayer5, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                    .addComponent(roundcornerlayer9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundcornerlayer13, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundcornerlayer17, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jLayeredPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LastnmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LastnmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LastnmActionPerformed

    private void raceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_raceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_raceActionPerformed

    private void fnmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnmActionPerformed

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneActionPerformed

    private void genderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderActionPerformed

    private void dobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dobActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dobActionPerformed

    private void maritalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maritalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maritalActionPerformed

    private void addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void usernamefieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernamefieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernamefieldActionPerformed

    private void departActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_departActionPerformed

    private void enameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enameActionPerformed

    private void roleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roleActionPerformed

    private void positionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_positionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_positionActionPerformed

    private void workexpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workexpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_workexpActionPerformed

    private void enricActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enricActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enricActionPerformed

    private void ephoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ephoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ephoneActionPerformed

    private void banknmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_banknmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_banknmActionPerformed

    private void banknoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_banknoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_banknoActionPerformed

    private void epfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_epfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_epfActionPerformed

    private void erelationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_erelationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_erelationActionPerformed

    private void tinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tinActionPerformed

    private void msalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_msalaryActionPerformed

    private void hsalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hsalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hsalaryActionPerformed

    private void hpositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hpositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hpositionActionPerformed

    private void annuallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annuallActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_annuallActionPerformed

    private void medicallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicallActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medicallActionPerformed

    private void unpaidlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unpaidlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unpaidlActionPerformed

    private void roundtext59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundtext59ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roundtext59ActionPerformed

    private void roundtext60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundtext60ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roundtext60ActionPerformed

    private void roundtext61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundtext61ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roundtext61ActionPerformed

    private void roundtext62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundtext62ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roundtext62ActionPerformed

    private void roundtext63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundtext63ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roundtext63ActionPerformed

    private void roundtext64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundtext64ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roundtext64ActionPerformed

    private void roundtext65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundtext65ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roundtext65ActionPerformed

    private void roundtext66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundtext66ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roundtext66ActionPerformed

    private void roundtext67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundtext67ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roundtext67ActionPerformed

    private void maternitylActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maternitylActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maternitylActionPerformed

    private void changepswActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changepswActionPerformed
        Changepssw cpssw = new Changepssw();
    
        DefaultOption option=new DefaultOption(){
           //@Override
           public boolean closeWhenClickedOutside(){
               return true;
           }
        };
        String[] actions = new String[]{"Cancel", "Update"};

            GlassPanePopup.showPopup(new SimplePopupBorder(cpssw, "Change Password", actions, (pc, i) -> {
            try{
                if (i == 1) {
                    // update
                    
                        // check empty
                        boolean valid = cpssw.emptypv();
                        if (!valid) {
                            Notifications.getInstance().show(Notifications.Type.ERROR, "Field cannot be empty");
                            return;
                        }

                        boolean isUpdated = cpssw.changep(getusername());

                        if (isUpdated) {
                            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Password updated");
                            pc.closePopup();
                            SwingUtilities.invokeLater(() -> {
                                reloadProfileData();
                                
                                    }); 
                        System.out.println("Closing popup...update");
                        }else {
                             Notifications.getInstance().show(Notifications.Type.ERROR, "Password not updated. Old password is incorrect or new passwords do not match.");
                        }
                } else { // "Cancel" button
                        pc.closePopup();
                    }
            } catch (Exception ex) {
                ex.printStackTrace();
                    
                }
            }), option);
    }//GEN-LAST:event_changepswActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      logintest log = new logintest();
            log.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        DepartmentMng depart = new DepartmentMng();
        depart.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    
private void reloadProfileData() {
    // Assume Changepssw is a class for changing the password
    Changepssw cpssw = new Changepssw();

    // Retrieve the latest user details
    String[] adminDetails = logintest.getuserDetails();
    
    // Update the profile data (this is assumed to be a method that updates the UI components)
    setuserprofile(adminDetails[3], adminDetails[4]);
    
    this.revalidate();
    this.repaint();
}


      
      
      
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Profiledepart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profiledepart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profiledepart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profiledepart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Profiledepart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private designcomponent.roundtext Lastnm;
    private javax.swing.JLabel Role;
    private designcomponent.roundtext address;
    private designcomponent.roundtext annuall;
    private javax.swing.JLabel back;
    private designcomponent.roundtext banknm;
    private designcomponent.roundtext bankno;
    private javax.swing.JButton changepsw;
    private javax.swing.JLabel datejoin;
    private designcomponent.roundtext depart;
    private javax.swing.JLabel department;
    private designcomponent.roundtext dob;
    private designcomponent.roundtext email;
    private designcomponent.roundtext ename;
    private designcomponent.roundtext enric;
    private designcomponent.roundtext epf;
    private designcomponent.roundtext ephone;
    private designcomponent.roundtext erelation;
    private designcomponent.roundtext fnm;
    private designcomponent.roundtext gender;
    private designcomponent.roundtext hposition;
    private designcomponent.roundtext hsalary;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label17;
    private javax.swing.JLabel label18;
    private javax.swing.JLabel label19;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label29;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label30;
    private javax.swing.JLabel label31;
    private javax.swing.JLabel label32;
    private javax.swing.JLabel label34;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label46;
    private javax.swing.JLabel label47;
    private javax.swing.JLabel label48;
    private javax.swing.JLabel label49;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label50;
    private javax.swing.JLabel label51;
    private javax.swing.JLabel label52;
    private javax.swing.JLabel label53;
    private javax.swing.JLabel label54;
    private javax.swing.JLabel label55;
    private javax.swing.JLabel label56;
    private javax.swing.JLabel label57;
    private javax.swing.JLabel label58;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel lnm;
    private designcomponent.roundtext marital;
    private designcomponent.roundtext maternityl;
    private designcomponent.roundtext medicall;
    private designcomponent.roundtext msalary;
    private javax.swing.JLabel name;
    private designcomponent.roundtext phone;
    private designcomponent.roundtext position;
    private javax.swing.JLabel profilepic;
    private designcomponent.roundtext race;
    private designcomponent.roundtext role;
    private designcomponent.roundcornerlayer roundcornerlayer1;
    private designcomponent.roundcornerlayer roundcornerlayer13;
    private designcomponent.roundcornerlayer roundcornerlayer17;
    private designcomponent.roundcornerlayer roundcornerlayer18;
    private designcomponent.roundcornerlayer roundcornerlayer19;
    private designcomponent.roundcornerlayer roundcornerlayer2;
    private designcomponent.roundcornerlayer roundcornerlayer20;
    private designcomponent.roundcornerlayer roundcornerlayer3;
    private designcomponent.roundcornerlayer roundcornerlayer4;
    private designcomponent.roundcornerlayer roundcornerlayer5;
    private designcomponent.roundcornerlayer roundcornerlayer9;
    private designcomponent.roundtext roundtext59;
    private designcomponent.roundtext roundtext60;
    private designcomponent.roundtext roundtext61;
    private designcomponent.roundtext roundtext62;
    private designcomponent.roundtext roundtext63;
    private designcomponent.roundtext roundtext64;
    private designcomponent.roundtext roundtext65;
    private designcomponent.roundtext roundtext66;
    private designcomponent.roundtext roundtext67;
    private designcomponent.roundtext tin;
    private designcomponent.roundtext unpaidl;
    private designcomponent.roundtext usernamefield;
    private designcomponent.roundtext workexp;
    // End of variables declaration//GEN-END:variables
}
