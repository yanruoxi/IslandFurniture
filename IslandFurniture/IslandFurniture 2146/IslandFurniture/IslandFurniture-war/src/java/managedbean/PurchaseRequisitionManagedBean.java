/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import datamodel.PartWithQuantity;
import entity.MPS;
import entity.Part;
import entity.PurchaseRequisition;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import session.stateless.MPSSessionBeanLocal;
import session.stateless.PartSessionBeanLocal;
import session.stateless.PurchaseReqSessionBeanLocal;

/**
 *
 * @author Meiling
 */
@Named(value = "purchaseReqManagedBean")
@SessionScoped
public class PurchaseRequisitionManagedBean implements Serializable {

    @EJB
    private MPSSessionBeanLocal mpsSessionBean;

    @EJB
    private PurchaseReqSessionBeanLocal purchaseReqSessionBean;
    @EJB
    private PartSessionBeanLocal partSessionBean;

    // Added
    @Inject
    private LoginManagedBean loginManagedBean;

    /**
     * Creates a new instance of PurchaseAcquisitionBean
     */
    public PurchaseRequisitionManagedBean() {
    }

    // Variables
    private Integer qty;
    private String selectedPartName;
    private List<String> partNames;
    private Part selectedPartForPurchaseReq;
    private Integer lotSize;
    private Date currentDate = new Date();
    private List<Part> parts;
    private List<PartWithQuantity> partQtyList = new ArrayList<PartWithQuantity>();
    private List<PurchaseRequisition> purchaseReqs; // For View All Purchase Requisitions
    private PurchaseRequisition selectedPurchaseReqForSending; // For Sending Purchase Requisition
    private Double contractTotalPrice;

    private List<MPS> mpsList;
    private String furnitureName;
    private List<String> furnitures = new ArrayList<String>();
    private List<String> partList = new ArrayList<String>();
    private String time;
    private String partNameForMRP;

    // Added
    private String replenishmentType;
    private PurchaseRequisition selectedPurchaseReq;
    private ArrayList<String> replenishmentTypeList = new ArrayList<String>();
    private List<PurchaseRequisition> filteredPurchaseReqs; 
    // Added 23-10-14
    private ArrayList<String> purchaseReqStatusList = new ArrayList<String>();
    
    @PostConstruct
    public void init() {
        System.err.println("init()");
        partNames = partSessionBean.getPartsString();
        parts = new ArrayList<Part>(partSessionBean.getAllParts());
        purchaseReqs = new ArrayList<PurchaseRequisition>(purchaseReqSessionBean.getAllPurchaseReq());
        furnitures = mpsSessionBean.getFurnitures();
        replenishmentTypeList.add("Scheduled");
        replenishmentTypeList.add("Ad Hoc");
        // Added 23-10-14
        purchaseReqStatusList.add("PO Not Created");
        purchaseReqStatusList.add("Submitted RFQ");
        purchaseReqStatusList.add("PO Sent");
    }


    // GETTERS AND SETTERS
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getSelectedPartName() {
        return selectedPartName;
    }

    public void setSelectedPartName(String selectedPartName) {
        this.selectedPartName = selectedPartName;
    }

    public List<String> getPartNames() {
        return partNames;
    }

    public void setPartNames(List<String> partNames) {
        this.partNames = partNames;
    }

    public Part getSelectedPartForPurchaseReq() {
        return selectedPartForPurchaseReq;
    }

    public void setSelectedPartForPurchaseReq(Part selectedPartForPurchaseReq) {
        this.selectedPartForPurchaseReq = selectedPartForPurchaseReq;
    }

    public Integer getLotSize() {
        return lotSize;
    }

    public void setLotSize(Integer lotSize) {
        this.lotSize = lotSize;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public List<PartWithQuantity> getPartQtyList() {
        return partQtyList;
    }

    public void setPartQtyList(List<PartWithQuantity> partQtyList) {
        this.partQtyList = partQtyList;
    }

    public List<PurchaseRequisition> getPurchaseReqs() {
        return purchaseReqs;
    }

    public void setPurchaseReqs(List<PurchaseRequisition> purchaseReqs) {
        this.purchaseReqs = purchaseReqs;
    }

    public PurchaseRequisition getSelectedPurchaseReqForSending() {
        return selectedPurchaseReqForSending;
    }

    public void setSelectedPurchaseReqForSending(PurchaseRequisition selectedPurchaseReqForSending) {
        this.selectedPurchaseReqForSending = selectedPurchaseReqForSending;
    }

    public Double getContractTotalPrice() {
        return contractTotalPrice;
    }

    public void setContractTotalPrice(Double contractTotalPrice) {
        this.contractTotalPrice = contractTotalPrice;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public List<String> getFurnitures() {
        return furnitures;
    }

    public void setFurnitures(List<String> furnitures) {
        this.furnitures = furnitures;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<MPS> getMpsList() {
        return mpsList;
    }

    public void setMpsList(List<MPS> mpsList) {
        this.mpsList = mpsList;
    }

    public List<String> getPartList() {
        return partList;
    }

    public void setPartList(List<String> partList) {
        this.partList = partList;
    }

    public String getPartNameForMRP() {
        return partNameForMRP;
    }

    public void setPartNameForMRP(String partNameForMRP) {
        this.partNameForMRP = partNameForMRP;
    }

    public String getReplenishmentType() {
        return replenishmentType;
    }

    public void setReplenishmentType(String replenishmentType) {
        this.replenishmentType = replenishmentType;
    }

    public PurchaseRequisition getSelectedPurchaseReq() {
        return selectedPurchaseReq;
    }

    public void setSelectedPurchaseReq(PurchaseRequisition selectedPurchaseReq) {
        this.selectedPurchaseReq = selectedPurchaseReq;
    }

    public ArrayList<String> getReplenishmentTypeList() {
        return replenishmentTypeList;
    }

    public void setReplenishmentTypeList(ArrayList<String> replenishmentTypeList) {
        this.replenishmentTypeList = replenishmentTypeList;
    }

    public List<PurchaseRequisition> getFilteredPurchaseReqs() {
        return filteredPurchaseReqs;
    }

    public void setFilteredPurchaseReqs(List<PurchaseRequisition> filteredPurchaseReqs) {
        this.filteredPurchaseReqs = filteredPurchaseReqs;
    }

    public ArrayList<String> getPurchaseReqStatusList() {
        return purchaseReqStatusList;
    }

    public void setPurchaseReqStatusList(ArrayList<String> purchaseReqStatusList) {
        this.purchaseReqStatusList = purchaseReqStatusList;
    }
    
    // END OF GETTERS AND SETTERS
    // Add Part to Purchase Req
    public void addToPurchaseReq() {
        System.out.println("selectedPartForPurchaseReq:" + selectedPartForPurchaseReq.getId() + " " + selectedPartForPurchaseReq.getPartName());
        System.out.println("qty:" + qty);
        System.out.println("replenishmentType:" + replenishmentType);

        // Check if qty is 0
        if (qty == 0) {
            System.out.println("qty 0?:" + qty);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Quantity must not be zero.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        } else if (replenishmentType.isEmpty()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Please select a Replenishment Type.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        } // Check if qty corresponds to lot size
        else if ((qty % selectedPartForPurchaseReq.getLotSize()) != 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Quantity must be corresponding to Lot Size.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            qty = 0;
            return;
        }

        for (int i = 0; i < partQtyList.size(); i++) {
            // Check if Part already added to Purchase Req
            if (partQtyList.get(i).getPart().getId().compareTo(selectedPartForPurchaseReq.getId()) == 0) {
                if (partQtyList.get(i).getReplenishmentType().compareTo(replenishmentType) == 0) {
                    //if (partQtyList.get(i).getPart().getId().compareTo(selectedPartForPurchaseReq.getId()) == 0) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Part with the same Replenishment Type is already added to Purchase Requisition.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    qty = 0;
                    return;
                }
            }
        }

        partQtyList.add(new PartWithQuantity(selectedPartForPurchaseReq, qty, replenishmentType)); // add to partQtyList
        System.out.println("partQtyList size:" + partQtyList.size());
        System.out.println("partQtyList element:" + partQtyList.get(0));

        qty = 0; // re-initialize qty for other part

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Part added to Purchase Requisition.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // Create Purchase Req
    public void createPurchaseReq(ActionEvent event) {
        for (int i = 0; i < partQtyList.size(); i++) {
            // Check if qty is 0
            if (partQtyList.get(i).getQuantity() == 0) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Quantity must not be zero");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            } // Check if qty corresponds to lot size
            else if (((partQtyList.get(i).getQuantity()) % partQtyList.get(i).getPart().getLotSize()) != 0) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Quantity must be corresponding to Lot Size.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                qty = 0;
                return;
            }

            // Create Purchase Req for each selected Part and its quantity
            PurchaseRequisition pr = new PurchaseRequisition();
            pr.setPurchaseReqDate(new Date());
            pr.setPart(partSessionBean.getPart(partQtyList.get(i).getPart().getId()));
            pr.setQty(partQtyList.get(i).getQuantity());

            // Added
            pr.setIsDeleted(false);
            pr.setReplenishmentType(partQtyList.get(i).getReplenishmentType());
            System.out.println("loginManagedBean.getUser():" + loginManagedBean.getUserName()); // Get username of current logged in SCM Staff
            pr.setCreatedBy(loginManagedBean.getUserName());
            // Added 23-10-14
            pr.setPurchaseReqStatus("PO Not Created");

            purchaseReqSessionBean.addPurchaseReq(pr);
        }
        partQtyList.clear(); // re-initialize partQtyList after adding
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Part(s) added to Purchase Requisition");
        FacesContext.getCurrentInstance().addMessage(null, message);

        purchaseReqs.clear();
        purchaseReqs = purchaseReqSessionBean.getAllPurchaseReq();
        replenishmentType = null;
    }

    // Cancel Create Purchase Req
    public void cancelPurchaseReq() {
        partQtyList.clear();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelled", "Purchase Requisition is cancelled");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // Added
    // Redirect Send Purchase Req to Select Supplier
    public void redirectSendPurchaseReq() {
        try {
            System.out.println("selectedPurchaseReqForSending:" + selectedPurchaseReqForSending.getPurchaseReqId());
            // Check Purchase Req Status. Purchase Req cannot be sent if 'PO Sent'
            // If Purchase Req Status is 'Submitted RFQ', Purchaser is still able to Request for Quotation for other suppliers.
            if (selectedPurchaseReqForSending.getPurchaseReqStatus().equals("PO Sent")) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Purchase Order has already been sent out for the Purchase Requisition.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            // If there is NO CONTRACT for Part
            if (selectedPurchaseReqForSending.getPart().getContract() == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("scmProStaffChooseFilterForSupplier.xhtml?i=1");
            } // Else, there is CONTRACT for Part
            else {
                // If CONTRACT has expired
                if (!selectedPurchaseReqForSending.getPart().getContract().getEndDate().after(new Date())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contract exists for Part but Contract has expired", "Please inform SCM Admin to extend or end the Contract.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                Integer selectedPurchaseReqQty = selectedPurchaseReqForSending.getQty();
                Double selectedPurchaseReqContractUnitPrice = selectedPurchaseReqForSending.getPart().getContract().getUnitPrice();
                contractTotalPrice = selectedPurchaseReqQty * selectedPurchaseReqContractUnitPrice;

                FacesContext.getCurrentInstance().getExternalContext().redirect("scmProStaffCreatePOToContractSup.xhtml?i=1");
            }
        } catch (IOException e) {
            System.err.println("Caught IO Exception");
        }
    }

    // View MRP Record
    public void viewMRPRecord() {
        if (!time.matches("\\d{2}-\\d{4}")) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error format of time", "Error format of time");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            mpsList = mpsSessionBean.viewMRP(time, furnitureName, partNameForMRP);
            if (mpsList == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No MRP records found", "Please contact MRP staff to generate MRP.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public void getPartNamesForFurniture() {
        partList = mpsSessionBean.getParts(furnitureName);
    }
    
    // Delete Purchase Requisition
    public void deletePurchaseReq() {
        // Added - Check Purchase Req status
        if(selectedPurchaseReq.getPurchaseReqStatus().equals("Submitted RFQ")){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Request for Quotation has already been submitted for the Purchase Requisition.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        if(selectedPurchaseReq.getPurchaseReqStatus().equals("PO Sent")){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Purchase Order has already been sent out for the Purchase Requisition.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        purchaseReqSessionBean.deletePurchaseReq(selectedPurchaseReq);
        purchaseReqs.remove(selectedPurchaseReq);
        selectedPurchaseReq = null;
        purchaseReqs = purchaseReqSessionBean.getAllPurchaseReq();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successfully Deleted Purchase Requisition");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void redirectToEditPurchaseReq() {
        System.out.println("redirectToEditPurchaseReq:" + selectedPurchaseReq.getPurchaseReqId());
        try {
            // Added - Check Purchase Req status
            if (selectedPurchaseReq.getPurchaseReqStatus().equals("Submitted RFQ")) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Request for Quotation has already been submitted for the Purchase Requisition.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (selectedPurchaseReq.getPurchaseReqStatus().equals("PO Sent")) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Purchase Order has already been sent out for the Purchase Requisition.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("scmInvEditPurchaseReq.xhtml?i=2");
        } catch (IOException ex) {
            Logger.getLogger(QuotationDetailManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editPurchaseReq(){
        System.out.println("editPurchaseReq");
        // Check if qty is 0
        if (selectedPurchaseReq.getQty() == 0) {
            System.out.println("qty 0?:" + selectedPurchaseReq.getQty());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Quantity must not be zero.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        // Check if qty corresponds to lot size
        else if ((selectedPurchaseReq.getQty() % selectedPurchaseReq.getPart().getLotSize()) != 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Quantity must be corresponding to Lot Size.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            qty = 0;
            return;
        }
        System.out.println("selectedPurchaseReq qty:" + selectedPurchaseReq.getQty());
        boolean editedPurchaseReq = purchaseReqSessionBean.editPurchaseReq(selectedPurchaseReq);
        if(editedPurchaseReq){
            purchaseReqs.clear();
            purchaseReqs = purchaseReqSessionBean.getAllPurchaseReq();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successfully updated Purchase Requisition.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Failed to update as no changes were made.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

}
