/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.SystemUser;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author Ruoxi
 */
@Named(value = "welcomeMessage")
@SessionScoped
public class WelcomeMessage implements Serializable {
    
    @Inject
    private LoginManagedBean loginManagedBean;
   
    
    private SystemUser currentUser;
 

    public void setLoginManagedBean(LoginManagedBean loginManagedBean) {
        this.loginManagedBean = loginManagedBean;
    }
    public SystemUser getCurrentUser() {
        return currentUser=loginManagedBean.getCurrentUser();
    }
    public void setCurrentUser(SystemUser currentUser) {
        this.currentUser = currentUser;
    }
    
    

}
