/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.kms;

import datamodel.SupplierWithIngredient;
import entity.kms.Ingredient;
import entity.kms.KitchenSupplier;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ZiGui
 */   
@Local
public interface KitchenSupplierSessionBeanLocal {

    public List<KitchenSupplier> getAllKitchenSuppliers();

    public void deleteKitchenSupplier(Long supplierId);

    public KitchenSupplier getKitchenSupplier(Long selectedSupplierId);

    public List<KitchenSupplier> getKitchenSupplier(String supplierName); 
    
    public void addKitchenSupplier(String supplierName, String supplierAddress, String telephoneNum,
            String contactPersonName, String mobileNum, String faxNum, String supplierEmailAddr);
    
    public void editKitchenSupplier(Long supplierId, String supplierName, String contactPersonName, String mobileNum, String faxNum, String supplierEmailAddr, String telephoneNum);

    public int checkKitchenSupplier(String supplierName);

    public KitchenSupplier getOneKitchenSupplierByName(String supplierName);

    public List<SupplierWithIngredient> getSuppliersForIngredient(Ingredient ingredient);

}
