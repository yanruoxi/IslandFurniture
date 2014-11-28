/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Supplier;
import entity.SupplierAcct;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.inject.Inject;
import manager.SupplierEmailManager;
import session.stateless.LogSessionLocal;
import session.stateless.SupplierAcctSessionBeanLocal;
import session.stateless.SupplierSessionBeanLocal;

/**
 *
 * @author Meiling
 */
@Named(value = "supplierAcctManagedBean")
@SessionScoped
public class SupplierAcctManagedBean implements Serializable {

    @EJB
    private SupplierSessionBeanLocal supplierSessionBean;

    @EJB
    private LogSessionLocal logSession;

    @EJB
    private SupplierAcctSessionBeanLocal supplierAcctSessionBean;

    @Inject
    private SupplierManagedBean supplierManagedBean;

    // Variables
    private String supplierUsername;
    private String supplierPwd;
    private SupplierAcct supplierAcct;
    private Integer count = 0;

    // Reset Password
    private String oldPwd;
    private String newPwd;
    private String reEnterNewPwd;

    // Reset Status
    private String newStatus;

    // GETTERS AND SETTERS
    public String getSupplierUsername() {
        return supplierUsername;
    }

    public void setSupplierUsername(String supplierUsername) {
        this.supplierUsername = supplierUsername;
    }

    public String getSupplierPwd() {
        return supplierPwd;
    }

    public void setSupplierPwd(String supplierPwd) {
        this.supplierPwd = supplierPwd;
    }

    public SupplierAcct getSupplierAcct() {
        return supplierAcct;
    }

    public void setSupplierAcct(SupplierAcct supplierAcct) {
        this.supplierAcct = supplierAcct;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getReEnterNewPwd() {
        return reEnterNewPwd;
    }

    public void setReEnterNewPwd(String reEnterNewPwd) {
        this.reEnterNewPwd = reEnterNewPwd;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    /**
     * Creates a new instance of SupplierAcctManagedBean
     */
    public SupplierAcctManagedBean() {
    }

    // Register Supplier Account
    public void registerSupAcct() {
        Supplier s = supplierManagedBean.getSelectedSupplier();
        SupplierAcct result = supplierAcctSessionBean.getSupplierAcct(s);
        if (result != null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Account already exist", "The Supplier Account has already been registered.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            String supEmail = s.getSupplierName() + "<" + s.getSupplierEmailAddr() + ">";
            Random random = new Random();
            Integer value = random.nextInt(50000000);
            String salt = value.toString();
            Integer value2 = random.nextInt(50000000);
            String supPassword = value2.toString();
            String supPasswordSalt = supPassword + salt;
            //String supPassword = value.toString();
            //System.out.println("Supplier Password:" + supPassword);

            supplierAcctSessionBean.addNewSupplierAcct(s.getSupplierEmailAddr(), supPasswordSalt, salt, s);

            SupplierEmailManager supEmailManager = new SupplierEmailManager();
            supEmailManager.setToEmailAddress(supEmail);
            supEmailManager.emailSupAcct(s.getSupplierName(), s.getSupplierEmailAddr(), supPassword);

            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Account Successfully Created", "You have successfully created an account");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void doLogin() {
        try {
            SupplierAcct sa = supplierAcctSessionBean.getSupplierAcctWithUsername(supplierUsername);
            if (sa == null) {
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failure", "Supplier Account does not exist.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                if (sa.getStatus().equals("blocked")) {
                    FacesMessage msg;
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Account blocked", "Your account is blocked. Please contact the Admin for more information.");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    if (comparePassword(supplierPwd)) {
                        System.out.println("success login");
                        supplierAcct = sa;
                        System.out.println(supplierAcct.getSupplierUsername());
                        Calendar cal = Calendar.getInstance();
                        logSession.createLog(sa.getSupplierUsername(), "Log in to system", cal);
                        FacesContext.getCurrentInstance().getExternalContext().redirect("scmSupplierPortalWelcome.xhtml");
                    } else {
                        System.out.println("wrong password");
                        count++;
                        System.out.println(count);
                        if (getCount() == 3) {
                            supplierAcctSessionBean.resetStatus(supplierUsername, "blocked");
                            FacesMessage msg;
                            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Your account has been blocked.", "Your account has been blocked due to too many wrong password attempts.");
                            FacesContext.getCurrentInstance().addMessage(null, msg);
                        } else {
                            FacesMessage msg;
                            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Username or Password", "Wrong user name or password.");
                            FacesContext.getCurrentInstance().addMessage(null, msg);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public boolean comparePassword(String supplierPwd) {
        System.out.println("comparePassword");
        boolean check = false;
        SupplierAcct sa = supplierAcctSessionBean.getSupplierAcctWithUsername(supplierUsername);

        String combinedPassword = sa.getSupplierPwd();
        System.out.println(combinedPassword);

        String salt = sa.getSalt();
        char[] databasePassword = combinedPassword.toCharArray();
        char[] enteredPassword = null;

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            supplierPwd = supplierPwd + salt;
            byte[] tmp = supplierPwd.getBytes();
            md5.update(tmp);
            enteredPassword = sa.byteArrToString(md5.digest()).toCharArray();
            System.out.println(databasePassword);
            System.out.println(enteredPassword);

            int correctCount = 0;
            if (databasePassword.length != enteredPassword.length) {
                System.out.println("length not same");

                return check;
            }
            for (int i = 0; i < databasePassword.length; i++) {
                if (databasePassword[i] == enteredPassword[i]) {
                    correctCount++;
                    System.out.println("I'm correctCount++");
                }
            }
            if (databasePassword.length == correctCount) {
                check = true;
                System.out.println("I'm here");
                return check;
            } else {
                return check;
            }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("cannot check password in SupplierAcctManagedBean");
        }
        return check;
    }

    public void checkLoggedIn(ComponentSystemEvent cse) {
        System.out.println("supplierAcct:" + supplierAcct);
        FacesContext context = FacesContext.getCurrentInstance();
        if (supplierAcct == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("redirectSupplierLogin.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public SupplierAcct getCurrentSupplierAcct() {
        return supplierAcct;
    }

    public void logout() {
        Calendar cal = Calendar.getInstance();
        logSession.createLog(supplierUsername, "Logout of system", cal);
        // Re-initialise
        supplierAcct = null;
        supplierUsername = "";
        supplierPwd = "";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("scmSupplierPortalIndex.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(SupplierAcctManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void resetPwd() {
        System.out.println("oldPwd db:" + supplierAcct.getSupplierPwd());
        if (comparePassword(oldPwd)) {
            if (newPwd.equals(reEnterNewPwd)) {
                supplierAcctSessionBean.resetPwd(supplierAcct, newPwd);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Password has been reset successfully");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect new password", "Please re-enter your new password again");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid old password", "You have entered an invalid old password");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
