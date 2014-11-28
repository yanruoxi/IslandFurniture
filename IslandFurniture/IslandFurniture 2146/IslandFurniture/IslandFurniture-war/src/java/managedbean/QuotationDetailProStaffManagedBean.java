/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import entity.QuotationDetail;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import session.stateless.QuotationDetailSessionBeanLocal;

/**
 *
 * @author Meiling
 */
@Named(value = "quotnDetailPSManagedBean")
@SessionScoped
public class QuotationDetailProStaffManagedBean implements Serializable {
    
    @EJB
    private QuotationDetailSessionBeanLocal quotnDetailSessionBean;

    // Variables for Procurement Staff
    private List<QuotationDetail> quotnDetailForProStaffList; 
    private List<QuotationDetail> filteredQuotnDetailForProStaffList;
    private List<String> quotationDetailStatusListForProStaff = new ArrayList<String>(); // Status to Display for Procurement Staff
    private String quotationDetailStatusForProStaff; // Status selected to Display for Procurement Staff
    private QuotationDetail selectedQuotationForProStaff;
    
    // GETTERS AND SETTERS
     public List<QuotationDetail> getQuotnDetailForProStaffList() {
        return quotnDetailForProStaffList;
    }

    public void setQuotnDetailForProStaffList(List<QuotationDetail> quotnDetailForProStaffList) {
        this.quotnDetailForProStaffList = quotnDetailForProStaffList;
    }

    public List<QuotationDetail> getFilteredQuotnDetailForProStaffList() {
        return filteredQuotnDetailForProStaffList;
    }

    public void setFilteredQuotnDetailForProStaffList(List<QuotationDetail> filteredQuotnDetailForProStaffList) {
        this.filteredQuotnDetailForProStaffList = filteredQuotnDetailForProStaffList;
    }

    public List<String> getQuotationDetailStatusListForProStaff() {
        return quotationDetailStatusListForProStaff;
    }

    public void setQuotationDetailStatusListForProStaff(List<String> quotationDetailStatusListForProStaff) {
        this.quotationDetailStatusListForProStaff = quotationDetailStatusListForProStaff;
    }

    public String getQuotationDetailStatusForProStaff() {
        return quotationDetailStatusForProStaff;
    }

    public void setQuotationDetailStatusForProStaff(String quotationDetailStatusForProStaff) {
        this.quotationDetailStatusForProStaff = quotationDetailStatusForProStaff;
    }

    public QuotationDetail getSelectedQuotationForProStaff() {
        return selectedQuotationForProStaff;
    }

    public void setSelectedQuotationForProStaff(QuotationDetail selectedQuotationForProStaff) {
        this.selectedQuotationForProStaff = selectedQuotationForProStaff;
    }
    // END OF GETTERS AND SETTERS

    /**
     * Creates a new instance of QuotationDetailProStaffManagedBean
     */
    public QuotationDetailProStaffManagedBean() {
    }
    
    @PostConstruct
    public void init() {
        System.err.println("init()");
        quotnDetailForProStaffList = quotnDetailSessionBean.getAllQuotnDetailForProStaff();
        quotationDetailStatusListForProStaff.add("Submitted");
        quotationDetailStatusListForProStaff.add("Expired");
    }
    
    
    
}
