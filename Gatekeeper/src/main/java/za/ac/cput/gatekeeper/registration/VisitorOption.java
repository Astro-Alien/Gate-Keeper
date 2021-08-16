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
import java.util.Timer;
import java.util.TimerTask;
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
    private JFrame window;
    private JLabel lblThankYou;
    private JLabel lblEnjoy;

    public VisitorOption() {
        lblThankYou = new JLabel("                THANK YOU");
        lblEnjoy = new JLabel("           ENJOY YOUR DAY");
     
    }

    public void optionsGUI() {
        
        

        window = new JFrame();
        window.setSize(876, 497);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //----------------------------------------Initial borders
        JPanel border = new JPanel();
        border.setLayout(null);
        window.add(border);
        
        lblThankYou.setFont(new Font("SourceSansPro", Font.BOLD, 40));
        lblEnjoy.setFont(new Font("SourceSansPro", Font.BOLD, 40));
        
        //----------------------------------------Secondary borders
        
        JPanel imgPanel = new JPanel();
        
        imgPanel.setLayout(null);
 
        imgPanel.setBounds(40, 41, 785, 370);
        imgPanel.add(lblThankYou);
        imgPanel.add(lblEnjoy);
        lblThankYou.setBounds(100,20,700,300);
        lblEnjoy.setBounds(100,80,700,300);
        //add to initial panel
        border.add(imgPanel);
   
        JLabel lblUser = new JLabel("OPTION PANEL");
        
        lblUser.setBounds(60, 50, 210, 60);
        //----------------------------------------------------------------------Design
        //----------------------------------------JPanel and JFrame Design
        border.setBackground(new Color(0x005ba3));
        window.setBackground(new Color(0x005ba3));

        //----------------------------------------Secondary JPanel Designs
        imgPanel.setBackground(new Color(0x03a9f4));
        imgPanel.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));

        lblUser.setFont(new Font("SourceSansPro", Font.BOLD | Font.ITALIC, 25));
        lblUser.setForeground(Color.BLACK);
       
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

   public void Start(){
        optionsGUI();
        Timer time = new Timer();
        time.schedule(new TimerTask(){
            @Override
            public void run(){
                
                Main show = new Main();
                window.setVisible(false);
                show.startProgram();
                
            
            }
        
        
        }, 2000);
        
        
    
    }
    
}
