/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.gatekeeper.registration.Database;

/**
 *
 * @author Brandon
 */
public class Visitor {
    
    private int visitorID;
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String company;
    
    /**
     * 
     * Parameter constructor with all the values for attributes
     * @param visitorID
     * @param mobileNumber
     * @param firstName
     * @param lastName
     * @param company
     */
    public Visitor(int visitorID, String mobileNumber, String firstName, String lastName, String company){
        this.visitorID = visitorID;
        this.mobileNumber = mobileNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
    }

    public int getVisitorID() {
        return visitorID;
    }

    public void setVisitorID(int visitorID) {
        this.visitorID = visitorID;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    
    /**
     * Got the string representation
     * 
     * @return string representation
     */
    @Override
    public String toString(){
       
        return String.format("%-12d %-30s %-30s %-30s %-30s%n", visitorID, mobileNumber, firstName, 
                lastName, company);
        
    }
}

