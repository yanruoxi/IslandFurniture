/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import javax.ejb.Local;
import entity.Furniture;
import entity.Part;
import entity.BOM;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author li
 */
@Local
public interface BOMSessionBeanLocal {
    public void GenerateBOM(Integer quantity,Furniture furniture,Part part);
    
    public List<ArrayList> ViewBOMByFurniture(String fName);
    public List<ArrayList> viewBOMByPart(String partName);
    
    public long DeleteBOM(Long B_ID);
    public long editBOM(Long B_ID, String furnitureName, String partName, Integer quan);
    
}
