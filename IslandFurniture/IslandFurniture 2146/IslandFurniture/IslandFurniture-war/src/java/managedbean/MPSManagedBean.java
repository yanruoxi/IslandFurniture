/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import entity.Furniture;
import entity.MPS;
import entity.SalesPlan;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import session.stateless.MPSSessionBeanLocal;

/**
 *
 * @author li
 */
@Named(value = "MPSManagedBean")
@ViewScoped
public class MPSManagedBean implements Serializable{
    @EJB
    private MPSSessionBeanLocal mpsSessionBeanLocal ;
    @ManagedProperty(value = "#{loginManagedBean}")
    private LoginManagedBean loginManagedBean;
    private String time;
    private String furnitureName;
    private String partName;
    private Furniture furniture = new Furniture();
    private List<String> furnitures = new ArrayList<String>();
    private List<String> parts = new ArrayList<String>();
    private SalesPlan plan = new SalesPlan();
    private List<MPS> weeklyPlan =new ArrayList<>();
    
    @PostConstruct
    public void init() {
           furnitures = mpsSessionBeanLocal.getFurnitures();
    }

    public List<MPS> getWeeklyPlan() {
        return weeklyPlan;
    }
     
    public void setWeeklyPlan(List<MPS> weeklyPlan) {
        this.weeklyPlan = weeklyPlan;
    }

    public List<String> getFurnitures() {
        return furnitures;
    }

    public void setFurnitures(List<String> furnitures) {
        this.furnitures = furnitures;
    }

    public List<String> getParts() {
        return parts;
    }

    public void setParts(List<String> parts) {
        this.parts = parts;
    }

    /**
     * Creates a new instance of MPSManagedBean
     */
    public void setLoginManagedBean(LoginManagedBean loginManagedBean) {
        this.loginManagedBean = loginManagedBean;
    }
     
    public MPSManagedBean() {
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public SalesPlan getPlan() {
        return plan;
    }

    public void setPlan(SalesPlan plan) {
        this.plan = plan;
    }
    
    public void searchMPS() {
        if (!time.matches("\\d{2}-\\d{4}")) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error format of time", "Error format of time");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else {
            plan = mpsSessionBeanLocal.viewSalesPlan(time, furnitureName);
            if(plan==null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No production plan for the furniture from HQ", "No production plan for the furniture from HQ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
    
    public void searchPlan() {
         if (!time.matches("\\d{2}-\\d{4}")) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error format of time", "Error format of time");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else {
            weeklyPlan = mpsSessionBeanLocal.generateMPS(time, furnitureName, null);
            if(weeklyPlan==null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No production plan for the furniture from HQ", "No production plan for the furniture from HQ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
        
    }
    
    public void searchMRP() {
        System.out.println("test1");
        if (!time.matches("\\d{2}-\\d{4}")) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error format of time", "Error format of time");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else {
            weeklyPlan = mpsSessionBeanLocal.generateMRP(time,furnitureName,partName);
            if(weeklyPlan==null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No production plan for the furniture from HQ", "No production plan for the furniture from HQ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
    
    public void onCellEdit(CellEditEvent event) {
        System.out.println("wangyan");
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        MPS data = context.getApplication().evaluateExpressionGet(context, "#{weeklyPlan}", MPS.class);
        long MPSId=data.getId();
        mpsSessionBeanLocal.updateMPS((Integer)newValue, MPSId);
        
        searchMRP();

        
    }
    
    public void getPartNamesForFurniture() {
        parts = mpsSessionBeanLocal.getParts(furnitureName);
    }
}
