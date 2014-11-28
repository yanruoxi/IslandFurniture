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
import javax.persistence.OneToOne;

/**
 *
 * @author li
 */
@Entity
public class Loyalty implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer pointsAvailable=0;
    private Integer pointsRedeemed=0;
    private float totalExpenditure=0;
    @OneToOne(cascade={CascadeType.PERSIST})
    private Customer customer;

    public Loyalty() {
    }

    public Loyalty(Integer pointsAvailable, Integer pointsRedeemed, float totalExpenditure, Customer customer) {
        this.pointsAvailable = pointsAvailable;
        this.pointsRedeemed = pointsRedeemed;
        this.totalExpenditure = totalExpenditure;
        this.customer = customer;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPointsAvailable() {
        return pointsAvailable;
    }

    public void setPointsAvailable(Integer pointsAvailable) {
        this.pointsAvailable = pointsAvailable;
    }

    public Integer getPointsRedeemed() {
        return pointsRedeemed;
    }

    public void setPointsRedeemed(Integer pointsRedeemed) {
        this.pointsRedeemed = pointsRedeemed;
    }

    public float getTotalExpenditure() {
        return totalExpenditure;
    }

    public void setTotalExpenditure(float totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
}
