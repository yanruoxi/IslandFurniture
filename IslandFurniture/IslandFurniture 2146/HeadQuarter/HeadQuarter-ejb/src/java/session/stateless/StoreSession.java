/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Factory;
import entity.Store;
import java.util.List;
import javax.ejb.EJB;
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
public class StoreSession implements StoreSessionLocal {

    @PersistenceContext(unitName = "HeadQuarter-ejbPU")
    private EntityManager entityManager;
    @EJB
    private FactorySessionLocal factorySessionLocal;

    @Override
    public void createStore(String storeName, String country, String address, String postal, String phone, String email) {
        Store store = new Store();
               
        store.setStoreName(storeName);
        store.setCountry(country);
        store.setAddress(address);
        store.setPostal(postal);
        store.setPhone(phone);
        store.setEmail(email);
        store.setIsDeleted("no");
        entityManager.persist(store);
        entityManager.flush();
    }

    @Override
    public Store getStore(String storeName) {
        Query query = entityManager.createQuery("SELECT u FROM Store u WHERE u.storeName = :inStoreName AND u.isDeleted= :inIsDeleted");
        query.setParameter("inStoreName", storeName);
        query.setParameter("inIsDeleted", "no");
        Store store = null;
        try {
            store = (Store) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }
        return store;
    }

    @Override
    public List<Store> getAllStore() {
        Query query = entityManager.createQuery("SELECT e FROM Store e WHERE e.isDeleted=:inIsDeleted");
        query.setParameter("inIsDeleted", "no");
        return query.getResultList();
    }

    @Override
    public void deleteStore(String storeName) {        
        Store store=getStore(storeName);
        store.setIsDeleted("yes");
        entityManager.merge(store);
    }

    @Override
    public void resetStoreName(String storeName, String newStoreName) {
        Store store=getStore(storeName);
        store.setStoreName(newStoreName);
        entityManager.merge(store);
        entityManager.flush();
    }
    
    @Override
    public void resetStoreLocation(String storeName, String location) {
        Store store=getStore(storeName);
  //      store.setLocation(location);
        entityManager.merge(store);
        entityManager.flush();
    }
//    
//    @Override
//    public void setFirstDefaultFactory(String storeName, String factoryName) {
//        Store store=getStore(storeName);
//        Factory factory=factorySessionLocal.getFactoryByName(factoryName);
//        store.setFirstDefault(factory);     
//        entityManager.merge(store);
//        entityManager.flush();
//    }
//    
//    @Override
//    public void setSecondDefaultFactory(String storeName, String factoryName) {
//        Store store=getStore(storeName);
//        Factory factory=factorySessionLocal.getFactoryByName(factoryName);
//        store.setSecondDefault(factory);
//        entityManager.merge(store);
//        entityManager.flush();
//    }
    
      @Override
    public void editSelectedStore(Store  store) {
        
        Store s=getStore(store.getStoreName());
        s.setCountry(store.getCountry());
        s.setAddress(store.getAddress());
        s.setPostal(store.getPostal());
        s.setPhone(store.getPhone());
        s.setEmail(store.getEmail());     

        entityManager.merge(s);

    }
    
    
    // Retrieve a Store based on storeID
    @Override
    public Store getStore(Long selectedStoreId) {
        Store gs = entityManager.find(Store.class, selectedStoreId);
        return gs;
    }
    
}
