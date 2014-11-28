/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.BOM;
import entity.Furniture;
import entity.Part;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Li
 */
@Stateless
public class BOMSessionBean implements BOMSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    private BOM bomEntity;
    private Furniture furnitureEntity;
    private Collection<BOM> B;
    private Collection<BOM> B2;
    private Part partEntity;
    
    //Generate BOM--Add a new BOM rord
    @Override
    public void GenerateBOM(Integer quantity,Furniture furniture,Part part){
        bomEntity = new BOM();
        bomEntity.create(quantity);
        bomEntity.setFurniture(furniture);
        bomEntity.setPart(part);
    }
    
    //search bom with fruniture name
    //view BOM-- with Furniture  name--no more than one furnitures get the same name(pre-condition)
    @Override
    public List<ArrayList> ViewBOMByFurniture(String fName ){
        Query q=em.createQuery("SELECT f FROM Furniture WHERE f.furnitureName=:fName");
        q.setParameter("fName",fName);
        List<ArrayList> aa=new ArrayList();
        if(q.getResultList().isEmpty()){
            System.out.println("there is no existing furniture for the name you provided/ furniture does  not exist ");
            return aa;//empty
        }else{
            //return furniture--only one furniture
             Furniture f=(Furniture)q.getResultList().get(0);
            
             Collection<BOM> bom = f.getBom();
             if(bom.isEmpty()){
                 System.out.println("no bom for this furniture,please add bom for the furniture");
                  return aa;//empty
             }else{
                 List<BOM> b=new ArrayList<BOM>(bom);//change collection to list
                 for(Object o:b){
                     ArrayList v= new ArrayList();
                     BOM b2=(BOM)o;
                     Integer bQuantity=b2.getQuantity();
                     String partName=b2.getPart().getPartName();
                     v.add(partName);
                     v.add(bQuantity);
                     aa.add(v);
                     
                     
                 }
                 return aa;
                 //what is in List<ArrayList> aa?
                 // aa[0]: partName1 ,Quantity
                 // aa[1]: partName2, Quantity
                 // ...
                 // partName is from part, quantity is from BOM
                 
             }
            
        }
        
        
        
    }
    @Override //cancel/delete bom
    public long DeleteBOM (Long B_ID){
        BOM b=em.find(BOM.class, B_ID);
        if(b == null){
            System.out.println("DeleteBOM(): Deletion is rejected, invalid BOM id");
            return -1l;
        }
        else{
            Query q=em.createQuery("select p from Part p");
            for(Object o:q.getResultList()){
                Part pa=(Part)o;
                Collection<BOM> bb= new ArrayList<BOM>();
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
    //edit bom: make sure BOM_id, furnitureName, partName are all provided
    @Override
    public long editBOM(Long B_ID,String furnitureName,String partName, Integer quan){
        BOM b0 = em.find(BOM.class, B_ID);
        if(b0 ==null){
            System.out.println("wrong b_id");
            return -1l;//wrong bom id;
        }else{
            Query q=em.createQuery("SELECT F FROM Furniture F WHERE F.furnitureName=:name");
            q.setParameter("name", furnitureName);
            if(q.getResultList().isEmpty()){
                System.out.println("wrong furnitureName");
                return -2l;
            }else{
                Query q1 = em.createQuery("select p from Part p where p.partName=:name");
                q1.setParameter("name", partName);
                if(q1.getResultList().isEmpty()){
                    System.out.println("wrong part name");
                    return -3l;
                }else{
                    b0.getFurniture().setFurnitureName(furnitureName);
                    b0.getPart().setPartName(partName);
                    b0.setQuantity(quan);
                    System.out.println("bom is updated /edited successfully");
                    return B_ID;
                }
            }
        }
        
    }
    //search bom by part name
    @Override 
    public List<ArrayList> viewBOMByPart(String partName){
        List<ArrayList> p=new ArrayList();
        Query q=em.createQuery("select p from Part p where p.partName=:name");
        q.setParameter("name", partName);
        if(q.getResultList().isEmpty()){
            System.out.println("no bom for this Part name");
            return p;//p is empty
        }
        else{
            Part part1= (Part)q.getResultList().get(0);
            Collection<BOM> b1p=part1.getBom();
            List<BOM> b2p=new ArrayList<BOM>(b1p);
            for(Object o:b2p){
                ArrayList at=new ArrayList();
                BOM boom=(BOM)o;
                at.add(partName);
                at.add(boom.getId());
                at.add(boom.getQuantity());
                p.add(at);
                // what is included in list<ArrayList> p?
                // p[0] : partName, boomid, boomQuantity
                // p[1] : partName, boomid2, boomQuantity2
                // ....
             }return p;
            
        }
        
        
    }
}
