/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ruoxi
 */
@Entity
public class SingleTransactionItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer amount;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Furniture furniture= new Furniture();
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private TransactionOrder transactionOrder = new TransactionOrder();

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
        if (!(object instanceof SingleTransactionItem)) {
            return false;
        }
        SingleTransactionItem other = (SingleTransactionItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SingleTransactionItem[ id=" + id + " ]";
    }

    /**
     * @return the amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * @return the transactionOrder
     */
    public TransactionOrder getTransactionOrder() {
        return transactionOrder;
    }

    /**
     * @param transactionOrder the transactionOrder to set
     */
    public void setTransactionOrder(TransactionOrder transactionOrder) {
        this.transactionOrder = transactionOrder;
    }

    /**
     * @return the furniture
     */
    public Furniture getFurniture() {
        return furniture;
    }

    /**
     * @param furniture the furniture to set
     */
    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

}
