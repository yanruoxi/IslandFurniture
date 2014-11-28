package entity;

import entity.DefaultFactory;
import entity.Factory;
import entity.ProductionOrder;
import entity.Store;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T14:35:33")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, String> instructions;
    public static volatile SingularAttribute<Product, String> color;
    public static volatile SetAttribute<Product, Store> stores;
    public static volatile ListAttribute<Product, ProductionOrder> productionOrderList;
    public static volatile SingularAttribute<Product, BigDecimal> discountPrice;
    public static volatile SetAttribute<Product, Factory> factories;
    public static volatile SingularAttribute<Product, String> type;
    public static volatile ListAttribute<Product, DefaultFactory> defaultFactoryList;
    public static volatile SingularAttribute<Product, String> productName;
    public static volatile SingularAttribute<Product, String> isDeleted;
    public static volatile SingularAttribute<Product, Double> lengths;
    public static volatile SingularAttribute<Product, String> paths;
    public static volatile SingularAttribute<Product, BigDecimal> price;
    public static volatile SingularAttribute<Product, Double> width;
    public static volatile SingularAttribute<Product, Long> id;
    public static volatile SingularAttribute<Product, String> category;
    public static volatile SingularAttribute<Product, String> productDescription;
    public static volatile SingularAttribute<Product, Double> height;
    public static volatile SingularAttribute<Product, String> pomotion;

}