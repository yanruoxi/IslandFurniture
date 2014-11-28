/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Factory;
import entity.Store;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import session.stateless.FactorySessionLocal;

/**
 *
 * @author Meiling
 */
//@ManagedBean(name = "supplierConverterBean") 
@FacesConverter(value = "factoryConverter")
public class FactoryConverter implements Converter {

    @EJB
    FactorySessionLocal factorySessionLocal;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        return factorySessionLocal.getFactory(new Long(value));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object == null) {
            return "";
        }
        Factory factory;

        factory = (Factory) object;
        return String.valueOf(factory.getId());
    }

}
