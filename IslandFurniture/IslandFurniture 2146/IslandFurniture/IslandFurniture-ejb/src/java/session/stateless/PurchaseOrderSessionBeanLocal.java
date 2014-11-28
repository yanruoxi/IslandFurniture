/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import datamodel.SupplierWithAvgQty;
import datamodel.SupplierWithMinPrice;
import entity.Part;
import entity.PurchaseOrder;
import entity.PurchaseRequisition;
import entity.QuotationDetail;
import entity.ShippingOrder;
import entity.Supplier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Meiling
 */
@Local
public interface PurchaseOrderSessionBeanLocal {

    public List<PurchaseOrder> getAllPurchaseOrdersForProMngr();

    public void createPOForNonContractSup(QuotationDetail qd);

    public void createPOForContractSup(PurchaseRequisition pr, Double contractTotalPrice, Date scheduledDeliveryDate);

    public void updatePOStatus(PurchaseOrder po);

    public PurchaseOrder getPODB(PurchaseOrder po);

    public ArrayList<SupplierWithMinPrice> filterPOByBestDealPriceTop3(Part p);

    public ArrayList<SupplierWithMinPrice> filterPOByBestDealPriceTop5(Part p);

    public ArrayList<SupplierWithAvgQty> filterPOByAvgQtyTop3(Part p);

    public ArrayList<SupplierWithAvgQty> filterPOByAvgQtyTop5(Part p);

    public List<Supplier> getDistSupForPart(Part p);

    public List<String> deliveryStatusListForSup(Part p, Supplier s);

    //public List<SupplierWithPart> getAllDistSupForPart(Part p);
    public List<PurchaseOrder> getEvaluateSupplierFields(Supplier s, Part p);

    public List<PurchaseOrder> getAllPurchaseOrdersForSupplier(Supplier s);

    public void deletePO(PurchaseOrder po);

    public void editPO(PurchaseOrder po);

    // Added in
    public ShippingOrder getShippingOrder(PurchaseOrder poId);

    //  public void createQuotation(PurchaseOrder po);
    public void update(PurchaseOrder po, Date shippedOutDate);

    public List<PurchaseOrder> getAllPurchaseOrdersForInvStaff();

    public void updateInvPO(PurchaseOrder po, String poStatus, String deliveryStatus);

    public void updateIssuePO(PurchaseOrder po, String status);

    public void updateInvReceivedQty(PurchaseOrder po, String poStatus, String deliveryStatus);

    public List<PurchaseOrder> getAllPurchaseOrdersForImStaff();

    public void updateImPO(PurchaseOrder po, String deliveryStatus);

}
