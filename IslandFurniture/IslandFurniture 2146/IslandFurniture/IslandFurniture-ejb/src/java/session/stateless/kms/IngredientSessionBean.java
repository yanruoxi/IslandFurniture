/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.kms;

import entity.Part;
import entity.kms.Dish;
import entity.kms.Ingredient;
import entity.kms.KitchenInventory;
import entity.kms.KitchenSupplier;
import entity.kms.RecipeItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Meiling
 */
@Stateless
public class IngredientSessionBean implements IngredientSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;

    // Retrieve all Suppliers
    @Override
    public List<Ingredient> getAllIngredients() {
        Query query = em.createQuery("SELECT i FROM Ingredient i WHERE i.isDeleted = false");
        return query.getResultList();
    }

    @Override
    public List<String> getAllIngredientNames() {
        Query query = em.createQuery("SELECT i.ingredientName FROM Ingredient i WHERE i.isDeleted = false");
        return query.getResultList();
    }

    @Override
    public Ingredient getIngredientByName(String ingredientName) {
        Query query = em.createQuery("SELECT i FROM Ingredient i WHERE i.isDeleted != true and i.ingredientName = :ingredientName");
        query.setParameter("ingredientName", ingredientName);
        Ingredient iDB = null;
        try {
            iDB = (Ingredient) query.getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return iDB;
    }

    @Override
    public boolean addIngredient(String ingredientName, int klotSize, int kleadTime, String ingredientType) {
        Ingredient iDB = getIngredientByName(ingredientName); // Check whether ingredient with same ingredient name already exist
        if (iDB == null) {
            Ingredient i = new Ingredient();
            i.setIngredientName(ingredientName);
            i.setIngredientType(ingredientType);
            i.setIsDeleted(false); // isDeleted default as false
            i.setKleadTime(kleadTime);
            i.setKlotSize(klotSize);

            KitchenInventory kinv = new KitchenInventory();
            kinv.setIngredient(i);
            i.setKitchenInventory(kinv);
            em.persist(i);
            em.persist(kinv);
            em.flush();

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean editIngredient(Ingredient selectedIngredient) {
        Ingredient iDB = em.find(Ingredient.class, selectedIngredient.getId());
        boolean hasSameLotSizeSameLeadTime = checkIngredientSameLotSizeSameLeadTime(iDB, selectedIngredient);
        if (!hasSameLotSizeSameLeadTime) {
            iDB.setKleadTime(selectedIngredient.getKleadTime());
            iDB.setKlotSize(selectedIngredient.getKlotSize());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkIngredientSameLotSizeSameLeadTime(Ingredient iDB, Ingredient selectedIngredient) {
        int selectedIngredientLotSize = selectedIngredient.getKlotSize();
        int iDBIngredientLotSize = iDB.getKlotSize();

        int selectedIngredientLeadTime = selectedIngredient.getKleadTime();
        int iDBLeadTime = iDB.getKleadTime();

        if ((selectedIngredientLotSize == iDBIngredientLotSize) && (selectedIngredientLeadTime == iDBLeadTime)) {
            System.out.println("same lot size and same lead time");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteIngredient(Ingredient selectedIngredient) {
        Ingredient iDB = em.find(Ingredient.class, selectedIngredient.getId());
        if (!iDB.getRecipeItem().isEmpty()) {
            System.out.println("Ingredient linked to RecipeItem, fail to delete");
            return false;
        } else {
            KitchenInventory kinvDB = em.find(KitchenInventory.class, iDB.getKitchenInventory().getKinventoryId());
            kinvDB.setIngredient(null);

            iDB.setIsDeleted(true);
            iDB.setKitchenInventory(null);
            iDB.setKdefaultSupplier(null);
            iDB.setKitchenSuppliers(null);
            System.out.println("I:" + iDB.getKitchenSuppliers());
            em.merge(iDB);
            return true;
        }
    }

    // Retrieve all Part Name
    @Override
    public List<String> getIngredientString() {
        Query query = em.createQuery("SELECT p.ingredientName FROM Ingredient p WHERE p.isDeleted = false");
        List<String> ingredientString = new ArrayList<String>(query.getResultList());
        return ingredientString;
    }

    // Retrieve a Ingredient object based on Ingredient Name
    @Override
    public Ingredient getSelectedIngredientToAdd(String ingredientName) {
        Query query = em.createQuery("SELECT p FROM Ingredient p WHERE p.ingredientName = :ingredientName AND p.isDeleted = false");
        query.setParameter("ingredientName", ingredientName);
        Ingredient result = (Ingredient) query.getSingleResult();
        return result;
    }

    // Add Ingredient(s) to Supplier
    @Override
    public boolean addIngredientToSupplier(KitchenSupplier selectedSupplier, Set<Ingredient> selectedIngredientToAdd) {
        KitchenSupplier sResult = em.find(KitchenSupplier.class, selectedSupplier.getKsupplierId()); // Retrieve existing Supplier object
        List<Ingredient> selectedIngredientToAddList = new ArrayList<Ingredient>(selectedIngredientToAdd); // Convert Set to List

        // If existing Ingredient to Supplier pair does not exist
        if (!ingredientToSupplierExist(selectedSupplier, selectedIngredientToAddList)) {
            for (int i = 0; i < selectedIngredientToAdd.size(); i++) {
                sResult.getIngredient().add(selectedIngredientToAddList.get(i)); // Add List of Part objects to Supplier object
            }
            em.persist(sResult);
            em.flush();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean ingredientToSupplierExist(KitchenSupplier selectedSupplier, List<Ingredient> selectedIngredientToAdd) {
        // Search for existing pair of Part and Supplier based on SupplierId
        Query q = em.createQuery("SELECT p FROM KitchenSupplier s, Ingredient p WHERE s.ingredient = p AND s.ksupplierId = :selectedSupplierId");
        q.setParameter("selectedSupplierId", selectedSupplier.getKsupplierId());
        List<Ingredient> ingredientResultList = q.getResultList();

        // If no records are found
        if (ingredientResultList.isEmpty()) {
            return false;
        } else {
            // Compare partResultList(from db) to selectedPartsToAdd (from checkbox)
            // If both contains the same PartId, it means record is already in db, so it exists
            for (int i = 0; i < ingredientResultList.size(); i++) {
                for (int j = 0; j < selectedIngredientToAdd.size(); j++) {
                    if (selectedIngredientToAdd.get(j).getId().equals(ingredientResultList.get(i).getId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> getIngredientsForSelectedKitchenSupplier(KitchenSupplier selectedSupplierForIngredient) {
        KitchenSupplier s = em.find(KitchenSupplier.class, selectedSupplierForIngredient.getKsupplierId());
        List<Ingredient> getIngredientsForSelectedKitchenSupplier = new ArrayList<Ingredient>(s.getIngredient()); // Convert Set to List
        List<String> getIngredientNameForSelectedKitchenSupplier = new ArrayList<String>();
        for (int i = 0; i < getIngredientsForSelectedKitchenSupplier.size(); i++) {
            getIngredientNameForSelectedKitchenSupplier.add(getIngredientsForSelectedKitchenSupplier.get(i).getIngredientName());
        }
        return getIngredientNameForSelectedKitchenSupplier;
    }

    @Override
    public boolean setDefaultSupplier(KitchenSupplier selectedKitchenSupplierForIngredient, Ingredient p, double unitPrice) {
        Ingredient iDB = em.find(Ingredient.class, p.getId());
        System.out.println("iDB:" + iDB.getIngredientName() + " " + iDB.isHasDefaultSupplier());
        if (iDB.isHasDefaultSupplier()) {
            return false;
        }
        iDB.setHasDefaultSupplier(true);
        iDB.setKdefaultSupplier(selectedKitchenSupplierForIngredient);
        iDB.setDefaultSupplierUnitPrice(unitPrice);
        em.merge(iDB);
        em.flush();
        return true;
    }

    // Update Safety Stock Level and Inventory Level
    @Override
    public void updateKmsInventory(Ingredient selectedIngredient, int safetyStock, int kqty) {
        System.out.println("IngredientSessionBean: updateKmsInventory");
        Long ingredientId = selectedIngredient.getId();
        KitchenInventory inv = new KitchenInventory();
        Query q = em.createQuery("SELECT i FROM KitchenInventory i WHERE i.ingredient.id = :ingredientId");
        q.setParameter("ingredientId", ingredientId);
        inv = (KitchenInventory) q.getSingleResult();

        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        if (safetyStock != 0) {
            inv.setKsafetyStockLevel(safetyStock);
        }
        if (kqty != 0) {
            inv.setKqty(kqty);
        }
        inv.setKdateUpdated(date);
        em.merge(inv);
        em.flush();
    }

    // Add Ingredient(s) to Dish
    @Override
    public Boolean addIngredientToDish(Dish selectedDish, Set<Ingredient> selectedIngredientToAdd) {
        System.out.println("IngredientSessionBean: addIngredientToDish()");
        Dish sResult = em.find(Dish.class, selectedDish.getDishId()); // Retrieve existing Supplier object
        List<Ingredient> selectedIngredientToAddList = new ArrayList<Ingredient>(selectedIngredientToAdd); // Convert Set to List    
        String msg = "";

// If existing Ingredient to Supplier pair does not exist
        if (!ingredientToRecipeToDishExists(selectedDish, selectedIngredientToAddList)) {
            List<RecipeItem> re = new ArrayList<RecipeItem>();
            for (int i = 0; i < selectedIngredientToAdd.size(); i++) {
                RecipeItem r = new RecipeItem();
                r.setQuantity(0);
                r.setUnit(null);
                r.setInstruction(null);
                r.setIngredient(selectedIngredientToAddList.get(i));
                r.setDish(selectedDish);
                re.add(r);
            }
            selectedDish.setRecipeItem(re);
            em.merge(selectedDish);
            em.flush();
            return true;
        }
        return false;
    }

    @Override
    public boolean ingredientToRecipeToDishExists(Dish selectedDish, List<Ingredient> selectedIngredientToAdd) {
        System.out.println("IngredientSessionBean: ingredientToRecipeToDishExists()");
        System.out.println("selected Ingredients To Add: " + selectedIngredientToAdd);

        // Search for existing pair of Part and Supplier based on SupplierId
        Query q = em.createQuery("SELECT i FROM Dish d, Ingredient i WHERE d.dishId = :dishId AND d.recipeItem = i.recipeItem");
        q.setParameter("dishId", selectedDish.getDishId());
        List<Ingredient> ingredientResultList = q.getResultList();
        System.out.println("ingredientResultList: " + ingredientResultList);

        // If no records are found
        if (ingredientResultList.isEmpty()) {
            return false;
        } else {
            // Compare partResultList(from db) to selectedPartsToAdd (from checkbox)
            // If both contains the same PartId, it means record is already in db, so it exists
            for (int i = 0; i < ingredientResultList.size(); i++) {
                for (int j = 0; j < selectedIngredientToAdd.size(); j++) {
                    if (selectedIngredientToAdd.get(j).getId().equals(ingredientResultList.get(i).getId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
