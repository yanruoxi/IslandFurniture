/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Furniture;
import entity.SingleTransactionItem;
import entity.TransactionOrder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ruoxi
 */
@Stateless
public class TransactionOrderSession implements TransactionOrderSessionLocal {

    @PersistenceContext(unitName = "IslandFurniture-ejbPU")
    private EntityManager entityManager;

    @EJB
    private FurnitureSessionLocal furnitureSessionLocal;

    @Override
    public void createTransationOrderforPOS(List<String> furnitureNameList, List<Integer> amountList, BigDecimal totalAmount, String date, String transactionReference) {

        testTransationOrderforPOS(furnitureNameList, amountList, totalAmount, date, transactionReference);

    }

    public void testTransationOrderforPOS(List<String> furnitureNameList, List<Integer> amountList, BigDecimal totalAmount, String date, String transactionReference) {
        List<SingleTransactionItem> itemList = new ArrayList<>();

        System.out.println(totalAmount.toString() + "  " + date + "  " + transactionReference);
        int i = 0;
        while (i < furnitureNameList.size()) {
            System.out.println(furnitureNameList.get(i));
            i++;
        }

        TransactionOrder transactionOrder = new TransactionOrder();
        transactionOrder.setDate(date);
        transactionOrder.setIsMember(Boolean.FALSE);
        transactionOrder.setTotal(totalAmount);
        transactionOrder.setTransactionReference(transactionReference);
        entityManager.persist(transactionOrder);

        System.out.println("transaction order should be persisted");

        int j = 0;
        while (j < furnitureNameList.size()) {

            SingleTransactionItem singleItem = new SingleTransactionItem();
            //   Furniture furniture= furnitureSessionLocal.getFurnitureByName( furnitureNameList.get(j));
            singleItem.setFurniture(furnitureSessionLocal.getFurnitureByName(furnitureNameList.get(j)));
            
            
            //retrieve furniture entity
           Furniture furniture= furnitureSessionLocal.getFurnitureByName(furnitureNameList.get(j));
           Integer quantity=furniture.getFrontQuantity();
           quantity=quantity-amountList.get(j);
           furniture.setFrontQuantity(quantity);
            
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

//    @Override
//    public void createMemberTransationOrderforPOS(List<String> furnitureNameList, List<Integer> amountList, BigDecimal totalAmount, String date, String transactionReference, String customerPhone) {
//
//        List<SingleTransactionItem> itemList = new ArrayList<>();
//
//        System.out.println(totalAmount.toString() + "  " + date + "  " + transactionReference + "customer phone:" + customerPhone);
//        int i = 0;
//        while (i < furnitureNameList.size()) {
//            System.out.println(furnitureNameList.get(i));
//            i++;
//        }
//
//        TransactionOrder transactionOrder = new TransactionOrder();
//        transactionOrder.setDate(date);
//        transactionOrder.setIsMember(Boolean.TRUE);
//        transactionOrder.setTotal(totalAmount);
//        transactionOrder.setTransactionReference(transactionReference);
//        transactionOrder.setCustomerPhone(customerPhone);
//        entityManager.persist(transactionOrder);
//
//        System.out.println("transaction order should be persisted");
//
//        int j = 0;
//        while (j < furnitureNameList.size()) {
//
//            SingleTransactionItem singleItem = new SingleTransactionItem();
//            //   Furniture furniture= furnitureSessionLocal.getFurnitureByName( furnitureNameList.get(j));
//            singleItem.setFurniture(furnitureSessionLocal.getFurnitureByName(furnitureNameList.get(j)));
//            singleItem.setAmount(amountList.get(j));
//            singleItem.setTransactionOrder(transactionOrder);
//            entityManager.persist(singleItem);
//            itemList.add(singleItem);
//            transactionOrder.setItemList(itemList);
//            System.out.println("in while statement added singlitem");
//            j++;
//        }
//
//        System.out.println("exited while loop");
//
//        entityManager.merge(transactionOrder);
//        entityManager.flush();
//
//    }

}
