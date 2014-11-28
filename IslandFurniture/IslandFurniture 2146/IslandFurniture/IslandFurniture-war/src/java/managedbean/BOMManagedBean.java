package managedbean;

/**
 *
 * @author wangyan
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entity.BOM;
import entity.Furniture;
import entity.Part;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import session.stateless.ItemSessionBeanLocal;
import session.stateless.LogSessionLocal;

@Named(value = "bomManagedBean")
@ViewScoped
public class BOMManagedBean implements Serializable {

    @EJB
    private ItemSessionBeanLocal itemSB;
    @EJB
    private LogSessionLocal logSessionLocal;

    private String furnitureName;
    private Long furnitureId;
    private String statusMessage;
    private String newFurnitureName;
    private List<Furniture> furnitureList = new ArrayList<>();
    private String partName;
    private String newPartName;
    private Long partId;
    private String lotSize;
    private List<Part> partList = new ArrayList<>();
    private List<Part> partListAll = new ArrayList<>();
    private String quantity;
    private Long bomId;
    private String bomName;
    private List<BOM> bomList = new ArrayList<>();
    private List<String> abc = new ArrayList<>();
    private List<String> cba = new ArrayList<>();

    public void init() {
        this.partListAll = itemSB.getAllPart();
        if (itemSB.getFurniture(furnitureName) == null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO SUCH FURNITURE", "The furniture you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.out.println("testing 1");

        } else {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "FURNITURE ALREADY EXIST", "Please enter part and quantity for this furniture");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.out.println("testing 2");
        }
        System.out.println("part list" + partListAll);

    }

    public List<String> getCba() {
        List<Furniture> l1 = itemSB.getAllFurniture();

        for (Object o : l1) {
            Furniture f = (Furniture) o;
            cba.add(f.getFurnitureName());
        }
        return cba;
    }

    public void setCba(List<String> cba) {
        this.cba = cba;
    }

    public List<String> getAbc() {
        abc.clear();
        List<Part> l2 = itemSB.getAllPart();
        for (Object o : l2) {
            Part p = (Part) o;
            abc.add(p.getPartName());
        }

        return abc;
    }

    public void setAbc(List<String> abc) {
        this.abc = abc;
    }

    public String getBomName() {
        return bomName;
    }

    public void setBomName(String bomName) {
        this.bomName = bomName;
    }

    public List<BOM> getBomList() {
        return bomList;
    }

    public void setBomList(List<BOM> bomList) {
        this.bomList = bomList;
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "BOM has been successfully updated", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        System.out.println("test from BOMManagedBean: " + event.getColumn().getHeaderText());
        //yeah you got it!!

        System.out.println("component-->" + event.getComponent());
        System.out.println(event.getSource());
        DataTable s = (DataTable) event.getSource();
        System.out.println(s.getVar());

        String head = event.getColumn().getHeaderText();

      //  bomId = (Long) event.getComponent().getAttributes().get("bomId");--cannot be used to trace var in datablae
        
        //use facescontext to retrive the var in databale
         FacesContext context = FacesContext.getCurrentInstance();
         BOM data = context.getApplication().evaluateExpressionGet(context, "#{bom}", BOM.class);
         bomId=data.getId();
         System.out.println("testing "+bomId);
        /*   if(head.equals("Furniture")) { 
         itemSB.editFurniture3((String)oldValue, (String)newValue);
         }
      
      
         else  */

        if(head.equals("Part")) {
           System.out.println("test part");
            //    setPartName((String)newValue);
            itemSB.changePartBom((String) newValue, bomId);

        } else if (head.equals("Quantity")) {

            System.out.println(bomId);
            itemSB.changeQuantityBom((Integer) newValue, bomId);
            //   setQuantity((String)newValue);
            // need to be edit later
        }

    }

    public void addBom(ActionEvent event) {
        System.out.println("test from addBom in BOMManagedBean.java");
        System.out.println(partName);
        System.out.println(furnitureName);
        System.out.println(quantity);
        try {
            System.out.println("test for addbom() from BOMManagedBean");
            bomId = itemSB.createBom(furnitureName, partName, Integer.valueOf(quantity));
            if (bomId != -2l && bomId != -3l) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS", "BOM saved successfully"));

            } else if (bomId == -2l) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FAILED TO SAVE BOM", "Name does not exists"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "FAILED TO SAVE BOM", "BOM already exists"));
            }

        } catch (NumberFormatException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "FAILED TO SAVE BOM", "Invalid quantity format. Integer expected."));
        }

    }

    public Long getBomId() {
        return bomId;
    }

    public void setBomId(Long bomId) {
        this.bomId = bomId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public List<Part> getPartListAll() {
        return partListAll;
    }

    public void setPartListAll(List<Part> partListAll) {
        this.partListAll = partListAll;
    }

    public String getLotSize() {
        return lotSize;
    }

    public void setLotSize(String lotSize) {
        this.lotSize = lotSize;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
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

    /**
     * Creates a new instance of FurManageBean
     */
    public BOMManagedBean() {
    }

    public void searchFurniture() {
        Furniture result = itemSB.getFurniture(furnitureName);  
        if (result == null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No such furniture.", "The furniture you are searching for does not exist.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            furnitureList.clear();
            furnitureList.add(result);
        }

    }

    public void searchBom(ActionEvent event) {
        //test
        System.out.println("test for method search bom ()1 ManagedBean");
        bomList.clear();
        System.out.println("test for method searchbom()2 in BOMManagedBean");

        bomList = itemSB.getFurnitureBom(furnitureName);
        System.out.println("test for method searchbom()3 BOMManagedBean");
    }

    public void searchPart() {
        Part result = itemSB.getPart(partName);
        if (result == null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No such part.", "The part you are searching for does not exist.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            partList.clear();
            partList.add(result);
        }

    }

    public void viewAllFurniture() {
        //test
        System.out.println("test for method viewAllFurniture()1 in FurManagedBean");
        furnitureList.clear();
        System.out.println("test for method viewAllFurniture()2 in FurManagedBean");

        furnitureList = itemSB.getAllFurniture();
        System.out.println("test for method viewAllFurniture()3 FurManagedBean");
    }

    public void viewAllBom() {
        //test
        System.out.println("test for method viewAllBom()1 BOMManagedBean");
        bomList.clear();
        System.out.println("test for method viewAllbom()2 in BOMManagedBean");

        bomList = itemSB.getAllBom();
        System.out.println("test for method viewAllbom()3 BOMManagedBean");
    }

    public void viewAllPart() {
        //test
        System.out.println("test for method viewAllPart()1 in FurManagedBean");
        partList.clear();
        System.out.println("test for method viewAllPart()2 in FurManagedBean");

        partList = itemSB.getAllPart();
        System.out.println("test for method viewAllPart()3 FurManagedBean");
    }

    public void deleteFurniture() {
        //test

        System.out.println("test for whether the deleteFurniture has been called in FurManagedBean");

        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(this.furnitureName, "Deleted furniture", cal);

        try {
            System.out.println("testing deletefurniture() from FurManagedBean");
            furnitureId = itemSB.DelectFurniture(getFurnitureName());
            System.out.println("testing 2 deletefurniture() from FurManagedBean");
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

    public void deletePart() {
        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(this.furnitureName, "Deleted furniture", cal);

        try {
            partId = itemSB.DelectPart(getPartName());
            if (partId != -1l && partId != -2l) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PART DELETED SUCCESSFULLY", "Part Id: " + partId + " deleted"));
            } else if (partId == -2l) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "DELETE FAILED", "There is BOM containing exsting part"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "DELETE FAILED", "Part does not exists"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void deleteBOM(ActionEvent event) {

        System.out.println("test deletebom() from BOMManagedBean");
        bomId = (Long) event.getComponent().getAttributes().get("bomId");
        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(this.furnitureName, "Deleted furniture bom ", cal);

        try {
            System.out.println("test deletebom() from BOMManagedBean" + bomId);
            bomId = itemSB.DeleteBOM2(bomId);
            System.out.println("test deletebom()2 from BOMManagedBean");
            System.out.println("test deletebom() from BOMManagedBean" + bomId);
            statusMessage = "BOM deleted successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, statusMessage , ""));
            bomList = itemSB.getAllBom();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void deleteFurniture2(Furniture furniture) {
        //need to edit later
        System.out.println("test successfully in deleteFurniture 2 in furmanagementbean");
    }

    public void deletePart2(Part part) {

        //need to edit later
        System.out.println("test successfully in deletePart 2 in partmagementbean");
    }

    public void deleteBom2(BOM bom) {
        //need to edit later
    }

    public void saveNewPart() {
        try {
            partId = itemSB.AddPart(partName, Integer.valueOf(lotSize));
            if (partId != -2l) {
                statusMessage = "New part saved successfully";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ADD NEW PART RESULT: " + statusMessage + "(new part id is " + partId + ")", ""));

            } else {
                statusMessage = "part saved failed. part name already exists";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ADD NEW PART RESULT: " + statusMessage, ""));

            }
        } catch (NumberFormatException e) {
            statusMessage = "part saved failed. invalid Lot Size format";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PLEASE ENTER AN INTEGER LOT SIZE: " + statusMessage, ""));

        }

    }

    public void saveNewFurniture() {
        try {
            System.out.println("testing 1");
            furnitureId = itemSB.AddFurniture(getFurnitureName());
            System.out.println("testing 2");
            if (furnitureId != -2l) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "NEW FURNITURE SAVED", "New Furniture Id: " + furnitureId));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "FAILED TO SAVE FURNITURE", "Furniture name already exists"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
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
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EDIT PART RESULT: " + statusMessage, ""));

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
    
     public List<String> completeText(String query) {
         
         List<Furniture> fa=itemSB.getAllFurniture();
         List<String> results = new ArrayList<String>();
        for(int i = 0; i < fa.size(); i++) {
            Furniture fb=fa.get(i);
            if(fb.getFurnitureName().toLowerCase().startsWith(query))
            results.add(fb.getFurnitureName());
        }        
        return results;
        
    }
     public List<String> completeText1(String query) {
         List<Part> pa=itemSB.getAllPart();
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < pa.size(); i++) {
            Part fb=pa.get(i);
            if(fb.getPartName().toLowerCase().startsWith(query))
            results.add(fb.getPartName());
        }
        Collections.sort(results);
        System.out.println(results);
         
        return results;
    }
     
     
        public void addBom1(ActionEvent event) {
        System.out.println("test from addBom1 in BOMManagedBean.java");
        System.out.println(partName);
        System.out.println(furnitureName);
        System.out.println(quantity);
        try {
            System.out.println("test for addbom() from BOMManagedBean");
            bomId = itemSB.createBom(furnitureName, partName, Integer.valueOf(quantity));
            if (bomId != -2l && bomId != -3l) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BOM SAVED SUCCESSFULLY", ""));
            } else if (bomId == -2l) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FAILED TO SAVE BOM", "Name does not exist"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "FAILED TO SAVE BOM", "BOM already exists"));
            }

        } catch (NumberFormatException e) {
            statusMessage = "BOM create failed. invalid quantity format";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "FAILED TO CREATE BOM", "Invalid quantity format. Integer expected"));
        }
          System.out.println("test for method search bom ()1 manageBean");
        bomList.clear();
        System.out.println("test for method searchbom()2 in BOMManagedBean");

        bomList = itemSB.getFurnitureBom(furnitureName);
        System.out.println("test for method searchbom()3 BOMManagedBean");
        System.out.println(bomList);

    }
     

}
