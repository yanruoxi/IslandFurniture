/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Factory;
import entity.Product;
import entity.ProductionOrder;
import entity.SalesForcast;
import entity.Store;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ruoxi
 */
@Stateless
public class ProductionOrderSession implements ProductionOrderSessionLocal {

    @PersistenceContext(unitName = "HeadQuarter-ejbPU")
    private EntityManager entityManager;
    @EJB
    StoreSessionLocal storeSessionLocal;
    @EJB
    ProductSessionLocal productSessionLocal;
    @EJB
    FactorySessionLocal factorySessionLocal;

    @Override
    public ProductionOrder createProductionOrder(Product product,Store store,Factory factory, SalesForcast sales, String year, String month, Integer quantity) {

        ProductionOrder productionOrder = new ProductionOrder();
        productionOrder.setFactory(factory);
        productionOrder.setStore(store);
        productionOrder.setProduct(product);
        productionOrder.setSalesForcast(sales);
        productionOrder.setMonth(month);
        productionOrder.setYear(year);
        productionOrder.setQuantity(quantity);
        
       store.getProductionOrderList().add(productionOrder);
       factory.getProductionOrderList().add(productionOrder);
       product.getProductionOrderList().add(productionOrder);
       sales.setProductionOrder(productionOrder);
       sales.setStatus("yes");//PO already created
               
        entityManager.persist(productionOrder);
        entityManager.merge(store);
        entityManager.merge(factory);
        entityManager.merge(product);
        entityManager.merge(sales);
        
        return productionOrder;
        

    }

    @Override
    public List<ProductionOrder> getAllProductionOrder() {
        Query query = entityManager.createQuery("SELECT e FROM ProductionOrder e");
        return query.getResultList();
    }

    @Override
    public List<ProductionOrder> getProductionOrderByStoreName(String storeName) {
        Query query = entityManager.createQuery("SELECT e FROM ProductionOrder e WHERE e.store.storeName= :inStoreName");
        query.setParameter("inStoreName", storeName);

        return query.getResultList();
    }

    @Override
    public List<ProductionOrder> getProductionOrderByFactory(String factoryName) {
        Query query = entityManager.createQuery("SELECT e FROM ProductionOrder e WHERE e.factory.factoryName =:inStoreName");
        query.setParameter("inStoreName", factoryName);

        return query.getResultList();
    }

    @Override
    public List<ProductionOrder> getProductionOrderByStorePlantProductMonth(String factoryName, String storeName, String productName, String month) {

        Query query = entityManager.createQuery("SELECT e FROM ProductionOrder e, IN (e.store) AS s WHERE s.storeName= :inStoreName");
        query.setParameter("inStoreName", storeName);

        return query.getResultList();

    }
    
    
    
    @Override
    public List<ProductionOrder> getProductionOrderByStorePlantProductMonthForWebService(String factoryName, String storeName, String productName, String month) {

        List<ProductionOrder> productionOrders = getProductionOrderByStorePlantProductMonth(factoryName, storeName, productName, month);
        
        for(ProductionOrder productionOrder:productionOrders)
        {
            entityManager.detach(productionOrder);
            productionOrder.getFactory().getProductionOrderList().clear();
        }

        return productionOrders;

    }

}
