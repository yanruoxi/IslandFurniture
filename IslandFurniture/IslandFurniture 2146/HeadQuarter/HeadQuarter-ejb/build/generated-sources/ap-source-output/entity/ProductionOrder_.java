package entity;

import entity.Factory;
import entity.Product;
import entity.SalesForcast;
import entity.Store;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T14:35:33")
@StaticMetamodel(ProductionOrder.class)
public class ProductionOrder_ { 

    public static volatile SingularAttribute<ProductionOrder, Factory> factory;
    public static volatile SingularAttribute<ProductionOrder, Product> product;
    public static volatile SingularAttribute<ProductionOrder, Integer> quantity;
    public static volatile SingularAttribute<ProductionOrder, String> month;
    public static volatile SingularAttribute<ProductionOrder, String> year;
    public static volatile SingularAttribute<ProductionOrder, SalesForcast> salesForcast;
    public static volatile SingularAttribute<ProductionOrder, Long> id;
    public static volatile SingularAttribute<ProductionOrder, Store> store;

}