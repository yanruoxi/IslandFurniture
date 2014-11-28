/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.Furniture;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ruoxi
 */
@Local
public interface TransactionOrderSessionLocal {
    
     public void createTransationOrderforPOS( List<String>furnitureNameList, List <Integer> amountList, BigDecimal totalAmount, String date,String transactionReference );
    
   //  public void createMemberTransationOrderforPOS(List<String> furnitureNameList, List<Integer> amountList, BigDecimal totalAmount, String date, String transactionReference, String customerPhone);
}
