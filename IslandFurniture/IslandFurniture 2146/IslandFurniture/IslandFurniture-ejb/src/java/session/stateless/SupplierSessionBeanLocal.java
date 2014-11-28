/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import datamodel.SupplierWithPart;
import entity.Part;
import entity.Supplier;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Meiling
 */
@Local
public interface SupplierSessionBeanLocal {

    public List<Supplier> getAllSuppliers();

    public boolean deleteSupplier(Long supplierId);

    public Supplier getSupplier(Long selectedSupplierId);

    public boolean contractExist(Supplier s);

    public boolean contractExistWithSupplierAndPart(Supplier s, Part pDB);

    public List<Supplier> getSupplier(String supplierName);

    public void addSupplier(String supplierName, String supplierAddress, String telephoneNum,
            String contactPersonName, String mobileNum, String faxNum, String supplierEmailAddr, String status);

    public void editSupplier(Long supplierId, String supplierName, String contactPersonName, String mobileNum, String faxNum, String supplierEmailAddr, String telephoneNum);

    public int checkSupplier(String supplierName);

    public Supplier getOneSupplierByName(String supplierName);
    
    public List<SupplierWithPart> getSuppliersForPart(Part p);
    
         public void editSupplier(Supplier supplierToEdit);

}
