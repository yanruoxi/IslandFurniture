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
 * @author li
 */
@Entity
public class MPS implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int workingDays;
    private int week;
    private int MPSweeklyDemand;
    private String periodDate;
    private int grossRequirment;
    private int scheduledReceipts;
    private int plannedOrder;
    private int onHandBefore;
    private int onHandAfter;
    private String partName;
    @ManyToOne
    private SalesPlan plan = new SalesPlan();
    @ManyToOne(cascade={CascadeType.PERSIST})
    private MRP mrp = new MRP();

    public MPS() {
    }

    public MPS(int workingDays, int week, int MPSweeklyDemand,String periodDate) {
        this.workingDays = workingDays;
        this.week = week;
        this.MPSweeklyDemand = MPSweeklyDemand;
        this.periodDate = periodDate;
    }

    public int getGrossRequirment() {
        return grossRequirment;
    }

    public void setGrossRequirment(int grossRequirment) {
        this.grossRequirment = grossRequirment;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getMPSweeklyDemand() {
        return MPSweeklyDemand;
    }

    public void setMPSweeklyDemand(int MPSweeklyDemand) {
        this.MPSweeklyDemand = MPSweeklyDemand;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }

    
    public SalesPlan getPlan() {
        return plan;
    }

    public void setPlan(SalesPlan plan) {
        this.plan = plan;
    }

    public MRP getMrp() {
        return mrp;
    }

    public void setMrp(MRP mrp) {
        this.mrp = mrp;
    }

    public int getScheduledReceipts() {
        return scheduledReceipts;
    }

    public void setScheduledReceipts(int scheduledReceipts) {
        this.scheduledReceipts = scheduledReceipts;
    }

    public int getPlannedOrder() {
        return plannedOrder;
    }

    public void setPlannedOrder(int plannedOrder) {
        this.plannedOrder = plannedOrder;
    }

    public int getOnHandBefore() {
        return onHandBefore;
    }

    public void setOnHandBefore(int onHandBefore) {
        this.onHandBefore = onHandBefore;
    }

    public int getOnHandAfter() {
        return onHandAfter;
    }

    public void setOnHandAfter(int onHandAfter) {
        this.onHandAfter = onHandAfter;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }



    
    
}
