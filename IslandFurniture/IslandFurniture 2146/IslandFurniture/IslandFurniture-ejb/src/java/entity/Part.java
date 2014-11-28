/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Ruoxi
 */
@Entity
public class Part implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String partName;
    private Integer lotSize;
    private int leadTime;
    @ManyToMany(mappedBy = "parts")
    private Set<Supplier> suppliers = new HashSet<Supplier>();
//    @ManyToMany(mappedBy="parts")
//    private Set<Quotation> quotations = new HashSet<Quotation>();
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "part")
    private Collection<BOM> bom = new ArrayList<BOM>();
    @OneToOne
    private Inventory inventory;
    @OneToOne
    private Contract contract;
    private String isDeleted;

    public Part() {
        //constructor;
    }

    public void create(String partName, Integer lotSize) {
        this.setPartName(partName);
        this.setLotSize(lotSize);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Integer getLotSize() {
        return lotSize;
    }

    public void setLotSize(Integer lotSize) {
        this.lotSize = lotSize;
    }

    public int getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(int leadTime) {
        this.leadTime = leadTime;
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

//    public Set<Quotation> getQuotations() {
//        return quotations;
//    }
//
//    public void setQuotations(Set<Quotation> quotations) {
//        this.quotations = quotations;
//    }
    public Collection<BOM> getBom() {
        return bom;
    }

    public void setBom(Collection<BOM> bom) {
        this.bom = bom;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Part)) {
            return false;
        }
        Part other = (Part) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Part[ id=" + id + " ]";
    }

}
