/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datamodel;

import entity.TransactionOrderHQ;
import java.math.BigDecimal;

/**
 *
 * @author Meiling
 */
public class TransactionWithTotalSpending {
    private TransactionOrderHQ tran;
    private BigDecimal totalSpending;

    public TransactionWithTotalSpending(TransactionOrderHQ transaction, BigDecimal totalSpending) {
        this.tran = transaction;
        this.totalSpending = totalSpending;
    }

    public TransactionWithTotalSpending() {
    }

    public TransactionOrderHQ getTran() {
        return tran;
    }

    public void setTran(TransactionOrderHQ tran) {
        this.tran = tran;
    }

    public BigDecimal getTotalSpending() {
        return totalSpending;
    }

    public void setTotalSpending(BigDecimal totalSpending) {
        this.totalSpending = totalSpending;
    }
    
    
}
