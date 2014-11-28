/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.kms;

import java.io.File;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

/**
 *
 * @author timotheus
 */
@Entity
public class Dish implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dishId;
    private String dishName;
    private String methodOfPreparation;
    private Integer pax;
    private String dishDescription;
    private String dishType;
    private Double dishPrice;
    private String dishImgLocation;
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "dish")
    private Collection<RecipeItem> recipeItem = new ArrayList<RecipeItem>();
    private boolean dishDeleteStatus;
    @Lob
    @Column(length = 100000)
    private byte[] imageData;

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getMethodOfPreparation() {
        return methodOfPreparation;
    }

    public void setMethodOfPreparation(String methodOfPreparation) {
        this.methodOfPreparation = methodOfPreparation;
    }

    public Integer getPax() {
        return pax;
    }

    public void setPax(Integer pax) {
        this.pax = pax;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public Double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(Double dishPrice) {
        this.dishPrice = dishPrice;
    }

    public String getDishImgLocation() {
        return dishImgLocation;
    }

    public void setDishImgLocation(String dishImgLocation) {
        this.dishImgLocation = dishImgLocation;
    }

    public Collection<RecipeItem> getRecipeItem() {
        return recipeItem;
    }

    public void setRecipeItem(Collection<RecipeItem> recipeItem) {
        this.recipeItem = recipeItem;
    }

    public boolean isDishDeleteStatus() {
        return dishDeleteStatus;
    }

    public void setDishDeleteStatus(boolean dishDeleteStatus) {
        this.dishDeleteStatus = dishDeleteStatus;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dishId != null ? dishId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dish)) {
            return false;
        }
        Dish other = (Dish) object;
        if ((this.dishId == null && other.dishId != null) || (this.dishId != null && !this.dishId.equals(other.dishId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.kms.NewEntity[ id=" + dishId + " ]";
    }
    
}
