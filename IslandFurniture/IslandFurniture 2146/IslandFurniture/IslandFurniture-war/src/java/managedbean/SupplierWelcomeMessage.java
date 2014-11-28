/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.SupplierAcct;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author Meiling
 */
@Named(value = "supplierWelcomeMessage")
@SessionScoped
public class SupplierWelcomeMessage implements Serializable {

    @Inject
    private SupplierAcctManagedBean supplierAcctManagedBean;
    
    // Variables
    private SupplierAcct currentSupplierAcct;
    
    @PostConstruct
    public void init(){
        currentSupplierAcct = supplierAcctManagedBean.getCurrentSupplierAcct();
    }

    // GETTERS AND SETTERS
    public SupplierAcct getCurrentSupplierAcct() {
        return currentSupplierAcct;
    }

    public void setCurrentSupplierAcct(SupplierAcct currentSupplierAcct) {
        this.currentSupplierAcct = currentSupplierAcct;
    }
    // END OF GETTERS AND SETTERS
}
