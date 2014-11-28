package entity.kms;

import entity.kms.Ingredient;
import entity.kms.KitchenSupplier;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(KitchenPurchaseOrder.class)
public class KitchenPurchaseOrder_ { 

    public static volatile SingularAttribute<KitchenPurchaseOrder, String> kpoIngredientType;
    public static volatile SingularAttribute<KitchenPurchaseOrder, Date> kscheduledDeliveryDate;
    public static volatile SingularAttribute<KitchenPurchaseOrder, Ingredient> ingredient;
    public static volatile SingularAttribute<KitchenPurchaseOrder, Double> ktotalPrice;
    public static volatile SingularAttribute<KitchenPurchaseOrder, Long> kpurchaseOrderId;
    public static volatile SingularAttribute<KitchenPurchaseOrder, Date> kgoodsReceiptDate;
    public static volatile SingularAttribute<KitchenPurchaseOrder, Boolean> kisDeleted;
    public static volatile SingularAttribute<KitchenPurchaseOrder, Double> kunitPrice;
    public static volatile SingularAttribute<KitchenPurchaseOrder, String> kpurchaseOrderStatus;
    public static volatile SingularAttribute<KitchenPurchaseOrder, KitchenSupplier> kitchenSupplier;
    public static volatile SingularAttribute<KitchenPurchaseOrder, Date> kpurchaseOrderDate;
    public static volatile SingularAttribute<KitchenPurchaseOrder, Date> bestBefore;
    public static volatile SingularAttribute<KitchenPurchaseOrder, Integer> kqty;
    public static volatile SingularAttribute<KitchenPurchaseOrder, String> kdeliveryStatus;

}