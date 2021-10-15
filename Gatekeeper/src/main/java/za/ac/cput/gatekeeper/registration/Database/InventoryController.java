/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.gatekeeper.registration.Database;

import java.util.*;

/**
 *
 * @author Brandon
 */
public class InventoryController {
    
    private Scanner keyboard = new Scanner(System.in).useLocale(Locale.US);;
    private DBHandler handler = new DBHandler();
    
    /**
     * Function to add a new Visitor
     */
    private void addVisitor(){
        
        //Prompt the user for data
        int visitorID = keyboard.nextInt();
        keyboard.nextLine();
        String mobileNumber = keyboard.nextLine();
        String firstName = keyboard.nextLine();
        String lastName = keyboard.nextLine();
        String company = keyboard.nextLine();
    
        //Create Visitor Object
        Visitor visitor = new Visitor(visitorID, mobileNumber, firstName, lastName, company);
        
        //Now add this object to the database -First C of CRUD
        if(handler.insertVisitor(visitor))
        {
            System.out.println("Visitor has been saved into the database");  
        }
        else
        {
                System.out.println("Failed to save the visitor into the database");
        }
    }
    /**
     *Update the Visitor 
     */
    private void updateVisitor(){
        
        System.out.println("Please enter Visitor ID: ");
        int id = keyboard.nextInt();
        
        Visitor visitor = handler.getVisitor(id);
        
        if(visitor == null){
            System.out.print("No such product exist with ID");
            return;
        }
        
        //details
        System.out.println("\nVisitor ID: " + visitor.getVisitorID());
        System.out.println("Mobile No: " + visitor.getMobileNumber());
        System.out.println("Product Name: " + visitor.getFirstName());
        System.out.println("Product Quantity: " + visitor.getLastName());
        System.out.println("Product Price: " + visitor.getCompany());
        System.out.println();
        
        int visitorID = keyboard.nextInt();
        System.out.print("Please enter a new Quantity: ");
        String lastName = keyboard.nextLine();
        System.out.print("Please enter new Price:  ");
        String company = keyboard.nextLine();
        
        visitor.setLastName(lastName);
        visitor.setCompany(company);
        
        //Call function to update it
        if(handler.updateVisitor(visitor)){
            System.out.println("Product has been updated!");
        }else{
            System.out.println("Product has not been updated!");
        }
    }
    
    //List all the products
    public void displayInventory(){
        // Get the list
        ArrayList<Visitor> visitors = handler.getVisitors();
        
        System.out.print(String.format("%s %s %s %s %s %n",
            "visitorID", "mobileNumber", "firstName",  "lastName", "company"));
        //print inventory
        for(Visitor v: visitors) {
            System.out.print(v);
        }
    }
    
    /**
     * Get the Products as String to display
     * @return products
     */
    public String getVisitorsAsString() {
    
        // Get the list
        ArrayList<Visitor> visitors = handler.getVisitors();
        
        String output = String.format("%-12s %-30s %-30s %-30s %-30s%n",
               "User ID", "Mobile", "First Name",  "Last Name", "Company");
        //print inventory
        for(Visitor v: visitors) {
            output +=v.toString();
        }
        return output;
    }
    
    /**
     *Delete the product 
     */
    private void deleteVisitor(){
        System.out.println("Please enter Visitor_ID: ");
        int id = keyboard.nextInt();
        
        //Call function to delete it
        if(handler.deleteVisitor(id)){
            System.out.println("Product wth id " + id + "has been deleted!");
        }else{
            System.out.println("Product wth id " + id + "has not been deleted!");
        }
    }
    
    /**
    * List all the products
    * 
    * @return list of products
    */
    public ArrayList<Visitor> getVisitors(){
        return this.handler.getVisitors();
    }
    /**
     * Add new product
     * @param visitor
     * @return true if added, false otherwise
     */
    public boolean addVisitor(Visitor visitor){
        return this.handler.insertVisitor(visitor);
    }
    
    /**
     * Edit product quantity and price
     * 
     * @param visitor
     * @return true if edit otherwise false
     */
    
    public boolean editVisitor(Visitor visitor){
        return this.handler.updateVisitor(visitor);
    }
    
    /**
     * Function to remove a product
     * @param id
     * @return true if deleted, false otherwise.
     */
    
    public boolean deleteVisitor(int id) {
        return this.handler.deleteVisitor(id);
    }
    
    /**
     * Get a product by Id
     * @param id
     * @return product
     */
    
    public Visitor getVisitor(int id) {
        return this.handler.getVisitor(id);
    }
}
