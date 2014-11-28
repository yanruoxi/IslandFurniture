/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeanKMS;

import entity.kms.Ingredient;
import entity.kms.KitchenSupplier;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
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
import session.stateless.kms.IngredientSessionBeanLocal;
import session.stateless.kms.KitchenSupplierSessionBeanLocal;

/**
 *
 * @author ZiGui
 */
@Named(value = "kitchenSupplierManagedBean")
@ViewScoped
public class KitchenSupplierManagedBean implements Serializable {

    @EJB
    private IngredientSessionBeanLocal ingredientSessionBean;
    @EJB
    private KitchenSupplierSessionBeanLocal kitchenSupplierSessionBean;

    @PostConstruct
    public void init() {

        suppliers = kitchenSupplierSessionBean.getAllKitchenSuppliers(); // Retrieve list of suppliers from session bean
        ingredients = ingredientSessionBean.getIngredientString();
        FacesContext fc = FacesContext.getCurrentInstance();
        String value = (String) fc.getExternalContext().getRequestParameterMap().get("kitchenSupplier");
        ksupplierName = value;
        System.out.println(ksupplierName);
        supplierToEdit = kitchenSupplierSessionBean.getOneKitchenSupplierByName(ksupplierName);
        System.err.println("int(): Supplier Name = " + ksupplierName);
    }

    public KitchenSupplierManagedBean() {
    }

    // Variables
    private List<KitchenSupplier> suppliers; // For View All Suppliers
    private List<KitchenSupplier> filteredSuppliers; // For filtering in View All Suppliers
    private KitchenSupplier selectedSupplier;
    private String[] selectedIngredient;   // For Add Ingredient to Supplier
    private List<String> ingredients; // For Add Ingredient to Supplier
    private List<String> ingredientNamesForSelectedSupplier; // For propogating Ingredient Names based on selected Supplier

    private String ksupplierId;
    private String ksupplierName;
    private String ksupplierAddress;
    private String ktelephone;
    private String kcontactPersonName;
    private String kmobileNum;
    private String kfaxNum;
    private String ksupplierEmailAddress;
    private KitchenSupplier supplierToEdit;
    private UIComponent supplierNameInput;
    private String newStatus;
    
    // Set Default Supplier
    private KitchenSupplier selectedKitchenSupplierForIngredient;

    // GETTERS AND SETTERS
    public List<KitchenSupplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<KitchenSupplier> suppliers) {
        this.suppliers = suppliers;
    }

    public List<KitchenSupplier> getFilteredSuppliers() {
        return filteredSuppliers;
    }

    public void setFilteredSuppliers(List<KitchenSupplier> filteredSuppliers) {
        this.filteredSuppliers = filteredSuppliers;
    }

    public KitchenSupplier getSelectedSupplier() {
        return selectedSupplier;
    }

    public void setSelectedSupplier(KitchenSupplier selectedSupplier) {
        this.selectedSupplier = selectedSupplier;
    }

    public String[] getSelectedIngredient() {
        return selectedIngredient;
    }

    public void setSelectedIngredient(String[] selectedIngredient) {
        this.selectedIngredient = selectedIngredient;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredientNamesForSelectedSupplier() {
        return ingredientNamesForSelectedSupplier;
    }

    public void setIngredientNamesForSelectedSupplier(List<String> ingredientNamesForSelectedSupplier) {
        this.ingredientNamesForSelectedSupplier = ingredientNamesForSelectedSupplier;
    }

    public KitchenSupplier getSupplierToEdit() {
        return supplierToEdit;
    }

    public void setSupplierToEdit(KitchenSupplier supplierToEdit) {
        this.supplierToEdit = supplierToEdit;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public UIComponent getSupplierNameInput() {
        return supplierNameInput;
    }

    public void setSupplierNameInput(UIComponent supplierNameInput) {
        this.supplierNameInput = supplierNameInput;
    }

    public String getKsupplierId() {
        return ksupplierId;
    }

    public void setKsupplierId(String ksupplierId) {
        this.ksupplierId = ksupplierId;
    }

    public String getKsupplierName() {
        return ksupplierName;
    }

    public void setKsupplierName(String ksupplierName) {
        this.ksupplierName = ksupplierName;
    }

    public String getKsupplierAddress() {
        return ksupplierAddress;
    }

    public void setKsupplierAddress(String ksupplierAddress) {
        this.ksupplierAddress = ksupplierAddress;
    }

    public String getKtelephone() {
        return ktelephone;
    }

    public void setKtelephone(String ktelephone) {
        this.ktelephone = ktelephone;
    }

    public String getKcontactPersonName() {
        return kcontactPersonName;
    }

    public void setKcontactPersonName(String kcontactPersonName) {
        this.kcontactPersonName = kcontactPersonName;
    }

    public String getKmobileNum() {
        return kmobileNum;
    }

    public void setKmobileNum(String kmobileNum) {
        this.kmobileNum = kmobileNum;
    }

    public String getKfaxNum() {
        return kfaxNum;
    }

    public void setKfaxNum(String kfaxNum) {
        this.kfaxNum = kfaxNum;
    }

    public String getKsupplierEmailAddress() {
        return ksupplierEmailAddress;
    }

    public void setKsupplierEmailAddress(String ksupplierEmailAddress) {
        this.ksupplierEmailAddress = ksupplierEmailAddress;
    }
    
    // Set Default Supplier
    public KitchenSupplier getSelectedKitchenSupplierForIngredient() {
        return selectedKitchenSupplierForIngredient;
    }

    public void setSelectedKitchenSupplierForIngredient(KitchenSupplier selectedKitchenSupplierForIngredient) {
        this.selectedKitchenSupplierForIngredient = selectedKitchenSupplierForIngredient;
    }
    

    // END OF GETTERS AND SETTERS
    // Add a new Supplier
    public void addSupplier() throws IOException {
        int check = kitchenSupplierSessionBean.checkKitchenSupplier(ksupplierName);
        if (check == 1) {
            System.out.println("Adding supplier");
            kitchenSupplierSessionBean.addKitchenSupplier(ksupplierName, ksupplierAddress, ktelephone, kcontactPersonName, kmobileNum, kfaxNum, ksupplierEmailAddress);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Supplier Successfully Added", "You have successfully added a supplier");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext.getCurrentInstance().getExternalContext().redirect("kmsViewAllSuppliers.xhtml?i=1");
        } else if (check == 2) {
            System.out.println("Supplier to add already in database");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Supplier Already Exist", "The supplier you are trying to add is already in database");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (check == 3) {
            System.out.println("Supplier was previously deleted");
            kitchenSupplierSessionBean.addKitchenSupplier(ksupplierName, ksupplierAddress, ktelephone, kcontactPersonName, kmobileNum, kfaxNum, ksupplierEmailAddress);
            FacesContext.getCurrentInstance().getExternalContext().redirect("kmsViewAllSuppliers.xhtml?i=1");
        }
    }

    // Check for Existing Suppliers
    public void checkExistingSuppliers() {
        int check = kitchenSupplierSessionBean.checkKitchenSupplier(ksupplierName);

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

    // Delete A Supplier
    public void deleteSupplier() {
        kitchenSupplierSessionBean.deleteKitchenSupplier(selectedSupplier.getKsupplierId());
        suppliers.remove(selectedSupplier);
        selectedSupplier = null;
        //suppliers.clear();
        suppliers = kitchenSupplierSessionBean.getAllKitchenSuppliers();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Supplier Deleted");
        FacesContext.getCurrentInstance().addMessage(null, message);
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

    // Edit Supplier
    public void editSupplier() {
        System.out.println("editSupplier: " + ksupplierName);
        kitchenSupplierSessionBean.editKitchenSupplier(supplierToEdit.getKsupplierId(), ksupplierName, kcontactPersonName, kmobileNum, kfaxNum, ksupplierEmailAddress, ktelephone);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Supplier Successfully Updated", "You have successfully updated supplier " + ksupplierName);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("kmsViewAllSuppliers.xhtml?i=1");
        } catch (IOException ex) {
            Logger.getLogger(KitchenSupplierManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onClickChangeSupplier() throws IOException {
        System.out.println("Selected Supplier to Edit: " + selectedSupplier.getKsupplierId());
        FacesContext.getCurrentInstance().getExternalContext().redirect("kmsEditSelectedSupplier.xhtml?i=1&kitchenSupplier=" + selectedSupplier.getKsupplierName());
    }

    // Add Ingredient(s) to A Supplier
    public void addIngredientToSupplier() {
        System.out.println("KitchenSupplierManagedBean: addIngredientToSupplier");
        Set<Ingredient> selectedIngredientToAdd = new HashSet<Ingredient>(); // HashSet that stores selectedIngredientToAdd Ingredient objects
        Ingredient p = new Ingredient(); // For Usage in for loop
        for (int i = 0; i < selectedIngredient.length; i++) {
            p = ingredientSessionBean.getSelectedIngredientToAdd(selectedIngredient[i]); // Retrieve Ingredient object using Ingredient Name
            selectedIngredientToAdd.add(p); // Add retrieved object to HashSet
        }

        boolean success = ingredientSessionBean.addIngredientToSupplier(selectedSupplier, selectedIngredientToAdd);
        if (success) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Ingredient added to Supplier");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Ingredient has already been added to Supplier");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    // Retrieve Ingredient Names based on selected Supplier from drop down list
    public void getIngredientNamesForSelectedSupplierList() {
       ingredientNamesForSelectedSupplier = ingredientSessionBean.getIngredientsForSelectedKitchenSupplier(selectedKitchenSupplierForIngredient);
    }
}
