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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Charles Lemmert Student No: 220498385
 */
public class VisitorLogin implements ActionListener {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet results = null;

    private JLabel label;
    private JPanel images;
    private JFrame window;
    //--------------------------------------------------------------------------J Labels and Textfields
    //Username
    private JLabel lblUsername;
    private JTextField txtUsername;

    //Password
    private JLabel lblPassword;
    private JPasswordField pwdPassword;

    //Hyperlink
    private JLabel lblLink;
    private JLabel lblQuestion;
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

        //---------------------------------------------------Hyperlink label
        lblQuestion = new JLabel("Do you have an account? ");
        lblLink = new JLabel("Register");
        //---------------------------------------------------Login button & Registration button
        btnLogin = new JButton("CHECKIN");
        btnReturn = new JButton("RETURN");

    }

    //--------------------------------------------------------------------------GUI layout for Login and Registration test
    public void StartGUI() {
        //starting connection
        conn = DbConnection.ConnectDb();

        //---------------------------------------------------Creating window and setting window Size
        window = new JFrame();
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
        outline.add(lblQuestion);
        outline.add(lblLink);

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

        //---------------------------------------------------positioning Register hyperlink
        lblQuestion.setBounds(50, 325, 148, 33);
        lblLink.setBounds(194, 325, 100, 33);

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
        //---------------------------------------------------Creating HyperLink
        lblLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                window.setVisible(false);
                VisitorRegistration rg = new VisitorRegistration();
                window.setVisible(false);
                rg.setVisible(true);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblLink.setForeground(new Color(0x005ba3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblLink.setForeground(Color.WHITE);
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
        border.setBackground(new Color(0x005ba3));
        outline.setBackground(new Color(0x03a9f4));
        images.setBackground(new Color(0x005ba3));
        outline.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        //---------------------------------------------------Design JLabel
        lblUsername.setFont(new Font("SourceSansPro", Font.BOLD | Font.ITALIC, 18));
        lblUsername.setForeground(Color.BLACK);
        lblPassword.setFont(new Font("SourceSansPro", Font.BOLD | Font.ITALIC, 18));
        lblPassword.setForeground(Color.BLACK);
        txtUsername.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        pwdPassword.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        txtUsername.setBackground(new Color(0x424242));
        pwdPassword.setBackground(new Color(0x424242));
        txtUsername.setForeground(Color.WHITE);
        pwdPassword.setForeground(Color.WHITE);
        txtUsername.setCaretColor(Color.WHITE);
        txtUsername.setCaretColor(Color.WHITE);
        pwdPassword.setCaretColor(Color.WHITE);

        //---------------------------------------------------Design JButton
        btnLogin.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        btnReturn.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        btnLogin.setBackground(new Color(0x424242));
        btnLogin.setForeground(Color.WHITE);
        btnReturn.setBackground(new Color(0x424242));
        btnReturn.setForeground(Color.WHITE);

        //---------------------------------------------------Hyperlink design
        lblLink.setForeground(Color.WHITE);

        //changing cursor icon to hand cursor 
        lblLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReturn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //Hover colour change when the cursor hovers over the Login Jbutton
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(new Color(0x005ba3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(new Color(0x424242));
            }
        });
        btnReturn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnReturn.setBackground(new Color(0x005ba3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnReturn.setBackground(new Color(0x424242));
            }
        });

    }

    public void scalingImg() {

       
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
        
        String query = "Select * FROM visitors WHERE id LIKE ? AND Mobile LIKE ?; ";

        try {

            stmt = conn.prepareStatement(query);
            stmt.setString(1, txtUsername.getText());
            stmt.setString(2, pwdPassword.getText());

            results = stmt.executeQuery();

            if (results.next()) {
                window.setVisible(false);
                VisitorOption rg = new VisitorOption();
                window.setVisible(false);
                rg.Start();
                
                //checkInTime();

            } else {
                UIManager UI = new UIManager();
                UI.put("OptionPane.background",new Color(0x03a9f4));
                UI.put("Panel.background", new Color(0x03a9f4));
                

               JOptionPane.showMessageDialog(null, "Incorrect Username/Password\n      USER NOT FOUND");

            }

        } catch (Exception e) {

            System.out.println("PROCESS FAILED!!!");
        }

    }

    //--------------------------------------------------------------------------timestamp function or method save time stamp to the database 
    /*public void checkInTime(){
        
    //------------------------------------------------check in time method code will be added here
        Date recentDate = new Date();
       
        SimpleDateFormat dateStamp = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeStamp = new SimpleDateFormat("h:mm:ss a");
        String dateuser = dateStamp.format(recentDate);
        String timeuser = timeStamp.format(recentDate);
        
        // save this to the database for the specific user that is logging in
       
    }*/
    //--------------------------------------------------------------------------Action Listener onclick functionality implemented here:User Verification
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            userVerification();

        }
    }

    //--------------------------------------------------------------------------main function calls starter method to run program
    public void starter() {
        new VisitorLogin().StartGUI();

    }
    
}
