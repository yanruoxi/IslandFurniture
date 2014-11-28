/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Store;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import session.stateless.InitWarehouseSessionBeanLocal;

/**
 *
 * @author ZiGui
 */
@Named(value = "warehouseManagedBean")
@SessionScoped
public class WarehouseManagedBean implements Serializable {

    @EJB
    InitWarehouseSessionBeanLocal initWarehouseSessionBean;

    private Store store;
    private Long warehouseId;
    private String warehouseAddress;
    private ArrayList<String> warehouseName = new ArrayList<String>();
    private String name;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public ArrayList<String> getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(ArrayList<String> warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void init() {
        warehouseName.add("Sentosa");
        warehouseName.add("East Coast");
        warehouseName.add("Palawan Beach");
    }

    public void initializeWarehouse(Store store) {
        initWarehouseSessionBean.initializeWarehouse(name, warehouseAddress);

        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "You have successfully initialized warehouse");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }
}
