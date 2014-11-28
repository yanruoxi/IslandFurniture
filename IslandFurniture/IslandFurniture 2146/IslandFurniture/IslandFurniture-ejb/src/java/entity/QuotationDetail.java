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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Meiling
 */
@Entity
public class QuotationDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quotationDetailId;
    private Double quotedUnitPrice;
    private Double quotedTotalPrice;
    private String quotedNegoCondition;
    @Temporal(value = TemporalType.DATE)
    private Date quotedDate;
    @Temporal(value = TemporalType.DATE)
    private Date quotationEndingDate;
    @Temporal(value = TemporalType.DATE)
    private Date quotedScheduledDeliveryDate;
    private Integer qty;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastUpdated;
    private String goodsType;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date quotedSentDate;
    private String quotationDetailStatus; // quotationDetailStatus: Pending/ Draft /Submitted/ Expired
    private boolean isDeleted;
    @ManyToOne
    private Supplier supplier;
    @ManyToOne
    private Part part;
    // Added
    private String replenishmentType;
    // Added 23-10-14
    @ManyToOne
    private PurchaseRequisition purchaseReq;
    

    public Long getQuotationDetailId() {
        return quotationDetailId;
    }

    public void setQuotationDetailId(Long quotationDetailId) {
        this.quotationDetailId = quotationDetailId;
    }

    public Double getQuotedUnitPrice() {
        return quotedUnitPrice;
    }

    public void setQuotedUnitPrice(Double quotedUnitPrice) {
        this.quotedUnitPrice = quotedUnitPrice;
    }

    public Double getQuotedTotalPrice() {
        return quotedTotalPrice;
    }

    public void setQuotedTotalPrice(Double quotedTotalPrice) {
        this.quotedTotalPrice = quotedTotalPrice;
    }

    public String getQuotedNegoCondition() {
        return quotedNegoCondition;
    }

    public void setQuotedNegoCondition(String quotedNegoCondition) {
        this.quotedNegoCondition = quotedNegoCondition;
    }

    public Date getQuotedDate() {
        return quotedDate;
    }

    public void setQuotedDate(Date quotedDate) {
        this.quotedDate = quotedDate;
    }

    public Date getQuotationEndingDate() {
        return quotationEndingDate;
    }

    public void setQuotationEndingDate(Date quotationEndingDate) {
        this.quotationEndingDate = quotationEndingDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getQuotedScheduledDeliveryDate() {
        return quotedScheduledDeliveryDate;
    }

    public void setQuotedScheduledDeliveryDate(Date quotedScheduledDeliveryDate) {
        this.quotedScheduledDeliveryDate = quotedScheduledDeliveryDate;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public Date getQuotedSentDate() {
        return quotedSentDate;
    }

    public void setQuotedSentDate(Date quotedSentDate) {
        this.quotedSentDate = quotedSentDate;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public String getQuotationDetailStatus() {
        return quotationDetailStatus;
    }

    public void setQuotationDetailStatus(String quotationDetailStatus) {
        this.quotationDetailStatus = quotationDetailStatus;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getReplenishmentType() {
        return replenishmentType;
    }

    public void setReplenishmentType(String replenishmentType) {
        this.replenishmentType = replenishmentType;
    }

    public PurchaseRequisition getPurchaseReq() {
        return purchaseReq;
    }

    public void setPurchaseReq(PurchaseRequisition purchaseReq) {
        this.purchaseReq = purchaseReq;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (quotationDetailId != null ? quotationDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuotationDetail)) {
            return false;
        }
        QuotationDetail other = (QuotationDetail) object;
        if ((this.quotationDetailId == null && other.quotationDetailId != null) || (this.quotationDetailId != null && !this.quotationDetailId.equals(other.quotationDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.QuotationDetail[ id=" + quotationDetailId + " ]";
    }
    
}
