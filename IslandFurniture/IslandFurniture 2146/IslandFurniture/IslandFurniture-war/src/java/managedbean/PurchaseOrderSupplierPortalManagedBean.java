/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import entity.PurchaseOrder;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import session.stateless.PurchaseOrderSessionBeanLocal;

/**
 *
 * @author Meiling
 */
@Named(value = "poSupplierPortalManagedBean")
@SessionScoped
public class PurchaseOrderSupplierPortalManagedBean implements Serializable {
    @EJB
    private PurchaseOrderSessionBeanLocal purchaseOrderSessionBean;
    
    @Inject
    private SupplierAcctManagedBean supplierAcctManagedBean;
    
    // Supplier Portal
    private List<PurchaseOrder> supplierPurchaseOrders; // For View All Purchase Orders (Supplier)
    private List<PurchaseOrder> filteredSupplierPurchaseOrders; // For filtering in View All Purchase Orders (Supplier)
    private PurchaseOrder selectedSupplierPurchaseOrder;


    /**
     * Creates a new instance of PurchaseOrderSupplierPortalManagedBean
     */
    public PurchaseOrderSupplierPortalManagedBean() {
    }
    
    @PostConstruct
    public void init() {
        System.err.println("init()");
        System.out.println("supplier:" + supplierAcctManagedBean.getSupplierAcct().getSupplier().getSupplierName());
        supplierPurchaseOrders = purchaseOrderSessionBean.getAllPurchaseOrdersForSupplier(supplierAcctManagedBean.getSupplierAcct().getSupplier());
    }
    
    // GETTERS AND SETTERS
    public List<PurchaseOrder> getSupplierPurchaseOrders() {
        return supplierPurchaseOrders;
    }

    public void setSupplierPurchaseOrders(List<PurchaseOrder> supplierPurchaseOrders) {
        this.supplierPurchaseOrders = supplierPurchaseOrders;
    }

    public List<PurchaseOrder> getFilteredSupplierPurchaseOrders() {
        return filteredSupplierPurchaseOrders;
    }

    public void setFilteredSupplierPurchaseOrders(List<PurchaseOrder> filteredSupplierPurchaseOrders) {
        this.filteredSupplierPurchaseOrders = filteredSupplierPurchaseOrders;
    }

    public PurchaseOrder getSelectedSupplierPurchaseOrder() {
        return selectedSupplierPurchaseOrder;
    }

    public void setSelectedSupplierPurchaseOrder(PurchaseOrder selectedSupplierPurchaseOrder) {
        this.selectedSupplierPurchaseOrder = selectedSupplierPurchaseOrder;
    }
    // END OF GETTERS AND SETTERS
    
    
    
}
