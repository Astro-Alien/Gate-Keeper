/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import za.ac.cput.GUI.AgentMenuGUI;

import za.ac.cput.main.Main;

/**
 *
 * @author Charles
 */
public class ClientAgent implements ActionListener {

    private String agentUsername;
    private String agentPassword;

    private JFrame window;
    private JPanel border;
    private JPanel secondBorder;
    private JPanel images;
    private JLabel label;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblPassword;
    private JPasswordField pwdPassword;
    //Buttons
    private JButton btnLogin;
    private JButton btnReturn;
    //header
    private JLabel lblHeader;
    //Icon
    private JLabel lblIcon;
    String acceptanceMessage;
    public String customerName;

    public ClientAgent() {
        window = new JFrame("Information Management System");
        border = new JPanel();
        secondBorder = new JPanel();
        images = new JPanel();

        lblHeader = new JLabel("Real Estate Agent Login");
        lblIcon = new JLabel();
        lblUsername = new JLabel("Username");
        txtUsername = new JTextField(16);

        lblPassword = new JLabel("Password");
        pwdPassword = new JPasswordField(16);

        btnLogin = new JButton("LOGIN");
        btnReturn = new JButton("BACK");
    }

    public void startLoginGUI() {
        window.setSize(850, 480);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        border.setLayout(null);
        window.add(border);

        border.add(secondBorder);
        secondBorder.setBounds(500, 30, 315, 380);
        secondBorder.setLayout(null);
        //----------------------------------------------------------------------JPanel design
        border.setBackground(new Color(0x36d23b));
        secondBorder.setBackground(new Color(0x007bb4));
        secondBorder.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        images.setBackground(new Color(0x36d23b));

        //----------------------------------------------------------------------JPanel Images
        border.add(images);
        label = new JLabel();
        border.add(label);
        scalingImg();

        //icon
        secondBorder.add(lblIcon);
        Icon();
        //----------------------------------------------------------------------JLabel Design
        lblUsername.setBounds(47, 155, 150, 40);
        secondBorder.add(lblUsername);
        txtUsername.setBounds(47, 190, 220, 30);
        secondBorder.add(txtUsername);

        lblHeader.setFont(new Font("SourceSansPro", Font.BOLD, 25));
        lblHeader.setForeground(Color.BLACK);
        lblHeader.setBounds(15, 5, 290, 60);
        secondBorder.add(lblHeader);
        //---------------------------------------------------positioning Password label and textfield
        lblPassword.setBounds(47, 215, 200, 40);
        secondBorder.add(lblPassword);
        pwdPassword.setBounds(47, 250, 220, 30);
        secondBorder.add(pwdPassword);

        //---------------------------------------------------positioning login button and adding action listener
        btnLogin.setBounds(92, 290, 130, 33);
        btnLogin.addActionListener(this);
        secondBorder.add(btnLogin);

        //---------------------------------------------------positioning  Submit button 
        btnReturn.setBounds(92, 330, 130, 33);
        btnReturn.addActionListener(this);
        secondBorder.add(btnReturn);

        //---------------------------------------------------Design JLabel
        lblUsername.setFont(new Font("SourceSansPro", Font.BOLD, 18));
        lblUsername.setForeground(Color.BLACK);
        lblPassword.setFont(new Font("SourceSansPro", Font.BOLD, 18));
        lblPassword.setForeground(Color.BLACK);
        txtUsername.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        pwdPassword.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        txtUsername.setBackground(new Color(0xffffff));
        pwdPassword.setBackground(new Color(0xffffff));
        txtUsername.setForeground(Color.BLACK);
        pwdPassword.setForeground(Color.BLACK);
        txtUsername.setCaretColor(Color.BLACK);
        txtUsername.setCaretColor(Color.BLACK);
        pwdPassword.setCaretColor(Color.BLACK);

        //---------------------------------------------------Design JButton
        btnLogin.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        btnReturn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        btnLogin.setBackground(new Color(0xffffff));
        btnLogin.setForeground(Color.BLACK);
        btnReturn.setBackground(new Color(0xffffff));
        btnReturn.setForeground(Color.BLACK);

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    public void ClientLoginVerification(String agentUsername, String agentPassword) {
        String username = agentUsername;
        String password = agentPassword;

        //create a socket connection
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", 1234);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            while (true) {

                //message that will be sent to the server
                String check = "Login";
                String namemsg = username;
                String passwordmsg = password;

                bufferedWriter.write(check);
                bufferedWriter.newLine();

                bufferedWriter.write(namemsg);
                bufferedWriter.newLine();

                bufferedWriter.write(passwordmsg);
                bufferedWriter.newLine();
                //this flushes the writer when the buffer is full
                bufferedWriter.flush();

                //System.out.println("Server: " + bufferedReader.readLine());
                String message = bufferedReader.readLine();
                System.out.println("Message from server: " + message);
                String acc = "accepted";
                String den = "denied";
                if (message.equals(acc)) {
                    window.hide();
                    AgentMenuGUI opn = new AgentMenuGUI();
                    opn.startGUI();
                    window.dispose();
                } else if (txtUsername.getText().isEmpty() && pwdPassword.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Please enter the missing values 'Username and Password'");
                } else if (message.equals(den)) {

                    JOptionPane.showMessageDialog(null, "Invalid Username/Password");
                    txtUsername.setText("");
                    pwdPassword.setText("");
                } else {
                    System.out.println("I tried");

                }
                //acceptanceMessage = bufferedReader.readLine();
                break;

                /*if(msg.equalsIgnoreCase("BYE")){
                
                    break;
                
                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }

            } catch (IOException e) {

                e.printStackTrace();

            }
        }

    }

    public void ClientRegisterCustomer(String ID, String Name, String Surname, String mobileNumber) {
        String name = Name;
        String surname = Surname;
        String id = ID;
        String mobilenumber = mobileNumber;

        //create a socket connection
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", 1234);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            while (true) {

                //message that will be sent to the server
                String createAcc = "createAccount";
                String idmsg = id;
                String namemsg = name;
                String surnamemsg = surname;
                String mobilemsg = mobilenumber;

                bufferedWriter.write(createAcc);
                bufferedWriter.newLine();
                bufferedWriter.write(idmsg);
                bufferedWriter.newLine();

                bufferedWriter.write(namemsg);
                bufferedWriter.newLine();

                bufferedWriter.write(surnamemsg);
                bufferedWriter.newLine();

                bufferedWriter.write(mobilemsg);
                bufferedWriter.newLine();
                //this flushes the writer when the buffer is full
                bufferedWriter.flush();

                String message = bufferedReader.readLine();
                System.out.println("Message from server: " + message);
                String accCreated = "record created";
                String denCreated = "record not created";

                if (message.equals(accCreated)) {

                    JOptionPane.showMessageDialog(null, "New Customer Record Saved");

                }
                else{

                    JOptionPane.showMessageDialog(null, "New Customer Record Not Saved");
                }

                break;

            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }

            } catch (IOException e) {

                e.printStackTrace();

            }
        }

    }
    public void populateCustomerBox() {
        
        System.out.println("Code is being called");
        //create a socket connection
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", 1234);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            while (true) {

                //message that will be sent to the server
                String starter = "Start";
                

                bufferedWriter.write(starter);
                bufferedWriter.newLine();

                //this flushes the writer when the buffer is full
                bufferedWriter.flush();

               //the value that is being sent back needs to be assigned to the combobox in agenttransaction form
                String message0 = bufferedReader.readLine();
                customerName = bufferedReader.readLine();
                
                
                
                
                System.out.println("this is being sent back: "+ message0);
            
                
                
                
                break;

                /*if(msg.equalsIgnoreCase("BYE")){
                
                    break;
                
                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }

            } catch (IOException e) {

                e.printStackTrace();

            }
        }

    }
    public void scalingImg() {

        images.setBounds(20, 60, 467, 320);

        ImageIcon icon = new ImageIcon("Images\\realestate.png");
        label.setLocation(50, 2);
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(467, 296, Image.SCALE_SMOOTH);
        ImageIcon ScaledIcon = new ImageIcon(imgScale);
        label.setIcon(ScaledIcon);
        images.add(label);

    }

    public void Icon() {

        ImageIcon userimage = new ImageIcon("Images\\icon.png");
        lblIcon.setBounds(60, 23, 190, 170);

        Image img = userimage.getImage();
        Image imgScale = img.getScaledInstance(190, 170, Image.SCALE_SMOOTH);
        ImageIcon ScaledIcon = new ImageIcon(imgScale);
        lblIcon.setIcon(ScaledIcon);
        secondBorder.add(lblIcon);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String username = txtUsername.getText();
            String password = pwdPassword.getText();
            ClientLoginVerification(username, password);

            //call client method in here to send the username and password to the server where it will be sent 
            //to the crud operation method to check if the information in valid in the database
        }
        if (e.getSource() == btnReturn) {

            Main opn = new Main();
            opn.startProgram();
            window.dispose();

        }

    }

    public static void main(String[] args) {
        new ClientAgent().startLoginGUI();
    }
}
