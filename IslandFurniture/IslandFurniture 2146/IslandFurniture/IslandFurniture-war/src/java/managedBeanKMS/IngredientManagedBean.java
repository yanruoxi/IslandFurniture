/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeanKMS;

import entity.kms.Ingredient;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import managedbean.SupplierManagedBean;
import session.stateless.kms.IngredientSessionBeanLocal;

/**
 *
 * @author Meiling
 */
@Named(value = "ingredientManagedBean")
@SessionScoped
public class IngredientManagedBean implements Serializable {

    @EJB
    private IngredientSessionBeanLocal ingredientSessionBean;

    @Inject
    private KitchenSupplierManagedBean kitchenSupplierManagedBean;

    public IngredientManagedBean() {
    }

    // Variables
    private List<Ingredient> ingredients; // For View All Ingredients
    private List<Ingredient> filteredIngredients; // For filtering in View All Ingredients
    private Ingredient selectedIngredient;
    private List<String> ingredientTypeList = new ArrayList<String>();

    private List<String> ingredientNameList;

    // Add Ingredient
    private String ingredientName;
    private int klotSize;
    private int kleadTime;
    private String ingredientType;

    // Set Default Supplier
    private double unitPrice;

    private int kqty;
    private int safetystock;

    @PostConstruct
    public void init() {

        ingredients = ingredientSessionBean.getAllIngredients();
        ingredientNameList = ingredientSessionBean.getAllIngredientNames();
        ingredientTypeList.add("Vegetable");
        ingredientTypeList.add("Poultry");
        ingredientTypeList.add("Seafood");
        ingredientTypeList.add("Noodle");
        ingredientTypeList.add("Grain Product");
        ingredientTypeList.add("Dairy Product");
        ingredientTypeList.add("Spices and Herbs");
        ingredientTypeList.add("Condiment");
        ingredientTypeList.add("Fruit");
        ingredientTypeList.add("Syrup");
        ingredientTypeList.add("Dry Goods");
        ingredientTypeList.add("Manufactured Items");
        ingredientTypeList.add("Beverage");

    }

    // GETTERS AND SETTERS
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredientNameList() {
        return ingredientNameList;
    }

    public void setIngredientNameList(List<String> ingredientNameList) {
        this.ingredientNameList = ingredientNameList;
    }

    public List<Ingredient> getFilteredIngredients() {
        return filteredIngredients;
    }

    public void setFilteredIngredients(List<Ingredient> filteredIngredients) {
        this.filteredIngredients = filteredIngredients;
    }

    public Ingredient getSelectedIngredient() {
        return selectedIngredient;
    }

    public void setSelectedIngredient(Ingredient selectedIngredient) {
        this.selectedIngredient = selectedIngredient;
    }

    public List<String> getIngredientTypeList() {
        return ingredientTypeList;
    }

    public void setIngredientTypeList(List<String> ingredientTypeList) {
        this.ingredientTypeList = ingredientTypeList;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getKlotSize() {
        return klotSize;
    }

    public void setKlotSize(int klotSize) {
        this.klotSize = klotSize;
    }

    public int getKleadTime() {
        return kleadTime;
    }

    public void setKleadTime(int kleadTime) {
        this.kleadTime = kleadTime;
    }

    public String getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getKqty() {
        return kqty;
    }

    public void setKqty(int kqty) {
        this.kqty = kqty;
    }

    public int getSafetystock() {
        return safetystock;
    }

    public void setSafetystock(int safetystock) {
        this.safetystock = safetystock;
    }

    // END OF GETTERS AND SETTERS
    // Delete An Ingredient
    public void deleteIngredient() {
        boolean deletedIngredient = ingredientSessionBean.deleteIngredient(selectedIngredient);
        if (deletedIngredient) {
            ingredients.remove(selectedIngredient);
            selectedIngredient = null;
            ingredients = ingredientSessionBean.getAllIngredients();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successfully Deleted Ingredient");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Failed to delete Ingredient as it is one of the Recipe Items.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void redirectToEditIngredient() {
        System.out.println("redirectToEditIngredient:" + selectedIngredient);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("kmsEditIngredient.xhtml?i=2");
        } catch (IOException ex) {
            Logger.getLogger(SupplierManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Edit Ingredient
    public void editIngredient() {
        System.out.println("editIngredient");
        System.out.println("selectedIngredient:" + selectedIngredient.getIngredientName());
        System.out.println("selectedIngredient lot size:" + selectedIngredient.getKlotSize());

        boolean edited = ingredientSessionBean.editIngredient(selectedIngredient);
        if (edited) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Ingredient has successfully been updated.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            ingredients.clear();
            ingredients = ingredientSessionBean.getAllIngredients();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Failed to update Ingredient because no changes were made.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    // Add a new Ingredient
    public void addIngredient() throws IOException {
        System.out.println("addIngredient");
        if (klotSize == 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Lot Size must be greater than 0");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        if (kleadTime == 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Lead Time must be greater than 0");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        boolean addedIngredient = ingredientSessionBean.addIngredient(ingredientName, klotSize, kleadTime, ingredientType);
        if (!addedIngredient) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Failed to add as Ingredient has already been added previously.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Ingredient added successfully.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            ingredients = ingredientSessionBean.getAllIngredients();

        }
        ingredientName = "";
        klotSize = 1;
        kleadTime = 1;
        ingredientType = "";
        ingredients = ingredientSessionBean.getAllIngredients();
    }

    // Set a Default Supplier for Ingredient
    public void setDefaultSupplier() {
        System.out.println("setDefaultSupplier");
        // Check that Ingredient Name is not null
        if (ingredientName.equals(null) || ingredientName == "") {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Ingredient must be selected");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        if (unitPrice == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Unit Price must be not be 0");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        Ingredient p = ingredientSessionBean.getSelectedIngredientToAdd(ingredientName); // Retrieve Ingredient object based on Ingredient Name
        // Set Default Supplier
        boolean success = ingredientSessionBean.setDefaultSupplier(kitchenSupplierManagedBean.getSelectedKitchenSupplierForIngredient(), p, unitPrice);
        // If set default supplier is successful
        if (success) {
            ingredients = ingredientSessionBean.getAllIngredients();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successfully Set Default Supplier to Ingredient");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Default Supplier for Ingredeint already exists");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    // Update safety stock and ingredient
    public void updateKmsInventory() {
        if (safetystock != 0 & kqty != 0) {
            ingredientSessionBean.updateKmsInventory(selectedIngredient, safetystock, kqty);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Safety Stock and Inventory Level updated successfully");
            FacesContext.getCurrentInstance().addMessage(null, message);
            ingredients.clear();
            ingredients = ingredientSessionBean.getAllIngredients();

        } else if (safetystock == 0 & kqty != 0) {
            ingredientSessionBean.updateKmsInventory(selectedIngredient, 0, kqty);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Inventory Level updated successfully");
            FacesContext.getCurrentInstance().addMessage(null, message);
            ingredients.clear();
            ingredients = ingredientSessionBean.getAllIngredients();

        } else if (kqty == 0 & safetystock != 0) {
            ingredientSessionBean.updateKmsInventory(selectedIngredient, safetystock, 0);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Safety Stock Level updated successfully");
            FacesContext.getCurrentInstance().addMessage(null, message);
            ingredients.clear();
            ingredients = ingredientSessionBean.getAllIngredients();

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Please enter value to update");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    // Check stock level
    public void checkStock() {
        String msg = "The following ingredient(s) are running low in stock:";
        List<Long> lowStockList = new ArrayList<Long>();
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getKitchenInventory().getKsafetyStockLevel() > ingredients.get(i).getKitchenInventory().getKqty()) {
                lowStockList.add(ingredients.get(i).getId());
                msg += "[" + ingredients.get(i).getIngredientName() + "]";
            }
        }
        msg += ".";
        if (!lowStockList.isEmpty()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    // Check zero stock level
    public void checkZeroStock() {
        String oosMessage = "The following ingredient(s) are Out of Stock";
        List<Long> oosList = new ArrayList<Long>();
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getKitchenInventory().getKqty() == 0) {
                oosList.add(ingredients.get(i).getId());
                oosMessage += "[" + ingredients.get(i).getIngredientName() + "]";
            }
        }
        oosMessage += ".";
        if (!oosList.isEmpty()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", oosMessage);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
