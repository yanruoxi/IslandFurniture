/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import datamodel.RevenueForMonth;
import datamodel.TransactionWithTotalSpending;
import entity.Customer;
import entity.SingleTransactionItemHQ;
import entity.Store;
import entity.TransactionOrderHQ;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class TransactionOrderSession implements TransactionOrderSessionLocal {

    @PersistenceContext(unitName = "HeadQuarter-ejbPU")
    private EntityManager entityManager;

    @EJB
    private ProductSessionLocal productSession;

    @EJB
    private CustomerSessionLocal customerSession;

    @EJB
    private StoreSessionLocal storeSession;

    @Override
    public void createHQTransationOrderforPOS(List<String> furnitureNameList, List<Integer> amountList, BigDecimal totalAmount, String date, String transactionReference) {

        List<SingleTransactionItemHQ> itemList = new ArrayList<>();

        System.out.println(totalAmount.toString() + "  " + date + "  " + transactionReference);
        int i = 0;
        while (i < furnitureNameList.size()) {
            System.out.println(furnitureNameList.get(i));
            i++;
        }

        TransactionOrderHQ transactionOrder = new TransactionOrderHQ();
        transactionOrder.setDate(date);
        transactionOrder.setIsMember(Boolean.FALSE);
        transactionOrder.setTotal(totalAmount);
        transactionOrder.setTransactionReference(transactionReference);
        entityManager.persist(transactionOrder);

        System.out.println("transaction order should be persisted");

        int j = 0;
        while (j < furnitureNameList.size()) {

            SingleTransactionItemHQ singleItem = new SingleTransactionItemHQ();
            //   Furniture furniture= furnitureSessionLocal.getFurnitureByName( furnitureNameList.get(j));

            singleItem.setFurntiture(productSession.getProduct(furnitureNameList.get(j)));
            singleItem.setAmount(amountList.get(j));
            singleItem.setTransactionOrder(transactionOrder);
            entityManager.persist(singleItem);
            itemList.add(singleItem);
            transactionOrder.setItemList(itemList);
            System.out.println("in while statement added singlitem");
            j++;
        }

        System.out.println("exited while loop");

        entityManager.merge(transactionOrder);
        entityManager.flush();

    }

    @Override
    public void createHQMemberTransationOrderforPOS(List<String> furnitureNameList, List<Integer> amountList, BigDecimal totalAmount, String date, String transactionReference, String customerPhone, String storeName) {

        List<SingleTransactionItemHQ> itemList = new ArrayList<>();

        System.out.println(totalAmount.toString() + "  " + date + "  " + transactionReference);
        int i = 0;
        while (i < furnitureNameList.size()) {
            System.out.println(furnitureNameList.get(i));
            i++;
        }

        TransactionOrderHQ transactionOrder = new TransactionOrderHQ();
        transactionOrder.setDate(date);
        transactionOrder.setIsMember(Boolean.FALSE);
        transactionOrder.setTotal(totalAmount);
        transactionOrder.setTransactionReference(transactionReference);

        //link transaction order to customer
        Customer customer = customerSession.getCustomerByPhone(customerPhone);

        System.out.println("Custoemr name for transaction order i s" + customer.getCustomerName());
        transactionOrder.setCustomer(customer);
//        customer.getTransationOrderList().add(transactionOrder);

        //link transaction order to store
        Store store = storeSession.getStore(storeName);
        transactionOrder.setStore(store);
        store.getTransationOrderList().add(transactionOrder);

        entityManager.merge(customer);
        entityManager.merge(store);

        System.out.println("transaction order should be persisted");

        int j = 0;
        while (j < furnitureNameList.size()) {

            SingleTransactionItemHQ singleItem = new SingleTransactionItemHQ();
            //   Furniture furniture= furnitureSessionLocal.getFurnitureByName( furnitureNameList.get(j));

            singleItem.setFurntiture(productSession.getProduct(furnitureNameList.get(j)));
            singleItem.setAmount(amountList.get(j));
            singleItem.setTransactionOrder(transactionOrder);
            entityManager.persist(singleItem);
            itemList.add(singleItem);
            transactionOrder.setItemList(itemList);
            System.out.println("in while statement added singlitem");
            j++;
        }

        System.out.println("exited while loop");

        entityManager.persist(transactionOrder);
        entityManager.flush();

    }

    // ACRM
    // Retrieve all Transactions
    @Override
    public List<TransactionOrderHQ> getAllTransactions() {
        Query query = entityManager.createQuery("SELECT s FROM TransactionOrderHQ s WHERE s.isMember = true");
        return query.getResultList();
    }

    @Override
    public ArrayList<TransactionWithTotalSpending> segmentCustByTotalSpending() {
        Query query = entityManager.createQuery("SELECT t, SUM(t.total) AS a FROM TransactionOrderHQ t WHERE t.isMember = true GROUP BY t.customer ORDER BY a DESC");
        System.out.println("query.getResultList() size:" + query.getResultList().size());

        List<Object[]> objs = query.getResultList();
        ArrayList<TransactionWithTotalSpending> arr = new ArrayList<TransactionWithTotalSpending>();
        for (Object[] obj : objs) {
            System.err.println("obj[0]: " + ((TransactionOrderHQ) obj[0]).getId());
            arr.add(new TransactionWithTotalSpending((TransactionOrderHQ) obj[0], (BigDecimal) obj[1]));
            System.out.println("(TransactionOrderHQ)obj[0], (Double)obj[1]" + (TransactionOrderHQ) obj[0] + "," + (BigDecimal) obj[1]);
        }
        return arr;
    }

    @Override
    public ArrayList<TransactionWithTotalSpending> segmentCustByLowestTotalSpending() {
        Query query = entityManager.createQuery("SELECT t, SUM(t.total) AS a FROM TransactionOrderHQ t WHERE t.isMember = true GROUP BY t.customer ORDER BY a ASC");
        System.out.println("query.getResultList() size:" + query.getResultList().size());

        List<Object[]> objs = query.getResultList();
        ArrayList<TransactionWithTotalSpending> arr = new ArrayList<TransactionWithTotalSpending>();
        for (Object[] obj : objs) {
            System.err.println("obj[0]: " + ((TransactionOrderHQ) obj[0]).getId());
            arr.add(new TransactionWithTotalSpending((TransactionOrderHQ) obj[0], (BigDecimal) obj[1]));
            System.out.println("(TransactionOrderHQ)obj[0], (Double)obj[1]" + (TransactionOrderHQ) obj[0] + "," + (BigDecimal) obj[1]);
        }
        return arr;
    }

    @Override
    public ArrayList<RevenueForMonth> revenueForMonth(int month, int year) {
        
        System.out.println("year equals:" + String.valueOf("year").trim().equals("2014"));
        String m = String.valueOf(month);   
        String y = String.valueOf(year);
        
        Query query = entityManager.createQuery("SELECT SUM(t.total), t.month FROM TransactionOrderHQ t WHERE t.year = :year");
        query.setParameter("month", String.valueOf(m));
        query.setParameter("year", String.valueOf(y));

        System.out.println("year " + year);

        System.out.println("query.getResultList() size:" + query.getResultList().size());
        List<Object[]> objs = query.getResultList();
        ArrayList<RevenueForMonth> arr = new ArrayList<RevenueForMonth>();
//        for (Object[] obj : objs) {
//            System.err.println("obj[0]: " + ((BigDecimal) obj[0]));
//            arr.add(new RevenueForMonth((BigDecimal) obj[0], (int) obj[1]));
//            System.out.println("(TransactionOrderHQ)obj[0], (Double)obj[1]" + (BigDecimal) obj[0] + "," + (int) obj[1]);
//        }
        return arr;
    }

}
