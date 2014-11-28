/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.Company;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ruoxi
 */
@Stateless
public class CompanySession implements CompanySessionLocal {

    @PersistenceContext(unitName = "IslandFurniture-ejbPU")
    private EntityManager entityManager;
    
    @Override
    public void createCompany(String companyName, String country, String address, String postal, String phone, String email, String type) {
        Company company = new Company();
        company.setCompanyName(companyName);
        company.setCountry(country);
        company.setAddress(address);
        company.setPostal(postal);
        company.setPhone(phone);
        company.setEmail(email);
        company.setType(type);
        entityManager.persist(company);
        entityManager.flush();
    }
    
    @Override
    public List<Company> getCompany() {
        Query query = entityManager.createQuery("SELECT e FROM Company e");
        return query.getResultList();
    }
    
    @Override
    public Company getCompanySingleResult() {
        Query query = entityManager.createQuery("SELECT e FROM Company e");
        return (Company) query.getSingleResult();
    }
    
}
