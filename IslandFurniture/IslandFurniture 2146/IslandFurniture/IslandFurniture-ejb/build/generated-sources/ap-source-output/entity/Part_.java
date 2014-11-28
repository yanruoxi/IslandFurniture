package entity;

import entity.BOM;
import entity.Contract;
import entity.Inventory;
import entity.Supplier;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(Part.class)
public class Part_ { 

    public static volatile CollectionAttribute<Part, BOM> bom;
    public static volatile SetAttribute<Part, Supplier> suppliers;
    public static volatile SingularAttribute<Part, String> isDeleted;
    public static volatile SingularAttribute<Part, Integer> lotSize;
    public static volatile SingularAttribute<Part, Contract> contract;
    public static volatile SingularAttribute<Part, Integer> leadTime;
    public static volatile SingularAttribute<Part, Long> id;
    public static volatile SingularAttribute<Part, Inventory> inventory;
    public static volatile SingularAttribute<Part, String> partName;

}