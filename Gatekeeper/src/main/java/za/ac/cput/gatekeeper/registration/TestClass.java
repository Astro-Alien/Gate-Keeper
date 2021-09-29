/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.gatekeeper.registration;

import java.awt.Color;
import java.awt.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

/**
 *
 * @author Charles
 */
public class TestClass {

    private JFrame welcome;
    private JButton btnshow;
    private JPanel thirdBorder;
    private JLabel label;
    private JPanel border;
    private JLabel lblimage;

    PreparedStatement stmt = null;
    ResultSet results = null;
    
    public TestClass() {
        welcome = new JFrame("Gatekeeper");
        border = new JPanel();
        btnshow = new JButton("SHOW");
        lblimage = new JLabel();
    }

    public void start() {
        //starting connection
       
        welcome.setSize(876, 497);
        welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcome.setResizable(false);
        border.setLayout(null);
        border.setBackground(Color.BLUE);
        welcome.add(border);
        
        label = new JLabel();
        thirdBorder = new JPanel();
        thirdBorder.setBounds(525,37,290,385);
        thirdBorder.setLayout(null);
        border.add(thirdBorder);
        thirdBorder.add(label);
       
        
       //image to be replaced with the current image being taken by the user will be modified once the images are saved to the database
        /*ImageIcon userimage = new ImageIcon("images\\default.jpg");
        label.setBounds(58, 30, 180, 160);
        label.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
        Image img = userimage.getImage();
        Image imgScale = img.getScaledInstance(180, 160, Image.SCALE_SMOOTH);
        ImageIcon ScaledIcon = new ImageIcon(imgScale);
        label.setIcon(ScaledIcon);
        thirdBorder.add(label);*/
    
         int value = 11;
         try{
             Connection conn = DbConnection.ConnectDb();
             String sql = "select image from visitors where visitorID ='"+value+"' ";
             stmt = conn.prepareStatement(sql);
             results = stmt.executeQuery();
             
             if(results.next()){ 
                 
                 byte[] imagedata = results.getBytes("image");
                 ImageIcon format = new ImageIcon(imagedata);
                 label.setBounds(58, 30, 180, 160);
                 label.setBorder(BorderFactory.createLineBorder(new Color(0xffffff), 3));
                 Image img = format.getImage();
                 Image myImg = img.getScaledInstance(180, 160, Image.SCALE_SMOOTH);
                 ImageIcon newImage = new ImageIcon(myImg);
                 label.setIcon(newImage);
                 thirdBorder.add(label);
                 
             
             }
             else{
             
                 JOptionPane.showMessageDialog(null, "No Data Was Retrieved");
             
             }
         
         }catch(Exception ex){
             
             ex.printStackTrace();
         }
        
        
        
       
     
        
        welcome.setLocationRelativeTo(null);
        welcome.setVisible(true);
    }
    
    public static void main(String[] args) {
        new TestClass().start();
        
    }

}
