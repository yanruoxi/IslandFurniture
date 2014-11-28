/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Ruoxi
 */
@Named
@SessionScoped
public class MenuManagedBean implements Serializable {

    private int active = 0;
    private int scm = 0;
    private int admin=0;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;

    }

    public int getScm() {
        return scm;
    }

    public void setScm(int scm) {
        this.scm = scm;
    }

    public String updateActive(int active) {
        this.active = active;

        if (active == 0) {
            return "headquarterWelcome";
        } else if (active == 1) {
            return "createFactory";
        } else if (active == 2) {
            return "manageFactory";
        } else if (active == 3) {
            return "createStore";
        } else if (active == 4) {
            return "manageStore";
        } else if (active == 5) {
            return "createProduct";
        } else if (active == 6) {
            return "manageProduct";
        } else if (active == 7) {
            return "managePart";
        } else if (active == 8) {
            return "viewProductionOrder";
        } else if (active == 9) {
            return "headquarterLogout";
        }else {
            return "";
        }

    }
    
    public String updateAdmin(int active) {
        this.active = active;

        if (active == 0) {
            return "adminWelcome";
        } else if (active == 1) {
            return "adminCreateAccount";
        } else if (active == 2) {
            return "adminManageAccount";
        } else if (active == 3) {
            return "adminResetPassword";
        } else if (active == 4) {
            return "adminViewLog";
        } else if (active == 5) {
            return "adminManageProfile";
        }else if (active == 6) {
            return "adminLogout";
        } else {
            return "";
        }

    }

}
