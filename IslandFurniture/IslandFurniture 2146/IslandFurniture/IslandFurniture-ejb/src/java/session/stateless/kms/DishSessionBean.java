/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.kms;

import entity.kms.Dish;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ZiGui
 */
@Stateless
public class DishSessionBean implements DishSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;

    // Retrieve Image 
    @Override
    public byte[] loadImage(Long dishId) {
        return em.find(Dish.class, dishId).getImageData();
    }

    // Retrieve all dishes
    @Override
    public List<Dish> getAllDishes() {
        Query query = em.createQuery("SELECT d FROM Dish d WHERE d.dishDeleteStatus = false");
        return query.getResultList();
    }

    // Retrieve all dish types
    @Override
    public List<String> getAllDishType() {
        Query query = em.createQuery("SELECT DISTINCT d.dishType FROM Dish d WHERE d.dishDeleteStatus = false");
        return query.getResultList();
    }

    // Retrieve all Main dishes
    @Override
    public List<Dish> getMainDish() {
        Query query = em.createQuery("SELECT d FROM Dish d WHERE d.dishDeleteStatus = false AND d.dishType = 'Mains'");
        return query.getResultList();
    }

    // Retrieve all Dessert Dish
    @Override
    public List<Dish> getDessertDish() {
        Query query = em.createQuery("SELECT d FROM Dish d WHERE d.dishDeleteStatus = false AND d.dishType = 'Dessert'");
        return query.getResultList();
    }

    // Retrieve all Beverage Dish
    @Override
    public List<Dish> getBeverageDish() {
        Query query = em.createQuery("SELECT d FROM Dish d WHERE d.dishDeleteStatus = false AND d.dishType = 'Beverage'");
        return query.getResultList();
    }

// Retrieve a Dish based on dishName
    @Override
    public List<Dish> getDish(String dishName) {
        Query query = em.createQuery("SELECT d FROM Dish d WHERE d.dishName = :dishName");
        query.setParameter("dishName", dishName);
        List<Dish> s = null;
        try {
            s = query.getResultList();
        } catch (NoResultException ex) {
            System.out.println("caught no result exception");
        }
        return s;
    }

    // Retrieve a Dish based on dishId
    @Override
    public Dish getDishWithId(Long selectedDishId) {
        Dish gs = em.find(Dish.class, selectedDishId);
        return gs;
    }

    // Check if dish exists before adding the dish
    @Override
    public int checkDish(String dishName) {
        List<Dish> d = getDish(dishName);
        int size = d.size();

        if (size == 0) {
            return 1;
        } else {
            for (int i = 0; i < size; i++) {
                if (!d.get(i).isDishDeleteStatus()) // Any existing supplier's delete status = no
                {
                    return 2;
                }
            }
            return 3;
        }
    }

    // Add a new Dish
    @Override
    public void addDish(String dishName, String methodOfPreparation, int pax,
            String dishDescription, String dishType, double dishPrice) {
        Dish d = new Dish();
        d.setDishDeleteStatus(false);
        d.setDishDescription(dishDescription);
        d.setDishName(dishName);
        d.setDishPrice(dishPrice);
        d.setDishType(dishType);
        d.setMethodOfPreparation(methodOfPreparation);
        d.setPax(pax);

        em.persist(d);
        em.flush();
    }

    // Delete a dish
    @Override
    public void deleteDish(Long dishId) {
        System.out.println("deleteDish:" + dishId);
        Dish d = em.find(Dish.class, dishId);
        d.setDishDeleteStatus(true);
    }

    // Edit a dish
    @Override
    public void editDish(Long dishToEditId, String description, String method, Integer pax, Double price) {
        System.out.println("DishSessionBean: editDish");
        Dish dishToEdit = getDishWithId(dishToEditId);
        dishToEdit.setDishDescription(description);
        dishToEdit.setMethodOfPreparation(method);
        dishToEdit.setPax(pax);
        dishToEdit.setDishPrice(price);

        em.merge(dishToEdit);
        em.flush();
    }

    // Edit a dish
    @Override
    public void editDishToEdit(Dish dishToEdit) {
        System.out.println("DishSessionBean: editDishToEdit()");
        Dish dd = getDishWithId(dishToEdit.getDishId());
        dd.setDishDescription(dishToEdit.getDishDescription());
        dd.setDishPrice(dishToEdit.getDishPrice());
        dd.setMethodOfPreparation(dishToEdit.getMethodOfPreparation());
        dd.setPax(dishToEdit.getPax());
        
        em.merge(dd);
        em.flush();
    }

}
