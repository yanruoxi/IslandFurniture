/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Factory;
import entity.Product;
import entity.ProductionOrder;
import entity.SalesForcast;
import entity.Store;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import session.stateless.FactorySessionLocal;
import session.stateless.ProductionOrderSessionLocal;
import session.stateless.SalesForcastSessionLocal;

/**
 *
 * @author Ruoxi
 */
@Named(value = "viewSalesBean")
@SessionScoped
public class ViewSalesBean implements Serializable {

    @EJB
    private SalesForcastSessionLocal salesSession;
    @EJB
    FactorySessionLocal factorySession;
    @EJB
    ProductionOrderSessionLocal POSession;

    @PostConstruct
    public void init() {
        salesList = salesSession.getAllSalesForcast();//retrieve all sales forecast

    }

    private List<SalesForcast> salesList;
    private List<SalesForcast> filteredList;
    private SalesForcast selectedSales;
    private Store selectedStore;
    private Product selectedProduct;
    private List<Factory> factoryList;
    private Factory selectedFactory;
    private ProductionOrder productionOrder;

    public void deleteSalesforecast(SalesForcast sales) {
        salesSession.deleteSalesForecast(sales);
        salesList = salesSession.getAllSalesForcast();//update sales forecast
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successfully deleted salesforecast");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void redirectSelectFactory(SalesForcast sales) throws IOException {
        //if production order is not already created
        if (sales.getStatus().equals("no")) {

            selectedSales = sales;
            selectedStore = sales.getStore();
            selectedProduct = sales.getProduct();
            System.out.println("store name " + selectedStore.getStoreName() + "product Name" + selectedProduct.getProductName());

            setFactoryList(factorySession.getFactoryForSelectedProduct(selectedProduct));

            FacesContext.getCurrentInstance().getExternalContext().redirect("poSelectProduct.xhtml");

        } else {//display po if po is already created
            System.out.println("staus for sales forcast is set to yes");
            productionOrder = sales.getProductionOrder();
            System.out.println(productionOrder.getYear());
            FacesContext.getCurrentInstance().getExternalContext().redirect("poViewPO.xhtml");

        }

    }

    public void creatPO() throws IOException {

        setProductionOrder(POSession.createProductionOrder(selectedProduct, selectedStore, getSelectedFactory(), selectedSales, selectedSales.getYear(), selectedSales.getMonth(), selectedSales.getAmount()));

        FacesContext.getCurrentInstance().getExternalContext().redirect("poViewPO.xhtml");

    }

    public void redirectManageSalesForcast() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("manageSalesForecast.xhtml");

    }

    /**
     * Creates a new instance of ViewSalesBean
     */
    public ViewSalesBean() {
    }

    /**
     * @return the salesList
     */
    public List<SalesForcast> getSalesList() {
        return salesList;
    }

    /**
     * @param salesList the salesList to set
     */
    public void setSalesList(List<SalesForcast> salesList) {
        this.salesList = salesList;
    }

    /**
     * @return the filteredList
     */
    public List<SalesForcast> getFilteredList() {
        return filteredList;
    }

    /**
     * @param filteredList the filteredList to set
     */
    public void setFilteredList(List<SalesForcast> filteredList) {
        this.filteredList = filteredList;
    }

    /**
     * @return the selectedSales
     */
    public SalesForcast getSelectedSales() {
        return selectedSales;
    }

    /**
     * @param selectedSales the selectedSales to set
     */
    public void setSelectedSales(SalesForcast selectedSales) {
        this.selectedSales = selectedSales;
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

    /**
     * @return the productionOrder
     */
    public ProductionOrder getProductionOrder() {
        return productionOrder;
    }

    /**
     * @param productionOrder the productionOrder to set
     */
    public void setProductionOrder(ProductionOrder productionOrder) {
        this.productionOrder = productionOrder;
    }

}
