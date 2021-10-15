/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.gatekeeper.registration.Database;

import java.util.*;
import java.sql.*;
import za.ac.cput.gatekeeper.registration.Database.DBConnection;
/**
 *
 * @author Brandon
 */
public class DBHandler {
    
    /**
     * Insert a visitor into the database.
     * @param visitor
     * @return  true if inserted
     */
    public boolean insertVisitor(Visitor visitor) {
        
        boolean result = false;
        try {
            
            //SQL
          String sql = "Insert into visitors(mobileNumber, firstName, lastName, company) Values(?, ?, ?, ?)";
          Connection connection = DBConnection.getConnection(); 
        
          //Create Prepared Statement
          PreparedStatement stmt = connection.prepareStatement(sql);
          
          //Set parameter values
          //stmt.setInt(1, visitor.getVisitorID());
          stmt.setString(1, visitor.getMobileNumber());
          stmt.setString(2, visitor.getFirstName());
          stmt.setString(3, visitor.getLastName());
          stmt.setString(4, visitor.getCompany());
          
          //stmt.setString(8, null);
          
          //Execute the command
          int inserted = stmt.executeUpdate();
          
          result = inserted >= 1;
          
          DBConnection.disconnect(connection);
          
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
    
    /**
     * Delete a visitor with visitorID
     * @param visitorID
     * @return 
     */
    public boolean deleteVisitor(int visitorID){
        boolean result = false;
        
        try{
            String sql = "Delete from visitors where visitorID=?";
            Connection conn = DBConnection.getConnection();
            
            //set parameter values
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, visitorID);
            int delete = stmt.executeUpdate();
            result = delete >= 1;
            DBConnection.disconnect(conn);
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
     /**
     * Delete a visitor with visitorID
     * @param product
     * @return 
     */
    public boolean updateVisitor(Visitor visitor){
        boolean result = false;
        
        try{
            String sql = "UPDATE visitors set firstName=?, lastName=?, mobileNumber=?, company=? where visitorID=?";
            Connection conn = DBConnection.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, visitor.getFirstName());
            stmt.setString(2, visitor.getLastName());
            stmt.setString(2, visitor.getMobileNumber());
            stmt.setString(4, visitor.getCompany());
            stmt.setInt(5, visitor.getVisitorID());
            
            int updated = stmt.executeUpdate();
            result = updated >= 1;
            DBConnection.disconnect(conn);
        
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Select a visitor by Id
     * @param VisitorID
     * @return visitor
     */
    public Visitor getVisitor(int visitorID)  {
        Visitor visitor = null;
        
        try {
            String sql = "SELECT * FROM visitors WHERE visitorID=?";
            Connection conn = DBConnection.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, visitorID);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
             
                visitor = new Visitor(rs.getInt(1), rs.getString(2),
                rs.getString(3), rs.getString(4) , rs.getString(5));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return visitor;
    }

/**
 * List all the visitors
 * 
 * @return list of visitors
 */
public ArrayList<Visitor> getVisitors(){

ArrayList<Visitor>visitors = new ArrayList<Visitor>();

Visitor visitor = null;
        
        try {
            String sql = "SELECT * FROM visitors";
            Connection conn = DBConnection.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
             
                visitor = new Visitor(rs.getInt(1), rs.getString(2),
                rs.getString(3), rs.getString(4), rs.getString(5));
                
                visitors.add(visitor);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

return visitors;
}
}
