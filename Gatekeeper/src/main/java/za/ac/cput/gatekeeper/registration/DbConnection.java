package za.ac.cput.gatekeeper.registration;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DbConnection {
    
    Connection conn = null;
    
    public static Connection ConnectDb(){
        
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Database\\visitors.db");
            System.out.println("Connection Succeeded");
            return conn;
        }
        catch(Exception e) {
            System.out.println("Connection failed" + e);
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
