/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import entity.Contract;
import entity.Part;
import entity.Supplier;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import session.stateless.ContractSessionBeanLocal;
import session.stateless.PartSessionBeanLocal;

/**
 *
 * @author Meiling
 */
@Named
@ViewScoped
public class ContractManagedBean implements Serializable {
    @EJB
    private PartSessionBeanLocal partSessionBean;
    
    @EJB
    private ContractSessionBeanLocal contractSessionBean;
    
    @Inject
    private SupplierManagedBean supplierManagedBean;
    
    // Variables
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private String partName;
    private List<Contract> contracts; // For View All Contracts
    private List<Contract> filteredContracts; // For filtering in View All Contracts
    private Contract selectedContract;

    private Long contractId;
    private Supplier supplier;
    private Part part;
    private String contractDeleteStatus;
    private Contract contractToEdit;
    
    private String remark;
    
    private Double unitPrice; // Added
     
    @PostConstruct
    public void init(){
        System.err.println("init()");
        contracts = contractSessionBean.getAllContracts(); // Retrieve list of contracts from session bean
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        if (paramMap.containsKey("supplier")) {
            String contractIdString = paramMap.get("supplier");
            contractToEdit = contractSessionBean.getContractWithContractId(Long.parseLong(contractIdString));
        }
    }
    
    // GETTERS AND SETTERS
    public Date getStartDate(){
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    } 

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public SupplierManagedBean getSupplierManagedBean() {
        return supplierManagedBean;
    }

    public void setSupplierManagedBean(SupplierManagedBean supplierManagedBean) {
        this.supplierManagedBean = supplierManagedBean;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<Contract> getFilteredContracts() {
        return filteredContracts;
    }

    public void setFilteredContracts(List<Contract> filteredContracts) {
        this.filteredContracts = filteredContracts;
    }

    public Contract getSelectedContract() {
        return selectedContract;
    }

    public void setSelectedContract(Contract selectedContract) {
        this.selectedContract = selectedContract;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractDeleteStatus() {
        return contractDeleteStatus;
    }

    public void setContractDeleteStatus(String contractDeleteStatus) {
        this.contractDeleteStatus = contractDeleteStatus;
    }

    public Contract getContractToEdit() {
        return contractToEdit;
    }

    public void setContractToEdit(Contract contractToEdit) {
        this.contractToEdit = contractToEdit;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    // END OF GETTERS AND SETTERS
    
    /**
     * Creates a new instance of ContractManagedBean
     */
    public ContractManagedBean() {
    }
    
    // Create a Contract
    public void createContract(){
        // Check that End Date is after Start Date
        if (!startDate.before(endDate)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "End Date must be after Start Date");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        // Check that Part Name is not null
        if(partName.equals(null) || partName == ""){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Part must be selected");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        // Check that End Date and Start Date is not before or equal current date
        if(!endDate.after(new Date()) || !startDate.after(new Date())){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Date must be after Current Date");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        Part p = partSessionBean.getSelectedPartToAdd(partName); // Retrieve Part object based on Part Name
        // Create Contract
        boolean success = contractSessionBean.createContract(startDate, endDate, remark, unitPrice, supplierManagedBean.getSelectedSupplierForContract(), p);
        // If create Contract is successful
        if(success){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successfully Created Contract");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Contract with Supplier exists");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    // Delete a Contract
    public void deleteContract() {
        contractSessionBean.deleteContract(selectedContract);
        contracts.remove(selectedContract);
        selectedContract = null;
        contracts = contractSessionBean.getAllContracts();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successfully Ended Contract");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
  
    public boolean filterBySupplierName(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            System.err.println("1");
            return true;
        }
        if(value == null) {
            System.err.println("2");
            return false;
        }
        System.err.println("3: " + value.toString().contains(filter.toString()));
        if(filteredContracts != null)
            System.err.println("filteredContract: " + filteredContracts.toString());
        return value.toString().contains(filter.toString());
    }
    
    public void redirectToEditContract() {
        System.out.println("redirectToEditContract:" + selectedContract);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("scmAdminEditContract.xhtml?i=2&supplier=" + selectedContract.getContractId().toString()); 
        } 
        catch (IOException ex) {
            Logger.getLogger(SupplierManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Edit Contract
    public void editContract(){
        System.out.println("contractToEdit:" + contractToEdit.getContractId());
        System.out.println("contractToEdit StartDate:" + contractToEdit.getStartDate());
        System.out.println("contractToEdit endDate:" + contractToEdit.getEndDate());
        System.out.println("contractToEdit partName:" + partName);
        
        // Check that End Date is after Start Date
        if (!contractToEdit.getStartDate().before(contractToEdit.getEndDate())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "End Date must be after Start Date");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        // Check that End Date is not before or equal current date
        if(!contractToEdit.getEndDate().after(new Date())){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "End Date must be after Current Date");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        
        Part p = partSessionBean.getSelectedPartToAdd(contractToEdit.getPart().getPartName()); // Retrieve Part object based on Part Name
        // Edit Contract
        boolean edited = contractSessionBean.editContract(contractToEdit, p);
        if(edited){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Contract has successfully been updated.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Failed to update contract because no changes were made.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    // Check contract expiry
    public void checkContractExpiry(){
        String msg = "The following contract(s) have expired: ";
        List<Long> expiredContractList = new ArrayList<Long>();
        for (int i = 0; i < contracts.size(); i++) {
            if (contractSessionBean.checkContractExpiry(contracts.get(i).getEndDate())) {
                expiredContractList.add(contracts.get(i).getContractId());
                msg += "[" + contracts.get(i).getContractId() + "]";
            }
        }
        // If there are expired contracts, prompt on before page is rendered
        msg += ". You may wish to end the contract(s).";
        if(!expiredContractList.isEmpty()){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
