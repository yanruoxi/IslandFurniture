package entity;

import entity.RedemptionCart;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(Evoucher.class)
public class Evoucher_ { 

    public static volatile SingularAttribute<Evoucher, String> termCondition;
    public static volatile SingularAttribute<Evoucher, RedemptionCart> redemptionCart;
    public static volatile SingularAttribute<Evoucher, Integer> faceValue;
    public static volatile SingularAttribute<Evoucher, Long> id;
    public static volatile SingularAttribute<Evoucher, Integer> point;

}