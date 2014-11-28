package entity;

import entity.Product;
import entity.ProductionOrder;
import entity.Store;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T14:35:33")
@StaticMetamodel(SalesForcast.class)
public class SalesForcast_ { 

    public static volatile SingularAttribute<SalesForcast, Integer> amount;
    public static volatile SingularAttribute<SalesForcast, Product> product;
    public static volatile SingularAttribute<SalesForcast, String> month;
    public static volatile SingularAttribute<SalesForcast, String> isDeleted;
    public static volatile SingularAttribute<SalesForcast, String> year;
    public static volatile SingularAttribute<SalesForcast, Long> id;
    public static volatile SingularAttribute<SalesForcast, Store> store;
    public static volatile SingularAttribute<SalesForcast, ProductionOrder> productionOrder;
    public static volatile SingularAttribute<SalesForcast, String> status;

}