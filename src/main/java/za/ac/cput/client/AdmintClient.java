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
import java.net.*;
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
import za.ac.cput.main.Main;

import za.ac.cput.server.Server;

/**
 *
 * @author Joshua
 */
public class AdmintClient implements ActionListener {

    //private String adminUsername;
    //private String adminPassword;
    public String numRooms;
    public String address;
    public String rent;
    public String deposit;
    public String propertyType;
    //-------------------GUI
    private JFrame window;
    private JPanel border;
    private JPanel outline;
    private JPanel images;
    private JLabel label;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblPassword;
    private JPasswordField pwdPassword;
    private JButton btnLogin;
    private JButton btnReturn;
    private JLabel lblHeader;
    private JLabel lblIcon;

    private Server serv = new Server();
    private SocketAddress addr = new InetSocketAddress("localhost", 1234);
    private Socket socket = null;

    public AdmintClient() {
        window = new JFrame("Information Management System");
        border = new JPanel();
        outline = new JPanel();
        images = new JPanel();

        lblHeader = new JLabel("Administrator Login");
        lblIcon = new JLabel();
        lblUsername = new JLabel("Username");
        txtUsername = new JTextField(16);

        lblPassword = new JLabel("Password");
        pwdPassword = new JPasswordField(16);

        btnLogin = new JButton("LOGIN");
        btnReturn = new JButton("BACK");
    }
    public void startGUI() {
        window.setSize(850, 480);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        border.setLayout(null);
        window.add(border);

        border.add(outline);
        outline.setBounds(500, 30, 315, 380);
        outline.setLayout(null);
        //----------------------------------------------------------------------JPanel design
        border.setBackground(new Color(0x36d23b));
        outline.setBackground(new Color(0x007bb4));
        outline.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        images.setBackground(new Color(0x36d23b));

        //----------------------------------------------------------------------JPanel Images
        border.add(images);
        label = new JLabel();
        border.add(label);
        scalingImg();

        //icon
        outline.add(lblIcon);
        iconImg();
        //----------------------------------------------------------------------JLabel Design
        lblUsername.setBounds(47, 155, 150, 40);
        outline.add(lblUsername);
        txtUsername.setBounds(47, 190, 220, 30);
        outline.add(txtUsername);

        lblHeader.setFont(new Font("SourceSansPro", Font.BOLD, 25));
        lblHeader.setForeground(Color.BLACK);
        lblHeader.setBounds(40, 2, 250, 60);
        outline.add(lblHeader);
        //---------------------------------------------------positioning Password label and textfield
        lblPassword.setBounds(47, 215, 200, 40);
        outline.add(lblPassword);
        pwdPassword.setBounds(47, 250, 220, 30);
        outline.add(pwdPassword);

        //---------------------------------------------------positioning login button and adding action listener
        btnLogin.setBounds(92, 290, 130, 33);
        btnLogin.addActionListener(this);
        outline.add(btnLogin);

        //---------------------------------------------------positioning  Submit button 
        btnReturn.setBounds(92, 330, 130, 33);
        btnReturn.addActionListener(this);
        outline.add(btnReturn);

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
    public void AdminVerification(String adminUsername, String adminPassword) {
        System.out.println("Client is connecting to server now...");
        //create a socket connection
        Socket socket = null;
        InputStream input;
        OutputStream output;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", 1234);
            //socket.connect(addr);
            
            input = socket.getInputStream();
            output = socket.getOutputStream();

            inputStreamReader = new InputStreamReader(input);
            outputStreamWriter = new OutputStreamWriter(output);

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            while (socket.isClosed() == false) {
                System.out.println("Client is sending information to the server...\n");

                //serv.AcceptClient();
                //message that will be sent to the server
                String userinput = "AdminLogin";
                String namemsg = adminUsername;
                String passwordmsg = adminPassword;

                bufferedWriter.write(userinput);
                bufferedWriter.newLine();

                bufferedWriter.write(namemsg);
                bufferedWriter.newLine();

                bufferedWriter.write(passwordmsg);
                bufferedWriter.newLine();

                
                //this flushes the writer when the buffer is full
                bufferedWriter.flush();
                
                //message received from server
                String message = bufferedReader.readLine();
                System.out.println("Message from server: " + message);
                String acc = "accepted";
                String den = "denied";
                if (message.equals(acc)) {
                    window.hide();
                    AdminGUIPostLogin opn = new  AdminGUIPostLogin();
                    opn.AdminMenu();
                    window.dispose();
                } else if (txtUsername.getText().isEmpty() && pwdPassword.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Please enter the missing values 'Username and Password'");
                    
                } else if (message.equals(den)) {

                    JOptionPane.showMessageDialog(null, "Invalid Username/Password");
                    txtUsername.setText("");
                    pwdPassword.setText("");
                    
                } else {
                    System.out.println("Failed Completely");

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
    public void AddHouse(String numRooms, String address, String rent, String propertyType) {
        System.out.println("Client is connecting to server now...");
        String num = numRooms;
        String adr = address;
        String rnt = rent;
        String prop = propertyType;

        //create a socket connection
        //Socket socket = null;
        InputStream input;
        OutputStream output;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", 1234);
            //socket.connect(addr);

            input = socket.getInputStream();
            output = socket.getOutputStream();

            inputStreamReader = new InputStreamReader(input);
            outputStreamWriter = new OutputStreamWriter(output);

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            while (socket.isClosed() == false) {
                System.out.println("Client is sending information to the server...\n");

                //message that will be sent to the server
                String nummsg = num;
                String addressmsg = adr;
                String rentmsg = rnt;
                String propertymsg = prop;

                bufferedWriter.write(nummsg + "\n");
                bufferedWriter.write(addressmsg + "\n");
                bufferedWriter.write(rentmsg + "\n");
                bufferedWriter.write(propertymsg + "\n");

                //this flushes the writer when the buffer is full
                bufferedWriter.flush();

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
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
    public void iconImg() {

        ImageIcon userimage = new ImageIcon("Images\\icon.png");
        lblIcon.setBounds(60, 23, 190, 170);

        Image img = userimage.getImage();
        Image imgScale = img.getScaledInstance(190, 170, Image.SCALE_SMOOTH);
        ImageIcon ScaledIcon = new ImageIcon(imgScale);
        lblIcon.setIcon(ScaledIcon);
        outline.add(lblIcon);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            
            String user = txtUsername.getText();
            String pass = new String(pwdPassword.getPassword());
            AdminVerification(user, pass);
            
   

            //window.dispose();
            //admin.AdminMenu();
        }
        if (e.getSource() == btnReturn) {

            Main opn = new Main();
            opn.startProgram();
            window.dispose();

        }

    }
}
