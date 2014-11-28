/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Part;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import session.stateless.PartSessionBeanLocal;
import session.stateless.PartWebSessionLocal;

/**
 *
 * @author Ruoxi
 */
@Named(value = "partManagedBean")
@ViewScoped
public class PartManagedBean implements Serializable {

    @EJB
    PartWebSessionLocal partWebSessionLocal;

    @EJB
    PartSessionBeanLocal partSessionBeanLocal;


    private String partName;
    private List<Part> partList = new ArrayList<>();
    private String activePartName;

    public String getPartName() {
        return partName;
    }
    public void setPartName(String partName) {
        this.partName = partName;
    }
    public List<Part> getPartList() {
        return partList;
    }
    public void setPartList(List<Part> partList) {
        this.partList = partList;
    }
    public String getActivePartName() {
        return activePartName;
    }
    public void setActivePartName(String activePartName) {
        this.activePartName = activePartName;
    }
    
    public void pullPartHeadquarter() {
        if (partWebSessionLocal.checkPartExist(partName)) {

            if (partSessionBeanLocal.getPartByName(partName) != null) {

                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "You have already pulled the part from headquarter", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            } else {
                partWebSessionLocal.PullPartHeadquarter(partName);
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Seccessfully Pulled Part from Headquarter", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }

        } else {

            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No such part exists in headquarter", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }
    
      public void viewAllPart() {
        getPartList().clear();
        partList=partSessionBeanLocal.getAllPart();
    }
      
       public void searchPart() {

        Part result = partSessionBeanLocal.getPartByName(partName);
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
       
        public void deletePart(String partName) {
        partSessionBeanLocal.deletePart(partName);
        getPartList().clear();
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Part Deleted Successfully", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

  
}
