/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Front;
import entity.Furniture;
import entity.ReturnGoods;
import entity.Store;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author WangYan
 */
@Stateless
public class WarehouseSessionBean implements WarehouseSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    private Store store;
    private Warehouse warehouse;
    private Front front;
    /*
     @Override
     public Long AddStore(String storeName, String storeAddress, String storeContact, String backName, String frontName) {
     System.out.println("store test");
     Query q = em.createQuery("select s from Store s where s.storeName=:sName ");
     System.out.println("store test1");
     q.setParameter("sName", storeName);

     System.out.println("store test2");
     if (q.getResultList().isEmpty()) {
     Store store1 = new Store();
     store1.setStoreAddress(storeAddress);
     store1.setStoreContact(storeContact);
     store1.setStoreName(storeName);
     warehouse = new Warehouse();
     warehouse.setWarehouseName(backName);
     em.persist(warehouse);
     store1.setWarehouse(warehouse);
     front = new Front();
     front.setFrontName(frontName);
     em.persist(front);
     store1.setFront(front);
     em.persist(store1);
     front.setStore(store1);
     em.persist(front);
     warehouse.setStore(store1);
     em.persist(warehouse);
     em.flush();
     //return store Id;
     return store1.getId();
     } else {
     return -2l;
     }

     }
     */

    @Override
    public Long AddWarehouse(String backName, String frontName) {
        Query q = em.createQuery("select w from Warehouse w ");
        // q.setParameter("wName", "backName");
        if (!q.getResultList().isEmpty()) {

            Query qqw = em.createQuery("select w from Warehouse w where w.deletema='N'");
            if (!qqw.getResultList().isEmpty()) {
                return -1l;//existing warehouse
            } else {
                warehouse = new Warehouse();
                warehouse.setWarehouseName(backName);
                front = new Front();
                front.setFrontName(frontName);
                front.setWarehouse(warehouse);
                warehouse.setFront(front);
                em.persist(front);
                em.persist(warehouse);
                return warehouse.getId();
            }

        } else {
            warehouse = new Warehouse();
            warehouse.setWarehouseName(backName);
            front = new Front();
            front.setFrontName(frontName);
            front.setWarehouse(warehouse);
            warehouse.setFront(front);
            em.persist(front);
            em.persist(warehouse);
            return warehouse.getId();
        }
    }

    @Override
    public Long AddMaterial(String materialName, String materialPostion) {
        Query q = em.createQuery("select w from Warehouse w ");
        if (q.getResultList().isEmpty()) {
            return -1l;
        } else {
            q = em.createQuery("select w from Warehouse w where w.deletema='N'");
            if (q.getResultList().isEmpty()) {
                return -1l;
            } else {
                Warehouse w = (Warehouse)q.getResultList().get(0);
                Front f = w.getFront();
                Query q0 = em.createQuery("select m from Furniture m");
                if (q0.getResultList().isEmpty()) {
                    Furniture m = new Furniture();
                    m.setFurnitureName(materialName);
                    m.setPosition(materialPostion);
                    m.setBackQuantity(0);
                    m.setFrontQuantity(0);
                    m.setSafetyStock(0);
                    m.setFront(f);
                    m.setWarehouse(w);
                    em.persist(m);
                    return m.getId();
                } else {
                    Query q1 = em.createQuery("select m from Furniture m where m.furnitureName=:mName");
                    q1.setParameter("mName", materialName);
                    if (q1.getResultList().isEmpty()) {
                        Furniture m = new Furniture();
                        m.setFurnitureName(materialName);
                        m.setPosition(materialPostion);
                        m.setBackQuantity(0);
                        m.setFrontQuantity(0);
                        m.setSafetyStock(0);
                         m.setFront(f);
                    m.setWarehouse(w);
                        em.persist(m);
                        return m.getId();
                    } else {
                        return -2l;//material has already exist
                    }
                }
            }
        }

    }

    @Override
    public List<Store> getStore(String storeName) {

        List<Store> s = new ArrayList<>();
        Query q = em.createQuery("select s from Store s where s.storeName=:sName and s.status='Y'");
        //  q.setParameter("st", "Y");
        q.setParameter("sName", storeName);
        if (!q.getResultList().isEmpty()) {
            s = q.getResultList();
            System.out.println("test from warehousesessionbean: first store name in store list" + s.get(0).getStoreName());
        }

        return q.getResultList();
    }
    /*
     @Override
     public Warehouse getWarehouse(String storeName) {

     List<Warehouse> s = new ArrayList<>();
     Query q = em.createQuery("select s from Store s where s.storeName=:sName");
     q.setParameter("sName", storeName);
     if (!q.getResultList().isEmpty()) {
     store = (Store) q.getResultList().get(0);
     //  System.out.println("test from warehousesessionbean: first store name in store list" + s.get(0).getWarehouseName());
     return store.getWarehouse();
     } else {
     return null;
     }

     }
     */

    @Override
    public List<Front> getWarehouseFront(String frontName) {
        List<Front> s = new ArrayList<>();
        Query q = em.createQuery("select s from Front s where s.frontName=:sName");
        q.setParameter("sName", frontName);
        if (!q.getResultList().isEmpty()) {
            s = q.getResultList();
            System.out.println("test from warehousesessionbean: first store name in store list" + s.get(0).getFrontName());
        }

        return q.getResultList();
    }

    @Override
    public List<Warehouse> getWarehousestock(String backName) {
        List<Warehouse> w = new ArrayList<>();
        Query q = em.createQuery("select h from Warehouse h where h.warehouseName=:hName");
        q.setParameter("hName", backName);
        if (!q.getResultList().isEmpty()) {
            w = q.getResultList();
        }
        return q.getResultList();

    }

    @Override
    public List<Store> getAllStore() {
        Query q = em.createQuery("select s from Store s where s.status='Y'");
        // q.setParameter("st", "Y");
        System.out.println("test from warehouseSessionBean: method-getAllStore()");
        return q.getResultList();
    }

    @Override
    public List<Store> getDeleteStore() {
        Query q = em.createQuery("select s from Store s where s.status='N'");
        // q.setParameter("st", "Y");
        System.out.println("test from warehouseSessionBean: method-getAllStore()");
        return q.getResultList();
    }

    @Override
    public List<Warehouse> getAllWarehouse() {

        Query q = em.createQuery("select w from Warehouse w");
        if (q.getResultList().isEmpty()) {
            return q.getResultList();
        } else {

            Query q2 = em.createQuery("select w from Warehouse w where w.deletema='N'");

            System.out.println("test from warehouseSessionBean: method-getAllWarehouse()");
            return q2.getResultList();
        }

    }

    @Override
    public List<Front> getAllFront() {
        Query q = em.createQuery("select s from Front s where s.store.status='Y' ");
        System.out.println("test from warehouseSessionBean: method-getAllFront()");
        return q.getResultList();
    }

    @Override
    public List<Furniture> getAllFrontMaterialList() {
        Query q0 = em.createQuery("SELECT m0 FROM Furniture m0");
        if (q0.getResultList().isEmpty()) {
            return q0.getResultList();
        } else {
            Query q = em.createQuery("select m from Furniture m where m.frontQuantity>0");
            return q.getResultList();
        }
    }

    @Override
    public void changeStoreName(String newValue, Long storeId) {
        Store b = em.find(Store.class, storeId);
        b.setStoreName(newValue);

    }

    @Override
    public void changeStoreAddress(String newValue, Long storeId) {
        Store b = em.find(Store.class, storeId);
        b.setStoreAddress(newValue);

    }

    @Override
    public void changeStoreContact(String newValue, Long storeId) {
        Store b = em.find(Store.class, storeId);
        b.setStoreContact(newValue);

    }
    /*
     @Override
     public void changeStoreWarehouse(String newValue, Long storeId) {
     Store b = em.find(Store.class, storeId);
     b.getWarehouse().setWarehouseName(newValue);
     }

     @Override
     public void changeStoreStock(String newValue, Long storeId) {
     Store b = em.find(Store.class, storeId);
     b.getFront().setFrontName(newValue);

     }
     */

    @Override
    public void changeMaterialName(String newValue, Long materialId) {

        Furniture m = em.find(Furniture.class, materialId);

        m.setFurnitureName(newValue);
    }

    @Override
    public void changeRGName(String newValue, Long rgId) {
        ReturnGoods rg = em.find(ReturnGoods.class, rgId);
        rg.setGoodsName(newValue);

    }

    @Override
    public void changeRGPosition(String newValue, Long rgId) {
        ReturnGoods rg = em.find(ReturnGoods.class, rgId);
        rg.setPosition(newValue);
    }

    /* this method is used if you want to change the front name and back end name at the same time! but it does not work if you want to change one name to another existing name !!please edit this method if you want to use it!!!! 
     @Override
     public void changeMaterialName1(String newValue, Long materialId) {
        
        
        
     Material m = em.find(Material.class, materialId);
     if(!m.getWarehouse().isEmpty()){
     Collection<Warehouse> ware = m.getWarehouse();
     for(Object o:ware){
     Warehouse wh = (Warehouse)o;
            
     Collection<Material> ware2=   wh.getStore().getFront().getMaterialCollection();
         
     for(Object o2:ware2){
     Material m2= (Material)o2;
     if(m2.getMaterialName().equals(m.getMaterialName())){
     m2.setMaterialName(newValue);
     }
                          
     }
     }
     m.setMaterialName(newValue);}
     else{
     Collection<Front> fr= m.getFront();
     for(Object i:fr){
     Front frt=(Front)i;
     Collection<Material> frt2= frt.getStore().getWarehouse().getMaterialCollection();
     for(Object i2:frt2){
     Material m3 = (Material)i2;
     if(m3.getMaterialName().equals(m.getMaterialName())){
     m3.setMaterialName(newValue);
     }
     }
     }
     m.setMaterialName(newValue);
     }

     }
     */
    @Override
    public void changeMaterialQuantity(Integer newValue, Long materialId) {
        Furniture m = em.find(Furniture.class, materialId);
        m.setFrontQuantity(newValue);
    }

    @Override
    public void changeRGQuantity(Integer newValue, Long rgId) {
        ReturnGoods rg = em.find(ReturnGoods.class, rgId);
        rg.setQuantity(newValue);
    }

    @Override
    public long changeMaterialQuantity1(Integer newValue, Long materialId) {
        Furniture m = em.find(Furniture.class, materialId);

        m.setBackQuantity(newValue);
        return 1l;

    }

    @Override
    public long changeMaterialQuantity11(Integer newValue, Long materialId) {
        Furniture m = em.find(Furniture.class, materialId);
        m.setBackQuantity(newValue);
        if (m.getBackQuantity() == 0) {
            return 1l;//zero stock;
        } else if (m.getBackQuantity() <= m.getSafetyStock()) {
            return 2l;//lower than safety level
        } else {
            return 3l;
        }
    }

    @Override
    public long changeMaterialQuantityImortant(Integer oldValue, Integer newValue, Long materialId) {
        Furniture m = em.find(Furniture.class, materialId);
        Integer difference = newValue - oldValue;
        if (m.getBackQuantity() < difference) {
            return -1l;//can not change
        } else {
            m.setBackQuantity(m.getBackQuantity() - difference);
            m.setFrontQuantity(m.getFrontQuantity() + difference);
            if (m.getBackQuantity() > 0 && m.getBackQuantity() <= m.getSafetyStock()) {
                return 1l;// lower than safety
            } else if ((m.getBackQuantity()) == 0) {
                return 2l;// o
            } else {
                return 3l;//normal
            }
        }

    }

    @Override
    public void DeleteStore(Long storeId) {
        Store s = em.find(Store.class, storeId);
        s.setStatus("N");
        em.persist(s);
        em.flush();
    }

    @Override
    public void DeleteWarehouse(Long warehouseId) {
        Warehouse w = em.find(Warehouse.class, warehouseId);
        w.setDeletema("Y");
        em.persist(w);
        em.flush();
    }

    @Override
    public Long SoldRG(Long rgId) {
        ReturnGoods s = em.find(ReturnGoods.class, rgId);
        if (s.getStatus().toLowerCase().equals("disposed")) {
            return -1l;
        } else {
            s.setStatus("SOLD");
        }
        em.persist(s);
        em.flush();
        return 1l;
    }

    @Override
    public Long DisposeRG(Long rgId) {
        ReturnGoods s = em.find(ReturnGoods.class, rgId);
        if (s.getStatus().toLowerCase().equals("sold")) {
            return -1l;//sold
        } else if (s.getStatus().toLowerCase().equals("returned")) {
            s.setStatus("DISPOSED");
            em.persist(s);
            em.flush();
            return 2l;
        } else {
            return -2l;// deleted
        }
    }

    @Override
    public void DeleteRG(Long rgId) {
        ReturnGoods rg = em.find(ReturnGoods.class, rgId);
        rg.setDeletema("Y");
        rg.setGoodsName(rg.getGoodsName() + "Deleted");
        em.persist(rg);
        em.flush();
    }

    @Override
    public void EnableStore(Long storeId) {
        Store s = em.find(Store.class, storeId);
        s.setStatus("Y");
        em.persist(s);
        em.flush();
    }
    /*

     @Override
     public long createWarehouse(String storeName, String frontName, String backName) {

     System.out.println("test create warehouse from warehsoueSessionbean");

     Query q = em.createQuery("select s from Store s where s.storeName=:sName ");
     q.setParameter("sName", storeName);

     if (q.getResultList().isEmpty()) {
     return -2l;
     } else {
     Store pc = (Store) q.getResultList().get(0);
     System.out.println("storeId:" + pc.getId());
     System.out.println("warehouse" + pc.getWarehouse());
     if ((pc.getWarehouse() == null)) {
     System.out.println("no warehouse or front store exist in store, so can create front and back");
     Warehouse bc = new Warehouse();
     bc.setWarehouseName(backName);
     em.persist(bc);
     Front f = new Front();
     f.setFrontName(frontName);
     em.persist(f);
     bc.setStore(pc);
     f.setStore(pc);

     pc.setWarehouse(bc);
     pc.setFront(f);

     return pc.getId();

     } else {
     return -3l;

     }

     }

     }
     */
    /*
     @Override
     public long createWarehouseMaterial(String storeName, String materialName, Integer meterailQuantity) {
     System.out.println("test createwarehousematerial() from warehsoueSessionbean");

     Query q = em.createQuery("select s from Store s where s.storeName=:sName ");
     q.setParameter("sName", storeName);

     if (q.getResultList().isEmpty()) {
     return -2l;
     } else {
     Store pc = (Store) q.getResultList().get(0);
     System.out.println(pc.getId());
     if ((pc.getWarehouse() == null)) {
     System.out.println("no warehouse or front store exist in store,please add warehouse before add meterial to it ");
     return -3l;//no warehouse;
     } else {
     Warehouse w = pc.getWarehouse();
     //   List<Material> materialList=new ArrayList<>();
     Collection<Material> materialCollection = new ArrayList<>();
     materialCollection.clear();
     //    materialCollection.addAll(w.getMaterialCollection());
     //    materialList = w.getMaterialCollection1();
     materialCollection = w.getMaterialCollection();
     if (materialCollection == null) {
     System.out.println("null");// this one will never be printed out!!!!!!!!!!!!!!!!!!!!why??
     }
     System.out.println("test +" + materialCollection);

     //  materialCollection.
     //    List<Material> materialList = new ArrayList<Material>();
     //    materialList.clear();
     List<Material> materialList = new ArrayList<>(materialCollection);
     if (!materialList.isEmpty()) {

     boolean check = true;

     for (Object o : materialList) {
     Material material = (Material) o;
     if (material.getMaterialName().toUpperCase().equals(materialName.toUpperCase())) {
     check = false;
     materialCollection.remove(material);
     material.setQuantity(meterailQuantity + material.getQuantity());

     materialCollection.add(material);
     break;
     }
     }
     if (check) {
     Material mmm = new Material();
     mmm.setMaterialName(materialName);
     mmm.setQuantity(meterailQuantity);
     em.persist(mmm);
     em.flush();

     materialCollection.add(mmm);
     }

     } else {
     Material mmm = new Material();
     mmm.setMaterialName(materialName);
     mmm.setQuantity(meterailQuantity);
     em.persist(mmm);
     em.flush();

     materialCollection.add(mmm);

     }
     w.setMaterialCollection(materialCollection);
     em.persist(w);
     em.flush();
     pc.setWarehouse(w);
     em.persist(pc);
     em.flush();

     }
     return pc.getId();
     }

     }
     */
    /*

     @Override
     public long createWarehouseMaterial1(String storeName, String materialName, Integer meterailQuantity) {
     Query q = em.createQuery("select s from Store s where s.storeName=:sName");
     q.setParameter("sName", storeName);
     if (q.getResultList().isEmpty()) {
     return -2l;//no store exception

     } else {
     Store storew = (Store) q.getResultList().get(0);
     Boolean check = true;
     if (storew.getWarehouse() == null) {
     System.out.println("no warehouse for this store,please add warehouse first");
     return -3l;
     } else {
     Warehouse warehouse0 = storew.getWarehouse();
     Collection<Material> materials = warehouse0.getMaterialCollection();
     for (Object o : materials) {
     Material material0 = (Material) o;
     if (material0.getMaterialName().toLowerCase().equals(materialName.toLowerCase())) {
     materials.remove(material0);
     material0.setBackQuantity(meterailQuantity + material0.getBackQuantity());
     em.persist(material0);
     materials.add(material0);
     warehouse0.setMaterialCollection(materials);

     check = false;
     break;

     }
     }
     if (check) {
     Material material1 = new Material();
     material1.setMaterialName(materialName);
     material1.setBackQuantity(meterailQuantity);
     em.persist(material1);
     System.out.println("testing here");
     // em.flush();
     materials.add(material1);
     warehouse0.setMaterialCollection(materials);
     em.persist(warehouse0);

     material1.setWarehouse(warehouse0);
     material1.setFront(storew.getFront());
     em.persist(material1);

     }

     storew.setWarehouse(warehouse0);
     return storew.getId();

     }

     }

     }

     @Override
     public long createFrontMaterial1(String storeName, String materialName, Integer meterailQuantity) {
     Boolean amount = true;
     Integer qua = 0;
     Query q = em.createQuery("select s from Store s where s.storeName=:sName");
     q.setParameter("sName", storeName);
     if (q.getResultList().isEmpty()) {
     return -2l;//no store;

     } else {
     Store storew = (Store) q.getResultList().get(0);
     Boolean check = true;
     if (storew.getFront() == null) {
     System.out.println("no Front for this store,please add front first");
     return -3l;
     } else {

     Warehouse warehouse0 = storew.getWarehouse();
     //  Front front0 = storew.getFront();
     Collection<Material> materials = warehouse0.getMaterialCollection();
     //  Collection<Material> materials1 = front0.getMaterialCollection();
     for (Object o : materials) {
     Material material0 = (Material) o;
     if (material0.getMaterialName().toLowerCase().equals(materialName.toLowerCase())) {
     if (material0.getBackQuantity() < meterailQuantity) {
     qua = material0.getBackQuantity();
     return -qua;

     } else {
     amount = false;
     materials.remove(material0);
     material0.setBackQuantity(material0.getBackQuantity() - meterailQuantity);

     material0.setFrontQuantity(meterailQuantity + material0.getFrontQuantity());
     em.persist(material0);
     materials.add(material0);
     warehouse0.setMaterialCollection(materials);
     em.persist(warehouse0);
     storew.setWarehouse(warehouse0);
     return storew.getId();

     }

     }
     }
     }
     return -2l;
     }
     }
     */

    @Override
    public long createFrontMaterial2(String materialName, Integer meterailQuantity) {
        Boolean amount = true;
        Integer qua = 0;
        Query q = em.createQuery("select f from Front f ");

        if (q.getResultList().isEmpty()) {
            return 1l;//no Front;

        } else {
            Query q1 = em.createQuery("select m from Furniture m where m.furnitureName=:mName");
            q1.setParameter("mName", materialName);
            Furniture m = (Furniture) q1.getResultList().get(0);
            if (m.getBackQuantity() < meterailQuantity) {
                return -m.getBackQuantity();
            } else {
                m.setFrontQuantity(m.getFrontQuantity() + meterailQuantity);
                m.setBackQuantity(m.getBackQuantity() - meterailQuantity);
                em.persist(m);
                Warehouse house = m.getWarehouse();
                if (m.getPosition().equals("BIG")) {
                    Furniture[][] BigArray = house.getBigArray();
                    for (int i = 9; i >= 0; i--) {
                        for (int j = 9; j >= 0; j--) {
                            if ((BigArray[i][j] != null) && (BigArray[i][j].getFurnitureName().equals(materialName))) {
                                BigArray[i][j] = null;
                                meterailQuantity--;
                            }
                            if (meterailQuantity == 0) {
                                house.setBigArray(BigArray);
                                em.persist(house);
                                break;
                            }
                        }
                        if (meterailQuantity == 0) {
                            house.setBigArray(BigArray);
                            em.persist(house);
                            break;
                        }
                    }
                } else {
                    Furniture[][][] SUArray = house.getSUArray();
                    for (int i = 9; i >= 0; i--) {
                        for (int j = 9; j >= 0; j--) {
                            for (int k = 9; k >= 0; k--) {
                                if ((SUArray[i][j][k] != null) && (SUArray[i][j][k].getFurnitureName().equals(materialName))) {
                                    SUArray[i][j][k] = null;
                                    meterailQuantity--;
                                }
                                if (meterailQuantity == 0) {
                                    house.setSUArray(SUArray);
                                    em.persist(house);
                                    break;
                                }
                            }
                            if (meterailQuantity == 0) {
                                house.setSUArray(SUArray);
                                em.persist(house);
                                break;
                            }

                        }
                        if (meterailQuantity == 0) {
                            house.setSUArray(SUArray);
                            em.persist(house);
                            break;
                        }
                    }
                }
                m.setWarehouse(house);
                m.setFront(house.getFront());
                em.persist(m);
                return 2l;
            }
        }
    }
    /*

     @Override
     public List<Warehouse> getBack(Long storeId
     ) {
     List<Warehouse> w = new ArrayList<>();
     Store s = em.find(Store.class, storeId);
     w.add(s.getWarehouse());
     return w;

     }

     @Override
     public List<Front> getFront(Long storeId
     ) {
     List<Front> f = new ArrayList<>();
     Store s = em.find(Store.class, storeId);
     f.add(s.getFront());
     return f;
     }
     // do not been used now!

     @Override
     public List<Material> getMaterialList2(Long storeId
     ) {
     List<Material> m = new ArrayList<>();
     Store s = em.find(Store.class, storeId);
     Front w = s.getFront();

     m = new ArrayList<Material>(w.getMaterialCollection());

     return m;
     }

     @Override
     public List<Material> getMaterialList(Long storeId) {
     List<Material> m = new ArrayList<>();
     m.clear();
     Store s = em.find(Store.class, storeId);
     Warehouse w = s.getWarehouse();
     for (Object o : w.getMaterialCollection()) {
     m.add((Material) o);
     }

     return m;
     }

     @Override
     public List<Material> getMaterialList(String storeName) {
     System.out.println("storeName" + storeName);
     Query q = em.createQuery("select s from Store s where s.storeName=:sName");
     q.setParameter("sName", storeName);
     Store st = (Store) q.getResultList().get(0);
     List<Material> w = new ArrayList<>(st.getWarehouse().getMaterialCollection());
     return w;

     }
     */

    @Override
    public List<Furniture> getWarehouseMaterialList(String backName) {
        List<Furniture> m = new ArrayList<>();
        m.clear();
        Query q = em.createQuery("select w from Warehouse w where w.warehouseName=:wName");
        q.setParameter("wName", backName);
        if (q.getResultList().isEmpty()) {
            return m;
        } else {
            Collection<Furniture> mc = ((Warehouse) q.getResultList().get(0)).getMaterialCollection();
            for (Object o : mc) {
                Furniture mo = (Furniture) o;
                m.add(mo);
            }
            return m;
        }
    }
    /*
     @Override
     public List<Material> getAllWarehouseMaterialList() {
     List<Material> m = new ArrayList<>();
     Query q = em.createQuery("select w from Warehouse w");
     for (Object o : q.getResultList()) {
     Warehouse w = (Warehouse) o;
     Collection wc = w.getMaterialCollection();
     for (Object o2 : wc) {
     m.add((Material) o2);

     }
     }
     return m;
     }
     */
//用啦
/*
     @Override
     public List<Material> getAllWarehouseMaterialList() {

     Query q = em.createQuery("select w from Material w");

     return q.getResultList();
     }
     */

    @Override
    public List<Furniture> getAllWarehouseMaterialList() {
        List<Furniture> m = new ArrayList<>();
        m.clear();
        Query q = em.createQuery("select w from Warehouse w where w.deletema='N'");

        if (q.getResultList().isEmpty()) {
            return m;
        } else {
            Warehouse house = (Warehouse) q.getResultList().get(0);
            Collection<Furniture> mc = ((Warehouse) q.getResultList().get(0)).getMaterialCollection();
            for (Object o : mc) {
                Furniture mo = (Furniture) o;
                ///
                mo.setMaterialPosition("");
                if (mo.getPosition() == null) {
                    mo.setPosition("");
                }
                ////
                if (mo.getPosition().equals("BIG")) {
                   Furniture[][] BigArray = house.getBigArray();
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            if ((BigArray[i][j] != null) && (BigArray[i][j].getFurnitureName().equals(mo.getFurnitureName()))) {
                                mo.setMaterialPosition(mo.getMaterialPosition() + "(" + i + "," + j + ")");
                                System.out.println(i + "-" + j);
                            }
                        }
                    }
                } else {
                    Furniture[][][] SUArray = house.getSUArray();
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            for (int k = 0; k < 10; k++) {
                                if ((SUArray[i][j][k] != null) && (SUArray[i][j][k].getFurnitureName().equals(mo.getFurnitureName()))) {
                                    mo.setMaterialPosition(mo.getMaterialPosition() + "(" + i + "," + j + "," + k + ")");
                                    System.out.println(i + "-" + j + "-" + k);
                                }
                            }
                        }
                    }
                }
                m.add(mo);
            }
            return m;
        }
    }

    @Override
    public Long addSafety(String materialName, Integer safetyQuantity) {
        Query q = em.createQuery("select m from Furniture m where m.furnitureName=:mName");
        q.setParameter("mName", materialName);
        if (q.getResultList().isEmpty()) {
            return -2l;//no material

        } else {
            for (Object o : q.getResultList()) {
                Furniture m = (Furniture) o;
                m.setSafetyStock(safetyQuantity);
                em.persist(m);
            }
            return 1l;
        }
    }
    /*
     @Override
     public Long addSafety2(String storeName, String materialName, Integer safetyQuantity) {
     Boolean check = true;
     Query q0 = em.createQuery("select s from Store s where s.storeName=:sName");
     q0.setParameter("sName", storeName);
     if (q0.getResultList().isEmpty()) {
     return -2l;//no store;
     } else {

     store = (Store) q0.getResultList().get(0);
     for (Object o : store.getWarehouse().getMaterialCollection()) {
     Material m = (Material) o;
     if (m.getMaterialName().toUpperCase().equals(materialName.toUpperCase())) {
     m.setSafetyStock(safetyQuantity);
     em.persist(m);
     check = false;
     }
     }
     if (check) {
     System.out.print("tssss");
     return -3l;//no material
     }
     return 1l;
     }

     }
     */

    @Override
    public Long addReturn(String goodsName, Integer goodsQuantity, String returnReason) {

        Query q1 = em.createQuery("select w from Warehouse w");
        if (q1.getResultList().isEmpty()) {
            return -2l;//no warehouse
        } else {
            Query qw = em.createQuery("select w from Warehouse w where w.deletema='N'");
            if (qw.getResultList().isEmpty()) {
                return -2l;
            } else {
                Query q = em.createQuery("select rg from  ReturnGoods rg where rg.goodsName=:gn and rg.deletema='N' and rg.status='RETURNED' and rg.returnReason=:rgReason");
                q.setParameter("rgReason", returnReason);
                q.setParameter("gn", goodsName);
                Warehouse w = (Warehouse) q1.getResultList().get(0);
                if (q.getResultList().isEmpty()) {
                    ReturnGoods rg = new ReturnGoods();
                    rg.setGoodsName(goodsName);
                    rg.setQuantity(goodsQuantity);
                    rg.setWarehouse(w);
                    rg.setReturnReason(returnReason);
                    em.persist(rg);
                    w.getReturnGoodsCollection().add(rg);
                    return 1l;
                } else {
                    ReturnGoods rg = (ReturnGoods) q.getResultList().get(0);
                    rg.setQuantity(goodsQuantity + rg.getQuantity());
                    rg.setReturnReason(returnReason);
                    em.persist(rg);
                    return 1l;
                }
            }
        }
    }

    @Override
    public List<Furniture> getAllMaterial() {
        Query q = em.createQuery("select m from Furniture m ");
        return q.getResultList();
    }
    /*
     @Override
     public Long addWarehouseMaterial(String materialName, Integer materialQuantity) {
     Query q0 = em.createQuery("select w from Warehouse w");
     if (q0.getResultList().isEmpty()) {
     return -1l;//no warehouse, need to add warehosue before add material to that warehouse
     } else {
     Query q = em.createQuery("select m from Material m where m.materialName=:mName");
     q.setParameter("mName", materialName);
     if (q.getResultList().isEmpty()) {
     System.out.println("test from warehouse Session bean addwarehouseMaterial() to test this method correctlly work");
     return -2l;
     } else {
     Material m = (Material) q.getResultList().get(0);
     Warehouse w = (Warehouse) q0.getResultList().get(0);
     if (m.getPosition().equals("BIG_ROW0")) {
     int check = -1;
     Material[][] BigArray = w.getBigArray();
     for (int i = 0; i < 10; i++) {
     if ((BigArray[0][i] == null) && ((10 - i) >= materialQuantity)) {
     check = i;
     break;
     }
     }
     if (check != -1) {
     for (int j = 0; j < materialQuantity; j++) {
     BigArray[0][check + j] = m;
     }
     m.setBackQuantity(materialQuantity + m.getBackQuantity());

     w.setBigArray(BigArray);
     em.persist(w);
     return 2l;//successfully
     } else {
     return -3l;// to much quantity;
     }

     } else if (m.getPosition().equals("BIG_ROW1")) {
     int check = -1;
     Material[][] BigArray = w.getBigArray();
     for (int i = 0; i < 10; i++) {
     if ((BigArray[1][i] == null) && ((10 - i) >= materialQuantity)) {
     check = i;
     break;
     }
     }
     if (check != -1) {
     for (int j = 0; j < materialQuantity; j++) {
     BigArray[1][check + j] = m;
     }
     m.setBackQuantity(materialQuantity + m.getBackQuantity());

     w.setBigArray(BigArray);
     em.persist(w);
     return 2l;//successfully
     } else {
     return -3l;// to much quantity;
     }

     } else if (m.getPosition().equals("BIG_ROW2")) {
     int check = -1;
     Material[][] BigArray = w.getBigArray();
     for (int i = 0; i < 10; i++) {
     if ((BigArray[2][i] == null) && ((10 - i) >= materialQuantity)) {
     check = i;
     break;
     }
     }
     if (check != -1) {
     for (int j = 0; j < materialQuantity; j++) {
     BigArray[2][check + j] = m;
     }
     m.setBackQuantity(materialQuantity + m.getBackQuantity());

     w.setBigArray(BigArray);
     em.persist(w);
     return 2l;//successfully
     } else {
     return -3l;// to much quantity;
     }

     } else if (m.getPosition().equals("BIG_ROW3")) {
     int check = -1;
     Material[][] BigArray = w.getBigArray();
     for (int i = 0; i < 10; i++) {
     if ((BigArray[3][i] == null) && ((10 - i) >= materialQuantity)) {
     check = i;
     break;
     }
     }
     if (check != -1) {
     for (int j = 0; j < materialQuantity; j++) {
     BigArray[3][check + j] = m;
     }
     m.setBackQuantity(materialQuantity + m.getBackQuantity());

     w.setBigArray(BigArray);
     em.persist(w);
     return 2l;//successfully
     } else {
     return -3l;// to much quantity;
     }

     } else if (m.getPosition().equals("BIG_ROW4")) {
     int check = -1;
     Material[][] BigArray = w.getBigArray();
     for (int i = 0; i < 10; i++) {
     if ((BigArray[4][i] == null) && ((10 - i) >= materialQuantity)) {
     check = i;
     break;
     }
     }
     if (check != -1) {
     for (int j = 0; j < materialQuantity; j++) {
     BigArray[4][check + j] = m;
     }
     m.setBackQuantity(materialQuantity + m.getBackQuantity());

     w.setBigArray(BigArray);
     em.persist(w);
     return 2l;//successfully
     } else {
     return -3l;// to much quantity;
     }

     } else if (m.getPosition().equals("BIG_ROW5")) {
     int check = -1;
     Material[][] BigArray = w.getBigArray();
     for (int i = 0; i < 10; i++) {
     if ((BigArray[5][i] == null) && ((10 - i) >= materialQuantity)) {
     check = i;
     break;
     }
     }
     if (check != -1) {
     for (int j = 0; j < materialQuantity; j++) {
     BigArray[5][check + j] = m;
     }
     m.setBackQuantity(materialQuantity + m.getBackQuantity());

     w.setBigArray(BigArray);
     em.persist(w);
     return 2l;//successfully
     } else {
     return -3l;// to much quantity;
     }

     } else if (m.getPosition().equals("BIG_ROW6")) {
     int check = -1;
     Material[][] BigArray = w.getBigArray();
     for (int i = 0; i < 10; i++) {
     if ((BigArray[6][i] == null) && ((10 - i) >= materialQuantity)) {
     check = i;
     break;
     }
     }
     if (check != -1) {
     for (int j = 0; j < materialQuantity; j++) {
     BigArray[6][check + j] = m;
     }
     m.setBackQuantity(materialQuantity + m.getBackQuantity());

     w.setBigArray(BigArray);
     em.persist(w);
     return 2l;//successfully
     } else {
     return -3l;// to much quantity;
     }

     } else if (m.getPosition().equals("BIG_ROW7")) {
     int check = -1;
     Material[][] BigArray = w.getBigArray();
     for (int i = 0; i < 10; i++) {
     if ((BigArray[7][i] == null) && ((10 - i) >= materialQuantity)) {
     check = i;
     break;
     }
     }
     if (check != -1) {
     for (int j = 0; j < materialQuantity; j++) {
     BigArray[7][check + j] = m;
     }
     m.setBackQuantity(materialQuantity + m.getBackQuantity());

     w.setBigArray(BigArray);
     em.persist(w);
     return 2l;//successfully
     } else {
     return -3l;// to much quantity;
     }

     } else if (m.getPosition().equals("BIG_ROW8")) {
     int check = -1;
     Material[][] BigArray = w.getBigArray();
     for (int i = 0; i < 10; i++) {
     if ((BigArray[8][i] == null) && ((10 - i) >= materialQuantity)) {
     check = i;
     break;
     }
     }
     if (check != -1) {
     for (int j = 0; j < materialQuantity; j++) {
     BigArray[8][check + j] = m;
     }
     m.setBackQuantity(materialQuantity + m.getBackQuantity());

     w.setBigArray(BigArray);
     em.persist(w);
     return 2l;//successfully
     } else {
     return -3l;// to much quantity;
     }

     } else if (m.getPosition().equals("BIG_ROW9")) {
     int check = -1;
     Material[][] BigArray = w.getBigArray();
     for (int i = 0; i < 10; i++) {
     if ((BigArray[9][i] == null) && ((10 - i) >= materialQuantity)) {
     check = i;
     break;
     }
     }
     if (check != -1) {
     for (int j = 0; j < materialQuantity; j++) {
     BigArray[9][check + j] = m;
     }
     m.setBackQuantity(materialQuantity + m.getBackQuantity());

     w.setBigArray(BigArray);
     em.persist(w);
     return 2l;//successfully
     } else {
     return -3l;// to much quantity;
     }

     }

     return -3l;//continues;
     }

     }
     }

    
     */

    @Override
    public Long addWarehouseMaterials(String materialName, Integer materialQuantity) {
        Query q0 = em.createQuery("select w from Warehouse w where w.deletema='N'");
        if (q0.getResultList().isEmpty()) {
            return -1l;//no warehouse, need to add warehosue before add material to that warehouse
        } else {
            Query q = em.createQuery("select m from Furniture m where m.furnitureName=:mName");
            q.setParameter("mName", materialName);
            if (q.getResultList().isEmpty()) {
                System.out.println("test from warehouse Session bean addwarehouseMaterial() to test this method correctlly work");
                return -2l;
            } else {
                Furniture m = (Furniture) q.getResultList().get(0);
                Warehouse w = (Warehouse) q0.getResultList().get(0);
                w.getMaterialCollection().remove(m);
                m.setBackQuantity(materialQuantity + m.getBackQuantity());
                m.setWarehouse(w);
                em.persist(m);
                w.getMaterialCollection().add(m);
                em.persist(w);
                return 2l;

            }
        }

    }

    @Override
    public Long addwarehouseMaterialMaterial(String materialName, Integer materialQuantity) {
        System.out.println("test0");
        Query q0 = em.createQuery("select w from Warehouse w where w.deletema='N'");
        System.out.println("test1");
        if (q0.getResultList().isEmpty()) {
            return -1l;//no warehouse, need to add warehosue before add material to that warehouse
        } else {
            Query q = em.createQuery("select m from Furniture m where m.furnitureName=:mName");
            q.setParameter("mName", materialName);
            if (q.getResultList().isEmpty()) {
                System.out.println("test from warehouse Session bean addwarehouseMaterial() to test this method correctlly work");
                return -2l;
            } else {
                Furniture m = (Furniture) q.getResultList().get(0);
                Warehouse w = (Warehouse) q0.getResultList().get(0);
                w.getMaterialCollection().remove(m);
                m.setBackQuantity(materialQuantity + m.getBackQuantity());
                m.setWarehouse(w);
                em.persist(m);
                w.getMaterialCollection().add(m);
                em.persist(w);
                ////
                if (m.getPosition() == null) {
                    m.setPosition("");
                }
                ///
                if (m.getPosition().equals("BIG")) {
                    Furniture[][] BigArray = w.getBigArray();
                    for (int i = 0; i < 10; i++) {
                        if ((BigArray[i][0] == null) || ((BigArray[i][0]).getFurnitureName().equals(materialName))) {
                            for (int j = 0; j < 10; j++) {
                                if (BigArray[i][j] == null) {
                                    BigArray[i][j] = m;
                                    materialQuantity--;
                                    System.out.println(BigArray[i][j].getFurnitureName());
                                }
                                if (materialQuantity == 0) {
                                    w.setBigArray(BigArray);
                                    break;
                                }
                            }
                        }
                        if (materialQuantity == 0) {
                            w.setBigArray(BigArray);

                            break;
                        }
                    }
                    w.setBigArray(BigArray);
                    m.setBackQuantity(m.getBackQuantity() - materialQuantity);
                    em.persist(w);

                    return (long) materialQuantity;
                } else {
                    Furniture[][][] SUArray = w.getSUArray();
                    for (int i = 0; i < 10; i++) {
                        for (int k = 0; k < 10; k++) {
                            if ((SUArray[i][k][0] == null) || ((SUArray[i][k][0]).getFurnitureName().equals(materialName))) {
                                for (int j = 0; j < 10; j++) {
                                    if (SUArray[i][k][j] == null) {
                                        SUArray[i][k][j] = m;
                                        materialQuantity--;
                                    }
                                    if (materialQuantity == 0) {
                                        w.setSUArray(SUArray);
                                        break;
                                    }
                                }
                            }
                            if (materialQuantity == 0) {
                                w.setSUArray(SUArray);
                                break;
                            }
                        }
                        if (materialQuantity == 0) {
                            w.setSUArray(SUArray);
                            break;
                        }
                    }
                    w.setSUArray(SUArray);
                    m.setBackQuantity(m.getBackQuantity() - materialQuantity);
                    return (long) materialQuantity;
                }

                //   return 2l;
            }
        }

    }

    @Override
    public List<Furniture> searchFrontMaterial(String materialName) {
        Query q = em.createQuery("SELECT m from Furniture m where m.furnitureName=:mName");
        q.setParameter("mName", materialName);
        return q.getResultList();
    }

    @Override
    public List<Furniture> searchBackMaterial(String materialName) {
        Query q = em.createQuery("SELECT m from Furniture m where m.furnitureName=:mName");
        q.setParameter("mName", materialName);
        return q.getResultList();
    }

    @Override
    public List<ReturnGoods> searchReturnGoods(String goodsName, String returnReason) {
        Query q = em.createQuery("select rg from ReturnGoods rg where rg.goodsName=:rgN and rg.returnReason=:rgR and rg.deletema='N'");
        q.setParameter("rgN", goodsName);
        q.setParameter("rgR", returnReason);
        return q.getResultList();
    }

    @Override
    public List<ReturnGoods> getAllRG() {
        Query q = em.createQuery("SELECT rg from ReturnGoods rg where rg.deletema='N'");
        return q.getResultList();
    }

    @Override
    public void editWarehouse(Warehouse ware) {
        Warehouse w = em.find(Warehouse.class, ware.getId());
        w.setAddress(ware.getAddress());
        w.setWarehouseName(ware.getWarehouseName());
        em.persist(w);

    }

    @Override
    public void editMaterial2Front(Furniture ware) {
       Furniture m = em.find(Furniture.class, ware.getId());
        // m.setBackQuantity(ware.getBackQuantity());
        //  m.setSafetyStock(ware.getSafetyStock());
        m.setFrontQuantity(ware.getFrontQuantity());
        em.persist(m);
    }

    @Override
    public long editMaterial(Furniture ware) {
        long rest = 2l;
        //this is for if the changed parameter is warehouse material amount
        Furniture m = em.find(Furniture.class, ware.getId());
        if (m.getBackQuantity() < ware.getBackQuantity()) {
            Integer amount = ware.getBackQuantity() - m.getBackQuantity();
            System.out.println("test back quantity changed1");
            // if amount >0, then I have to add material to the warehouse
            rest = -addwarehouseMaterialMaterial(m.getFurnitureName(), amount);
            //  m.setBackQuantity(ware.getBackQuantity());
            m.setSafetyStock(ware.getSafetyStock());
            //      m.setFrontQuantity(ware.getFrontQuantity());
            em.persist(m);
        } else if (m.getBackQuantity() > ware.getBackQuantity()) {
            System.out.println("test back quantity changed2");
            Integer amount = m.getBackQuantity() - ware.getBackQuantity();// amount>0; I removethe material from warehouse as well as change the position
            rest = editMaterial2ReduceAmount(m.getFurnitureName(), amount);
            m.setSafetyStock(ware.getSafetyStock());
            //  m.setFrontQuantity(ware.getFrontQuantity());
            em.persist(m);

        }
        m.setSafetyStock(ware.getSafetyStock());
        em.persist(m);
        return rest;

    }

    public long editMaterial2ReduceAmount(String materialName, Integer meterailQuantity) {
        Boolean amount = true;
        Integer qua = 0;

        Query q1 = em.createQuery("select m from Furniture m where m.furnitureName=:mName");
        q1.setParameter("mName", materialName);
        Furniture m = (Furniture) q1.getResultList().get(0);

        m.setBackQuantity(m.getBackQuantity() - meterailQuantity);
        em.persist(m);
        Warehouse house = m.getWarehouse();
        if (m.getPosition().equals("BIG")) {
            Furniture[][] BigArray = house.getBigArray();
            for (int i = 9; i >= 0; i--) {
                for (int j = 9; j >= 0; j--) {
                    if ((BigArray[i][j] != null) && (BigArray[i][j].getFurnitureName().equals(materialName))) {
                        BigArray[i][j] = null;
                        meterailQuantity--;
                    }
                    if (meterailQuantity == 0) {
                        house.setBigArray(BigArray);
                        em.persist(house);
                        break;
                    }
                }
                if (meterailQuantity == 0) {
                    house.setBigArray(BigArray);
                    em.persist(house);
                    break;
                }
            }
        } else {
            Furniture[][][] SUArray = house.getSUArray();
            for (int i = 9; i >= 0; i--) {
                for (int j = 9; j >= 0; j--) {
                    for (int k = 9; k >= 0; k--) {
                        if ((SUArray[i][j][k] != null) && (SUArray[i][j][k].getFurnitureName().equals(materialName))) {
                            SUArray[i][j][k] = null;
                            meterailQuantity--;
                        }
                        if (meterailQuantity == 0) {
                            house.setSUArray(SUArray);
                            em.persist(house);
                            break;
                        }
                    }
                    if (meterailQuantity == 0) {
                        house.setSUArray(SUArray);
                        em.persist(house);
                        break;
                    }

                }
                if (meterailQuantity == 0) {
                    house.setSUArray(SUArray);
                    em.persist(house);
                    break;
                }
            }
        }
        m.setWarehouse(house);
        //  m.setFront((Front)q.getSingleResult());
        em.persist(m);
        return 2l;

    }

    @Override
    public void editRG(ReturnGoods ware) {
        System.out.println("ware quantity=" + ware.getQuantity());
        ReturnGoods rg = em.find(ReturnGoods.class, ware.getId());
        rg.setPosition(ware.getPosition());
        System.out.println("rg quantity=" + rg.getQuantity());
        rg.setQuantity(ware.getQuantity());
        rg.setStatus(ware.getStatus());
        em.persist(rg);
    }

}
