/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.kms;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ZiGui
 */
@Entity
public class KitchenInventory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long kinventoryId;
    private int kqty;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date kdateUpdated;
    private int ksafetyStockLevel;
    @OneToOne
    private Ingredient ingredient;

    public Long getKinventoryId() {
        return kinventoryId;
    }

    public void setKinventoryId(Long kinventoryId) {
        this.kinventoryId = kinventoryId;
    }

    public int getKqty() {
        return kqty;
    }

    public void setKqty(int kqty) {
        this.kqty = kqty;
    }

    public Date getKdateUpdated() {
        return kdateUpdated;
    }

    public void setKdateUpdated(Date kdateUpdated) {
        this.kdateUpdated = kdateUpdated;
    }

    public int getKsafetyStockLevel() {
        return ksafetyStockLevel;
    }

    public void setKsafetyStockLevel(int ksafetyStockLevel) {
        this.ksafetyStockLevel = ksafetyStockLevel;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }


    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kinventoryId != null ? kinventoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KitchenInventory)) {
            return false;
        }
        KitchenInventory other = (KitchenInventory) object;
        if ((this.kinventoryId == null && other.kinventoryId != null) || (this.kinventoryId != null && !this.kinventoryId.equals(other.kinventoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.kms.KitchenInventory[ id=" + kinventoryId + " ]";
    }
    
}
