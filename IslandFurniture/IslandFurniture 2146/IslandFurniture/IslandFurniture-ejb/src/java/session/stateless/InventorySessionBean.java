/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Inventory;
import entity.Part;
import entity.Furniture;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ZiGui
 */
@Stateless
@LocalBean
public class InventorySessionBean implements InventorySessionBeanLocal {

    @PersistenceContext
    private EntityManager em;

//    @Override
//    public List<Inventory> getAllInventory() {
//        Query query = em.createQuery("SELECT i FROM Inventory i");
//        return query.getResultList();
//    }
    @Override
    public List<Part> getPartInventory() {
        Query query = em.createQuery("SELECT p FROM Part p");
        return query.getResultList();
    }

    @Override
    public List<Furniture> getFurnitureInventory() {
        Query query = em.createQuery("SELECT f FROM Furniture f");
        return query.getResultList();
    }

    @Override
    public void updateInventoryPart(String partName, int quantity) {
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        Inventory i = getInventoryPart(partName);
        i.setQuantity(quantity);
        i.setDateUpdated(date);
        em.merge(i);
        em.flush();
    }

    @Override
    public void updateInventoryFurniture(String furniture, int quantity) {
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        Inventory i = getInventoryFurniture(furniture);
        i.setQuantity(quantity);
        i.setDateUpdated(date);
        em.merge(i);
        em.flush();
    }

    @Override
    public Inventory getInventoryPart(String partName) {
        Query query = em.createQuery("SELECT p FROM Part p WHERE p.partName = :inPartName");
        query.setParameter("inPartName", partName);
        Part p = null;
        try {
            p = (Part) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("caught no result exception");
        }

        Long pInventoryId = p.getInventory().getInventoryId();
        Query qQuery = em.createQuery("SELECT i FROM Inventory i WHERE i.inventoryId = :pInventoryId");
        qQuery.setParameter("pInventoryId", pInventoryId);
        Inventory i = null;
        try {
            i = (Inventory) qQuery.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("caught no result exception");
        }

        return i;
    }

    @Override
    public Inventory getInventoryFurniture(String furnitureName) {
        Query query = em.createQuery("SELECT f FROM Furniture f WHERE f.furnitureName = :furnitureName");
        query.setParameter("furnitureName", furnitureName);
        Furniture f = null;
        try {
            f = (Furniture) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("caught no result exception");
        }

        Long fInventoryId = f.getInventory().getInventoryId();
        Query qQuery = em.createQuery("SELECT i FROM Inventory i WHERE i.inventoryId = :fInventoryId");
        qQuery.setParameter("fInventoryId", fInventoryId);
        Inventory i = null;
        try {
            i = (Inventory) qQuery.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("caught no result exception");
        }

        return i;
    }

}
