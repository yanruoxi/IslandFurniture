/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import javax.ejb.Local;

/**
 *
 * @author Ruoxi
 */
@Local
public interface pullMPSSessionLocal {
    
     public void creatMPSHQ(String date, Integer amount, String furnitureName );
     
         public Boolean existMPS(String date, String furnitureName );
    
}
