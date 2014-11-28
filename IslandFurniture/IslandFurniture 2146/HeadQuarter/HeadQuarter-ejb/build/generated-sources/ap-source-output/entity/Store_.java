package entity;

import entity.DefaultFactory;
import entity.Product;
import entity.ProductionOrder;
import entity.SalesForcast;
import entity.TransactionOrderHQ;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T14:35:33")
@StaticMetamodel(Store.class)
public class Store_ { 

    public static volatile SingularAttribute<Store, String> country;
    public static volatile SingularAttribute<Store, String> address;
    public static volatile ListAttribute<Store, TransactionOrderHQ> transationOrderList;
    public static volatile ListAttribute<Store, ProductionOrder> productionOrderList;
    public static volatile ListAttribute<Store, DefaultFactory> defaultFactoryList;
    public static volatile SetAttribute<Store, Product> products;
    public static volatile ListAttribute<Store, SalesForcast> salesForcastList;
    public static volatile SingularAttribute<Store, String> isDeleted;
    public static volatile SingularAttribute<Store, String> phone;
    public static volatile SingularAttribute<Store, String> storeName;
    public static volatile SingularAttribute<Store, Long> id;
    public static volatile SingularAttribute<Store, String> postal;
    public static volatile SingularAttribute<Store, String> email;

}