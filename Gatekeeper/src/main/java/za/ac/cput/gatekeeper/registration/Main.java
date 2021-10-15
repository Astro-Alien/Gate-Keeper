/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.gatekeeper.registration;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author Charles
 */
public class Main implements ActionListener {

    private JPanel border;
    private JPanel secondBorder;
    private JPanel thirdBorder;
    private JPanel fourthBorder;
    private JButton regUser;
    private JButton newUser;
    private JButton btnEmp;
    private JFrame welcome;
    private JLabel lblIcon;
    private JLabel lblIconTwo;
    private JLabel lblIconThree;
    private JLabel lblIconFour;
    private JLabel lblLink;
    private JLabel lblQ;

    public Main() {

        lblIcon = new JLabel();
        lblIconTwo = new JLabel();
        lblIconThree = new JLabel();
        lblIconFour = new JLabel();
        border = new JPanel();
        secondBorder = new JPanel();
        thirdBorder = new JPanel();
        fourthBorder = new JPanel();
    }

    public void welcomeWindow() {

        welcome = new JFrame("Gatekeeper");
        welcome.setSize(876, 497);
        welcome.setResizable(false);

        border.setSize(876, 497);
        border.setLayout(null);
        //border.setBackground(new Color(0x005ba3));
        welcome.add(border);

        border.add(lblIcon);
        scalingImg();
       
        
        border.add(lblIconFour);
        scalingImg();
        //----------------------------------------------------------------------Secondary Panel
       
        secondBorder.setLayout(null);
        secondBorder.setBackground(new Color(0x03a9f4));
        secondBorder.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        secondBorder.setBounds(28, 100, 250, 300);
        lblIcon.add(secondBorder);
        
        secondBorder.add(lblIconTwo);
        scalingImgTwo();
        
       
        thirdBorder.setLayout(null);
        thirdBorder.setBackground(new Color(0x03a9f4));
        thirdBorder.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        thirdBorder.setBounds(310, 100, 250, 300);
        lblIcon.add(thirdBorder);

        thirdBorder.add(lblIconThree);
        scalingImgThree();
        
        
        fourthBorder.setLayout(null);
        fourthBorder.setBackground(new Color(0x03a9f4));
        fourthBorder.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        fourthBorder.setBounds(588, 100, 250, 300);
        lblIcon.add(fourthBorder);

        fourthBorder.add(lblIconFour);
        scalingImgFour();
        
        JLabel lblNewUserRegister = new JLabel("Gatekeeper");
        lblIcon.add(lblNewUserRegister);
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 60));
        lblNewUserRegister.setForeground(Color.BLACK);
        lblNewUserRegister.setBounds(310, 25, 500, 50);

        regUser = new JButton("SEARCH VISITOR");
        regUser.setFont(new Font("roman", Font.BOLD, 15));
        regUser.setBounds(24, 240, 202, 37);

        newUser = new JButton("REGISTER VISITOR");
        newUser.setFont(new Font("roman", Font.BOLD, 15));
        newUser.setBounds(24, 240, 202, 37);

        btnEmp = new JButton("GARAGE LOGIN");
        btnEmp.setFont(new Font("roman", Font.BOLD, 15));
        btnEmp.setBounds(24, 240, 202, 37);

        lblQ = new JLabel("Are you an admin?");
        lblQ.setFont(new Font("roman", Font.BOLD, 15));
        lblQ.setBounds(340, 410, 210, 37);

        lblLink = new JLabel("Login");
        lblLink.setFont(new Font("roman", Font.BOLD, 15));
        lblLink.setBounds(485, 410, 210, 37);
        lblLink.setForeground(new Color(0xffffff));

        //----------------------------------------------------------------------HyperLink
        lblLink.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                welcome.setVisible(false);
                EmployeeLogin vr = new EmployeeLogin();
                vr.starter();
                welcome.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblLink.setForeground(new Color(0x005ba3));
                lblLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblLink.setForeground(new Color(0xffffff));
            }
        });
        //----------------------------------------------------------------------adding elements to border
        secondBorder.add(regUser);
        thirdBorder.add(newUser);
        fourthBorder.add(btnEmp);
        lblIcon.add(lblQ);
        lblIcon.add(lblLink);
        //-----------------------------------------------------image

        //---------------------------------------------------Design JButton
        regUser.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        newUser.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        btnEmp.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        regUser.setBackground(new Color(0x424242));
        regUser.setForeground(Color.WHITE);
        newUser.setBackground(new Color(0x424242));
        newUser.setForeground(Color.WHITE);
        btnEmp.setBackground(new Color(0x424242));
        btnEmp.setForeground(Color.WHITE);

        newUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        regUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEmp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //Hover colour change when the cursor hovers over the Login Jbutton
        newUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                newUser.setBackground(new Color(0x005ba3));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                newUser.setBackground(new Color(0x424242));
            }
        });
        regUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                regUser.setBackground(new Color(0x005ba3));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                regUser.setBackground(new Color(0x424242));
            }
        });
        btnEmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnEmp.setBackground(new Color(0x005ba3));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnEmp.setBackground(new Color(0x424242));
            }
        });

        //-----------------------------------------------------Action Listeners for buttons
        regUser.addActionListener(this);
        newUser.addActionListener(this);
        btnEmp.addActionListener(this);

        //-----------------------------------------------------------------------
        welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcome.setLocationRelativeTo(null);
        welcome.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        /**
         * Added code to call the login form
         *
         * @author Charles
         */
        if (e.getSource() == newUser) {

            welcome.setVisible(false);
            VisitorRegistration rg = new VisitorRegistration();
            rg.VisitorRegistrationGUI();
            welcome.dispose();
        } else if (e.getSource() == regUser) {
            welcome.setVisible(false);
            VisitorLogin rg = new VisitorLogin();
            rg.starter();
            welcome.dispose();

        } else if (e.getSource() == btnEmp) {

            welcome.setVisible(false);
            garageEntrance opn = new garageEntrance();
            opn.loginFrame();
            welcome.dispose();

        }

    }

    public void startProgram() {
        /**
         * @author Charles
         */

        new Main().welcomeWindow();

    }

    public void scalingImg() {

        ImageIcon userimg = new ImageIcon("images\\backgroundColour.png");
        lblIcon.setBounds(0, 0, 876, 497);
        Image img = userimg.getImage();

        Image imgScale = img.getScaledInstance(876, 497, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        lblIcon.setIcon(scaledIcon);
        border.add(lblIcon);

    }
    public void scalingImgTwo() {

        ImageIcon userimg = new ImageIcon("images\\search.png");
        lblIconTwo.setBounds(30, 50, 198, 144);
        Image img = userimg.getImage();

        Image imgScale = img.getScaledInstance(198, 144, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        lblIconTwo.setIcon(scaledIcon);
        secondBorder.add(lblIconTwo);

    }
    public void scalingImgThree() {

        ImageIcon userimg = new ImageIcon("images\\acc.png");
        lblIconThree.setBounds(50, 50, 167, 171);
        Image img = userimg.getImage();

        Image imgScale = img.getScaledInstance(167, 171, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        lblIconThree.setIcon(scaledIcon);
        thirdBorder.add(lblIconThree);

    }
    public void scalingImgFour() {

        ImageIcon userimg = new ImageIcon("images\\parkings.png");
        lblIconFour.setBounds(23, 40, 206, 185);
        Image img = userimg.getImage();

        Image imgScale = img.getScaledInstance(206, 185, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        lblIconFour.setIcon(scaledIcon);
        fourthBorder.add(lblIconFour);

    }

    public static void main(String[] args) {
        Main st = new Main();
        st.startProgram();

    }
}
