/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.Part;
import entity.Supplier;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author Meiling
 */
@Local
public interface PartSessionBeanLocal {
    
    public void addPart(String partName, int leadTime, Integer lotSize, String isDeleted);
    
    public HashSet<Part> getAllParts();
    public List<Part> getPartByPartName(String partName);
    public List<String> getPartsString();
    public Part getPart(Long selectedPartId);
    public Part getSelectedPartToAdd(String partName);
    
        public int checkPart(String partName);
            public void update(Part ri, int leadTime, Integer lotSize);
                public int deleteCheck(Part p);
                public void delete(Part p);

    
    
    public boolean addPartToSupplier(Supplier selectedSupplier, Set<Part> selectedPartsToAdd);
    
    public boolean partToSupplierExist(Supplier selectedSupplier, List<Part> selectedPartsToAdd);
    
    public List<String> getPartsForSelectedSupplier(Supplier selectedSupplierForContract);
    
        //edited 26 Sep 8:49 Ruoxi
    public Part getPartByName(String partName);

    public List<Part> getAllPart();

    public void deletePart(String partName);
    
}
