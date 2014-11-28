/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.QuotationDetail;
import entity.Supplier;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import session.stateless.QuotationDetailSessionBeanLocal;
import session.stateless.SupplierSessionBeanLocal;

/**
 *
 * @author Meiling
 */
@Named(value = "quotnDetailSupplierPortalManagedBean")
@SessionScoped
public class QuotationDetailSupplierPortalManagedBean implements Serializable {

    @EJB
    private QuotationDetailSessionBeanLocal quotnDetailSessionBean;

    @Inject
    private SupplierAcctManagedBean supplierAcctManagedBean;

    // Supplier Portal
    private List<QuotationDetail> quotnDetailForSupList; // For Viewing Quotation in Supplier Portal
    private List<QuotationDetail> filteredQuotnDetailForSupList;
    private List<String> quotationDetailStatusList = new ArrayList<String>(); // Status to Display
    private QuotationDetail selectedQuotation;

    // Create/ Edit Quotation
    private Double quotedUnitPrice;
    private Double quotedTotalPrice;
    private String quotedNegoCondition;
    private Date quotedDate;
    private Date quotationEndingDate;
    private Date quotedScheduledDeliveryDate;
    private String quotationDetailStatus; // quotationDetailStatus: Pending/ Draft /Submitted
    private boolean isDeleted;
    private UIComponent quotedUnitPriceInput;
    private Double totalPrice = 0.0; // Calculated Total Price from input

    public Double getQuotedUnitPrice() {
        return quotedUnitPrice;
    }

    public void setQuotedUnitPrice(Double quotedUnitPrice) {
        this.quotedUnitPrice = quotedUnitPrice;
    }

    public Double getQuotedTotalPrice() {
        return quotedTotalPrice;
    }

    public void setQuotedTotalPrice(Double quotedTotalPrice) {
        this.quotedTotalPrice = quotedTotalPrice;
    }

    public String getQuotedNegoCondition() {
        return quotedNegoCondition;
    }

    public void setQuotedNegoCondition(String quotedNegoCondition) {
        this.quotedNegoCondition = quotedNegoCondition;
    }

    public Date getQuotedDate() {
        return quotedDate;
    }

    public void setQuotedDate(Date quotedDate) {
        this.quotedDate = quotedDate;
    }

    public Date getQuotationEndingDate() {
        return quotationEndingDate;
    }

    public void setQuotationEndingDate(Date quotationEndingDate) {
        this.quotationEndingDate = quotationEndingDate;
    }

    public Date getQuotedScheduledDeliveryDate() {
        return quotedScheduledDeliveryDate;
    }

    public void setQuotedScheduledDeliveryDate(Date quotedScheduledDeliveryDate) {
        this.quotedScheduledDeliveryDate = quotedScheduledDeliveryDate;
    }

    public String getQuotationDetailStatus() {
        return quotationDetailStatus;
    }

    public void setQuotationDetailStatus(String quotationDetailStatus) {
        this.quotationDetailStatus = quotationDetailStatus;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public UIComponent getQuotedUnitPriceInput() {
        return quotedUnitPriceInput;
    }

    public void setQuotedUnitPriceInput(UIComponent quotedUnitPriceInput) {
        this.quotedUnitPriceInput = quotedUnitPriceInput;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<QuotationDetail> getQuotnDetailForSupList() {
        return quotnDetailForSupList;
    }

    public void setQuotnDetailForSupList(List<QuotationDetail> quotnDetailForSupList) {
        this.quotnDetailForSupList = quotnDetailForSupList;
    }

    public List<QuotationDetail> getFilteredQuotnDetailForSupList() {
        return filteredQuotnDetailForSupList;
    }

    public void setFilteredQuotnDetailForSupList(List<QuotationDetail> filteredQuotnDetailForSupList) {
        this.filteredQuotnDetailForSupList = filteredQuotnDetailForSupList;
    }

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

    @PostConstruct
    public void init() {
        System.err.println("init()");
       quotnDetailForSupList = quotnDetailSessionBean.getAllQuotnDetailForSupplier(supplierAcctManagedBean.getSupplierAcct().getSupplier()); // Retrieve list of Quotation Details from session bean
        System.out.println("quotnDetailForSupList size:" + quotnDetailForSupList.size());
        quotationDetailStatusList.add("Pending");
        quotationDetailStatusList.add("Draft");
        quotationDetailStatusList.add("Submitted");
    }

    /**
     * Creates a new instance of QuotationDetailSupplierPortalManagedBean
     */
    public QuotationDetailSupplierPortalManagedBean() {
    }
    

    public void redirectToCreateQuotation() {
        System.out.println("redirectToCreateQuotation:" + selectedQuotation.getQuotationDetailStatus());
        if (selectedQuotation.getQuotationDetailStatus().equals("Pending")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("scmSupplierPortalCreateQuotn.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(QuotationDetailManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // Check if Quotation has already been submitted 
        else if (selectedQuotation.getQuotationDetailStatus().equals("Submitted")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed", "Quotation has already been submitted.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (selectedQuotation.getQuotationDetailStatus().equals("Draft")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed", "Quotation has already been created.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            System.out.println("selectQuotation status:" + selectedQuotation.getQuotationDetailStatus());
        }
    }

    // Get Calculated Total Price based on quotedUnitPriceInput
    public void getCalculatedTotalPrice() {
        System.out.println("selectedQuotation here:" + selectedQuotation.getQty());
        if (quotedUnitPrice != null) {
            totalPrice = quotedUnitPrice * selectedQuotation.getQty();
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(quotedUnitPriceInput.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Total Quoted Price: $" + df.format(totalPrice) + "", ""));
        totalPrice = 0.0;
    }

    public void calculatedTotalPriceEdit() {
        System.out.println("selectedQuotation getQuotedUnitPrice:" + selectedQuotation.getQuotedUnitPrice());
        totalPrice = selectedQuotation.getQuotedUnitPrice() * selectedQuotation.getQty();
        DecimalFormat df = new DecimalFormat("#0.00");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(quotedUnitPriceInput.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Total Quoted Price: $" + df.format(totalPrice) + "", ""));
        totalPrice = 0.0;
    }

    public void createQuotn() {
        System.out.println("createQuotn");
        // Check that quotationEndingDate and quotedScheduledDeliveryDate  is not before or equal current date
        if ((!quotationEndingDate.after(new Date())) || (!quotedScheduledDeliveryDate.after(new Date()))) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Date must be after Current Date");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        // Check that quotedScheduledDeliveryDate is not before or quotationEndingDate
        if ((!quotedScheduledDeliveryDate.after(quotationEndingDate))) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Scheduled Delivery Date must be after Quotation Ending Date");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        Supplier s = supplierAcctManagedBean.getSupplierAcct().getSupplier();
        totalPrice = selectedQuotation.getQty() * quotedUnitPrice;
        quotnDetailSessionBean.createQuotnDetailBySupplier(selectedQuotation, quotationEndingDate, quotedScheduledDeliveryDate, quotedUnitPrice, totalPrice, quotedNegoCondition, s);

        //quotnDetailForSupList.clear();
        //quotnDetailForSupList = quotnDetailSessionBean.getAllQuotnDetailForSupplier(supplierAcctManagedBean.getSupplierAcct().getSupplier()); // Retrieve list of Quotation Details from session bean
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successfully created Quotation. Quotation will be saved as draft before submission.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        quotnDetailForSupList.clear();
        quotnDetailForSupList = quotnDetailSessionBean.getAllQuotnDetailForSupplier(supplierAcctManagedBean.getSupplierAcct().getSupplier());
    }

    public void submitQuotn() {
        if (selectedQuotation.getQuotationDetailStatus().equals("Draft")) {
            quotnDetailSessionBean.submitQuotnBySupplier(selectedQuotation);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Quotation has been successfully submitted.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            quotnDetailForSupList.clear();
            quotnDetailForSupList = quotnDetailSessionBean.getAllQuotnDetailForSupplier(supplierAcctManagedBean.getSupplierAcct().getSupplier()); // Retrieve list of Quotation Details from session bean
        } else if (selectedQuotation.getQuotationDetailStatus().equals("Submitted")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Quotation has already been submitted.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Quotation has not been created.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void redirectToEditQuotation() {
        System.out.println("redirectToEditQuotation:" + selectedQuotation.getQuotationDetailStatus());
        if (selectedQuotation.getQuotationDetailStatus().equals("Draft")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("scmSupplierPortalEditQuotn.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(QuotationDetailManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // Check if Quotation has already been submitted 
        else if (selectedQuotation.getQuotationDetailStatus().equals("Submitted")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed", "Quotation has already been submitted.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (selectedQuotation.getQuotationDetailStatus().equals("Pending")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed", "Quotation has not been created.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            System.out.println("selectQuotation status:" + selectedQuotation.getQuotationDetailStatus());
        }
    }

    public void editQuotn() {
        System.out.println("editQuotn");
        // Check that quotationEndingDate and quotedScheduledDeliveryDate  is not before or equal current date
        if ((!selectedQuotation.getQuotationEndingDate().after(new Date())) || (!selectedQuotation.getQuotedScheduledDeliveryDate().after(new Date()))) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Date must be after Current Date");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        // Check that quotedScheduledDeliveryDate is not before or quotationEndingDate
        if ((!selectedQuotation.getQuotedScheduledDeliveryDate().after(selectedQuotation.getQuotationEndingDate()))) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Scheduled Delivery Date must be after Quotation Ending Date");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        totalPrice = selectedQuotation.getQty() * selectedQuotation.getQuotedUnitPrice();
        selectedQuotation.setQuotedTotalPrice(totalPrice);
        System.out.println("selectedQuotation edit:" + selectedQuotation.getQuotedTotalPrice() + " " + selectedQuotation.getQuotedUnitPrice());
        quotnDetailSessionBean.editQuotnDetailBySupplier(selectedQuotation);

        quotnDetailForSupList.clear();
        quotnDetailForSupList = quotnDetailSessionBean.getAllQuotnDetailForSupplier(supplierAcctManagedBean.getSupplierAcct().getSupplier()); // Retrieve list of Quotation Details from session bean
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successfully updated Quotation. Quotation will be saved as draft before submission.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
