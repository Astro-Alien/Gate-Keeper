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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Sine
 */
public class garageEntrance implements ActionListener {

    DbConnection db;

    private JPanel border;
    private JLabel label;
    private JLabel lblIcon;
    private JLabel lblIconTwo;
    private JLabel backgroundIconTwo;
    private JFrame window;
    private JPanel outline;

    private String temp;
    //--------------------------------------------------------------------------J Labels and Textfields
    //Firstname
    private JLabel lblUsername;
    private JTextField txtUsername;
    //Lastname
    private JLabel lblLastname;
    private JPasswordField txtLastname;
    //Buttons
    private JButton btnLogin;
    private JButton btnReturn;

    //Secondary panel
    private JPanel imgPanel;
    private JLabel lblName;
    private JLabel lblWMsg;
    private JLabel lblMsg;
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

    JFrame parking_allo;
    JLabel greeting;
    JLabel parking_spot_label;
    JPanel parking_panel;

    Connection conn;

    //PARKING SPOT ASSIGNMENT-----------------------------------------------
    Random rnd_num = new Random();
    int i = (int) (1 + rnd_num.nextInt(25));
    String parking = "Bay " + i;
    //----------------------------------------------------------------------

    public garageEntrance() {

        //--------Login frame labels and buttons----------------//
        //---------------------------------------------------Username label and textfield
        lblUsername = new JLabel("USERNAME");
        txtUsername = new JTextField(16);
        border = new JPanel();
        lblLastname = new JLabel("PASSWORD");
        txtLastname = new JPasswordField(16);
        backgroundIconTwo = new JLabel();
        imgPanel = new JPanel();

        //---------------------------------------------------Login button & Registration button
        btnLogin = new JButton("LOGIN");
        btnReturn = new JButton("BACK");

        //---------------------------------------------------option welcome message
        lblWelcome = new JLabel("WELCOME");
        lblWMsg = new JLabel("WELCOME");
        lblMsg = new JLabel("Login to receive your parking bay number");
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
        lblIconTwo = new JLabel();
        //-----------------------------------------------------//

        greeting = new JLabel();
    }

    public void greetingFrame() {
        parking_allo = new JFrame("Gatekeeper");
        parking_allo.getContentPane().setBackground(new Color(0x005ba3));
        parking_allo.setSize(876, 497);
        parking_allo.setLocationRelativeTo(null);
        parking_allo.setVisible(true);
        parking_allo.getContentPane().setLayout(null);

        parking_spot_label = new JLabel();
        parking_panel = new JPanel();

        parking_panel.setLayout(null);
        parking_panel.setBounds(30, 22, 800, 405);
        parking_panel.setBackground(new Color(0x03a9f4));
        parking_panel.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));

        greeting.setFont(new Font("SourceSansPro", Font.BOLD, 30));
        greeting.setForeground(Color.BLACK);
        greeting.setBounds(250, 50, 400, 60);
        parking_panel.add(greeting);

        parking_spot_label.setFont(new Font("SourceSansPro", Font.BOLD, 20));
        parking_spot_label.setForeground(Color.BLACK);
        parking_spot_label.setBounds(230, 300, 400, 60);
        parking_spot_label.setText("Your parking spot for today is: " + parking);
        parking_panel.add(parking_spot_label);

        parking_allo.add(parking_panel);
    }

    //takes and verifies username and password credentials
    public void loginFrame() {

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
        outline = new JPanel();
        lblIconTwo.add(outline);
        outline.setBounds(125, 60, 294, 355);
        outline.setLayout(null);

        outline.add(lblIcon);

        iconImg();
        lblIconTwo.add(backgroundIconTwo);
        scalingImg();
        //---------------------------------------------------Secondary panel
        border.add(imgPanel);

        //layout and size
        imgPanel.setLayout(null);
        imgPanel.setBounds(353, 22, 480, 420);
        imgPanel.setBackground(new Color(0x03a9f4));
        imgPanel.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));

        imgPanel.setVisible(false);
        //---------------------------------------------------JLabel
        JLabel lblUser = new JLabel("Garage Entrance");
        lblUser.setFont(new Font("SourceSansPro", Font.BOLD | Font.ITALIC, 25));
        lblUser.setForeground(Color.BLACK);
        lblUser.setBounds(40, 5, 300, 60);
        outline.add(lblUser);

        //---------------------------------------------------positioning Username label and textfield
        lblUsername.setBounds(47, 135, 150, 40);
        outline.add(lblUsername);
        txtUsername.setBounds(47, 170, 200, 30);
        outline.add(txtUsername);

        lblLastname.setBounds(47, 195, 200, 40);
        outline.add(lblLastname);
        txtLastname.setBounds(47, 230, 200, 30);
        outline.add(txtLastname);
        //---------------------------------------------------positioning login button and adding action listener
        btnLogin.setBounds(82, 268, 130, 33);
        outline.add(btnLogin);
        btnLogin.addActionListener(this);

        //---------------------------------------------------positioning  Submit button 
        btnReturn.setBounds(82, 305, 130, 33);
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
        outline.setBackground(new Color(0xffffff));

        outline.setBorder(BorderFactory.createLineBorder(new Color(0x03a9f4), 4));
        //---------------------------------------------------Design JLabel
        lblUsername.setFont(new Font("SourceSansPro", Font.BOLD, 15));
        lblUsername.setForeground(Color.BLACK);
        txtUsername.setBorder(BorderFactory.createLineBorder(new Color(0x03a9f4), 3));
        txtUsername.setBackground(new Color(0x424242));
        txtUsername.setForeground(Color.WHITE);
        txtUsername.setCaretColor(Color.WHITE);
        txtUsername.setCaretColor(Color.WHITE);
        lblLastname.setFont(new Font("SourceSansPro", Font.BOLD, 15));
        lblLastname.setForeground(Color.BLACK);
        txtLastname.setBorder(BorderFactory.createLineBorder(new Color(0x03a9f4), 3));
        txtLastname.setBackground(new Color(0x424242));
        txtLastname.setForeground(Color.WHITE);
        txtLastname.setCaretColor(Color.WHITE);
        txtLastname.setCaretColor(Color.WHITE);

        //---------------------------------------------------Design JButton
        btnLogin.setBorder(BorderFactory.createLineBorder(new Color(0x03a9f4), 3));
        btnReturn.setBorder(BorderFactory.createLineBorder(new Color(0x03a9f4), 3));
        btnLogin.setBackground(new Color(0x424242));
        btnLogin.setForeground(Color.WHITE);
        btnReturn.setBackground(new Color(0x424242));
        btnReturn.setForeground(Color.WHITE);

        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReturn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblWMsg.setBounds(570, 85, 150, 33);
        lblWMsg.setForeground(Color.WHITE);
        lblWMsg.setFont(new Font("SourceSansPro", Font.BOLD, 29));

        lblMsg.setBounds(490, 110, 350, 33);
        lblMsg.setForeground(Color.WHITE);
        lblMsg.setFont(new Font("SourceSansPro", Font.BOLD, 15));

        lblIconTwo.add(lblWMsg);
        lblIconTwo.add(lblMsg);
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

    public void scalingImgTwo() {

        ImageIcon userimg = new ImageIcon("images\\backgroundColour.png");
        lblIconTwo.setBounds(0, 0, 876, 497);
        Image img = userimg.getImage();

        Image imgScale = img.getScaledInstance(876, 497, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        lblIconTwo.setIcon(scaledIcon);
        border.add(lblIconTwo);

    }

    public void scalingImg() {

        ImageIcon userimg = new ImageIcon("images\\parking.png");
        backgroundIconTwo.setBounds(500, 159, 271, 271);
        Image img = userimg.getImage();

        Image imgScale = img.getScaledInstance(271, 271, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        backgroundIconTwo.setIcon(scaledIcon);
        lblIconTwo.add(backgroundIconTwo);

    }

    public void iconImg() {

        ImageIcon userimage = new ImageIcon("images\\icon.png");
        lblIcon.setBounds(85, 30, 130, 130);

        Image img = userimage.getImage();
        Image imgScale = img.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            try {
                conn = DbConnection.ConnectEmpDb();

                String user;
                ResultSet userResults;
                PreparedStatement userStatement;

                user = "SELECT * FROM employee WHERE  id_no = ? AND password = ?";

                userStatement = conn.prepareStatement(user);

                userStatement.setString(1, txtUsername.getText());
                userStatement.setString(2, txtLastname.getText());

                userResults = userStatement.executeQuery();

                if (userResults.next()) {
                    String name = userResults.getString("first_name");
                    greeting.setText("Good morning " + name + "!");
                    checkInTime();
                    greetingFrame();
                } else {

                    txtUsername.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    txtLastname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    JOptionPane.showMessageDialog(null, "Login credentials not found.");
                    txtUsername.setBorder(BorderFactory.createLineBorder(new Color(0x03a9f4), 3));
                    txtLastname.setBorder(BorderFactory.createLineBorder(new Color(0x03a9f4), 3));
                    
                }
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
    }

    public void checkInTime() {
        //------------------------------------------------check in time method code will be added here
        Date recentDate = new Date();
        PreparedStatement stmt;

        SimpleDateFormat dateStamp = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeStamp = new SimpleDateFormat("h:mm:ss a");
        String dateuser = dateStamp.format(recentDate);
        String timeuser = timeStamp.format(recentDate);

        String id = txtUsername.getText();

        try {
            String querysql = "update employee set time_in='" + timeuser + "',date='" + dateuser + "'" + ", parking_spot = '" + parking + "' where id_no='" + id + "' ";
            stmt = conn.prepareStatement(querysql);
            stmt.execute();

            conn.close();

        } catch (SQLException e) {

            System.out.println("Failed to update");

        }

    }

}
