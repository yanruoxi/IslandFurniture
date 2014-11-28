/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ZiGui
 */
@Entity
public class PurchaseOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchaseOrderId;
    private Integer qty;
    private Double unitPrice;
    private Double totalPrice;
    private String purchaseOrderStatus; // purchaseOrderStatus: Pending Approval/ Approved/ Rejected/ Received/ Issued/ False Received/ False Issued
    @Temporal(value = TemporalType.DATE)
    private Date purchaseOrderDate;
    @Temporal(value = TemporalType.DATE)
    private Date goodsReceiptDate;
    @Temporal(value = TemporalType.DATE)
    private Date goodsIssuedDate;
    private String goodsType;
    private boolean isDeleted;
    @Temporal(value = TemporalType.DATE)
    private Date scheduledDeliveryDate;
    private String deliveryStatus;
    private String negoCondition;
    @ManyToOne
    private Part part;
    @ManyToOne
    private Supplier supplier;
    
    // Added
    private String replenishmentType;

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPurchaseOrderStatus() {
        return purchaseOrderStatus;
    }

    public void setPurchaseOrderStatus(String purchaseOrderStatus) {
        this.purchaseOrderStatus = purchaseOrderStatus;
    }

    public Date getPurchaseOrderDate() {
        return purchaseOrderDate;
    }

    public void setPurchaseOrderDate(Date purchaseOrderDate) {
        this.purchaseOrderDate = purchaseOrderDate;
    }

    public Date getGoodsReceiptDate() {
        return goodsReceiptDate;
    }

    public void setGoodsReceiptDate(Date goodsReceiptDate) {
        this.goodsReceiptDate = goodsReceiptDate;
    }

    public Date getGoodsIssuedDate() {
        return goodsIssuedDate;
    }

    public void setGoodsIssuedDate(Date goodsIssuedDate) {
        this.goodsIssuedDate = goodsIssuedDate;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Date getScheduledDeliveryDate() {
        return scheduledDeliveryDate;
    }

    public void setScheduledDeliveryDate(Date scheduledDeliveryDate) {
        this.scheduledDeliveryDate = scheduledDeliveryDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getNegoCondition() {
        return negoCondition;
    }

    public void setNegoCondition(String negoCondition) {
        this.negoCondition = negoCondition;
    }

    public String getReplenishmentType() {
        return replenishmentType;
    }

    public void setReplenishmentType(String replenishmentType) {
        this.replenishmentType = replenishmentType;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchaseOrderId != null ? purchaseOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrder)) {
            return false;
        }
        PurchaseOrder other = (PurchaseOrder) object;
        if ((this.purchaseOrderId == null && other.purchaseOrderId != null) || (this.purchaseOrderId != null && !this.purchaseOrderId.equals(other.purchaseOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Order[ orderID=" + purchaseOrderId + " ]";
    }
    
}
