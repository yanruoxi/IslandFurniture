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
 * @author Meiling
 */
@Entity
public class ShippingOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shippingOrderId;
    private String shippingOrderStatus; // shippingOrderStatus: In Transit
    @Temporal(value = TemporalType.DATE)
    private Date shippedOutDate;
    @Temporal(value = TemporalType.DATE)
    private Date dateCreated;
    private boolean isDeleted;
    @OneToOne
    private PurchaseOrder purchaseOrder;
    @ManyToOne
    private Company company;

    public Long getShippingOrderId() {
        return shippingOrderId;
    }

    public void setShippingOrderId(Long shippingOrderId) {
        this.shippingOrderId = shippingOrderId;
    }

    public String getShippingOrderStatus() {
        return shippingOrderStatus;
    }

    public void setShippingOrderStatus(String shippingOrderStatus) {
        this.shippingOrderStatus = shippingOrderStatus;
    }

    public Date getShippedOutDate() {
        return shippedOutDate;
    }

    public void setShippedOutDate(Date shippedOutDate) {
        this.shippedOutDate = shippedOutDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shippingOrderId != null ? shippingOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShippingOrder)) {
            return false;
        }
        ShippingOrder other = (ShippingOrder) object;
        if ((this.shippingOrderId == null && other.shippingOrderId != null) || (this.shippingOrderId != null && !this.shippingOrderId.equals(other.shippingOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ShippingOrder[ id=" + shippingOrderId + " ]";
    }
    
}
