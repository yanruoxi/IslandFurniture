/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import javax.ejb.Local;
import entity.SystemUser;
import java.util.List;

/**
 *
 * @author Ruoxi
 */
@Local
public interface SystemUserSessionLocal {

    public SystemUser getSystemUser(String userName);

    public void addNewAccount(String userName, String password, String salt, String accountType, String email, String dob, String employeeName) ;

    public void resetPassword(String userName, String password);

    public void resetStatus(String userName, String status);

    public List<SystemUser> getAllSystemUser();

    public void deleteSystemUser(String userName);

    public void resetAccountType(String userName, String accountType);

    public void resetEmployeeName(String userName, String employeeName);

    public void resetEmail(String userName, String email);

    public void resetDateOfBirth(String userName, String dateOfBirth);

    public boolean LoginForPOS(String userName, String password);

}
