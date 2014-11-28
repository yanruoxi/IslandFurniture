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
public class PurchaseRequisition implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchaseReqId;
    private Integer qty;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date purchaseReqDate;
    @ManyToOne
    private Part part;
    
    // New
    private boolean isDeleted;
    private String createdBy;
    private String replenishmentType; // replenishmentType: Scheduled/ Ad Hoc
    private String purchaseReqStatus; // purchaseReqStatus: PO Not Created (default)/ Submitted RFQ/ PO Sent
    
    public Long getPurchaseReqId() {
        return purchaseReqId;
    }

    public void setPurchaseReqId(Long purchaseReqId) {
        this.purchaseReqId = purchaseReqId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }


    public Date getPurchaseReqDate() {
        return purchaseReqDate;
    }

    public void setPurchaseReqDate(Date purchaseReqDate) {
        this.purchaseReqDate = purchaseReqDate;
    }
    
    // Added
    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getReplenishmentType() {
        return replenishmentType;
    }

    public void setReplenishmentType(String replenishmentType) {
        this.replenishmentType = replenishmentType;
    }

    public String getPurchaseReqStatus() {
        return purchaseReqStatus;
    }

    public void setPurchaseReqStatus(String purchaseReqStatus) {
        this.purchaseReqStatus = purchaseReqStatus;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchaseReqId != null ? purchaseReqId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseRequisition)) {
            return false;
        }
        PurchaseRequisition other = (PurchaseRequisition) object;
        if ((this.purchaseReqId == null && other.purchaseReqId != null) || (this.purchaseReqId != null && !this.purchaseReqId.equals(other.purchaseReqId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PurchaseRequisition[ id=" + purchaseReqId + " ]";
    }
    
}
