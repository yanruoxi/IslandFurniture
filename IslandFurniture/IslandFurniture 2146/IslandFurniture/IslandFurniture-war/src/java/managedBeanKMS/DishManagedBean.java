/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeanKMS;

import entity.kms.Dish;
import entity.kms.Ingredient;
import entity.kms.RecipeItem;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import session.stateless.kms.DishSessionBeanLocal;
import session.stateless.kms.IngredientSessionBeanLocal;
import session.stateless.kms.RecipeSessionBeanLocal;

/**
 *
 * @author ZiGui
 */
@Named(value = "dishManagedBean")
@ViewScoped
@RequestScoped
public class DishManagedBean {

    @EJB
    private DishSessionBeanLocal dishSessionBean;
    @EJB
    private IngredientSessionBeanLocal ingredientSessionBean;
    @EJB
    private RecipeSessionBeanLocal recipeSessionBean;

    @PostConstruct
    public void init() {
        dishes = dishSessionBean.getAllDishes();
        dishTypeList = dishSessionBean.getAllDishType();
        mainDish = dishSessionBean.getMainDish();
        dessertDish = dishSessionBean.getDessertDish();
        beverageDish = dishSessionBean.getBeverageDish();
        ingredients = ingredientSessionBean.getIngredientString();

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        if (paramMap.containsKey("dish")) {
            String dishIdString = paramMap.get("dish");
            dishToEdit = dishSessionBean.getDishWithId(Long.parseLong(dishIdString));
        }
    }

    public DishManagedBean() {
    }

    // Variables
    private List<Dish> dishes;  // For View All Dishes
    private List<Dish> filteredDish; // For filtering in View All Dishes
    private List<String> dishTypeList;
    private List<Dish> mainDish;
    private Dish selectedDish;
    private List<Dish> dessertDish;
    private List<Dish> beverageDish;

    // For adding ingredients to recipe
    private String[] selectedIngredient;   // For Add Ingredient to Dish/Recipe
    private List<String> ingredients; // For Add Ingredient to Dish/Recipe
    private List<String> ingredientNamesForSelectedDish; // For propogating Ingredient Names based on selected Dish 
    private RecipeItem selectedRecipe;

    private String dishName;
    private String methodOfPreparation;
    private Integer pax;
    private String dishDescription;
    private String dishType;
    private Double dishPrice;
    private String dishImgLocation;
    private UIComponent dishNameInput;
    private Dish dishToEdit;
    private UploadedFile file;

    private float quantity;
    private String unit;

    // GETTERS AND SETTERS
    public List<Dish> getBeverageDish() {
        return beverageDish;
    }

    public void setBeverageDish(List<Dish> beverageDish) {
        this.beverageDish = beverageDish;
    }

    public List<Dish> getDessertDish() {
        return dessertDish;
    }

    public void setDessertDish(List<Dish> dessertDish) {
        this.dessertDish = dessertDish;
    }

    public List<Dish> getMainDish() {
        return mainDish;
    }

    public void setMainDish(List<Dish> mainDish) {
        this.mainDish = mainDish;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Dish> getFilteredDish() {
        return filteredDish;
    }

    public void setFilteredDish(List<Dish> filteredDish) {
        this.filteredDish = filteredDish;
    }

    public Dish getSelectedDish() {
        return selectedDish;
    }

    public void setSelectedDish(Dish selectedDish) {
        this.selectedDish = selectedDish;
    }

    public List<String> getDishTypeList() {
        return dishTypeList;
    }

    public void setDishTypeList(List<String> dishTypeList) {
        this.dishTypeList = dishTypeList;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getMethodOfPreparation() {
        return methodOfPreparation;
    }

    public void setMethodOfPreparation(String methodOfPreparation) {
        this.methodOfPreparation = methodOfPreparation;
    }

    public Integer getPax() {
        return pax;
    }

    public void setPax(Integer pax) {
        this.pax = pax;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public Double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(Double dishPrice) {
        this.dishPrice = dishPrice;
    }

    public String getDishImgLocation() {
        return dishImgLocation;
    }

    public void setDishImgLocation(String dishImgLocation) {
        this.dishImgLocation = dishImgLocation;
    }

    public UIComponent getDishNameInput() {
        return dishNameInput;
    }

    public void setDishNameInput(UIComponent dishNameInput) {
        this.dishNameInput = dishNameInput;
    }

    public Dish getDishToEdit() {
        return dishToEdit;
    }

    public void setDishToEdit(Dish dishToEdit) {
        this.dishToEdit = dishToEdit;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String[] getSelectedIngredient() {
        return selectedIngredient;
    }

    public void setSelectedIngredient(String[] selectedIngredient) {
        this.selectedIngredient = selectedIngredient;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredientNamesForSelectedDish() {
        return ingredientNamesForSelectedDish;
    }

    public void setIngredientNamesForSelectedDish(List<String> ingredientNamesForSelectedDish) {
        this.ingredientNamesForSelectedDish = ingredientNamesForSelectedDish;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    // END OF GETTERS AND SETTERS
    // Filter dishes by dish name
    public boolean filterByDishName(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            System.err.println("1");
            return true;
        }
        if (value == null) {
            System.err.println("2");
            return false;
        }
        System.err.println("3: " + value.toString().toLowerCase().contains(filter.toString().toLowerCase(locale)));
        if (filteredDish != null) {
            System.err.println("filteredDish: " + filteredDish.toString());
        }
        return value.toString().toLowerCase().contains(filter.toString().toLowerCase());
    }

    // Filter dishes by method of preparation
    public boolean filterByMethod(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            System.err.println("1");
            return true;
        }
        if (value == null) {
            System.err.println("2");
            return false;
        }
        System.err.println("3: " + value.toString().toLowerCase().contains(filter.toString().toLowerCase(locale)));
        if (filteredDish != null) {
            System.err.println("filteredDish: " + filteredDish.toString());
        }
        return value.toString().toLowerCase().contains(filter.toString().toLowerCase());
    }

    // Add a new Dish
    public void addDish() throws IOException {
        int check = dishSessionBean.checkDish(dishName);
        if (pax > 0) {
            if (check == 1) {
                System.out.println("DishManagedBean: Adding dish");
                dishSessionBean.addDish(dishName, methodOfPreparation, pax, dishDescription, dishType, dishPrice);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dish Successfully Added", "You have successfully added a dish");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().redirect("kmsViewAllDish.xhtml?i=4");
            } else if (check == 2) {
                System.out.println("Dish to add already in database");
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dish Already Exists", "The dish you are trying to add is already in database");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else if (check == 3) {
                System.out.println("Dish was previously deleted");
                dishSessionBean.addDish(dishName, methodOfPreparation, pax, dishDescription, dishType, dishPrice);
                FacesContext.getCurrentInstance().getExternalContext().redirect("kmsViewAllDish.xhtml?i=4");
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No. of Pax cannot be less than 1");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    // Check for Existing Dish
    public void checkExistingDish() {
        int check = dishSessionBean.checkDish(dishName);

        if (check == 1) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(dishNameInput.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Valid Dish ", null));
        } else if (check == 2) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(dishNameInput.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dish Already Exists", "The dish you are trying to add is already in database"));
        } else if (check == 3) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(dishNameInput.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "Dish was previously deleted from database", null));
        }
    }

    // Delete a new Dish
    public void deleteDish() {
        dishSessionBean.deleteDish(selectedDish.getDishId());
        dishes.remove(selectedDish);
        selectedDish = null;
        //dishes.clear();
        dishes = dishSessionBean.getAllDishes();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Dish Deleted");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // Redirect to kmsEditDish.xhtml
    public void redirectToEditDish() {
        System.out.println("redirectToEditDish:" + selectedDish);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("kmsEditDish.xhtml?i=4&dish=" + selectedDish.getDishId().toString());
        } catch (IOException ex) {
            Logger.getLogger(DishManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Edit a Dish
    public void editDish() {
        System.out.println("DishManagedBean: editDish");
        dishSessionBean.editDishToEdit(dishToEdit);
//    dishSessionBean.editDish(dishToEdit.getDishId(), dishToEdit.getDishDescription(), dishToEdit.getMethodOfPreparation(), dishToEdit.getPax(), dishToEdit.getDishPrice());
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dish Successfully Updated", "You have successfully updated dish " + dishToEdit.getDishName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("kmsViewAllDish.xhtml?i=4");
        } catch (IOException ex) {
            Logger.getLogger(DishManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Store file in the database
    public void upload() throws IOException {
        System.out.println("ImageManagedBean: upload()");

        // Create connection    
        try {
            Class.forName("com.mysql.jdbc.Driver");         // Load driver
            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/islandfurniture?user=root&password=12345678");
            // Set autocommit to false to manage it by hand
            connection.setAutoCommit(false);

            // Create the statement object
            PreparedStatement statement = connection.prepareStatement("UPDATE Dish d SET d.imageData=? WHERE d.dishId=?");
            // Set file data
            statement.setBinaryStream(1, file.getInputstream());
            statement.setLong(2, dishToEdit.getDishId());
            statement.executeUpdate();   // Insert data to the database

            // Commit & close
            connection.commit();    // when autocommit=false
            connection.close();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Upload success", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            e.printStackTrace();
            // Add error message
            FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Upload error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, errorMsg);
        }
    }

    // Cancel Edit Recipe
    public void noEdit(RowEditEvent event) {
    }

    // Update Recipe
    public void edit(RowEditEvent event) {
        RecipeItem ri = ((RecipeItem) event.getObject());
        System.out.println(ri);
        try {
            recipeSessionBean.update(ri, quantity, unit);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Recipe updated successfuly");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Invalid input");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    // Add Ingredient(s) to A Recipe
    public void addIngredientToDish() {
        System.out.println("DishManagedBean: addIngredientToDish");
        Set<Ingredient> selectedIngredientToAdd = new HashSet<Ingredient>(); // HashSet that stores selectedIngredientToAdd Ingredient objects

        Ingredient p = new Ingredient(); // For Usage in for loop
        for (int i = 0; i < selectedIngredient.length; i++) {
            p = ingredientSessionBean.getSelectedIngredientToAdd(selectedIngredient[i]); // Retrieve Ingredient object using Ingredient Name
            selectedIngredientToAdd.add(p); // Add retrieved object to HashSet
        }

        Boolean success = ingredientSessionBean.addIngredientToDish(selectedDish, selectedIngredientToAdd);
        if (success) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Ingredient added to Recipe");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Ingredient has already been added to Recipe");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

}
