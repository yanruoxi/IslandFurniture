/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Factory;
import entity.Product;
import java.util.List;
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
public class FactorySession implements FactorySessionLocal {

    @PersistenceContext(unitName = "HeadQuarter-ejbPU")
    private EntityManager entityManager;

    @Override
    public void createFactory(String factoryName, String country, String address, String postal, String phone, String email) {

        Factory factory = new Factory();
        factory.setFactoryName(factoryName);
        factory.setCountry(country);
        factory.setAddress(address);
        factory.setPostal(postal);
        factory.setPhone(phone);
        factory.setEmail(email);
        factory.setStatus("Available");
        factory.setIsDeleted("no");

        entityManager.persist(factory);
        entityManager.flush();

    }

    @Override
    public Factory getFactoryByName(String factoryName) {
        Query query = entityManager.createQuery("SELECT u FROM Factory u WHERE u.factoryName = :inFactoryName AND u.isDeleted= :inIsDeleted");
        query.setParameter("inFactoryName", factoryName);
        query.setParameter("inIsDeleted", "no");
        Factory factory = null;
        try {
            factory = (Factory) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }
        return factory;
    }

    @Override
    public Factory getFactoryById(Long Id) {
        Query query = entityManager.createQuery("SELECT u FROM Factory u WHERE u.id = :inId");
        query.setParameter("inId", Id);
        Factory factory = factory = (Factory) query.getSingleResult();

        return factory;
    }

    @Override
    public List<Factory> getFactoryByLocation(String location) {
        Query query = entityManager.createQuery("SELECT u FROM Factory u WHERE u.country LIKE :inLocation");
        location = "%" + location + "%";
        query.setParameter("inLocation", location);

        return query.getResultList();
    }

    @Override
    public Factory getFactoryByNameAndLocation(String location, String factoryName) {
        Query query = entityManager.createQuery("SELECT u FROM Factory u WHERE u.location = :inLocation AND u.factoryName = :inFactoryName");
        query.setParameter("inLocation", location);
        query.setParameter("inFactoryName", factoryName);
        Factory factory = null;
        try {
            factory = (Factory) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }
        return factory;
    }

    @Override
    public List<Factory> getAllFactory() {
        Query query = entityManager.createQuery("SELECT e FROM Factory e WHERE e.isDeleted= :inIsDeleted");
        query.setParameter("inIsDeleted", "no");
        return query.getResultList();
    }

    @Override
    public void deleteFactory(String factoryName) {
        Factory factory = getFactoryByName(factoryName);
        factory.setIsDeleted("yes");
        entityManager.merge(factory);
    }

    @Override
    public void resetFactoryName(String factoryName, String newFactoryName) {
        Factory factory = getFactoryByName(factoryName);
        factory.setFactoryName(newFactoryName);
        entityManager.merge(factory);
        entityManager.flush();
    }

    @Override
    public void resetFactoryLocation(String factoryName, String location) {
        Factory factory = getFactoryByName(factoryName);
        // factory.setLocation(location);
        entityManager.merge(factory);
        entityManager.flush();
    }

    @Override
    public void resetFactoryStatus(String factoryName, String status) {
        Factory factory = getFactoryByName(factoryName);
        factory.setStatus(status);
        entityManager.merge(factory);
        entityManager.flush();
    }

    @Override
    public boolean checkAvailability(String factoryName) {
        Factory factory = getFactoryByName(factoryName);
        if (factory.getStatus().equalsIgnoreCase("Available")) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void editSelectedFactory(Factory factory) {
        Factory editFactory = getFactoryByName(factory.getFactoryName());

        editFactory.setCountry(factory.getCountry());
        editFactory.setAddress(factory.getAddress());
        editFactory.setPostal(factory.getPostal());
        editFactory.setPhone(factory.getPhone());
        editFactory.setEmail(factory.getEmail());
        editFactory.setStatus(factory.getStatus());

        entityManager.merge(editFactory);

    }

    // Retrieve a Store based on storeID
    @Override
    public Factory getFactory(Long selectedStoreId) {
        Factory gs = entityManager.find(Factory.class, selectedStoreId);
        return gs;
    }

    //retrieve list of factory that produce certan product
    @Override
    public List<Factory> getFactoryForSelectedProduct(Product selectedProduct) {
        Query q = entityManager.createQuery("SELECT s FROM Factory s, Product p WHERE p.factories=s AND p.id = :selectedStoreId AND s.status='Available'");
        q.setParameter("selectedStoreId", selectedProduct.getId());
        List<Factory> factoryResultList = q.getResultList();
        return factoryResultList;
    }

}
