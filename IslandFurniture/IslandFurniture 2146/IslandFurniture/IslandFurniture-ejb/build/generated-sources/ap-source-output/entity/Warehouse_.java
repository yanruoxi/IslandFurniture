package entity;

import entity.Front;
import entity.Furniture;
import entity.Furniture;
import entity.Furniture;
import entity.ReturnGoods;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(Warehouse.class)
public class Warehouse_ { 

    public static volatile SingularAttribute<Warehouse, Furniture[][]> BigArray;
    public static volatile CollectionAttribute<Warehouse, Furniture> materialCollection;
    public static volatile CollectionAttribute<Warehouse, ReturnGoods> returnGoodsCollection;
    public static volatile SingularAttribute<Warehouse, String> address;
    public static volatile SingularAttribute<Warehouse, String> deletema;
    public static volatile SingularAttribute<Warehouse, entity.Furniture[][]> RG;
    public static volatile SingularAttribute<Warehouse, Long> Id;
    public static volatile SingularAttribute<Warehouse, Front> front;
    public static volatile SingularAttribute<Warehouse, String> warehouseName;
    public static volatile SingularAttribute<Warehouse, Furniture[][][]> SUArray;

}