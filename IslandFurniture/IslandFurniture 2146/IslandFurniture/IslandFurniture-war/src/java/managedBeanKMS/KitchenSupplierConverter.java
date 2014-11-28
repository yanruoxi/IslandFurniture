/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeanKMS;

import entity.kms.KitchenSupplier;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import session.stateless.kms.KitchenSupplierSessionBean;

/**
 *
 * @author ZiGui
 */
@FacesConverter(value = "kitchenSupplierConverter")
public class KitchenSupplierConverter implements Converter {

    @EJB
    private KitchenSupplierSessionBean kitchenSupplierSessionBean;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        return kitchenSupplierSessionBean.getKitchenSupplier(new Long(value));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {

        if (object == null) {
            return "";
        }
        KitchenSupplier ks;
        ks = (KitchenSupplier) object;
        System.out.println("getAsString:" + String.valueOf(ks.getKsupplierId()));
        return String.valueOf(ks.getKsupplierId());
    }

}
