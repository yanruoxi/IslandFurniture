/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Factory;
import entity.Product;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ruoxi
 */
@Local
public interface FactorySessionLocal {

public void createFactory(String factoryName, String country, String address, String postal, String phone, String email);

    public Factory getFactoryByName(String factoryName);

    public Factory getFactoryById(Long Id);

    public List<Factory> getFactoryByLocation(String location);

    public Factory getFactoryByNameAndLocation(String location, String factoryName);

    public List<Factory> getAllFactory();

    public void deleteFactory(String factoryName);

    public void resetFactoryName(String factoryName, String newFactoryName);

    public void resetFactoryLocation(String factoryName, String location);

    public void resetFactoryStatus(String factoryName, String status);

    public boolean checkAvailability(String factoryName);
    
    public void editSelectedFactory(Factory factory) ;
    
      public Factory getFactory(Long selectedStoreId);
      
      public List<Factory> getFactoryForSelectedProduct(Product selectedProduct) ;

}
