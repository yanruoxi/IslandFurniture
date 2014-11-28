/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.Supplier;
import entity.SupplierAcct;
import javax.ejb.Local;

/**
 *
 * @author Meiling
 */
@Local
public interface SupplierAcctSessionBeanLocal {
    
    public SupplierAcct getSupplierAcct(Supplier s);
    
    public void addNewSupplierAcct(String userName, String password, String salt, Supplier s);
    
    public SupplierAcct getSupplierAcctWithUsername(String supplierUsername);
    
    public void resetStatus(String supplierUsername, String status);
    
    public void resetPwd(SupplierAcct supplierAcct, String reEnterNewPwd);
    
    public void resetSupplierAcctStatus(Supplier s, String status);
}
