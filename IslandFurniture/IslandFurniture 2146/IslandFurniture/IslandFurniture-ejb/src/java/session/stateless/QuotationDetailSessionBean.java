/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.PurchaseRequisition;
import entity.QuotationDetail;
import entity.Supplier;
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
public class QuotationDetailSessionBean implements QuotationDetailSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;   

    // Retrieve all Quotation Details               
    @Override
    public List<QuotationDetail> getAllQuotnDetailForSupplier(Supplier s) {
        System.out.println("s:" + s.getSupplierName());
        Query query = em.createQuery("SELECT qd FROM QuotationDetail qd WHERE qd.supplier.supplierId = :sId");
        Long sId = s.getSupplierId();
        query.setParameter("sId", sId);
        System.out.println("query.getResultList:" + query.getResultList().size());

        if (query.getResultList().isEmpty()) {
            return null;
        } else {        
            return query.getResultList();
        }
    }

    // Retrieve SUBMITTED and NOT DELETED Quotation Details for Pro Staff
    @Override
    public List<QuotationDetail> getAllQuotnDetailForProStaff() {
        Query query = em.createQuery("SELECT qd FROM QuotationDetail qd WHERE qd.quotationDetailStatus = 'Submitted' AND qd.isDeleted = false");
        System.out.println("query.getResultList:" + query.getResultList().size());
        return query.getResultList();
    }

    // Create Quotation Detail
    @Override
    public void createQuotnDetail(PurchaseRequisition pr, Supplier s) {
        QuotationDetail qd = new QuotationDetail();
        qd.setPart(pr.getPart());
        qd.setQty(pr.getQty());
        qd.setQuotedSentDate(new Date());
        qd.setSupplier(s);
        qd.setQuotationDetailStatus("Pending"); // Default quotationDetailStatus
        qd.setIsDeleted(false); // Default isDeleted status
        //Added
        qd.setReplenishmentType(pr.getReplenishmentType());

        PurchaseRequisition prDB = em.find(PurchaseRequisition.class, pr.getPurchaseReqId());
        prDB.setPurchaseReqStatus("Submitted RFQ"); // Set purchaseReqStatus to 'Submitted RFQ'
        qd.setPurchaseReq(prDB); // Link QuotationDetail to PurchaseReq

        em.persist(qd);
        em.flush();
    }

    @Override
    public QuotationDetail getQuotationWithQuotationId(Long quotationId) {
        QuotationDetail qd = em.find(QuotationDetail.class, quotationId);
        return qd;
    }

    @Override
    public void createQuotnDetailBySupplier(QuotationDetail selectedQuotation, Date quotationEndingDate, Date quotedScheduledDeliveryDate, Double quotedUnitPrice, Double totalPrice, String quotedNegoCondition, Supplier s) {
        QuotationDetail qdDB = em.find(QuotationDetail.class, selectedQuotation.getQuotationDetailId());
        System.out.println("qdDB:" + qdDB);
        qdDB.setLastUpdated(new Date());
        qdDB.setQuotationDetailStatus("Draft"); // Default status before submission of Quotation
        qdDB.setQuotationEndingDate(quotationEndingDate);
        qdDB.setQuotedNegoCondition(quotedNegoCondition);
        qdDB.setQuotedScheduledDeliveryDate(quotedScheduledDeliveryDate);
        qdDB.setQuotedUnitPrice(quotedUnitPrice);
        qdDB.setQuotedTotalPrice(totalPrice);
        qdDB.setQuotedDate(new Date());
        qdDB.setSupplier(s);
        em.merge(qdDB);
        em.flush();
    }

    @Override
    public void submitQuotnBySupplier(QuotationDetail selectedQuotation) {
        QuotationDetail qdDB = em.find(QuotationDetail.class, selectedQuotation.getQuotationDetailId());
        System.out.println("qdDB:" + qdDB);
        qdDB.setQuotationDetailStatus("Submitted");
        em.merge(qdDB);
        em.flush();
    }

    @Override
    public void editQuotnDetailBySupplier(QuotationDetail selectedQuotation) {
        QuotationDetail qdDB = em.find(QuotationDetail.class, selectedQuotation.getQuotationDetailId());
        qdDB.setLastUpdated(new Date());
        qdDB.setQuotationEndingDate(selectedQuotation.getQuotationEndingDate());
        qdDB.setQuotedNegoCondition(selectedQuotation.getQuotedNegoCondition());
        qdDB.setQuotedScheduledDeliveryDate(selectedQuotation.getQuotedScheduledDeliveryDate());
        qdDB.setQuotedTotalPrice(selectedQuotation.getQuotedTotalPrice());
        qdDB.setQuotedUnitPrice(selectedQuotation.getQuotedUnitPrice());
        em.merge(qdDB);
        em.flush();
    }

    @Override
    public boolean checkQuotationExpiry(Date quotationEndingDate) {
        if (!quotationEndingDate.after(new Date())) { // meaning (contractEndDate =< currentDate)
            return true;
        } else {
            return false;
        }
    }
}
