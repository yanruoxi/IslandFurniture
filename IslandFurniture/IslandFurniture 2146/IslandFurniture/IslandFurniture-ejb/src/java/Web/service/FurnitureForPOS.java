/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Web.service;

import entity.Furniture;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import session.stateless.FurnitureSessionLocal;

/**
 *
 * @author Ruoxi
 */
@WebService(serviceName = "FurnitureForPOS")
@Stateless()
public class FurnitureForPOS {
    @EJB
    private FurnitureSessionLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")



    @WebMethod(operationName = "getFurnitureForPOS")
    public Furniture getFurnitureForPOS(@WebParam(name = "furnitureName") String furnitureName) {
        return ejbRef.getFurnitureForPOS(furnitureName);
    }

   
    
}
