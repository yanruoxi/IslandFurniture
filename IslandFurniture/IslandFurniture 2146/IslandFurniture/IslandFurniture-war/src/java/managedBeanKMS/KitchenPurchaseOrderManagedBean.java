/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeanKMS;

import datamodel.SupplierWithIngredient;
import entity.kms.Ingredient;
import entity.kms.KitchenPurchaseOrder;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import manager.KitchenSupplierEmailManager;
import session.stateless.kms.IngredientSessionBeanLocal;
import session.stateless.kms.KitchenPurchaseOrderSessionBeanLocal;
import session.stateless.kms.KitchenSupplierSessionBeanLocal;

/**
 *
 * @author Meiling
 */
@Named(value = "kpoManagedBean")
@SessionScoped
public class KitchenPurchaseOrderManagedBean implements Serializable {

    @EJB
    private KitchenSupplierSessionBeanLocal kitchenSupplierSessionBean;
    @EJB
    private IngredientSessionBeanLocal ingredientSessionBean;
    @EJB
    private KitchenPurchaseOrderSessionBeanLocal kitchenPurchaseOrderSessionBean;

    /**
     * Creates a new instance of KitchenPurchaseOrderManagedBean
     */
    public KitchenPurchaseOrderManagedBean() {
    }

    // Variables
    private String selectedFilterForSupplier; // Selected Filter for Supplier
    private List<String> statusList = new ArrayList<String>(); // Status to Display
    //
    private List<KitchenPurchaseOrder> purchaseOrders; // For View All Kitchen Purchase Orders
    //
    private List<KitchenPurchaseOrder> filteredPurchaseOrders; // For filtering in View All Kitchen Purchase Orders
    //
    private KitchenPurchaseOrder selectedKitchenPurchaseOrder;
    //
    private Ingredient selectedIngredientForKPO;

    private int quantityForKPO;

    private String selectedIngredientNameForKPO;

    private double unitPriceForKPO;

    private double defaultKitchenSupplierTotalPrice;

    private String deliveryStatus;

    private Date scheduledDeliveryDate;

    private UIComponent quantityForKPOInput;

    private UIComponent quantityForKPOInputForNonDefaultSup;

    private UIComponent getUnitPriceNonDefaultSup;

    public UIComponent getGetUnitPriceNonDefaultSup() {
        return getUnitPriceNonDefaultSup;
    }

    public void setGetUnitPriceNonDefaultSup(UIComponent getUnitPriceNonDefaultSup) {
        this.getUnitPriceNonDefaultSup = getUnitPriceNonDefaultSup;
    }

    public UIComponent getQuantityForKPOInputForNonDefaultSup() {
        return quantityForKPOInputForNonDefaultSup;
    }

    public void setQuantityForKPOInputForNonDefaultSup(UIComponent quantityForKPOInputForNonDefaultSup) {
        this.quantityForKPOInputForNonDefaultSup = quantityForKPOInputForNonDefaultSup;
    }

    private Double totalPrice = 0.0; // Calculated Total Price from input

    private Date bestBefore;

    private double totalPriceForNonDefaultSup;

    private KitchenPurchaseOrder kpoToEdit;
    // Rank by No Filter (Retrieve all Suppliers providing for Part)
    private List<SupplierWithIngredient> supplierWithIngredientList = new ArrayList<SupplierWithIngredient>();
    private SupplierWithIngredient selectedsupplierWithIngredient = new SupplierWithIngredient();
//
//    // Get ALL Selected Suppliers across DIFFERENT ranking criteria
//    private HashSet<Supplier> allSelectedSupplierSet = new HashSet<Supplier>();
//    private ArrayList<Supplier> allSelectedSupplierList = new ArrayList<Supplier>();
//
//    // GET ALL PAST PO from Selected Supplier
//    private List<PurchaseOrder> evaluateSupplierList = new ArrayList<PurchaseOrder>();
//    private List<PurchaseOrder> selectedEvaluateSupplierList = new ArrayList<PurchaseOrder>();
//    private ArrayList<String> evalSupNameList = new ArrayList<String>(); // For displaying Suppliers in drop down list
//    private ArrayList<String> selectedEvalSupNameList = new ArrayList<String>(); // For selected Suppliers in drop down list

    @PostConstruct
    public void init() {
        System.err.println("init()");
        purchaseOrders = kitchenPurchaseOrderSessionBean.getAllKitchenPurchaseOrders(); // Retrieve list of Purchase Orders from session bean (Pending, Approved, Rejected)
        statusList.add("Draft");
        statusList.add("Sent");
        statusList.add("Received");
        statusList.add("Issued");
        statusList.add("False Received");

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        if (paramMap.containsKey("kpo")) {
            String IdString = paramMap.get("kpo");
            kpoToEdit = kitchenPurchaseOrderSessionBean.getKPOwithId(Long.parseLong(IdString));
        }

//        invPurchaseOrders = purchaseOrderSessionBean.getAllPurchaseOrdersForInvStaff();
//        invStatusList.add("Approved");
//        invStatusList.add("Issued");
//        invStatusList.add("Received");
//        invStatusList.add("Falsed Issued");
//
//        imPurchaseOrders = purchaseOrderSessionBean.getAllPurchaseOrdersForImStaff();
//        imStatusList.add("Issued");
//        imStatusList.add("Falsed Issued");
    }

    // GETTERS AND SETTERS
    public double getTotalPriceForNonDefaultSup() {
        return totalPriceForNonDefaultSup;
    }

    public void setTotalPriceForNonDefaultSup(double totalPriceForNonDefaultSup) {
        this.totalPriceForNonDefaultSup = totalPriceForNonDefaultSup;
    }

    public String getSelectedIngredientNameForKPO() {
        return selectedIngredientNameForKPO;
    }

    public void setSelectedIngredientNameForKPO(String selectedIngredientNameForKPO) {
        this.selectedIngredientNameForKPO = selectedIngredientNameForKPO;
    }

    public String getSelectedFilterForSupplier() {
        return selectedFilterForSupplier;
    }

    public void setSelectedFilterForSupplier(String selectedFilterForSupplier) {
        this.selectedFilterForSupplier = selectedFilterForSupplier;
    }

    public double getDefaultKitchenSupplierTotalPrice() {
        return defaultKitchenSupplierTotalPrice;
    }

    public void setDefaultKitchenSupplierTotalPrice(double defaultKitchenSupplierTotalPrice) {
        this.defaultKitchenSupplierTotalPrice = defaultKitchenSupplierTotalPrice;
    }

    public Date getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(Date bestBefore) {
        this.bestBefore = bestBefore;
    }

    public double getUnitPriceForKPO() {
        return unitPriceForKPO;
    }

    public void setUnitPriceForKPO(double unitPriceForKPO) {
        this.unitPriceForKPO = unitPriceForKPO;
    }

    public List<KitchenPurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(List<KitchenPurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public List<KitchenPurchaseOrder> getFilteredPurchaseOrders() {
        return filteredPurchaseOrders;
    }

    public void setFilteredPurchaseOrders(List<KitchenPurchaseOrder> filteredPurchaseOrders) {
        this.filteredPurchaseOrders = filteredPurchaseOrders;
    }

    public KitchenPurchaseOrder getSelectedKitchenPurchaseOrder() {
        return selectedKitchenPurchaseOrder;
    }

    public void setSelectedKitchenPurchaseOrder(KitchenPurchaseOrder selectedKitchenPurchaseOrder) {
        this.selectedKitchenPurchaseOrder = selectedKitchenPurchaseOrder;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public Ingredient getSelectedIngredientForKPO() {
        return selectedIngredientForKPO;
    }

    public void setSelectedIngredientForKPO(Ingredient selectedIngredientForKPO) {
        this.selectedIngredientForKPO = selectedIngredientForKPO;
    }

    public int getQuantityForKPO() {
        return quantityForKPO;
    }

    public void setQuantityForKPO(int quantityForKPO) {
        this.quantityForKPO = quantityForKPO;
    }

    public UIComponent getQuantityForKPOInput() {
        return quantityForKPOInput;
    }

    public void setQuantityForKPOInput(UIComponent quantityForKPOInput) {
        this.quantityForKPOInput = quantityForKPOInput;
    }

    public KitchenPurchaseOrder getKpoToEdit() {
        return kpoToEdit;
    }

    public void setKpoToEdit(KitchenPurchaseOrder kpoToEdit) {
        this.kpoToEdit = kpoToEdit;
    }
    
    

//    public List<SupplierWithPart> getSupplierWithPartList() {
//        return supplierWithPartList;
//    }
//
//    public void setSupplierWithPartList(List<SupplierWithPart> supplierWithPartList) {
//        this.supplierWithPartList = supplierWithPartList;
//    }
//
//    public List<SupplierWithPart> getSelectedsupplierWithPartList() {
//        return selectedsupplierWithPartList;
//    }
//
//    public void setSelectedsupplierWithPartList(List<SupplierWithPart> selectedsupplierWithPartList) {
//        this.selectedsupplierWithPartList = selectedsupplierWithPartList;
//    }
//
//    public HashSet<Supplier> getAllSelectedSupplierSet() {
//        return allSelectedSupplierSet;
//    }
//
//    public void setAllSelectedSupplierSet(HashSet<Supplier> allSelectedSupplierSet) {
//        this.allSelectedSupplierSet = allSelectedSupplierSet;
//    }
//
//    public ArrayList<Supplier> getAllSelectedSupplierList() {
//        return allSelectedSupplierList;
//    }
//
//    public void setAllSelectedSupplierList(ArrayList<Supplier> allSelectedSupplierList) {
//        this.allSelectedSupplierList = allSelectedSupplierList;
//    }
//
//    public List<PurchaseOrder> getEvaluateSupplierList() {
//        return evaluateSupplierList;
//    }
//
//    public void setEvaluateSupplierList(List<PurchaseOrder> evaluateSupplierList) {
//        this.evaluateSupplierList = evaluateSupplierList;
//    }
//
//    public List<PurchaseOrder> getSelectedEvaluateSupplierList() {
//        return selectedEvaluateSupplierList;
//    }
//
//    public void setSelectedEvaluateSupplierList(List<PurchaseOrder> selectedEvaluateSupplierList) {
//        this.selectedEvaluateSupplierList = selectedEvaluateSupplierList;
//    }
//
//    public ArrayList<String> getEvalSupNameList() {
//        return evalSupNameList;
//    }
//
//    public void setEvalSupNameList(ArrayList<String> evalSupNameList) {
//        this.evalSupNameList = evalSupNameList;
//    }
//
//    public ArrayList<String> getSelectedEvalSupNameList() {
//        return selectedEvalSupNameList;
//    }
//
//    public void setSelectedEvalSupNameList(ArrayList<String> selectedEvalSupNameList) {
//        this.selectedEvalSupNameList = selectedEvalSupNameList;
//    }
    public Date getScheduledDeliveryDate() {
        return scheduledDeliveryDate;
    }

    public void setScheduledDeliveryDate(Date scheduledDeliveryDate) {
        this.scheduledDeliveryDate = scheduledDeliveryDate;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public List<SupplierWithIngredient> getSupplierWithIngredientList() {
        return supplierWithIngredientList;
    }

    public void setSupplierWithIngredientList(List<SupplierWithIngredient> supplierWithIngredientList) {
        this.supplierWithIngredientList = supplierWithIngredientList;
    }

    public SupplierWithIngredient getSelectedsupplierWithIngredient() {
        return selectedsupplierWithIngredient;
    }

    public void setSelectedsupplierWithIngredient(SupplierWithIngredient selectedsupplierWithIngredient) {
        this.selectedsupplierWithIngredient = selectedsupplierWithIngredient;
    }

    // END OF GETTERS AND SETTERS
    public void redirectToCreateKPO() {
        try {
            System.out.println("redirectToCreateKPO");
            System.out.println("selectedIngredientNameForKPO:" + selectedIngredientNameForKPO);
            selectedIngredientForKPO = ingredientSessionBean.getIngredientByName(selectedIngredientNameForKPO);
            System.out.println("selectedIngredientForKPO:" + selectedIngredientForKPO.getIngredientName());
            if (selectedIngredientForKPO.isHasDefaultSupplier()) {
                System.out.println("HasDefaultSupplier");
                FacesContext.getCurrentInstance().getExternalContext().redirect("kmsCreateKPOForDefaultSupplier.xhtml?i=4");
            } else {
                getAllSuppliersForIngredient();
                if (supplierWithIngredientList.isEmpty()) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No suppliers found", "There are no Suppliers providing for the selected Ingredient");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                FacesContext.getCurrentInstance().getExternalContext().redirect("kmsSelectNonDefaultSupplier.xhtml?i=4");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    // Retrieve Retrive ALL Suppliers for NO Filter
    public void getAllSuppliersForIngredient() {
        supplierWithIngredientList = kitchenSupplierSessionBean.getSuppliersForIngredient(selectedIngredientForKPO);
    }

    // Get Calculated Total Price based on quotedUnitPriceInput
    public void getCalculatedTotalPrice() {
        System.out.println("getCalculatedTotalPrice");
        if (quantityForKPO != 0) {
            totalPrice = selectedIngredientForKPO.getDefaultSupplierUnitPrice() * quantityForKPO;
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(quantityForKPOInput.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Calculated Total Price: $" + df.format(totalPrice) + "", ""));
        totalPrice = 0.0;
    }

    public void getUnitPriceNonDefaultSup() {
        if (unitPriceForKPO != 0) {
            totalPriceForNonDefaultSup = unitPriceForKPO * quantityForKPO;
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(quantityForKPOInputForNonDefaultSup.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Calculated Total Price: $" + df.format(totalPriceForNonDefaultSup) + "", ""));
        totalPriceForNonDefaultSup = 0.0;
    }

    public void getCalculatedTotalPriceNonDefaultSup() {
        System.out.println("getCalculatedTotalPrice");
        System.out.println("unitPriceForKPO:" + unitPriceForKPO);
        System.out.println("quantityForKPO:" + quantityForKPO);
        if (quantityForKPO != 0) {
            totalPriceForNonDefaultSup = unitPriceForKPO * quantityForKPO;
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(quantityForKPOInputForNonDefaultSup.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Calculated Total Price: $" + df.format(totalPriceForNonDefaultSup) + "", ""));
        totalPriceForNonDefaultSup = 0.0;
    }

//    public void getCalculatedTotalPriceEdit() {
//        System.out.println("selectedQuotation getQuotedUnitPrice:" + selectedQuotation.getQuotedUnitPrice());
//        totalPrice = selectedQuotation.getQuotedUnitPrice() * selectedQuotation.getQty();
//        DecimalFormat df = new DecimalFormat("#0.00");
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(quotedUnitPriceInput.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Total Quoted Price: $" + df.format(totalPrice) + "", ""));
//        totalPrice = 0.0;
//    }
    // Create Purchase Order For Default Supplier
    public void createPOForDefaultSup() {
        Double totalPrice = quantityForKPO * selectedIngredientForKPO.getDefaultSupplierUnitPrice();
        if (!scheduledDeliveryDate.after(new Date())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Scheduled Delivery Date is before current date");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        if (quantityForKPO == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Quantity must not be 0");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        kitchenPurchaseOrderSessionBean.createPOForDefaultSup(selectedIngredientForKPO, totalPrice, scheduledDeliveryDate, quantityForKPO);
        purchaseOrders.clear();
        purchaseOrders = kitchenPurchaseOrderSessionBean.getAllKitchenPurchaseOrders();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully created Purchase Order", "Purchase Order will be saved as draft before sending to Supplier.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        quantityForKPO = 0;
        scheduledDeliveryDate = null;

    }

    public void redirectTocreatePOToNonDefaultSup() {
        try {
            if (selectedsupplierWithIngredient == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed", "Please select a Supplier for the Ingredient");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            System.out.println("selectedsupplierWithIngredient:" + selectedsupplierWithIngredient);
            FacesContext.getCurrentInstance().getExternalContext().redirect("kmsCreateKPOForNonDefaultSupplier.xhtml?i=4");
        } catch (IOException ex) {
            System.out.println("caught io exception");
        }
    }

    // Create Purchase Order For Non Default Supplier
    public void createPOForNonDefaultSup() {
        Double totalPriceForNonDefaultSup = quantityForKPO * unitPriceForKPO;
        if (!scheduledDeliveryDate.after(new Date())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Scheduled Delivery Date is before current date");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        if (unitPriceForKPO == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Unit Price must not be 0");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        if (quantityForKPO == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Quantity must not be 0");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }

        kitchenPurchaseOrderSessionBean.createPOForNonDefaultSup(selectedIngredientForKPO, unitPriceForKPO, totalPriceForNonDefaultSup, scheduledDeliveryDate, quantityForKPO, selectedsupplierWithIngredient.getKitchenSupplier());
        purchaseOrders.clear();
        purchaseOrders = kitchenPurchaseOrderSessionBean.getAllKitchenPurchaseOrders();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully created Purchase Order", "Purchase Order will be saved as draft before sending to Supplier.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // Send Purchase Order to Kitchen Supplier
    public void sendPO() {
        if (selectedKitchenPurchaseOrder.getKpurchaseOrderStatus().equals("Draft")) {
            String supEmail = selectedKitchenPurchaseOrder.getKitchenSupplier().getKsupplierName() + "<" + selectedKitchenPurchaseOrder.getKitchenSupplier().getKsupplierEmailAddress() + ">";
            kitchenPurchaseOrderSessionBean.updatePOStatusToSent(selectedKitchenPurchaseOrder);

            KitchenSupplierEmailManager kitchenSupEmailManager = new KitchenSupplierEmailManager();
            kitchenSupEmailManager.setToEmailAddress(supEmail);
            kitchenSupEmailManager.emailSupKPO(selectedKitchenPurchaseOrder);

            purchaseOrders.clear();
            purchaseOrders = kitchenPurchaseOrderSessionBean.getAllKitchenPurchaseOrders();
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmation Email Sent", "Confirmation Email of Purchase Order sent to Supplier");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Purchase Order cannot be sent as it is not in Draft status.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

//    // Update Purchase Order Status
//    public void updatePOStatus() {
//        System.out.println("selectedPurchaseOrder status:" + selectedPurchaseOrder.getPurchaseOrderStatus());
//        if (selectedPurchaseOrder.getPurchaseOrderStatus().isEmpty()) {
//            selectedPurchaseOrder.setPurchaseOrderStatus(purchaseOrderSessionBean.getPODB(selectedPurchaseOrder).getPurchaseOrderStatus());
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Please select a Purchase Order Status for updating");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//            return;
//        }
//        purchaseOrderSessionBean.updatePOStatus(selectedPurchaseOrder);
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Purchase Order Status has been successfully updated");
//        FacesContext.getCurrentInstance().addMessage(null, message);
//    }
//    // Edit Purchase Order
//    public void redirectToEditPO() {
//        System.out.println("redirectToEditPO:" + selectedPurchaseOrder.getPurchaseOrderStatus());
//        if (selectedPurchaseOrder.getPurchaseOrderStatus().equals("Pending Approval")) {
//            try {
//                FacesContext.getCurrentInstance().getExternalContext().redirect("scmProStaffEditPO.xhtml");
//            } catch (IOException ex) {
//                Logger.getLogger(QuotationDetailManagedBean.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } // Check if Quotation has already been submitted 
//        else if (selectedPurchaseOrder.getPurchaseOrderStatus().equals("Approved")) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed", "Purchase Order has already been approved.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        } else if (selectedPurchaseOrder.getPurchaseOrderStatus().equals("Rejected")) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed", "Purchase Order has already been rejected.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        } else {
//            System.out.println("selectedPurchaseOrder status:" + selectedPurchaseOrder.getPurchaseOrderStatus());
//        }
//    }
    // Redirect to redirectToEditPO.xhtml
    public void redirectToEditPO() throws IOException {
        System.out.println("KitchenPurchaseOrderManagedBean: redirectToEditPO()");
        System.out.println(selectedKitchenPurchaseOrder.getKpurchaseOrderId());
        if (selectedKitchenPurchaseOrder.getKpurchaseOrderStatus().equals("Draft")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("kmsEditPO.xhtml?i=4&kpo=" + selectedKitchenPurchaseOrder.getKpurchaseOrderId().toString());
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "ONly Draft Purchase Order can be edited.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

// Edit Purchase Order
    public void editPO() {
        System.out.println("editPO");
        // Check that Scheduled Delivery Date is not before or equals Current Date
        if ((!selectedKitchenPurchaseOrder.getKscheduledDeliveryDate().after(new Date()))) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Scheduled Delivery Date must be after Current Date");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        if (quantityForKPO < 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Quantity must be more than 0");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }

        kitchenPurchaseOrderSessionBean.editPO(selectedKitchenPurchaseOrder, selectedKitchenPurchaseOrder.getKscheduledDeliveryDate(), quantityForKPO);
        purchaseOrders.clear();
        purchaseOrders = kitchenPurchaseOrderSessionBean.getAllKitchenPurchaseOrders();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successfully updated Purchase Order.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // Delete Purchase Order
    public void deletePO() {
        System.out.println("KitchenPurchaseOrderManagedBean: deletePO()");
        if (selectedKitchenPurchaseOrder.getKpurchaseOrderStatus().equals("Draft")) {
            kitchenPurchaseOrderSessionBean.deletePO(selectedKitchenPurchaseOrder);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Purchase Order has been deleted.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            purchaseOrders.clear();
            purchaseOrders = kitchenPurchaseOrderSessionBean.getAllKitchenPurchaseOrders();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Only Draft Purchase Order can be deleted");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

}
