/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Furniture;
import entity.Part;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import session.stateless.ItemSessionBeanLocal;
import session.stateless.LogSessionLocal;
import session.stateless.PartSessionBeanLocal;

/**
 *
 * @author wangyan
 */
@Named(value = "partManageBean")
@ViewScoped
public class PartManageBean implements Serializable {

    @EJB
    private ItemSessionBeanLocal itemSB;
    @EJB
    private LogSessionLocal logSessionLocal;
    @EJB
    private PartSessionBeanLocal partSessionBean;

    @PostConstruct
    public void init() {

        parts = partSessionBean.getAllPart(); // Retrieve list of parts from session bean

//        FacesContext context = FacesContext.getCurrentInstance();
//        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
//        if (paramMap.containsKey("supplier")) {
//            String supplierName = paramMap.get("supplier");
//            supplierToEdit = supplierSessionBean.getOneSupplierByName(supplierName);
//        }
    }

    // Variables
    private List<Part> parts; // For View All Suppliers
    private List<Part> filteredPart; // For filtering in View All Suppliers
    private Part selectedPart;
    private String[] selectedParts;   // For Add Part to Supplier

    private String furnitureName;
    private Long furnitureId;
    private String statusMessage;
    private String newFurnitureName;
    private List<Furniture> furnitureList = new ArrayList<>();

    // Part
    private String partName;
    private Integer lotSize;
    private int leadTime;
    private UIComponent partNameInput;

    private String newPartName;
    private Long partId;
    private List<Part> partList = new ArrayList<>();

    // Getters and Setters
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public List<Part> getFilteredPart() {
        return filteredPart;
    }

    public void setFilteredPart(List<Part> filteredPart) {
        this.filteredPart = filteredPart;
    }

    public Part getSelectedPart() {
        return selectedPart;
    }

    public void setSelectedPart(Part selectedPart) {
        this.selectedPart = selectedPart;
    }

    public String[] getSelectedParts() {
        return selectedParts;
    }

    public void setSelectedParts(String[] selectedParts) {
        this.selectedParts = selectedParts;
    }

    public UIComponent getPartNameInput() {
        return partNameInput;
    }

    public void setPartNameInput(UIComponent partNameInput) {
        this.partNameInput = partNameInput;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public Long getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(Long furnitureId) {
        this.furnitureId = furnitureId;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getNewFurnitureName() {
        return newFurnitureName;
    }

    public void setNewFurnitureName(String newFurnitureName) {
        this.newFurnitureName = newFurnitureName;
    }

    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }

    public void setFurnitureList(List<Furniture> furnitureList) {
        this.furnitureList = furnitureList;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Integer getLotSize() {
        return lotSize;
    }

    public void setLotSize(Integer lotSize) {
        this.lotSize = lotSize;
    }

    public int getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(int leadTime) {
        this.leadTime = leadTime;
    }

    public String getNewPartName() {
        return newPartName;
    }

    public void setNewPartName(String newPartName) {
        this.newPartName = newPartName;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public List<Part> getPartList() {
        return partList;
    }

    public void setPartList(List<Part> partList) {
        this.partList = partList;
    }

    public PartManageBean() {
    }

    public void addNewPart() throws IOException {
        int check = partSessionBean.checkPart(partName);
        if (check == 1) {
            System.out.println("Adding Part");
            partSessionBean.addPart(partName, leadTime, lotSize, "no");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", " Part Successfully Added");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext.getCurrentInstance().getExternalContext().redirect("mrpManagePart.xhtml?i=2");
        } else if (check == 2) {
            System.out.println("Part to add already in database");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Part Already Exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (check == 3) {
            System.out.println("Part was previously deleted");
            partSessionBean.addPart(partName, leadTime, lotSize, "no");
            FacesContext.getCurrentInstance().getExternalContext().redirect("mrpManagePart.xhtml?i=2");
        }
    }

    public void checkExistingPart() {
        int check = partSessionBean.checkPart(partName);
        if (check == 1) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(partNameInput.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Valid Part ", null));
        } else if (check == 2) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(partNameInput.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Part Already Exist", "The Part you are trying to add is already in database"));
        } else if (check == 3) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(partNameInput.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "Part was previously deleted from database", null));
        }
    }

    public boolean filterByPartName(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            System.err.println("1");
            return true;
        }
        if (value == null) {
            System.err.println("2");
            return false;
        }
        System.err.println("3: " + value.toString().toLowerCase().contains(filter.toString().toLowerCase(locale)));
        if (filteredPart != null) {
            System.err.println("filteredPart: " + filteredPart.toString());
        }
        return value.toString().toLowerCase().contains(filter.toString().toLowerCase());
    }

    public void noEdit(RowEditEvent event) {

    }

    public void edit(RowEditEvent event) {
        Part ri = ((Part) event.getObject());
        System.out.println(ri);
        try {
            partSessionBean.update(ri, leadTime, lotSize);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Part updated successfuly");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Invalid input");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void deletePart() {
        System.out.println("PartManagedBean: deletePart()");
        int check = partSessionBean.deleteCheck(selectedPart);

        if (check == 1) {
            partSessionBean.delete(selectedPart);
            parts.remove(selectedPart);
            parts = partSessionBean.getAllPart();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Part deleted successfuly");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (check == 2) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Part is needed for BOM");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Part is tied to contact");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
        //
    //
    //
    //
    //
    //
    //
    //

    public void searchFurniture() {
        Furniture result = itemSB.getFurniture(furnitureName);
        if (result == null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No such furniture ", "The furniture you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            furnitureList.clear();
            furnitureList.add(result);
        }

    }

    public void searchPart(ActionEvent event) {
        Part result = itemSB.getPart(partName);
        if (result == null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No such part exists ", "The part you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            partList.clear();
            partList.add(result);
        }

    }

    public void viewAllFurniture() {
        System.out.println("test for method viewAllFurniture()1 in furmanageBean");
        furnitureList.clear();
        System.out.println("test for method viewAllFurniture()2 in furmanagebean");

        furnitureList = itemSB.getAllFurniture();
        System.out.println("test for method viewAllFurniture()3 furmanagebean");
    }

    public void viewAllPart() {
        //test
        System.out.println("test for method viewAllPart()1 in furmanageBean");
        partList.clear();
        System.out.println("test for method viewAllPart()2 in furmanagebean");

        partList = itemSB.getAllPart();
        System.out.println("test for method viewAllPart()3 furmanagebean");
    }

    public void deleteFurniture() {
        //test

        System.out.println("test for whether the deleteFurniture has been called in FurManageBean");

        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(this.furnitureName, "Deleted furniture", cal);

        try {
            System.out.println("testing deletefurniture() from furManagebean");
            furnitureId = itemSB.DelectFurniture(getFurnitureName());
            System.out.println("testing 2 deletefurniture() from furManagebean");
            if (furnitureId != -1l) {
                statusMessage = "New furniture delete successfully";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "DELETE FURNITURE RESULT: " + statusMessage + "(Deleted furniture id is " + furnitureId + ")", ""));

            } else {
                statusMessage = "furniture deleted failed. furniture name not exists";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "DELETE FURNITURE RESULT: " + statusMessage, ""));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

//    public void deletePart(ActionEvent event) {
//
//        partName = (String) event.getComponent().getAttributes().get("partName");
//        Calendar cal = Calendar.getInstance();
//        logSessionLocal.createLog(partName, "Deleted Part", cal);
//
//        try {
//            partId = itemSB.DelectPart(getPartName());
//            if (partId != -1l && partId != -2l) {
//                statusMessage = "Part delete successfully";
//
//                partList = itemSB.getAllPart();
//
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "DELETE PART RESULT: " + statusMessage, ""));
//
//            } else if (partId == -2l) {
//                statusMessage = "Part delete failed, Some BOM contain exsting part. the part is not available to delete";
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "DELETE PART RESULT: " + statusMessage, ""));
//
//            } else {
//                statusMessage = "part deleted failed. part name not exists";
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "DELETE PART RESULT: " + statusMessage, ""));
//
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }
    public void deleteFurniture2(Furniture furniture) {
        //need to edit later

        System.out.println("test successfully in deleteFurniture 2 in furmanagementbean");
    }

    public void deletePart2() {

        //need to edit later
        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(this.partName, "Deleted part", cal);

        try {
            partId = itemSB.DelectPart(getPartName());
            if (partId != -1l && partId != -2l) {
                statusMessage = "Part delete successfully";

                partList = itemSB.getAllPart();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "DELETE PART RESULT: " + statusMessage, ""));

            } else if (partId == -2l) {
                statusMessage = "Part delete failed, Some BOM contain exsting part. the part is not available to delete";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "DELETE PART RESULT: " + statusMessage, ""));

            } else {
                statusMessage = "part deleted failed. part name not exists";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "DELETE PART RESULT: " + statusMessage, ""));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("test successfully in deletePart 2 in partmagementbean");
    }

    public void saveNewPart() {
        try {
            partId = itemSB.AddPart(partName, Integer.valueOf(lotSize));
            if (partId != -2l) {
                statusMessage = "New part saved successfully";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ADD NEW PART RESULT: " + statusMessage + "(new part id is " + partId + ")", ""));

            } else {
                statusMessage = "part saved failed. part already exist in database";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ADD NEW PART RESULT: " + statusMessage, ""));

            }
        } catch (NumberFormatException e) {
            statusMessage = "part saved failed. invalid Lot size format --(please enter an integer for lot size)";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "PLEASE ENTER AN INTEGER LOT SIZE: " + statusMessage, ""));

        }

    }

    public void editPart() {
        try {
            partId = itemSB.editPart2(partName, newPartName, Integer.valueOf(lotSize));
            if (partId != -2l) {
                statusMessage = "Part edited successfully";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EDIT PART RESULT: " + statusMessage + "(new part id is " + partId + ")" + "new part name is " + getNewPartName(), ""));

            } else {
                statusMessage = "part saved failed. original part name not exsit";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "EDIT PART RESULT: " + statusMessage, ""));

            }

        } catch (NumberFormatException e) {
            statusMessage = "part eidt failed. invalid Lot Size format";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PLEASE ENTER AN INTEGER LOT SIZE: " + statusMessage, ""));
        }
    }

    public void editFurniture() {
        try {
            System.out.println("testing 3");
            furnitureId = itemSB.editFurniture2(getFurnitureName(), getNewFurnitureName());
            System.out.println("testing 4");
            if (furnitureId != -2l && furnitureId != -1) {
                statusMessage = "furniture edited successfully";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EDIT FURNITURE RESULT: " + statusMessage + "(new furniture id is " + furnitureId + ")" + "new furniture name is " + getNewFurnitureName(), ""));
            } else if (furnitureId == -2l) {
                statusMessage = "furniture saved failed. original furniture name not exsit";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EDIT FURNITURE RESULT: " + statusMessage, ""));
            } else {
                statusMessage = "furniture saved failed. new furniture name has already been used,please provide a new valid furniture name";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EDIT FURNITURE RESULT: " + statusMessage, ""));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

//    public void saveNewFurniture() {
//        try {
//            System.out.println("testing 1");
//            furnitureId = itemSB.AddFurniture(getFurnitureName());
//            System.out.println("testing 2");
//            if (furnitureId != -2l) {
//                statusMessage = "New furniture saved successfully";
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ADD NEW FURNITURE RESULT: " + statusMessage + "(new furniture id is " + furnitureId + ")", ""));
//
//            } else {
//                statusMessage = "furniture saved failed. furniture name already exists";
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ADD NEW FURNITURE RESULT: " + statusMessage, ""));
//
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
