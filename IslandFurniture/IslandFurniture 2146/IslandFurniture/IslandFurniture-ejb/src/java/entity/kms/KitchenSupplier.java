/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.kms;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author ZiGui
 */
@Entity
public class KitchenSupplier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ksupplierId;
    private String ksupplierName;
    private String ksupplierAddress;
    private String ktelephone;
    private String kcontactPersonName;
    private String kmobileNum;
    private String kfaxNum;
    private String ksupplierEmailAddress;
    private boolean ksupplierDeleteStatus;
    @ManyToMany
    private Set<Ingredient> ingredient = new HashSet<Ingredient>();

    public Long getKsupplierId() {
        return ksupplierId;
    }

    public void setKsupplierId(Long ksupplierId) {
        this.ksupplierId = ksupplierId;
    }

    public String getKsupplierName() {
        return ksupplierName;
    }

    public void setKsupplierName(String ksupplierName) {
        this.ksupplierName = ksupplierName;
    }

    public String getKsupplierAddress() {
        return ksupplierAddress;
    }

    public void setKsupplierAddress(String ksupplierAddress) {
        this.ksupplierAddress = ksupplierAddress;
    }

    public String getKtelephone() {
        return ktelephone;
    }

    public void setKtelephone(String ktelephone) {
        this.ktelephone = ktelephone;
    }

    public String getKcontactPersonName() {
        return kcontactPersonName;
    }

    public void setKcontactPersonName(String kcontactPersonName) {
        this.kcontactPersonName = kcontactPersonName;
    }

    public String getKmobileNum() {
        return kmobileNum;
    }

    public void setKmobileNum(String kmobileNum) {
        this.kmobileNum = kmobileNum;
    }

    public String getKfaxNum() {
        return kfaxNum;
    }

    public void setKfaxNum(String kfaxNum) {
        this.kfaxNum = kfaxNum;
    }

    public String getKsupplierEmailAddress() {
        return ksupplierEmailAddress;
    }

    public void setKsupplierEmailAddress(String ksupplierEmailAddress) {
        this.ksupplierEmailAddress = ksupplierEmailAddress;
    }

    public boolean isKsupplierDeleteStatus() {
        return ksupplierDeleteStatus;
    }

    public void setKsupplierDeleteStatus(boolean ksupplierDeleteStatus) {
        this.ksupplierDeleteStatus = ksupplierDeleteStatus;
    }
    
    



    public Set<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(Set<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ksupplierId != null ? ksupplierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KitchenSupplier)) {
            return false;
        }
        KitchenSupplier other = (KitchenSupplier) object;
        if ((this.ksupplierId == null && other.ksupplierId != null) || (this.ksupplierId != null && !this.ksupplierId.equals(other.ksupplierId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.kms.KitchenSupplier[ id=" + ksupplierId + " ]";
    }
    
}
