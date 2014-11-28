package entity;

import entity.Customer;
import entity.Evoucher;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T14:35:33")
@StaticMetamodel(RedemptionCart.class)
public class RedemptionCart_ { 

    public static volatile SingularAttribute<RedemptionCart, String> createTime;
    public static volatile SingularAttribute<RedemptionCart, Long> id;
    public static volatile CollectionAttribute<RedemptionCart, Evoucher> vouchers;
    public static volatile SingularAttribute<RedemptionCart, Integer> points;
    public static volatile SingularAttribute<RedemptionCart, Customer> customer;

}