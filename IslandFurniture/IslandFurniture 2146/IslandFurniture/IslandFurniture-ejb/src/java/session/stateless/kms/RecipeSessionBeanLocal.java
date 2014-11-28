/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless.kms;

import entity.Furniture;
import entity.Part;
import entity.kms.RecipeItem;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ZiGui
 */
@Local
public interface RecipeSessionBeanLocal {

      public void update(RecipeItem ri, float qty, String unit);

}
