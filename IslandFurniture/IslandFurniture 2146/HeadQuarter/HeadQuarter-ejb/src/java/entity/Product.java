/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.OneToMany;

/**
 *
 * @author WangYan
 */
@Entity
public class Product implements Serializable {
    private static long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    private String type;
    private String isDeleted;
    @OneToMany(mappedBy="product")
    private List<ProductionOrder>productionOrderList=new ArrayList<>();
    private String category;
    private String color;
    //Product size
    private Double lengths;
    private Double width;
    private Double height;
    
    private String paths;//img path
    
    private String productDescription; 
    private String instructions;  
    @Column(precision = 19,scale =4)
    private BigDecimal price;
    
    
    @ManyToMany(mappedBy = "products")
    private Set<Store> stores = new HashSet<Store>();

    @ManyToMany(mappedBy = "products")
    private Set<Factory> factories = new HashSet<Factory>();
    
   @OneToMany(mappedBy="product")
   private List<DefaultFactory> defaultFactoryList= new ArrayList<>();
    
    private String pomotion="N";
    private BigDecimal discountPrice;//null if the price is the U.P
    public Product(){
        
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Double getLength() {
        return lengths;
    }

    public void setLength(Double length) {
        this.lengths=length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }
  

  public Set<Factory> getFactories() {
        return factories;
    }

    public void setFactories(Set<Factory> factories) {
        this.factories = factories;
    }
 

   public List<DefaultFactory> getDefaultFactoryList() {
        return defaultFactoryList;
    }

    public void setDefaultFactoryList(List<DefaultFactory> defaultFactoryList) {
        this.defaultFactoryList = defaultFactoryList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return paths;
    }

    public void setPath(String path) {
        this.paths=path;
    }

    public String getProductName() {
        return productName;
    }

 
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    
    public void setType(String type) {
        this.type = type;
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
     * @return the productionOrderList
     */
    public List<ProductionOrder> getProductionOrderList() {
        return productionOrderList;
    }

    /**
     * @param productionOrderList the productionOrderList to set
     */
    public void setProductionOrderList(List<ProductionOrder> productionOrderList) {
        this.productionOrderList = productionOrderList;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    public String getProductDescription() {
        return productDescription;
    }

    /**
     * @param productDescription the productDescription to set
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * @return the instructions
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * @param instructions the instructions to set
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Product[ id=" + getId() + " ]";
    }

    /**
     * @return the promotion
     */
    public String getPomotion() {
        return pomotion;
    }

    /**
     * @param promotion the promotion to set
     */
    public void setPomotion(String pomotion) {
        this.pomotion = pomotion;
    }


}
