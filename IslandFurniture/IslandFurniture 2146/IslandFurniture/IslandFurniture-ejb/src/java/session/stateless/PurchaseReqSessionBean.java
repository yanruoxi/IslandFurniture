/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.PurchaseRequisition;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Meiling
 */
@Stateless
public class PurchaseReqSessionBean implements PurchaseReqSessionBeanLocal {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void addPurchaseReq(PurchaseRequisition pr) {
        em.persist(pr);
    }

    // Retrieve all Purchase Requisitions
    @Override
    public List<PurchaseRequisition> getAllPurchaseReq() {
        Query query = em.createQuery("SELECT prq FROM PurchaseRequisition prq WHERE prq.isDeleted != true");
        return query.getResultList();
    }
    
    @Override
    public void deletePurchaseReq(PurchaseRequisition selectedPurchaseReq){
        PurchaseRequisition prqDB = em.find(PurchaseRequisition.class, selectedPurchaseReq.getPurchaseReqId());
        prqDB.setIsDeleted(true);
        em.merge(prqDB);
        em.flush();
    }
    
    @Override
    public boolean editPurchaseReq(PurchaseRequisition selectedPurchaseReq){
        PurchaseRequisition prqDB = em.find(PurchaseRequisition.class, selectedPurchaseReq.getPurchaseReqId());
        // Same qty and same replenishment type, return false
        if((prqDB.getQty().compareTo(selectedPurchaseReq.getQty()) == 0) && (prqDB.getReplenishmentType().equals(selectedPurchaseReq.getReplenishmentType()))){
            return false;
        }
        prqDB.setQty(selectedPurchaseReq.getQty());
        prqDB.setReplenishmentType(selectedPurchaseReq.getReplenishmentType());
        em.merge(prqDB);
        em.flush();
        return true;
    }
    
    
}
