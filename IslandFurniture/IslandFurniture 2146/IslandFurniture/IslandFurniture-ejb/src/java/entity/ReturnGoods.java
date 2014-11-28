/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Collection;
//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;

/**
 *
 * @author WangYan
 */
@Entity(name = "ReturnGoods")
public class ReturnGoods implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String goodsName;
    private String marketPrice;
    private float discountprice=0;
    private String deletema="N";
    private String status="RETURNED";
    private Integer quantity;
    private String position ="return position in warehouse";
    private String returnReason="";
    
    @ManyToOne
    private Warehouse warehouse = new Warehouse();

    
    public ReturnGoods(){
        
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDeletema() {
        return deletema;
    }

    public void setDeletema(String deletema) {
        this.deletema = deletema;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

   

    
    public float getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(float discountprice) {
        this.discountprice = discountprice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

   
    
    
    
    
}