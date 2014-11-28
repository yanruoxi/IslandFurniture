/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Customer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author WangYan
 */
@Local
public interface CustomerSessionLocal {
    
    public boolean getPhoneNumber(String phoneNumber);

    public Customer getCustomer(String customerName);

    public List<Customer> getAllCustomer();

    public void deleteCustomer(String customerName);

    public void resetEmail(String customerName, String email);

    public void resetDateOfBirth(String customerName, String dateOfBirth);
    public void registerCustomer(String honorific,String customerName, String password,String eamil,String address,String postalcode,String country,String phoneNumber);

    public Customer getCustomerByEmail(String userEmail);
    
    public Customer getForgetEmail(String userName2);
    
    public void editCustomer(Customer customer);
    
    public void editCustomerPoints(Customer customer);
    
    public void resetPassword(String userName,String password);
    
    
       public Customer getCustomerByPhone(String customerPhone) ;

}
