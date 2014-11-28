/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Log;
import entity.SystemUser;
//import static entity.SystemUser.userName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import session.stateless.LogSessionLocal;

/**
 *
 * @author Ruoxi
 */
@Named(value = "logManagedBean")
@SessionScoped
public class LogManagedBean implements Serializable {

    @EJB
    private LogSessionLocal logSessionLocal;
    private String systemUser;
    private List<Log> logList = new ArrayList<Log>();

    public String getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(String systemUser) {
        this.systemUser = systemUser;
    }

    public List<Log> getLogList() {
        return logList;
    }

    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }

    public void searchLog() {
        logList.clear();
        logList = logSessionLocal.getLog(systemUser);

    }

    public void getLog() {
        logList.clear();
        logList = logSessionLocal.getAllLog();
    }

}
