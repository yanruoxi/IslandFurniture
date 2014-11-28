/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.SalesForcast;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ruoxi
 */
@Local
public interface SalesForcastSessionLocal {

    public Boolean checkSalesForcastExist(String storeName, String productName, String year, String month);

    public Boolean checkPastDataExist(String storeName, String productName, String year, String month);

    public Integer createSalesForcast(String storeName, String productName, String year, String month);

    public void createSalesForecast(String storeName, String productName, String year, String month, Integer amount);
    
     public List<SalesForcast> getAllSalesForcast();
     
      public void deleteSalesForecast(SalesForcast sales);

}
