/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import datamodel.SupplierWithAvgQty;
import datamodel.SupplierWithMinPrice;
import entity.Inventory;
import entity.Part;
import entity.PurchaseOrder;
import entity.PurchaseRequisition;
import entity.QuotationDetail;
import entity.ShippingOrder;
import entity.Supplier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class PurchaseOrderSessionBean implements PurchaseOrderSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;

    // Retrieve all Purchase Orders
    @Override
    public List<PurchaseOrder> getAllPurchaseOrdersForProMngr() {
        Query query = em.createQuery("SELECT po FROM PurchaseOrder po WHERE (po.purchaseOrderStatus = 'Pending Approval' OR po.purchaseOrderStatus = 'Approved' OR po.purchaseOrderStatus = 'Rejected') AND po.isDeleted = false");
        return query.getResultList();
    }

    // Create Purchase Order for Contract Supplier
    @Override
    public void createPOForContractSup(PurchaseRequisition pr, Double contractTotalPrice, Date scheduledDeliveryDate) {
        PurchaseOrder po = new PurchaseOrder();
        po.setPart(pr.getPart());
        po.setIsDeleted(false);
        po.setPurchaseOrderDate(new Date());
        po.setPurchaseOrderStatus("Pending Approval");
        po.setQty(pr.getQty());
        po.setTotalPrice(contractTotalPrice);
        po.setUnitPrice(pr.getPart().getContract().getUnitPrice());
        po.setNegoCondition("Term(s) from Contract: " + pr.getPart().getContract().getRemark()); // ADDED
        po.setScheduledDeliveryDate(scheduledDeliveryDate);
        po.setSupplier(pr.getPart().getContract().getSupplier());
        //Added
        po.setReplenishmentType(pr.getReplenishmentType());
        // Added 23-10-14
        // Update Purchase Requisition status to 'PO Sent'
        PurchaseRequisition prDB = em.find(PurchaseRequisition.class, pr.getPurchaseReqId());
        prDB.setPurchaseReqStatus("PO Sent");
        
        em.persist(po);
        em.flush();
    }
    
    
    
    

    @Override
    public void createPOForNonContractSup(QuotationDetail qd){
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
    

    // Update Purchase Order Status
    @Override
    public void updatePOStatus(PurchaseOrder po) {
        System.out.println("PO status:" + po.getPurchaseOrderStatus());
        System.out.println("PO id:" + po.getPurchaseOrderId());
        PurchaseOrder poDB = em.find(PurchaseOrder.class, po.getPurchaseOrderId());
        poDB.setPurchaseOrderStatus(po.getPurchaseOrderStatus());

        em.flush();
    }

    // Delete Purchase Order (NOT YET APPROVED/ REJECTED
    @Override
    public void deletePO(PurchaseOrder po) {
        PurchaseOrder poDB = em.find(PurchaseOrder.class, po.getPurchaseOrderId());
        poDB.setIsDeleted(true);
        System.out.println("poDB session bean:" + poDB.getPurchaseOrderId());
        em.merge(poDB);
        em.flush();
    }

    // Edit Purchase Order
    @Override
    public void editPO(PurchaseOrder po) {
        System.out.println("po Scheduled Delivery Date:" + po.getScheduledDeliveryDate());
        PurchaseOrder poDB = em.find(PurchaseOrder.class, po.getPurchaseOrderId());
        poDB.setScheduledDeliveryDate(po.getScheduledDeliveryDate());
        em.merge(poDB);
        em.flush();
    }

    // Get Purchase Order from DB
    @Override
    public PurchaseOrder getPODB(PurchaseOrder po) {
        PurchaseOrder poDB = em.find(PurchaseOrder.class, po.getPurchaseOrderId());
        return poDB;
    }

    @Override
    public ArrayList<SupplierWithMinPrice> filterPOByBestDealPriceTop3(Part p) {
        Query query = em.createQuery("SELECT po.supplier, MIN(po.unitPrice) AS a FROM PurchaseOrder po WHERE po.part = :p AND (po.purchaseOrderStatus='Received' OR po.purchaseOrderStatus='Issued') AND po.isDeleted = false GROUP BY po.supplier ORDER BY a ASC");
        query.setParameter("p", p);
        query.setMaxResults(3); // Top 3
        System.out.println("query.getResultList() size:" + query.getResultList().size());

        List<Object[]> objs = query.getResultList();
        ArrayList<SupplierWithMinPrice> arr = new ArrayList<SupplierWithMinPrice>();
        int rank = 1; // Rank of Supplier
        for (Object[] obj : objs) {
            System.err.println("obj[0]: " + ((Supplier) obj[0]).getSupplierId());
            arr.add(new SupplierWithMinPrice(rank++, (Supplier) obj[0], (Double) obj[1], p));
            System.out.println("(Supplier)obj[0], (Double)obj[1]" + (Supplier) obj[0] + "," + (Double) obj[1]);
        }
        rank = 0; // Re-initialise rank
        return arr;
    }

    @Override
    public ArrayList<SupplierWithMinPrice> filterPOByBestDealPriceTop5(Part p) {
        Query query = em.createQuery("SELECT po.supplier, MIN(po.unitPrice) AS a FROM PurchaseOrder po WHERE po.part = :p AND (po.purchaseOrderStatus='Received' OR po.purchaseOrderStatus='Issued') AND po.isDeleted = false GROUP BY po.supplier ORDER BY a ASC");
        query.setParameter("p", p);
        query.setMaxResults(5); // Top 5
        System.out.println("query.getResultList() size:" + query.getResultList().size());

        List<Object[]> objs = query.getResultList();
        ArrayList<SupplierWithMinPrice> arr = new ArrayList<SupplierWithMinPrice>();
        int rank = 1; // Rank of Supplier
        for (Object[] obj : objs) {
            System.err.println("obj[0]: " + ((Supplier) obj[0]).getSupplierId());
            arr.add(new SupplierWithMinPrice(rank++, (Supplier) obj[0], (Double) obj[1], p));
            System.out.println("(Supplier)obj[0], (Double)obj[1]" + (Supplier) obj[0] + "," + (Double) obj[1]);
        }
        rank = 0; // Re-initialise rank
        return arr;
    }

    @Override
    public ArrayList<SupplierWithAvgQty> filterPOByAvgQtyTop3(Part p) {
        Query query = em.createQuery("SELECT po.supplier, AVG(po.qty) AS a FROM PurchaseOrder po WHERE po.part = :p AND (po.purchaseOrderStatus='Received' OR po.purchaseOrderStatus='Issued') AND po.isDeleted = false GROUP BY po.supplier ORDER BY a DESC");
        query.setParameter("p", p);
        query.setMaxResults(3); // Top 3
        System.out.println("query.getResultList() size:" + query.getResultList().size());

        List<Object[]> objs = query.getResultList();
        ArrayList<SupplierWithAvgQty> arr = new ArrayList<SupplierWithAvgQty>();
        int rank = 1; // Rank of Supplier
        for (Object[] obj : objs) {
            System.err.println("obj[0]: " + ((Supplier) obj[0]).getSupplierId());
            arr.add(new SupplierWithAvgQty(rank++, (Supplier) obj[0], (Double) obj[1], p));
            System.out.println("(Supplier)obj[0], (Double)obj[1]" + (Supplier) obj[0] + "," + (Double) obj[1]);
        }
        rank = 0; // Re-initialise rank
        return arr;
    }

    @Override
    public ArrayList<SupplierWithAvgQty> filterPOByAvgQtyTop5(Part p) {
        Query query = em.createQuery("SELECT po.supplier, AVG(po.qty) AS a FROM PurchaseOrder po WHERE po.part = :p AND (po.purchaseOrderStatus='Received' OR po.purchaseOrderStatus='Issued') AND po.isDeleted = false GROUP BY po.supplier ORDER BY a DESC");
        query.setParameter("p", p);
        query.setMaxResults(5); // Top 5
        System.out.println("query.getResultList() size:" + query.getResultList().size());

        List<Object[]> objs = query.getResultList();
        ArrayList<SupplierWithAvgQty> arr = new ArrayList<SupplierWithAvgQty>();
        int rank = 1; // Rank of Supplier
        for (Object[] obj : objs) {
            System.err.println("obj[0]: " + ((Supplier) obj[0]).getSupplierId());
            arr.add(new SupplierWithAvgQty(rank++, (Supplier) obj[0], (Double) obj[1], p));
            System.out.println("(Supplier)obj[0], (Double)obj[1]" + (Supplier) obj[0] + "," + (Double) obj[1]);
        }
        rank = 0; // Re-initialise rank
        return arr;
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

//    // Retrieve all DISTINCT Supplier for Part
//    @Override
//    public List<SupplierWithPart> getAllDistSupForPart(Part p) {
//        Query query = em.createQuery("SELECT DISTINCT po.supplier, po.part, COUNT(po.deliveryStatus) FROM PurchaseOrder po WHERE po.part = :p GROUP BY po.supplier");
//        query.setParameter("p", p);
//        
//        List<Object[]> objs = query.getResultList();
//        ArrayList<SupplierWithPart> arr = new ArrayList<SupplierWithPart>();
//        for(Object[] obj:objs)
//        {
//            System.err.println("obj[0]: " + ((Supplier)obj[0]).getSupplierId());
//            arr.add(new SupplierWithPart((Supplier)obj[0], (Part)obj[1], (Long)obj[2]));
//            System.out.println("(Supplier)obj[0], (Long)obj[2]" + (Supplier)obj[0] + "," +(Long)obj[2]);
//        }
//        return arr;
//    }
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
        
        if (results.isEmpty())
            return null;
        else 
            return results;
    }

    // Added in
//    @Override
//    public void createQuotation(PurchaseOrder po) {
//        System.out.println("Creating Quotation");
//        if (po.getNegoCondition().isEmpty()) {
//            QuotationDetail qd = new QuotationDetail();
//            qd.setPurchaseOrder(po);
//            qd.setIsDeleted(false);
//            qd.setLastUpdated(new Date());
//            qd.setQuotedDate(null);
//            qd.setGoodsType(null);
//            System.out.println("Quotation Created");
//
//            em.persist(qd);
//            em.flush();
    //       }
//    }
    @Override
    public ShippingOrder getShippingOrder(PurchaseOrder po) {
        Long poId = po.getPurchaseOrderId();
        Query query = em.createQuery("SELECT so FROM ShippingOrder so WHERE so.purchaseOrder.purchaseOrderId = :poId");
        query.setParameter("poId", poId);
        ShippingOrder so = null;
        try {
            so = (ShippingOrder) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("getShippingOrder: caught no result exception");
        }
        return so;
    }

//    @Override
//    public QuotationDetail getSupplierQuotation(PurchaseOrder po) {
//        Long poId = po.getPurchaseOrderId();
//        Query query = em.createQuery("SELECT qd FROM QuotationDetail qd WHERE qd.purchaseOrder.purchaseOrderId = :poId");
//        query.setParameter("poId", poId);
//        QuotationDetail qd = null;
//        try {
//            qd = (QuotationDetail) query.getSingleResult();
//        } catch (NoResultException ex) {
//            System.out.println("caught no result exception");
//        }
//        return qd;
//
//    }
    @Override
    public void update(PurchaseOrder po, Date shippedOutDate) {
        System.out.println("In purchaseOrderSessionBean.update method");
        Long poId = po.getPurchaseOrderId();
        Query query = em.createQuery("SELECT so FROM ShippingOrder so WHERE so.purchaseOrder.purchaseOrderId = :poId");
        query.setParameter("poId", poId);
        ShippingOrder so = null;
        try {
            so = (ShippingOrder) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("caught no result exception");
        }

        so.setShippedOutDate(shippedOutDate);
        so.setShippingOrderStatus("In Transit");
        po.setGoodsIssuedDate(shippedOutDate);
        po.setDeliveryStatus("Pending");
        so.setPurchaseOrder(po);
        System.out.println("Supplier Shipping Details Updated");
        em.merge(so);
        em.flush();
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrdersForInvStaff() {
        Query query = em.createQuery("SELECT po FROM PurchaseOrder po WHERE (po.purchaseOrderStatus = 'Issued' OR po.purchaseOrderStatus = 'Approved' OR po.purchaseOrderStatus = 'False Received' OR po.purchaseOrderStatus = 'Received') AND po.isDeleted = false");
        return query.getResultList();
    }

    @Override
    public void updateInvPO(PurchaseOrder po, String poStatus, String deliveryStatus) {
        System.out.println("Updating Purchase Order Status");
        po.setPurchaseOrderStatus(poStatus);
        po.setDeliveryStatus(deliveryStatus);
        //  po.getPart().getInventory().getPart().ntory(null);

        em.persist(po);
    }

    @Override
    public void updateInvReceivedQty(PurchaseOrder po, String poStatus, String deliveryStatus) {
        System.out.println("Updating quantity");
        Long partId = po.getPart().getId();

        Query query = em.createQuery("SELECT i FROM Inventory i WHERE i.part.id = :partId");
        query.setParameter("partId", partId);
        Inventory i = null;
        try {
            i = (Inventory) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("caught no result exception");
        }

        i.setQuantity(po.getQty() + i.getQuantity());
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        i.setDateUpdated(date);

        po.setDeliveryStatus(deliveryStatus);
        po.setPurchaseOrderStatus(poStatus);
        po.setGoodsReceiptDate(date);
    
        em.merge(po);
        em.merge(i);
        em.flush();

    }

    @Override
    public void updateIssuePO(PurchaseOrder po, String status) {
        System.out.println("Updating Purchase Order Status");
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);

        po.setGoodsIssuedDate(date);
        po.setPurchaseOrderStatus(status);

        ShippingOrder so = new ShippingOrder();
        so.setIsDeleted(false);
        so.setShippingOrderStatus("In Transit");
        so.setDateCreated(date);
        so.setShippedOutDate(date);
        so.setPurchaseOrder(po);

        System.out.println(status);
        System.out.println(date);

        System.out.println("Updated Purchase Order Status");
        em.merge(po);
        em.persist(so);
        em.flush();

    }

    // Retrieve all Purchase Orders for IM Staff
    @Override
    public List<PurchaseOrder> getAllPurchaseOrdersForImStaff() {
        Query query = em.createQuery("SELECT po FROM PurchaseOrder po WHERE (po.purchaseOrderStatus = 'Issued' OR po.purchaseOrderStatus = 'False Issued') AND po.isDeleted = false");
        return query.getResultList();
    }

    @Override
    public void updateImPO(PurchaseOrder po, String deliveryStatus) {
        System.out.println("Updating quantity");
        Long partId = po.getPart().getId();
        Long poId = po.getPurchaseOrderId();
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);

        Query query = em.createQuery("SELECT i FROM Inventory i WHERE i.part.id = :partId");
        query.setParameter("partId", partId);
        Inventory i = (Inventory) query.getSingleResult();

        i.setQuantity(i.getQuantity() - po.getQty());
        i.setDateUpdated(date);

        po.setDeliveryStatus(deliveryStatus);
        if (deliveryStatus.equals("Not Delivered")) {
            po.setPurchaseOrderStatus("False Issued");
        } else {
            po.setGoodsReceiptDate(date);
        }

        Query q = em.createQuery("SELECT so FROM ShippingOrder so WHERE so.purchaseOrder.purchaseOrderId := poId");
        q.setParameter("poId", poId);
        ShippingOrder so = (ShippingOrder) q.getSingleResult();
        so.setShippingOrderStatus(deliveryStatus);
        
        em.merge(po);
        em.merge(i);
        em.flush();

    }


}
