/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.ProductionOrder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ruoxi
 */
@Stateless
public class SendPO implements SendPOLocal {

    @PersistenceContext(unitName = "HeadQuarter-ejbPU")
    private EntityManager entityManager;

    @Override
    public List<ProductionOrder> sendPO(String factoryName, String year, String month, String productName) {

        return testSendPO(factoryName, year, month, productName);

    }

    public List<ProductionOrder> testSendPO(String factoryName, String year, String month, String productName) {

        Query query = entityManager.createQuery("SELECT e FROM ProductionOrder e WHERE e.factory.factoryName= :inFactoryName AND e.year= :inYear AND e.month= :inMonth AND e.product.productName= :inProductName AND e.Status= :inStatus");
        query.setParameter("inFactoryName", factoryName);
        query.setParameter("inYear", year);
        query.setParameter("inMonth", month);
        query.setParameter("inProductName", productName);
        query.setParameter("inStatus", "no");

        List<ProductionOrder> poList = query.getResultList();

        System.out.println("pull from web service for PO" + poList.size());

        setStatusPO(poList);

        for (ProductionOrder po : poList) {
            System.out.println("starting detach entity" + po.getYear());

            entityManager.detach(po);
            po.setFactory(null);
            po.setProduct(null);
            po.setSalesForcast(null);
            po.setStore(null);

        }

        return poList;

    }

    public void setStatusPO(List<ProductionOrder> poList) {

     

    }

}
