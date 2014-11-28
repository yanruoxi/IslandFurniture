/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Inventory;
import entity.Part;
import entity.Furniture;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ZiGui
 */
@Local
public interface InventorySessionBeanLocal {

//    public List<Inventory> getAllInventory();
    public Inventory getInventoryPart(String partName);

    public Inventory getInventoryFurniture(String furnitureName);

    public void updateInventoryPart(String partName, int quantity);

    public void updateInventoryFurniture(String furniture, int quantity);

    public List<Part> getPartInventory();

    public List<Furniture> getFurnitureInventory();


}
