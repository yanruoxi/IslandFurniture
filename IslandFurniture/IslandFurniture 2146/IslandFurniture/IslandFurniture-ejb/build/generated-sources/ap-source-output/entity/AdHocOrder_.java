package entity;

import entity.Furniture;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(AdHocOrder.class)
public class AdHocOrder_ { 

    public static volatile SingularAttribute<AdHocOrder, Integer> amount;
    public static volatile SingularAttribute<AdHocOrder, Furniture> furniture;
    public static volatile SingularAttribute<AdHocOrder, String> requestStore;
    public static volatile SingularAttribute<AdHocOrder, String> plant;
    public static volatile SingularAttribute<AdHocOrder, Long> id;
    public static volatile SingularAttribute<AdHocOrder, String> deadline;
    public static volatile SingularAttribute<AdHocOrder, String> status;

}