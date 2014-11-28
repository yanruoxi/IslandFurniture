/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.PurchaseRequisition;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Meiling
 */
@Local
public interface PurchaseReqSessionBeanLocal {

    public void addPurchaseReq(PurchaseRequisition pr);

    public List<PurchaseRequisition> getAllPurchaseReq();
    
    public void deletePurchaseReq(PurchaseRequisition selectedPurchaseReq);
    
    public boolean editPurchaseReq(PurchaseRequisition selectedPurchaseReq);
    
}
