/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author user
 */
@Named(value = "mrpManageBean")
@RequestScoped
public class MrpManageBean {

    /**
     * Creates a new instance of MrpManageBean
     */
    public MrpManageBean() {
    }
    
}
