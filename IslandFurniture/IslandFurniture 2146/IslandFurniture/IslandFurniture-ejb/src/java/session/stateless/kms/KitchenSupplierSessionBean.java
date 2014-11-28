/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.kms;

import datamodel.SupplierWithIngredient;
import entity.kms.Ingredient;
import entity.kms.KitchenSupplier;
import java.util.ArrayList;
import java.util.List;
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
public class KitchenSupplierSessionBean implements KitchenSupplierSessionBeanLocal {

    //@EJB
    // private PartSessionBeanLocal partSessionBean; 
    @PersistenceContext
    private EntityManager em;

    // Retrieve all Suppliers
    @Override
    public List<KitchenSupplier> getAllKitchenSuppliers() {
        Query query = em.createQuery("SELECT k FROM KitchenSupplier k WHERE k.ksupplierDeleteStatus = false");
        return query.getResultList();
    }

    // Delete a Supplier
    @Override
    public void deleteKitchenSupplier(Long kSupplierId) {
        System.out.println("deleteSupplierSessionBean:" + kSupplierId);
        KitchenSupplier ks = em.find(KitchenSupplier.class, kSupplierId);
        ks.setKsupplierDeleteStatus(true);
    }

// Retrieve a Supplier based on SupplierId
    @Override
    public KitchenSupplier getKitchenSupplier(Long selectedSupplierId) {
        KitchenSupplier ks = em.find(KitchenSupplier.class, selectedSupplierId);
        return ks;
    }

// Retrieve a Supplier based on SupplierName
    @Override
    public List<KitchenSupplier> getKitchenSupplier(String supplierName) {
        Query query = em.createQuery("SELECT k FROM KitchenSupplier k WHERE k.ksupplierName = :inSupplierName");
        query.setParameter("inSupplierName", supplierName);
        List<KitchenSupplier> s = null;
        try {
            s = query.getResultList();
        } catch (NoResultException ex) {
            System.out.println("caught no result exception");
        }
        return s;
    }

    // Add a new Supplier
    @Override
    public void addKitchenSupplier(String supplierName, String supplierAddress, String telephoneNum,
            String contactPersonName, String mobileNum, String faxNum, String supplierEmailAddr) {
        KitchenSupplier ks = new KitchenSupplier();
        ks.setKsupplierName(supplierName);
        ks.setKsupplierAddress(supplierAddress);
        ks.setKtelephone(telephoneNum);
        ks.setKcontactPersonName(contactPersonName);
        ks.setKmobileNum(mobileNum);
        ks.setKfaxNum(faxNum);
        ks.setKsupplierEmailAddress(supplierEmailAddr);
        ks.setKsupplierDeleteStatus(false);

        em.persist(ks);
        em.flush();
    }

    // Edit Supplier
    @Override
    public void editKitchenSupplier(Long supplierId, String supplierName, String contactPersonName, String mobileNum, String faxNum,
            String supplierEmailAddr, String telephoneNum) {
        KitchenSupplier s = getKitchenSupplier(supplierId);
        s.setKcontactPersonName(contactPersonName);
        s.setKmobileNum(mobileNum);
        s.setKfaxNum(faxNum);
        s.setKsupplierEmailAddress(supplierEmailAddr);
        s.setKtelephone(telephoneNum);
        em.merge(s);
        em.flush();
    }

    @Override
    public int checkKitchenSupplier(String supplierName) {
        List<KitchenSupplier> s = getKitchenSupplier(supplierName);
        int size = s.size();

        if (size == 0) {
            return 1;
        } else {
            for (int i = 0; i < size; i++) {
                if (!s.get(i).isKsupplierDeleteStatus()) // Any existing supplier's delete status = no
                {
                    return 2;
                }
            }
            return 3;
        }
    }

    @Override
    public KitchenSupplier getOneKitchenSupplierByName(String supplierName) {
        Query query = em.createQuery("SELECT s FROM KitchenSupplier s WHERE s.ksupplierName = :inSupplierName AND s.ksupplierDeleteStatus = false");
        query.setParameter("inSupplierName", supplierName);

        KitchenSupplier s = null;
        try {
            s = (KitchenSupplier) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("getOneKitchenSupplierByName: caught no result exception");
        }   

        return s;
    }

    @Override
    public List<SupplierWithIngredient> getSuppliersForIngredient(Ingredient ingredient) {
        Query query = em.createQuery("SELECT k, i FROM Ingredient i, KitchenSupplier k WHERE i.ingredientName = :i AND i.kitchenSuppliers = k");
        query.setParameter("i", ingredient.getIngredientName());
        List<Object[]> objs = query.getResultList();
        ArrayList<SupplierWithIngredient> arr = new ArrayList<SupplierWithIngredient>();
        for (Object[] obj : objs) {
            System.err.println("obj[0]: " + ((KitchenSupplier) obj[0]).getKsupplierId());
            arr.add(new SupplierWithIngredient((KitchenSupplier) obj[0], (Ingredient) obj[1]));
            System.out.println("(Supplier)obj[0], (Ingredient)obj[1]" + (KitchenSupplier) obj[0] + "," + (Ingredient) obj[1]);
        }
        return arr;
    }
}
