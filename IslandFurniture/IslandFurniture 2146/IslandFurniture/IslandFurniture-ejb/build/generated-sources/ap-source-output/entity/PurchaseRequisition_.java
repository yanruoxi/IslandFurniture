package entity;

import entity.Part;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(PurchaseRequisition.class)
public class PurchaseRequisition_ { 

    public static volatile SingularAttribute<PurchaseRequisition, Long> purchaseReqId;
    public static volatile SingularAttribute<PurchaseRequisition, Date> purchaseReqDate;
    public static volatile SingularAttribute<PurchaseRequisition, Boolean> isDeleted;
    public static volatile SingularAttribute<PurchaseRequisition, String> purchaseReqStatus;
    public static volatile SingularAttribute<PurchaseRequisition, String> createdBy;
    public static volatile SingularAttribute<PurchaseRequisition, Integer> qty;
    public static volatile SingularAttribute<PurchaseRequisition, Part> part;
    public static volatile SingularAttribute<PurchaseRequisition, String> replenishmentType;

}