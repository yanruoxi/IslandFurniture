/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import entity.Product;
import entity.Store;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import session.stateless.ProductSessionLocal;

/**
 *
 * @author Meiling
 */

//@ManagedBean(name = "supplierConverterBean") 
@FacesConverter(value = "productConverter")
public class ProductConverter implements Converter {
   
    
   @EJB
    ProductSessionLocal productSessionLocal;
    
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) { 
        return productSessionLocal.getProduct(new Long(value));
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object == null) {
            return "";
        }
        Product product;
        product =(Product) object;
        return String.valueOf(product.getId());
    }  
    

}         