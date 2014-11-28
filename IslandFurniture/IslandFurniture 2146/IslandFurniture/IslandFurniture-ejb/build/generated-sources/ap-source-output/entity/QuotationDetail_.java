package entity;

import entity.Part;
import entity.PurchaseRequisition;
import entity.Supplier;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(QuotationDetail.class)
public class QuotationDetail_ { 

    public static volatile SingularAttribute<QuotationDetail, Long> quotationDetailId;
    public static volatile SingularAttribute<QuotationDetail, PurchaseRequisition> purchaseReq;
    public static volatile SingularAttribute<QuotationDetail, Date> quotationEndingDate;
    public static volatile SingularAttribute<QuotationDetail, Part> part;
    public static volatile SingularAttribute<QuotationDetail, String> replenishmentType;
    public static volatile SingularAttribute<QuotationDetail, String> goodsType;
    public static volatile SingularAttribute<QuotationDetail, Date> lastUpdated;
    public static volatile SingularAttribute<QuotationDetail, Date> quotedDate;
    public static volatile SingularAttribute<QuotationDetail, Boolean> isDeleted;
    public static volatile SingularAttribute<QuotationDetail, Date> quotedScheduledDeliveryDate;
    public static volatile SingularAttribute<QuotationDetail, Integer> qty;
    public static volatile SingularAttribute<QuotationDetail, Supplier> supplier;
    public static volatile SingularAttribute<QuotationDetail, Double> quotedUnitPrice;
    public static volatile SingularAttribute<QuotationDetail, String> quotedNegoCondition;
    public static volatile SingularAttribute<QuotationDetail, Date> quotedSentDate;
    public static volatile SingularAttribute<QuotationDetail, Double> quotedTotalPrice;
    public static volatile SingularAttribute<QuotationDetail, String> quotationDetailStatus;

}