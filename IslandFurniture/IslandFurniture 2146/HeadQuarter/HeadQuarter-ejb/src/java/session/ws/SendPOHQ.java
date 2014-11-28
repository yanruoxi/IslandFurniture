/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.ws;

import entity.ProductionOrder;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import session.stateless.SendPOLocal;

/**
 *
 * @author Ruoxi
 */
@WebService(serviceName = "SendPOHQ")
@Stateless()
public class SendPOHQ {
    @EJB
    private SendPOLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "sendPO")
    public List<ProductionOrder> sendPO(@WebParam(name = "factoryName") String factoryName, @WebParam(name = "year") String year, @WebParam(name = "month") String month, @WebParam(name = "productName") String productName) {
        return ejbRef.sendPO(factoryName, year, month, productName);
    }
    
}
