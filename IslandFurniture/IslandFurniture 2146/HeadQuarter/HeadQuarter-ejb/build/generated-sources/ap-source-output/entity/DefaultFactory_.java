package entity;

import entity.Factory;
import entity.Product;
import entity.Store;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T14:35:33")
@StaticMetamodel(DefaultFactory.class)
public class DefaultFactory_ { 

    public static volatile SingularAttribute<DefaultFactory, Factory> factory;
    public static volatile SingularAttribute<DefaultFactory, Product> product;
    public static volatile SingularAttribute<DefaultFactory, String> rank;
    public static volatile SingularAttribute<DefaultFactory, Long> id;
    public static volatile SingularAttribute<DefaultFactory, Store> store;

}