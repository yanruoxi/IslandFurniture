package entity;

import entity.Part;
import entity.Supplier;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(PurchaseOrder.class)
public class PurchaseOrder_ { 

    public static volatile SingularAttribute<PurchaseOrder, Double> unitPrice;
    public static volatile SingularAttribute<PurchaseOrder, Date> goodsReceiptDate;
    public static volatile SingularAttribute<PurchaseOrder, Date> goodsIssuedDate;
    public static volatile SingularAttribute<PurchaseOrder, Double> totalPrice;
    public static volatile SingularAttribute<PurchaseOrder, String> negoCondition;
    public static volatile SingularAttribute<PurchaseOrder, Part> part;
    public static volatile SingularAttribute<PurchaseOrder, String> purchaseOrderStatus;
    public static volatile SingularAttribute<PurchaseOrder, String> replenishmentType;
    public static volatile SingularAttribute<PurchaseOrder, String> goodsType;
    public static volatile SingularAttribute<PurchaseOrder, Date> scheduledDeliveryDate;
    public static volatile SingularAttribute<PurchaseOrder, Date> purchaseOrderDate;
    public static volatile SingularAttribute<PurchaseOrder, Boolean> isDeleted;
    public static volatile SingularAttribute<PurchaseOrder, Long> purchaseOrderId;
    public static volatile SingularAttribute<PurchaseOrder, Integer> qty;
    public static volatile SingularAttribute<PurchaseOrder, Supplier> supplier;
    public static volatile SingularAttribute<PurchaseOrder, String> deliveryStatus;

}