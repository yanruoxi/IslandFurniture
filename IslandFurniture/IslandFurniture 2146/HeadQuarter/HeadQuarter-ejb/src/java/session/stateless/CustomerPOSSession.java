/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Customer;
import entity.SystemUser;
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
public class CustomerPOSSession implements CustomerPOSSessionLocal {

    @PersistenceContext(unitName = "HeadQuarter-ejbPU")
    private EntityManager entityManager;

    @Override
    public Customer getCustomerForPOS(String phone) {

        Query query = entityManager.createQuery("SELECT u FROM Customer u WHERE u.phone = :inPhone and u.isDeleted= :inIsDeleted");
        query.setParameter("inPhone", phone);
        query.setParameter("inIsDeleted", "no");
        Customer customer = null;

        try {
            customer = (Customer) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }

        if (customer != null) {
            entityManager.detach(customer);
            customer.setShoppingcart(null);
            customer.setTransationOrderList(null);
			customer.setLoyalty(null);
            customer.setCart(null);

        }

        return customer;

    }

}
