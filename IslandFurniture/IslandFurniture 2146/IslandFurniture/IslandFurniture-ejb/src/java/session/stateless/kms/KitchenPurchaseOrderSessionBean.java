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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Meiling
 */
@Stateless
public class KitchenPurchaseOrderSessionBean implements KitchenPurchaseOrderSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;

    // Retrieve all Kitchen Purchase Orders
    @Override
    public List<KitchenPurchaseOrder> getAllKitchenPurchaseOrders() {
        Query query = em.createQuery("SELECT po FROM KitchenPurchaseOrder po WHERE po.kisDeleted = false");
        return query.getResultList();
    }

    // Create Purchase Order for Contract Supplier
    @Override
    public void createPOForDefaultSup(Ingredient selectedIngredientForKPO, Double totalPrice, Date scheduledDeliveryDate, int quantityForKPO) {
        KitchenPurchaseOrder po = new KitchenPurchaseOrder();
        po.setIngredient(selectedIngredientForKPO);
        po.setKisDeleted(false);
        po.setKpurchaseOrderDate(new Date());
        po.setKpurchaseOrderStatus("Draft");
        po.setKqty(quantityForKPO);
        po.setKtotalPrice(totalPrice);
        po.setKunitPrice(selectedIngredientForKPO.getDefaultSupplierUnitPrice());
        po.setKscheduledDeliveryDate(scheduledDeliveryDate);
        po.setKitchenSupplier(selectedIngredientForKPO.getKdefaultSupplier());
        po.setKpoIngredientType(selectedIngredientForKPO.getIngredientType());
        em.persist(po);
        em.flush();
    }

    @Override
    public void createPOForNonDefaultSup(Ingredient selectedIngredientForKPO, double unitPriceForKPO, double totalPrice, Date scheduledDeliveryDate, int quantityForKPO, KitchenSupplier kp) {
        KitchenPurchaseOrder po = new KitchenPurchaseOrder();
        po.setIngredient(selectedIngredientForKPO);
        po.setKisDeleted(false);
        po.setKpurchaseOrderDate(new Date());
        po.setKpurchaseOrderStatus("Draft");
        po.setKqty(quantityForKPO);
        po.setKtotalPrice(totalPrice);
        po.setKunitPrice(unitPriceForKPO);
        po.setKscheduledDeliveryDate(scheduledDeliveryDate);
        po.setKitchenSupplier(kp);
        po.setKpoIngredientType(selectedIngredientForKPO.getIngredientType());
        em.persist(po);
        em.flush();
    }

    @Override
    public void createPOForNonContractSup(QuotationDetail qd) {
        PurchaseOrder po = new PurchaseOrder();
        po.setIsDeleted(false);
        po.setNegoCondition("Term(s) from Quotation: " + qd.getQuotedNegoCondition());
        po.setPart(qd.getPart());
        po.setPurchaseOrderDate(new Date());
        po.setPurchaseOrderStatus("Pending Approval");
        po.setQty(qd.getQty());
        po.setScheduledDeliveryDate(qd.getQuotedScheduledDeliveryDate());
        po.setSupplier(qd.getSupplier());
        po.setTotalPrice(qd.getQuotedTotalPrice());
        po.setUnitPrice(qd.getQuotedUnitPrice());
        // Added
        po.setReplenishmentType(qd.getReplenishmentType());
        // Added 23-10-14
        // Update Purchase Requisition status to 'PO Sent'
        QuotationDetail qdDB = em.find(QuotationDetail.class, qd.getQuotationDetailId());
        qdDB.getPurchaseReq().setPurchaseReqStatus("PO Sent");

        em.persist(po);
        em.flush();
    }

    // Update Kitchen Purchase Order Status
    @Override
    public void updatePOStatusToSent(KitchenPurchaseOrder po) {
        KitchenPurchaseOrder poDB = em.find(KitchenPurchaseOrder.class, po.getKpurchaseOrderId());
        poDB.setKpurchaseOrderStatus("Sent");
        em.merge(poDB);
        em.flush();
    }

    // Get Purchase Order from DB
    @Override
    public PurchaseOrder getPODB(PurchaseOrder po) {
        PurchaseOrder poDB = em.find(PurchaseOrder.class, po.getPurchaseOrderId());
        return poDB;
    }

    // Retrieve all DISTINCT Supplier for Part
    @Override
    public List<Supplier> getDistSupForPart(Part p) {
        Query query = em.createQuery("SELECT DISTINCT po.supplier FROM PurchaseOrder po WHERE po.part = :p");
        query.setParameter("p", p);
        return query.getResultList();
    }

    @Override
    // Retrieve Delivery Status for Supplier
    public List<String> deliveryStatusListForSup(Part p, Supplier s) {
        Query query = em.createQuery("SELECT po.deliveryStatus FROM PurchaseOrder po WHERE po.part = :p AND po.supplier = :s AND po.isDeleted = false");
        query.setParameter("p", p);
        query.setParameter("s", s);
        return query.getResultList();
    }

    @Override
    public List<PurchaseOrder> getEvaluateSupplierFields(Supplier s, Part p) {
        Query query = em.createQuery("SELECT po FROM PurchaseOrder po WHERE po.part = :p AND po.supplier = :s AND po.isDeleted = false");
        query.setParameter("p", p);
        query.setParameter("s", s);
        return query.getResultList();
    }

    // Retrieve all Approved Purchase Orders for Supplier
    @Override
    public List<PurchaseOrder> getAllPurchaseOrdersForSupplier(Supplier s) {
        Query query = em.createQuery("SELECT po FROM PurchaseOrder po WHERE (po.purchaseOrderStatus = 'Approved' OR po.purchaseOrderStatus = 'Received') AND po.supplier = :s AND po.isDeleted = false");
        query.setParameter("s", s);
        List<PurchaseOrder> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        } else {
            return results;
        }
    }

    // Delete Purchase Order
    @Override
    public void deletePO(KitchenPurchaseOrder kpo) {
        kpo.setKisDeleted(true);
        em.merge(kpo);
        em.flush();
    }

    // Edit a Purchase Order
    @Override
    public void editPO(KitchenPurchaseOrder kpo, Date date, int quantity) {
        kpo.setKscheduledDeliveryDate(date);
        kpo.setKqty(quantity);
        em.merge(kpo);
        em.flush();
    }

    // Retrieve a kpo using Id
    @Override
    public KitchenPurchaseOrder getKPOwithId(Long stringId) {
        Query query = em.createQuery("SELECT DISTINCT kpo FROM KitchenPurchaseOrder kpo WHERE kpo.kpurchaseOrderId = :p AND kpo.kisDeleted = false");
        query.setParameter("p", stringId);
        return (KitchenPurchaseOrder) query.getSingleResult();  
    }

}
