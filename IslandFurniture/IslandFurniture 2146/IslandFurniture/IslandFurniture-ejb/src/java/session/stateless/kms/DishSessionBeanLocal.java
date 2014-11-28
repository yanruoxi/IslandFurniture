/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.kms;

import entity.kms.Dish;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ZiGui
 */
@Local
public interface DishSessionBeanLocal {

    public byte[] loadImage(Long dishId);

    public List<Dish> getAllDishes();

    public List<String> getAllDishType();

    public List<Dish> getDish(String dishName);

    public List<Dish> getMainDish();

    public List<Dish> getDessertDish();

    public List<Dish> getBeverageDish();

    public void deleteDish(Long dishId);

    public int checkDish(String dishName);

    public void addDish(String dishName, String methodOfPreparation, int pax,
            String dishDescription, String dishType, double dishPrice);

    public Dish getDishWithId(Long dishId);

    public void editDish(Long dishToEditId, String description, String method, Integer pax, Double price);

//    public void upload(Long dishToEditId, Decoder.BinaryStream file);
    public void editDishToEdit(Dish dishToEdit);

}
