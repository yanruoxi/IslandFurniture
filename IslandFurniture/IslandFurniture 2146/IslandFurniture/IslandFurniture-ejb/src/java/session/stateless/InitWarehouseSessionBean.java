/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Company;
import entity.Warehouse;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ZiGui
 */
@Stateless
public class InitWarehouseSessionBean implements InitWarehouseSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
    private String warehouseName;
    private String location;

    @Override
    public void initializeWarehouse(String warehouseName, String location) {
        Warehouse warehouse = new Warehouse();
        warehouse.setDeletema("N");
        warehouse.setAddress(location);
        System.out.println("Initialize warehouse success");

        em.persist(warehouse);
        em.flush();
    }

    @Override
    public List<Warehouse> getWarehouse() {
        Query query = em.createQuery("SELECT w FROM Warehouse w");
        return query.getResultList();
    }

    @Override
    public Warehouse getWarehouseSingleResult() {
        Query query = em.createQuery("SELECT w FROM Warehouse w WHERE w.deletema = 'N'");
        return (Warehouse) query.getSingleResult();
    }

    @Override
    public void updateWarehouse(Warehouse warehouse, String isDeleted) {
        Query query = em.createQuery("SELECT w FROM Warehouse w WHERE w.deletema = 'N'");
        Warehouse wh = (Warehouse) query.getSingleResult();
        wh.setDeletema(isDeleted);

        em.persist(wh);
        em.flush();

    }
    
    
}
