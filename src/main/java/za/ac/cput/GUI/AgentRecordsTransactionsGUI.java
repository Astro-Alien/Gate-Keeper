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
public class AgentRecordsTransactionsGUI implements ActionListener {

  
    private JFrame window;
    private JPanel border;
    private JPanel secondborder;
    private JPanel thirdborder;

    private JComboBox bxLocation;
    private JComboBox bxHouses;
    private JComboBox bxCustomer;
    
    private JLabel lblLocation;
    private JLabel lblHouses;
    private JLabel lblCustomer;
    
    private JLabel lblHeader;
    private JLabel lblIcon;
    private JLabel lblIconTwo;

    private JLabel lblProperty;
    private JLabel lblRooms;
    private JLabel lblAddress;
    private JLabel lblPrice;
    private JLabel lblName;
    private JLabel lblSurname;

    private JTextField txtProperty;
    private JTextField txtRooms;
    private JTextField txtAddress;
    private JTextField txtPrice;
    private JTextField txtName;
    private JTextField txtSurname;

    private JButton btnTransact;
    private JButton btnBack;

    public AgentRecordsTransactionsGUI() {
        window = new JFrame("Information Management System");
        border = new JPanel();
        secondborder = new JPanel();
        thirdborder = new JPanel();

        lblHeader = new JLabel("CUSTOMER TRANSACTION");
        lblProperty = new JLabel("Property type:");
        lblRooms = new JLabel("Number of rooms:");
        lblAddress = new JLabel("Address:");
        lblPrice = new JLabel("Renat cost:");
        lblName = new JLabel("Name:");
        lblSurname = new JLabel("Surname:");

        txtProperty = new JTextField();
        txtRooms = new JTextField();
        txtAddress = new JTextField();
        txtPrice = new JTextField();
        txtName = new JTextField();
        txtSurname = new JTextField();

        bxLocation = new JComboBox();
        bxHouses = new JComboBox();
        bxCustomer = new JComboBox();
        
        lblLocation = new JLabel("Locations:");
        lblHouses = new JLabel("Available Homes:");
        lblCustomer = new JLabel("Customers:");
        
        lblIcon = new JLabel();
        lblIconTwo = new JLabel();

        btnTransact = new JButton("TRANSACT");
        btnBack = new JButton("BACK");
        
        
    }
    
    public void startGUI() {
        ClientAgent opn = new ClientAgent();
        opn.populateCustomerBox();
        window.setSize(850, 480);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        border.setLayout(null);
        window.add(border);
        border.setBackground(new Color(0x36d23b));

        //----------------------------------------------------------------------border
        //Icons
        lblHeader.setForeground(Color.BLACK);
        lblHeader.setFont(new Font("SourceSansPro", Font.BOLD, 24));
        lblHeader.setBounds(300, 20, 340, 33);
        
        border.add(lblIcon);
        border.add(lblHeader);
        border.add(lblIconTwo);

        Icon();
        IconTwo();

        //----------------------------------------------------------------------secondborder
        secondborder.setLayout(null);
        secondborder.setBackground(new Color(0x007bb4));
        secondborder.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        secondborder.setBounds(30, 80, 420, 348);
        border.add(secondborder);
        
        lblLocation.setForeground(Color.BLACK);
        lblLocation.setFont(new Font("SourceSansPro", Font.BOLD, 16));
        lblLocation.setBounds(20, 18, 150, 33);
        secondborder.add(lblLocation);
        lblHouses.setForeground(Color.BLACK);
        lblHouses.setFont(new Font("SourceSansPro", Font.BOLD, 16));
        lblHouses.setBounds(20, 68, 150, 33);
        secondborder.add(lblHouses);

        
        bxLocation.setBounds(160,20,200,25);
        bxHouses.setBounds(160,70,200,25);
        
        
        secondborder.add(bxLocation);
        secondborder.add(bxHouses);
        
        //labels
        lblProperty.setForeground(Color.BLACK);
        lblProperty.setFont(new Font("SourceSansPro", Font.BOLD, 16));
        lblProperty.setBounds(10, 150, 150, 33);
        secondborder.add(lblProperty);
        txtProperty.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        txtProperty.setBackground(Color.white);
        txtProperty.setBounds(160, 150, 220, 30);
        txtProperty.setEditable(false);
        secondborder.add(txtProperty);

        lblRooms.setForeground(Color.BLACK);
        lblRooms.setFont(new Font("SourceSansPro", Font.BOLD, 16));
        lblRooms.setBounds(10, 190, 150, 33);
        secondborder.add(lblRooms);
        txtRooms.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        txtRooms.setBackground(Color.white);
        txtRooms.setBounds(160, 190, 220, 30);
        txtRooms.setEditable(false);
        secondborder.add(txtRooms);

        lblAddress.setForeground(Color.BLACK);
        lblAddress.setFont(new Font("SourceSansPro", Font.BOLD, 16));
        lblAddress.setBounds(10, 230, 150, 33);
        secondborder.add(lblAddress);
        txtAddress.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        txtAddress.setBackground(Color.white);
        txtAddress.setBounds(160, 230, 220, 30);
        txtAddress.setEditable(false);
        secondborder.add(txtAddress);

        lblPrice.setForeground(Color.BLACK);
        lblPrice.setFont(new Font("SourceSansPro", Font.BOLD, 16));
        lblPrice.setBounds(10, 270, 150, 33);
        secondborder.add(lblPrice);
        txtPrice.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        txtPrice.setBackground(Color.white);
        txtPrice.setBounds(160, 270, 220, 30);
        txtPrice.setEditable(false);
        secondborder.add(txtPrice);

        //----------------------------------------------------------------------thirdborder
        thirdborder.setLayout(null);
        thirdborder.setBackground(new Color(0x007bb4));
        thirdborder.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        thirdborder.setBounds(480, 80, 326, 348);
        border.add(thirdborder);
        
        lblCustomer.setForeground(Color.BLACK);
        lblCustomer.setFont(new Font("SourceSansPro", Font.BOLD, 16));
        lblCustomer.setBounds(20, 18, 150, 33);
        thirdborder.add(lblCustomer);
        
        bxCustomer.setBounds(110,20,200,25);
        bxCustomer.addActionListener(this);
        thirdborder.add(bxCustomer);
        
        lblName.setForeground(Color.BLACK);
        lblName.setFont(new Font("SourceSansPro", Font.BOLD, 16));
        lblName.setBounds(50, 130, 150, 33);
        thirdborder.add(lblName);
        txtName.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        txtName.setBackground(Color.white);
        txtName.setBounds(50, 160, 220, 30);
        txtName.setEditable(false);
        thirdborder.add(txtName);

        lblSurname.setForeground(Color.BLACK);
        lblSurname.setFont(new Font("SourceSansPro", Font.BOLD, 16));
        lblSurname.setBounds(50, 190, 150, 33);
        thirdborder.add(lblSurname);
        txtSurname.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        txtSurname.setBackground(Color.white);
        txtSurname.setBounds(50, 220, 220, 30);
        txtSurname.setEditable(false);
        thirdborder.add(txtSurname);

        btnTransact.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        btnTransact.setForeground(Color.BLACK);
        btnTransact.setBackground(new Color(0xffffff));
        btnTransact.setBounds(90, 260, 140, 35);
        btnTransact.addActionListener(this);
        thirdborder.add(btnTransact);

        btnBack.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        btnBack.setForeground(Color.BLACK);
        btnBack.setBackground(new Color(0xffffff));
        btnBack.setBounds(90, 303, 140, 35);
        btnBack.addActionListener(this);
        thirdborder.add(btnBack);

        //----------------------------------------------------------------------
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
    public void Icon() {

        ImageIcon userimage = new ImageIcon("Images\\realestate.png");
        lblIcon.setBounds(200, 10, 100, 56);

        Image img = userimage.getImage();
        Image imgScale = img.getScaledInstance(100, 56, Image.SCALE_SMOOTH);
        ImageIcon ScaledIcon = new ImageIcon(imgScale);
        lblIcon.setIcon(ScaledIcon);
        border.add(lblIcon);

    }
    public void IconTwo() {

        ImageIcon userimage = new ImageIcon("Images\\realestate.png");
        lblIconTwo.setBounds(620,  10, 100, 56);

        Image img = userimage.getImage();
        Image imgScale = img.getScaledInstance(100, 56, Image.SCALE_SMOOTH);
        ImageIcon ScaledIcon = new ImageIcon(imgScale);
        lblIconTwo.setIcon(ScaledIcon);
        border.add(lblIconTwo);

    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTransact) {
            //send request to client?

        }
        if(e.getSource() == bxCustomer){
            
           ;
        
        
        }
        if (e.getSource() == btnBack) {

            window.hide();
            AgentMenuGUI opn = new AgentMenuGUI();
            opn.startGUI();
            window.dispose();

        }
    }

   /* public static void main(String[] args) {
        new AgentRecordsTransactionsGUI().startGUI();
    }*/

}
