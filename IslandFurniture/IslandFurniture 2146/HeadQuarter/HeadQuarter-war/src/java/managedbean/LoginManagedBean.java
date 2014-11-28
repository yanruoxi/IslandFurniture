/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.Serializable;
import javax.ejb.EJB;
import entity.SystemUser;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import session.stateless.LogSessionLocal;
import session.stateless.SystemUserSessionLocal;

/**
 *
 * @author Ruoxi
 */
@Named(value = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable {

    @EJB
    private SystemUserSessionLocal systemUserSessionLocal;
    @EJB
    private LogSessionLocal logSessionLocal;
    private String userName;
    private String password;
    private String accountType;
    private SystemUser systemUser;
    private String newPassword;
    private String confirmNewPassword;
    private Integer count = 0;

    public LoginManagedBean() {
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

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void doLogin() throws IOException {

        SystemUser result = systemUserSessionLocal.getSystemUser(userName);
        if (result == null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "Wrong user name or password.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            System.out.println(systemUserSessionLocal.getSystemUser(userName).getStatus());
            if (systemUserSessionLocal.getSystemUser(userName).getStatus().equals("blocked")) {
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Account blocked", "Your account is blocked, please contact the admin for more information");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            } else {
                if (comparePassword()) {
                    System.out.println("success login");
                    systemUser = result;
                    System.out.println(systemUser.getUserName());

                    Calendar cal = Calendar.getInstance();
                    logSessionLocal.createLog(userName, "Log in to system", cal);

                    if (systemUser.getAccountType().equals("Admin")) {

                        FacesContext.getCurrentInstance().getExternalContext().redirect("adminWelcome.xhtml");
                    } else if (systemUser.getAccountType().equals("Headquarter Staff")) {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("headquarterWelcome.xhtml");

                    } else if (systemUser.getAccountType().equals("OCRM Staff")) {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("ocrmWelcome.xhtml");
                    }
					else if (systemUser.getAccountType().equals("ACRM Staff")) {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("acrm/acrmWelcome.xhtml");

                    }
                    else {
                        System.out.println("cannot redirect to correct page");
                    }

                } else {
                    System.out.println("wrong password come here");

                    count++;
                    System.out.println(count);
                    if (getCount() == 3) {

                        systemUserSessionLocal.resetStatus(userName, "blocked");
                        FacesMessage msg;
                        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Your account has been blocked.", "Your account has been blocked due to too many wrong password.");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    } else {
                        FacesMessage msg;
                        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid User Name or Password", "Wrong user name or password.");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    }

                }

            }

        }

    }

    public boolean comparePassword() {
        boolean check = false;
        SystemUser systemUser = systemUserSessionLocal.getSystemUser(userName);
        String combinedPassword = systemUser.getPassword();
        String salt = systemUser.getSalt();
        char[] databasePassword = combinedPassword.toCharArray();
        char[] enteredPassword = null;
        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            password = password + salt;
            byte[] tmp = password.getBytes();
 
            md5.update(tmp);
            enteredPassword = systemUser.byteArrToString(md5.digest()).toCharArray();
            int correctCount = 0;
            if (databasePassword.length != enteredPassword.length) {
                return check;
            }
            for (int i = 0; i < databasePassword.length; i++) {
                if (databasePassword[i] == enteredPassword[i]) {
                    correctCount++;
                }
            }
            if (databasePassword.length == correctCount) {
                check = true;
                return check;
            } else {
                return check;
            }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("cannot check password in LoginManagedBean");
        }

        return check;

    }

    public void checkLoggedIn(ComponentSystemEvent cse) {

        FacesContext context = FacesContext.getCurrentInstance();

        if (systemUser == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/HeadQuarter-war/redirectLogin.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void checkLoginAdmin(ComponentSystemEvent cse) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (systemUser == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/HeadQuarter-war/redirectLogin.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
			System.out.println(systemUser.getAccountType());
            if (!systemUser.getAccountType().equalsIgnoreCase("Admin")) {
				System.out.println(systemUser.getAccountType().equalsIgnoreCase("Admin"));
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/HeadQuarter-war/redirectLogin.xhtml?faces-redirect=true");
                } catch (IOException ex) {
                    Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
	 public void checkLoginMRP(ComponentSystemEvent cse) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (systemUser == null) {
            try {
               FacesContext.getCurrentInstance().getExternalContext().redirect("/HeadQuarter-war/redirectLogin.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (!systemUser.getAccountType().equalsIgnoreCase("MRP Staff")) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/HeadQuarter-war/redirectLogin.xhtml?faces-redirect=true");
                } catch (IOException ex) {
                    Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public void checkLoginOCRM(ComponentSystemEvent cse) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (systemUser == null) {
            try {
               FacesContext.getCurrentInstance().getExternalContext().redirect("/HeadQuarter-war/redirectLogin.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (!systemUser.getAccountType().equalsIgnoreCase("OCRM Staff")) {
             try {
                   FacesContext.getCurrentInstance().getExternalContext().redirect("/HeadQuarter-war/redirectLogin.xhtml?faces-redirect=true");
                } catch (IOException ex) {
                    Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
    
     public void checkLoginHQ(ComponentSystemEvent cse) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (systemUser == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/HeadQuarter-war/redirectLogin.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (!systemUser.getAccountType().equalsIgnoreCase("Headquarter Staff")) {

                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/HeadQuarter-war/redirectLogin.xhtml?faces-redirect=true");
                } catch (IOException ex) {
                    Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void logout() {
        Calendar cal = Calendar.getInstance();
        logSessionLocal.createLog(userName, "Log out of system", cal);

        systemUser = null;
        userName = "";
        password = "";
        accountType = "";

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
           FacesContext.getCurrentInstance().getExternalContext().redirect("/HeadQuarter-war/redirectLogin.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void resetPassword() {
        if (comparePassword()) {
            if (!newPassword.equals(confirmNewPassword)) {
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password entered not the same", "Password entered not the same");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {

                Calendar cal = Calendar.getInstance();
                logSessionLocal.createLog(userName, "Reset password", cal);

                systemUserSessionLocal.resetPassword(userName, newPassword);
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Seccessfully Reset Password", "You have successfully reset your password");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }

        } else {

            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Old Password is Wrong", "Wrong Old Password");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

    }

    public SystemUser getCurrentUser() {
        return systemUser;
    }
	
	public void checkLoginACRMStaff(ComponentSystemEvent cse) {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("came to checkLogin Admin");

        if (systemUser == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/HeadQuarter-war/redirectLogin.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            System.out.println(systemUser.getAccountType());
            if (!systemUser.getAccountType().equalsIgnoreCase("ACRM Staff")) {
                System.out.println(systemUser.getAccountType().equalsIgnoreCase("ACRM Staff"));
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/HeadQuarter-war/redirectLogin.xhtml?faces-redirect=true");
                } catch (IOException ex) {
                    Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
public void checkLoginSCM(ComponentSystemEvent cse) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (systemUser == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/HeadQuarter-war/redirectLogin.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (!systemUser.getAccountType().equalsIgnoreCase("SCM Staff")) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/HeadQuarter-war/redirectLogin.xhtml?faces-redirect=true");
                } catch (IOException ex) {
                    Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

}
