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

/**
 *
 * @author li
 */
@Entity
public class PointPolicy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String currency;
    private float priceForOnePoint;

    public PointPolicy() {
    }

    public PointPolicy(String currency, float priceForOnePoint) {
        this.currency = currency;
        this.priceForOnePoint = priceForOnePoint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getPriceForOnePoint() {
        return priceForOnePoint;
    }

    public void setPriceForOnePoint(float priceForOnePoint) {
        this.priceForOnePoint = priceForOnePoint;
    }

    
}
