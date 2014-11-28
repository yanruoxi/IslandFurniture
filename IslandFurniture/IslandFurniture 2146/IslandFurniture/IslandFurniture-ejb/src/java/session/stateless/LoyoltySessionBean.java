/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.Customer;
import entity.Evoucher;
import entity.PointPolicy;
import entity.RedemptionCart;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author li
 */
@Stateless
public class LoyoltySessionBean implements LoyoltySessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    
    private Evoucher voucher;
    private RedemptionCart cart;
    private Customer customer;
    private PointPolicy policy;
    
/*    @PostConstruct
    public void init() {
        Query q=em.createQuery("SELECT v FROM Evoucher v WHERE v.faceValue=:vValue");
        q.setParameter("vValue",faceValue);
    }
 */   
    @Override
    public Evoucher addNewVoucher(Integer faceValue,Integer points,String termCondition) {     
        Query q1=em.createQuery("SELECT c FROM RedemptionCart c WHERE c.createTime=:cTime");
        q1.setParameter("cTime","initial");
        if(q1.getResultList().isEmpty()) {
            cart = new RedemptionCart(0,"initial");
            em.persist(cart);
        }
             
         
        Query q=em.createQuery("SELECT v FROM Evoucher v WHERE v.faceValue=:vValue");
        q.setParameter("vValue",faceValue);
        if(q.getResultList().isEmpty()){
            voucher = new Evoucher(faceValue,points,termCondition);
            voucher.setRedemptionCart(cart);
            em.persist(voucher);

            return voucher;//empty
        }
        else{
            return null;
        }
    }
    
    @Override
    public List<Evoucher> getVouchers(){
        List<Evoucher> vouchers = new ArrayList<Evoucher>();
        Query q3=em.createQuery("SELECT p FROM Evoucher p");
        for(int i=0; i<q3.getResultList().size(); i++) {
            voucher = (Evoucher) q3.getResultList().get(i);
            vouchers.add(voucher);
        }
        return vouchers;
    }
    
    @Override
    public void deleteVoucher(long id) {
        voucher = em.find(Evoucher.class, id);
        em.remove(voucher);
    }
    
    @Override
    public Customer viewCustomer(long id) {
        customer = em.find(Customer.class, id);
        return customer;
    }
    
    @Override
    public List<Customer> viewAllCustomers() {
        List<Customer> customers = new ArrayList<> ();
        Query q2 = em.createQuery("SELECT c FROM Customer c");
        for(int i=0; i<q2.getResultList().size(); i++) {
            customer = (Customer) q2.getResultList().get(i);
            customers.add(customer);
        }
        return customers;
        
    }
    
    @Override
    public Customer updataOrderStatus(long id,String status) {
        customer = em.find(Customer.class, id);
        customer.setStatus(status);
        
        return customer;
    }
    
    @Override
    public List<PointPolicy> getPolicies() {
        List<PointPolicy> policies = new ArrayList<PointPolicy>();
        Query q3=em.createQuery("SELECT p FROM PointPolicy p");
        for(int i=0; i<q3.getResultList().size(); i++) {
            policy = (PointPolicy) q3.getResultList().get(i);
            policies.add(policy);
        }
        return policies;
    }
    
    @Override
    public PointPolicy addNewRatio(String currency,float ratio) {
        Query q1=em.createQuery("SELECT c FROM PointPolicy c WHERE c.currency=:cCurrency");
        q1.setParameter("cCurrency",currency);
        if(q1.getResultList().isEmpty()) {
            policy = new PointPolicy(currency,ratio);
            em.persist(policy);

            return policy;//empty
        }
        else{
            return null;
        }
        
    }
    
    @Override
    public void deletePolicy(long id) {
        policy = em.find(PointPolicy.class, id);
        em.remove(policy);
    }
    
    @Override
    public void updatePolicy(float newValue,long policyId) {
        policy= em.find(PointPolicy.class, policyId);
        policy.setPriceForOnePoint(newValue);
    }
}
