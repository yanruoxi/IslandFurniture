/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datamodel;

import entity.Part;
import entity.Supplier;
import java.io.Serializable;

/**
 *
 * @author Meiling
 */
public class SupplierWithAvgQty implements Serializable{
    private int rank;
    private Supplier supplier;
    private Double avgQty;
    private Part part;

    public SupplierWithAvgQty(int rank, Supplier supplier, Double avgQty, Part part) {
        this.rank = rank;
        this.supplier = supplier;
        this.avgQty = avgQty;
        this.part = part;
    }

    public SupplierWithAvgQty() {
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Double getAvgQty() {
        return avgQty;
    }

    public void setAvgQty(Double avgQty) {
        this.avgQty = avgQty;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    
}
