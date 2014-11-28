/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 *
 * @author WangYan
 */
@Entity(name="Warehouse")
public class Warehouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String warehouseName;
    @OneToMany(cascade={CascadeType.PERSIST},mappedBy="warehouse" )
    private Collection<Furniture> materialCollection=new ArrayList<>();
    @OneToOne(cascade={CascadeType.PERSIST})
    private Front front;
    @OneToMany(cascade={CascadeType.PERSIST},mappedBy="warehouse")
    private Collection<ReturnGoods> returnGoodsCollection = new ArrayList<>();
    
    private String deletema = "N";
    private String address;
    //private int size;
    
    //Big Size & unfold inventory ;store 10 kinds of materials storeage, each material storeage can store 10 materials
    private Furniture[][] BigArray = new Furniture[10][10];
 
    
    //shelf inventory;// within 10 shelf ,each shelf have 10 rows, each row contain 10 material
    private Furniture[][][] SUArray = new Furniture[10][10][10];
  
    
   
    
    
    //return goods position
    private Furniture[][] RG = new Furniture[10][10];
  
  
  
    
  
   
   
   
  
   
   
   
    
    public Warehouse(){
        
    }

    public String getDeletema() {
        return deletema;
    }

    public void setDeletema(String deletema) {
        this.deletema = deletema;
    }

    public Collection<ReturnGoods> getReturnGoodsCollection() {
        return returnGoodsCollection;
    }

    public void setReturnGoodsCollection(Collection<ReturnGoods> returnGoodsCollection) {
        this.returnGoodsCollection = returnGoodsCollection;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
/*
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
*/
   

    public Furniture[][] getRG() {
        return RG;
    }

    public void setRG(Furniture[][] RG) {
        this.RG = RG;
    }

    public Furniture[][] getBigArray() {
        return BigArray;
    }

    public void setBigArray(Furniture[][] BigArray) {
        this.BigArray = BigArray;
    }

    public Furniture[][][] getSUArray() {
        return SUArray;
    }

    public void setSUArray(Furniture[][][] SUArray) {
        this.SUArray = SUArray;
    }

   

   
   

    public Front getFront() {
        return front;
    }

    public void setFront(Front front) {
        this.front = front;
    }
    
    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Collection<Furniture> getMaterialCollection() {
        return materialCollection;
    }

    public void setMaterialCollection(Collection<Furniture> materialCollection) {
        this.materialCollection = materialCollection;
    }

   
    
}
