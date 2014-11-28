/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Company;
import entity.SystemUser;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;
import session.stateless.CompanySessionLocal;

/**
 *
 * @author Ruoxi
 */
@Named(value = "welcomeMessage")
@SessionScoped
public class WelcomeMessage implements Serializable {
    
    @Inject
    private LoginManagedBean loginManagedBean;
    @EJB
    private CompanySessionLocal companySessionLocal;
    
    private SystemUser currentUser;
    private Company company;

    public void setLoginManagedBean(LoginManagedBean loginManagedBean) {
        this.loginManagedBean = loginManagedBean;
    }
    public SystemUser getCurrentUser() {
        return currentUser=loginManagedBean.getCurrentUser();
    }
    public void setCurrentUser(SystemUser currentUser) {
        this.currentUser = currentUser;
    }
    public Company getCompany() {
        return company=companySessionLocal.getCompanySingleResult();
    }
    public void setCompany(Company company) {
        this.company = company;
    }

}
