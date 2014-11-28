/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.PartHeadquarter;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ruoxi
 */
@Local
public interface PartSessionLocal {

    public PartHeadquarter getPart(String partName);

    public void createPart(String parttName);

   public List<PartHeadquarter> getAllPart();

    public void deletePart(String partName);

    public void resetPartName(String partName, String newPartName);

}
