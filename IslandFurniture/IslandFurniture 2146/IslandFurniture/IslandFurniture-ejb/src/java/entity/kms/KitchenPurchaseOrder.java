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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ZiGui
 */
@Entity
public class KitchenPurchaseOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long kpurchaseOrderId;
    private int kqty;
    private Double kunitPrice;
    private Double ktotalPrice;
    private String kpurchaseOrderStatus; // kPurchaseOrderStatus: Draft/ Sent/ Received/ Issued/ False Issued
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date kpurchaseOrderDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date kgoodsReceiptDate;
    private String kpoIngredientType;
    private boolean kisDeleted;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date kscheduledDeliveryDate;
    private String kdeliveryStatus; // kDeliveryStatus: Delivered/ Not Delivered/ Delayed

    @ManyToOne
    private KitchenSupplier kitchenSupplier;
    @ManyToOne
    private Ingredient ingredient;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date bestBefore;

    public Long getKpurchaseOrderId() {
        return kpurchaseOrderId;
    }

    public void setKpurchaseOrderId(Long kpurchaseOrderId) {
        this.kpurchaseOrderId = kpurchaseOrderId;
    }

    public int getKqty() {
        return kqty;
    }

    public void setKqty(int kqty) {
        this.kqty = kqty;
    }

    public Double getKunitPrice() {
        return kunitPrice;
    }

    public void setKunitPrice(Double kunitPrice) {
        this.kunitPrice = kunitPrice;
    }

    public Double getKtotalPrice() {
        return ktotalPrice;
    }

    public void setKtotalPrice(Double ktotalPrice) {
        this.ktotalPrice = ktotalPrice;
    }

    public String getKpurchaseOrderStatus() {
        return kpurchaseOrderStatus;
    }

    public void setKpurchaseOrderStatus(String kpurchaseOrderStatus) {
        this.kpurchaseOrderStatus = kpurchaseOrderStatus;
    }

    public Date getKgoodsReceiptDate() {
        return kgoodsReceiptDate;
    }

    public void setKgoodsReceiptDate(Date kgoodsReceiptDate) {
        this.kgoodsReceiptDate = kgoodsReceiptDate;
    }

    public String getKpoIngredientType() {
        return kpoIngredientType;
    }

    public void setKpoIngredientType(String kpoIngredientType) {
        this.kpoIngredientType = kpoIngredientType;
    }

    public boolean isKisDeleted() {
        return kisDeleted;
    }

    public void setKisDeleted(boolean kisDeleted) {
        this.kisDeleted = kisDeleted;
    }

    public Date getKscheduledDeliveryDate() {
        return kscheduledDeliveryDate;
    }

    public void setKscheduledDeliveryDate(Date kscheduledDeliveryDate) {
        this.kscheduledDeliveryDate = kscheduledDeliveryDate;
    }

    public String getKdeliveryStatus() {
        return kdeliveryStatus;
    }

    public void setKdeliveryStatus(String kdeliveryStatus) {
        this.kdeliveryStatus = kdeliveryStatus;
    }

    public KitchenSupplier getKitchenSupplier() {
        return kitchenSupplier;
    }

    public void setKitchenSupplier(KitchenSupplier kitchenSupplier) {
        this.kitchenSupplier = kitchenSupplier;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Date getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(Date bestBefore) {
        this.bestBefore = bestBefore;
    }

    public Date getKpurchaseOrderDate() {
        return kpurchaseOrderDate;
    }

    public void setKpurchaseOrderDate(Date kpurchaseOrderDate) {
        this.kpurchaseOrderDate = kpurchaseOrderDate;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kpurchaseOrderId != null ? kpurchaseOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KitchenPurchaseOrder)) {
            return false;
        }
        KitchenPurchaseOrder other = (KitchenPurchaseOrder) object;
        if ((this.kpurchaseOrderId == null && other.kpurchaseOrderId != null) || (this.kpurchaseOrderId != null && !this.kpurchaseOrderId.equals(other.kpurchaseOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.kms.KitchenPurchaseOrder[ id=" + kpurchaseOrderId + " ]";
    }

}
