/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import entity.Customer;
import entity.Evoucher;
import entity.PointPolicy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import session.stateless.LoyoltySessionBeanLocal;

/**
 *
 * @author li
 */
@Named(value = "loyaltyManageBean")
@ViewScoped
public class LoyaltyManageBean implements Serializable {
    @EJB
    private LoyoltySessionBeanLocal loyaltySB;
    
    private Integer faceValue;
    private String faceValue1;
    private Integer points;
    private String points1;
    private String termCondition;
    private String customerID;
    private String status;
    private long cusID;
    private String currency;
    private String ratio;
    
    private Evoucher voucher;
    private PointPolicy policy;
    private List<Evoucher> vouchers = new ArrayList<> ();
    private List<Customer> customers = new ArrayList<> ();
    private List<PointPolicy> policies = new ArrayList<> ();

    /**
     * Creates a new instance of LoyaltyManageBean
     */
    public LoyaltyManageBean() {
    }
    
    @PostConstruct
    public void init() {
           vouchers = loyaltySB.getVouchers();
           policies = loyaltySB.getPolicies();
    }


    public Integer getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(Integer faceValue) {
        this.faceValue = faceValue;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }



    public String getTermCondition() {
        return termCondition;
    }

    public void setTermCondition(String termCondition) {
        this.termCondition = termCondition;
    }

    public Evoucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Evoucher voucher) {
        this.voucher = voucher;
    }

    public List<Evoucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<Evoucher> vouchers) {
        this.vouchers = vouchers;
    }

    public String getFaceValue1() {
        return faceValue1;
    }

    public void setFaceValue1(String faceValue1) {
        this.faceValue1 = faceValue1;
    }

    public String getPoints1() {
        return points1;
    }

    public void setPoints1(String points1) {
        this.points1 = points1;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCusID() {
        return cusID;
    }

    public void setCusID(long cusID) {
        this.cusID = cusID;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public List<PointPolicy> getPolicies() {
        return policies;
    }

    public void setPolicies(List<PointPolicy> policies) {
        this.policies = policies;
    }

    public PointPolicy getPolicy() {
        return policy;
    }

    public void setPolicy(PointPolicy policy) {
        this.policy = policy;
    }
    
    
    
    
    public void AddNewEVoucher() {
        if (faceValue1==null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Please fill in faceValue", "Please fill in faceValue");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if(points1==null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Please fill in points required", "Please fill in points required");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else {
            voucher = loyaltySB.addNewVoucher(Integer.valueOf(faceValue1),Integer.valueOf(points1),termCondition);
            if(voucher==null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cannot add voucher of same value", "Cannot add voucher of same value");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            else
                vouchers.add(voucher);
        }
    }
    
    public void DeleteVoucher(long id) {
        
        loyaltySB.deleteVoucher(id);
        
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "User Deleted Successfully", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        vouchers = loyaltySB.getVouchers();
    }
    
    public void searchCustomer() {
        Customer customer = loyaltySB.viewCustomer(Long.valueOf(customerID));
        if (customer==null) {
            customers.clear();
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No search result", "No search result");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } 
        else {
            customers.clear();
            customers.add(customer);
        }

    }
    
    public void viewAllCustomer() {
        customers = loyaltySB.viewAllCustomers();
        if (customers.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No customers", "No customers");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } 
    }
    
     public void resetAccountSatus() {

        Customer customer = loyaltySB.updataOrderStatus(cusID,status);
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully reset order status", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        customers =loyaltySB.viewAllCustomers();
    }
     
    public void updateCustomerStatus(ActionEvent event)
    {
        cusID = (long) event.getComponent().getAttributes().get("customerId");
    }
    
    public void addRatio() {
         if (currency==null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Please fill in currency", "Please fill in currency");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if(ratio==null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Please fill in ratio", "Please fill in ratio");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else {
            policy = loyaltySB.addNewRatio(currency,Float.parseFloat(ratio));
            if(policy==null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cannot add policy of same currency", "Cannot add policy of same currency");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            else
                policies.add(policy);
        }
        
    }
    
    public void DeletePolicy(long id) {
        
        loyaltySB.deletePolicy(id);
        
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Policy Deleted Successfully", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        policies = loyaltySB.getPolicies();
    }

    public void onCellEdit(CellEditEvent event) {
        
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ratio Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        PointPolicy data = context.getApplication().evaluateExpressionGet(context, "#{policies}", PointPolicy.class);
        long policyId=data.getId();
        loyaltySB.updatePolicy((float)newValue, policyId);

    }
    
    
    
}
