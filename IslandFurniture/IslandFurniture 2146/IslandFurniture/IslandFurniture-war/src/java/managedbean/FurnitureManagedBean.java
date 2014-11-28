/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Furniture;
import entity.Part;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import session.stateless.FurnitureSessionLocal;
import session.stateless.FurnitureWebSessionLocal;

/**
 *
 * @author Ruoxi
 */
@Named(value = "furnitureManagedBean")
@ViewScoped
public class FurnitureManagedBean implements Serializable {

    @EJB
    FurnitureWebSessionLocal furnitureWebSessionLocal;
    @EJB
    FurnitureSessionLocal furnitureSessionLocal;

    private String furnitureName;
    private List<Furniture> furnitureList = new ArrayList<>();

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }

    public void setFurnitureList(List<Furniture> furnitureList) {
        this.furnitureList = furnitureList;
    }

    public void pullFurnitureHeadquarter() {
        if (furnitureWebSessionLocal.checkFurnitureExist(furnitureName)) {

            if (furnitureSessionLocal.getFurnitureByName(furnitureName) != null) {

                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have already pulled the furniture from headquarter, furniture information is updated", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            } else {
                furnitureWebSessionLocal.PullFurnitureHeadquarter(furnitureName);
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Seccessfully Pulled Furniture from Headquarter", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }

        } else {

            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No such furniture exists in headquarter", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void viewAllFurniture() {
        furnitureList.clear();
        furnitureList = furnitureSessionLocal.getAllFurniture();
    }

    public void searchFurniture() {
        Furniture result = furnitureSessionLocal.getFurnitureByName(furnitureName);

        if (result == null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No such furniture", "The furniture you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            furnitureList.clear();

        } else {
            furnitureList.clear();
            furnitureList.add(result);
        }
    }
 public void deleteFurniture(String furnitureName) {
     
     furnitureSessionLocal.deleteFurniture(furnitureName);
      furnitureList.clear();
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Furniture Deleted Successfully", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }
}
