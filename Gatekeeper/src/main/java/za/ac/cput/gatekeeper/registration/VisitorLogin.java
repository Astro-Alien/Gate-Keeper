/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.gatekeeper.registration;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.sql.*;
import javax.imageio.ImageIO;

/**
 *
 * @author Charles Lemmert Student No: 220498385
 */
public class VisitorLogin extends DbConnection implements ActionListener {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet results = null;

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
        lblUsername = new JLabel("Username: ");
        txtUsername = new JTextField(16);

        //---------------------------------------------------Password label and textfield
        lblPassword = new JLabel("Password: ");
        pwdPassword = new JPasswordField(16);

        //---------------------------------------------------Login button & Registration button
        btnLogin = new JButton("Login");
        btnReturn = new JButton("Return");

    }

    //--------------------------------------------------------------------------GUI layout for Login and Registration test
    public void StartGUI() {
        //starting connection
        conn = DbConnection.ConnectDb();

        //---------------------------------------------------Creating window and setting window Size
        JFrame window = new JFrame("Login");
        window.setSize(600, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        //---------------------------------------------------Creating panel to place textfields and labels in
        JPanel border = new JPanel();
        window.add(border);
        border.setLayout(null);

        //---------------------------------------------------creating Default image panel
        JPanel images = new JPanel();
        
        images.setBounds(155, 18, 277, 196);
        try {
            BufferedImage myPicture = ImageIO.read(new File("images\\logo.jpg"));
            Image userImage = myPicture.getScaledInstance(200, 150, Image.SCALE_DEFAULT);
            JLabel picLabel = new JLabel(new ImageIcon(userImage));
            images.add(picLabel);
            
        } catch (IOException e) {

            System.out.println("Failed to retrieve image");
        }
        border.add(images);
        
        //---------------------------------------------------JLabel
        JLabel lblUser = new JLabel("Gatekeeper Visitor Login");
        lblUser.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        lblUser.setBounds(150, 200, 500, 50);
        border.add(lblUser);
        //---------------------------------------------------positioning Username label and textfield
        lblUsername.setBounds(80, 267, 80, 25);
        border.add(lblUsername);
        txtUsername.setBounds(180, 270, 229, 25);
        border.add(txtUsername);

        //---------------------------------------------------positioning Password label and textfield
        lblPassword.setBounds(80, 317, 80, 25);
        border.add(lblPassword);
        pwdPassword.setBounds(180, 320, 229, 25);
        border.add(pwdPassword);

        //---------------------------------------------------positioning login button and adding action listener
        btnLogin.setBounds(255, 370, 80, 25);
        border.add(btnLogin);
        btnLogin.addActionListener(this);
        //---------------------------------------------------positioning  Submit button 
        btnReturn.setBounds(250, 400, 90, 25);
        border.add(btnReturn);

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
    }

    //--------------------------------------------------------------------------call data from visitor database and verify if user is registered or not
    public void userVerification() {

        String query = "Select * from visitors WHERE id LIKE ? AND Mobile LIKE ?; ";

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
}
