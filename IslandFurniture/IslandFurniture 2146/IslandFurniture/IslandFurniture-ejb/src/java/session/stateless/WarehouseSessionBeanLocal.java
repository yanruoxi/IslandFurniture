/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Front;
import entity.Furniture;
//import entity.Material;
import entity.ReturnGoods;
import entity.Store;
import entity.Warehouse;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wangyan
 */
@Local
public interface WarehouseSessionBeanLocal {

    public List<Furniture> getWarehouseMaterialList(String backName);

    public Long AddWarehouse(String backName, String frontName);
    public Long AddMaterial(String materialName,String materialPostion);
    public List<Furniture> getAllWarehouseMaterialList();

    public long changeMaterialQuantity1(Integer newValue, Long materialId);

    public void changeMaterialName(String newValue, Long materialId);

    public List<Furniture> getAllFrontMaterialList();

    public List<Furniture> getAllMaterial();
// this method is used for looking for localtion,but i cannot find a correct way to do it!
  //  public Long addWarehouseMaterial(String materialName, Integer materialQuantity);
    
    public Long addWarehouseMaterials(String materialName, Integer materialQuantity);
    public Long addwarehouseMaterialMaterial(String materialName, Integer materialQuantity);
   public List<Furniture> searchFrontMaterial(String materialName);
    public List<Furniture> searchBackMaterial(String materialName);
   public long createFrontMaterial2(String materialName, Integer meterailQuantity) ;
     public Long addReturn(String goodsName, Integer goodsQuantity,String returnReason) ;
    public List<ReturnGoods> searchReturnGoods(String goodsName,String returnReason);
     public List<ReturnGoods> getAllRG();
    public void DeleteRG(Long rgId);
    public Long SoldRG(Long rgId);
    public Long DisposeRG(Long rgId);
    public void changeRGName(String newValue, Long rgId);
    public void changeRGQuantity(Integer newValue,Long rgId);
     public void changeRGPosition(String newValue,Long rgId);
    public void DeleteWarehouse(Long warehouseId);
   public void editWarehouse(Warehouse ware);
   public long editMaterial(Furniture ware);
    public void editMaterial2Front(Furniture ware) ;
  public void editRG(ReturnGoods ware);
  
   
    
    

   // public Long AddStore(String storeName, String storeAddress,String storeContact,String backName,String frontName);
    public List<Store> getStore(String storeName);

    public List<Store> getAllStore();

    public List<Store> getDeleteStore();

//    public Warehouse getWarehouse(String storeName);

    public List<Warehouse> getAllWarehouse();

    public List<Warehouse> getWarehousestock(String backName);

    public List<Front> getWarehouseFront(String frontName);

    public List<Front> getAllFront();

    public void changeStoreName(String newValue, Long storeId);

    public void changeStoreAddress(String newValue, Long storeId);

    public void changeStoreContact(String newValue, Long storeId);

  //  public void changeStoreWarehouse(String newValue, Long storeId);

  //  public void changeStoreStock(String newValue, Long storeId);

    // public void changeMaterialName(String newValue,Long materialId);

    public void changeMaterialQuantity(Integer newValue, Long materialId);

    //public void changeMaterialQuantity1(Integer newValue,Long materialId);
    public long changeMaterialQuantity11(Integer newValue, Long materialId);

    public long changeMaterialQuantityImortant(Integer oldValue, Integer newValue, Long materialId);

    public void DeleteStore(Long storeId);

    public void EnableStore(Long storeId);

  //  public long createWarehouse(String storeName, String frontName, String backName);

    //  public long createWarehouseMaterial(String storeName, String materialName, Integer meterailQuantity);

   // public long createWarehouseMaterial1(String storeName, String materialName, Integer meterailQuantity);

   // public long createFrontMaterial1(String storeName, String materialName, Integer meterailQuantity);

   // public List<Warehouse> getBack(Long storeId);

   // public List<Front> getFront(Long storeId);

   // public List<Material> getMaterialList(Long storeId);

  //  public List<Material> getMaterialList2(Long storeId);

   // public List<Material> getMaterialList(String storeName);

    public Long addSafety(String materialName, Integer safetyQuantity);

   // public Long addSafety2(String storeName, String materialName, Integer safetyQuantity);

   // public Long addReturn(String storeName, String goodsName);
}
