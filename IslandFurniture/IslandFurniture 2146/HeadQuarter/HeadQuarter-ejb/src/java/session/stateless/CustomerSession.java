/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Customer;
import entity.Loyalty;
import entity.RedemptionCart;
import entity.Shoppingcart;
import entity.SystemUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ruoxi
 */
@Stateless
public class CustomerSession implements CustomerSessionLocal {

    @PersistenceContext(unitName = "HeadQuarter-ejbPU")
    private EntityManager entityManager;

    @Override
    public Customer getCustomer(String customerName) {
        Query query = entityManager.createQuery("SELECT u FROM Customer u WHERE u.customerName = :inUserName and u.isDeleted= :inIsDeleted");
        query.setParameter("inUserName", customerName);
        query.setParameter("inIsDeleted", "no");
        Customer customer = null;
        try {
            customer = (Customer) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }
        System.out.println("test");
        return customer;
    }

    @Override
    public List<Customer> getAllCustomer() {
        Query query = entityManager.createQuery("SELECT e FROM Customer e WHERE e.isDeleted= :inIsDeleted");
        query.setParameter("inIsDeleted", "no");
        return query.getResultList();
    }

    @Override
    public void deleteCustomer(String customerName) {
        Customer customer = getCustomer(customerName);
        customer.setIsDeleted("no");
        entityManager.merge(customer);

    }
@Override
public boolean getPhoneNumber(String phoneNumber){
    Query q = entityManager.createQuery("select c from Customer c where c.phone=:cPhone");
    q.setParameter("cPhone", phoneNumber);
    if(q.getResultList().isEmpty()){
        return false;
        
    }else{
        return true;
    }
   
}
    @Override
    public void resetEmail(String customerName, String email) {
        Customer customer = getCustomer(customerName);
        customer.setEmail(email);
        entityManager.merge(customer);
        entityManager.flush();
    }

    @Override
    public void resetDateOfBirth(String customerName, String dateOfBirth) {

        Customer customer = getCustomer(customerName);
        customer.setDob(dateOfBirth);
        entityManager.merge(customer);
        entityManager.flush();
    }
    @Override
     public void registerCustomer(String honorific ,String customerName, String password,String email,String address,String postalCode,String country,String phoneNumber){
         Customer customer = new Customer();
         customer.setGender(honorific);
         customer.setCountry(country);
         customer.setPostalCode(postalCode);
         customer.setCustomerName(customerName);
         customer.setStatus("active");
         customer.setPassword(customer.hashPassword(password));
         customer.setAddressLine1(address);
         customer.setEmail(email);
         customer.setPhone(phoneNumber);
         Shoppingcart cart = new Shoppingcart();
         cart.setCustomer(customer);
         customer.setShoppingcart(cart);
         Loyalty l = new Loyalty();
         entityManager.persist(l);
         customer.setLoyalty(l);
         l.setCustomer(customer);
         entityManager.persist(l);
         
         entityManager.persist(cart);
         entityManager.persist(customer);
         
     }
 @Override
     public void editCustomer(Customer customer){
         Customer ct = entityManager.find(Customer.class, customer.getId());
         ct.setCustomerName(customer.getCustomerName());
         ct.setEmail(customer.getEmail());
         ct.setAddressLine1(customer.getAddressLine1());
         ct.setPassword(customer.getPassword());
         entityManager.persist(ct);
     }
    @Override
    public Customer getCustomerByEmail(String userEmail) {
        Query query = entityManager.createQuery("SELECT u FROM Customer u WHERE u.email = :email and u.isDeleted= :inIsDeleted");
        query.setParameter("email", userEmail);
        query.setParameter("inIsDeleted", "no");
        Customer customer = null;
        try {
            customer = (Customer) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }
        System.out.println("test");
        return customer;
    }
    
    @Override
     public Customer getForgetEmail(String userName2){
         Query q = entityManager.createQuery("select c from Customer c where c.customerName=:cName");
         q.setParameter("cName", userName2);
         if(q.getResultList()!=null){
             Customer c =(Customer)q.getResultList().get(0);
             return c;
         }else{
             return null;
         }
     }
	@Override
    public Customer getCustomerByPhone(String customerPhone) {
        Query query = entityManager.createQuery("SELECT u FROM Customer u WHERE u.phone = :inUserName and u.isDeleted= :inIsDeleted");
        query.setParameter("inUserName", customerPhone);
		query.setParameter("inIsDeleted", "no");
        Customer customer = null;
        try {
            customer = (Customer) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }
        return customer;
    }


     @Override
      public void editCustomerPoints(Customer customer){
          Customer old = entityManager.find(Customer.class, customer.getId());
          Loyalty l = old.getLoyalty();
          l.setPointsAvailable(customer.getLoyalty().getPointsAvailable());
         entityManager.persist(l);
          
         
      }
      
      @Override
       public void resetPassword(String userName,String password){
           Query q = entityManager.createQuery("select c from Customer c where c.customerName=:cName");
           q.setParameter("cName", userName);
           Customer c = (Customer)q.getResultList().get(0);
           System.out.println("user Name"+c);
           System.out.println(password);
           c.setPassword(c.hashPassword(password));
           entityManager.persist(c);
       }
}
