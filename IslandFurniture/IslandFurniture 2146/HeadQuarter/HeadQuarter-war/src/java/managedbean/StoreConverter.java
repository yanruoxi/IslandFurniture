/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import entity.Store;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import session.stateless.StoreSessionLocal;

/**
 *
 * @author Meiling
 */

//@ManagedBean(name = "supplierConverterBean") 
@FacesConverter(value = "storeConverter")
public class StoreConverter implements Converter {
   
    
   @EJB
    StoreSessionLocal storeSessionLocal;
    
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {       
        return storeSessionLocal.getStore(new Long(value));
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object == null) {
            return "";
        }
        Store store;
        store=(Store) object;
        return String.valueOf(store.getId());
    }  
    

}         