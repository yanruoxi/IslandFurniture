package entity;

import entity.Product;
import entity.TransactionOrderHQ;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T14:35:33")
@StaticMetamodel(SingleTransactionItemHQ.class)
public class SingleTransactionItemHQ_ { 

    public static volatile SingularAttribute<SingleTransactionItemHQ, Integer> amount;
    public static volatile SingularAttribute<SingleTransactionItemHQ, TransactionOrderHQ> transactionOrder;
    public static volatile SingularAttribute<SingleTransactionItemHQ, Product> furntiture;
    public static volatile SingularAttribute<SingleTransactionItemHQ, Long> id;

}