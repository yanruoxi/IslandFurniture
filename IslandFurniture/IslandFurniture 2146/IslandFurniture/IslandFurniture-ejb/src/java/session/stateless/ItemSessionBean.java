/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.BOM;
import entity.Front;
import entity.Furniture;
import entity.Part;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ItemSessionBean implements ItemSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    private BOM bomEntity;
    private Furniture furnitureEntity;
    private Collection<BOM> B;
    private Collection<BOM> B2;
    private Part partEntity;

    @Override
    public Furniture searchFurniture(long id) {
        Furniture furniture = em.find(Furniture.class, id);
        return furniture;
    }

    @Override
    public Furniture getFurniture(String furnitureName) {
        Furniture f = null;
        Query q = em.createQuery("select f from Furniture f where f.furnitureName=:fName");
        q.setParameter("fName", furnitureName);
        if (!q.getResultList().isEmpty()) {
            f = (Furniture) q.getResultList().get(0);
            System.out.println(f.getId());
        }

        return f;
    }

    @Override
    public List<BOM> getFurnitureBom(String furnitureName) {
        List<BOM> bl = new ArrayList<>();
        Furniture f = null;
        Query q = em.createQuery("select f from Furniture f where f.furnitureName=:fName");
        q.setParameter("fName", furnitureName);
        if (!q.getResultList().isEmpty()) {
            f = (Furniture) q.getResultList().get(0);
            System.out.println(f.getId());
            Collection<BOM> b = f.getBom();
            bl = new ArrayList<>(b);
        }
        return bl;
    }

    @Override
    public Part getPart(String partName) {
        Part p = null;
        Query q = em.createQuery("select p from Part p where p.partName=:pName");
        q.setParameter("pName", partName);
        if (!q.getResultList().isEmpty()) {
            p = (Part) q.getResultList().get(0);
        }
        return p;
    }

    @Override
    public List<Furniture> getAllFurniture() {
        Query query = em.createQuery("SELECT f FROM Furniture f");
        //test
        System.out.println("testing for methond getAllFurniture() in itemSessionBean");
        return query.getResultList();

    }

    @Override
    public List<BOM> getAllBom() {
        Query query = em.createQuery("SELECT b FROM BOM b");
        //test
        System.out.println("testing for methond getAllbom() in itemSessionBean");
        return query.getResultList();

    }

    @Override
    public List<Part> getAllPart() {
        Query query = em.createQuery("SELECT f FROM Part f");
        //test
        System.out.println("testing for methond getAllPart() in itemSessionBean");
        return query.getResultList();

    }

    //add item -furniture
    @Override
    public Long AddFurniture(String furnitureName) {
        Warehouse w1 = new Warehouse();
        w1.setWarehouseName("w1");
        Front f1 = new Front();
        f1.setFrontName("f1");
        w1.setFront(f1);
        f1.setWarehouse(w1);
        em.persist(w1);
        em.persist(f1);
        em.flush();
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
                    m.setFurnitureName(furnitureName);
                    m.setPosition("BIG");
                    m.setBackQuantity(0);
                    m.setFrontQuantity(0);
                    m.setSafetyStock(0);
                    m.setFront(f);
                    m.setWarehouse(w);
                    em.persist(m);
                    return m.getId();
                } else {
                    Query q1 = em.createQuery("select m from Furniture m where m.furnitureName=:mName");
                    q1.setParameter("mName", furnitureName);
                    if (q1.getResultList().isEmpty()) {
                        Furniture m = new Furniture();
                        m.setFurnitureName(furnitureName);
                        m.setPosition("BIG");
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

    //add item - part
    @Override
    public Long AddPart(String partName, Integer lotSize) {
        Query q = em.createQuery("select p from Part p where p.partName=:pName");
        q.setParameter("pName", partName);
        if (q.getResultList().isEmpty()) {
            partEntity = new Part();
            partEntity.create(partName, lotSize);
            //B2 = new ArrayList<BOM>();
            em.persist(partEntity);
            em.flush();
            return partEntity.getId();
        } else {
            System.out.println("part name already existed");
            return -2l;
        }

    }

    //search item by furniture name
    @Override
    public ArrayList searchFurniture(String furnitureName) {
        ArrayList sf = new ArrayList();
        Query q = em.createQuery("SELECT f from Furniture f where f.furnitureName=:fname");
        q.setParameter("fname", furnitureName);
        if (q.getResultList().isEmpty()) {
            System.out.println("wrong funitureName,please provide an exsiting furniture for search");
            sf.add("no result, the furniture you are searching is not existed");
            return sf;

        } else {
            Furniture ff = (Furniture) q.getResultList().get(0);
            sf.add(ff.getId());
            sf.add(ff.getFurnitureName());
            Collection<BOM> b = ff.getBom();
            List<BOM> bb = new ArrayList<BOM>(b);
            for (Object o : bb) {
                BOM bommm = (BOM) o;
                Part ppp = bommm.getPart();
                sf.add(ppp.getPartName());

            }
            return sf;
            //what is included in sf?
            // sf[0]: furniture id,
            // sf[1]: furnitue name;
            // sf[2~]: the part/materials that is needed to build the furniture

        }
    }

    @Override
    public ArrayList searchPart(String partName) {
        ArrayList spt = new ArrayList();
        Query q = em.createQuery("SELECT p from Part p where p.partName=:pName");
        q.setParameter("pName", partName);
        if (q.getResultList().isEmpty()) {
            System.out.println("no part for what you are searching,please provide an correct partName to search");
            spt.add("no part info");

        } else {
            Part ppp = (Part) q.getResultList().get(0);
            spt.add(ppp.getId());
            spt.add(ppp.getPartName());
            spt.add(ppp.getLotSize());
        }
        return spt;

    }

    //delete furniture with furniture name provided
    @Override
    public long DelectFurniture(String fName) {
        Query q = em.createQuery("select f from Furniture f where f.furnitureName=:fName");
        q.setParameter("fName", fName);
        if (q.getResultList().isEmpty()) {
            System.out.println("invalid furniture Name");
            return -1l;
        } else {
            System.out.println("Testing for delectfurniture(String fname) from itemsessionbean");

            Furniture f = (Furniture) q.getResultList().get(0);
            System.out.println("test: print furniture id" + f.getId());

            Collection<BOM> bom123 = new ArrayList<BOM>();
            bom123.addAll(f.getBom());
            System.out.println("test2: print furniture id" + f.getId());

            List<BOM> bom1 = new ArrayList<BOM>(bom123);
            System.out.println("test22: print furniture id" + f.getId());

            System.out.println("test2: print bom(0)" + f.getId());
            for (Object o : bom1) {
                System.out.println("test3: print furniture id" + f.getId());
                BOM bd1 = (BOM) o;
                System.out.println("test4: print furniture id" + f.getId());
                long l = DeleteBOM(bd1.getId());

            }

            //test
            System.out.println("delete sucessfully");

            em.remove(f);
            em.flush();
            return 1l;// remove successfully
        }

    }

    @Override
    public long DelectPart(String pName) {
        Query q = em.createQuery("select p from Part p where p.partName=:pName");
        q.setParameter("pName", pName);
        System.out.println("part 1 test");
        if (q.getResultList().isEmpty()) {
            System.out.println("invalid Part Name");
            return -1l;
        } else {
            System.out.println("part 2 test");
            Part p = (Part) q.getResultList().get(0);

            //tesing
            System.out.println(p);
            System.out.println("testing 3 for deleting part");
            if (!p.getBom().isEmpty()) {
                System.out.println("Delete part fail, the part has been in BOM with existing furniture. cannot remove now");
                return -2l;
            } else {
                System.out.println("Part :" + pName + " has been deleted successfully");
                em.remove(p);
                em.flush();
                return 1l;
            }
            // Collection<BOM> bom123 = new ArrayList<BOM>();
            //    bom123.addAll(f.getBom());

        }

    }

    @Override
    public void editFurniture(Long furnitureID, String furnitureName) {

        Furniture f = em.find(Furniture.class, furnitureID);
        if (f
                == null) {
            System.out.println("EditFurniture(): edit is rejected, invalid furniture ID");
        } else {

            f.setFurnitureName(furnitureName);
        }

    }

    @Override
    public long editFurniture2(String furnitureName, String newFurnitureName) {
        Query q = em.createQuery("SELECT f from Furniture f where f.furnitureName=:fName");
        q.setParameter("fName", furnitureName);
        if (q.getResultList().isEmpty()) {
            System.out.println("edit fail, furniture Name is invalid");
            return -2l;
        } else {
            q.setParameter("fName", newFurnitureName);
            if (!q.getResultList().isEmpty()) {
                System.out.println("newFurniture already exist, invalid new name");
                return -1l;
            } else {
                q.setParameter("fName", furnitureName);
                Furniture fur = (Furniture) q.getResultList().get(0);
                fur.setFurnitureName(newFurnitureName);
                return fur.getId();
            }
        }
    }
    /*
     @Override
     public void editFurniture3(String oldValue, String newValue){
     Query q=em.createQuery(("select p from Furniture p where p.furnitureName=:pName"));
     q.setParameter("pName", oldValue);
     Furniture f=(Furniture)q.getResultList().get(0);
     f.setFurnitureName(newValue);
     }
     */

    @Override
    public long editPart2(String partName, String newPartName, Integer lotSize) {
        Query q = em.createQuery("select p from Part p where p.partName=:pName");
        q.setParameter("pName", partName);
        if (q.getResultList().isEmpty()) {
            System.out.println("edit fail, Part name does not exist");
            return -2l;
        } else {
            Part par = (Part) q.getResultList().get(0);
            par.setLotSize(lotSize);
            par.setPartName(newPartName);
            return par.getId();
        }
    }

    @Override
    public void changePartBom(String newValue, Long bomId) {
        BOM b = em.find(BOM.class, bomId);
        Query q = em.createQuery("select p from Part p where p.partName=:pName");
        q.setParameter("pName", newValue);
        Part p = (Part) q.getResultList().get(0);
        b.setPart(p);

    }

    @Override
    public void changeQuantityBom(Integer newValue, Long bomId) {
        BOM b = em.find(BOM.class, bomId);
        b.setQuantity(newValue);
    }

    @Override
    public void editPart(Long partID, String partName, Integer lotSize) {
        Part part = em.find(Part.class, partID);
        part.setPartName(partName);

        part.setLotSize(lotSize);
    }
//no override here!! pay attention --> inner method 

    public long DeleteBOM(Long B_ID) {
        BOM b = em.find(BOM.class, B_ID);
        if (b == null) {
            System.out.println("DeleteBOM(): Deletion is rejected, invalid BOM id");
            return -1l;
        } else {
            Query q = em.createQuery("select p from Part p");
            for (Object o : q.getResultList()) {
                Part pa = (Part) o;
                Collection<BOM> bb = new ArrayList<BOM>();

                bb.add(b);
                pa.getBom().removeAll(bb);
            }
            em.remove(b);
            em.flush();
            System.out.println("bom has been removed");
            return B_ID;

            // if reuturn -1l, then bom has not been removed;else if return b_id, remove successfully
        }
    }
// this is override method

    @Override
    public long DeleteBOM2(Long B_ID) {
        BOM b = em.find(BOM.class, B_ID);
        if (b == null) {
            System.out.println("DeleteBOM(): Deletion is rejected, invalid BOM id");
            return -1l;
        } else {
            Query q = em.createQuery("select p from Part p");
            for (Object o : q.getResultList()) {
                Part pa = (Part) o;
                Collection<BOM> bb = new ArrayList<BOM>();

                bb.add(b);
                pa.getBom().removeAll(bb);
            }
            System.out.println("test deletebomb()4 from itemsessionbean" + B_ID);

            Query q2 = em.createQuery("select f from Furniture f");
            for (Object o2 : q2.getResultList()) {
                Furniture f2 = (Furniture) o2;
                Collection<BOM> bbb = new ArrayList<BOM>();
                bbb.add(b);
                f2.getBom().removeAll(bbb);

                System.out.println("test deletebomb()5 from itemsessionbean" + B_ID);
            }
            em.remove(b);
            em.flush();
            System.out.println("bom has been removed");

            return B_ID;

            // if reuturn -1l, then bom has not been removed;else if return b_id, remove successfully
        }
    }

    @Override
    public Long createBom(String furnitureName, String partName, Integer quantity) {
        int t = 0;
        System.out.println("test create bom from itemSessionbean");
        BOM bc = new BOM();
        Query q = em.createQuery("select p from Part p where p.partName=:pName");
        q.setParameter("pName", partName);
        if (q.getResultList().isEmpty()) {
            return -2l;
        } else {
            Part pc = (Part) q.getResultList().get(0);
            Query q1 = em.createQuery("select f from Furniture f where f.furnitureName=:fName");
            q1.setParameter("fName", furnitureName);
            if (q1.getResultList().isEmpty()) {
                return -2l;
            } else {
                Query q3 = em.createQuery("select b from BOM b");
                if (!q3.getResultList().isEmpty()) {
                    System.out.println("test for if method ");
                    List<BOM> bomq3 = q3.getResultList();
                    for (Object o : bomq3) {

                        BOM bq3 = (BOM) o;
                        System.out.println(bq3);
                        /*   if(bq3.getFurniture()..equals(furnitureName)){
                         if(bq3.getPart().getPartName().equals(partName)){
                                
                         t=1; 
                         System.out.println(t);
                         return -3l;
                         }
                         }
                         */

                        System.out.println(bq3.getFurniture().getFurnitureName() + "---" + furnitureName);

                        System.out.println(bq3.getPart().getPartName() + "---" + partName);

                        if (((bq3.getFurniture().getFurnitureName()).toUpperCase().equals((furnitureName).toUpperCase())) && ((bq3.getPart().getPartName()).toUpperCase().equals((partName).toUpperCase()))) {
                            System.out.println("duplicate bom");
                            t = 1;
                            return -3l;
                        }

                    }

                }
                if (t == 0) {
                    Furniture fc = (Furniture) q1.getResultList().get(0);
                    bc.setPart(pc);
                    bc.setFurniture(fc);
                    bc.setQuantity(quantity);
                    em.persist(bc);
                    fc.getBom().add(bc);
                    pc.getBom().add(bc);
                    em.persist(pc);
                    em.persist(fc);
                    return bc.getId();
                }

            }

        }
        return -3l;

    }
}
