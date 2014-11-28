package entity;

import entity.Furniture;
import entity.Part;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(Inventory.class)
public class Inventory_ { 

    public static volatile SingularAttribute<Inventory, Integer> quantity;
    public static volatile SingularAttribute<Inventory, Furniture> furniture;
    public static volatile SingularAttribute<Inventory, Part> part;
    public static volatile SingularAttribute<Inventory, Long> inventoryId;
    public static volatile SingularAttribute<Inventory, Date> dateUpdated;

}