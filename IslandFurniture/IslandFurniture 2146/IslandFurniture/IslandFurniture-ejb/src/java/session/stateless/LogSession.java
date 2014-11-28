/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Log;
import entity.SystemUser;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Ruoxi
 */
@Stateless
public class LogSession implements LogSessionLocal {
    @PersistenceContext(unitName = "IslandFurniture-ejbPU")
    private EntityManager entityManager;
    
    @Override
    public void createLog(String systemUser, String activity, Calendar activityTime){
    
        Log log= new Log();
        log.setActivity(activity);
        log.setActivityTime(activityTime.getTime());
        log.setSystemUser(systemUser);
        
        entityManager.persist(log);
        entityManager.flush();  
    }
      @Override
    public List<Log> getLog(String systemUser) {
        
        Query query = entityManager.createQuery("SELECT u FROM Log u WHERE u.systemUser = :inSystemUser");
        query.setParameter("inSystemUser", systemUser);
 
        return query.getResultList();
    }
    @Override
    public List<Log> getAllLog() {
       Query query = entityManager.createQuery("SELECT e FROM Log e");
 
        return query.getResultList();
    }
   

   
    
}
