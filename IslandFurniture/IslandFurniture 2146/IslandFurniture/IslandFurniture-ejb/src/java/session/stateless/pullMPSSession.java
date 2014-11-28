/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.Furniture;
import entity.SalesPlan;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ruoxi
 */
@Stateless
public class pullMPSSession implements pullMPSSessionLocal {
    @PersistenceContext(unitName = "IslandFurniture-ejbPU")
    private EntityManager em;
    @EJB
    private FurnitureSessionLocal furnitureSession;

    @Override
    public void creatMPSHQ(String date, Integer amount, String furnitureName ){
    
        SalesPlan s= new SalesPlan();
        s.setTime(date);
        
        Furniture furniture= furnitureSession.getFurnitureByName(furnitureName);
        s.setFurniture(furniture);
        
    //store amount
        s.setProductionPlan(amount);
        
        em.persist(s);
        em.merge(furniture);
    
    }
    
    @Override
    public Boolean existMPS(String date, String furnitureName ){
    Query query = em.createQuery("SELECT e FROM SalesPlan e WHERE e.time= :inTime AND e.furniture.furnitureName=:inName ");
        query.setParameter("inTime", date);
        query.setParameter("inName", furnitureName);
        
        List<SalesPlan> salesList=query.getResultList();
        
        if(salesList.isEmpty()){
            return true;//no created in database
        
        }else{
             return false;
        }
        
    
    
    }

  
}
