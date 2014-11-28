/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import session.stateless.PartSessionLocal;
import entity.PartHeadquarter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Part;

/**
 *
 * @author Ruoxi
 */
@Named(value = "partManagedBean")
@ViewScoped
public class PartManagedBean implements Serializable {

    @EJB
    private PartSessionLocal partSessionLocal;

    private String partName;
    private List<PartHeadquarter> partList = new ArrayList<>();
    private String activePartName;

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public List<PartHeadquarter> getPartList() {
        return partList;
    }

    public void setPartList(List<PartHeadquarter> partList) {
        this.partList = partList;
    }

    public String getActivePartName() {
        return activePartName;
    }

    public void setActivePartName(String activePartName) {
        this.activePartName = activePartName;
    }

    public void createPart() {

        PartHeadquarter part = partSessionLocal.getPart(partName);

        if (part != null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Part Already Exist", "The part you are trying to create is already registered");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            partSessionLocal.createPart(partName);
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Part Successfully Created", "You have successfully added a part");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void searchPart() {

        PartHeadquarter result = partSessionLocal.getPart(partName);
        if (result == null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No such part", "The part you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            getPartList().clear();

        } else {
            getPartList().clear();
            getPartList().add(result);
        }
    }

    public void viewAllPart() {
        getPartList().clear();

        setPartList(partSessionLocal.getAllPart());
    }

    public void deletePart(String partName) {
        partSessionLocal.deletePart(partName);
        getPartList().clear();
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Part Deleted Successfully", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void resetPartName() {
        if (partSessionLocal.getPart(partName) != null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Two parts cannot have the same name", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            partSessionLocal.resetPartName(activePartName, partName);
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully reset part name", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            getPartList().clear();
            setPartList(partSessionLocal.getAllPart());

        }

    }

}
