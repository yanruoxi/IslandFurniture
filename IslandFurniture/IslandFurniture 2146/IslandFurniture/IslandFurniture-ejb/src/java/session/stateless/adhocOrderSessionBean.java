/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.AdHocOrder;
import entity.Furniture;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author li
 */
@Stateless
public class adhocOrderSessionBean implements adhocOrderSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    private Furniture furniture;
    private AdHocOrder adhocOrder;
    
    @Override
    public ArrayList<AdHocOrder> viewOrder(String plant) {
        Query q= em.createQuery("SELECT orders FROM AdHocOrder orders WHERE orders.plant=:oName");
        q.setParameter("oName",plant);
        ArrayList<AdHocOrder> orders=new ArrayList();
        if(q.getResultList().isEmpty()){
            System.out.println("there is no existing furniture for the name you provided/ furniture does  not exist ");
            return orders;//empty
        }
        else
        {
            for(Object o:q.getResultList()){
               AdHocOrder order1 = (AdHocOrder)o;
               orders.add(order1);
            }
            return orders;        
        }
    }
    
    @Override 
    public ArrayList<AdHocOrder> viewAllOrders() {
        Query q= em.createQuery("SELECT orders FROM AdHocOrder orders ");
        ArrayList<AdHocOrder> orders=new ArrayList();
        if(q.getResultList().isEmpty()){
            System.out.println("there is no existing orders ");
            return orders;//empty
        }
        else
        {
            for(Object o:q.getResultList()){
               AdHocOrder order1 = (AdHocOrder)o;
               orders.add(order1);
            }
            return orders;        
        }
        
    }
    
    @Override
    public String updataOrderStatus(long id,String status) {
        
        System.err.println("updataOrderStatus: " + id);
        AdHocOrder order1 = em.find(AdHocOrder.class, id);
        order1.setStatus(status);
        return order1.getPlant();
    }
    

    
}
