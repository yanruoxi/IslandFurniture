/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import datamodel.RevenueForMonth;
import datamodel.TransactionWithTotalSpending;
import entity.TransactionOrderHQ;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ruoxi
 */
@Local
public interface TransactionOrderSessionLocal {

    public void createHQTransationOrderforPOS(List<String> furnitureNameList, List<Integer> amountList, BigDecimal totalAmount, String date, String transactionReference);

   public void createHQMemberTransationOrderforPOS(List<String> furnitureNameList, List<Integer> amountList, BigDecimal totalAmount, String date, String transactionReference, String customerPhone, String storeName) ;

   //ACRM
   public List<TransactionOrderHQ> getAllTransactions();
   public ArrayList<TransactionWithTotalSpending> segmentCustByTotalSpending();
   public ArrayList<TransactionWithTotalSpending> segmentCustByLowestTotalSpending();
    public ArrayList<RevenueForMonth> revenueForMonth(int month, int year);

}
