package entity;

import entity.SingleTransactionItem;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(TransactionOrder.class)
public class TransactionOrder_ { 

    public static volatile SingularAttribute<TransactionOrder, String> date;
    public static volatile SingularAttribute<TransactionOrder, BigDecimal> total;
    public static volatile SingularAttribute<TransactionOrder, String> customerPhone;
    public static volatile SingularAttribute<TransactionOrder, String> transactionReference;
    public static volatile ListAttribute<TransactionOrder, SingleTransactionItem> itemList;
    public static volatile SingularAttribute<TransactionOrder, Long> id;
    public static volatile SingularAttribute<TransactionOrder, Boolean> isMember;

}