/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.gatekeeper.registration.Database;


/**
 *
 * @author Brandon
 */

import java.util.*;
import java.awt.*;
import static java.awt.SystemColor.window;
import java.awt.event.*;
import javax.swing.*;
import za.ac.cput.gatekeeper.registration.Main;

/**
 * GUI Class
 */
public class InventoryView extends JFrame implements ActionListener {
    
    //Inventory controller
    private InventoryController controller;
    
    //Declare Components
    private JLabel lblSelect;
    private JLabel lblVisitor_ID;
    private JLabel lblMobileNumber;
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblCompany;
    
    //Text Fields
    private JTextField txtVisitor_ID;
    private JTextField txtMobileNumber;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtCompany;
    
    //ComboBox
    private JComboBox<Integer>cboVisitors;
    
    //Buttons
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnDisplay;
    private JButton btnClose;
    private JButton btnReturn;
    
    //Display Area
    private JTextArea txtDisplay;
    private JTable tableDisplay;
    private JScrollPane pane;
    
    
    
    //Panels
    private JPanel panelSelect;
    private JPanel panelInputs;
    private JPanel displayTable;
    
    /**
     * 
     * Constructor to setup the GUI. 
     */
    public InventoryView(){
        
        //Setup Frame Features
        this.setTitle("Inventory Management System");
        this.setSize(860, 500);
        this.setPreferredSize(new Dimension(800, 700));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        //Setup Panel with GUI components
        
        setupSelectPanel();
        
        //Input Panel
        setupInputPanel();
        
        buttonPanel();
        
        displayTable();

        //Output Area
       
        this.txtDisplay = new JTextArea();
        Font font = new Font("Courier New", Font.PLAIN, 14);
        this.txtDisplay.setFont(font);
        this.pane = new JScrollPane(this.txtDisplay);
        this.pane.setPreferredSize(new Dimension(670, 300));
        this.pane.setAutoscrolls(true);
        this.add(this.pane);
        
        //Object
        this.controller = new InventoryController();
        
        //Load Ids
        this.loadVisitors();
        
        //Pack the gui
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    /**
     * Input panel and components
     */
    
    public void setupInputPanel(){
     
       this.panelInputs = new JPanel();  
       this.panelInputs.setPreferredSize(new Dimension(670, 120));
       this.panelInputs.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
       this.panelInputs.setBorder(BorderFactory.createTitledBorder("Manage Visitors"));
       this.add(this.panelInputs);
        
       //Components for inputs
       
       //Labels and text
       
       this.lblVisitor_ID = new JLabel("Visitor ID: ");
       this.lblVisitor_ID.setPreferredSize(new Dimension(150, 25));
       this.panelInputs.add(this.lblVisitor_ID);
       
       this.txtVisitor_ID = new JTextField();
       this.txtVisitor_ID.setPreferredSize(new Dimension(150, 25));
       this.panelInputs.add(this.txtVisitor_ID);
       
       this.lblMobileNumber = new JLabel("Mobile: ");
       this.lblMobileNumber.setPreferredSize(new Dimension(150, 25));
       this.panelInputs.add(this.lblMobileNumber);
       
       this.txtMobileNumber = new JTextField();
       this.txtMobileNumber.setPreferredSize(new Dimension(150, 25));
       this.panelInputs.add(this.txtMobileNumber);
       
       this.lblFirstName = new JLabel("First Name: ");
       this.lblFirstName.setPreferredSize(new Dimension(150, 25));
       this.panelInputs.add(this.lblFirstName);

       this.txtFirstName = new JTextField();
       this.txtFirstName.setPreferredSize(new Dimension(150, 25));
       this.panelInputs.add(this.txtFirstName);
       
       this.lblLastName = new JLabel("Last Name: ");
       this.lblLastName.setPreferredSize(new Dimension(150, 25));
       this.panelInputs.add(this.lblLastName);
       
       this.txtLastName = new JTextField();
       this.txtLastName.setPreferredSize(new Dimension(150, 25));
       this.panelInputs.add(this.txtLastName);
  
       this.lblCompany = new JLabel("Company: ");
       this.lblCompany.setPreferredSize(new Dimension(150, 25));
       this.panelInputs.add(this.lblCompany);
       
       this.txtCompany = new JTextField();
       this.txtCompany.setPreferredSize(new Dimension(150, 25));
       this.panelInputs.add(this.txtCompany);
       
       
    }
       //Buttons
       
       public void buttonPanel(){
     
       this.panelInputs = new JPanel();  
       this.panelInputs.setPreferredSize(new Dimension(670, 80));
       this.add(this.panelInputs);
       
       this.btnAdd = new JButton("Create User Record");
       this.btnAdd.setPreferredSize(new Dimension(180, 25));
       this.btnAdd.addActionListener(this);
       this.panelInputs.add(this.btnAdd);
       
       this.btnEdit = new JButton("Modify Record");
       this.btnEdit.setPreferredSize(new Dimension(180, 25));
       this.btnEdit.addActionListener(this);
       this.panelInputs.add(this.btnEdit);
       
       this.btnDelete = new JButton("Delete Record");
       this.btnDelete.setPreferredSize(new Dimension(180, 25));
       this.btnDelete.addActionListener(this);
       this.panelInputs.add(this.btnDelete);
       
       this.btnDisplay = new JButton("Display All Records");
       this.btnDisplay.setPreferredSize(new Dimension(180, 25));
       this.btnDisplay.addActionListener(this);
       this.panelInputs.add(this.btnDisplay);
       
       this.btnReturn = new JButton("Return");
       this.btnReturn.setPreferredSize(new Dimension(180, 25));
       this.btnReturn.addActionListener(this);
       this.panelInputs.add(this.btnReturn);
       
       this.btnClose = new JButton("Close");
       this.btnClose.setPreferredSize(new Dimension(180, 25));
       this.btnClose.addActionListener(this);
       this.panelInputs.add(this.btnClose);
       
    }
       
       public void displayTable(){
           
       }
    
    //Edit an existing Visitor Record
    private void editVisitor(){
        
    //Selection
    int idx = this.cboVisitors.getSelectedIndex();
    
    if(idx >=0) {
        
        try{
            int id = Integer.parseInt(this.txtVisitor_ID.getText());
            String mobile = this.txtMobileNumber.getText();
            String fName = this.txtFirstName.getText();
            String lName = this.txtLastName.getText();
            String company = (this.txtCompany.getText());
            
            //Visitor Object
            Visitor visitor = new Visitor(id, mobile, fName, lName, company);
            
            //Add by calling controller
            if(controller.editVisitor(visitor)){
                displayInfo("Visitor record has been updated");        
            } else {
                displayError("Visitor record has not been updated");
                
            }
            this.loadVisitors();
            resetForm();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    
    }else{
        this.displayError("Please select a Visitor record");
    } 
    }
    
    /**
     * Add new Visitor into the database
     */
    private void addVisitor(){
        
        try{
            int id = 0;
            String mobile = this.txtMobileNumber.getText();
            String fName = this.txtFirstName.getText();
            String lName = this.txtLastName.getText();
            String company = (this.txtCompany.getText());
            
            //Visitor Object
            Visitor visitor = new Visitor(id, mobile, fName, lName, company);
            
            //Add by calling controller
            if(controller.addVisitor(visitor)){
                displayInfo("Visitor successfully registered.");        
            } else {
                displayError("Error visitor record could not be created.");
                
            }
            this.loadVisitors();
            resetForm();
        }catch(Exception e){
            e.printStackTrace();
        }
      }
    // Alert Message    
    public void displayError(String message){
            JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.ERROR_MESSAGE);    
      }
    
    // Alert Message    
    public void displayInfo(String message){
            JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);    
      }
    
       /**
        * This function will load all the visitor IDs into the ComboBox.
        */
       private void loadVisitors(){
        
           //Clean
           this.cboVisitors.removeAllItems();
           
           //Add
           ArrayList<Visitor> visitors = this.controller.getVisitors();
           for(Visitor v: visitors) {
               this.cboVisitors.addItem(v.getVisitorID());
           }
           
       }
       /**
        * Clear the form
        */
       
       private void resetForm(){
           this.txtDisplay.setText("");
           this.txtCompany.setText("");
           this.txtMobileNumber.setText("");
           this.txtVisitor_ID.setText("");
           this.txtFirstName.setText("");
           this.txtLastName.setText("");
       }
       
    
    
    /**
     * Create Visitor Selection Panel
     */
    
    public void setupSelectPanel(){
       this.panelSelect = new JPanel();  
       this.panelSelect.setPreferredSize(new Dimension(670, 95));
       this.panelSelect.setBorder(BorderFactory.createTitledBorder("Visitor DBMS"));
       this.add(this.panelSelect);
       
       //JLabels
       this.lblSelect = new JLabel("Select a Visitor Record");
       this.lblSelect.setPreferredSize(new Dimension(200, 30));
       this.panelSelect.add(this.lblSelect);
       
       //JComboBox
       this.cboVisitors = new JComboBox<Integer>();
       this.cboVisitors.setPreferredSize(new Dimension(50, 30));
       this.cboVisitors.addActionListener(this);
       this.panelSelect.add(this.cboVisitors);
    }
    /**
     * Select and display visitor data into TextFields
     */
    
    private void selectVisitor(){
       
        // Index
        int idx = this.cboVisitors.getSelectedIndex();
        
        if(idx >= 0){
            int id = Integer.parseInt(this.cboVisitors.getSelectedItem().toString());
            Visitor visitor = controller.getVisitor(id);
            
            if(visitor != null) {
                this.txtVisitor_ID.setText(String.valueOf(visitor.getVisitorID()));
                this.txtMobileNumber.setText(visitor.getMobileNumber());
                this.txtFirstName.setText(visitor.getFirstName());
                this.txtLastName.setText(String.valueOf(visitor.getLastName()));
                this.txtCompany.setText(String.valueOf(visitor.getCompany()));
            }
        }
    }
    
    private void deleteVisitor(){
        //Index
        int idx = this.cboVisitors.getSelectedIndex(); 
        if(idx >= 0){
            
            int id = Integer.parseInt(this.cboVisitors.getSelectedItem().toString());
            if(controller.deleteVisitor(id)) {
                displayInfo("Visitor record has been deleted");
            } else {
                displayError("Unable to delete visitor record");
            }
            this.loadVisitors();
            this.resetForm();
        }else{
            this.displayError("Please select a visitor");
        }
    }
    //Display all the products
    private void displayVisitors(){
     this.txtDisplay.setText(controller.getVisitorsAsString());
    }
    
    @Override
    /**
     * Event handler
     */
    
    public void actionPerformed(ActionEvent event) {
        // Implement later
        Object src = event.getSource();
        
        
        if (src.equals(this.btnReturn)) {
            Main rg = new Main();
            rg.startProgram();
            dispose();
        }
        
        if(src.equals(this.cboVisitors)){
          selectVisitor();  
        }else if(src.equals(this.btnAdd)) {
           addVisitor(); 
        }else if(src.equals(this.btnEdit)) {
            this.editVisitor();
        }else if(src.equals(this.btnDelete)) {
            this.deleteVisitor();
        }else if(src.equals(btnClose)) {
            System.exit(0);
        }else if(src.equals(btnDisplay)){
           displayVisitors(); 
        }
        
    }
    
    
}