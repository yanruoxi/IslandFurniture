package entity;

import entity.MRP;
import entity.SalesPlan;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(MPS.class)
public class MPS_ { 

    public static volatile SingularAttribute<MPS, Integer> week;
    public static volatile SingularAttribute<MPS, Integer> workingDays;
    public static volatile SingularAttribute<MPS, MRP> mrp;
    public static volatile SingularAttribute<MPS, Integer> grossRequirment;
    public static volatile SingularAttribute<MPS, String> periodDate;
    public static volatile SingularAttribute<MPS, String> partName;
    public static volatile SingularAttribute<MPS, Integer> onHandAfter;
    public static volatile SingularAttribute<MPS, Integer> plannedOrder;
    public static volatile SingularAttribute<MPS, Integer> MPSweeklyDemand;
    public static volatile SingularAttribute<MPS, Integer> scheduledReceipts;
    public static volatile SingularAttribute<MPS, Long> id;
    public static volatile SingularAttribute<MPS, Integer> onHandBefore;
    public static volatile SingularAttribute<MPS, SalesPlan> plan;

}