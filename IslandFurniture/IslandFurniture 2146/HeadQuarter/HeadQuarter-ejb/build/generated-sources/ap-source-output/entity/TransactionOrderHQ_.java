package entity;

import entity.Customer;
import entity.SingleTransactionItemHQ;
import entity.Store;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T14:35:33")
@StaticMetamodel(TransactionOrderHQ.class)
public class TransactionOrderHQ_ { 

    public static volatile SingularAttribute<TransactionOrderHQ, String> date;
    public static volatile SingularAttribute<TransactionOrderHQ, String> Status;
    public static volatile SingularAttribute<TransactionOrderHQ, BigDecimal> total;
    public static volatile SingularAttribute<TransactionOrderHQ, String> customerPhone;
    public static volatile SingularAttribute<TransactionOrderHQ, String> month;
    public static volatile SingularAttribute<TransactionOrderHQ, String> transactionReference;
    public static volatile SingularAttribute<TransactionOrderHQ, String> year;
    public static volatile ListAttribute<TransactionOrderHQ, SingleTransactionItemHQ> itemList;
    public static volatile SingularAttribute<TransactionOrderHQ, Boolean> isMember;
    public static volatile SingularAttribute<TransactionOrderHQ, Store> store;
    public static volatile SingularAttribute<TransactionOrderHQ, Long> id;
    public static volatile SingularAttribute<TransactionOrderHQ, Customer> customer;

}