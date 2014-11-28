package entity;

import entity.Customer;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(Loyalty.class)
public class Loyalty_ { 

    public static volatile SingularAttribute<Loyalty, Float> totalExpenditure;
    public static volatile SingularAttribute<Loyalty, Integer> pointsAvailable;
    public static volatile SingularAttribute<Loyalty, Integer> pointsRedeemed;
    public static volatile SingularAttribute<Loyalty, Long> id;
    public static volatile SingularAttribute<Loyalty, Customer> customer;

}