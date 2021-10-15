/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import za.ac.cput.client.AdmintClient;
import za.ac.cput.client.ClientAgent;




/**
 *
 * @author Charles
 */
public class Main implements ActionListener{
    private JFrame window;
    private JPanel border;
    private JPanel secondBorder;
    private JButton btnAdmin;
    private JButton btnAgent;
    private JButton btnExit;
    
    public Main(){
         window = new JFrame("Information Management System");
         border = new JPanel();
         secondBorder = new JPanel();
         
         btnAdmin = new JButton("Admin Login");
         btnAgent = new JButton("Agent Login");
         btnExit = new JButton("Exit");    
        
    }
    public void startProgram(){
    
        window.setSize(876,486);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        border.setLayout(null);
        window.add(border);
        border.setBackground(new Color(0x36d23b));
        //----------------------------------------------------------------------Design second Border
        secondBorder.setLayout(null);
        secondBorder.setBounds(70,50,720,350);
        secondBorder.setBackground(new Color(0x007bb4));
        secondBorder.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        border.add(secondBorder);
        
        //----------------------------------------------------------------------buttons design
        btnAdmin.setBounds(290, 150, 130, 33);
        btnAdmin.addActionListener(this);
        secondBorder.add(btnAdmin);
        
        btnAgent.setBounds(290, 200, 130, 33);
        btnAgent.addActionListener(this);
        secondBorder.add(btnAgent);
        
        btnExit.setBounds(290, 250, 130, 33);
        btnExit.addActionListener(this);
        secondBorder.add(btnExit);
        
        //---------------------------------------------------Design JButton
        btnAdmin.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        btnAgent.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        btnExit.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        btnAdmin.setForeground(Color.BLACK);
        btnAdmin.setBackground(new Color(0xffffff));
        btnAgent.setBackground(new Color(0xffffff));
        btnAgent.setForeground(Color.BLACK);
        btnExit.setBackground(new Color(0xffffff));
        btnExit.setForeground(Color.BLACK);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    
        if(e.getSource() == btnAdmin){
           
            AdmintClient opn = new AdmintClient();
            opn.startGUI();
            window.dispose();
            
        }
    
        if(e.getSource() == btnAgent){
            ClientAgent opn = new ClientAgent();
            opn.startLoginGUI();
            window.dispose();
            
            
        }
        if(e.getSource() == btnExit){
            window.dispose();
            
        }
    
    }
    public static void main(String[] args) {
        new Main().startProgram();
    }
}
