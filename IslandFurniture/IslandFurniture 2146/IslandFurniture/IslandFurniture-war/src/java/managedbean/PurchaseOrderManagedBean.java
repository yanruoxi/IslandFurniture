/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import datamodel.SupplierWithAvgQty;
import datamodel.SupplierWithMinPrice;
import datamodel.SupplierWithPart;
import datamodel.SupplierWithScore;
import entity.PurchaseOrder;
import entity.PurchaseRequisition;
import entity.QuotationDetail;
import entity.ShippingOrder;
import entity.Supplier;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import session.stateless.PurchaseOrderSessionBeanLocal;
import session.stateless.PurchaseReqSessionBeanLocal;
import session.stateless.QuotationDetailSessionBeanLocal;
import session.stateless.SupplierSessionBeanLocal;

/**
 *
 * @author Meiling
 */
@Named(value = "poManagedBean")
@SessionScoped
public class PurchaseOrderManagedBean implements Serializable {

    @EJB
    private QuotationDetailSessionBeanLocal quotationDetailSessionBean;
    @EJB
    private PurchaseReqSessionBeanLocal purchaseReqSessionBean;
    @EJB
    private SupplierSessionBeanLocal supplierSessionBean;
    @EJB
    private PurchaseOrderSessionBeanLocal purchaseOrderSessionBean;
    @Inject
    private PurchaseRequisitionManagedBean purchaseReqManagedBean;
    @Inject
    private QuotationDetailProStaffManagedBean quotnDetailProStaffManagedBean;
    @Inject
    private PurchaseOrderSupplierPortalManagedBean poSupplierPortalManagedBean;

    // Variables
    private String selectedFilterForSupplier; // Selected Filter for Supplier
    private List<String> statusList = new ArrayList<String>(); // Status to Display
    private List<PurchaseOrder> purchaseOrders; // For View All Purchase Orders (Procurement Manager)
    private List<PurchaseOrder> filteredPurchaseOrders; // For filtering in View All Purchase Orders
    private PurchaseOrder selectedPurchaseOrder;
    private ShippingOrder selectedShippingOrder;
    private String deliveryStatus;
    private List<String> invStatusList = new ArrayList<String>();   // Status to Display for Inv Staff
    private List<PurchaseOrder> invPurchaseOrders; // Purchase Orders for Inventory Staff to View
    private String invPOstatus;     // PO Status to be updated
    private List<String> imStatusList = new ArrayList<String>();   // Status to Display for Inv Staff
    private List<PurchaseOrder> imPurchaseOrders; // Purchase Orders for Inventory Staff to View

    private Date scheduledDeliveryDate; // For PO to Contract Supplier

    // Ranking Top 3/ Top 5 Suppliers
    private int topNum; // Number to display for Top 3/5

    // Rank by Best Price (Mininum Unit Price)
    private ArrayList<SupplierWithMinPrice> supplierWithMinPriceList = new ArrayList<SupplierWithMinPrice>();
    private List<SupplierWithMinPrice> selectedSupplierWithMinPriceList = new ArrayList<SupplierWithMinPrice>();

    // Rank by Highest Average Quantity
    private ArrayList<SupplierWithAvgQty> supplierWithAvgQtyList = new ArrayList<SupplierWithAvgQty>();
    private List<SupplierWithAvgQty> selectedsupplierWithAvgQtyList = new ArrayList<SupplierWithAvgQty>();

    // Rank by Best Delivery Performance (Based on Delivery Score Rating)
    private ArrayList<SupplierWithScore> supplierWithScoreList = new ArrayList<SupplierWithScore>(); // List of ALL SupplierWithScore
    private ArrayList<SupplierWithScore> supplierWithScoreListTop = new ArrayList<SupplierWithScore>(); // Sub-List of TOP 3/5 SupplierWithScore
    private List<SupplierWithScore> selectedsupplierWithScoreList = new ArrayList<SupplierWithScore>(); // Selected Non-Contracts Supplier from Quantity Purchased    

    // Rank by No Filter (Retrieve all Suppliers providing for Part)
    private List<SupplierWithPart> supplierWithPartList = new ArrayList<SupplierWithPart>();
    private List<SupplierWithPart> selectedsupplierWithPartList = new ArrayList<SupplierWithPart>();

    // Get ALL Selected Suppliers across DIFFERENT ranking criteria
    private HashSet<Supplier> allSelectedSupplierSet = new HashSet<Supplier>();
    private ArrayList<Supplier> allSelectedSupplierList = new ArrayList<Supplier>();

    // GET ALL PAST PO from Selected Supplier
    private List<PurchaseOrder> evaluateSupplierList = new ArrayList<PurchaseOrder>();
    private List<PurchaseOrder> selectedEvaluateSupplierList = new ArrayList<PurchaseOrder>();
    private ArrayList<String> evalSupNameList = new ArrayList<String>(); // For displaying Suppliers in drop down list
    private ArrayList<String> selectedEvalSupNameList = new ArrayList<String>(); // For selected Suppliers in drop down list

    @PostConstruct
    public void init() {
        System.err.println("init()");
        purchaseOrders = purchaseOrderSessionBean.getAllPurchaseOrdersForProMngr(); // Retrieve list of Purchase Orders from session bean (Pending, Approved, Rejected)
        statusList.add("Pending Approval");
        statusList.add("Approved");
        statusList.add("Rejected");

        invPurchaseOrders = purchaseOrderSessionBean.getAllPurchaseOrdersForInvStaff();
        invStatusList.add("Approved");
        invStatusList.add("Issued");
        invStatusList.add("Received");
        invStatusList.add("Falsed Issued");

        imPurchaseOrders = purchaseOrderSessionBean.getAllPurchaseOrdersForImStaff();
        imStatusList.add("Issued");
        imStatusList.add("Falsed Issued");

    }

    public List<String> getImStatusList() {
        return imStatusList;
    }

    public void setImStatusList(List<String> imStatusList) {
        this.imStatusList = imStatusList;
    }

    public List<PurchaseOrder> getImPurchaseOrders() {
        return imPurchaseOrders;
    }

    public void setImPurchaseOrders(List<PurchaseOrder> imPurchaseOrders) {
        this.imPurchaseOrders = imPurchaseOrders;
    }

    // GETTERS AND SETTERS
    public String getSelectedFilterForSupplier() {
        return selectedFilterForSupplier;
    }

    public void setSelectedFilterForSupplier(String selectedFilterForSupplier) {
        this.selectedFilterForSupplier = selectedFilterForSupplier;
    }

    public ArrayList<SupplierWithMinPrice> getSupplierWithMinPriceList() {
        return supplierWithMinPriceList;
    }

    public void setSupplierWithMinPriceList(ArrayList<SupplierWithMinPrice> supplierWithMinPriceList) {
        this.supplierWithMinPriceList = supplierWithMinPriceList;
    }

    public List<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(List<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public List<PurchaseOrder> getFilteredPurchaseOrders() {
        return filteredPurchaseOrders;
    }

    public void setFilteredPurchaseOrders(List<PurchaseOrder> filteredPurchaseOrders) {
        this.filteredPurchaseOrders = filteredPurchaseOrders;
    }

    public PurchaseOrder getSelectedPurchaseOrder() {
        return selectedPurchaseOrder;
    }

    public void setSelectedPurchaseOrder(PurchaseOrder selectedPurchaseOrder) {
        this.selectedPurchaseOrder = selectedPurchaseOrder;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public List<SupplierWithMinPrice> getSelectedSupplierWithMinPriceList() {
        return selectedSupplierWithMinPriceList;
    }

    public void setSelectedSupplierWithMinPriceList(List<SupplierWithMinPrice> selectedSupplierWithMinPriceList) {
        this.selectedSupplierWithMinPriceList = selectedSupplierWithMinPriceList;
    }

    public int getTopNum() {
        return topNum;
    }

    public void setTopNum(int topNum) {
        this.topNum = topNum;
    }

    public ArrayList<SupplierWithAvgQty> getSupplierWithAvgQtyList() {
        return supplierWithAvgQtyList;
    }

    public void setSupplierWithAvgQtyList(ArrayList<SupplierWithAvgQty> supplierWithAvgQtyList) {
        this.supplierWithAvgQtyList = supplierWithAvgQtyList;
    }

    public List<SupplierWithAvgQty> getSelectedsupplierWithAvgQtyList() {
        return selectedsupplierWithAvgQtyList;
    }

    public void setSelectedsupplierWithAvgQtyList(List<SupplierWithAvgQty> selectedsupplierWithAvgQtyList) {
        this.selectedsupplierWithAvgQtyList = selectedsupplierWithAvgQtyList;
    }

//    public List<Supplier> getDistSupForPartList() {
//        return distSupForPartList;
//    }
//
//    public void setDistSupForPartList(List<Supplier> distSupForPartList) {
//        this.distSupForPartList = distSupForPartList;
//    }
    public ArrayList<SupplierWithScore> getSupplierWithScoreList() {
        return supplierWithScoreList;
    }

    public void setSupplierWithScoreList(ArrayList<SupplierWithScore> supplierWithScoreList) {
        this.supplierWithScoreList = supplierWithScoreList;
    }

    public ArrayList<SupplierWithScore> getSupplierWithScoreListTop() {
        return supplierWithScoreListTop;
    }

    public void setSupplierWithScoreListTop(ArrayList<SupplierWithScore> supplierWithScoreListTop) {
        this.supplierWithScoreListTop = supplierWithScoreListTop;
    }

    public List<SupplierWithScore> getSelectedsupplierWithScoreList() {
        return selectedsupplierWithScoreList;
    }

    public void setSelectedsupplierWithScoreList(List<SupplierWithScore> selectedsupplierWithScoreList) {
        this.selectedsupplierWithScoreList = selectedsupplierWithScoreList;
    }

    public List<SupplierWithPart> getSupplierWithPartList() {
        return supplierWithPartList;
    }

    public void setSupplierWithPartList(List<SupplierWithPart> supplierWithPartList) {
        this.supplierWithPartList = supplierWithPartList;
    }

    public List<SupplierWithPart> getSelectedsupplierWithPartList() {
        return selectedsupplierWithPartList;
    }

    public void setSelectedsupplierWithPartList(List<SupplierWithPart> selectedsupplierWithPartList) {
        this.selectedsupplierWithPartList = selectedsupplierWithPartList;
    }

    public HashSet<Supplier> getAllSelectedSupplierSet() {
        return allSelectedSupplierSet;
    }

    public void setAllSelectedSupplierSet(HashSet<Supplier> allSelectedSupplierSet) {
        this.allSelectedSupplierSet = allSelectedSupplierSet;
    }

    public ArrayList<Supplier> getAllSelectedSupplierList() {
        return allSelectedSupplierList;
    }

    public void setAllSelectedSupplierList(ArrayList<Supplier> allSelectedSupplierList) {
        this.allSelectedSupplierList = allSelectedSupplierList;
    }

    public List<PurchaseOrder> getEvaluateSupplierList() {
        return evaluateSupplierList;
    }

    public void setEvaluateSupplierList(List<PurchaseOrder> evaluateSupplierList) {
        this.evaluateSupplierList = evaluateSupplierList;
    }

    public List<PurchaseOrder> getSelectedEvaluateSupplierList() {
        return selectedEvaluateSupplierList;
    }

    public void setSelectedEvaluateSupplierList(List<PurchaseOrder> selectedEvaluateSupplierList) {
        this.selectedEvaluateSupplierList = selectedEvaluateSupplierList;
    }

    public ArrayList<String> getEvalSupNameList() {
        return evalSupNameList;
    }

    public void setEvalSupNameList(ArrayList<String> evalSupNameList) {
        this.evalSupNameList = evalSupNameList;
    }

    public ArrayList<String> getSelectedEvalSupNameList() {
        return selectedEvalSupNameList;
    }

    public void setSelectedEvalSupNameList(ArrayList<String> selectedEvalSupNameList) {
        this.selectedEvalSupNameList = selectedEvalSupNameList;
    }

    public Date getScheduledDeliveryDate() {
        return scheduledDeliveryDate;
    }

    public void setScheduledDeliveryDate(Date scheduledDeliveryDate) {
        this.scheduledDeliveryDate = scheduledDeliveryDate;
    }

    public ShippingOrder getSelectedShippingOrder() {
        return selectedShippingOrder;
    }

    public void setSelectedSO(ShippingOrder selectedShippingOrder) {
        selectedShippingOrder = purchaseOrderSessionBean.getShippingOrder(selectedPurchaseOrder);
        System.out.println(selectedShippingOrder);
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public List<PurchaseOrder> getInvPurchaseOrders() {
        return invPurchaseOrders;
    }

    public void setInvPurchaseOrders(List<PurchaseOrder> invPurchaseOrders) {
        this.invPurchaseOrders = invPurchaseOrders;
    }

    public List<String> getInvStatusList() {
        return invStatusList;
    }

    public void setInvStatusList(List<String> invStatusList) {
        this.invStatusList = invStatusList;
    }

    public String getInvPOstatus() {
        return invPOstatus;
    }

    public void setInvPOstatus(String invPOstatus) {
        this.invPOstatus = invPOstatus;
    }

    // END OF GETTERS AND SETTERS
    /**
     * Creates a new instance of PurchaseOrderManagedBean
     */
    public PurchaseOrderManagedBean() {
    }

    // Create Purchase Order For Contract Supplier
    public void createPOForContractSup() {
        PurchaseRequisition pr = purchaseReqManagedBean.getSelectedPurchaseReqForSending();
        Double totalPrice = purchaseReqManagedBean.getContractTotalPrice();
        if (!scheduledDeliveryDate.after(new Date())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Date is before current date");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        purchaseOrderSessionBean.createPOForContractSup(pr, totalPrice, scheduledDeliveryDate);
        purchaseOrders.clear();
        purchaseOrders = purchaseOrderSessionBean.getAllPurchaseOrdersForProMngr(); // Retrieve list of Purchase Orders from session bean (Pending, Approved, Rejected)

        purchaseReqManagedBean.getPurchaseReqs().clear();
        purchaseReqManagedBean.setPurchaseReqs(purchaseReqSessionBean.getAllPurchaseReq());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully created Purchase Order", "Purchase Order has been sent to Procurement Manager for Approval");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // Create Purchase Order for Non-Contract Supplier
    public void createPOForNonContractSup() {
        QuotationDetail qd = quotnDetailProStaffManagedBean.getSelectedQuotationForProStaff();
        System.out.println("qd ending date:" + qd.getQuotationEndingDate());
        // Check if Quotation has expired
        if (!qd.getQuotationEndingDate().after(new Date())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Quotation has expired");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        // Added 23-10-14
        // Check if Purchase Order is already sent for Quotation
        if (qd.getPurchaseReq().getPurchaseReqStatus().equals("PO Sent")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Purchase Order has already been sent out for corresponding Purchase Requisition.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        purchaseOrderSessionBean.createPOForNonContractSup(qd);
        purchaseReqManagedBean.getPurchaseReqs().clear();
        purchaseReqManagedBean.setPurchaseReqs(purchaseReqSessionBean.getAllPurchaseReq());
        quotnDetailProStaffManagedBean.setQuotnDetailForProStaffList(quotationDetailSessionBean.getAllQuotnDetailForProStaff());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully created Purchase Order", "Purchase Order has been sent to Procurement Manager for Approval");
        FacesContext.getCurrentInstance().addMessage(null, message);
        purchaseOrders.clear();
        purchaseOrders = purchaseOrderSessionBean.getAllPurchaseOrdersForProMngr();
    }

    // Update Purchase Order Status
    public void updatePOStatus() {
        System.out.println("selectedPurchaseOrder status:" + selectedPurchaseOrder.getPurchaseOrderStatus());
        if (selectedPurchaseOrder.getPurchaseOrderStatus().isEmpty()) {
            selectedPurchaseOrder.setPurchaseOrderStatus(purchaseOrderSessionBean.getPODB(selectedPurchaseOrder).getPurchaseOrderStatus());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Please select a Purchase Order Status for updating");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        purchaseOrderSessionBean.updatePOStatus(selectedPurchaseOrder);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Purchase Order Status has been successfully updated");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // Edit Purchase Order
    public void redirectToEditPO() {
        System.out.println("redirectToEditPO:" + selectedPurchaseOrder.getPurchaseOrderStatus());
        if (selectedPurchaseOrder.getPurchaseOrderStatus().equals("Pending Approval")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("scmProStaffEditPO.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(QuotationDetailManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // Check if Quotation has already been submitted 
        else if (selectedPurchaseOrder.getPurchaseOrderStatus().equals("Approved")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed", "Purchase Order has already been approved.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (selectedPurchaseOrder.getPurchaseOrderStatus().equals("Rejected")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed", "Purchase Order has already been rejected.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            System.out.println("selectedPurchaseOrder status:" + selectedPurchaseOrder.getPurchaseOrderStatus());
        }
    }

    // Edit Purchase Order
    public void editPO() {
        System.out.println("editPO");
        // Check that Scheduled Delivery Date is not before or equals Current Date
        if ((!selectedPurchaseOrder.getScheduledDeliveryDate().after(new Date()))) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Scheduled Delivery Date must be after Current Date");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        purchaseOrderSessionBean.editPO(selectedPurchaseOrder);
        purchaseOrders.clear();
        purchaseOrders = purchaseOrderSessionBean.getAllPurchaseOrdersForProMngr();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successfully updated Purchase Order. Purchase Order has been sent to Procurement Manager for Approval.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // Delete Purchase Order
    public void deletePO() {
        System.out.println("deletePO:" + selectedPurchaseOrder.getPurchaseOrderId());
        if (selectedPurchaseOrder.getPurchaseOrderStatus().equals("Pending Approval")) {
            purchaseOrders.remove(selectedPurchaseOrder);
            purchaseOrderSessionBean.deletePO(selectedPurchaseOrder);
            purchaseOrders.remove(selectedPurchaseOrder);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Purchase Order has been deleted.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            purchaseOrders.clear();
            purchaseOrders = purchaseOrderSessionBean.getAllPurchaseOrdersForProMngr();
            //    filteredPurchaseOrders.clear();
            //      purchaseOrders.remove(selectedPurchaseOrder);
        } // Check if Quotation has already been submitted 
        else if (selectedPurchaseOrder.getPurchaseOrderStatus().equals("Approved")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed", "Purchase Order has already been approved.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (selectedPurchaseOrder.getPurchaseOrderStatus().equals("Rejected")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed", "Purchase Order has already been rejected.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            System.out.println("selectedPurchaseOrder status:" + selectedPurchaseOrder.getPurchaseOrderStatus());
        }
    }

    // Filter Supplier based on Filter
    public void displayFilteredPO() {
        try {
            System.out.println("selectedFilterForSupplier:" + selectedFilterForSupplier);
            PurchaseRequisition pr = purchaseReqManagedBean.getSelectedPurchaseReqForSending();
            if (selectedFilterForSupplier.equals("Best Past Deal Price")) {
                displayTop3BestPriceSup();
                FacesContext.getCurrentInstance().getExternalContext().redirect("scmProStaffFilterSupplierByBestPrice.xhtml");
            } else if (selectedFilterForSupplier.equals("Quantity Purchased")) {
                displayTop3AvgQtySup();
                FacesContext.getCurrentInstance().getExternalContext().redirect("scmProStaffFilterSupplierByAvgQty.xhtml");
            } else if (selectedFilterForSupplier.equals("Best Delivery Performance")) {
                retrieveAllSupForBestDelivery();
                displayTop3BestDelSup();
                FacesContext.getCurrentInstance().getExternalContext().redirect("scmProStaffFilterSupplierByBestDelPerf.xhtml");
            } // NO FILTER
            else {
                getAllSuppliersForPart();
                FacesContext.getCurrentInstance().getExternalContext().redirect("scmProStaffFilterSupplierByNoFilter.xhtml");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    // Display Top 3 Best Price Suppliers
    public void displayTop3BestPriceSup() {
        PurchaseRequisition pr = purchaseReqManagedBean.getSelectedPurchaseReqForSending();
        topNum = 3;
        supplierWithMinPriceList = purchaseOrderSessionBean.filterPOByBestDealPriceTop3(pr.getPart());
    }

    // Display Top 5 Best Price Suppliers
    public void displayTop5BestPriceSup() {
        PurchaseRequisition pr = purchaseReqManagedBean.getSelectedPurchaseReqForSending();
        supplierWithMinPriceList.clear();
        topNum = 5;
        supplierWithMinPriceList = purchaseOrderSessionBean.filterPOByBestDealPriceTop5(pr.getPart());
    }

    // Display Top 3 Highest Avg Qty
    public void displayTop3AvgQtySup() {
        PurchaseRequisition pr = purchaseReqManagedBean.getSelectedPurchaseReqForSending();
        topNum = 3;
        supplierWithAvgQtyList = purchaseOrderSessionBean.filterPOByAvgQtyTop3(pr.getPart());
    }

    // Display Top 5 Best Price Suppliers
    public void displayTop5AvgQtySup() {
        PurchaseRequisition pr = purchaseReqManagedBean.getSelectedPurchaseReqForSending();
        supplierWithAvgQtyList.clear();
        topNum = 5;
        supplierWithAvgQtyList = purchaseOrderSessionBean.filterPOByAvgQtyTop5(pr.getPart());
    }

    // Retrive ALL Suppliers for Best Delivery
    public void retrieveAllSupForBestDelivery() {
        supplierWithScoreList.clear();
        supplierWithScoreListTop.clear();
        PurchaseRequisition pr = purchaseReqManagedBean.getSelectedPurchaseReqForSending();
        System.out.println("pr.getPart():" + pr.getPart().getPartName());
        ArrayList<Supplier> distSupForPartList = new ArrayList<Supplier>(purchaseOrderSessionBean.getDistSupForPart(pr.getPart()));
        for (int i = 0; i < distSupForPartList.size(); i++) {
            System.out.println("supplier:" + distSupForPartList.get(i).getSupplierName());
            ArrayList<String> supDeliveryStatusList = new ArrayList<String>(purchaseOrderSessionBean.deliveryStatusListForSup(pr.getPart(), distSupForPartList.get(i)));
            Double totalScore = 0.0; // Initialise totalScore;
            System.out.println("supDeliveryStatusList size:" + supDeliveryStatusList.size());
            if (!supDeliveryStatusList.isEmpty()) {
                for (int j = 0; j < supDeliveryStatusList.size(); j++) {
                    if (supDeliveryStatusList.get(j) == null) {
                        continue;
                    }
                    if (supDeliveryStatusList.get(j).equals("Delivered On Time")) {
                        totalScore += 3;
                    }
                    if (supDeliveryStatusList.get(j).equals("Delayed")) {
                        totalScore += 2;
                    }
                    if (supDeliveryStatusList.get(j).equals("Not Delivered")) {
                        totalScore += 1;
                    }
                }

                Double avgScore = totalScore / supDeliveryStatusList.size();
                System.out.println("pr.getPart():" + pr.getPart().getPartName());
                System.out.println("supplierWithScoreList size:" + supplierWithScoreList.size());
                //supplierWithScoreList.clear();
                supplierWithScoreList.add(new SupplierWithScore(distSupForPartList.get(i), avgScore, pr.getPart(), supDeliveryStatusList.size()));
            }
        }
        // Sort by Score DESC
        Collections.sort(supplierWithScoreList, Collections.reverseOrder());
        System.out.println("supplierWithScoreList i:" + supplierWithScoreList.get(0).getPart().getPartName());
        System.out.println("supplierWithScoreList size:" + supplierWithScoreList.size());
    }

    // Display Top 3 Best Delivery Suppliers
    public void displayTop3BestDelSup() {
        supplierWithScoreListTop = new ArrayList<SupplierWithScore>(supplierWithScoreList.subList(0, 3));
        topNum = 3;
    }

    // Display Top 5 Best Delivery Suppliers
    public void displayTop5BestDelSup() {
        supplierWithScoreListTop = new ArrayList<SupplierWithScore>(supplierWithScoreList.subList(0, 5));
        topNum = 5;
    }

    // Retrieve Retrive ALL Suppliers for NO Filter
    public void getAllSuppliersForPart() {
        PurchaseRequisition pr = purchaseReqManagedBean.getSelectedPurchaseReqForSending();
        supplierWithPartList = supplierSessionBean.getSuppliersForPart(pr.getPart());
    }

    // ALL Selected Suppliers
    public void getAllSelectedSupplier() {
        allSelectedSupplierSet.clear();
        if (!selectedSupplierWithMinPriceList.isEmpty()) {
            for (int i = 0; i < selectedSupplierWithMinPriceList.size(); i++) {
                allSelectedSupplierSet.add(selectedSupplierWithMinPriceList.get(i).getSupplier());
            }
        }
        if (!selectedsupplierWithAvgQtyList.isEmpty()) {
            for (int i = 0; i < selectedsupplierWithAvgQtyList.size(); i++) {
                allSelectedSupplierSet.add(selectedsupplierWithAvgQtyList.get(i).getSupplier());
            }
        }
        if (!selectedsupplierWithScoreList.isEmpty()) {
            for (int i = 0; i < selectedsupplierWithScoreList.size(); i++) {
                allSelectedSupplierSet.add(selectedsupplierWithScoreList.get(i).getSupplier());
            }
        }
        if (!selectedsupplierWithPartList.isEmpty()) {
            for (int i = 0; i < selectedsupplierWithPartList.size(); i++) {
                allSelectedSupplierSet.add(selectedsupplierWithPartList.get(i).getSupplier());
            }
        }
        allSelectedSupplierList = new ArrayList<Supplier>(allSelectedSupplierSet);
        System.out.println("allSelectedSupplierList size:" + allSelectedSupplierSet.size());
    }

//    // Clear supplierWithScoreList, supplierWithScoreListTop and selectedPurchaseReqForSending
//    public void clearTopBestDelSup() {
//        try {
//            supplierWithScoreList.clear();
//            supplierWithScoreListTop.clear();
//            purchaseReqManagedBean.setSelectedPurchaseReqForSending(null);
//            FacesContext.getCurrentInstance().getExternalContext().redirect("scmProStaffViewPurchaseReq.xhtml");
//        } catch (IOException e) {
//            System.err.println(e);
//        }
//    }
    public void clearSelectedSupplierList() {
        try {
            allSelectedSupplierList.clear();
            selectedSupplierWithMinPriceList.clear();
            selectedsupplierWithScoreList.clear();
            selectedsupplierWithPartList.clear();
            selectedsupplierWithAvgQtyList.clear();
            supplierWithMinPriceList.clear();
            supplierWithScoreListTop.clear();
            supplierWithScoreList.clear();
            supplierWithPartList.clear();
            supplierWithAvgQtyList.clear();
            RequestContext.getCurrentInstance().execute("filteredSupNoFiltersTable.unselectAllRows();");
            RequestContext.getCurrentInstance().execute("filteredSupBestDelPerfsTable.unselectAllRows();");
            RequestContext.getCurrentInstance().execute("filteredSupAvgQtysTable.unselectAllRows();");
            RequestContext.getCurrentInstance().execute("filteredSupBestPricesTable.unselectAllRows();");
            FacesContext.getCurrentInstance().getExternalContext().redirect("scmProStaffViewPurchaseReq.xhtml");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void redirectEvaluateSupplier() {
        try {
            System.out.println("redirectEvaluateSupplier");
            PurchaseRequisition pr = purchaseReqManagedBean.getSelectedPurchaseReqForSending();
            if (!evaluateSupplierList.isEmpty()) {
                evaluateSupplierList.clear();
            }
            if (!evalSupNameList.isEmpty()) {
                evalSupNameList.clear();
            }
//            evaluateSupplierList.clear();
//            evalSupNameList.clear();
            if (allSelectedSupplierList.isEmpty()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Please select at least one Supplier");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            for (int i = 0; i < allSelectedSupplierList.size(); i++) {
                System.out.println(purchaseOrderSessionBean.getEvaluateSupplierFields(allSelectedSupplierList.get(i), pr.getPart()));
                evaluateSupplierList.addAll(purchaseOrderSessionBean.getEvaluateSupplierFields(allSelectedSupplierList.get(i), pr.getPart()));
                evalSupNameList.add(allSelectedSupplierList.get(i).getSupplierName());
            }
            System.out.println("evaluateSupplierList size:" + evaluateSupplierList.size());
            FacesContext.getCurrentInstance().getExternalContext().redirect("scmProStaffEvaluateSelectedSup.xhtml");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void evalSupBackRedirect() {
        try {
            evaluateSupplierList.clear();
            evalSupNameList.clear();
            FacesContext.getCurrentInstance().getExternalContext().redirect("scmProStaffFilterSupplierByBestPrice.xhtml");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void updateInvPO() {
        System.out.println(selectedPurchaseOrder.getPurchaseOrderStatus() + " to " + invPOstatus);
        selectedShippingOrder = purchaseOrderSessionBean.getShippingOrder(selectedPurchaseOrder);

        if (selectedPurchaseOrder.getPurchaseOrderStatus().equals("Approved") && !invPOstatus.equals("Received")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed", "Purchase Order has yet to be Received.");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else if (selectedPurchaseOrder.getPurchaseOrderStatus().equals("Approved") && invPOstatus.equals("Received")) {
            if (deliveryStatus.equals("")) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed", "Please update Delivery Status.");
                FacesContext.getCurrentInstance().addMessage(null, message);

            } else if (deliveryStatus.equals("Not Delivered")) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Purchase Order Status has been updated.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                purchaseOrderSessionBean.updateInvPO(selectedPurchaseOrder, "False Received", deliveryStatus);

                List<PurchaseOrder> spo = poSupplierPortalManagedBean.getSupplierPurchaseOrders();
                poSupplierPortalManagedBean.getSupplierPurchaseOrders().clear();
                poSupplierPortalManagedBean.setSupplierPurchaseOrders(purchaseOrderSessionBean.getAllPurchaseOrdersForSupplier(selectedPurchaseOrder.getSupplier()));

            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Purchase Order Status has been updated.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                purchaseOrderSessionBean.updateInvReceivedQty(selectedPurchaseOrder, invPOstatus, deliveryStatus);

                List<PurchaseOrder> spo = poSupplierPortalManagedBean.getSupplierPurchaseOrders();
                poSupplierPortalManagedBean.getSupplierPurchaseOrders().clear();
                poSupplierPortalManagedBean.setSupplierPurchaseOrders(purchaseOrderSessionBean.getAllPurchaseOrdersForSupplier(selectedPurchaseOrder.getSupplier()));

            }

        } else if (selectedPurchaseOrder.getPurchaseOrderStatus().equals("Received") && invPOstatus.equals("Issued")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Purchase Order has been updated.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            purchaseOrderSessionBean.updateIssuePO(selectedPurchaseOrder, invPOstatus);

        } else {
            System.out.println("selectedPurchaseOrder status:" + selectedPurchaseOrder.getPurchaseOrderStatus());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed", "Error updating Purchase Order Status. Incompatible ");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void updateImPO() {
        if (selectedPurchaseOrder.getPurchaseOrderStatus().equals("Issued") && !invPOstatus.equals("")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Sucess", "Delivery Status Updated");
            FacesContext.getCurrentInstance().addMessage(null, message);
            purchaseOrderSessionBean.updateImPO(selectedPurchaseOrder, invPOstatus);
        }
    }

    public void redirectToViewSO() {
        System.out.println(selectedShippingOrder);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("scmInvViewSO.xhtml?i=3");
        } catch (IOException ex) {
            Logger.getLogger(PurchaseOrderManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
