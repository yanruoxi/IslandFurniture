/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Inventory;
import entity.Part;
import entity.Furniture;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import session.stateless.InventorySessionBeanLocal;
import session.stateless.PartSessionBeanLocal;

/**
 *
 * @author ZiGui
 */
@ManagedBean(name = "inventoryManagedBean")
@ViewScoped
public class InventoryManagedBean {

    @EJB
    private InventorySessionBeanLocal inventorySessionBean;

    public InventoryManagedBean() {
    }

    private List<Part> inventoryPartList;
    private List<Furniture> inventoryFurnitureList;
    private List<Furniture> filteredFurnitureInventory;
    private List<Part> filteredPartInventory;
    private Part selectedPartInventory;
    private Furniture selectedFurnitureInventory;
    private int inventoryQuantity;
    private String partName;
    private int inventoryId;
    

//    public List<Inventory> getInventoryList() {
//        inventoryList = inventorySessionBean.getAllInventory();
//        return inventoryList;
//    }
    public List<Part> getInventoryPartList() {
        inventoryPartList = inventorySessionBean.getPartInventory();
        return inventoryPartList;
    }

    public List<Furniture> getInventoryFurnitureList() {
        inventoryFurnitureList = inventorySessionBean.getFurnitureInventory();
        return inventoryFurnitureList;
    }

    public List<Part> getFilteredPartInventory() {
        return filteredPartInventory;
    }

    public void setFilteredPartInventory(List<Part> filteredPartInventory) {
        this.filteredPartInventory = filteredPartInventory;
    }

    public List<Furniture> getFilteredFurnitureInventory() {
        return filteredFurnitureInventory;
    }

    public void setFilteredFurnitureInventory(List<Furniture> filteredFurnitureInventory) {
        this.filteredFurnitureInventory = filteredFurnitureInventory;
    }

    public InventorySessionBeanLocal getInventorySessionBean() {
        return inventorySessionBean;
    }

    public void setInventorySessionBean(InventorySessionBeanLocal inventorySessionBean) {
        this.inventorySessionBean = inventorySessionBean;
    }

    public int getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(int inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public Part getSelectedPartInventory() {
        return selectedPartInventory;
    }

    public void setSelectedPartInventory(Part selectedPartInventory) {
        this.selectedPartInventory = selectedPartInventory;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Furniture getSelectedFurnitureInventory() {
        return selectedFurnitureInventory;
    }

    public void setSelectedFurnitureInventory(Furniture selectedFurnitureInventory) {
        this.selectedFurnitureInventory = selectedFurnitureInventory;
    }
    
    

    public void updateInventoryPart() {
        System.out.println("updating inventory: " + selectedPartInventory);
        System.out.println("quantity: " + inventoryQuantity);

        if (inventoryQuantity < 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value cannot be lesser than 0", "Please re-enter quantity");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
           inventorySessionBean.updateInventoryPart(selectedPartInventory.getPartName(), inventoryQuantity);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Part Inventory Successfully Updated", "You have successfully updated inventory " + selectedPartInventory.getPartName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void updateInventoryFurniture() {
        System.out.println("updating inventory: " + selectedFurnitureInventory);
        System.out.println("quantity: " + inventoryQuantity);

        if (inventoryQuantity < 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value cannot be lesser than 0", "Please re-enter quantity");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            inventorySessionBean.updateInventoryFurniture(selectedFurnitureInventory.getFurnitureName(), inventoryQuantity);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Furniture Inventory Successfully Updated", "You have successfully updated inventory " + selectedFurnitureInventory.getFurnitureName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
