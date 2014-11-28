/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Ruoxi
 */
@Entity
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String systemUser;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date activityTime;
    private String activity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getSystemUser() {
        return systemUser;
    }
    public void setSystemUser(String systemUser) {
        this.systemUser = systemUser;
    }
    public String getActivity() {
        return activity;
    }
    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getActivityTime() {
        return activityTime;
    }
    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }
    
}
