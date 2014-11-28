/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Part;
import entity.Supplier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.LocalBean;
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
@LocalBean
public class PartSessionBean implements PartSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;

    // Retrieve all Parts
    @Override
    public HashSet<Part> getAllParts() {
        Query query = em.createQuery("SELECT p FROM Part p WHERE p.isDeleted != 'yes'");
        HashSet<Part> allParts = new HashSet<Part>(query.getResultList());
        if (allParts.isEmpty()) {
            return null;
        } else {
            return allParts;
        }
    }

    // Retrieve all Part Name
    @Override
    public List<String> getPartsString() {
        Query query = em.createQuery("SELECT p.partName FROM Part p WHERE p.isDeleted != 'yes'");
        List<String> partsString = new ArrayList<String>(query.getResultList());
        return partsString;
    }

    // Retrieve a Part object based on PartId
    @Override
    public Part getPart(Long selectedPartId) {
        Part gp = em.find(Part.class, selectedPartId);
        return gp;
    }

    // Retrieve List of Part based on PartName
    @Override
    public List<Part> getPartByPartName(String partName) {
        Query query = em.createQuery("SELECT p FROM Part p WHERE p.partName = :partName");
        query.setParameter("partName", partName);
        return query.getResultList();
    }

    // Retrieve a Part object based on Part Name
    @Override
    public Part getSelectedPartToAdd(String partName) {
        Query query = em.createQuery("SELECT p FROM Part p WHERE p.partName = :partName");
        query.setParameter("partName", partName);
        Part result = (Part) query.getSingleResult();
        return result;
    }

    // Add New Part
    @Override
    public void addPart(String partName, int leadTime, Integer lotSize, String isDeleted) {
        Part p = new Part();
        p.setPartName(partName);
        p.setLeadTime(leadTime);
        p.setLotSize(lotSize);
        p.setIsDeleted(isDeleted);

        em.merge(p);
        em.flush();
    }

    @Override
    public int checkPart(String partName) {
        List<Part> s = getPartByPartName(partName);
        System.out.println(partName + s + "---------" + s.size());
        int size = s.size();

        if (size == 0) {
            return 1;
        } else {
            for (int i = 0; i < size; i++) {
                if (s.get(i).getIsDeleted().equals("no")) // Any existing supplier's delete status = no
                {
                    return 2;
                }
            }
            return 3;
        }
    }

    @Override
    public void update(Part ri, int leadTime, Integer lotSize) {
        System.out.println("PartSessionBean: update() ");
        ri.setLeadTime(leadTime);
        ri.setLotSize(lotSize);

        em.merge(ri);
        em.flush();
    }

    @Override
    public int deleteCheck(Part p) {
        System.out.println("PartSessionBean: deleteCheck()");
        if (p.getContract() == null) {  
            if (p.getBom().isEmpty()) {
                return 1;
            }
        } else if (p.getContract() != null) {
            return 2;
        }
        return 3;
    }
    
    @Override
        public void delete(Part p) {
            System.out.println("PartSessionBean: delete()");
            p.setIsDeleted("yes");
            em.merge(p);
            em.flush();
        }


    
    
    
    
    //
    //
    //
    //
    
    // Add Part(s) to Supplier
    @Override
    public boolean addPartToSupplier(Supplier selectedSupplier, Set<Part> selectedPartsToAdd) {
        Supplier sResult = em.find(Supplier.class, selectedSupplier.getSupplierId()); // Retrieve existing Supplier object
        List<Part> selectedPartsToAddList = new ArrayList<Part>(selectedPartsToAdd); // Convert Set to List
        // If existing Part to Supplier pair does not exist
        if (!partToSupplierExist(selectedSupplier, selectedPartsToAddList)) {
            for (int i = 0; i < selectedPartsToAdd.size(); i++) {
                sResult.getParts().add(selectedPartsToAddList.get(i)); // Add List of Part objects to Supplier object
            }
            em.persist(sResult);
            em.flush();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean partToSupplierExist(Supplier selectedSupplier, List<Part> selectedPartsToAdd) {
        // Search for existing pair of Part and Supplier based on SupplierId
        Query q = em.createQuery("SELECT p FROM Supplier s, Part p WHERE s.parts = p AND s.supplierId = :selectedSupplierId");
        q.setParameter("selectedSupplierId", selectedSupplier.getSupplierId());
        List<Part> partResultList = q.getResultList();

        // If no records are found
        if (partResultList.isEmpty()) {
            return false;
        } else {
            // Compare partResultList(from db) to selectedPartsToAdd (from checkbox)
            // If both contains the same PartId, it means record is already in db, so it exists
            for (int i = 0; i < partResultList.size(); i++) {
                for (int j = 0; j < selectedPartsToAdd.size(); j++) {
                    if (selectedPartsToAdd.get(j).getId().equals(partResultList.get(i).getId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> getPartsForSelectedSupplier(Supplier selectedSupplierForContract) {
        Supplier s = em.find(Supplier.class, selectedSupplierForContract.getSupplierId());
        List<Part> getPartsForSelectedSupplier = new ArrayList<Part>(s.getParts()); // Convert Set to List
        List<String> getPartNameForSelectedSupplier = new ArrayList<String>();
        for (int i = 0; i < getPartsForSelectedSupplier.size(); i++) {
            getPartNameForSelectedSupplier.add(getPartsForSelectedSupplier.get(i).getPartName());
        }
        return getPartNameForSelectedSupplier;
    }

    //edited 26 Sep 8:49 Ruoxi
    @Override
    public Part getPartByName(String partName) {
        Query query = em.createQuery("SELECT p FROM Part p WHERE p.isDeleted != 'yes' and p.partName=:inPartName");
        query.setParameter("inPartName", partName);
        Part part = null;

        try {
            part = (Part) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }

        return part;
    }

    @Override
    public List<Part> getAllPart() {
        Query query = em.createQuery("SELECT e FROM Part e WHERE e.isDeleted= :inIsDeleted");
        query.setParameter("inIsDeleted", "no");
        return query.getResultList();
    }

    @Override
    public void deletePart(String partName) {
        Part part = getPartByName(partName);
        part.setIsDeleted("yes");
        em.merge(part);
    }
}
