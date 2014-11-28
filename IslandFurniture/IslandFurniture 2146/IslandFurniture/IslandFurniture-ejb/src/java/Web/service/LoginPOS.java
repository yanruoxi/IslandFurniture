/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Web.service;

import entity.SystemUser;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import session.stateless.SystemUserSessionLocal;

/**
 *
 * @author Ruoxi
 */
@WebService(serviceName = "LoginPOS")
@Stateless()
public class LoginPOS {
    @EJB
    private SystemUserSessionLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "getSystemUser")
    public SystemUser getSystemUser(@WebParam(name = "userName") String userName) {
        return ejbRef.getSystemUser(userName);
    }

   

    @WebMethod(operationName = "LoginForPOS")
    public boolean LoginForPOS(@WebParam(name = "userName") String userName, @WebParam(name = "password") String password) {
        return ejbRef.LoginForPOS(userName, password);
    }
    
}
