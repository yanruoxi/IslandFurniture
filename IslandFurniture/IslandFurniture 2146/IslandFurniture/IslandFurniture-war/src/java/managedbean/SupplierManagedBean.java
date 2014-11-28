/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Part;
import entity.Supplier;
import entity.SupplierAcct;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import session.stateless.PartSessionBeanLocal;
import session.stateless.SupplierAcctSessionBeanLocal;
import session.stateless.SupplierSessionBeanLocal;

/**
 *
 * @author Meiling
 */
@Named
@ViewScoped
public class SupplierManagedBean implements Serializable {
    @EJB
    private SupplierAcctSessionBeanLocal supplierAcctSessionBean;

    @EJB
    private PartSessionBeanLocal partSessionBean;

    @EJB
    private SupplierSessionBeanLocal supplierSessionBean;
    
    

    @PostConstruct
    public void init() {

        suppliers = supplierSessionBean.getAllSuppliers(); // Retrieve list of suppliers from session bean
        parts = partSessionBean.getPartsString();

        FacesContext fc = FacesContext.getCurrentInstance();
        String value = (String) fc.getExternalContext().getRequestParameterMap().get("supplier");
        supplierName = value;
        System.out.println(supplierName);
        supplierToEdit = supplierSessionBean.getOneSupplierByName(supplierName);
        System.err.println("int(): Supplier Name = " + supplierName);
        
//        FacesContext context = FacesContext.getCurrentInstance();
//        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
//        if (paramMap.containsKey("supplier")) {
//            String supplierName = paramMap.get("supplier");
//            supplierToEdit = supplierSessionBean.getOneSupplierByName(supplierName);
//        }

    }

    public SupplierManagedBean() {
    }

    // Variables
    private List<Supplier> suppliers; // For View All Suppliers
    private List<Supplier> filteredSuppliers; // For filtering in View All Suppliers
    private Supplier selectedSupplier;
    private String[] selectedParts;   // For Add Part to Supplier
    private List<String> parts; // For Add Part to Supplier
    private String selectedPart; // For Add Contract
    private List<String> partNamesForSelectedSupplier; // For propogating Part Names based on selected Supplier
    private Supplier selectedSupplierForContract; // For Add Contract

//zigui
    private String supplierName;
    private String supplierAddress;
    private String telephoneNum;
    private String contactPersonName;
    private String mobileNum;
    private String faxNum;
    private String supplierEmailAddr;
    private Supplier supplierToEdit;
    private List<String> partNamesForSelectedContractSupplier; // For edit contract
    private UIComponent supplierNameInput;
    
    //
    private String newStatus;

    // GETTERS AND SETTERS
    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
    
    public List<Supplier> getFilteredSuppliers() {
        return filteredSuppliers;
    }

    public void setFilteredSuppliers(List<Supplier> filteredSuppliers) {
        this.filteredSuppliers = filteredSuppliers;
    }

    public Supplier getSelectedSupplier() {
        return selectedSupplier;
    }

    public void setSelectedSupplier(Supplier selectedSupplier) {
        this.selectedSupplier = selectedSupplier;
    }

    public String[] getSelectedParts() {
        return selectedParts;
    }

    public void setSelectedParts(String[] selectedParts) {
        this.selectedParts = selectedParts;
    }

    public List<String> getParts() {
        return parts;
    }

    public void setParts(List<String> parts) {
        this.parts = parts;
    }

    public String getSelectedPart() {
        return selectedPart;
    }

    public void setSelectedPart(String selectedPart) {
        this.selectedPart = selectedPart;
    }

    public List<String> getPartNamesForSelectedSupplier() {
        return partNamesForSelectedSupplier;
    }

    public void setPartNamesForSelectedSupplier(List<String> partNamesForSelectedSupplier) {
        this.partNamesForSelectedSupplier = partNamesForSelectedSupplier;
    }

    public Supplier getSelectedSupplierForContract() {
        return selectedSupplierForContract;
    }

    public void setSelectedSupplierForContract(Supplier selectedSupplierForContract) {
        this.selectedSupplierForContract = selectedSupplierForContract;
    }

    public List<String> getPartNamesForSelectedContractSupplier() {
        return partNamesForSelectedContractSupplier;
    }

    public void setPartNamesForSelectedContractSupplier(List<String> partNamesForSelectedContractSupplier) {
        this.partNamesForSelectedContractSupplier = partNamesForSelectedContractSupplier;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
    
    // END OF GETTERS AND SETTERS
    // Delete A Supplier
    public void deleteSupplier() {
        boolean deletedSupplier = supplierSessionBean.deleteSupplier(selectedSupplier.getSupplierId());
        if (deletedSupplier) {
            suppliers.remove(selectedSupplier);
            selectedSupplier = null;
            //suppliers.clear();
            suppliers = supplierSessionBean.getAllSuppliers();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Supplier Deleted");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Failed to Delete Supplier as Contract exists.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    // Add Part(s) to A Supplier
    public void addPartToSupplier() {
        Set<Part> selectedPartsToAdd = new HashSet<Part>(); // HashSet that stores selectedPartsToAdd Part objects
        Part p = new Part(); // For Usage in for loop
        for (int i = 0; i < selectedParts.length; i++) {
            p = partSessionBean.getSelectedPartToAdd(selectedParts[i]); // Retrieve Part object using Part Name
            selectedPartsToAdd.add(p); // Add retrieved object to HashSet
        }

        boolean success = partSessionBean.addPartToSupplier(selectedSupplier, selectedPartsToAdd);
        if (success) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Part added to Supplier");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Part has already been added to Supplier");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    // Retrieve Part Names based on selected Supplier from drop down list
    public void getPartNamesForSelectedSupplierList() {
        partNamesForSelectedSupplier = partSessionBean.getPartsForSelectedSupplier(selectedSupplierForContract);
    }

    public boolean filterBySupplierName(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            System.err.println("1");
            return true;
        }
        if (value == null) {
            System.err.println("2");
            return false;
        }
        System.err.println("3: " + value.toString().toLowerCase().contains(filter.toString().toLowerCase(locale)));
        if (filteredSuppliers != null) {
            System.err.println("filteredSuppliers: " + filteredSuppliers.toString());
        }
        return value.toString().toLowerCase().contains(filter.toString().toLowerCase());
    }

    // For edit contract
    public void getPartNamesForSelectedContractSupplierList(Supplier contractSupplier) {
        System.out.println("getPartNamesForSelectedContractSupplierList");
        System.out.println("contractSupplier:" + contractSupplier);
        setPartNamesForSelectedContractSupplier(partSessionBean.getPartsForSelectedSupplier(contractSupplier));
        System.out.println("partNamesForSelectedContractSupplier:" + getPartNamesForSelectedContractSupplier().size());
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getTelephoneNum() {
        return telephoneNum;
    }

    public void setTelephoneNum(String telephoneNum) {
        this.telephoneNum = telephoneNum;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getFaxNum() {
        return faxNum;
    }

    public void setFaxNum(String faxNum) {
        this.faxNum = faxNum;
    }

    public String getSupplierEmailAddr() {
        return supplierEmailAddr;
    }

    public void setSupplierEmailAddr(String supplierEmailAddr) {
        this.supplierEmailAddr = supplierEmailAddr;
    }

    public Supplier getSupplierToEdit() {
        return supplierToEdit;
    }

    public void setSupplierToEdit(Supplier supplierToEdit) {
        this.supplierToEdit = supplierToEdit;
    }

    public UIComponent getSupplierNameInput() {
        return supplierNameInput;
    }

    public void setSupplierNameInput(UIComponent supplierNameInput) {
        this.supplierNameInput = supplierNameInput;
    }

    // Edit Supplier
    public void editSupplier() {
        System.out.println("editSupplier: " + supplierName);
      //  supplierSessionBean.editSupplier(supplierToEdit.getSupplierId(), supplierName, contactPersonName, mobileNum, faxNum, supplierEmailAddr, telephoneNum);
        supplierSessionBean.editSupplier(supplierToEdit);
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Supplier Successfully Updated", "You have successfully updated supplier " + supplierName);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("scmAdminViewAllSuppliers.xhtml?i=1");
        } catch (IOException ex) {
            Logger.getLogger(SupplierManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onClickChangeSupplier() throws IOException {
        System.out.println("Selected Supplier to Edit: " + selectedSupplier.getSupplierId());
        FacesContext.getCurrentInstance().getExternalContext().redirect("scmAdminEditSelectedSupplier.xhtml?i=1&supplier=" + selectedSupplier.getSupplierName());
    }

    // Add a new Supplier
    public void addSupplier() throws IOException {
        int check = supplierSessionBean.checkSupplier(supplierName);
        if (check == 1) {
            System.out.println("Adding supplier");
            supplierSessionBean.addSupplier(supplierName, supplierAddress, telephoneNum, contactPersonName, mobileNum, faxNum, supplierEmailAddr, "no");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Supplier Successfully Added", "You have successfully added a supplier");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext.getCurrentInstance().getExternalContext().redirect("scmAdminViewAllSuppliers.xhtml?i=1");
        } else if (check == 2) {
            System.out.println("Supplier to add already in database");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Supplier Already Exist", "The supplier you are trying to add is already in database");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (check == 3) {
            System.out.println("Supplier was previously deleted");
            supplierSessionBean.addSupplier(supplierName, supplierAddress, telephoneNum, contactPersonName, mobileNum, faxNum, supplierEmailAddr, "no");
            FacesContext.getCurrentInstance().getExternalContext().redirect("scmAdminViewAllSuppliers.xhtml?i=1");
        }
    }

    // Check for Existing Suppliers
    public void checkExistingSuppliers() {
        int check = supplierSessionBean.checkSupplier(supplierName);
        if (check == 1) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(supplierNameInput.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Valid Supplier ", null));
        } else if (check == 2) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(supplierNameInput.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Supplier Already Exist", "The supplier you are trying to add is already in database"));
        } else if (check == 3) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(supplierNameInput.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "Supplier was previously deleted from database", null));
        }
    }
    
    public void resetAcctStatus(){
        SupplierAcct result = supplierAcctSessionBean.getSupplierAcct(selectedSupplier);
        if(newStatus.isEmpty()){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Please select a status to reset.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        if (result == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Account does not exist", "Please create a Supplier Account for the supplier first.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        if(newStatus.equals(result.getStatus())){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Status is not being reset as it is the same as the initial status.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        supplierAcctSessionBean.resetSupplierAcctStatus(selectedSupplier, newStatus);
        suppliers.clear();
        suppliers = supplierSessionBean.getAllSuppliers();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Account status is being reset successfully.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
