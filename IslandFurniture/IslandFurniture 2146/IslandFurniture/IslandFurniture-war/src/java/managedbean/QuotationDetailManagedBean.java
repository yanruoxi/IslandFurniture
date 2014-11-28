/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.PurchaseRequisition;
import entity.QuotationDetail;
import entity.Supplier;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import session.stateless.PurchaseReqSessionBeanLocal;
import session.stateless.QuotationDetailSessionBeanLocal;
import session.stateless.SupplierSessionBeanLocal;

/**
 *
 * @author Meiling
 */
@Named(value = "quotnDetailManagedBean")
@SessionScoped
public class QuotationDetailManagedBean implements Serializable {
    @EJB
    private PurchaseReqSessionBeanLocal purchaseReqSessionBean;

    @EJB
    private SupplierSessionBeanLocal supplierSessionBean;

    @EJB
    private QuotationDetailSessionBeanLocal quotnDetailSessionBean;
    
    

    @Inject
    private PurchaseRequisitionManagedBean purchaseReqManagedBean;

    @Inject
    private PurchaseOrderManagedBean poManagedBean;

    /**
     * Creates a new instance of QuotationDetailManagedBean
     */
    public QuotationDetailManagedBean() {
    }

    // Variables
    private List<String> quotationDetailStatusList = new ArrayList<String>(); // Status to Display
    private QuotationDetail selectedQuotation;

    // GETTERS AND SETTERS
    public List<String> getQuotationDetailStatusList() {
        return quotationDetailStatusList;
    }

    public void setQuotationDetailStatusList(List<String> quotationDetailStatusList) {
        this.quotationDetailStatusList = quotationDetailStatusList;
    }

    public QuotationDetail getSelectedQuotation() {
        return selectedQuotation;
    }

    public void setSelectedQuotation(QuotationDetail selectedQuotation) {
        this.selectedQuotation = selectedQuotation;
    }
    // END OF GETTERS AND SETTERS
    
    
    @PostConstruct
    public void init() {
        System.err.println("init()");
        //quotnDetailForSupList = quotnDetailSessionBean.getAllQuotnDetailForSupplier(supplierAcctManagedBean.getSupplierAcct().getSupplier()); // Retrieve list of Quotation Details from session bean
        //System.out.println("quotnDetailForSupList size:" + quotnDetailForSupList.size());
        quotationDetailStatusList.add("Pending");
        quotationDetailStatusList.add("Draft");
        quotationDetailStatusList.add("Submitted");
    }

    // Send Request for Quotation
    public void sendSupReqForQuotation() {
        System.out.println("sendSupReqForQuotation");
        System.out.println("selectedEvalSupNameList:" + poManagedBean.getSelectedEvalSupNameList().size());
        
        PurchaseRequisition pr = purchaseReqManagedBean.getSelectedPurchaseReqForSending();
        System.out.println("pr:" + pr);
        System.out.println(poManagedBean.getSelectedEvalSupNameList().isEmpty());
        if (poManagedBean.getSelectedEvalSupNameList().isEmpty()) {
            System.out.println("here");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Please select at least one Supplier");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        for (int i = 0; i < poManagedBean.getSelectedEvalSupNameList().size(); i++) {
            System.out.println("sss");
            Supplier s = supplierSessionBean.getOneSupplierByName(poManagedBean.getSelectedEvalSupNameList().get(i));
            System.out.println("s " + s.getSupplierName());
            quotnDetailSessionBean.createQuotnDetail(pr, s);
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successfully Requested for Quotation to Supplier(s)");
        FacesContext.getCurrentInstance().addMessage(null, message);
        poManagedBean.setEvaluateSupplierList(null);
        poManagedBean.setEvalSupNameList(null);
        purchaseReqManagedBean.getPurchaseReqs().clear();
        purchaseReqManagedBean.setPurchaseReqs(purchaseReqSessionBean.getAllPurchaseReq());
    }
}
