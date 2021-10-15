/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.gatekeeper.registration;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import za.ac.cput.gatekeeper.registration.Database.InventoryView;

/**
 *
 * @author Charles
 */
public class EmployeeLogin implements ActionListener {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet results = null;

    
    private JFrame window;
    private JPanel outline;
    private JPanel outlineTwo;
    private JPanel border;
    //--------------------------------------------------------------------------J Labels and Textfields
    //Username
    private JLabel lblUsername;
    private JTextField txtUsername;

    //Password
    private JLabel lblPassword;
    private JPasswordField pwdPassword;
    private JLabel lblMsg;
    private JLabel lblMsgTwo;
    //image
    private JLabel lblIcon;
    private JLabel lblIconTwo;
    private JLabel lblIconThree;
    
    //Buttons
    private JButton btnLogin;
    private JButton btnReturn;

    //--------------------------------------------------------------------------Login Constructor
    public EmployeeLogin() {

        outline = new JPanel();
        outlineTwo = new JPanel();
        lblIcon = new JLabel();
        lblIconTwo = new JLabel();
        lblIconThree = new JLabel();
        border = new JPanel();
        //---------------------------------------------------Username label and textfield
        lblUsername = new JLabel("Username");
        txtUsername = new JTextField(16);

        //---------------------------------------------------Password label and textfield
        lblPassword = new JLabel("Password");
        pwdPassword = new JPasswordField(16);

        lblMsg = new JLabel("Welcome");
        lblMsgTwo = new JLabel("To the administrator Login");
        //---------------------------------------------------Login button & Registration button
        btnLogin = new JButton("LOGIN");
        btnReturn = new JButton("BACK");

    }

    //--------------------------------------------------------------------------GUI layout for Login and Registration test
    public void StartGUI() {
        //starting connection
        conn = DbConnection.ConnectEmpDb();//should be replaced with the connection to the employee database

        //---------------------------------------------------Creating window and setting window Size
        window = new JFrame();
        window.setSize(876, 497);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        //---------------------------------------------------Creating panel to place textfields and labels in
        
        window.add(border);
        border.setLayout(null);
        border.add(lblIconTwo);
        scalingImgTwo();
        //---------------------------------------------------Login panel
        
        lblIconTwo.add(outline);
        outline.setBounds(165, 65, 536, 334);
        outline.setLayout(null);
        
        outline.add(lblIcon);
        iconImg();
        
        outlineTwo.setLayout(null);
        outlineTwo.setBounds(5,6,234,321);
        outline.add(outlineTwo);
        outlineTwo.add(lblIconThree);
        iconImgTwo();

        lblMsg.setBounds(65,220,150,33);
        lblMsg.setFont(new Font("SourceSansPro", Font.BOLD, 25));
        lblMsg.setForeground(Color.BLACK);
        lblMsgTwo.setBounds(20,240,320,33);
        lblMsgTwo.setFont(new Font("SourceSansPro", Font.BOLD, 15));
        lblMsgTwo.setForeground(Color.BLACK);
        outlineTwo.add(lblMsg);
        outlineTwo.add(lblMsgTwo);
        //---------------------------------------------------JLabel
        JLabel lblUser = new JLabel("ADMINISTRATOR");
        lblUser.setFont(new Font("SourceSansPro", Font.BOLD, 25));
        lblUser.setForeground(Color.BLACK);
        lblUser.setBounds(280, 2, 210, 60);
        outline.add(lblUser);
        //---------------------------------------------------positioning Username label and textfield
        lblUsername.setBounds(280, 128, 150, 40);
        outline.add(lblUsername);
        txtUsername.setBounds(280, 161, 200, 30);
        outline.add(txtUsername);

        //---------------------------------------------------positioning Password label and textfield
        lblPassword.setBounds(280, 184, 200, 40);
        outline.add(lblPassword);
        pwdPassword.setBounds(280, 216, 200, 30);
        outline.add(pwdPassword);

        //---------------------------------------------------positioning login button and adding action listener
        btnLogin.setBounds(330, 253, 100, 33);
        outline.add(btnLogin);
        btnLogin.addActionListener(this);
        //---------------------------------------------------positioning  Submit button 
        btnReturn.setBounds(330, 291, 100, 33);
        outline.add(btnReturn);

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //--------------------------------------------------returns the user to the main page
        btnReturn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                window.setVisible(false);
                Main rg = new Main();
                window.setVisible(false);
                rg.startProgram();

            }
        });

        //-------------------------------------------------------------------------------------------------------Design
        //---------------------------------------------------Design JFrame
        //form title bar 
        window.setTitle("Login");
        ImageIcon img = new ImageIcon("images\\anu.jpg");
        window.setIconImage(img.getImage());//changes the icon of the frame
        window.getRootPane().setDefaultButton(btnLogin);
        //---------------------------------------------------Design JPanels
        //Panel Colour
      
        outline.setBackground(Color.WHITE);
        outlineTwo.setBackground(new Color(0x03a9f4));
        
        outline.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        //---------------------------------------------------Design JLabel
        lblUsername.setFont(new Font("SourceSansPro", Font.BOLD, 15));
        lblUsername.setForeground(Color.BLACK);
        lblPassword.setFont(new Font("SourceSansPro", Font.BOLD, 15));
        lblPassword.setForeground(Color.BLACK);
        txtUsername.setBorder(BorderFactory.createLineBorder(new Color(0x03a9f4), 3));
        pwdPassword.setBorder(BorderFactory.createLineBorder(new Color(0x03a9f4), 3));
        txtUsername.setBackground(new Color(0x424242));
        pwdPassword.setBackground(new Color(0x424242));
        txtUsername.setForeground(Color.WHITE);
        pwdPassword.setForeground(Color.WHITE);
        txtUsername.setCaretColor(Color.WHITE);
        txtUsername.setCaretColor(Color.WHITE);
        pwdPassword.setCaretColor(Color.WHITE);

        //---------------------------------------------------Design JButton
        btnLogin.setBorder(BorderFactory.createLineBorder(new Color(0x03a9f4), 3));
        btnReturn.setBorder(BorderFactory.createLineBorder(new Color(0x03a9f4), 3));
        btnLogin.setBackground(new Color(0x424242));
        btnLogin.setForeground(Color.WHITE);
        btnReturn.setBackground(new Color(0x424242));
        btnReturn.setForeground(Color.WHITE);

        //Hover colour change when the cursor hovers over the Login Jbutton
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReturn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnLogin.setBackground(new Color(0x005ba3));
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                btnLogin.setBackground(new Color(0x424242));
            }
        });
        btnReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnReturn.setBackground(new Color(0x005ba3));
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                btnReturn.setBackground(new Color(0x424242));
            }
        });

    }
     public void scalingImgTwo() {

        ImageIcon userimg = new ImageIcon("images\\backgroundColour.png");
        lblIconTwo.setBounds(0, 0, 876, 497);
        Image img = userimg.getImage();
        
        Image imgScale = img.getScaledInstance(876, 497, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        lblIconTwo.setIcon(scaledIcon);
        border.add(lblIconTwo);

    }
    public void iconImg(){
       
        ImageIcon userimg = new ImageIcon("images\\icon.png");
        lblIcon.setBounds(320, 28, 130, 130);
        Image img = userimg.getImage();
        
        Image imgScale = img.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        lblIcon.setIcon(scaledIcon);
        outline.add(lblIcon);
        
        
    
    
    
    }
    public void iconImgTwo(){
       
        ImageIcon userimg = new ImageIcon("images\\adminIcon.png");
        lblIconThree.setBounds(50, 50, 151, 168);
        Image img = userimg.getImage();
        
        Image imgScale = img.getScaledInstance(151, 168, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        lblIconThree.setIcon(scaledIcon);
        outlineTwo.add(lblIconThree);

    
    
    }
    //--------------------------------------------------------------------------call data from visitor database and verify if user is registered or not
    public void userVerification() {
        //BUG DOUBLE VERIFICATION BUSY FIXING IT 
        String query = "Select * FROM employee WHERE id_no = ? AND password = ?; ";

        try {

            stmt = conn.prepareStatement(query);
            stmt.setString(1, txtUsername.getText());
            stmt.setString(2, pwdPassword.getText());

            results = stmt.executeQuery();

            if (results.next()) {
                  checkInTime();
                 //Open inventory system or dashboard here
                 window.setVisible(false);
                 InventoryView opn = new InventoryView();
                 window.dispose();
                 
            } else {

                txtUsername.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                pwdPassword.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                JOptionPane.showMessageDialog(null, "     USER NOT FOUND");
                txtUsername.setBorder(BorderFactory.createLineBorder(new Color(0x03a9f4), 3));
                pwdPassword.setBorder(BorderFactory.createLineBorder(new Color(0x03a9f4), 3));
                
                txtUsername.setText("");
                pwdPassword.setText("");

            }

        } catch (Exception e) {

            System.out.println("PROCESS FAILED!!!");
        }

    }

    //--------------------------------------------------------------------------timestamp function or method save time stamp to the database 
    public void checkInTime() {

        //------------------------------------------------check in time method code will be added here
        Date recentDate = new Date();

        SimpleDateFormat dateStamp = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeStamp = new SimpleDateFormat("h:mm:ss a");
        String dateuser = dateStamp.format(recentDate);
        String timeuser = timeStamp.format(recentDate);

        int ID = Integer.parseInt(txtUsername.getText());
       
        try {
            String querysql = "update employee set time_in='" + timeuser + "',date='" + dateuser + "' where id_no='" + ID + "' ";
            stmt = conn.prepareStatement(querysql);
            stmt.execute();
            System.out.println("It has worked!!!");
            
            conn.close();
           
        } catch (SQLException e) {

            System.out.println("Failed to update");

        }

    }

    //--------------------------------------------------------------------------Action Listener onclick functionality implemented here:User Verification
    @Override
    public void actionPerformed(ActionEvent e) {
        userVerification();
        
        
       
    }

    //--------------------------------------------------------------------------main function calls starter method to run program
    public void starter() {
        new EmployeeLogin().StartGUI();

    }

}
