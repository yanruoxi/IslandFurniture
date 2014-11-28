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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author WangYan
 */
@Entity(name="Front")
public class Front implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String frontName;
     @OneToMany(cascade={CascadeType.PERSIST},mappedBy="front" )
    private Collection<Furniture> materialCollection=new ArrayList<>();
    @OneToOne(cascade={CascadeType.PERSIST},mappedBy = "front")
    private Warehouse warehouse =new Warehouse();

    
    public Front(){
        
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
    
    public Collection<Furniture> getMaterialCollection() {
        return materialCollection;
    }

    public void setMaterialCollection(Collection<Furniture> materialCollection) {
        this.materialCollection = materialCollection;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getFrontName() {
        return frontName;
    }

    public void setFrontName(String frontName) {
        this.frontName = frontName;
    }

   
        
    
}
