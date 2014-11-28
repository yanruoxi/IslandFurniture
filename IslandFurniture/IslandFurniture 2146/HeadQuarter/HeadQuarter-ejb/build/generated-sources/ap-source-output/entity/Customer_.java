package entity;

import entity.Loyalty;
import entity.RedemptionCart;
import entity.Shoppingcart;
import entity.TransactionOrderHQ;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T14:35:33")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, String> country;
    public static volatile SingularAttribute<Customer, String> gender;
    public static volatile ListAttribute<Customer, TransactionOrderHQ> transationOrderList;
    public static volatile SingularAttribute<Customer, String> postalCode;
    public static volatile SingularAttribute<Customer, Loyalty> loyalty;
    public static volatile SingularAttribute<Customer, String> customerName;
    public static volatile SingularAttribute<Customer, RedemptionCart> cart;
    public static volatile SingularAttribute<Customer, String> password;
    public static volatile SingularAttribute<Customer, String> isDeleted;
    public static volatile SingularAttribute<Customer, String> phone;
    public static volatile SingularAttribute<Customer, String> dob;
    public static volatile SingularAttribute<Customer, Shoppingcart> shoppingcart;
    public static volatile SingularAttribute<Customer, String> addressLine1;
    public static volatile SingularAttribute<Customer, Long> id;
    public static volatile SingularAttribute<Customer, String> email;
    public static volatile SingularAttribute<Customer, String> status;

}