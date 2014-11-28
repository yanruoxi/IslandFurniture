/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import entity.Supplier;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import session.stateless.SupplierSessionBean;
/**
 *
 * @author Meiling
 */

//@ManagedBean(name = "supplierConverterBean") 
@FacesConverter(value = "supplierConverter")
public class SupplierConverter implements Converter {
    @EJB
    private SupplierSessionBean supplierSessionBean;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        return supplierSessionBean.getSupplier(new Long(value));
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object == null) {
            return "";
        }
        Supplier sSupplier;
        sSupplier = (Supplier) object;
        System.out.println("getAsString:" + String.valueOf(sSupplier.getSupplierId()));
        return String.valueOf(sSupplier.getSupplierId());
    }  
    

}         