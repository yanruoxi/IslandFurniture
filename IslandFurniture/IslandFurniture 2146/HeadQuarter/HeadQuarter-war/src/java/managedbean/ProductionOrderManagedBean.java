/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Factory;
import entity.Product;
import entity.ProductionOrder;
import entity.Store;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import session.stateless.FactorySessionLocal;
import session.stateless.ProductSessionLocal;
import session.stateless.ProductionOrderSessionLocal;
import session.stateless.StoreSessionLocal;

/**
 *
 * @author Ruoxi
 */
@Named(value = "productionOrderManagedBean")
@SessionScoped
public class ProductionOrderManagedBean implements Serializable {

    @EJB
    private ProductionOrderSessionLocal productionOrderSessionLocal;
    @EJB
    private ProductSessionLocal productSessionLocal;
    @EJB
    private StoreSessionLocal storeSessionLocal;
    @EJB
    private FactorySessionLocal factorySessionLocal;

    @PostConstruct
    public void init() {
        List<Product> productList = productSessionLocal.getAllProduct();
        for (Product product : productList) {
            getProductNameList().add(product.getProductName());
        }
    }

    private String furnitureName;
    private String month;
    private Integer quantity;
    private String activeStore;
    private String factoryName;
    private String storeName;
    private List<ProductionOrder> productionOrderList = new ArrayList<>();
    private List<String> productNameList = new ArrayList<>();

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getActiveStore() {
        return activeStore;
    }

    public void setActiveStore(String activeStore) {
        this.activeStore = activeStore;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<ProductionOrder> getProductionOrderList() {
        return productionOrderList;
    }

    public void setProductionOrderList(List<ProductionOrder> productionOrderList) {
        this.productionOrderList = productionOrderList;
    }

    public List<String> getProductNameList() {
        return productNameList = refreshProductList();
    }

    public void setProductNameList(List<String> productNameList) {
        this.productNameList = productNameList;
    }

    public void createProductionOrder() {
//        Factory factory = null;
//        Store store = storeSessionLocal.getStore(activeStore);
//      
//
//        if (factoryName.equalsIgnoreCase("1")) {
//            try {
//                System.out.println("first default is" + store.getFirstDefault().getFactoryName());
//                factory = store.getFirstDefault();
//            } catch (NullPointerException e) {
//                System.out.println("no result excepton for first default factory");
//            }
//
//        } else {
//            try {               
//                factory = store.getSecondDefault();
//            } catch (NullPointerException e) {
//                System.out.println("no result excepton for second default factory");
//            }
//        }
//       
//        if (factory == null) {
//            FacesMessage msg;
//            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "You have to set default factory first", "");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        } else {
//
//            if (factorySessionLocal.checkAvailability(factory.getFactoryName())) {
//
//                productionOrderSessionLocal.createProductionOrder(furnitureName, month, quantity, activeStore, factory.getFactoryName());
//                FacesMessage msg;
//                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Production Order Successfully Created", "You have successfully created a production order");
//                FacesContext.getCurrentInstance().addMessage(null, msg);
//            } else {
//                FacesMessage msg;
//                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The selected factory is not available", "");
//                FacesContext.getCurrentInstance().addMessage(null, msg);
//            }
//
//        }

    }

    public void searchAllProductionOrder() {
        productionOrderList.clear();
        productionOrderList = productionOrderSessionLocal.getAllProductionOrder();
    }

    public void searchByStore() {
        productionOrderList.clear();
        productionOrderList = productionOrderSessionLocal.getProductionOrderByStoreName(storeName);

    }

    public void searchByFacory() {
        productionOrderList.clear();
        productionOrderList = productionOrderSessionLocal.getProductionOrderByFactory(factoryName);

    }

    public List<String> refreshProductList() {
        System.out.println("production order managed bean started");
        productNameList.clear();
        List<Product> productList = productSessionLocal.getAllProduct();
        for (Product product : productList) {
            productNameList.add(product.getProductName());
        }
        System.out.println("refresed product list" + productNameList);
        return productNameList;
    }

}
