/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.Customer;
import entity.Evoucher;
import entity.PointPolicy;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author li
 */
@Local
public interface LoyoltySessionBeanLocal {
    public Evoucher addNewVoucher(Integer faceValue,Integer points,String termCondition);
    public List<Evoucher> getVouchers();
    public void deleteVoucher(long id);
    public Customer viewCustomer(long id);
    public List<Customer> viewAllCustomers();
    public Customer updataOrderStatus(long id,String status);
    public List<PointPolicy> getPolicies();
    public PointPolicy addNewRatio(String currency,float ratio);
    public void deletePolicy(long id);
    public void updatePolicy(float newValue,long policyId);
}
