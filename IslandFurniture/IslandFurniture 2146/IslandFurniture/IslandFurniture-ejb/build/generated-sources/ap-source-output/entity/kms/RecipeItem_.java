package entity.kms;

import entity.kms.Dish;
import entity.kms.Ingredient;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(RecipeItem.class)
public class RecipeItem_ { 

    public static volatile SingularAttribute<RecipeItem, String> unit;
    public static volatile SingularAttribute<RecipeItem, Float> quantity;
    public static volatile SingularAttribute<RecipeItem, Ingredient> ingredient;
    public static volatile SingularAttribute<RecipeItem, Dish> dish;
    public static volatile SingularAttribute<RecipeItem, String> instruction;
    public static volatile SingularAttribute<RecipeItem, Long> recipeItemId;

}