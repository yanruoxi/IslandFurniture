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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ruoxi
 */
@Stateless
public class ProductSession implements ProductSessionLocal {

    @PersistenceContext(unitName = "HeadQuarter-ejbPU")
    private EntityManager entityManager;

    @EJB
    private StoreSessionLocal storeSessionLocal;

    @EJB
    private FactorySessionLocal factorySessionLocal;

    @Override
    public Product getProduct(String productName) {
        Query query = entityManager.createQuery("SELECT u FROM Product u WHERE u.productName = :inProductName AND u.isDeleted= :inIsDeleted");
        query.setParameter("inProductName", productName);
         query.setParameter("inIsDeleted", "no");
        Product product = null;
        try {
            product = (Product) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }
        return product;
    }

    
  @Override
    public Product getProduct1(String productName,String category) {
        Query query = entityManager.createQuery("SELECT u FROM Product u WHERE u.productName = :inProductName and  u.category=:inCategory");
        query.setParameter("inProductName", productName);
        query.setParameter("inCategory", category);
        Product product = null;
        try {
            product = (Product) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }
        return product;
}
    @Override
    public Product getProductPOS(String productName, String companyType, String companyName) {
        
        System.out.println("come to get product POS web service" + productName+ companyType+ companyName );

//        Query query = entityManager.createQuery("SELECT u FROM Product u WHERE u.productName = :inProductName");
//        query.setParameter("inProductName", productName);
//        Product product = null;
//        try {
//            product = (Product) query.getSingleResult();
//        } catch (NoResultException ex) {
//            System.out.println("canght no result exception");
//        }
//
//        if (product != null) {
//            entityManager.detach(product);
//            product.getProductionOrderList().clear();
//        //    product.getStores().clear();
//        }
//
//        Factory factory = factorySessionLocal.getFactoryByName(companyName);
//
//        Query q = entityManager.createQuery("SELECT p FROM Factory s, Product p WHERE s.products = p AND s.factoryName = :FactoryName");
//        q.setParameter("FactoryName", factory.getFactoryName());
//        List<Product> productResultList = q.getResultList();
//        
//        Boolean exist=true;
//        
//        if(productResultList.isEmpty()){
//        }else{
//           for (int i = 0; i < productResultList.size(); i++) {
//               
//               if(product.getId().equals(productResultList.get(i).getId())){
//               
//               exist=false;
//               }             
//            }
//        }
//        
//        if(exist){
//        product.getFactories().add(factory);        
//        factory.getProducts().add(product);
//        }
//        entityManager.merge(product);
//        entityManager.merge(factory);
        Product product=new Product();
     return product;
    }
    

    @Override
     public void createProduct(String productName, String type,String category,BigDecimal pri,Double height,Double width,Double length,String color,String productDescription,String instructions,String path){
      
        Product product = new Product();
        product.setProductName(productName);
        product.setType(type);
        product.setCategory(category);
        product.setColor(color);
        product.setHeight(height);
        product.setWidth(width);
        product.setLength(length);
        product.setProductDescription(productDescription);
        
        product.setInstructions(instructions);
        product.setPrice(pri);
        product.setPath(path);
        product.setIsDeleted("no");

        entityManager.persist(product);
        entityManager.flush();
    }
    
    
    @Override
    public List<Product> getAllProduct() {
        Query query = entityManager.createQuery("SELECT e FROM Product e WHERE e.isDeleted= :inIsDeleted");
        query.setParameter("inIsDeleted", "no");
        return query.getResultList();
    }
    @Override
     public List<String> getAllProductName(){
         List<String> s = new ArrayList<>();
         s.clear();
         Query query = entityManager.createQuery("SELECT e FROM Product e WHERE e.isDeleted= :inIsDeleted");
        query.setParameter("inIsDeleted", "no");
        for(Object o:query.getResultList()){
            Product p = (Product)o;
            s.add(p.getProductName());
            
        }
        return s;
     }
     @Override
      public List<Product> getAllPromotionProduct(){
       //    List<Product> s = new ArrayList<>();
       // s.clear();
         Query query = entityManager.createQuery("SELECT e FROM Product e WHERE e.isDeleted= :inIsDeleted and e.pomotion=:eP");
        query.setParameter("inIsDeleted", "no");
        query.setParameter("eP", "Y");
        return query.getResultList();
      }
    
    @Override
    public void deleteProduct(String productName) {            
        Product product=getProduct(productName);      
        product.setIsDeleted("yes");
        entityManager.merge(product);      
    }
    
     @Override
    public void resetProductName(String productName, String newProductName) {
        Product product=getProduct(productName);
        product.setProductName(newProductName);  
        entityManager.merge(product);
        entityManager.flush();
    }
    
    // Retrieve all Product Name
    @Override
    public List<String> getProductsString(){
        Query query = entityManager.createQuery("SELECT p.productName FROM Product p WHERE p.isDeleted != 'yes'");
        List<String> productsString = new ArrayList<String>(query.getResultList());
        
         System.out.println(productsString.isEmpty());
        
        System.out.println(productsString.size());
        return productsString;
    }
    
      // Add Part(s) to Supplier
    @Override
    public boolean addProductToStore(Store selectedStore, Set<Product> selectedProductsToAdd) {
        Store sResult = entityManager.find(Store.class, selectedStore.getId()); // Retrieve existing Supplier object
        List<Product> selectedProductsToAddList = new ArrayList<Product>(selectedProductsToAdd); // Convert Set to List
        // If existing Part to Supplier pair does not exist
        if(!productToStoreExist(selectedStore, selectedProductsToAddList)){
            for(int i = 0; i < selectedProductsToAdd.size(); i++){
                sResult.getProducts().add(selectedProductsToAddList.get(i)); // Add List of Part objects to Supplier object
            }
            entityManager.persist(sResult);
            entityManager.flush();
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
      public long createPromotion(String productName, float discount){
          Query q = entityManager.createQuery("select p from Product p where p.productName=:pName");
          q.setParameter("pName", productName);
          Product p = (Product)q.getResultList().get(0);
          if(p.getPomotion().equals("Y")){
              return -2l;//this product is promoted ;
          }else{
             p.setPomotion("Y");
            Double discounts = (1- discount*0.01);
            String price =  String.valueOf(p.getPrice());
            Double dp = Double.valueOf(price);
            Double discountPrice = dp*discounts;
           
            p.setDiscountPrice(BigDecimal.valueOf(discountPrice));
           entityManager.merge(p);
           return 2l;
          }
          
      }
		@Override
        public void invalidPromotion(Long productId){
            Product p = entityManager.find(Product.class, productId);
            p.setPomotion("N");
            entityManager.persist(p);
		}
	@Override
    public boolean productToStoreExist(Store selectedStore, List<Product> selectedProductsToAdd){
        // Search for existing pair of Part and Supplier based on SupplierId
        Query q = entityManager.createQuery("SELECT p FROM Store s, Product p WHERE s.products = p AND s.id = :selectedStoreId");
        q.setParameter("selectedStoreId", selectedStore.getId());
        List<Product> productResultList = q.getResultList();
    
        // If no records are found
        if(productResultList.isEmpty()){
            return false;
        }
        else {
            // Compare partResultList(from db) to selectedPartsToAdd (from checkbox)
            // If both contains the same PartId, it means record is already in db, so it exists
            for (int i = 0; i < productResultList.size(); i++) {
                for (int j = 0; j < selectedProductsToAdd.size(); j++) {
                    if (selectedProductsToAdd.get(j).getId().equals(productResultList.get(i).getId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
      // Add Part(s) to Supplier
    @Override
    public boolean addProductToFactory(Factory selectedFactory, Set<Product> selectedProductsToAdd) {
        Factory sResult = entityManager.find(Factory.class, selectedFactory.getId()); // Retrieve existing Supplier object
        List<Product> selectedProductsToAddList = new ArrayList<Product>(selectedProductsToAdd); // Convert Set to List
        // If existing Part to Supplier pair does not exist
        if(!productToFactoryExist(selectedFactory, selectedProductsToAddList)){
            for(int i = 0; i < selectedProductsToAdd.size(); i++){
                sResult.getProducts().add(selectedProductsToAddList.get(i)); // Add List of Part objects to Supplier object
            }
            entityManager.persist(sResult);
            entityManager.flush();
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public boolean productToFactoryExist(Factory selectedFactory, List<Product> selectedProductsToAdd){
        // Search for existing pair of Part and Supplier based on SupplierId
        Query q = entityManager.createQuery("SELECT p FROM Factory s, Product p WHERE s.products = p AND s.id = :selectedStoreId");
        q.setParameter("selectedStoreId", selectedFactory.getId());
        List<Product> productResultList = q.getResultList();
    
        // If no records are found
        if(productResultList.isEmpty()){
            return false;
        }
        else {
            // Compare partResultList(from db) to selectedPartsToAdd (from checkbox)
            // If both contains the same PartId, it means record is already in db, so it exists
            for (int i = 0; i < productResultList.size(); i++) {
                for (int j = 0; j < selectedProductsToAdd.size(); j++) {
                    if (selectedProductsToAdd.get(j).getId().equals(productResultList.get(i).getId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    //retrieve the list of products linked to selected store
    @Override
    public List<Product> getProductsForSelectedStore(Store selectedStore){
     // Search for existing pair of Part and Supplier based on SupplierId
        Query q = entityManager.createQuery("SELECT p FROM Store s, Product p WHERE s.products = p AND s.id = :selectedStoreId");
        q.setParameter("selectedStoreId", selectedStore.getId());
        List<Product> productResultList = q.getResultList();
        return productResultList;
    }
    
      
    // Retrieve a Product based on productID
    @Override
    public Product getProduct(Long selectedStoreId) {
        Product gs = entityManager.find(Product.class, selectedStoreId);
        return gs;
    }

}
