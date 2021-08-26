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

    private JButton regUser;
    private JButton newUser;
    private JButton btnEmp;
    private ImageIcon icon;
    private Image image;
    private JFrame welcome;
    private JLabel lblLink;
    private JLabel lblQ;

    public void welcomeWindow() {

        welcome = new JFrame("Gatekeeper");
        welcome.setSize(876, 497);
        welcome.setResizable(false);

        JPanel border = new JPanel();
        border.setSize(876, 497);
        border.setLayout(null);
        border.setBackground(new Color(0x005ba3));
        welcome.add(border);

        //----------------------------------------------------------------------Secondary Panel
        JPanel secondBorder = new JPanel();
        secondBorder.setLayout(null);
        secondBorder.setBackground(new Color(0x03a9f4));
        secondBorder.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        secondBorder.setBounds(39, 43, 785, 370);
        border.add(secondBorder);

        JLabel lblNewUserRegister = new JLabel("Gatekeeper");
        secondBorder.add(lblNewUserRegister);
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 60));
        lblNewUserRegister.setForeground(Color.BLACK);
        lblNewUserRegister.setBounds(255, 35, 500, 50);

        regUser = new JButton("VISITOR");
        regUser.setFont(new Font("roman", Font.BOLD, 14));
        regUser.setBounds(290, 190, 210, 37);

        newUser = new JButton("NEW VISITOR");
        newUser.setFont(new Font("roman", Font.BOLD, 14));
        newUser.setBounds(290, 240, 210, 37);

        btnEmp = new JButton("EMPLOYEE");
        btnEmp.setFont(new Font("roman", Font.BOLD, 14));
        btnEmp.setBounds(290, 290, 210, 37);

        lblQ = new JLabel("Are you an employee?");
        lblQ.setFont(new Font("roman", Font.BOLD, 13));
        lblQ.setBounds(300, 325, 210, 37);

        lblLink = new JLabel("Login");
        lblLink.setFont(new Font("roman", Font.BOLD, 13));
        lblLink.setBounds(440, 325, 210, 37);
        lblLink.setForeground(new Color(0xffffff));

        //----------------------------------------------------------------------HyperLink
        lblLink.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                welcome.setVisible(false);
                garageEntrance opn = new garageEntrance();
                opn.loginFrame();
                welcome.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblLink.setForeground(Color.RED);
                lblLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblLink.setForeground(new Color(0xffffff));
            }
        });
        //----------------------------------------------------------------------adding elements to border
        secondBorder.add(regUser);
        secondBorder.add(newUser);
        secondBorder.add(btnEmp);
        secondBorder.add(lblQ);
        secondBorder.add(lblLink);
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
            EmployeeLogin vr = new EmployeeLogin();
            vr.starter();
            welcome.dispose();

        }

    }

    public void startProgram() {
        /**
         * @author Charles
         */

        new Main().welcomeWindow();

    }

    public static void main(String[] args) {
        Main st = new Main();
        st.startProgram();

    }
}
