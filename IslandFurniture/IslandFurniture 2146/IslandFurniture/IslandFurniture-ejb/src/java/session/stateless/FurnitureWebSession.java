/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Furniture;
import entity.Inventory;
import entity.Part;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Ruoxi
 */
@Stateless
public class FurnitureWebSession implements FurnitureWebSessionLocal {

    @PersistenceContext(unitName = "IslandFurniture-ejbPU")
    private EntityManager entityManager;

    @Override
    public void PullFurnitureHeadquarter(String furnitureName) {

    }

    @Override
    public boolean checkFurnitureExist(String furnitureName) {

        return false;
    }

}
