/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.MPS;
import entity.SalesPlan;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author li
 */
@Local
public interface MPSSessionBeanLocal {
    
     public SalesPlan viewSalesPlan(String time,String furnitureName);
    public List<MPS> generateMPS(String time,String furnitureName,String partName );
    public void updateMPS(int plannedOrder,long mpsID);
    public List<MPS> generateMRP(String time,String furnitureName,String partName);
    public List<String> getFurnitures();
    public List<String> getParts(String FurnitureName);
    public List<MPS> viewMRP(String time, String furnitureName, String partName);
    
}
