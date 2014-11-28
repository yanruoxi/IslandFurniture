/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.ProductionOrder;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ruoxi
 */
@Local
public interface SendPOLocal {
    
     public List<ProductionOrder> sendPO(String factoryName, String year, String month, String productName);
    
}
