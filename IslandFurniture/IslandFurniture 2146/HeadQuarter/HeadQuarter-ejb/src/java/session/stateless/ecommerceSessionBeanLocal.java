/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.Customer;
import entity.Product;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wangyan
 */
@Local
public interface ecommerceSessionBeanLocal {
    public List<Product> getAllProducts();
    public List<Product> getAllSofa();
    public List<Product> getAllArmChair();
    public List<Product> getAllTV();
    public List<Product> getAllVanity();
    public List<Product> getAllBathtub();
    public List<Product> getAllLight();
    public List<Product> getAllShower();
    public List<Product> getAllTapSink();
    public List<Product> getAllWorktop();
    public List<Product> getAllCabinet();
    public List<Product> getAllFrame();
    public List<Product> getAllWardrobe();
    public List<Product> getAllLivingRoom();
    public List<Product> getAllBathRoom();
    public List<Product> getAllBedRoom();
    public List<Product> getAllKitchen();
    public List<Product> getAllDecoration();
    public List<Product> getAllPromotion();
    public Collection<Product> getShoppingCartList(Customer customer);
    
    
    public Product getProduct(Long id);
    
    public void addToShoppingCart(Product product,Customer customer);
   public List<Product> getSearchList(String input);
}
