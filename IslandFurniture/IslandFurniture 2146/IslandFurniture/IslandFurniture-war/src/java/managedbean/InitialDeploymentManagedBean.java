/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import javax.ejb.EJB;
import java.io.Serializable;
import entity.Company;
import entity.SystemUser;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import session.stateless.CompanySessionLocal;
import session.stateless.SystemUserSessionLocal;

/**
 *
 * @author Ruoxi
 */
@Named(value = "initialDeploymentManagedBean")
@SessionScoped
public class InitialDeploymentManagedBean implements Serializable {

    @EJB
    private CompanySessionLocal companySessionLocal;
    @EJB
    private SystemUserSessionLocal systemUserSessionLocal;
    private String companyName;
    private String location;
    private String type;
    private Company company;

    private String country;
    private String address;
    private String postal;
    private String phone;
    private String email;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void createCompany() {
        companySessionLocal.createCompany(companyName, country, address, postal, phone, email, type);

        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Company Successfully Created", "You have successfully created the company");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("firstAccount.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(InitialDeploymentManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void checkCompany() {
        List<Company> companyList = companySessionLocal.getCompany();
        if (!companyList.isEmpty()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("errorInitialDeployment.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(InitialDeploymentManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void checkSystemUser() {
        System.out.println("error occure here");
        List<SystemUser> systemUserList = systemUserSessionLocal.getAllSystemUser();
        System.out.println("can get the list of users");
        if (!systemUserList.isEmpty()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("errorInitialDeployment.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(InitialDeploymentManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the postal
     */
    public String getPostal() {
        return postal;
    }

    /**
     * @param postal the postal to set
     */
    public void setPostal(String postal) {
        this.postal = postal;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
