/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Furniture;
import entity.SystemUser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import session.stateless.ItemSessionBeanLocal;
import session.stateless.LogSessionLocal;

/**
 *
 * @author wangyan
 */
@Named(value = "furManageBean")
@ViewScoped
public class FurManageBean implements Serializable {

    @EJB
    private ItemSessionBeanLocal itemSB;
    @EJB
    private LogSessionLocal logSessionLocal;

    private String furnitureName;
    private Long furnitureId;
    private String statusMessage;
    private String newFurnitureName;
    private List<Furniture> furnitureList = new ArrayList<>();

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String FurnitureName) {
        this.furnitureName = FurnitureName;
    }

    public Long getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(Long furnitureId) {
        this.furnitureId = furnitureId;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getNewFurnitureName() {
        return newFurnitureName;
    }

    public void setNewFurnitureName(String newFurnitureName) {
        this.newFurnitureName = newFurnitureName;
    }

    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }

    public void setFurnitureList(List<Furniture> furnitureList) {
        this.furnitureList = furnitureList;
    }

    /**
     * Creates a new instance of FurManageBean
     */
    public FurManageBean() {
    }

    public void searchFurniture() {
        Furniture result = itemSB.getFurniture(furnitureName);
        if (result == null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No such furniture ", "The furniture you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            furnitureList.clear();
            furnitureList.add(result);
        }

    }

    public void viewAllFurniture() {
        //test
        System.out.println("test for method viewAllFurniture()1 in furmanageBean");
        furnitureList.clear();
        System.out.println("test for method viewAllFurniture()2 in furmanagebean");

        furnitureList = itemSB.getAllFurniture();
        System.out.println("test for method viewAllFurniture()3 furmanagebean");
    }

    public void deleteFurniture(ActionEvent event) {
         //test

        System.out.println("test for whether the deleteFurniture has been called in FurManageBean");

        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(this.furnitureName, "Deleted furniture", cal);

        try {
            System.out.println("testing deletefurniture() from furManagebean");
            furnitureId = itemSB.DelectFurniture(furnitureName);
            System.out.println("testing 2 deletefurniture() from furManagebean");
            if (furnitureId != -1l) {
                statusMessage = "New furniture delete successfully";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "DELETE FURNITURE RESULT: " + statusMessage, ""));

            } else {
                statusMessage = "furniture deleted failed. furniture name not exists";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "DELETE FURNITURE RESULT: " + statusMessage, ""));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void deleteFurniture2(ActionEvent event) {

        furnitureName = (String) event.getComponent().getAttributes().get("furnitureName");

        System.out.println("test successfully in deleteFurniture 2 in furmanagementbean");

        System.out.println("test for whether the deleteFurniture has been called in FurManageBean");

        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(furnitureName, "Deleted furniture", cal);

        try {
            System.out.println("testing deletefurniture() from furManagebean");
            furnitureId = itemSB.DelectFurniture(furnitureName);
            System.out.println("testing 2 deletefurniture() from furManagebean");
            if (furnitureId != -1l) {
                statusMessage = "furniture delete successfully";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "DELETE FURNITURE RESULT: " + statusMessage, ""));
                furnitureList = itemSB.getAllFurniture();
            } else {
                statusMessage = "furniture deleted failed. furniture name not exists";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "DELETE FURNITURE RESULT: " + statusMessage, ""));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void saveNewFurniture(ActionEvent event) {
        try {
            System.out.println("testing 1" + furnitureName);

            furnitureId = itemSB.AddFurniture(furnitureName);
            System.out.println("testing 2");
            if (furnitureId != -2l) {
                statusMessage = "New furniture saved successfully";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ADD NEW FURNITURE RESULT: " + statusMessage + "(new furniture id is " + furnitureId + ")", ""));

            } else {
                statusMessage = "furniture saved failed. furniture name already exists";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ADD NEW FURNITURE RESULT: " + statusMessage, ""));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void editFurniture() {
        try {
            System.out.println("testing 3");
            furnitureId = itemSB.editFurniture2(getFurnitureName(), getNewFurnitureName());
            System.out.println("testing 4");
            if (furnitureId != -2l && furnitureId != -1) {
                statusMessage = "furniture edited successfully";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EDIT FURNITURE RESULT: " + statusMessage + "(new furniture id is " + furnitureId + ")" + "new furniture name is " + getNewFurnitureName(), ""));

            } else if (furnitureId == -2) {
                statusMessage = "furniture saved failed. original furniture name not exsit";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "EDIT FURNITURE RESULT: " + statusMessage, ""));

            } else {
                statusMessage = "furniture saved failed. new furniture name has already been used,please provide a new valid furniture name";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "EDIT FURNITURE RESULT: " + statusMessage, ""));

            }

        } catch (Exception ex) {
        }
    }

}
