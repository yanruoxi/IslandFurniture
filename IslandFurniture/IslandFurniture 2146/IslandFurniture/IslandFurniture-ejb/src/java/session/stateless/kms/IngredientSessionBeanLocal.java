/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless.kms;

import entity.kms.Dish;
import entity.kms.Ingredient;
import entity.kms.KitchenSupplier;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author Meiling
 */
@Local
public interface IngredientSessionBeanLocal {
    public List<Ingredient> getAllIngredients();
    public Ingredient getIngredientByName(String ingredientName);
    public List<String> getAllIngredientNames();
    public boolean addIngredient(String ingredientName, int klotSize, int kleadTime, String ingredientType);
    public boolean editIngredient(Ingredient selectedIngredient);
    public boolean checkIngredientSameLotSizeSameLeadTime(Ingredient iDB, Ingredient selectedIngredient);
    public boolean deleteIngredient(Ingredient selectedIngredient);
    public List<String> getIngredientString();
    public Ingredient getSelectedIngredientToAdd(String ingredientName);

    public boolean addIngredientToSupplier(KitchenSupplier selectedSupplier, Set<Ingredient> selectedIngredientToAdd);

    public boolean ingredientToSupplierExist(KitchenSupplier selectedSupplier, List<Ingredient> selectedIngredientToAdd);
    public List<String> getIngredientsForSelectedKitchenSupplier(KitchenSupplier selectedSupplierForIngredient);
    public boolean setDefaultSupplier(KitchenSupplier selectedKitchenSupplierForIngredient, Ingredient p, double unitPrice);
    
    public void updateKmsInventory(Ingredient selectedIngredient, int safetyStock, int kqty);
        
    public Boolean addIngredientToDish(Dish selectedDish, Set<Ingredient> selectedIngredientToAdd);
        public boolean ingredientToRecipeToDishExists(Dish selectedDish, List<Ingredient> selectedIngredientToAdd);
        
        
}
