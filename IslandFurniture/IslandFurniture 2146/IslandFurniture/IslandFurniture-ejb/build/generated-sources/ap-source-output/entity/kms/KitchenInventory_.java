package entity.kms;

import entity.kms.Ingredient;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(KitchenInventory.class)
public class KitchenInventory_ { 

    public static volatile SingularAttribute<KitchenInventory, Date> kdateUpdated;
    public static volatile SingularAttribute<KitchenInventory, Long> kinventoryId;
    public static volatile SingularAttribute<KitchenInventory, Ingredient> ingredient;
    public static volatile SingularAttribute<KitchenInventory, Integer> ksafetyStockLevel;
    public static volatile SingularAttribute<KitchenInventory, Integer> kqty;

}