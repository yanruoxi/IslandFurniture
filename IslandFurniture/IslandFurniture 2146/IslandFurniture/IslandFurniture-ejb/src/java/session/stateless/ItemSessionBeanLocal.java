/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.BOM;
import javax.ejb.Local;

import entity.Furniture;
import entity.Part;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author li
 */
@Local
public interface ItemSessionBeanLocal {
    
    public Long AddFurniture(String furnitureName);
    public Long AddPart(String partName, Integer lotSize);
    
    public Furniture searchFurniture(long id);
    public ArrayList searchFurniture(String furnitureName);
    public ArrayList searchPart(String partName);
     
    public long DelectFurniture(String fName);
    public long DelectPart(String pName);
    public long DeleteBOM2(Long B_ID);
     
    public void editFurniture(Long furnitureID,String furnitureName);
    public long editFurniture2(String furnitureName, String newFurnitureName);
 //   public void editFurniture3(String oldValue, String newValue);
    public void editPart(Long partID,String partName,Integer lotSize );
    public long editPart2(String partName,String newPartName ,Integer lotSize);
 //   public void editPart3(String oldValue,String newValue);
    
    public Furniture getFurniture(String furnitureName);
    public List<Furniture> getAllFurniture();
    
    public Part getPart(String partName);
    public List<Part> getAllPart();
    
    public Long createBom(String furnitureName,String partName,Integer quantity);
    
    public List<BOM> getFurnitureBom(String furnitureName);
    public List<BOM> getAllBom() ;
    
    public void changePartBom(String newValue,Long bomId);
    public void changeQuantityBom(Integer newValue, Long bomId);
}
