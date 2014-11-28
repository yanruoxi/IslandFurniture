/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Inventory;
import entity.Part;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ruoxi
 */
@Stateless
public class PartWebSession implements PartWebSessionLocal {

    @PersistenceContext(unitName = "IslandFurniture-ejbPU")
    private EntityManager entityManager;

    @Override
    public void PullPartHeadquarter(String partName) {

    }

    @Override
    public boolean checkPartExist(String partName) {
        return true;

    }

}
