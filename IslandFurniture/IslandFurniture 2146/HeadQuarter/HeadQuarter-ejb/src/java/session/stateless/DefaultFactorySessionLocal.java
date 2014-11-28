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
import javax.ejb.Local;

/**
 *
 * @author Ruoxi
 */
@Local
public interface DefaultFactorySessionLocal {

    public DefaultFactory getFirstDefaultFactory(Store selectedStore, Product selectedProduct);

    public DefaultFactory creatFirstDefault(Store selectedStore, Product selectedProduct);

    public DefaultFactory getSecondDefaultFactory(Store selectedStore, Product selectedProduct);

    public DefaultFactory creatSecondDefault(Store selectedStore, Product selectedProduct);

    public Boolean compareFirstSecondDefault(DefaultFactory second, Factory selectedFactory);
    
    public DefaultFactory setFirstFactory(DefaultFactory first, Factory selectedFactory);

}
