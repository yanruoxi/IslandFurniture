/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Customer;
import entity.Product;
import entity.Shoppingcart;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author WangYan
 */
@Stateless
public class ecommerceSessionBean implements ecommerceSessionBeanLocal {

    @PersistenceContext
    EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Product> getSearchList(String input) {
        Query q = em.createQuery("select p from Product p where p.category=:input or p.color=:input or p.productName=:input or p.type=:input ");
        q.setParameter("input".toLowerCase(), input.toLowerCase());
        return q.getResultList();

    }

    @Override
    public List<Product> getAllProducts() {
        Query q = em.createQuery("select p from Product p");
        return q.getResultList();

    }

    @Override
    public List<Product> getAllSofa() {
        Query q = em.createQuery("select s from Product s where s.category='SOFA'");
        return q.getResultList();

    }

    @Override
    public List<Product> getAllArmChair() {
        Query q = em.createQuery("select a from Product a where a.category='ARMCHAIR'");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllTV() {
        Query q = em.createQuery("select p from Product p where p.category='TV' or p.category ='MEDIA'");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllLight() {
        Query q = em.createQuery("select a from Product a where a.category='BEDROOM LIGHTING'");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllBathtub() {
        Query q = em.createQuery("select p from Product p where p.category='TV' or p.category ='BATHTUB'");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllShower() {
        Query q = em.createQuery("select p from Product p where  p.category ='SHOWER'");

        return q.getResultList();
    }

    @Override
    public List<Product> getAllVanity() {
        Query q = em.createQuery("SELECT p from Product p where p.category = 'VANITY'");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllTapSink() {
        Query q = em.createQuery("SELECT p from Product p where p.category = 'TAP' or p.category='SINK'");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllWorktop() {
        Query q = em.createQuery("SELECT p from Product p where p.category = 'WORKTOP'");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllCabinet() {
        Query q = em.createQuery("SELECT p from Product p where p.category = 'KITCHEN CABINET'");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllFrame() {
        Query q = em.createQuery("SELECT p from Product p where p.category = 'BED FRAME'");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllWardrobe() {
        Query q = em.createQuery("SELECT p from Product p where p.category = 'WARDROBE'");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllLivingRoom() {
        Query q = em.createQuery("SELECT p from Product p where p.category = 'SOFA' or p.category='ARMCHAIR' OR p.category='TV' or p.category='MEDIA'");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllBathRoom() {
        Query q = em.createQuery("SELECT p from Product p where p.category = 'BATHTUB' OR p.category='SHOWER' OR p.category='VANITY'");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllBedRoom() {
        Query q = em.createQuery("SELECT p from Product p where p.category = 'BED FRAME' OR p.category='BEDROOM LIGHTING' OR p.category='WARDROBE'");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllKitchen() {
        Query q = em.createQuery("SELECT p from Product p where p.category = 'KITCHEN CABINET' OR p.category='WORKTOP' OR p.category='TAP' OR p.category='SINK'");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllDecoration() {
        Query q = em.createQuery("SELECT p from Product p where p.category = 'DECORATION'");
        return q.getResultList();
    }

    @Override
    public List<Product> getAllPromotion() {
        Query q = em.createQuery("SELECT p from Product p where p.pomotion='Y'");
        return q.getResultList();
    }

    @Override
    public Product getProduct(Long id) {
        Product p = em.find(Product.class, id);
        return p;
    }

    @Override
    public void addToShoppingCart(Product product, Customer customer) {
       int check = 0;
        
        Shoppingcart shoppingcart = customer.getShoppingcart();
        if(shoppingcart==null){
             System.out.println("test for 0");
            shoppingcart = new Shoppingcart();
            shoppingcart.getProducts().add(product);
            shoppingcart.setCustomer(customer);
            em.persist(shoppingcart);
            customer.setShoppingcart(shoppingcart);
            em.persist(customer);
        }else{
            Collection<Product> pl = shoppingcart.getProducts();
            for(Object o:pl){
                Product p = (Product)o;
                if (p.getProductName().equals(product.getProductName())){
                    System.out.print("already add to shoppingcart");
                    check = 1;break;
                    
               }
                    
            }
            if(check==1){
                System.out.print("already add to shoppingcart");}
            else{
                
            
            
            System.out.println("test for 1");
            customer.getShoppingcart().getProducts().add(product);
            em.merge(customer.getShoppingcart());
          
            System.out.println("test for 2");
           
            em.merge(customer);
            System.out.println("test for 3");
            
        }}
    }
    @Override
    public Collection<Product> getShoppingCartList(Customer customer){
       return customer.getShoppingcart().getProducts();

}
}
