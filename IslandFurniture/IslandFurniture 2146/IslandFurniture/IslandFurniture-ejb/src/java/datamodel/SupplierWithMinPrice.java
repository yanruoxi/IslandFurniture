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
public class SupplierWithMinPrice implements Serializable {
    private int rank;
    private Supplier supplier;
    private Double unitPrice;
    private Part part;

    public SupplierWithMinPrice(int rank, Supplier supplier, Double unitPrice, Part part) {
        this.rank = rank;
        this.supplier = supplier;
        this.unitPrice = unitPrice;
        this.part = part;
    }

    public SupplierWithMinPrice() {
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
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
