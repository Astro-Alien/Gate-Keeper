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
public class AgentAddNewCustomerGUI implements ActionListener{

    private JFrame window;
    private JPanel border;
    
    private JPanel secondBorder;
    
    private JLabel lblHeader;
    private JLabel lblName;
    private JTextField txtName;
    
    private JLabel lblSurname;
    private JTextField txtSurname;
    
    private JLabel lblIDnumber;
    private JTextField txtIDnumber;
    
    private JLabel lblMobileNumber;
    private JTextField txtMobileNumber;
    
    private JButton btnSave;
    private JButton btnBack;
    
    private JLabel lblIcon;
    private JLabel lblIconTwo;
    

    public AgentAddNewCustomerGUI() {
        window = new JFrame("Information Management System");
        border = new JPanel();
        secondBorder = new JPanel();
        lblHeader = new JLabel("REGISTER NEW CUSTOMER");
        
        lblName = new JLabel("Name");
        lblSurname = new JLabel("Surname");
        lblIDnumber = new JLabel("ID Number");
        lblMobileNumber = new JLabel("Mobile Number");
        
        txtName = new JTextField();
        txtSurname = new JTextField();
        txtIDnumber = new JTextField();
        txtMobileNumber = new JTextField();
        
        btnSave = new JButton("SAVE RECORD");
        btnBack = new JButton("BACK");
        
        lblIcon = new JLabel();
        lblIconTwo = new JLabel();
        
    }

    public void startGUI() {
        window.setSize(850, 480);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
      
        
        //JPanel border
        border.setLayout(null);
        border.setBackground(new Color(0x36d23b));
        window.add(border);
        
        //Icons
        border.add(lblIcon);
        border.add(lblIconTwo);
        
        Icon();
        IconTwo();
        //----------------------------------------------------------------------Design second Border
        secondBorder.setLayout(null);
        secondBorder.setBounds(139,110,563,289);
        secondBorder.setBackground(new Color(0x007bb4));
        secondBorder.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        border.add(secondBorder);
        
        //HEADING
        lblHeader.setFont(new Font("SourceSansPro", Font.BOLD, 28));
        lblHeader.setForeground(Color.BLACK);
        lblHeader.setBounds(235, 20, 500, 50);
        border.add(lblHeader);
        
        //----------------------------------------------------------------------JLabel and Textfield Designs
        lblName.setFont(new Font("SourceSansPro", Font.BOLD, 18));
        lblName.setForeground(Color.BLACK);
        lblName.setBounds(50, 40, 150, 40);
        txtName.setBounds(50, 75, 200, 30);
        txtName.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        secondBorder.add(lblName);
        secondBorder.add(txtName);
        
        lblIDnumber.setFont(new Font("SourceSansPro", Font.BOLD, 18));
        lblIDnumber.setForeground(Color.BLACK);
        lblIDnumber.setBounds(300, 40, 150, 40);
        txtIDnumber.setBounds(300, 75, 200, 30);
        txtIDnumber.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        secondBorder.add(lblIDnumber);
        secondBorder.add(txtIDnumber);
        
        lblSurname.setFont(new Font("SourceSansPro", Font.BOLD, 18));
        lblSurname.setForeground(Color.BLACK);
        lblSurname.setBounds(50, 110, 150, 40);
        txtSurname.setBounds(50, 145, 200, 30);
        txtSurname.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        secondBorder.add(lblSurname);
        secondBorder.add(txtSurname);
        
        lblMobileNumber.setFont(new Font("SourceSansPro", Font.BOLD, 18));
        lblMobileNumber.setForeground(Color.BLACK);
        lblMobileNumber.setBounds(300, 110, 150, 40);
        txtMobileNumber.setBounds(300, 145, 200, 30);
        txtMobileNumber.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        secondBorder.add(lblMobileNumber);
        secondBorder.add(txtMobileNumber);
        
        //Button design
        btnSave.setBounds(220, 190, 130, 33);
        btnSave.addActionListener(this);
        secondBorder.add(btnSave);
        
        btnBack.setBounds(220, 240, 130, 33);
        btnBack.addActionListener(this);
        secondBorder.add(btnBack);
        
        btnSave.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        btnBack.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        btnSave.setBackground(new Color(0xffffff));
        btnSave.setForeground(Color.BLACK);
        btnBack.setBackground(new Color(0xffffff));
        btnBack.setForeground(Color.BLACK);
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
    }
    public void Icon() {

        ImageIcon userimage = new ImageIcon("Images\\realestate.png");
        lblIcon.setBounds(100, 10, 121, 77);

        Image img = userimage.getImage();
        Image imgScale = img.getScaledInstance(121, 77, Image.SCALE_SMOOTH);
        ImageIcon ScaledIcon = new ImageIcon(imgScale);
        lblIcon.setIcon(ScaledIcon);
        border.add(lblIcon);

    }
    public void IconTwo() {

        ImageIcon userimage = new ImageIcon("Images\\realestate.png");
        lblIconTwo.setBounds(625,  10, 121, 77);

        Image img = userimage.getImage();
        Image imgScale = img.getScaledInstance(121, 77, Image.SCALE_SMOOTH);
        ImageIcon ScaledIcon = new ImageIcon(imgScale);
        lblIconTwo.setIcon(ScaledIcon);
        border.add(lblIconTwo);

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSave){
            String id = txtIDnumber.getText();
            String name = txtName.getText();
            String surname = txtSurname.getText();
            String mobile = txtMobileNumber.getText();
            
            ClientAgent opn = new ClientAgent();
            opn.ClientRegisterCustomer(id,name,surname,mobile);
            
            
            
        }
        if(e.getSource() == btnBack){ 
            AgentMenuGUI opn = new AgentMenuGUI();
            opn.startGUI();
            window.dispose();
        
        }
     
     
     
     
     }
   
}
