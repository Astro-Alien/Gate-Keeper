/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.gatekeeper.registration;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Charles
 */
public class Main extends JFrame {

    private JButton regUser;
    private JButton newUser;
    private JButton btnEmp;

    public JFrame welcomeWindow() {

        JFrame welcome = new JFrame("Gatekeeper");

        JLabel lblNewUserRegister = new JLabel("Gatekeeper Visitor Registration");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        lblNewUserRegister.setBounds(50, 100, 500, 50);

        regUser = new JButton("VISITOR");
        newUser = new JButton("NEW VISITOR");
        btnEmp = new JButton("EMPLOYEE");
        welcome.setResizable(false);
        btnEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //VisitorRegistration vr = new VisitorRegistration();
                EmployeeLogin vr = new EmployeeLogin();
                welcome.setVisible(false);
                vr.starter();
            }
        });
        newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                welcome.setVisible(false);
                VisitorRegistration rg = new VisitorRegistration();
                welcome.setVisible(false);
                rg.setVisible(true);
            }
        });

        regUser.addActionListener(new ActionListener() {
            /**
             * Added code to call the login form
             *
             * @author Charles
             */

            public void actionPerformed(ActionEvent ae) {
                welcome.setVisible(false);
                VisitorLogin rg = new VisitorLogin();
                welcome.setVisible(false);
                rg.starter();
            }
        });

        welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcome.setSize(500, 600);
        welcome.setLocationRelativeTo(null);
        welcome.setVisible(true);
        welcome.getContentPane().setLayout(null);

        regUser.setFont(new Font("roman", Font.BOLD, 14));
        regUser.setBounds(130, 300, 210, 37);
        
        newUser.setFont(new Font("roman", Font.BOLD, 14));
        newUser.setBounds(130, 350, 210, 37);

        btnEmp.setFont(new Font("roman", Font.BOLD, 14));
        btnEmp.setBounds(130, 400, 210, 37);

        welcome.add(lblNewUserRegister);
        welcome.add(regUser);
        welcome.add(newUser);
        welcome.add(btnEmp);

        return welcome;
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