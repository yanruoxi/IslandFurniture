/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.Supplier;
import entity.SupplierAcct;
import entity.SystemUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Meiling
 */
@Stateless
public class SupplierAcctSessionBean implements SupplierAcctSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public SupplierAcct getSupplierAcct(Supplier s) {
        System.out.println("s:" + s.getSupplierName() + " " + s.getSupplierId());
        Supplier sDB = em.find(Supplier.class, s.getSupplierId());
        Query query = em.createQuery("SELECT sa FROM SupplierAcct sa WHERE sa.supplier = :s AND sa.isDeleted != true");
        query.setParameter("s", sDB);
        SupplierAcct supplierAcct = null;
        try {
            supplierAcct = (SupplierAcct) query.getSingleResult();
        } 
        catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }
        return supplierAcct;
    }
    
    @Override
    public SupplierAcct getSupplierAcctWithUsername(String supplierUsername) {
        Query query = em.createQuery("SELECT sa FROM SupplierAcct sa WHERE sa.supplierUsername = :supplierUsername AND sa.isDeleted != true");
        query.setParameter("supplierUsername", supplierUsername);
        SupplierAcct supplierAcct = null;
        try {
            supplierAcct = (SupplierAcct) query.getSingleResult();
        } 
        catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }
        return supplierAcct;
    }

    @Override
    public void addNewSupplierAcct(String supEmail, String supPwdSalt, String salt, Supplier s) {
        Supplier sDB = em.find(Supplier.class, s.getSupplierId());
        SupplierAcct sa = new SupplierAcct();
        sa.setSupplierUsername(supEmail);
        sa.setSupplier(sDB);
        sa.setIsDeleted(false);
        sa.setSupplierPwd(sa.hashPassword(supPwdSalt));
        sa.setStatus("active");
        sa.setSalt(salt);
        em.persist(sa);
        sDB.setSupplierAcct(sa);
        em.flush();
    }
    
    @Override
    public void resetStatus(String supplierUsername, String status){
        SupplierAcct sa = getSupplierAcctWithUsername(supplierUsername);
        sa.setStatus(status);
        em.merge(sa);
        em.flush();
    }
    
    @Override
    public void resetPwd(SupplierAcct supplierAcct, String reEnterNewPwd){
        SupplierAcct saDB = em.find(SupplierAcct.class, supplierAcct.getSupplierAcctId());
        saDB.setSupplierPwd(saDB.hashPassword(reEnterNewPwd + saDB.getSalt()));
        em.merge(saDB);
        em.flush();
    }
    
    // Reset status to blocked or active
    @Override
    public void resetSupplierAcctStatus(Supplier s, String status){
        Supplier sDB = em.find(Supplier.class, s.getSupplierId());
        SupplierAcct saDB = em.find(SupplierAcct.class, sDB.getSupplierAcct().getSupplierAcctId());
        saDB.setStatus(status);
        em.merge(saDB);
        em.flush();
    }
    
    
}
