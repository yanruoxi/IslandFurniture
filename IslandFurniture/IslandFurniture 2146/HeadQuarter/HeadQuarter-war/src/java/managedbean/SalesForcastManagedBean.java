/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

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
import session.stateless.ProductSessionLocal;
import session.stateless.SalesForcastSessionLocal;

/**
 *
 * @author Ruoxi
 */
@Named(value = "salesForcastManagedBean")
@SessionScoped
public class SalesForcastManagedBean implements Serializable {

    @EJB
    private ProductSessionLocal productSessionLocal;
    @EJB
    private SalesForcastSessionLocal salesForcastSession;

    private List<Product> productList;
    private Store selectedStore;
    private Product selectedProduct;
    private String year;
    private String month;
    private Integer totalPast;
    private Integer totalForcast;
    private Double factor;

    public void setSelectedStoreRedirect(Store store) throws IOException {
        selectedStore = store;

        setProductList(productSessionLocal.getProductsForSelectedStore(selectedStore));

        FacesContext.getCurrentInstance().getExternalContext().redirect("sfSelectProduct.xhtml");

    }

    public void redirectSelectedMonthYear() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().redirect("sfSelectProduct.xhtml");

    }

    public void redirectGenerateSalesForcast() throws IOException {
        //check if the sales forcast is already created
        Boolean check = salesForcastSession.checkSalesForcastExist(selectedStore.getStoreName(), selectedProduct.getProductName(), year, month);

        System.out.println("storeName is" + selectedStore.getStoreName() + " product Name is" + selectedProduct.getProductName() + "year is" + year + "mont is " + month);
        System.out.println("results checking if salesforcast already exist . true means exitst" + check);

        if (check) {
            System.out.println("came to error messgae sales forcast already created");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Sales Forcast Already generated");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            //check if there is past data available to generate transactionOrder
            Boolean checkPast = salesForcastSession.checkPastDataExist(selectedStore.getStoreName(), selectedProduct.getProductName(), year, month);

            System.out.println("true means no past transaction data" + checkPast);

            if (checkPast) {
                System.out.println("came to error messgae sales forcast already created");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "No Past Transaction Data Available to Generate Sales Forcast");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {

                totalPast = salesForcastSession.createSalesForcast(selectedStore.getStoreName(), selectedProduct.getProductName(), year, month);
                System.out.println("the total past sale data is " + totalPast);

                //calculate totalfarcst using growth factor
                Double temp = totalPast * factor;
                int t = (int) Math.round(temp);
                Integer integerNum = new Integer(t);
                totalForcast = integerNum;

                FacesContext.getCurrentInstance().getExternalContext().redirect("sfGenerateSalesForcast.xhtml");

            }

        }
    }

    public void saveSalesForcast() {
        Boolean check = salesForcastSession.checkSalesForcastExist(selectedStore.getStoreName(), selectedProduct.getProductName(), year, month);

        if (check) {
            System.out.println("came to error messgae sales forcast already created");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Sales Forcast Already generated");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            salesForcastSession.createSalesForecast(selectedStore.getStoreName(), selectedProduct.getProductName(), year, month, totalForcast);

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Sales Forcast Saved");
            FacesContext.getCurrentInstance().addMessage(null, message);

        }

    }
    
 
    /**
     * Creates a new instance of SalesForcastManagedBean
     */
    public SalesForcastManagedBean() {
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
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return the totalPast
     */
    public Integer getTotalPast() {
        return totalPast;
    }

    /**
     * @param totalPast the totalPast to set
     */
    public void setTotalPast(Integer totalPast) {
        this.totalPast = totalPast;
    }

    /**
     * @return the totalForcast
     */
    public Integer getTotalForcast() {
        return totalForcast;
    }

    /**
     * @param totalForcast the totalForcast to set
     */
    public void setTotalForcast(Integer totalForcast) {
        this.totalForcast = totalForcast;
    }

    /**
     * @return the factor
     */
    public Double getFactor() {
        return factor;
    }

    /**
     * @param factor the factor to set
     */
    public void setFactor(Double factor) {
        this.factor = factor;
    }

}
