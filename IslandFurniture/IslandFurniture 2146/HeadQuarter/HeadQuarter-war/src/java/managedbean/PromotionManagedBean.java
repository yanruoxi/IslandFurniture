/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import entity.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import session.stateless.ProductSessionLocal;

/**
 *
 * @author Wu
 */
@Named(value = "promotionManagedBean")
@ViewScoped
public class PromotionManagedBean implements Serializable {
    
    @EJB
     private ProductSessionLocal productSessionLocal;
    private String productName;
    private String discount;
    private List<String> productNameList=new ArrayList<>();
    private List<Product> promotionProductList = new ArrayList<>();
    

    /**
     * Creates a new instance of PromotionManagedBean
     */
    public PromotionManagedBean() {
    }

    public List<Product> getPromotionProductList() {
        promotionProductList.clear();
        promotionProductList = productSessionLocal.getAllPromotionProduct();
        
        return promotionProductList;
    }

    public void setPromotionProductList(List<Product> promotionProductList) {
        this.promotionProductList = promotionProductList;
    }

    public ProductSessionLocal getProductSessionLocal() {
        
        return productSessionLocal;
    }

    public void setProductSessionLocal(ProductSessionLocal productSessionLocal) {
        this.productSessionLocal = productSessionLocal;
    }

    public List<String> getProductNameList() {
        productNameList.clear();
        productNameList = productSessionLocal.getAllProductName();
        return productNameList;
    }

    public void setProductNameList(List<String> productNameList) {
        this.productNameList = productNameList;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
    
    public void createPromotion(ActionEvent event){
        try{
            
        
        long l = productSessionLocal.createPromotion(productName,Float.valueOf(discount));
        if(l==2l){
             FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Promotion added Successfully", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
            FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Product is within promotion, please choose another product", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }catch(NumberFormatException e){
         FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "discount format is wrong ", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    }
    
    public void invalidPromotion(ActionEvent event){
        Long rgId;
        
        rgId = (Long) event.getComponent().getAttributes().get("RGId");
        productSessionLocal.invalidPromotion(rgId);
    }
    
}
