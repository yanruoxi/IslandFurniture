package entity;

import entity.Company;
import entity.PurchaseOrder;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(ShippingOrder.class)
public class ShippingOrder_ { 

    public static volatile SingularAttribute<ShippingOrder, Date> dateCreated;
    public static volatile SingularAttribute<ShippingOrder, Boolean> isDeleted;
    public static volatile SingularAttribute<ShippingOrder, String> shippingOrderStatus;
    public static volatile SingularAttribute<ShippingOrder, PurchaseOrder> purchaseOrder;
    public static volatile SingularAttribute<ShippingOrder, Company> company;
    public static volatile SingularAttribute<ShippingOrder, Date> shippedOutDate;
    public static volatile SingularAttribute<ShippingOrder, Long> shippingOrderId;

}