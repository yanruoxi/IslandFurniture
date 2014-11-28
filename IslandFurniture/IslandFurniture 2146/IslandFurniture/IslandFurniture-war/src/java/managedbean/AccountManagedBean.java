/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.SystemUser;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import session.stateless.LogSessionLocal;
import session.stateless.SystemUserSessionLocal;
import manager.EmailManager;

/**
 *
 * @author Ruoxi
 */
@Named(value = "accountManagedBean")
@SessionScoped
public class AccountManagedBean implements Serializable {

    @EJB
    private SystemUserSessionLocal systemUserSessionLocal;
    @EJB
    private LogSessionLocal logSessionLocal;
    @Inject
    private LoginManagedBean loginManagedBean;

    @Inject
    private InitialDeploymentManagedBean initialDeploymentManagedBean;

    private String userName;
    private String password;
    private String accountType;
    private String activeUserName;
    private String accountStatus;
    private List<SystemUser> systemUserList = new ArrayList<>();
    private SystemUser currentUser;

    private String employeeName;
    private String email;
    private String dateOfBirth;
    private String newPassword;
    private String confirmNewPassword;

    public void setLoginManagedBean(LoginManagedBean loginManagedBean) {

        this.loginManagedBean = loginManagedBean;
    }

    public AccountManagedBean() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getActiveUserName() {
        return activeUserName;
    }

    public void setActiveUserName(String activeUserName) {
        this.activeUserName = activeUserName;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public List<SystemUser> getSystemUserList() {
        return systemUserList;
    }

    public void setSystemUserList(List<SystemUser> systemUserList) {
        this.systemUserList = systemUserList;
    }

    public SystemUser getCurrentUser() {
        return currentUser = systemUserSessionLocal.getSystemUser(loginManagedBean.getCurrentUser().getUserName());
    }

    public void setCurrentUser(SystemUser currentUser) {
        this.currentUser = currentUser;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the confirmNewPassword
     */
    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    /**
     * @param confirmNewPassword the confirmNewPassword to set
     */
    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public void createAccount() {
        SystemUser result = systemUserSessionLocal.getSystemUser(userName);

        if (result != null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Account Already Exist", "The account you are trying to create is already registered");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            Calendar cal = Calendar.getInstance();
            logSessionLocal.createLog(loginManagedBean.getCurrentUser().getUserName(), "Create new account", cal);

            Random random = new Random();
            Integer value = random.nextInt(50000000);
            String salt = value.toString();

         systemUserSessionLocal.addNewAccount(userName, password, salt, accountType,email, dateOfBirth,employeeName);

            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Account Successfully Created", "You have successfully created an account");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

    public void createFirstAccount() {
        SystemUser result = systemUserSessionLocal.getSystemUser(userName);
        if (result != null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Account Already Exist", "The account you are trying to create is already registered");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {

            Random random = new Random();
            Integer value = random.nextInt(50000000);
            String salt = value.toString();

            ////////
            String companyType = initialDeploymentManagedBean.getType();
            String accountType = "";
            System.out.println("initialDeploymentManagedBean company type:" + companyType);
            if (companyType.equals("Plant")) {
                accountType = "Plant Admin";
            } else if (companyType.equals("Store")) {
                accountType = "Store Admin";
            }

            systemUserSessionLocal.addNewAccount(userName, password, salt, accountType,  email, dateOfBirth, employeeName) ;

            ///////////////

            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Account Successfully Created", "You have successfully created an account");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(AccountManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void searchUser() {
        SystemUser result = systemUserSessionLocal.getSystemUser(userName);
        if (result == null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No such user", "The user you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            systemUserList.clear();
            systemUserList.add(result);
        }

    }

    public void deleteUser(String userName) {
        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(loginManagedBean.getCurrentUser().getUserName(), "Deleted account", cal);

        systemUserSessionLocal.deleteSystemUser(userName);
        systemUserList.clear();
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "User Deleted Successfully", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void viewAllUser() {
        systemUserList.clear();
        systemUserList = systemUserSessionLocal.getAllSystemUser();
    }

    public void resetAccountType() {
        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(loginManagedBean.getCurrentUser().getUserName(), "Changed account type", cal);

        systemUserSessionLocal.resetAccountType(activeUserName, accountType);
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully reset account type", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        systemUserList.clear();
        systemUserList = systemUserSessionLocal.getAllSystemUser();
    }

    public void resetAccountSatus() {
        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(loginManagedBean.getCurrentUser().getUserName(), "Reset account status", cal);

        systemUserSessionLocal.resetStatus(activeUserName, accountStatus);
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully reset account status", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        systemUserList.clear();
        systemUserList = systemUserSessionLocal.getAllSystemUser();
    }

    public void setActiveUser(String userName) {
        setActiveUserName(userName);
    }

    public void resetEmployeename() {
        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(loginManagedBean.getCurrentUser().getUserName(), "Reset Employee Name", cal);

        systemUserSessionLocal.resetEmployeeName(activeUserName, employeeName);
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully reset employee name", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        systemUserList.clear();
        systemUserList = systemUserSessionLocal.getAllSystemUser();
    }

    public void resetEmail() {
        systemUserSessionLocal.resetEmail(activeUserName, email);

        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully reset email", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        systemUserList.clear();
        systemUserList = systemUserSessionLocal.getAllSystemUser();
    }

    public void resetDateOfBirth() {

        systemUserSessionLocal.resetDateOfBirth(activeUserName, dateOfBirth);

        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully reset email", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        systemUserList.clear();
        systemUserList = systemUserSessionLocal.getAllSystemUser();
    }

    public void resetPassword() {

        String emailAddress = systemUserSessionLocal.getSystemUser(activeUserName).getEmail();
        email = "Yan Ruoxi<" + emailAddress + ">";
        Random random = new Random();

        Integer value = random.nextInt(50000000);
        newPassword = value.toString();


        System.out.println("new password is set to " + newPassword);

        EmailManager emailManager = new EmailManager();
        emailManager.setToEmialAddress(email);
        emailManager.emailNewPassword(newPassword, activeUserName);

        systemUserSessionLocal.resetPassword(activeUserName, newPassword);
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Reset Password", "You have successfully reset your password");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

}
