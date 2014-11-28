/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.ws;

import entity.Customer;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import session.stateless.CustomerPOSSessionLocal;

/**
 *
 * @author Ruoxi
 */
@WebService(serviceName = "CustomerPOS")
@Stateless()
public class CustomerPOS {
    @EJB
    private CustomerPOSSessionLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "getCustomerForPOS")
    public Customer getCustomerForPOS(@WebParam(name = "phone") String phone) {
        return ejbRef.getCustomerForPOS(phone);
    }
    
}
