/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Front;
import entity.Furniture;
import entity.ReturnGoods;
import entity.Store;
import entity.Warehouse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import session.stateless.LogSessionLocal;
import session.stateless.WarehouseSessionBeanLocal;

/**
 *
 * @author wangyan
 */
@Named(value = "warehouseManageBean")
@ViewScoped
public class warehouseManageBean implements Serializable {

    @EJB
    private WarehouseSessionBeanLocal warehouseSB;
    @EJB
    private LogSessionLocal logSessionLocal;
    
    @PostConstruct
    public void init() {
        warehouseList = warehouseSB.getAllWarehouse();
    }
    
    private String storeName;
    private String storeAddress;
    private String storeContact;
    private Long storeId;
    private String statusMessage;
    private List<Store> storeList = new ArrayList<>();
    private List<Store> frontbackList = new ArrayList<>();
    private String frontName;
    private String backName;
    private Long warehouseId;
    private Long frontId;
    private List<Warehouse> warehouseList = new ArrayList<>();
    private List<Front> frontList = new ArrayList<>();
    private String materialName;
    private String materialQuantity;
    private List<Furniture> materialList = new ArrayList<>();
    private Long materialId;
    private String safetyQuantity;
    private String goodsName;
    private String goodsQuantity;

    private Boolean viewAllStores;
    private Boolean viewDeleteStores;
    private List<ReturnGoods> rgList = new ArrayList<>();
    private List<String> materialNameList = new ArrayList<>();
    private String returnReason;
    private List<String>returnReasonList = new ArrayList<>();
    private Boolean checkcheck;
    private String materialPosition;
    private List<String>materialPositionList = new ArrayList<>();
    public warehouseManageBean() {
        viewAllStores = false;
        viewDeleteStores = false;
        checkcheck = false;
    }

    public Boolean getCheckcheck() {
        return checkcheck;
    }

    public void setCheckcheck(Boolean checkcheck) {
        this.checkcheck = checkcheck;
    }

    public String getMaterialPosition() {
        return materialPosition;
    }

    public void setMaterialPosition(String materialPosition) {
        this.materialPosition = materialPosition;
    }

    public List<String> getMaterialPositionList() {
        materialPositionList.clear();
        materialPositionList.add("BIG");
        materialPositionList.add("Shelf_Postion");
        return materialPositionList;
    }

    public void setMaterialPositionList(List<String> materialPostionList) {
        this.materialPositionList = materialPostionList;
    }
    

    //   private List<String> abc = new ArrayList<>();
    //   private List<String> cba = new ArrayList<>();
/*
     public List<String> getCba() {
     List<Store> l1 = warehouseSB.getAllStore();
     for (Object o : l1) {
     Store f = (Store) o;
     cba.add(f.getStoreName());
     }
     return cba;
     }
     public void setCba(List<String> cba) {
     this.cba = cba;
     }
     */
    
    public List<String> getMaterialNameList() {
        materialNameList.clear();
       List<Furniture> materialListname = warehouseSB.getAllMaterial();
        for (Object o : materialListname) {
            Furniture m = (Furniture) o;
            materialNameList.add(m.getFurnitureName());
        }
      //  materialList.clear();

        return materialNameList;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public List<String> getReturnReasonList() {
        returnReasonList.clear();
        returnReasonList.add("EXCHANGED");
        returnReasonList.add("DAMAGED");
        returnReasonList.add("Others");
        
        return returnReasonList;
    }

    public void setReturnReasonList(List<String> returnReasonList) {
        this.returnReasonList = returnReasonList;
    }

    public void setMaterialNameList(List<String> materialNameList) {
        this.materialNameList = materialNameList;
    }

    public List<ReturnGoods> getRgList() {
        return rgList;
    }

    public void setRgList(List<ReturnGoods> rgList) {
        this.rgList = rgList;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(String goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public String getSafetyQuantity() {
        return safetyQuantity;
    }

    public void setSafetyQuantity(String safetyQuantity) {
        this.safetyQuantity = safetyQuantity;
    }

    public Long getFrontId() {
        return frontId;
    }

    public void setFrontId(Long frontId) {
        this.frontId = frontId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public List<Furniture> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Furniture> materialList) {
        this.materialList = materialList;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialQuantity() {
        return materialQuantity;
    }

    public void setMaterialQuantity(String materialQuantity) {
        this.materialQuantity = materialQuantity;
    }

    public List<Store> getFrontbackList() {
        return frontbackList;
    }

    public void setFrontbackList(List<Store> frontbackList) {
        this.frontbackList = frontbackList;
    }

    public List<Warehouse> getWarehouseList() {
        return warehouseList;
    }

    public void setWarehouseList(List<Warehouse> warehouseList) {
        this.warehouseList = warehouseList;
    }

    public List<Front> getFrontList() {
        return frontList;
    }

    public void setFrontList(List<Front> frontList) {
        this.frontList = frontList;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getFrontName() {
        return frontName;
    }

    public void setFrontName(String frontName) {
        this.frontName = frontName;
    }

    public String getBackName() {
        return backName;
    }

    public void setBackName(String backName) {
        this.backName = backName;
    }

    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreContact() {
        return storeContact;
    }

    public void setStoreContact(String storeContact) {
        this.storeContact = storeContact;
    }
    /*
     public void saveNewStore(ActionEvent event) {
     try {
     System.out.println("testing 1");
     storeId = warehouseSB.AddStore(storeName, storeAddress, storeContact,backName,frontName);
            
            
            
     System.out.println("testing 2");
     if (storeId != -2l) {
     statusMessage = "New Store saved successfully";
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ADD NEW Store RESULT: " + statusMessage + "(new store id is " + storeId + ")", ""));

     } else {
     statusMessage = "Store saved failed. store already exists";
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ADD NEW Store RESULT: " + statusMessage, ""));

     }

     } catch (Exception ex) {
     ex.printStackTrace();
     }
     }
     */
    public void saveNewMaterial(ActionEvent event){
       materialId = warehouseSB.AddMaterial(materialName,materialPosition);
       if (materialId==-2l){
           statusMessage = "save unsuccessfully , material existed in database already";
           FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,statusMessage,""));
       }else if(materialId==-1l){
            statusMessage = "save unsuccessfully,warehouse does not exist";
           FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,statusMessage,""));
     
       }else{
            statusMessage = "save successfully";
           FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,statusMessage,""));
     
       }
        
       
        
    }
    public void saveNewWarehouse(ActionEvent event) {
        try {
            warehouseId = warehouseSB.AddWarehouse(backName, frontName);
            if (warehouseId > 0) {
                statusMessage = "New Warehouse saved successfully";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, statusMessage, ""));

            } else {
                statusMessage = "Warehouse saved failed. warehouse already exists";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, statusMessage, ""));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void searchStore(ActionEvent event) {

        System.out.println("test for method searchStore()1 in warehousemanageBean");
        storeList.clear();
        System.out.println("test for method vsearchStore()2 in warehousemanageBean");

        storeList = warehouseSB.getStore(storeName);
        if (storeList.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No such Store ", "The Store you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        System.out.println("test for method searchStore()3 in warehousemanageBean");
    }

 

    public void searchFront(ActionEvent event) {

        //  System.out.println("test for method searchFront()1 in warehousemanageBean");
        frontList.clear();
        // System.out.println("test for method vsearchWarehouse()2 in warehousemanageBean");

        frontList = warehouseSB.getWarehouseFront(frontName);
        if (frontList.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No such Front ", "The front you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        //System.out.println("test for method searchWarehouse()3 in warehousemanageBean");
    }
   
    public void searchBack(ActionEvent event) {
        materialList.clear();
        materialList = warehouseSB.getWarehouseMaterialList(backName);
        if (materialList.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No Material result ", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void viewAllStore(ActionEvent event) {
        System.out.println("test for method viewStore()1 in warehousemanageBean");
        viewAllStores = true;
        viewDeleteStores = false;
        storeList.clear();

        storeList = warehouseSB.getAllStore();
        if (storeList.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No such Store ", "The Store you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void viewAllWarehouse(ActionEvent event) {
        System.out.println("test for method viewAllWarehouse()1 in warehousemanageBean");

        warehouseList.clear();

        warehouseList = warehouseSB.getAllWarehouse();
        if (warehouseList.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No Warehouse ", "No result");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void viewDeleteStore(ActionEvent event) {
        System.out.println("test for method viewStore()1 in warehousemanageBean");
        viewDeleteStores = true;
        viewAllStores = false;
        storeList.clear();

        storeList = warehouseSB.getDeleteStore();
        if (storeList.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No such Store ", "The Store you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }



   
    public void viewAllFront(ActionEvent event) {
        System.out.println("test for method viewfront()1 in warehousemanageBean");
        materialList.clear();

        materialList = warehouseSB.getAllFrontMaterialList();
        if (materialList.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No result ", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }
   
    public void viewAllBack(ActionEvent event) {
        System.out.println("test for method viewback()1 in warehousemanageBean");
        materialList.clear();

        materialList = warehouseSB.getAllWarehouseMaterialList();
        if (materialList.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No result ", "no warehosue exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            for (Object o : materialList) {
                Furniture m = (Furniture) o;
                if (m.getBackQuantity() <= m.getSafetyStock() && m.getBackQuantity() > 0) {
                    FacesMessage msg;
                    msg = new FacesMessage(FacesMessage.SEVERITY_WARN, m.getFurnitureName() + " lower than safety stock ", "");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else if (m.getBackQuantity() == 0) {
                    FacesMessage msg;
                    msg = new FacesMessage(FacesMessage.SEVERITY_WARN, m.getFurnitureName() + " ZERO stock in warehosue ", "");
                    FacesContext.getCurrentInstance().addMessage(null, msg);

                }
            }
        }

    }

 

 


    public void onCellEdit4(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        System.out.println("test from warehousemanagebean: " + event.getColumn().getHeaderText());

        System.out.println("component-->" + event.getComponent());
        System.out.println(event.getSource());
        DataTable s = (DataTable) event.getSource();
        System.out.println(s.getVar());

        String head = event.getColumn().getHeaderText();

        //  bomId = (Long) event.getComponent().getAttributes().get("bomId");--cannot be used to trace var in datablae
        //use facescontext to retrive the var in databale
        FacesContext context = FacesContext.getCurrentInstance();
        Furniture data = context.getApplication().evaluateExpressionGet(context, "#{fb}", Furniture.class);
        materialId = data.getId();
        System.out.println("testing " + materialId);
       

        if (head.equals("Material Name")) {
            System.out.println("test storename from oncelledit4() ");
            //    setPartName((String)newValue);
            warehouseSB.changeMaterialName((String) newValue, materialId);

        } else if (head.equals("Quantity")) {

            System.out.println(materialId);
            try {
                long ln = warehouseSB.changeMaterialQuantity11((Integer) newValue, materialId);
                if (ln == 1l) {
                    statusMessage = "Zero Stock ";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "PLEASE ADD NEW INVENTORY :" + statusMessage, ""));

                } else if (ln == 2) {
                    statusMessage = "Lower than safety stock!";

                    //////////!!!!!!!!!!!!!!!!!!!!!!
                    data.setBackQuantity((Integer) oldValue);

                    //////////!!!!!!!!!!!!!!!!!!!!!!!
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "PLEASE ADD NEW INVENTORY :" + statusMessage, ""));

                }

            } catch (NumberFormatException e) {
                statusMessage = "quantity change failed. invalid quantity format";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "PLEASE ENTER AN INTEGER FOR QUANTITY: " + statusMessage, ""));

            }
           

        }

    }

    public void onCellEdit44(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        try {
            /* if (newValue != null && !newValue.equals(oldValue)) {
             FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
             FacesContext.getCurrentInstance().addMessage(null, msg);
             }
             */
            System.out.println("test from warehousemanagebean: " + event.getColumn().getHeaderText());

            System.out.println("component-->" + event.getComponent());
            System.out.println(event.getSource());
            DataTable s = (DataTable) event.getSource();
            System.out.println(s.getVar());

            String head = event.getColumn().getHeaderText();

            //  bomId = (Long) event.getComponent().getAttributes().get("bomId");--cannot be used to trace var in datablae
            //use facescontext to retrive the var in databale
            FacesContext context = FacesContext.getCurrentInstance();
            Furniture data = context.getApplication().evaluateExpressionGet(context, "#{fb}", Furniture.class);
            materialId = data.getId();
            System.out.println("testing " + materialId);
            /*   if(head.equals("Furniture")) { 
             itemSB.editFurniture3((String)oldValue, (String)newValue);
             }
      
      
             else  */

            if (head.equals("Material Name")) {
                System.out.println("test storename from oncelledit4() ");
                //    setPartName((String)newValue);
                warehouseSB.changeMaterialName((String) newValue, materialId);

            } else if (head.equals("Quantity")) {

                System.out.println(materialId);
                try {
                    long m = warehouseSB.changeMaterialQuantityImortant((Integer) oldValue, (Integer) newValue, materialId);
                    if (m == -1l) {
                        statusMessage = "quantity change failed. warehouse does not have enough materials ";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "PLEASE ENTER AN INTEGER FOR QUANTITY: " + statusMessage, ""));

                    } else if (m == 1l) {
                        statusMessage = "inventory lower than safety stock . warehouse does not have enough materials ";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "" + statusMessage, ""));

                    } else if (m == 2l) {
                        statusMessage = "ZERO inventory !warehouse does not have enough materials ";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "" + statusMessage, ""));

                    } else {

                        statusMessage = "quantity change successfully ";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "" + statusMessage, ""));

                    }
                } catch (NumberFormatException e) {
                    statusMessage = "quantity change failed. invalid quantity format";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "PLEASE ENTER AN INTEGER FOR QUANTITY: " + statusMessage, ""));

                }
                //   setQuantity((String)newValue);
                // need to be edit later

            }

        } catch (Exception ex) {
            statusMessage = "quantity change failed. invalid quantity format";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "PLEASE ENTER AN INTEGER FOR QUANTITY: " + statusMessage, ""));

        }

    }

    public void onCellEdit31(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        String head = event.getColumn().getHeaderText();

        //  bomId = (Long) event.getComponent().getAttributes().get("bomId");--cannot be used to trace var in datablae
        //use facescontext to retrive the var in databale
        FacesContext context = FacesContext.getCurrentInstance();
        Furniture data = context.getApplication().evaluateExpressionGet(context, "#{material}", Furniture.class);
        materialId = data.getId();

        if (head.equals("Material name")) {

            warehouseSB.changeMaterialName((String) newValue, materialId);

        } else if (head.equals("Front material amount")) {

            warehouseSB.changeMaterialQuantity((Integer) newValue, materialId);

        }

    }

    public void onCellEditRG(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        String head = event.getColumn().getHeaderText();

        //  bomId = (Long) event.getComponent().getAttributes().get("bomId");--cannot be used to trace var in datablae
        //use facescontext to retrive the var in databale
        FacesContext context = FacesContext.getCurrentInstance();
        ReturnGoods data = context.getApplication().evaluateExpressionGet(context, "#{rg}", ReturnGoods.class);
        materialId = data.getId();

        if (head.equals("Return Goods name")) {

            warehouseSB.changeRGName((String) newValue, materialId);

        } else if (head.equals("Amount")) {

            warehouseSB.changeRGQuantity((Integer) newValue, materialId);

        } else if (head.equals("Position")) {
            warehouseSB.changeRGPosition((String) newValue, materialId);
        }

    }

    public void onCellEdit32(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        String head = event.getColumn().getHeaderText();

        //  bomId = (Long) event.getComponent().getAttributes().get("bomId");--cannot be used to trace var in datablae
        //use facescontext to retrive the var in databale
        FacesContext context = FacesContext.getCurrentInstance();
        Furniture data = context.getApplication().evaluateExpressionGet(context, "#{material}", Furniture.class);
        materialId = data.getId();
        System.out.println("testing " + materialId);

        if (head.equals("Material name")) {

            warehouseSB.changeMaterialName((String) newValue, materialId);

        } else if (head.equals("Warehouse material amount")) {

            long l = warehouseSB.changeMaterialQuantity1((Integer) newValue, materialId);
            if (l == -1l) {
                /////////!!!!!!!!!!!!!!!!!!!!!!
                data.setBackQuantity((Integer) oldValue);

                //////////!!!!!!!!!!!!!!!!!!!!!!!
            }

        }

    }

    public void deleteStore(ActionEvent event) {

        System.out.println("test deleteStore() from warehousemanagebean");
        storeId = (Long) event.getComponent().getAttributes().get("bomId");
        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(this.storeName, "Deleted Store  ", cal);

        try {
            System.out.println("test deleteStore() from storemanagebean" + storeId);
            warehouseSB.DeleteStore(storeId);

            statusMessage = "Store delete successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "DELETE Store RESULT: " + statusMessage + " ==>(Deleted Store id is " + storeId + ")", ""));
            storeList.clear();
            storeList = warehouseSB.getAllStore();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void deleteWarehouse(ActionEvent event) {

        System.out.println("test deleteWarehouse() from warehousemanagebean");
        warehouseId = (Long) event.getComponent().getAttributes().get("warehouseId");
         System.out.println("test deleteWarehouse() from warehousemanagebean: warehosueId:"+warehouseId);

        try {
            System.out.println("test deleteWarehouse() from warehosuemanagebean" + warehouseId);
            warehouseSB.DeleteWarehouse(warehouseId);

            statusMessage = "Warehouse deleted successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "" + statusMessage, ""));
            warehouseList.clear();
            warehouseList = warehouseSB.getAllWarehouse();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void deleteRG(ActionEvent event) {
        checkcheck=false;
        Long rgId;
        System.out.println("test deleteRG() from warehousemanagebean");
        rgId = (Long) event.getComponent().getAttributes().get("RGId");
        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(this.storeName, "Deleted Return Goods  ", cal);

        try {

            warehouseSB.DeleteRG(rgId);

            statusMessage = "Return goods delete successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, statusMessage, ""));
            rgList.clear();
            rgList = warehouseSB.getAllRG();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void soldRG(ActionEvent event) {
        checkcheck=false;
        Long rgId;
        System.out.println("test soldRG() from warehousemanagebean");
        rgId = (Long) event.getComponent().getAttributes().get("RGId");
        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(this.storeName, "sold Return Goods  ", cal);

        try {

           Long l= warehouseSB.SoldRG(rgId);
          if(l==1)
          { statusMessage = "Status change successfully successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, statusMessage, ""));
            rgList.clear();
            rgList = warehouseSB.getAllRG();}
          else{
             statusMessage = "Goods has been disposed,cannot be changed to sold ";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, statusMessage, ""));
             
          }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void disposeRG(ActionEvent event) {
        checkcheck=false;
        Long rgId;
        System.out.println("test disposeRG() from warehousemanagebean");
        rgId = (Long) event.getComponent().getAttributes().get("RGId");
        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(this.storeName, "dispose Return Goods  ", cal);

        try {

            Long l = warehouseSB.DisposeRG(rgId);
           if(l==2){
            statusMessage = "Status changed successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, statusMessage, ""));
            rgList.clear();
            rgList = warehouseSB.getAllRG();}
           else if(l==-1l){
             statusMessage = "Goods has been sold ,can not change a sold goods status to disposed";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, statusMessage, ""));
             
           }else{
                statusMessage = "Status changed faild, GOODS HAS BEEN DELETED";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, statusMessage, ""));
          
           }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void enableStore(ActionEvent event) {

        System.out.println("test enableStore() from warehousemanagebean");
        storeId = (Long) event.getComponent().getAttributes().get("bomId");
        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(this.storeName, "Enable Store  ", cal);

        try {
            System.out.println("test deleteStore() from storemanagebean" + storeId);
            warehouseSB.EnableStore(storeId);

            statusMessage = "Store enable successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Enable Store RESULT: " + statusMessage + " ==>(Deleted Store id is " + storeId + ")", ""));
            storeList.clear();
            storeList = warehouseSB.getDeleteStore();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public List<String> completeText(String query) {

        List<Store> fa = warehouseSB.getAllStore();
        //  fa.clear();

        List<String> results = new ArrayList<String>();
        results.clear();
        for (int i = 0; i < fa.size(); i++) {
            Store fb = fa.get(i);
            if (fb.getStoreName().startsWith(query)) {
                results.add(fb.getStoreName());
            }
        }

        return results;
    }

    public List<String> completeTextMaterial(String query) {

        List<Furniture> ml = warehouseSB.getAllMaterial();

        List<String> results = new ArrayList<>();
        results.clear();
        for (int i = 0; i < ml.size(); i++) {
            Furniture fb = ml.get(i);
            if ((fb.getFurnitureName().toLowerCase()).startsWith(query.toLowerCase())) {
                results.add(fb.getFurnitureName());
            }
        }

        return results;
    }

    public List<String> completeText1(String query) {
        List<Store> fa = warehouseSB.getAllStore();
        //  fa.clear();
        List<String> results = new ArrayList<String>();
        results.clear();
        for (int i = 0; i < fa.size(); i++) {
            Store fb = fa.get(i);
            if (fb.getStoreAddress().startsWith(query)) {
                results.add(fb.getStoreAddress());
            }
        }

        return results;
    }

   
   

    public void addwarehousematerial(ActionEvent event) {

        try {
            if(Integer.valueOf(materialQuantity)<0){
                statusMessage = "material added unsuccessfully. Quantity is less than 0";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, statusMessage, ""));
 
            }else{

            storeId = warehouseSB.addwarehouseMaterialMaterial(materialName, Integer.valueOf(materialQuantity));

            if (storeId == 0) {
                statusMessage = "material added successfully. please  add new material if any";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, statusMessage, ""));

                materialList.clear();

                materialList = warehouseSB.getAllWarehouseMaterialList();
                for (Object o : materialList) {
                    Furniture m = (Furniture) o;
                    if (m.getBackQuantity() <= m.getSafetyStock() && m.getBackQuantity() > 0) {
                        statusMessage = "Material:" + m.getFurnitureName() + " is lower than safety stock";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, statusMessage, ""));

                    } else if (m.getBackQuantity() == 0) {
                        statusMessage = "Material:" + m.getFurnitureName() + " with ZERO stock";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, statusMessage, ""));

                    }
                }

            }else if(storeId>0){
               statusMessage = "material cannot be totoall added to warehouse. "+storeId+" "+materialName+" cannot be add to warehouse";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, statusMessage, ""));
 
            } 
            else if (storeId == -2l) {
                statusMessage = "material created failed. Material does not exist";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, statusMessage, ""));

            } else if (storeId == -1l) {
                statusMessage = " warehouse not exists, please create warehouse for that store before add material";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, statusMessage, ""));

            } else if (storeId == -3l) {
                statusMessage = "the quantity you enter for this material is over the storage";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, statusMessage, ""));

            }

        }
        }catch (NumberFormatException e) {
            statusMessage = "materail added failed. invalid quantity format";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "PLEASE ENTER AN INTEGER FOR QUANTITY: " + statusMessage, ""));
        }
        

    }
    

    public void addfrontmaterial(ActionEvent event) {
        System.out.println("test from warehouseManageBean: addfrontmaterial");
         
        try {
            if (materialQuantity == null) {
                materialQuantity = "0";
            }
            if(Integer.valueOf(materialQuantity)<0){
                 statusMessage = "illegal action; you can not inbound negative integer from Warehouse to front store ";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, statusMessage, ""));

            }else{

            storeId = warehouseSB.createFrontMaterial2(materialName, Integer.valueOf(materialQuantity));

            System.out.println("test from addwarehouseMaterail in warehousemanagebean.java: storeId" + storeId);

            if (storeId == 2l) {
                statusMessage = "material added successfully. please create add new material if any";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, statusMessage, ""));

                materialList.clear();

                materialList = warehouseSB.getAllWarehouseMaterialList();
                for (Object o : materialList) {
                    Furniture m = (Furniture) o;
                    if (m.getBackQuantity() <= m.getSafetyStock() && m.getBackQuantity() > 0) {
                        statusMessage = "Material:" + m.getFurnitureName() + " lower than safety stock in Warehouse";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, statusMessage, ""));

                    } else if (m.getBackQuantity() == 0) {
                        statusMessage = "Material:" + m.getFurnitureName() + " with ZERO stock in Warehouse";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, statusMessage, ""));

                    }
                }

            } else if (storeId == 1l) {
                statusMessage = " action failed. front not exists, please create front for that store before add material";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, statusMessage, ""));

            } else {
                statusMessage = " action failed. warehouse does not have enough material now,available material amount in warehouse is: " + (-storeId);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, statusMessage, ""));

            }
        } }catch (NumberFormatException e) {
            statusMessage = "materail added failed. invalid quantity format";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "PLEASE ENTER AN INTEGER FOR QUANTITY: " + statusMessage, ""));
        }

    }

   
    public void addSafetyMaterial(ActionEvent event) {
        try {
            if (safetyQuantity == null) {
                statusMessage = " No Quantity,please enter a quantity for safety level";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, statusMessage, ""));

            } else {
                System.out.println("testing 1");

                storeId = warehouseSB.addSafety(materialName, Integer.valueOf(safetyQuantity));

                System.out.println("testing 2");
                if (storeId != -2l) {
                    statusMessage = " Saved successfully";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, statusMessage, ""));
                    materialList.clear();

                    materialList = warehouseSB.getAllWarehouseMaterialList();

                } else {
                    statusMessage = "Saved failed. Material does not exists";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, " RESULT: " + statusMessage, ""));

                }

            }
        } catch (NumberFormatException e) {
            statusMessage = " saved failed. invalid quantity format";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "PLEASE ENTER AN INTEGER FOR QUANTITY: " + statusMessage, ""));
        }
    }

    public void addReturnGoods(ActionEvent event) {
        try {
            System.out.println("testing 1");

            storeId = warehouseSB.addReturn(goodsName, Integer.valueOf(goodsQuantity),returnReason);

            System.out.println("testing 2");
            if (storeId != -2l) {
                statusMessage = " Saved successfully";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, statusMessage, ""));
                rgList.clear();

                rgList = warehouseSB.getAllRG();

            } else {
                statusMessage = "Saved failed.  warehouse not exist,please create warehouse before you add Return Goods";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, " RESULT: " + statusMessage, ""));

            }

        } catch (NumberFormatException e) {
            statusMessage = " saved failed. invalid quantity format";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "PLEASE ENTER AN INTEGER FOR QUANTITY: " + statusMessage, ""));
        }
    }

   

    public Boolean getViewAllStores() {
        return viewAllStores;
    }

    public void setViewAllStores(Boolean viewAllStores) {
        this.viewAllStores = viewAllStores;
    }

    public Boolean getViewDeleteStores() {
        return viewDeleteStores;
    }

    public void setViewDeleteStores(Boolean viewDeleteStores) {
        this.viewDeleteStores = viewDeleteStores;
    }

    public void searchfrontmaterial(ActionEvent event) {
        materialList.clear();
        materialList = warehouseSB.searchFrontMaterial(materialName);
        if (materialList.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No result ", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }
    
    public void searchWarehousematerial(ActionEvent event) {
        materialList.clear();
        System.out.println("testing from searchWarehouseMaterial: materialName:"+materialName+materialList.toString());
        
        materialList = warehouseSB.searchBackMaterial(materialName);
        for(Object o:materialList){
           Furniture m = (Furniture)o;
            System.out.println(m);
        }
         System.out.println("testing from searchWarehouseMaterial: materialName:"+materialName+materialList.toString());
        
        if (materialList.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No result ", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }


    public void searchReturnGoods(ActionEvent event) {
        rgList.clear();
        rgList = warehouseSB.searchReturnGoods(goodsName,returnReason);
        if (rgList.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No result ", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void viewSafetyLevel(ActionEvent event) {
        materialList.clear();
        materialList = warehouseSB.getAllWarehouseMaterialList();
        if (materialList.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No result ", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void viewAllRG(ActionEvent event) {
        System.out.println("test for method viewwarehosue()1 in warehousemanageBean");
        rgList.clear();

        rgList = warehouseSB.getAllRG();
        if (rgList.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No result ", "no warehouse exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit successfully");
        Warehouse ware = ((Warehouse) event.getObject());
        warehouseSB.editWarehouse(ware);
        System.out.println("testing : wareName==" + ware.getWarehouseName());
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onRowEditCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowEdit1(RowEditEvent event) {
         Furniture ware = ((Furniture) event.getObject());
       long rest= warehouseSB.editMaterial(ware);
        materialList.clear();

        materialList = warehouseSB.getAllWarehouseMaterialList();
        if(rest<0){
           
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Material quantity is too much, "+(-rest)+" amount cannot be add to the warehouse", "");
         FacesContext.getCurrentInstance().addMessage(null, msg);
       }else{
             FacesMessage msg = new FacesMessage("Edit successfully");
         FacesContext.getCurrentInstance().addMessage(null, msg);
        }
       
       
       // FacesContext.getCurrentInstance().addMessage(null, msg);

    }
    public void onRowEdit31(RowEditEvent event){
      FacesMessage msg = new FacesMessage("Edit successfully");
         FacesContext.getCurrentInstance().addMessage(null, msg);
       Furniture ware = (Furniture)event.getObject();
       warehouseSB.editMaterial2Front(ware);
       
    }
    public void onRowEditRG(RowEditEvent event) {
        //checkcheck=true;
        FacesMessage msg = new FacesMessage("Edit successfully");
         FacesContext.getCurrentInstance().addMessage(null, msg);
        ReturnGoods ware = ((ReturnGoods) event.getObject());
        warehouseSB.editRG(ware);
       
       // FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onRowEditCancel1(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      //  Material ware = ((Material) event.getObject());
      //  warehouseSB.editMaterial(ware);
    }
     public void onRowCancelRG(RowEditEvent event) {
       //  checkcheck=true;
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    //    Material ware = ((Material) event.getObject());
     //   warehouseSB.editMaterial(ware);
    }

}
