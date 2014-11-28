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

/**
 *
 * @author li
 */
@Entity
public class MRP implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int grossRequirment;
    private int schedulesReceipts;
    private int OnHandBefore;
    private int onHandAfter;
    private int plannedOrder;
    @OneToMany(cascade={CascadeType.ALL},mappedBy="mrp")
    private Collection<MPS> mps=new ArrayList<MPS>();

    public MRP() {
    }

    public MRP(int grossRequirment, int schedulesReceipts, int OnHandBefore, int onHandAfter, int plannedOrder) {
        this.grossRequirment = grossRequirment;
        this.schedulesReceipts = schedulesReceipts;
        this.OnHandBefore = OnHandBefore;
        this.onHandAfter = onHandAfter;
        this.plannedOrder = plannedOrder;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGrossRequirment() {
        return grossRequirment;
    }

    public void setGrossRequirment(int grossRequirment) {
        this.grossRequirment = grossRequirment;
    }

    public int getSchedulesReceipts() {
        return schedulesReceipts;
    }

    public void setSchedulesReceipts(int schedulesReceipts) {
        this.schedulesReceipts = schedulesReceipts;
    }

    public int getOnHandBefore() {
        return OnHandBefore;
    }

    public void setOnHandBefore(int OnHandBefore) {
        this.OnHandBefore = OnHandBefore;
    }

    public int getOnHandAfter() {
        return onHandAfter;
    }

    public void setOnHandAfter(int onHandAfter) {
        this.onHandAfter = onHandAfter;
    }

    public int getPlannedOrder() {
        return plannedOrder;
    }

    public void setPlannedOrder(int plannedOrder) {
        this.plannedOrder = plannedOrder;
    }

    public Collection<MPS> getMps() {
        return mps;
    }

    public void setMps(Collection<MPS> mps) {
        this.mps = mps;
    }

   
   

    
}
