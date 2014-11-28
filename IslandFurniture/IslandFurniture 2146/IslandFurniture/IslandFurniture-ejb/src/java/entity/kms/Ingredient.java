/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.kms;

import entity.Supplier;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ZiGui
 */
@Entity
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ingredientName;
    private int klotSize;
    private int kleadTime;
    private boolean isDeleted;
    private String ingredientType;
    @OneToOne(mappedBy = "ingredient")
    private KitchenInventory kitchenInventory;
    @ManyToMany(mappedBy = "ingredient")
    private Set<KitchenSupplier> kitchenSuppliers = new HashSet<KitchenSupplier>();
    @OneToMany(mappedBy = "ingredient")
    private Collection<RecipeItem> recipeItem = new ArrayList<RecipeItem>();
    private boolean hasDefaultSupplier;
    private KitchenSupplier kdefaultSupplier;
    private double defaultSupplierUnitPrice;

    public KitchenSupplier getKdefaultSupplier() {
        return kdefaultSupplier;
    }

    public void setKdefaultSupplier(KitchenSupplier kdefaultSupplier) {
        this.kdefaultSupplier = kdefaultSupplier;
    }

    public Ingredient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getKlotSize() {
        return klotSize;
    }

    public void setKlotSize(int klotSize) {
        this.klotSize = klotSize;
    }

    public int getKleadTime() {
        return kleadTime;
    }

    public void setKleadTime(int kleadTime) {
        this.kleadTime = kleadTime;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    public KitchenInventory getKitchenInventory() {
        return kitchenInventory;
    }

    public void setKitchenInventory(KitchenInventory kitchenInventory) {
        this.kitchenInventory = kitchenInventory;
    }

    public Set<KitchenSupplier> getKitchenSuppliers() {
        return kitchenSuppliers;
    }

    public void setKitchenSuppliers(Set<KitchenSupplier> kitchenSuppliers) {
        this.kitchenSuppliers = kitchenSuppliers;
    }

    public Collection<RecipeItem> getRecipeItem() {
        return recipeItem;
    }

    public void setRecipeItem(Collection<RecipeItem> recipeItem) {
        this.recipeItem = recipeItem;
    }

    public boolean isHasDefaultSupplier() {
        return hasDefaultSupplier;
    }

    public void setHasDefaultSupplier(boolean hasDefaultSupplier) {
        this.hasDefaultSupplier = hasDefaultSupplier;
    }

    public double getDefaultSupplierUnitPrice() {
        return defaultSupplierUnitPrice;
    }

    public void setDefaultSupplierUnitPrice(double defaultSupplierUnitPrice) {
        this.defaultSupplierUnitPrice = defaultSupplierUnitPrice;
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
        if (!(object instanceof Ingredient)) {
            return false;
        }
        Ingredient other = (Ingredient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.kms.Ingredient[ id=" + id + " ]";
    }

}
