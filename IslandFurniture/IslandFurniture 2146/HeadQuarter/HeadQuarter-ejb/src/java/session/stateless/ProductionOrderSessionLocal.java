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
import javax.ejb.Local;

/**
 *
 * @author Ruoxi
 */
@Local
public interface ProductionOrderSessionLocal {

    public ProductionOrder createProductionOrder(Product product, Store store, Factory factory, SalesForcast sales, String year, String month, Integer quantity);

    public List<ProductionOrder> getAllProductionOrder();

    public List<ProductionOrder> getProductionOrderByStoreName(String storeName);

    public List<ProductionOrder> getProductionOrderByFactory(String factoryName);

    public List<ProductionOrder> getProductionOrderByStorePlantProductMonth(String factoryName, String storeName, String productName, String month);

    public List<ProductionOrder> getProductionOrderByStorePlantProductMonthForWebService(String factoryName, String storeName, String productName, String month);
}
