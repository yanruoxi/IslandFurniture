package entity;

import entity.Customer;
import entity.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T14:35:33")
@StaticMetamodel(Shoppingcart.class)
public class Shoppingcart_ { 

    public static volatile SingularAttribute<Shoppingcart, Long> id;
    public static volatile SingularAttribute<Shoppingcart, Customer> customer;
    public static volatile CollectionAttribute<Shoppingcart, Product> products;

}