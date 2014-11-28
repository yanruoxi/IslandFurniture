/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.Furniture;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ruoxi
 */
@Local
public interface FurnitureSessionLocal {
    
  public Furniture getFurnitureForPOS(String furnitureName);
    
       public Furniture getFurniture(Long selectedSupplierId);


     public Furniture getFurnitureByName(String furnitureName);
     
     public List<Furniture> getAllFurniture() ;
     
      public void deleteFurniture(String furnitureName) ;
    
}
