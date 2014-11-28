package entity;

import entity.Furniture;
import entity.Warehouse;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(Front.class)
public class Front_ { 

    public static volatile CollectionAttribute<Front, Furniture> materialCollection;
    public static volatile SingularAttribute<Front, Long> Id;
    public static volatile SingularAttribute<Front, Warehouse> warehouse;
    public static volatile SingularAttribute<Front, String> frontName;

}