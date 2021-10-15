/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.dbconnection;

import java.sql.*;

/**
 *
 * @author Charles
 */
public class DbConnection {
    
    public static Connection DbConnect() throws SQLException{
        
        String url = "jdbc:derby://localhost:1527/Login";
        String user = "administrator";
        String password = "password";
        return DriverManager.getConnection(url, user, password);
        
    }
}
