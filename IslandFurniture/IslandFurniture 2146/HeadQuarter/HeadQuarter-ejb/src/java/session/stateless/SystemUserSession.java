/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.SystemUser;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ruoxi
 */
@Stateless
public class SystemUserSession implements SystemUserSessionLocal {
    @PersistenceContext(unitName = "HeadQuarter-ejbPU")
    private EntityManager entityManager;

   

    @Override
    public SystemUser getSystemUser(String userName) {
        Query query = entityManager.createQuery("SELECT u FROM SystemUser u WHERE u.userName = :inUserName and u.isDeleted= :inIsDeleted");
        query.setParameter("inUserName", userName);
        query.setParameter("inIsDeleted", "no");
        SystemUser systemUser = null;
        try {
            systemUser = (SystemUser) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }
        return systemUser;
    }

    @Override
    public void addNewAccount(String userName, String password, String salt, String accountType) {

        SystemUser systemUser = new SystemUser();
        systemUser.setUserName(userName);
        systemUser.setAccountType(accountType);
        systemUser.setSalt(salt);
        systemUser.setStatus("active");
        systemUser.setIsDeleted("no");
        systemUser.setPassword(systemUser.hashPassword(password + salt));

        entityManager.persist(systemUser);
        entityManager.flush();

    }

    @Override
    public void resetPassword(String userName, String password) {

        SystemUser systemUser = getSystemUser(userName);
        systemUser.setPassword(systemUser.hashPassword(password));

        entityManager.merge(systemUser);
        entityManager.flush();
    }

    @Override
    public void resetStatus(String userName, String status) {
        SystemUser systemUser = getSystemUser(userName);
        systemUser.setStatus(status);

        entityManager.merge(systemUser);
        entityManager.flush();
    }

    @Override
    public void resetAccountType(String userName, String accountType) {
        SystemUser systemUser = getSystemUser(userName);
        systemUser.setAccountType(accountType);
        entityManager.merge(systemUser);
        entityManager.flush();
    }
    
   
    @Override
    public List<SystemUser> getAllSystemUser() {
        Query query = entityManager.createQuery("SELECT e FROM SystemUser e WHERE e.isDeleted= :inIsDeleted");
        query.setParameter("inIsDeleted", "no");
        return query.getResultList();
    }

    @Override
    public void deleteSystemUser(String userName) {
        SystemUser systemUser = getSystemUser(userName);
        systemUser.setIsDeleted("yes");
        
        entityManager.merge(systemUser);

    }
    
    @Override
    public void resetEmployeeName(String userName, String employeeName) {
        SystemUser systemUser = getSystemUser(userName);
        systemUser.setEmployeeName(employeeName);
        entityManager.merge(systemUser);
        entityManager.flush();
    }
    
    @Override
    public void resetEmail(String userName, String email) {
 
        SystemUser systemUser = getSystemUser(userName);
        systemUser.setEmail(email);
        entityManager.merge(systemUser);
        entityManager.flush();
    }

    @Override
    public void resetDateOfBirth(String userName, String dateOfBirth) {
 
        SystemUser systemUser = getSystemUser(userName);
        systemUser.setDateOfBirth(dateOfBirth);
        entityManager.merge(systemUser);
        entityManager.flush();
    }

    @Override
    public boolean LoginForPOS(String userName, String password) {
        boolean check = false;
        Query query = entityManager.createQuery("SELECT u FROM SystemUser u WHERE u.userName = :inUserName and u.isDeleted= :inIsDeleted");
        query.setParameter("inUserName", userName);
        query.setParameter("inIsDeleted", "no");
        SystemUser systemUser = null;
        try {
            systemUser = (SystemUser) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }

        if (systemUser != null) {

            String combinedPassword = systemUser.getPassword();
            String salt = systemUser.getSalt();
            char[] databasePassword = combinedPassword.toCharArray();

            char[] enteredPassword = null;
            try {

                MessageDigest md5 = MessageDigest.getInstance("MD5");
                password = password + salt;
                byte[] tmp = password.getBytes();

                md5.update(tmp);
                enteredPassword = systemUser.byteArrToString(md5.digest()).toCharArray();
                int correctCount = 0;
                if (databasePassword.length != enteredPassword.length) {
                    return check;
                }
                for (int i = 0; i < databasePassword.length; i++) {
                    if (databasePassword[i] == enteredPassword[i]) {
                        correctCount++;
                    }
                }
                if (databasePassword.length == correctCount && systemUser.getAccountType().equalsIgnoreCase("POS Staff")) {
                    check = true;
                    return check;
                } else {
                    return check;
                }
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("cannot check password in LoginManagedBean");
            }
        }
        return check;
    }
    
}
