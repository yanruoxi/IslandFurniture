/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.Log;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ruoxi
 */
@Local
public interface LogSessionLocal {
    
    public void createLog(String systemUser, String activity, Calendar activityTime);
    
     public List<Log> getLog(String systemUser);
     
     public List<Log> getAllLog();
    
}
