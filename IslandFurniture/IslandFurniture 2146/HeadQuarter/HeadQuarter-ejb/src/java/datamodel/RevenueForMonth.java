/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datamodel;

import java.math.BigDecimal;

/**
 *
 * @author ZiGui
 */
public class RevenueForMonth {
    
    private BigDecimal total;
    private int month; 
    
    public RevenueForMonth() {
    }

    public RevenueForMonth(BigDecimal total, int month) {
        this.total = total;
        this.month = month;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    
    
    
    
    
}
