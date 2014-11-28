/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author li
 */
@Entity
public class RedemptionCart implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer points;
    private String createTime;
    @OneToOne(cascade={CascadeType.PERSIST})
    private Customer customer;
    @OneToMany(cascade={CascadeType.PERSIST},mappedBy="redemptionCart")
    private Collection<Evoucher> vouchers = new ArrayList<Evoucher>();

    public RedemptionCart() {
    }

    public RedemptionCart(Integer points, String createTime) {
        this.points = points;
        this.createTime = createTime;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Collection<Evoucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(Collection<Evoucher> vouchers) {
        this.vouchers = vouchers;
    }
   
}
