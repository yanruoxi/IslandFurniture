/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Factory;
import entity.Product;
import entity.Store;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import session.stateless.FactorySessionLocal;
import session.stateless.ProductSessionLocal;

/**
 *
 * @author Ruoxi
 */
@ManagedBean
@RequestScoped
public class FactoryProductManagedBean {

    @EJB
    ProductSessionLocal productSessionLocal;

    @EJB
    FactorySessionLocal factorySessionLocal;

    @PostConstruct
    public void init() {
        factories = factorySessionLocal.getAllFactory();
        productsNameList = productSessionLocal.getProductsString();

    }

    private List<Factory> factories;
    private Factory selectedFactory;
    private List<String> productsNameList;
    private String[] selectedProducts;

    // Add Products to A Factory
    public void addProductToFactory() {

        System.out.println("length of selected prduct is" + selectedProducts.length);

        System.out.println("selected store name is " + selectedFactory.getFactoryName());

        Set<Product> selectedProductsToAdd = new HashSet<Product>(); // HashSet that stores selectedPartsToAdd Part objects
        Product p = new Product(); // For Usage in for loop
        for (int i = 0; i < selectedProducts.length; i++) {

            System.out.println(selectedProducts[i]);

            p = productSessionLocal.getProduct(selectedProducts[i]);
            selectedProductsToAdd.add(p);
        }
        boolean success = productSessionLocal.addProductToFactory(selectedFactory, selectedProductsToAdd);
        if (success) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Product(s) added to Factory");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Product has already been added to Factory");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

  

    /**
     * Creates a new instance of FactoryProductManagedBean
     */
    public FactoryProductManagedBean() {
    }

    /**
     * @return the factories
     */
    public List<Factory> getFactories() {
        return factories;
    }

    /**
     * @param factories the factories to set
     */
    public void setFactories(List<Factory> factories) {
        this.factories = factories;
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
