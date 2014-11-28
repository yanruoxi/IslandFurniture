/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Product;
import entity.Store;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import session.stateless.ProductSessionLocal;
import session.stateless.StoreSessionLocal;

/**
 *
 * @author Ruoxi
 */
@Named
@ViewScoped
public class StoreProductManagedBean {

    @EJB
    StoreSessionLocal storeSessionLocal;
    
    @EJB
    ProductSessionLocal productSessionLocal;

    @PostConstruct
    public void init() {
        setStores(storeSessionLocal.getAllStore());
        productsNameList=productSessionLocal.getProductsString();              
      
    }

    /**
     * Creates a new instance of StoreProductManagedBean
     */
    public StoreProductManagedBean() {
    }
    private Store selectedStore;
    private List<Store> stores;
    private List<String> productsNameList;
    private String[] selectedProducts; 
    
    
     // Add Products to A Supplier
    public void addProductToStore() {
        
        System.out.println("length of selected prduct is" + selectedProducts.length);
        
        System.out.println("selected store name is " +selectedStore.getStoreName());
        
        
        Set<Product> selectedProductsToAdd = new HashSet<Product>(); // HashSet that stores selectedPartsToAdd Part objects
        Product p = new Product(); // For Usage in for loop
        for (int i = 0; i < selectedProducts.length; i++) {
            
            System.out.println(selectedProducts[i]);
            
            p=productSessionLocal.getProduct(selectedProducts[i]);
            selectedProductsToAdd.add(p);
        }
        boolean success=productSessionLocal.addProductToStore(selectedStore, selectedProductsToAdd);
        if (success) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Product(s) added to Store");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Product has already been added to Store");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
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

    /**
     * @return the stores
     */
    public List<Store> getStores() {
        return stores;
    }

    /**
     * @param stores the stores to set
     */
    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    /**
     * @return the productsNameList
     */
    public List<String> getProductsNameList() {
        return productsNameList;
    }

    /**
     * @param productsNameList the productsNameList to set
     */
    public void setProductsNameList(List<String> productsNameList) {
        this.productsNameList = productsNameList;
    }

    /**
     * @return the selectedProducts
     */
    public String[] getSelectedProducts() {
        return selectedProducts;
    }

    /**
     * @param selectedProducts the selectedProducts to set
     */
    public void setSelectedProducts(String[] selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

}
