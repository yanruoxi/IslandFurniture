/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.Factory;
import entity.Product;
import entity.Store;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author Ruoxi
 */
@Local
public interface ProductSessionLocal {
    
     public Product getProduct1(String productName,String category);
     public Product getProduct(String productName);
      public Product getProductPOS(String productName, String companyType, String companyName);
       public void createProduct(String productName, String type,String category,BigDecimal pri,Double height,Double width,Double length,String color,String productDescription,String instructions, String path);
    
      public List<Product> getAllProduct();
      public List<String> getAllProductName();
      public List<Product> getAllPromotionProduct();
      
       public void deleteProduct(String productName);
       
        public void resetProductName(String productName, String newProductName);
    public long createPromotion(String productName, float discount);
     public List<String> getProductsString();   
public boolean addProductToStore(Store selectedStore, Set<Product> selectedProductsToAdd);

    public boolean productToStoreExist(Store selectedStore, List<Product> selectedProductsToAdd);

    public boolean addProductToFactory(Factory selectedFactory, Set<Product> selectedProductsToAdd);

    public boolean productToFactoryExist(Factory selectedFactory, List<Product> selectedProductsToAdd);

    public List<Product> getProductsForSelectedStore(Store selectedStore);

    public Product getProduct(Long selectedStoreId);
  public void invalidPromotion(Long productId);
    
}
