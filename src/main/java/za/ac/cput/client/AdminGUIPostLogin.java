/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import za.ac.cput.main.Main;
import za.ac.cput.server.Server;

/**
 *
 * @author Joshua Julies
 */
public class AdminGUIPostLogin implements ActionListener{
    
    //MAIN
    private JFrame mainFrame;
    private JFrame houseFrame;
    private JFrame updateHouseFrame;
    private JFrame agentFrame;
    private JPanel menuPanel;
    private JPanel menuPanel2;
    private JPanel holder;
    private JLabel menuLbl;
    private JPanel houseHolder;
    private JPanel housePanel;
    private JPanel housePanel2;
    private JPanel housePanel3;
    private JPanel houseHoldingPanel;
    private JPanel updateHouseHolder;
    private JPanel updateHousePanel;
    private JPanel updateHousePanel2;
    private JPanel updateHousePanel3;
    private JPanel updateHouseHoldingPanel;
    private JPanel agentHolder;
    private JPanel agentPanel;
    private JPanel agentPanel2;
    private JPanel agentPanel3;
    private JPanel agentHoldingPanel;
    private JButton addhouseBtn;
    private JButton updatehouseBtn;
    private JButton agentBtn;
    private JButton returnBtn;
    private Dimension prefSize = new Dimension(150, 30);
    
    //IMAGES
    private JLabel imgLbl;
    private JLabel imgLbl2;
    private JLabel imgLbl3;
    private JLabel imgLbl4;
    
    //HOUSE
    private JLabel houseLbl;
    private JLabel numRoomsLbl;
    private JTextField numRoomsText;
    private JLabel addressLbl;
    private JTextField addressText;
    private JLabel rentLbl;
    private JTextField rentText;
    private JLabel depositLbl;
    private JTextField depositText;
    private JLabel propertyLbl;
    private JComboBox propertyType; // the type of house: condo, apartment, house, townhouse?
    private JButton houseConfirmBtn;  
    private JButton houseReturnBtn;
    private boolean housePanelVisible = false;
       
    //UPDATE HOUSE
    private JLabel updatehouseLbl;
    private JLabel updatenumRoomsLbl;
    private JTextField updatenumRoomsText;
    private JLabel updateaddressLbl;
    private JTextField updateaddressText;
    private JLabel updaterentLbl;
    private JTextField updaterentText;
    private JLabel updatedepositLbl;
    private JTextField updatedepositText;
    private JLabel updatepropertyLbl;
    private JComboBox updatepropertyType; // the type of house: condo, apartment, house, townhouse?
    private JButton updatehouseConfirmBtn;  
    private JButton updatehouseReturnBtn;
    private JLabel specificHouseLbl;
    private JComboBox specificHouse;
    
    //AGENT 
    private JLabel agentLbl;
    private JLabel nameLbl;
    private JTextField nameText;
    private JLabel phoneNumLbl;
    private JTextField phoneNumText;
    private JLabel emailLbl;
    private JTextField emailText;
    private JButton agentConfirmBtn;
    private JButton agentReturnBtn;
    private boolean agentPanelVisible = false;
    
    //OBJECTS
    private Main main = new Main();
    private static AdminGUIPostLogin admin = new AdminGUIPostLogin();
    private AdmintClient client = new AdmintClient();
    
    
    public int flag;
    public String numRooms;
    public String address;
    public String rent;
    public String property;
    private String updateHouseBtnPressed;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        admin.AdminMenu();
    }
    
    
    public void AdminMenu(){
        mainFrame = new JFrame("Admin Client");
        houseFrame = new JFrame("Housing Details");
        updateHouseFrame = new JFrame("Update Housing Details");
        agentFrame = new JFrame("Agent Details");
        holder = new JPanel();
        
        ImageIcon userimage = new ImageIcon("Images\\realestate.png");
        Image img = userimage.getImage();
        Image newImg = img.getScaledInstance(450, 270, Image.SCALE_SMOOTH);
        imgLbl = new JLabel(new ImageIcon(newImg));
        imgLbl2 = new JLabel(new ImageIcon(newImg));
        imgLbl3 = new JLabel(new ImageIcon(newImg));
        imgLbl4 = new JLabel(new ImageIcon(newImg));
        
        menuLbl = new JLabel("Admin Menu");
        menuLbl.setFont(new Font("Sans", Font.BOLD, 30));
        menuPanel = new JPanel();
        menuPanel2 = new JPanel();
        addhouseBtn = new JButton("Enter house details");
        updatehouseBtn = new JButton("Update house details");
        agentBtn = new JButton("Enter agent details");
        returnBtn = new JButton("Return");
        addhouseBtn.addActionListener(this);
        updatehouseBtn.addActionListener(this);
        agentBtn.addActionListener(this);
        returnBtn.addActionListener(this);
        addhouseBtn.setBackground(Color.white);
        updatehouseBtn.setBackground(Color.white);
        agentBtn.setBackground(Color.white);
        returnBtn.setBackground(Color.white);
        addhouseBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        updatehouseBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        agentBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        returnBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        addhouseBtn.setPreferredSize(prefSize);
        updatehouseBtn.setPreferredSize(prefSize);
        agentBtn.setPreferredSize(prefSize);
        returnBtn.setPreferredSize(prefSize);
        
        //FRAME that holds all components
        //mainFrame.setLayout(new GridLayout(1, 1, 2, 2));
        mainFrame.setSize(850, 480);
        mainFrame.setEnabled(true);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLocation(350, 230);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //HOUSE
        houseFrame = new JFrame("Housing Details");
        housePanel = new JPanel();
        housePanel2 = new JPanel();
        housePanel3 = new JPanel();
        houseHoldingPanel = new JPanel();
        houseHolder = new JPanel();
        houseLbl = new JLabel("Housing Details");
        houseLbl.setFont(new Font("Sans", Font.BOLD, 25));
        numRoomsLbl = new JLabel("Number of Rooms: ");
        numRoomsText = new JTextField();
        addressLbl = new JLabel("Address: ");
        addressText = new JTextField();
        rentLbl = new JLabel("Rent: ");
        rentText = new JTextField();
        propertyLbl = new JLabel("Property type: ");
        propertyType = new JComboBox();
        propertyType.addItem("Family/Single-Family Home");
        propertyType.addItem("Town House");
        propertyType.addItem("Condominium");
        propertyType.addItem("Apartment");
        numRoomsText.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        addressText.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        rentText.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        
        houseConfirmBtn = new JButton("Confirm");
        houseReturnBtn = new JButton("Return");
        houseConfirmBtn.addActionListener(this);
        houseReturnBtn.addActionListener(this);
        houseConfirmBtn.setBackground(Color.white);
        houseReturnBtn.setBackground(Color.white);
        houseConfirmBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        houseReturnBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        houseConfirmBtn.setPreferredSize(prefSize);
        houseReturnBtn.setPreferredSize(prefSize);

        housePanel2.add(houseLbl);
        housePanel2.setLayout(new GridLayout(1,1));
        housePanel.add(numRoomsLbl);
        housePanel.add(numRoomsText);
        housePanel.add(addressLbl);
        housePanel.add(addressText);
        housePanel.add(rentLbl);
        housePanel.add(rentText);
        housePanel.add(propertyLbl);
        housePanel.add(propertyType);
        housePanel3.add(houseConfirmBtn);
        housePanel3.add(houseReturnBtn);
        housePanel3.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        housePanel.setLayout(new GridLayout(4, 2, 30, 30));        
        houseHoldingPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 4), BorderFactory.createEmptyBorder(40, 200, 40, 200)));
        houseHoldingPanel.setBackground(Color.decode("#007bb4"));
        houseHoldingPanel.setLayout(new BorderLayout(60, 60));
        houseHoldingPanel.add(housePanel2, BorderLayout.NORTH);
        houseHoldingPanel.add(housePanel, BorderLayout.CENTER);
        houseHoldingPanel.add(housePanel3, BorderLayout.SOUTH);
        housePanel2.setBackground(Color.decode("#007bb4"));
        housePanel.setBackground(Color.decode("#007bb4"));
        housePanel3.setBackground(Color.decode("#007bb4"));
        
        houseHolder.add(houseHoldingPanel);
        houseHolder.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        houseHolder.setBackground(Color.decode("#36d23b"));
        //houseFrame.add(imgLbl2);
        houseFrame.add(houseHolder);
        //houseFrame.setLayout(new GridLayout(1, 2));
        houseFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        houseFrame.setResizable(false);
        houseFrame.pack();
        houseFrame.setLocationRelativeTo(null);
        
        //UPDATE HOUSE
        updateHouseFrame = new JFrame("Housing Details");
        updateHousePanel = new JPanel();
        updateHousePanel2 = new JPanel();
        updateHousePanel3 = new JPanel();
        updateHouseHoldingPanel = new JPanel();
        updateHouseHolder = new JPanel();
        updatehouseLbl = new JLabel("Housing Details");
        updatehouseLbl.setFont(new Font("Sans", Font.BOLD, 25));
        updatenumRoomsLbl = new JLabel("Number of Rooms: ");
        updatenumRoomsText = new JTextField();
        updateaddressLbl = new JLabel("Address: ");
        updateaddressText = new JTextField();
        updaterentLbl = new JLabel("Rent: ");
        updaterentText = new JTextField();
        updatepropertyLbl = new JLabel("Property type: ");
        updatepropertyType = new JComboBox();
        updatepropertyType.addItem("Family/Single-Family Home");
        updatepropertyType.addItem("Town House");
        updatepropertyType.addItem("Condominium");
        updatepropertyType.addItem("Apartment");
        specificHouseLbl = new JLabel("House: ");
        specificHouse = new JComboBox();
        updatenumRoomsText.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        updateaddressText.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        updaterentText.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        updatehouseConfirmBtn = new JButton("Confirm");
        updatehouseReturnBtn = new JButton("Return");
        updatehouseConfirmBtn.addActionListener(this);
        updatehouseReturnBtn.addActionListener(this);
        updatehouseConfirmBtn.setBackground(Color.white);
        updatehouseReturnBtn.setBackground(Color.white);
        updatehouseConfirmBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        updatehouseReturnBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        updatehouseConfirmBtn.setPreferredSize(prefSize);
        updatehouseReturnBtn.setPreferredSize(prefSize);

        updateHousePanel2.add(houseLbl, BorderLayout.CENTER);
        updateHousePanel.add(numRoomsLbl);
        updateHousePanel.add(numRoomsText);
        updateHousePanel.add(addressLbl);
        updateHousePanel.add(addressText);
        updateHousePanel.add(rentLbl);
        updateHousePanel.add(rentText);
        updateHousePanel.add(propertyLbl);
        updateHousePanel.add(propertyType);
        updateHousePanel3.add(houseConfirmBtn);
        updateHousePanel3.add(houseReturnBtn);
        updateHousePanel3.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        updateHousePanel.setLayout(new GridLayout(4, 2, 30, 30));
        updateHouseHoldingPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 4), BorderFactory.createEmptyBorder(40, 200, 40, 200)));
        updateHouseHoldingPanel.setBackground(Color.decode("#007bb4"));
        updateHouseHoldingPanel.setLayout(new BorderLayout(60, 60));
        updateHouseHoldingPanel.add(updateHousePanel2, BorderLayout.NORTH);
        updateHouseHoldingPanel.add(updateHousePanel, BorderLayout.CENTER);
        updateHouseHoldingPanel.add(updateHousePanel3, BorderLayout.SOUTH);
        updateHousePanel2.setBackground(Color.decode("#007bb4"));
        updateHousePanel.setBackground(Color.decode("#007bb4"));
        updateHousePanel3.setBackground(Color.decode("#007bb4"));
        
        updateHouseHolder.add(houseHoldingPanel);
        updateHouseHolder.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        updateHouseHolder.setBackground(Color.decode("#36d23b"));
        updateHouseFrame.add(imgLbl2);
        updateHouseFrame.add(houseHolder);
        updateHouseFrame.setLayout(new GridLayout(1, 2));
        //updateHouseFrame.setLocation(330, 200);
        updateHouseFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //updateHouseFrame.setEnabled(true);
        updateHouseFrame.setResizable(false);
        updateHouseFrame.pack();
        updateHouseFrame.setLocationRelativeTo(null);
                
        //AGENT
        agentFrame = new JFrame("Agent Details");
        agentHolder = new JPanel();
        agentPanel = new JPanel();
        agentPanel2 = new JPanel();
        agentPanel3 = new JPanel();
        agentHoldingPanel = new JPanel();
        agentLbl = new JLabel("Agent Details");
        agentLbl.setFont(new Font("Sans", Font.BOLD, 25));
        nameLbl = new JLabel("Name: ");
        nameText = new JTextField();
        phoneNumLbl = new JLabel("Contact number: ");
        phoneNumText = new JTextField();
        emailLbl = new JLabel("E-mail address: ");
        emailText = new JTextField();
        nameText.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        phoneNumText.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        emailText.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        
        agentConfirmBtn = new JButton("Confirm");
        agentReturnBtn = new JButton("Return");
        agentConfirmBtn.addActionListener(this);
        agentReturnBtn.addActionListener(this);
        agentConfirmBtn.setBackground(Color.white);
        agentReturnBtn.setBackground(Color.white);
        agentConfirmBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        agentReturnBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        agentConfirmBtn.setPreferredSize(prefSize);
        agentReturnBtn.setPreferredSize(prefSize);
        
        agentPanel2.add(agentLbl);
        agentPanel2.setBackground(Color.decode("#007bb4"));
        agentPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        //agentPanel2.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        //agentHoldingPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
        agentPanel.add(nameLbl);
        agentPanel.add(nameText);
        agentPanel.add(phoneNumLbl);
        agentPanel.add(phoneNumText);
        agentPanel.add(emailLbl);
        agentPanel.add(emailText);
        agentPanel3.add(agentConfirmBtn);
        agentPanel3.add(agentReturnBtn);
        agentPanel3.setBackground(Color.decode("#007bb4"));
        agentPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        agentPanel.setLayout(new GridLayout(3, 2, 90, 30));
        agentPanel.setBackground(Color.decode("#007bb4"));
        agentHoldingPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 4), BorderFactory.createEmptyBorder(20, 50, 20, 50)));
        agentHoldingPanel.setBackground(Color.decode("#007bb4"));
        agentHoldingPanel.setLayout(new BorderLayout(60, 60));
        agentHoldingPanel.add(agentPanel2, BorderLayout.NORTH);
        agentHoldingPanel.add(agentPanel, BorderLayout.CENTER);
        agentHoldingPanel.add(agentPanel3, BorderLayout.SOUTH);
        agentHolder.add(imgLbl3);
        agentHolder.add(agentHoldingPanel);
        agentHolder.setBackground(Color.decode("#36d23b"));
        agentHolder.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        agentFrame.add(agentHolder);
        agentFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        agentFrame.setEnabled(true);
        agentFrame.setResizable(false);
        agentFrame.pack();
        agentFrame.setLocationRelativeTo(null);
        
        //PANEL that hold the housing details and agent details buttons 
        //menuPanel.add(menuLbl);
        menuPanel2.add(menuLbl);
        menuPanel2.setBackground(Color.decode("#007bb4"));
        menuPanel.add(menuPanel2);
        menuPanel.add(addhouseBtn);
        menuPanel.add(updatehouseBtn);
        menuPanel.add(agentBtn);
        menuPanel.add(returnBtn);
        menuPanel.setLayout(new GridLayout(5, 1, 30, 30));
        menuPanel.setEnabled(true);
        menuPanel.setSize(800, 400);
        menuPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 4), BorderFactory.createEmptyBorder(80, 50, 80, 50)));
        menuPanel.setBackground(Color.decode("#007bb4"));
        
        holder.add(imgLbl);
        holder.add(menuPanel);
        holder.setBorder(BorderFactory.createEmptyBorder(40, 40, 20, 40));
        holder.setBackground(Color.decode("#36d23b"));
        
        mainFrame.setResizable(false);
        mainFrame.add(holder);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
    }
    
    private void AddHouseDetails(){
        housePanel.setEnabled(true);
        housePanel.setVisible(true);
        houseFrame.setEnabled(true);
        houseFrame.setVisible(true);
        houseHolder.setVisible(true);
        houseHolder.setEnabled(true);
    }
    
    private void UpdateHouseDetails(){
        updatepropertyType.setSelectedIndex(1);
        updateHousePanel.setEnabled(true);
        updateHousePanel.setVisible(true);
        updateHouseFrame.setEnabled(true);
        updateHouseFrame.setVisible(true);
        updateHouseHolder.setVisible(true);
        updateHouseHolder.setEnabled(true);
    }
    
    private void AgentDetails(){
        agentPanel.setEnabled(true);
        agentPanel.setVisible(true);
        agentFrame.setEnabled(true);
        agentFrame.setVisible(true);
    }
    
    private void Close(){
        mainFrame.setVisible(false);
        houseFrame.setVisible(false);
        agentFrame.setVisible(false);
        mainFrame.dispose();
        houseFrame.dispose();
        agentFrame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == houseReturnBtn){
            mainFrame.setVisible(true);
            agentFrame.setVisible(false);
            houseFrame.setVisible(false);
        } 
        if(e.getSource() == agentReturnBtn){
            mainFrame.setVisible(true);
            agentFrame.setVisible(false);
            houseFrame.setVisible(false);
        }
        if(e.getSource() == houseConfirmBtn){
            flag = 1;
            
            //send house information to the server
            if(numRoomsText != null || addressText != null || rentText != null || depositText != null || propertyType.getSelectedItem() != null){
                String rooms = numRoomsText.toString(); 
                String addr = addressText.toString();
                String rent = rentText.toString();
                String prop = propertyType.getSelectedItem().toString();
                client.AddHouse(rooms, addr, rent, prop);
            }
        } 
        if(e.getSource() == agentConfirmBtn){
            flag = 2;
            
            //send agent information over to the server
            if(nameText != null || phoneNumText != null || emailText != null){
                String name = nameText.toString(); 
                String num = phoneNumText.toString();
                String email = emailText.toString();
                //client.AddAgent(name, num, email);
            }
        }
        if(e.getSource() == addhouseBtn){            
            mainFrame.setVisible(false);
            agentFrame.setVisible(false);
            updateHouseFrame.setVisible(false);
            AddHouseDetails();
        } 
        if(e.getSource() == updatehouseBtn){        
            mainFrame.setVisible(false);
            agentFrame.setVisible(false);
            houseFrame.setVisible(false);
            UpdateHouseDetails();
            
            flag = 3;
            
            updateHouseBtnPressed = "true";
            //client.GetHouse(updateHouseBtnPressed);
            if(client.numRooms != null || client.address != null || client.rent != null || client.propertyType != null){
                updatenumRoomsText.setText(client.numRooms); 
                updateaddressText.setText(client.address);
                updaterentText.setText(client.rent);
                updatepropertyType.setSelectedItem(client.propertyType);
            }
        }
        if(e.getSource() == agentBtn){
            mainFrame.setVisible(false);
            houseFrame.setVisible(false);
            updateHouseFrame.setVisible(false);
            AgentDetails();
        } 
        if(e.getSource() == returnBtn){
            Close();
            main.startProgram();
        }
    }
    
}
