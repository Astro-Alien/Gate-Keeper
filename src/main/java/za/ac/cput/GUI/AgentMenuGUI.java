/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import za.ac.cput.client.ClientAgent;


/**
 *
 * @author Charles
 */
public class AgentMenuGUI implements ActionListener{

    private JFrame window;
    private JPanel border;
    private JPanel secondBorder;
    private JLabel lblIcon;
    private JLabel lblIconTwo;
    private JLabel lblIconThree;
    private JLabel lblHeading;
    
    private JButton btnCreateNewUser;
    private JButton btnRecordTransactions;
    private JButton btnBack;
   
    public AgentMenuGUI() {
        window = new JFrame("Information Management System");
        border = new JPanel();
        secondBorder = new JPanel();
        
        lblIcon = new JLabel();
        lblIconTwo = new JLabel();
        lblIconThree = new JLabel();
        lblHeading = new JLabel("REAL ESTATE COMPANY");
        
        btnCreateNewUser = new JButton("ADD CUSTOMER");
        btnRecordTransactions = new JButton("RECORD TRANSACTION");
        btnBack = new JButton("BACK");
    }

    public void startGUI() {
        window.setSize(850, 480);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        //JPanel border
        border.setLayout(null);
        border.setBackground(new Color(0x36d23b));
        window.add(border);
        
        //----------------------------------------------------------------------Design second Border
        secondBorder.setLayout(null);
        secondBorder.setBounds(265,50,306,370);
        secondBorder.setBackground(new Color(0x007bb4));
        secondBorder.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        border.add(secondBorder);
        
        //icon
        border.add(lblIcon);
        border.add(lblIconTwo);
        secondBorder.add(lblIconThree);
        Icon();
        IconTwo();
        IconThree();
        
        //----------------------------------------------------------------------JLabel Design
        lblHeading.setFont(new Font("SourceSansPro", Font.BOLD, 28));
        lblHeading.setForeground(Color.BLACK);
        lblHeading.setBounds(250, 0, 500, 50);
        border.add(lblHeading);
        //---------------------------------------------------Design JButton
        btnCreateNewUser.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        btnRecordTransactions.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        btnBack.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        btnCreateNewUser.setForeground(Color.BLACK);
        btnCreateNewUser.setBackground(new Color(0xffffff));
        btnRecordTransactions.setBackground(new Color(0xffffff));
        btnRecordTransactions.setForeground(Color.BLACK);
        btnBack.setBackground(new Color(0xffffff));
        btnBack.setForeground(Color.BLACK);
        
        btnCreateNewUser.setBounds(73, 150, 160, 40);
        btnCreateNewUser.addActionListener(this);
        btnRecordTransactions.setBounds(73, 210, 160, 40);
        btnRecordTransactions.addActionListener(this);
        btnBack.setBounds(73, 270, 160, 40);
        btnBack.addActionListener(this);
        
        secondBorder.add(btnCreateNewUser);
        secondBorder.add(btnRecordTransactions);
        secondBorder.add(btnBack);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
    public void Icon() {

        ImageIcon userimage = new ImageIcon("Images\\realestate.png");
        lblIcon.setBounds(0, 100, 282, 179);

        Image img = userimage.getImage();
        Image imgScale = img.getScaledInstance(282, 179, Image.SCALE_SMOOTH);
        ImageIcon ScaledIcon = new ImageIcon(imgScale);
        lblIcon.setIcon(ScaledIcon);
        border.add(lblIcon);

    }
    public void IconTwo() {

        ImageIcon userimage = new ImageIcon("Images\\realestate.png");
        lblIconTwo.setBounds(555, 100, 282, 179);

        Image img = userimage.getImage();
        Image imgScale = img.getScaledInstance(282, 179, Image.SCALE_SMOOTH);
        ImageIcon ScaledIcon = new ImageIcon(imgScale);
        lblIconTwo.setIcon(ScaledIcon);
        border.add(lblIconTwo);

    }
    public void IconThree() {

        ImageIcon userimage = new ImageIcon("Images\\realestate.png");
        lblIconThree.setBounds(60, 10, 187, 119);

        Image img = userimage.getImage();
        Image imgScale = img.getScaledInstance(187, 119, Image.SCALE_SMOOTH);
        ImageIcon ScaledIcon = new ImageIcon(imgScale);
        lblIconThree.setIcon(ScaledIcon);
        secondBorder.add(lblIconThree);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCreateNewUser){
           //opens create new user form
            AgentAddNewCustomerGUI opn = new AgentAddNewCustomerGUI();
            opn.startGUI();
            window.dispose();
        }
        if(e.getSource() == btnRecordTransactions){
           //opens create new user form
            window.hide();
            AgentRecordsTransactionsGUI opn = new AgentRecordsTransactionsGUI();
            opn.startGUI();
            window.dispose();
        }
        if(e.getSource() == btnBack){
            
            ClientAgent opn = new ClientAgent();
            opn.startLoginGUI();
            window.dispose();
            
        }
    
    }
    public static void main(String[] args) {
        new AgentMenuGUI().startGUI();
    }
 
}
