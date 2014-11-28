/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Ruoxi
 */
@Entity
public class Store implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String storeName;
   private String country;
    private String address;
    private String postal;
    private String phone;
    private String email;
    private String isDeleted;
 
    @OneToMany(mappedBy="store")
    private List<ProductionOrder>productionOrderList= new ArrayList<>();
    
    @ManyToMany
      private Set<Product>products=new HashSet<Product>();
    
    @OneToMany(mappedBy="store")
    private List<DefaultFactory> defaultFactoryList= new ArrayList<>();
    
     @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "store")
     private List<TransactionOrderHQ> transationOrderList= new ArrayList(){};
     
     @OneToMany(mappedBy="store")
     private List<SalesForcast> salesForcastList=new ArrayList(){};
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    /**
     * @return the isDeleted
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted the isDeleted to set
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
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
     * @return the products
     */
    public Set<Product> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(Set<Product> products) {
        this.products = products;
    }

   @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Store)) {
            return false;
        }
        Store other = (Store) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Store[ id=" + id + " ]";
    }

    /**
     * @return the defaultFactoryList
     */
    public List<DefaultFactory> getDefaultFactoryList() {
        return defaultFactoryList;
    }

    /**
     * @param defaultFactoryList the defaultFactoryList to set
     */
    public void setDefaultFactoryList(List<DefaultFactory> defaultFactoryList) {
        this.defaultFactoryList = defaultFactoryList;
    }

    /**
     * @return the transationOrderList
     */
    public List<TransactionOrderHQ> getTransationOrderList() {
        return transationOrderList;
    }

    /**
     * @param transationOrderList the transationOrderList to set
     */
    public void setTransationOrderList(List<TransactionOrderHQ> transationOrderList) {
        this.transationOrderList = transationOrderList;
    }

    /**
     * @return the salesForcastList
     */
    public List<SalesForcast> getSalesForcastList() {
        return salesForcastList;
    }

    /**
     * @param salesForcastList the salesForcastList to set
     */
    public void setSalesForcastList(List<SalesForcast> salesForcastList) {
        this.salesForcastList = salesForcastList;
    }
 

    
}
