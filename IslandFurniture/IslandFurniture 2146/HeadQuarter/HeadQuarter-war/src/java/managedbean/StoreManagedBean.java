/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Factory;
import entity.Store;
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
import session.stateless.FactorySessionLocal;
import session.stateless.StoreSessionLocal;

/**
 *
 * @author Ruoxi
 */
@Named(value = "storeManagedBean")
@SessionScoped
public class StoreManagedBean implements Serializable {

    @EJB
    private StoreSessionLocal storeSessionLocal;
    @EJB
    private FactorySessionLocal factorySessionLocal;
    @Inject
    private ProductionOrderManagedBean productionOrderManagedBean;

    @PostConstruct
    public void init() {
        List<Factory> factoryList = factorySessionLocal.getAllFactory();
        for (Factory factory : factoryList) {
            factoryNameList.add(factory.getFactoryName());
        }
    }

    private String storeName;
    private String location;
    private List<Store> storeList = new ArrayList<>();
    private String activeStoreName;
    private String factoryName;
    private List<String> factoryNameList = new ArrayList<>();
    private Factory factory;
  	private String country;
    private String address;
    private String postal;
    private String phone;
    private String email;
    private Store selectedStore;
    public void setProductionOrderManagedBean(ProductionOrderManagedBean productionOrderManagedBean) {
        this.productionOrderManagedBean = productionOrderManagedBean;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
    }

    public String getActiveStoreName() {
        return activeStoreName;
    }

    public void setActiveStoreName(String activeStoreName) {
        this.activeStoreName = activeStoreName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public List<String> getFactoryNameList() {
        return factoryNameList=refreshFactoryList();
    }

    public void setFactoryNameList(List<String> factoryNameList) {
        this.factoryNameList = factoryNameList;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public void createStore() {
        Store store = storeSessionLocal.getStore(storeName);

        if (store != null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Store Already Exist", "The store you are trying to create is already registered");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            storeSessionLocal.createStore(storeName, country, address, postal, phone, email);
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Store Successfully Created", "You have successfully added a store");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

    public void searchStore() {

        Store result = storeSessionLocal.getStore(storeName);

        if (result == null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No such store", "The store you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            getStoreList().clear();
        } else {
            getStoreList().clear();
            getStoreList().add(result);

        }
    }

    public void viewAllStore() {
        storeList.clear();
        storeList = storeSessionLocal.getAllStore();

    }

    public void deleteStore(String storeName) {
        storeSessionLocal.deleteStore(storeName);
        storeList.clear();
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Factory Deleted Successfully", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void resetStoreName() {
        if (storeSessionLocal.getStore(storeName) != null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Two stores cannot have the same name", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            storeSessionLocal.resetStoreName(activeStoreName, storeName);
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully reset store name", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            storeList.clear();
            storeList = storeSessionLocal.getAllStore();
        }

    }

    public void resetStoreLocation() {
        storeSessionLocal.resetStoreLocation(activeStoreName, location);
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully reset store location", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        storeList.clear();
        storeList = storeSessionLocal.getAllStore();
    }

    public void setFirstDefault() {
//        Factory result = factorySessionLocal.getFactoryByName(factoryName);
//        if (result == null) {
//            FacesMessage msg;
//            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No such factory", "The facotry does not exist");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        } else {
//            storeSessionLocal.setFirstDefaultFactory(activeStoreName, factoryName);
//            FacesMessage msg;
//            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully set first default factory", "");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//            storeList.clear();
//            storeList = storeSessionLocal.getAllStore();
//        }

    }

    public void setSecondDefault() {
//        Factory result = factorySessionLocal.getFactoryByName(factoryName);
//        if (result == null) {
//            FacesMessage msg;
//            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No such factory", "The facotry does not exist");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        } else {
//            storeSessionLocal.setSecondDefaultFactory(activeStoreName, factoryName);
//            FacesMessage msg;
//            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully set second default factory", "");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//            storeList.clear();
//            storeList = storeSessionLocal.getAllStore();
//        }

    }

    public void loadFactory() {
        setFactory(factorySessionLocal.getFactoryByName(factoryName));

    }

    public List<String> refreshFactoryList() {
        factoryNameList.clear();
        List<Factory> factoryList = factorySessionLocal.getAllFactory();
        for (Factory factory : factoryList) {
            factoryNameList.add(factory.getFactoryName());
        }
        return factoryNameList;
    }

    public void setSelectedStoreRedirect(Store store) throws IOException {

        selectedStore = store;

        FacesContext.getCurrentInstance().getExternalContext().redirect("ViewSelectedStore.xhtml");

    }

    public void reDirectEditStore() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("EditSelectedStore.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FactoryManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void saveStore() {
        storeSessionLocal.editSelectedStore(selectedStore);

        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully saved the Store", "Success");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("ViewSelectedStore.xhtml");
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
     * @return the postal
     */
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
     * @return the selectedStore
     */
    public Store getSelectedStore() {
        return selectedStore;
    }

    /**
     * @param selectedStore the selectedStore to set
     */
    public void setSelectedStore(Store selectedStore) {
        this.selectedStore = selectedStore;
    }

}
