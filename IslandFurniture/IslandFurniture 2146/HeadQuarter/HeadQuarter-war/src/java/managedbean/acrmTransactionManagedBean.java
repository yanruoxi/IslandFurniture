/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import datamodel.TransactionWithTotalSpending;
import entity.TransactionOrderHQ;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import session.stateless.TransactionOrderSessionLocal;

/**
 *
 * @author Meiling
 */
@Named(value = "acrmTranManagedBean")
@SessionScoped
public class acrmTransactionManagedBean implements Serializable {
    @EJB
    private TransactionOrderSessionLocal transactionOrderSession;

    
    // Variables
    private List<TransactionOrderHQ> transactions; // For View All Transactions
    private List<TransactionOrderHQ> filteredTransactions; // For filtering in View All Transactions
    private TransactionOrderHQ selectedTransaction;
    
    // Segmentation
    private ArrayList<TransactionWithTotalSpending> segmentCustByTotalSpendingList = new ArrayList<TransactionWithTotalSpending>();
    private ArrayList<TransactionWithTotalSpending> segmentCustByLowestTotalSpendingList = new ArrayList<TransactionWithTotalSpending>();
    
    
    /**
     * Creates a new instance of acrmTransactionManagedBean
     */
    public acrmTransactionManagedBean() {
    }
    
    @PostConstruct
    public void init() {
        transactions = transactionOrderSession.getAllTransactions(); // Retrieve list of transactions from session bean
        segmentCustByTotalSpendingList = transactionOrderSession.segmentCustByTotalSpending();
        segmentCustByLowestTotalSpendingList = transactionOrderSession.segmentCustByLowestTotalSpending();
    }

    public List<TransactionOrderHQ> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionOrderHQ> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionOrderHQ> getFilteredTransactions() {
        return filteredTransactions;
    }

    public void setFilteredTransactions(List<TransactionOrderHQ> filteredTransactions) {
        this.filteredTransactions = filteredTransactions;
    }

    public TransactionOrderHQ getSelectedTransaction() {
        return selectedTransaction;
    }

    public void setSelectedTransaction(TransactionOrderHQ selectedTransaction) {
        this.selectedTransaction = selectedTransaction;
    }

    public ArrayList<TransactionWithTotalSpending> getSegmentCustByTotalSpendingList() {
        return segmentCustByTotalSpendingList;
    }

    public void setSegmentCustByTotalSpendingList(ArrayList<TransactionWithTotalSpending> segmentCustByTotalSpendingList) {
        this.segmentCustByTotalSpendingList = segmentCustByTotalSpendingList;
    }

    public ArrayList<TransactionWithTotalSpending> getSegmentCustByLowestTotalSpendingList() {
        return segmentCustByLowestTotalSpendingList;
    }

    public void setSegmentCustByLowestTotalSpendingList(ArrayList<TransactionWithTotalSpending> segmentCustByLowestTotalSpendingList) {
        this.segmentCustByLowestTotalSpendingList = segmentCustByLowestTotalSpendingList;
    }
    
}
