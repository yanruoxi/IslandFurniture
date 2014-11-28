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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author li
 */
@Entity
public class SalesPlan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String time;
    private int forecast;
    private int productionPlan;
    private int inventory;
    private int workingDays;
    @ManyToOne
    private Furniture furniture = new Furniture();
    @OneToMany(cascade={CascadeType.ALL},mappedBy="plan")
    private Collection<MPS> mps=new ArrayList<MPS>();

    public SalesPlan() {
    }

    
    public SalesPlan(String time, int forecast, int productionPlan, int inventory, int workingDays) {
        this.time = time;
        this.forecast = forecast;
        this.productionPlan = productionPlan;
        this.inventory = inventory;
        this.workingDays = workingDays;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getForecast() {
        return forecast;
    }

    public void setForecast(int forecast) {
        this.forecast = forecast;
    }

    public int getProductionPlan() {
        return productionPlan;
    }

    public void setProductionPlan(int productionPlan) {
        this.productionPlan = productionPlan;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public Collection<MPS> getMps() {
        return mps;
    }

    public void setMps(Collection<MPS> mps) {
        this.mps = mps;
    }

    
    
}
