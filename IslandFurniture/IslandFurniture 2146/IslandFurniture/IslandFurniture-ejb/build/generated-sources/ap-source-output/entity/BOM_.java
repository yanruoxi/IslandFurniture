package entity;

import entity.Furniture;
import entity.Part;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(BOM.class)
public class BOM_ { 

    public static volatile SingularAttribute<BOM, Integer> quantity;
    public static volatile SingularAttribute<BOM, Furniture> furniture;
    public static volatile SingularAttribute<BOM, Part> part;
    public static volatile SingularAttribute<BOM, Long> id;

}