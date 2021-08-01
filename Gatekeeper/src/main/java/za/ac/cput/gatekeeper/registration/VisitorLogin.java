/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.gatekeeper.registration;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Charles Lemmert Student No: 220498385
 */
public class VisitorLogin extends DbConnection implements ActionListener {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet results = null;

    private JLabel label;
    private JPanel images;
    //--------------------------------------------------------------------------J Labels and Textfields
    //Username
    private JLabel lblUsername;
    private JTextField txtUsername;

    //Password
    private JLabel lblPassword;
    private JPasswordField pwdPassword;

    //Buttons
    private JButton btnLogin;
    private JButton btnReturn;

    //--------------------------------------------------------------------------Login Constructor
    public VisitorLogin() {

        //---------------------------------------------------Username label and textfield
        lblUsername = new JLabel("Username");
        txtUsername = new JTextField(16);

        //---------------------------------------------------Password label and textfield
        lblPassword = new JLabel("Password");
        pwdPassword = new JPasswordField(16);

        //---------------------------------------------------Login button & Registration button
        btnLogin = new JButton("Checkin");
        btnReturn = new JButton("Return");

    }

    //--------------------------------------------------------------------------GUI layout for Login and Registration test
    public void StartGUI() {
        //starting connection
        conn = DbConnection.ConnectDb();

        //---------------------------------------------------Creating window and setting window Size
        JFrame window = new JFrame();
        window.setSize(876, 497);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        //---------------------------------------------------Creating panel to place textfields and labels in
        JPanel border = new JPanel();
        window.add(border);
        border.setLayout(null);
        //---------------------------------------------------Login panel
        JPanel outline = new JPanel();
        border.add(outline);
        outline.setBounds(37, 45, 294, 371);
        outline.setLayout(null);

        images = new JPanel();
        border.add(images);
        label = new JLabel();
        border.add(label);

        scalingImg();
        //---------------------------------------------------JLabel
        JLabel lblUser = new JLabel("GATEKEEPER");
        lblUser.setFont(new Font("SourceSansPro", Font.BOLD | Font.ITALIC, 25));
        lblUser.setForeground(Color.BLACK);
        lblUser.setBounds(59, 17, 210, 60);
        outline.add(lblUser);
        //---------------------------------------------------positioning Username label and textfield
        lblUsername.setBounds(47, 90, 100, 40);
        outline.add(lblUsername);
        txtUsername.setBounds(47, 130, 200, 30);
        outline.add(txtUsername);

        //---------------------------------------------------positioning Password label and textfield
        lblPassword.setBounds(47, 160, 100, 40);
        outline.add(lblPassword);
        pwdPassword.setBounds(47, 200, 200, 30);
        outline.add(pwdPassword);

        //---------------------------------------------------positioning login button and adding action listener
        btnLogin.setBounds(82, 245, 130, 33);
        outline.add(btnLogin);
        btnLogin.addActionListener(this);
        //---------------------------------------------------positioning  Submit button 
        btnReturn.setBounds(82, 295, 130, 33);
        outline.add(btnReturn);

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        btnLogin.addActionListener(this);
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

        //---------------------------------------------------Design JPanels
        //Panel Colour
        border.setBackground(new Color(0x005ba3));
        outline.setBackground(new Color(0x03a9f4));
        images.setBackground(new Color(0x005ba3));

        //---------------------------------------------------Design JLabel
        lblUsername.setFont(new Font("SourceSansPro", Font.BOLD | Font.ITALIC, 18));
        lblUsername.setForeground(Color.BLACK);
        lblPassword.setFont(new Font("SourceSansPro", Font.BOLD | Font.ITALIC, 18));
        lblPassword.setForeground(Color.BLACK);
        txtUsername.setBorder(BorderFactory.createLineBorder(new Color(0x005ba3),3));
        pwdPassword.setBorder(BorderFactory.createLineBorder(new Color(0x005ba3),3));
        //---------------------------------------------------Design JButton
        btnLogin.setBorder(BorderFactory.createLineBorder(new Color(0x005ba3), 2));
        btnReturn.setBorder(BorderFactory.createLineBorder(new Color(0x005ba3), 2));
        //btnLogin.setBorder(new LineBorder(Color.BLACK));
        //btnLogin.setContentAreaFilled(false);
        //btnLogin.setFocusPainted(false);
        //btnLogin.setBorder(new RoundedBorder(10));
    }

    public void scalingImg() {

        //images.setLayout(null);
        images.setBounds(400, 0, 462, 462);

        ImageIcon icon = new ImageIcon("images\\bg1.png ");
        label.setLocation(400, 2);
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(462, 462, Image.SCALE_SMOOTH);
        ImageIcon ScaledIcon = new ImageIcon(imgScale);
        label.setIcon(ScaledIcon);
        images.add(label);

    }

    //--------------------------------------------------------------------------call data from visitor database and verify if user is registered or not
    public void userVerification() {
        //BUG DOUBLE VERIFICATION BUSY FIXING IT 
        String query = "Select * FROM visitors WHERE id LIKE ? AND Mobile LIKE ?; ";

        try {

            stmt = conn.prepareStatement(query);
            stmt.setString(1, txtUsername.getText());
            stmt.setString(2, pwdPassword.getText());

            results = stmt.executeQuery();

            if (results.next()) {

                JOptionPane.showMessageDialog(null, "LOGIN SUCCESSFULL");

            } else {

                JOptionPane.showMessageDialog(null, "USER NOT FOUND");

            }

        } catch (Exception e) {

            System.out.println("PROCESS FAILED!!!");
        }

    }

    //--------------------------------------------------------------------------timestamp function or method save time stamp to the database 
    /*public void checkInTime(){
        
                //check in time method code will be added here 
    
    }*/
    //--------------------------------------------------------------------------Action Listener onclick functionality implemented here:User Verification
    @Override
    public void actionPerformed(ActionEvent e) {
        userVerification();

    }

    //--------------------------------------------------------------------------main function calls starter method to run program
    public void starter() {
        new VisitorLogin().StartGUI();

    }
    /*class RoundedBorder implements Border {
        int radius;
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }
        public boolean isBorderOpaque() {
            return true;
        }
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x,y,width-1,height-1,radius,radius);
        }
    }*/
    
}
