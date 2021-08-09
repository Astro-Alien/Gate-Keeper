/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.gatekeeper.registration;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Charles
 */
public class VisitorOption extends DbConnection {

    private JButton btnMeeting;
    private JButton btnInterview;
    private JButton btnVisitor;
    private JButton btnDelivery;
    private JButton btnContinue;
    private JButton btnReturn;

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet results = null;

    public VisitorOption() {

        btnMeeting = new JButton("Meeting");
        btnInterview = new JButton("Interview");
        btnVisitor = new JButton("Visit");
        btnDelivery = new JButton("Delivery");
        btnContinue = new JButton("Continue");
        btnReturn = new JButton("Return");
    }

    public void optionsGUI() {
        conn = DbConnection.ConnectDb();

        JFrame window = new JFrame();
        window.setSize(876, 497);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //----------------------------------------Initial borders
        JPanel border = new JPanel();
        border.setLayout(null);
        window.add(border);

        //----------------------------------------Secondary borders
        JPanel optionPanel = new JPanel();
        JPanel imgPanel = new JPanel();
        optionPanel.setLayout(null);
        imgPanel.setLayout(null);

        //layout and size
        imgPanel.setBounds(40, 41, 437, 370);
        optionPanel.setBounds(530, 41, 290, 370);

        //add to initial panel
        border.add(imgPanel);

        border.add(optionPanel);

        
        JLabel lblUser = new JLabel("OPTION PANEL");
        optionPanel.add(lblUser);
        //----------------------------------------Btn add to secondary panel optionPanel
        btnMeeting.setBounds(20, 160, 120, 50);
        btnInterview.setBounds(150, 160, 120, 50);
        btnDelivery.setBounds(20, 235, 120, 50);
        btnVisitor.setBounds(150, 235, 120, 50);
        btnContinue.setBounds(85, 310, 130, 33);
        optionPanel.add(btnMeeting);
        optionPanel.add(btnInterview);
        optionPanel.add(btnDelivery);
        optionPanel.add(btnVisitor);
        optionPanel.add(btnContinue);
        
        lblUser.setBounds(60, 50, 210, 60);
        //----------------------------------------------------------------------Design
        //----------------------------------------JPanel and JFrame Design
        border.setBackground(new Color(0x005ba3));
        window.setBackground(new Color(0x005ba3));

        //----------------------------------------Secondary JPanel Designs
        imgPanel.setBackground(new Color(0x03a9f4));
        imgPanel.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));

        optionPanel.setBackground(new Color(0x03a9f4));
        optionPanel.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));

        //----------------------------------------Button design
        btnMeeting.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        btnMeeting.setBackground(new Color(0x424242));
        btnMeeting.setForeground(Color.WHITE);

        btnInterview.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        btnInterview.setBackground(new Color(0x424242));
        btnInterview.setForeground(Color.WHITE);

        btnDelivery.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        btnDelivery.setBackground(new Color(0x424242));
        btnDelivery.setForeground(Color.WHITE);

        btnVisitor.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        btnVisitor.setBackground(new Color(0x424242));
        btnVisitor.setForeground(Color.WHITE);

        btnContinue.setBorder(BorderFactory.createLineBorder(new Color(0x424242), 3));
        btnContinue.setBackground(new Color(0xffffff));
        btnContinue.setForeground(Color.BLACK);
        
        lblUser.setFont(new Font("SourceSansPro", Font.BOLD | Font.ITALIC, 25));
        lblUser.setForeground(Color.BLACK);
        

        
                
        //----------------------------------------------------------------------Button hover methods
        //Hover colour change when the cursor hovers over the Login Jbutton
        btnMeeting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnMeeting.setBackground(new Color(0x005ba3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnMeeting.setBackground(new Color(0x424242));
            }
        });
        btnInterview.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnInterview.setBackground(new Color(0x005ba3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnInterview.setBackground(new Color(0x424242));
            }
        });
        btnDelivery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnDelivery.setBackground(new Color(0x005ba3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDelivery.setBackground(new Color(0x424242));
            }
        });
        btnVisitor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnVisitor.setBackground(new Color(0x005ba3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnVisitor.setBackground(new Color(0x424242));
            }
        });
        btnContinue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnContinue.setBackground(new Color(0x005ba3));
                btnContinue.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnContinue.setBackground(Color.WHITE);
                btnContinue.setForeground(Color.BLACK);

            }
        });

        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    public void Start(){
        optionsGUI();
        
    
    }
    /*public static void main(String[] args) {
        new VisitorOption().optionsGUI();
    }*/
}
