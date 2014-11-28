/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.PartHeadquarter;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.Part;

/**
 *
 * @author Ruoxi
 */
@Stateless
public class PartSession implements PartSessionLocal {

    @PersistenceContext(unitName = "HeadQuarter-ejbPU")
    private EntityManager entityManager;

    @Override
    public PartHeadquarter getPart(String partName) {
        Query query = entityManager.createQuery("SELECT u FROM PartHeadquarter u WHERE u.partName = :inPartName AND u.isDeleted= :inIsDeleted");
        query.setParameter("inPartName", partName);
        query.setParameter("inIsDeleted", "no");
        PartHeadquarter part = null;
        try {
            part = (PartHeadquarter) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }
        return part;
    }

    @Override
    public void createPart(String parttName) {
        PartHeadquarter part = new PartHeadquarter();
        part.setPartName(parttName);
        part.setIsDeleted("no");
        entityManager.persist(part);
        entityManager.flush();
    }

    @Override
    public List<PartHeadquarter> getAllPart() {
        Query query = entityManager.createQuery("SELECT e FROM PartHeadquarter e WHERE e.isDeleted= :inIsDeleted");
        query.setParameter("inIsDeleted", "no");
        return query.getResultList();
    }

    @Override
    public void deletePart(String partName) {
        PartHeadquarter part = getPart(partName);
        part.setIsDeleted("yes");
        entityManager.merge(part);
    }

    @Override
    public void resetPartName(String partName, String newPartName) {

        PartHeadquarter part = getPart(partName);
        part.setPartName(newPartName);

        entityManager.merge(part);
        entityManager.flush();
    }

}
