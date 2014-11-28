/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ruoxi
 */
@ManagedBean
public class IdleMonitorView {
 public void onIdle() {     
     FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No Activity", "Session will timeout soon");
                FacesContext.getCurrentInstance().addMessage(null, msg); 
      
    }
 
    public void onActive() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        "Welcome Back", "Hope you had a good rest"));
    }
}
