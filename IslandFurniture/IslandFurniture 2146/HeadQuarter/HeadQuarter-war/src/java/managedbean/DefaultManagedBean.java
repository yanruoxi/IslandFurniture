/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.DefaultFactory;
import entity.Factory;
import entity.Product;
import entity.Store;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import session.stateless.DefaultFactorySessionLocal;
import session.stateless.FactorySessionLocal;
import session.stateless.ProductSessionLocal;

/**
 *
 * @author Ruoxi
 */
@Named(value = "defaultManagedBean")
@SessionScoped
public class DefaultManagedBean implements Serializable {

    @EJB
    ProductSessionLocal productSessionLocal;

    @EJB
    DefaultFactorySessionLocal defaultFactorySession;

    @EJB
    FactorySessionLocal factorySession;

    private Store selectedStore;
    private List<Product> productList;
    private Product selectedProduct;
    private DefaultFactory first;
    private DefaultFactory second;
    private List<Factory> factoryList;
    private Factory selectedFactory;

    public void setSelectedStoreRedirect(Store store) throws IOException {

        selectedStore = store;

        setProductList(productSessionLocal.getProductsForSelectedStore(selectedStore));

        FacesContext.getCurrentInstance().getExternalContext().redirect("defaultChooseProduct.xhtml");

    }

    public void displayDefaultFactory() throws IOException {
        System.out.println("selected Product name" + selectedProduct.getProductName());

        first = defaultFactorySession.getFirstDefaultFactory(selectedStore, selectedProduct);

        if (first == null) {

            first = defaultFactorySession.creatFirstDefault(selectedStore, selectedProduct);
        }

        second = defaultFactorySession.getSecondDefaultFactory(selectedStore, selectedProduct);
        if (second == null) {

            second = defaultFactorySession.creatSecondDefault(selectedStore, selectedProduct);
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("defaultDisplayDefault.xhtml");

    }

    public void setFirstDefault() throws IOException {

        setFactoryList(factorySession.getFactoryForSelectedProduct(selectedProduct));

        FacesContext.getCurrentInstance().getExternalContext().redirect("defaultSetFirstDefault.xhtml");

    }

    public void setSecondDefault() throws IOException {

        setFactoryList(factorySession.getFactoryForSelectedProduct(selectedProduct));

        FacesContext.getCurrentInstance().getExternalContext().redirect("defaultSetSecondDefault.xhtml");

    }

    public void setFirstFactory() throws IOException {

        boolean check = defaultFactorySession.compareFirstSecondDefault(second, selectedFactory);

        if (check) {

            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cannot set same factory for first and second default");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            first = defaultFactorySession.setFirstFactory(first, selectedFactory);

            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "You have successfully updated first default factory");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            FacesContext.getCurrentInstance().getExternalContext().redirect("defaultDisplayDefault.xhtml");
        }

    }

    public void setSecondFactory() throws IOException {

        boolean check = defaultFactorySession.compareFirstSecondDefault(first, selectedFactory);

        if (check) {

            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cannot set same factory for first and second default");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            second = defaultFactorySession.setFirstFactory(second, selectedFactory);

            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "You have successfully updated first default factory");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            FacesContext.getCurrentInstance().getExternalContext().redirect("defaultDisplayDefault.xhtml");
        }

    }

    /**
     * Creates a new instance of DefaultManagedBean
     */
    public DefaultManagedBean() {
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
     * @return the productList
     */
    public List<Product> getProductList() {
        return productList;
    }

    /**
     * @param productList the productList to set
     */
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    /**
     * @return the selectedProduct
     */
    public Product getSelectedProduct() {
        return selectedProduct;
    }

    /**
     * @param selectedProduct the selectedProduct to set
     */
    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    /**
     * @return the first
     */
    public DefaultFactory getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(DefaultFactory first) {
        this.first = first;
    }

    /**
     * @return the second
     */
    public DefaultFactory getSecond() {
        return second;
    }

    /**
     * @param second the second to set
     */
    public void setSecond(DefaultFactory second) {
        this.second = second;
    }

    /**
     * @return the factoryList
     */
    public List<Factory> getFactoryList() {
        return factoryList;
    }

    /**
     * @param factoryList the factoryList to set
     */
    public void setFactoryList(List<Factory> factoryList) {
        this.factoryList = factoryList;
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
