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
import javax.ejb.Local;

/**
 *
 * @author Meiling
 */
@Local
public interface ContractSessionBeanLocal {
    
    public List<Contract> getAllContracts();
    
    public boolean createContract(Date startDate, Date endDate, String remark, Double unitPrice, Supplier selectedSupplierForContract, Part p);
    
    public boolean contractExistWithPart(Part pDB);
    
    public void deleteContract(Contract selectedContract);
    
    public Contract searchByPartName(String partName);

    public Contract getContractWithContractId(Long contractId);

    public boolean editContract(Contract contractToEdit, Part p);
    
    public boolean checkContractExpiry(Date contractEndDate);
    
    
}
