package entity;

import entity.Furniture;
import entity.TransactionOrder;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(SingleTransactionItem.class)
public class SingleTransactionItem_ { 

    public static volatile SingularAttribute<SingleTransactionItem, Integer> amount;
    public static volatile SingularAttribute<SingleTransactionItem, TransactionOrder> transactionOrder;
    public static volatile SingularAttribute<SingleTransactionItem, Furniture> furniture;
    public static volatile SingularAttribute<SingleTransactionItem, Long> id;

}