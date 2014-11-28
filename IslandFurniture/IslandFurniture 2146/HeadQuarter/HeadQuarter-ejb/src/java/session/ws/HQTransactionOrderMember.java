/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.ws;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import session.stateless.TransactionOrderSessionLocal;

/**
 *
 * @author Ruoxi
 */
@WebService(serviceName = "HQTransactionOrderMember")
@Stateless()
public class HQTransactionOrderMember {
    @EJB
    private TransactionOrderSessionLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

   

    @WebMethod(operationName = "createHQMemberTransationOrderforPOS")
    @Oneway
    public void createHQMemberTransationOrderforPOS(@WebParam(name = "furnitureNameList") List<String> furnitureNameList, @WebParam(name = "amountList") List<Integer> amountList, @WebParam(name = "totalAmount") BigDecimal totalAmount, @WebParam(name = "date") String date, @WebParam(name = "transactionReference") String transactionReference, @WebParam(name = "customerPhone") String customerPhone, @WebParam(name = "storeName") String storeName) {
        ejbRef.createHQMemberTransationOrderforPOS(furnitureNameList, amountList, totalAmount, date, transactionReference, customerPhone, storeName);
    }
    
}
