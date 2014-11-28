/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Furniture;
import static entity.Inventory_.part;
import entity.Part;
import entity.Supplier;
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
public class FurnitureSession implements FurnitureSessionLocal {

    @PersistenceContext(unitName = "IslandFurniture-ejbPU")
    private EntityManager em;

    @Override
    public Furniture getFurnitureByName(String furnitureName) {
        Query query = em.createQuery("SELECT p FROM Furniture p WHERE p.isDeleted != 'yes' and p.furnitureName=:inPartName");
        query.setParameter("inPartName", furnitureName);
        Furniture furniture = null;

        try {
            furniture = (Furniture) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }

        return furniture;
    }
    
     @Override
    public Furniture getFurnitureForPOS(String furnitureName) {
       return  testFurniturePOS( furnitureName);
    }
    
    public Furniture testFurniturePOS(String furnitureName){
        Query query = em.createQuery("SELECT p FROM Furniture p WHERE p.isDeleted != 'yes' and p.furnitureName=:inPartName");
        query.setParameter("inPartName", furnitureName);
        Furniture furniture = null;

        try {
            furniture = (Furniture) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }
        
        if(furniture!=null){
        em.detach(furniture);
        furniture.getBom().clear();
        furniture.getOrder().clear();
        furniture.getPlan().clear();
        furniture.setInventory(null);
        furniture.getSingleTransactionItemList().clear();
        furniture.setWarehouse(null);
        furniture.setFront(null);
        
        }

        return furniture;
    }

    @Override
    public List<Furniture> getAllFurniture() {
        Query query = em.createQuery("SELECT e FROM Furniture e WHERE e.isDeleted= :inIsDeleted");
        query.setParameter("inIsDeleted", "no");
        return query.getResultList();
    }

    @Override
    public void deleteFurniture(String furnitureName) {

        Furniture furniture = getFurnitureByName(furnitureName);
        furniture.setIsDeleted("yes");
        em.merge(furniture);

    }
    
    
    // Retrieve a Supplier based on SupplierId
    @Override
    public Furniture getFurniture(Long selectedSupplierId) {
        Furniture gs = em.find(Furniture.class, selectedSupplierId);
        return gs;
    }

}
