package entity.kms;

import entity.kms.RecipeItem;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(Dish.class)
public class Dish_ { 

    public static volatile SingularAttribute<Dish, String> dishDescription;
    public static volatile SingularAttribute<Dish, byte[]> imageData;
    public static volatile SingularAttribute<Dish, Integer> pax;
    public static volatile SingularAttribute<Dish, String> dishType;
    public static volatile CollectionAttribute<Dish, RecipeItem> recipeItem;
    public static volatile SingularAttribute<Dish, Long> dishId;
    public static volatile SingularAttribute<Dish, Double> dishPrice;
    public static volatile SingularAttribute<Dish, String> dishName;
    public static volatile SingularAttribute<Dish, String> methodOfPreparation;
    public static volatile SingularAttribute<Dish, Boolean> dishDeleteStatus;
    public static volatile SingularAttribute<Dish, String> dishImgLocation;

}