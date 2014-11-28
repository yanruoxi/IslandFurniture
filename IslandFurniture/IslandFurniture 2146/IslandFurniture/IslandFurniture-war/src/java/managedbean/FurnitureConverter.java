/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import entity.Furniture;
import entity.Supplier;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import session.stateless.FurnitureSessionLocal;
import session.stateless.SupplierSessionBean;
/**
 *
 * @author Meiling
 */

//@ManagedBean(name = "supplierConverterBean") 
@FacesConverter(value = "furnitureConverter")
public class FurnitureConverter implements Converter {
    @EJB
    private SupplierSessionBean supplierSessionBean;
    
    @EJB
    private FurnitureSessionLocal furnitureSession;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        return furnitureSession.getFurniture(new Long(value));
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object == null) {
            return "";
        }
        Furniture furniture;
        furniture=(Furniture) object;        
       
        return String.valueOf(furniture.getId());
    }  
    

}         