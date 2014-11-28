/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.AdHocOrder;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author li
 */
@Local
public interface adhocOrderSessionBeanLocal {
    public ArrayList<AdHocOrder> viewOrder(String plant);
    public ArrayList<AdHocOrder> viewAllOrders();
    public String updataOrderStatus(long id,String status);
    
}
