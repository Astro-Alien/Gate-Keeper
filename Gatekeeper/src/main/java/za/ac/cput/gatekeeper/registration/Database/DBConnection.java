
package za.ac.cput.gatekeeper.registration.Database;

import java.sql.*;

public class DBConnection {
// Database parameters
private static final String URL = "jdbc:sqlite:Database\\visitors.db";


//Connection
public static Connection getConnection(){
    Connection conn = null;
    
    try {
        conn = DriverManager.getConnection(URL);
    } catch(Exception e){
        e.printStackTrace();
    }
    return conn;
}
    public static void disconnect(Connection conn){
        try{
            if(conn != null){
                conn.close();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Connection conn = getConnection();
        
        if(conn != null) {
            System.out.println("Connected!");
        } else {
            System.out.println("Not Connected!");
        }
    }
}

