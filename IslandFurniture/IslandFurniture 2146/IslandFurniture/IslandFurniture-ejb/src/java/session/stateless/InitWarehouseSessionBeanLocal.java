/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Warehouse;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ZiGui
 */
@Local
public interface InitWarehouseSessionBeanLocal {

    public void initializeWarehouse(String warehouseName, String location);

    public List<Warehouse> getWarehouse();

    public Warehouse getWarehouseSingleResult();

    public void updateWarehouse(Warehouse warehouse, String isDeleted);

}
