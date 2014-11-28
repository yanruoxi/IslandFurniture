/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBeanKMS;

import entity.kms.Dish;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import session.stateless.kms.DishSessionBeanLocal;

/**
 *
 * @author ZiGui
 */
@FacesConverter(value="dishConverter")
public class DishConverter implements Converter {
    
    @EJB
    private DishSessionBeanLocal dishSessionBean;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        return dishSessionBean.getDishWithId(new Long(value));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {

        if (object == null) {
            return "";
        }
        Dish ks;
        ks = (Dish) object;
        System.out.println("getAsString:" + String.valueOf(ks.getDishId()));
        return String.valueOf(ks.getDishId());
    }

}