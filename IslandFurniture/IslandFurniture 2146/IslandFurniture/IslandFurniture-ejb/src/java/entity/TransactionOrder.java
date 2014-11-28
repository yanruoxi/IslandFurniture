/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Ruoxi
 */
@Entity
public class TransactionOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String date;
    @Column(precision = 19, scale = 4)
    private BigDecimal total;
    private Boolean isMember;
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "transactionOrder")
    private List<SingleTransactionItem> itemList = new ArrayList<>();
    private String transactionReference;
    private String customerPhone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof TransactionOrder)) {
            return false;
        }
        TransactionOrder other = (TransactionOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TransactionOrder[ id=" + id + " ]";
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the isMember
     */
    public Boolean getIsMember() {
        return isMember;
    }

    /**
     * @param isMember the isMember to set
     */
    public void setIsMember(Boolean isMember) {
        this.isMember = isMember;
    }

    /**
     * @return the itemList
     */
    public List<SingleTransactionItem> getItemList() {
        return itemList;
    }

    /**
     * @param itemList the itemList to set
     */
    public void setItemList(List<SingleTransactionItem> itemList) {
        this.itemList = itemList;
    }

    /**
     * @return the transactionReference
     */
    public String getTransactionReference() {
        return transactionReference;
    }

    /**
     * @param transactionReference the transactionReference to set
     */
    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    /**
     * @return the customerPhone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * @param customerPhone the customerPhone to set
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

}
