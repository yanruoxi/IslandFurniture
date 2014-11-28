/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.DefaultFactory;
import entity.Factory;
import entity.Product;
import entity.Store;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ruoxi
 */
@Stateless
public class DefaultFactorySession implements DefaultFactorySessionLocal {

    @PersistenceContext(unitName = "HeadQuarter-ejbPU")
    private EntityManager entityManager;

    //retrieve the first default factory
    @Override
    public DefaultFactory getFirstDefaultFactory(Store selectedStore, Product selectedProduct) {
        // Search for existing pair of Part and Supplier based on SupplierId
        Query q = entityManager.createQuery("SELECT p FROM DefaultFactory p WHERE p.store.id=:selectedStoreId AND p.product.id=:selectedProductId AND p.rank='first' ");

        q.setParameter("selectedStoreId", selectedStore.getId());
        q.setParameter("selectedProductId", selectedProduct.getId());
        DefaultFactory s = null;
        try {
            s = (DefaultFactory) q.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }

        return s;
    }

    //create first default Factory
    @Override
    public DefaultFactory creatFirstDefault(Store selectedStore, Product selectedProduct) {
        DefaultFactory first = new DefaultFactory();

        first.setProduct(selectedProduct);
        first.setStore(selectedStore);
        first.setRank("first");

        selectedStore.getDefaultFactoryList().add(first);
        selectedProduct.getDefaultFactoryList().add(first);

        entityManager.persist(first);
        entityManager.merge(selectedProduct);
        entityManager.merge(selectedStore);

        return first;
    }

    //retrieve the first default factory
    @Override
    public DefaultFactory getSecondDefaultFactory(Store selectedStore, Product selectedProduct) {
        // Search for existing pair of Part and Supplier based on SupplierId
        Query q = entityManager.createQuery("SELECT p FROM DefaultFactory p WHERE p.store.id=:selectedStoreId AND p.product.id=:selectedProductId AND p.rank='second' ");

        q.setParameter("selectedStoreId", selectedStore.getId());
        q.setParameter("selectedProductId", selectedProduct.getId());
        DefaultFactory s = null;
        try {
            s = (DefaultFactory) q.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }
        return s;
    }

    //create second default Factory
    @Override
    public DefaultFactory creatSecondDefault(Store selectedStore, Product selectedProduct) {
        DefaultFactory first = new DefaultFactory();

        first.setProduct(selectedProduct);
        first.setStore(selectedStore);
        first.setRank("second");

        selectedStore.getDefaultFactoryList().add(first);
        selectedProduct.getDefaultFactoryList().add(first);

        entityManager.persist(first);
        entityManager.merge(selectedProduct);
        entityManager.merge(selectedStore);

        return first;
    }

    //compare to ensure that no same factory set for first and second default
    @Override
    public Boolean compareFirstSecondDefault(DefaultFactory second, Factory selectedFactory) {

        if (second.getFactory() != null) {

            if (second.getFactory().equals(selectedFactory)) {
                return true;
            }
        }

        return false;

    }

    //set the new first default factory
    @Override
    public DefaultFactory setFirstFactory(DefaultFactory first, Factory selectedFactory) {
        first.setFactory(selectedFactory);
        selectedFactory.getDefaultFactoryList().add(first);

        entityManager.merge(first);
        entityManager.merge(selectedFactory);
        return first;

    }
    
    

}
