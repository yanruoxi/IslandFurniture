package entity;

import entity.Furniture;
import entity.MPS;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(SalesPlan.class)
public class SalesPlan_ { 

    public static volatile CollectionAttribute<SalesPlan, MPS> mps;
    public static volatile SingularAttribute<SalesPlan, Integer> workingDays;
    public static volatile SingularAttribute<SalesPlan, Furniture> furniture;
    public static volatile SingularAttribute<SalesPlan, Integer> forecast;
    public static volatile SingularAttribute<SalesPlan, Long> id;
    public static volatile SingularAttribute<SalesPlan, String> time;
    public static volatile SingularAttribute<SalesPlan, Integer> productionPlan;
    public static volatile SingularAttribute<SalesPlan, Integer> inventory;

}