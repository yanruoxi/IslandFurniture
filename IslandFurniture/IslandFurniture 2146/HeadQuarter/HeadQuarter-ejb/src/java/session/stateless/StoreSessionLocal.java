/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Store;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ruoxi
 */
@Local
public interface StoreSessionLocal {

    public void createStore(String storeName, String country, String address, String postal, String phone, String email);

    public Store getStore(String storeName);

    public List<Store> getAllStore();

    public void deleteStore(String storeName);

    public void resetStoreName(String storeName, String newStoreName);

    public void resetStoreLocation(String storeName, String location);
//
//    public void setFirstDefaultFactory(String storeName, String factoryName);
//
//    public void setSecondDefaultFactory(String storeName, String factoryName);
    
     public void editSelectedStore(Store  store);
     
     public Store getStore(Long selectedStoreId) ;

}
