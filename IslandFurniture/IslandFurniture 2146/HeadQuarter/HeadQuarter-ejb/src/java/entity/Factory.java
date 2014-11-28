/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.ArrayList;
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

/**
 *
 * @author Ruoxi
 */
@Entity
public class Factory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String factoryName;
    private String country;
    private String address;
    private String postal;
    private String phone;
    private String email;
    private String status;
    private String isDeleted;
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="factory")
    private List<ProductionOrder>productionOrderList=new ArrayList<>();
    
     @ManyToMany
      private Set<Product>products=new HashSet<Product>();
     
     @OneToMany(mappedBy="factory")
    private List<DefaultFactory> defaultFactoryList= new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    public String getFactoryName() {
        return factoryName;
    }

  
    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

  
    public String getStatus() {
        return status;
    }

    
    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof Factory)) {
            return false;
        }
        Factory other = (Factory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Factory[ id=" + id + " ]";
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
 
    
   

  

    
}
