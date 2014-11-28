/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Ruoxi
 */
@Entity
public class ProductionOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String month;
    private Integer quantity;
    @ManyToOne
    private Store store;
    @ManyToOne
    private Factory factory;
    @ManyToOne
    private Product product;
    
    private String year;
    @OneToOne
    private SalesForcast salesForcast;
    
   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

   
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

   
    public Factory getFactory() {
        return factory;
    }

    
    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
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
     * @return the salesForcast
     */
    public SalesForcast getSalesForcast() {
        return salesForcast;
    }

    /**
     * @param salesForcast the salesForcast to set
     */
    public void setSalesForcast(SalesForcast salesForcast) {
        this.salesForcast = salesForcast;
    }

 
    
}
