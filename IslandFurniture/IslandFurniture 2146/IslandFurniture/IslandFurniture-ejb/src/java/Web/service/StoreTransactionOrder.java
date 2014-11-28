/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Web.service;

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
@WebService(serviceName = "StoreTransactionOrder")
@Stateless()
public class StoreTransactionOrder {
    @EJB
    private TransactionOrderSessionLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "createTransationOrderforPOS")
    @Oneway
    public void createTransationOrderforPOS(@WebParam(name = "furnitureNameList") List<String> furnitureNameList, @WebParam(name = "amountList") List<Integer> amountList, @WebParam(name = "totalAmount") BigDecimal totalAmount, @WebParam(name = "date") String date, @WebParam(name = "transactionReference") String transactionReference) {
        ejbRef.createTransationOrderforPOS(furnitureNameList, amountList, totalAmount, date, transactionReference);
    }
    
}
