/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.doa;

/**
 *
 * @author Charles
 */
public class AgentWorker {
   /* private String customerID;*/
    private String customerName;
    //private String customerSurname;
    //private String customerMobile;

    public AgentWorker(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "AgentWorker{" + "customerName=" + customerName + '}';
    }
    
}
