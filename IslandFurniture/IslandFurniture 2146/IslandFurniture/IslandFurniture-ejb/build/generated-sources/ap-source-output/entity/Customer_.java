package entity;

import entity.Loyalty;
import entity.RedemptionCart;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, String> lastName;
    public static volatile SingularAttribute<Customer, String> address;
    public static volatile SingularAttribute<Customer, String> city;
    public static volatile SingularAttribute<Customer, Integer> postalCode;
    public static volatile SingularAttribute<Customer, Loyalty> loyalty;
    public static volatile SingularAttribute<Customer, String> birthDate;
    public static volatile SingularAttribute<Customer, RedemptionCart> cart;
    public static volatile SingularAttribute<Customer, String> identityNo;
    public static volatile SingularAttribute<Customer, String> firstName;
    public static volatile SingularAttribute<Customer, String> password;
    public static volatile SingularAttribute<Customer, Integer> phoneNumber;
    public static volatile SingularAttribute<Customer, String> nationality;
    public static volatile SingularAttribute<Customer, Long> id;
    public static volatile SingularAttribute<Customer, String> email;
    public static volatile SingularAttribute<Customer, String> honorific;
    public static volatile SingularAttribute<Customer, String> status;

}