package entity;

import entity.MPS;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(MRP.class)
public class MRP_ { 

    public static volatile SingularAttribute<MRP, Integer> OnHandBefore;
    public static volatile SingularAttribute<MRP, Integer> onHandAfter;
    public static volatile CollectionAttribute<MRP, MPS> mps;
    public static volatile SingularAttribute<MRP, Integer> plannedOrder;
    public static volatile SingularAttribute<MRP, Integer> schedulesReceipts;
    public static volatile SingularAttribute<MRP, Long> id;
    public static volatile SingularAttribute<MRP, Integer> grossRequirment;

}