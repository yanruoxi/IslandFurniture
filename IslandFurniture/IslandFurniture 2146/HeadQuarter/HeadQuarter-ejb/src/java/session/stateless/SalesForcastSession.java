/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Product;
import entity.SalesForcast;
import entity.SingleTransactionItemHQ;
import entity.Store;
import entity.TransactionOrderHQ;
import java.util.List;
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
public class SalesForcastSession implements SalesForcastSessionLocal {

    @PersistenceContext(unitName = "HeadQuarter-ejbPU")
    private EntityManager entityManager;
    
   @EJB
    private StoreSessionLocal storeSessionLocal;
   
   @EJB
   private ProductSessionLocal productSession;

    //check if the sales forcast is already created
    @Override
    public Boolean checkSalesForcastExist(String storeName, String productName, String year, String month) {
        Query query = entityManager.createQuery("SELECT u FROM SalesForcast u WHERE u.product.productName = :inProductName AND u.store.storeName = :inStoreName AND u.year = :inYear AND u.month = :inMonth AND u.isDeleted= :inIsDeleted");
        query.setParameter("inProductName", productName);
        query.setParameter("inStoreName", storeName);
        query.setParameter("inYear", year);
        query.setParameter("inMonth", month);
        query.setParameter("inIsDeleted", "no");

        SalesForcast salesForcast = null;

        try {
            salesForcast = (SalesForcast) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }
        if (salesForcast != null) {
            //true means the sales forcast is already created
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Boolean checkPastDataExist(String storeName, String productName, String year, String month) {

        System.out.println("inputed yeat and mont for check past data exist year is " + year + "mont is " + month);
        //get the last year's data by this year -1
        Integer y = Integer.valueOf(year);
        y--;
        year = y.toString();

        System.out.println("new year is " + year);

        Query query = entityManager.createQuery("SELECT u FROM TransactionOrderHQ u , SingleTransactionItemHQ q WHERE q.furntiture.productName = :inProductName AND q.transactionOrder.id=u.id AND u.store.storeName = :inStoreName AND u.year = :inYear AND u.month = :inMonth ");
        query.setParameter("inProductName", productName);
        query.setParameter("inStoreName", storeName);
        query.setParameter("inYear", year);
        query.setParameter("inMonth", month);

        List<TransactionOrderHQ> transactionOrderList = query.getResultList();

        if (transactionOrderList.isEmpty()) {
            //true means no past transaction data
            return true;
        } else {
            return false;
        }

    }

    //calculate total past sales amount
    @Override
    public Integer createSalesForcast(String storeName, String productName, String year, String month) {

        System.out.println("inputed yeat and mont for check past data exist year is " + year + "mont is " + month);
        //get the last year's data by this year -1
        Integer y = Integer.valueOf(year);
        y--;
        year = y.toString();

        System.out.println("new year is " + year);

        Query query = entityManager.createQuery("SELECT q FROM TransactionOrderHQ u , SingleTransactionItemHQ q WHERE q.furntiture.productName = :inProductName AND q.transactionOrder.id=u.id AND u.store.storeName = :inStoreName AND u.year = :inYear AND u.month = :inMonth ");
        query.setParameter("inProductName", productName);
        query.setParameter("inStoreName", storeName);
        query.setParameter("inYear", year);
        query.setParameter("inMonth", month);

        List<SingleTransactionItemHQ> itemList = query.getResultList();

        //total recored total sales data
        Integer total = 0;
        if (!itemList.isEmpty()) {

            for (SingleTransactionItemHQ item : itemList) {
                total = total + item.getAmount();
            }
        }
        return total;
    }
    //cearte and persiste the sales Forcst
    @Override
    public void createSalesForecast(String storeName, String productName, String year, String month, Integer amount){
    
    SalesForcast salesForcast= new SalesForcast();
    
    Store store= storeSessionLocal.getStore(storeName);
    salesForcast.setStore(store);
    
    Product product= productSession.getProduct(productName);
    salesForcast.setProduct(product);
    
    salesForcast.setYear(year);
    salesForcast.setMonth(month);
    salesForcast.setIsDeleted("no");
    salesForcast.setAmount(amount);
    salesForcast.setStatus("no");
    salesForcast.setProductionOrder(null);
    
    entityManager.persist(salesForcast);
    entityManager.merge(store);
    entityManager.merge(product);
    
    }
    
 //retrieve all sales forecast
    @Override
    public List<SalesForcast> getAllSalesForcast() {
        Query query = entityManager.createQuery("SELECT e FROM SalesForcast e WHERE e.isDeleted= :inIsDeleted");
        query.setParameter("inIsDeleted", "no");
        return query.getResultList();
    }
//delete a sales forecast
    @Override
    public void deleteSalesForecast(SalesForcast sales){
    sales.setIsDeleted("yes");
    entityManager.merge(sales);
    
    }
    
    

}
