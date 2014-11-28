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
import javax.inject.Named;
import session.stateless.FactorySessionLocal;
import entity.Factory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Ruoxi
 */
@Named(value = "factoryManagedBean")
@SessionScoped
public class FactoryManagedBean implements Serializable {

    @EJB
    private FactorySessionLocal factorySessionLocal;
    private String factoryName;
    private String location;
    private List<Factory> factoryList = new ArrayList<>();
    private String activeFactoryName;
    private String status;

    private String country;
    private String address;
    private String postal;
    private String phone;
    private String email;
    private Factory selectedFactory;

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Factory> getFactoryList() {
        return factoryList;
    }

    public void setFactoryList(List<Factory> factoryList) {
        this.factoryList = factoryList;
    }

    public String getActiveFactoryName() {
        return activeFactoryName;
    }

    public void setActiveFactoryName(String activeFactoryName) {
        this.activeFactoryName = activeFactoryName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void createFactory() {
        Factory factory = factorySessionLocal.getFactoryByName(factoryName);

        if (factory != null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Factory Already Exist", "The factory you are trying to create is already registered");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            factorySessionLocal.createFactory(factoryName, country, address, postal, phone, email);

            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Factory Successfully Created", "You have successfully added a factory");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

    public void searchFactoryByName() {
        Factory result = factorySessionLocal.getFactoryByName(factoryName);
        if (result == null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No such factory", "The factory you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            factoryList.clear();
        } else {
            factoryList.clear();
            factoryList.add(result);
        }
    }

    public void searchFactoryByLocation() {
        factoryList = factorySessionLocal.getFactoryByLocation(country);
        if (factoryList.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No such factory", "The factory you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
        }
    }

    public void viewAllFactory() {
        factoryList.clear();
        factoryList = factorySessionLocal.getAllFactory();
    }

    public void deleteFactory(String factoryName) {
        factorySessionLocal.deleteFactory(factoryName);
        factoryList.clear();
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Factory Deleted Successfully", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        RequestContext.getCurrentInstance().update("factoryDataTable");
    }

    public void resetFactoryName() {

        if (factorySessionLocal.getFactoryByName(factoryName) != null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Factories cannot have the same name", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            factorySessionLocal.resetFactoryName(activeFactoryName, factoryName);
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully reset factory name", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            factoryList.clear();
            factoryList = factorySessionLocal.getAllFactory();
        }

    }

    public void resetFactoryLocation() {

        factorySessionLocal.resetFactoryLocation(activeFactoryName, location);
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully reset factory location", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        factoryList.clear();
        factoryList = factorySessionLocal.getAllFactory();
    }

    public void resetFactoryStatus() {
        System.out.println("activeFactoryName: " + activeFactoryName + "factory name: " + factoryName);
        factorySessionLocal.resetFactoryStatus(activeFactoryName, status);
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully reset factory status", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        factoryList.clear();
        factoryList = factorySessionLocal.getAllFactory();
    }

    public void setSelcetedFactoryRedirect(Factory factory) throws IOException {

        selectedFactory = factory;

        FacesContext.getCurrentInstance().getExternalContext().redirect("ViewSelectedFactory.xhtml");

    }
    
    
       public void saveFactory() {
           
       factorySessionLocal.editSelectedFactory(selectedFactory);
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully saved the factory", "Success");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("ViewSelectedFactory.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FactoryManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void reDirectEditFactory() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("EditSelectedFactory.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FactoryManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    



    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal() {
        return postal;
    }

    /**
     * @param postal the postal to set
     */
    public void setPostal(String postal) {
        this.postal = postal;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the selectedFactory
     */
    public Factory getSelectedFactory() {
        return selectedFactory;
    }

    /**
     * @param selectedFactory the selectedFactory to set
     */
    public void setSelectedFactory(Factory selectedFactory) {
        this.selectedFactory = selectedFactory;
    }

}
