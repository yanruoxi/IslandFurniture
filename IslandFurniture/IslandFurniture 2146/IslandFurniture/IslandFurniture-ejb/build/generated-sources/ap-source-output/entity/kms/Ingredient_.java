package entity.kms;

import entity.kms.KitchenInventory;
import entity.kms.KitchenSupplier;
import entity.kms.RecipeItem;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(Ingredient.class)
public class Ingredient_ { 

    public static volatile SingularAttribute<Ingredient, String> ingredientType;
    public static volatile SingularAttribute<Ingredient, Double> defaultSupplierUnitPrice;
    public static volatile SingularAttribute<Ingredient, String> ingredientName;
    public static volatile SingularAttribute<Ingredient, Boolean> isDeleted;
    public static volatile SingularAttribute<Ingredient, Integer> klotSize;
    public static volatile CollectionAttribute<Ingredient, RecipeItem> recipeItem;
    public static volatile SingularAttribute<Ingredient, Integer> kleadTime;
    public static volatile SetAttribute<Ingredient, KitchenSupplier> kitchenSuppliers;
    public static volatile SingularAttribute<Ingredient, Long> id;
    public static volatile SingularAttribute<Ingredient, Boolean> hasDefaultSupplier;
    public static volatile SingularAttribute<Ingredient, KitchenSupplier> kdefaultSupplier;
    public static volatile SingularAttribute<Ingredient, KitchenInventory> kitchenInventory;

}