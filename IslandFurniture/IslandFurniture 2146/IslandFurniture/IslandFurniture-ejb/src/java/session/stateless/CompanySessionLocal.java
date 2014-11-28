/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Company;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ruoxi
 */
@Local
public interface CompanySessionLocal {

   public void createCompany(String companyName, String country, String address, String postal, String phone, String email, String type);

    public List<Company> getCompany();

    public Company getCompanySingleResult();

}
