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
public class SupplierWithPart implements Serializable{
    private Supplier supplier;
    private Part part;
    private Long freq;

    public SupplierWithPart(Supplier supplier, Part part) {
        this.supplier = supplier;
        this.part = part;
    }
    
    public SupplierWithPart() {
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Long getFreq() {
        return freq;
    }

    public void setFreq(Long freq) {
        this.freq = freq;
    }
}
