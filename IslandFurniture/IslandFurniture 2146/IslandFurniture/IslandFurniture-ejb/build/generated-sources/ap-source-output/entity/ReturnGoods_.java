package entity;

import entity.Warehouse;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(ReturnGoods.class)
public class ReturnGoods_ { 

    public static volatile SingularAttribute<ReturnGoods, String> marketPrice;
    public static volatile SingularAttribute<ReturnGoods, Integer> quantity;
    public static volatile SingularAttribute<ReturnGoods, String> deletema;
    public static volatile SingularAttribute<ReturnGoods, String> returnReason;
    public static volatile SingularAttribute<ReturnGoods, Long> id;
    public static volatile SingularAttribute<ReturnGoods, Float> discountprice;
    public static volatile SingularAttribute<ReturnGoods, String> position;
    public static volatile SingularAttribute<ReturnGoods, Warehouse> warehouse;
    public static volatile SingularAttribute<ReturnGoods, String> goodsName;
    public static volatile SingularAttribute<ReturnGoods, String> status;

}