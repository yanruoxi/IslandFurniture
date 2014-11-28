/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Web.service;

import entity.Company;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import session.stateless.CompanySessionLocal;

/**
 *
 * @author Ruoxi
 */
@WebService(serviceName = "GetStoreInfoPOS")
@Stateless()
public class GetStoreInfoPOS {
    @EJB
    private CompanySessionLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

  

    @WebMethod(operationName = "getCompanySingleResult")
    public Company getCompanySingleResult() {
        return ejbRef.getCompanySingleResult();
    }
    
}
