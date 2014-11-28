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
import javax.ejb.Local;

/**
 *
 * @author Meiling
 */
@Local
public interface QuotationDetailSessionBeanLocal {
     public List<QuotationDetail> getAllQuotnDetailForSupplier(Supplier s);
     
     public void createQuotnDetail(PurchaseRequisition pr, Supplier s);
     
     public QuotationDetail getQuotationWithQuotationId(Long quotationId);
     
     public void createQuotnDetailBySupplier(QuotationDetail selectedQuotation, Date quotationEndingDate, Date quotedScheduledDeliveryDate, Double quotedUnitPrice, Double totalPrice, String quotedNegoCondition, Supplier s);
     
     public void submitQuotnBySupplier(QuotationDetail selectedQuotation);
     
     public void editQuotnDetailBySupplier(QuotationDetail selectedQuotation);
     
     public List<QuotationDetail> getAllQuotnDetailForProStaff();
     
     public boolean checkQuotationExpiry(Date quotationEndingDate);
}
