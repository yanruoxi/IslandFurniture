/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless.kms;

import entity.BOM;
import entity.Furniture;
import entity.Part;
import entity.kms.RecipeItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ZiGui
 */
@Stateless
public class RecipeSessionBean implements RecipeSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
    
    private RecipeItem recipe;
    
    // update recipe
   @Override
  public void update(RecipeItem ri, float qty, String unit) {
      System.out.println("RecipeSessionBean: update() ");
      System.out.println("Qty: "+ qty + " Unit: " + unit);
      ri.setQuantity(qty);
      ri.setUnit(unit);
      
      em.merge(ri);
      em.flush();
      
  }
    
}