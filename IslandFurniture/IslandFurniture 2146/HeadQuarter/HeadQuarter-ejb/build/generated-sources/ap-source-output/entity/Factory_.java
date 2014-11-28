package entity;

import entity.DefaultFactory;
import entity.Product;
import entity.ProductionOrder;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T14:35:33")
@StaticMetamodel(Factory.class)
public class Factory_ { 

    public static volatile SingularAttribute<Factory, String> country;
    public static volatile SingularAttribute<Factory, String> address;
    public static volatile SingularAttribute<Factory, String> isDeleted;
    public static volatile SingularAttribute<Factory, String> phone;
    public static volatile ListAttribute<Factory, ProductionOrder> productionOrderList;
    public static volatile SingularAttribute<Factory, String> factoryName;
    public static volatile SingularAttribute<Factory, Long> id;
    public static volatile SingularAttribute<Factory, String> postal;
    public static volatile ListAttribute<Factory, DefaultFactory> defaultFactoryList;
    public static volatile SingularAttribute<Factory, String> email;
    public static volatile SetAttribute<Factory, Product> products;
    public static volatile SingularAttribute<Factory, String> status;

}