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
    private int mrp=0;
    private int bom=0;

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
    
    public int getMrp() {
        return mrp;
    }

    public void setMrp(int mrp) {
        this.mrp = mrp;
    }
      
    public int getBom() {
        return bom;
    }

   
    public void setBom(int bom) {
        this.bom = bom;
    }



    public String updateActive(int active) {
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

    public String updateScm(int scm) {
        this.scm = scm;
        if (scm == 0) {
            return "scmWelcome";
        } else if (scm == 1) {
            return "scmViewAllSuppliers";
        } else if (scm == 2) {
            return "scmViewAllContracts";
        } else if (scm == 3) {
            return "scmAddPurchaseRequisition";
        }else if (scm == 4) {
            return "scmViewAllInventory";
        }  else if (scm == 5) {
            return "adminResetPassword";

        } else if (scm == 6) {
            return "adminLogout";

        } else {

            return "";
        }

    }
    
    public String updateMrp(int mrp) {
        this.mrp=mrp;
      
        if (mrp == 0) {
            return "mrpWelcom";
        } else if (mrp == 1) {
            return "mrpManageOrder";
        } else if (mrp == 2) {
            return "mrpManagePart";
        } else if (mrp == 3) {
            return "mrpManageBom";
        } else if (mrp == 4) {
            return "viewMPS";

        } else if (mrp == 5) {
            return "viewWeeklyPlan";

        } else if (mrp == 6) {
            return "ManageMRP";

        } else if (mrp == 7) {
            return "mrpManageProfile";

        } else if (mrp == 8) {
            return "mrpLogout";

        } else {

            return "";
        }

    }
    
     public String updateBom(int bom) {
   
        this.bom=bom;

        if (bom == 0) {
            return "mrpViewBom";
        } else if (bom== 1) {
            return "mrpAddBom2";
        }  else {
            return "";
        }

    }

  
   
}
