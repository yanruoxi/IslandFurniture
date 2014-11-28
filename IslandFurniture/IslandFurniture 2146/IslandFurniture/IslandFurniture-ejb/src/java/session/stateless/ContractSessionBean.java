/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.Contract;
import entity.Part;
import entity.Supplier;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Meiling
 */
@Stateless
@LocalBean
public class ContractSessionBean implements ContractSessionBeanLocal {
    @EJB
    private PartSessionBeanLocal partSessionBean;
    @EJB
    private SupplierSessionBeanLocal supplierSessionBean;
    
    @PersistenceContext
    private EntityManager em;
    
    // Retrieve all Contracts
    @Override
    public List<Contract> getAllContracts() {
        Query query = em.createQuery("SELECT c FROM Contract c WHERE c.contractDeleteStatus != 'yes'");
        return query.getResultList();
    }

    // Create a Contract
    @Override
    public boolean createContract(Date startDate, Date endDate, String remark, Double unitPrice, Supplier selectedSupplierForContract, Part p) {
        System.out.println("createContract");
        System.out.println("selectedSupplierForContract:" + selectedSupplierForContract);
        System.out.println("p:" + p.getPartName());
        
        Part pDB = em.find(Part.class, p.getId()); // Retrieve Part object to link Contract to from DB
        boolean contractExistWithPart = contractExistWithPart(pDB); // Check whether Part already has a Contract
        // If Part does not have Contract
        if(!contractExistWithPart){
            // Check whether Part already has a Contract with same Supplier
            boolean contractExistWithSupplierAndPart = supplierSessionBean.contractExistWithSupplierAndPart(selectedSupplierForContract, pDB);
            // If Part does not have Contract with same Supplier
            if (!contractExistWithSupplierAndPart) {
                Contract c = new Contract();
                c.setStartDate(startDate);
                c.setEndDate(endDate);
                c.setContractDeleteStatus("no"); // default value for contract delete status
                c.setRemark(remark);
                c.setUnitPrice(unitPrice); // Added
                c.setSupplier(selectedSupplierForContract);
                c.setPart(p);
                
                em.persist(c);
                em.flush();
                pDB.setContract(c);
                em.flush();
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
    
    // Check whether a Contract exist for the same Part, with another Supplier
    @Override
    public boolean contractExistWithPart(Part pDB) {
        try {
            System.out.println("pDB:" + pDB.getPartName());
            // If p.contract is not null, means there is contract attached to the part
            Query q = em.createQuery("SELECT p FROM Part p WHERE p.contract IS NOT NULL AND p.id = :pDBId");
            q.setParameter("pDBId", pDB.getId());
            q.getSingleResult();
            return true;     
        } 
        catch (NoResultException ex) {
            System.out.println("No contract for Part");
            return false;
        }
    }
    
    
    
    @Override
    public void deleteContract(Contract selectedContract){
        Contract c = em.find(Contract.class, selectedContract.getContractId());
        c.setContractDeleteStatus("yes");
        c.setSupplier(null);
        
        Part p = (Part)c.getPart();
        p.setContract(null);
        c.setPart(null);
    }
    
    @Override
    public Contract searchByPartName(String partName){
        System.out.println("partName:" + partName);
        Query query = em.createQuery("SELECT c FROM Contract c WHERE c.part.partName = :partName");
        query.setParameter("partName", partName);
        Contract result = null;
        try {
            result = (Contract) query.getSingleResult();
        } 
        catch (NoResultException ex) {
            System.out.println("canght no result exception");
        }
        return result;
    }

    @Override
    public Contract getContractWithContractId(Long contractId) {
        Contract c = em.find(Contract.class, contractId);
        return c;
    }

    @Override
    public boolean editContract(Contract contractToEdit, Part p) {
        contractToEdit.setPart(p);
        Contract cDB = em.find(Contract.class, contractToEdit.getContractId());
        boolean hasSameEndDateSameRemark = checkContractSameEndDateSameRemark(cDB, contractToEdit);
        if(!hasSameEndDateSameRemark){
            cDB.setEndDate(contractToEdit.getEndDate());
            cDB.setRemark(contractToEdit.getRemark());
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public boolean checkContractExpiry(Date contractEndDate){
        if(!contractEndDate.after(new Date())){ // meaning (contractEndDate =< currentDate)
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean checkContractSameEndDateSameRemark(Contract cDB, Contract contractToEdit){
        Date contractToEditEndDate = contractToEdit.getEndDate();
        Date cDBEndDate = cDB.getEndDate();
        
        String contractToEditRemark = contractToEdit.getRemark();
        String cDBRemark = cDB.getRemark();
        
        if((contractToEditEndDate.compareTo(cDBEndDate) == 0) && (contractToEditRemark.equals(cDBRemark))){
            System.out.println("same end date and same remark");
            return true;
        }
        
        else{
            return false;
        }
    }
}
