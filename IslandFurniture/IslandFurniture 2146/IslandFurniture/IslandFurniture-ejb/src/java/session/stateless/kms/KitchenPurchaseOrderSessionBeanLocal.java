/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless.kms;

import entity.Part;
import entity.PurchaseOrder;
import entity.QuotationDetail;
import entity.Supplier;
import entity.kms.Ingredient;
import entity.kms.KitchenPurchaseOrder;
import entity.kms.KitchenSupplier;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Meiling
 */
@Local
public interface KitchenPurchaseOrderSessionBeanLocal {
    
    //public List<PurchaseOrder> getAllPurchaseOrdersForProMngr();
    public List<KitchenPurchaseOrder> getAllKitchenPurchaseOrders();

    public void createPOForNonContractSup(QuotationDetail qd);

    public void createPOForDefaultSup(Ingredient selectedIngredientForKPO, Double totalPrice, Date scheduledDeliveryDate, int quantityForKPO);

    public void createPOForNonDefaultSup(Ingredient selectedIngredientForKPO, double unitPriceForKPO, double totalPrice, Date scheduledDeliveryDate, int quantityForKPO, KitchenSupplier kp);
    

    public PurchaseOrder getPODB(PurchaseOrder po);

    public List<Supplier> getDistSupForPart(Part p);

    public List<String> deliveryStatusListForSup(Part p, Supplier s);

    //public List<SupplierWithPart> getAllDistSupForPart(Part p);
    public List<PurchaseOrder> getEvaluateSupplierFields(Supplier s, Part p);

    public List<PurchaseOrder> getAllPurchaseOrdersForSupplier(Supplier s);

    public void editPO(KitchenPurchaseOrder kpo, Date date, int quantity);
    
    public void updatePOStatusToSent(KitchenPurchaseOrder po);
    
    public void deletePO(KitchenPurchaseOrder po);
    
    public KitchenPurchaseOrder getKPOwithId(Long id);


}
