package entity;

import entity.AdHocOrder;
import entity.BOM;
import entity.Front;
import entity.Inventory;
import entity.SalesPlan;
import entity.SingleTransactionItem;
import entity.Warehouse;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(Furniture.class)
public class Furniture_ { 

    public static volatile CollectionAttribute<Furniture, BOM> bom;
    public static volatile SingularAttribute<Furniture, String> color;
    public static volatile SingularAttribute<Furniture, Integer> POSquantity;
    public static volatile SingularAttribute<Furniture, String> furnitureName;
    public static volatile SingularAttribute<Furniture, String> materialPosition;
    public static volatile SingularAttribute<Furniture, Inventory> inventory;
    public static volatile SingularAttribute<Furniture, String> type;
    public static volatile SingularAttribute<Furniture, String> isDeleted;
    public static volatile SingularAttribute<Furniture, BigDecimal> price;
    public static volatile SingularAttribute<Furniture, Integer> frontQuantity;
    public static volatile SingularAttribute<Furniture, Long> id;
    public static volatile CollectionAttribute<Furniture, SalesPlan> plan;
    public static volatile SingularAttribute<Furniture, String> productDescription;
    public static volatile CollectionAttribute<Furniture, AdHocOrder> order;
    public static volatile SingularAttribute<Furniture, Double> height;
    public static volatile SingularAttribute<Furniture, String> pomotion;
    public static volatile SingularAttribute<Furniture, Integer> safetyStock;
    public static volatile SingularAttribute<Furniture, Warehouse> warehouse;
    public static volatile ListAttribute<Furniture, SingleTransactionItem> singleTransactionItemList;
    public static volatile SingularAttribute<Furniture, Double> lengths;
    public static volatile SingularAttribute<Furniture, Double> width;
    public static volatile SingularAttribute<Furniture, String> position;
    public static volatile SingularAttribute<Furniture, Front> front;
    public static volatile SingularAttribute<Furniture, String> category;
    public static volatile SingularAttribute<Furniture, Integer> backQuantity;

}