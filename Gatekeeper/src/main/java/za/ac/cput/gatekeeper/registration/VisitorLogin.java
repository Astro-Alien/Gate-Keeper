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
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author Charles Lemmert Student No: 220498385
 */
public class VisitorLogin implements ActionListener {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet results = null;

    private JLabel label;
    private JLabel lblIcon;
    private JPanel images;
    private JFrame window;
    private JPanel outline;

    private String temp;
    //--------------------------------------------------------------------------J Labels and Textfields
    //Firstname
    private JLabel lblUsername;
    private JTextField txtUsername;
    //Lastname
    private JLabel lblLastname;
    private JTextField txtLastname;
    //Buttons
    private JButton btnLogin;
    private JButton btnReturn;

    //Secondary panel
    private JPanel imgPanel;
    private JLabel lblName;
    private JLabel lblSurname;
    private JLabel lblWelcome;
    private JLabel lblInstruction;

    //Secondary panel Buttons
    private JButton btnMeeting;
    private JButton btnInterview;
    private JButton btnVisitor;
    private JButton btnDelivery;
    private JButton btnCheckIn;

    //time and date
    private String dateuser;
    private String timeuser;

    private String userN;

    //--------------------------------------------------------------------------Login Constructor
    public VisitorLogin() {

        //---------------------------------------------------Username label and textfield
        lblUsername = new JLabel("Enter Your Name");
        txtUsername = new JTextField(16);

        lblLastname = new JLabel("Enter Your Surname");
        txtLastname = new JTextField(16);

        imgPanel = new JPanel();

        //---------------------------------------------------Login button & Registration button
        btnLogin = new JButton("SEARCH");
        btnReturn = new JButton("RETURN");

        //---------------------------------------------------option welcome message
        lblWelcome = new JLabel("WELCOME");
        lblName = new JLabel();
        lblSurname = new JLabel();
        lblInstruction = new JLabel("Please select the reason for your visit today.");

        //---------------------------------------------------secondary panel buttons
        btnMeeting = new JButton("Meeting");
        btnInterview = new JButton("Interview");
        btnVisitor = new JButton("Visiting");
        btnDelivery = new JButton("Delivery");
        btnCheckIn = new JButton("CHECKIN");
        lblIcon = new JLabel();

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
        outline = new JPanel();
        border.add(outline);
        outline.setBounds(37, 22, 294, 420);
        outline.setLayout(null);

        images = new JPanel();
        border.add(images);
        label = new JLabel();
        border.add(label);
        outline.add(lblIcon);
        scalingImg();
        iconImg();
        //---------------------------------------------------Secondary panel
        border.add(imgPanel);

        //layout and size
        imgPanel.setLayout(null);
        imgPanel.setBounds(353, 22, 480, 420);
        imgPanel.setBackground(new Color(0x03a9f4));
        imgPanel.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));

        imgPanel.setVisible(false);
        //---------------------------------------------------JLabel
        JLabel lblUser = new JLabel("GATEKEEPER");
        lblUser.setFont(new Font("SourceSansPro", Font.BOLD | Font.ITALIC, 25));
        lblUser.setForeground(Color.BLACK);
        lblUser.setBounds(59, 5, 210, 60);
        outline.add(lblUser);

        //---------------------------------------------------positioning Username label and textfield
        lblUsername.setBounds(47, 180, 150, 40);
        outline.add(lblUsername);
        txtUsername.setBounds(47, 215, 200, 30);
        outline.add(txtUsername);

        lblLastname.setBounds(47, 238, 200, 40);
        outline.add(lblLastname);
        txtLastname.setBounds(47, 273, 200, 30);
        outline.add(txtLastname);
        //---------------------------------------------------positioning login button and adding action listener
        btnLogin.setBounds(82, 320, 130, 33);
        outline.add(btnLogin);
        btnLogin.addActionListener(this);

        //---------------------------------------------------positioning  Submit button 
        btnReturn.setBounds(82, 365, 130, 33);
        outline.add(btnReturn);

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //--------------------------------------------------returns the user to the main page
        btnReturn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                window.setVisible(false);
                Main rg = new Main();
                window.setVisible(false);
                rg.startProgram();

            }
        });
        //------------------------------------------------------------------------------Design
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
        lblUsername.setFont(new Font("SourceSansPro", Font.BOLD | Font.ITALIC, 16));
        lblUsername.setForeground(Color.BLACK);
        txtUsername.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        txtUsername.setBackground(new Color(0x424242));
        txtUsername.setForeground(Color.WHITE);
        txtUsername.setCaretColor(Color.WHITE);
        txtUsername.setCaretColor(Color.WHITE);
        lblLastname.setFont(new Font("SourceSansPro", Font.BOLD | Font.ITALIC, 16));
        lblLastname.setForeground(Color.BLACK);
        txtLastname.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        txtLastname.setBackground(new Color(0x424242));
        txtLastname.setForeground(Color.WHITE);
        txtLastname.setCaretColor(Color.WHITE);
        txtLastname.setCaretColor(Color.WHITE);

        //---------------------------------------------------Design JButton
        btnLogin.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        btnReturn.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        btnLogin.setBackground(new Color(0x424242));
        btnLogin.setForeground(Color.WHITE);
        btnReturn.setBackground(new Color(0x424242));
        btnReturn.setForeground(Color.WHITE);

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

    public void iconImg() {

        ImageIcon userimage = new ImageIcon("images\\icon.png");
        lblIcon.setBounds(50, 30, 200, 180);

        Image img = userimage.getImage();
        Image imgScale = img.getScaledInstance(200, 180, Image.SCALE_SMOOTH);
        ImageIcon ScaledIcon = new ImageIcon(imgScale);
        lblIcon.setIcon(ScaledIcon);
        outline.add(lblIcon);

    }

    public void optionPanelDesign() {

        //call image from database when code is written to save the image in the database
        ImageIcon userimage = new ImageIcon("images\\default.jpg");
        label.setBounds(150, 15, 180, 160);
        label.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        Image img = userimage.getImage();
        Image imgScale = img.getScaledInstance(180, 160, Image.SCALE_SMOOTH);
        ImageIcon ScaledIcon = new ImageIcon(imgScale);
        label.setIcon(ScaledIcon);
        imgPanel.add(label);

        //welcome message and name 
        imgPanel.add(lblWelcome);
        lblWelcome.setBounds(190, 180, 150, 35);
        lblWelcome.setFont(new Font("SourceSansPro", Font.BOLD, 21));
        lblWelcome.setForeground(Color.WHITE);

        imgPanel.add(lblName);
        lblName.setBounds(160, 210, 100, 35);
        lblName.setFont(new Font("SourceSansPro", Font.BOLD, 21));
        lblName.setForeground(Color.WHITE);

        imgPanel.add(lblSurname);
        lblSurname.setBounds(250, 210, 100, 35);
        lblSurname.setFont(new Font("SourceSansPro", Font.BOLD, 21));
        lblSurname.setForeground(Color.WHITE);

        imgPanel.add(lblInstruction);
        lblInstruction.setBounds(80, 245, 330, 35);
        lblInstruction.setFont(new Font("SourceSansPro", Font.BOLD, 15));
        lblInstruction.setForeground(Color.WHITE);

        //option buttons
        imgPanel.add(btnMeeting);
        imgPanel.add(btnInterview);
        imgPanel.add(btnVisitor);
        imgPanel.add(btnDelivery);
        imgPanel.add(btnCheckIn);

        btnMeeting.addActionListener(this);
        btnInterview.addActionListener(this);
        btnVisitor.addActionListener(this);
        btnDelivery.addActionListener(this);
        btnCheckIn.addActionListener(this);
        //button layout design
        btnMeeting.setBounds(150, 285, 90, 30);
        btnInterview.setBounds(250, 285, 90, 30);
        btnVisitor.setBounds(150, 325, 90, 30);
        btnDelivery.setBounds(250, 325, 90, 30);
        btnCheckIn.setBounds(180, 377, 130, 33);

        //----------------------------------------Button design
        btnMeeting.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        btnMeeting.setBackground(new Color(0x424242));
        btnMeeting.setForeground(Color.WHITE);

        btnInterview.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        btnInterview.setBackground(new Color(0x424242));
        btnInterview.setForeground(Color.WHITE);

        btnDelivery.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        btnDelivery.setBackground(new Color(0x424242));
        btnDelivery.setForeground(Color.WHITE);

        btnVisitor.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        btnVisitor.setBackground(new Color(0x424242));
        btnVisitor.setForeground(Color.WHITE);

        btnCheckIn.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        btnCheckIn.setBackground(new Color(0x424242));
        btnCheckIn.setForeground(Color.WHITE);

        //----------------------------------------------------------------------Button hover methods
        //Hover colour change when the cursor hovers over the Login Jbutton
        btnMeeting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnMeeting.setBackground(new Color(0x005ba3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnMeeting.setBackground(new Color(0x424242));
            }
        });
        btnInterview.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnInterview.setBackground(new Color(0x005ba3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnInterview.setBackground(new Color(0x424242));
            }
        });
        btnDelivery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnDelivery.setBackground(new Color(0x005ba3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDelivery.setBackground(new Color(0x424242));
            }
        });
        btnVisitor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnVisitor.setBackground(new Color(0x005ba3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnVisitor.setBackground(new Color(0x424242));
            }
        });
        btnCheckIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCheckIn.setBackground(new Color(0x005ba3));
                btnCheckIn.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnCheckIn.setBackground(new Color(0x424242));
                btnCheckIn.setForeground(Color.WHITE);

            }
        });
    }

    //--------------------------------------------------------------------------call data from visitor database and verify if user is registered or not
    public void userVerification() {
        String query = "Select * FROM visitors WHERE first_name = ? AND last_name = ?; ";

        try {

            stmt = conn.prepareStatement(query);
            stmt.setString(1, txtUsername.getText());
            stmt.setString(2, txtLastname.getText());

            results = stmt.executeQuery();

            if (results.next()) {

                images.setVisible(false);
                imgPanel.setVisible(true);
                String userName = results.getString("first_name");
                lblName.setText(userName);
                String userSurname = results.getString("last_name");
                lblSurname.setText(userSurname);
                optionPanelDesign();
                temp = userName;
                //checkInTime();

            } else {

                JOptionPane.showMessageDialog(null, "     USER NOT FOUND");

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

        userN = txtUsername.getText();
       
        try {
            String querysql = "update visitors set time_in='" + timeuser + "',date='" + dateuser + "' where first_name='" + userN + "' ";
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
        if (e.getSource() == btnLogin) {
            userVerification();

        } 
        
        else if (e.getSource() == btnMeeting) {

            String reasons = "Meeting";

            try {
                String username = txtUsername.getText();
                
                String querysql = "update visitors set reason='" + reasons + "' where first_name='" + username +"' ";
                stmt = conn.prepareStatement(querysql);
                stmt.execute();
                System.out.println("Reason Updated!!!");

            } catch (SQLException ex) {

                System.out.println("Failed to update");

            }

        } 
        
        else if (e.getSource() == btnInterview) {
            String reasons = "Interview";
            try {
                String username = txtUsername.getText();
                String querysql = "update visitors set reason='" + reasons + "' where first_name='" + username +"' ";
                stmt = conn.prepareStatement(querysql);
                stmt.execute();
                System.out.println("Reason Updated!!!");

            } catch (SQLException ex) {

                System.out.println("Failed to update");

            }
        } 
        
        else if (e.getSource() == btnVisitor) {
            String reasons = "Visiting";
            try {
                String username = txtUsername.getText();
                String querysql = "update visitors set reason='" + reasons + "' where first_name='" + username +"' ";
                stmt = conn.prepareStatement(querysql);
                stmt.execute();
                System.out.println("Reason Updated!!!");

            } catch (SQLException ex) {

                System.out.println("Failed to update");

            }
        } 
        
        else if (e.getSource() == btnDelivery) {
            String reasons = "Delivery";
            try {
                String username = txtUsername.getText();
                String querysql = "update visitors set reason='" + reasons + "' where first_name='" + username +"' ";
                stmt = conn.prepareStatement(querysql);
                stmt.execute();
                System.out.println("Reason Updated!!!");

            } catch (SQLException ex) {

                System.out.println("Failed to update");

            }

        }else if (e.getSource() == btnCheckIn) {

            // save the time stamp to the database
            checkInTime();
            window.setVisible(false);
            VisitorOption view = new VisitorOption();
            window.setVisible(false);
            view.Start();

        }
    }

    //--------------------------------------------------------------------------main function calls starter method to run program
    public void starter() {
        new VisitorLogin().StartGUI();

    }

}
