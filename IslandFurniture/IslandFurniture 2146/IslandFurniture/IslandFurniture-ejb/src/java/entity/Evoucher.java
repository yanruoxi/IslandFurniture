/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author li
 */
@Entity
public class Evoucher implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer point;
    private Integer faceValue;
    private String termCondition;
    @ManyToOne
    private RedemptionCart redemptionCart = new RedemptionCart();
    
    public Evoucher() {
    }

    public Evoucher(Integer point, Integer faceValue, String termCondition) {
        this.point = point;
        this.faceValue = faceValue;
        this.termCondition = termCondition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(Integer faceValue) {
        this.faceValue = faceValue;
    }

    public String getTermCondition() {
        return termCondition;
    }

    public void setTermCondition(String termCondition) {
        this.termCondition = termCondition;
    }

    public RedemptionCart getRedemptionCart() {
        return redemptionCart;
    }

    public void setRedemptionCart(RedemptionCart redemptionCart) {
        this.redemptionCart = redemptionCart;
    }

    
}
