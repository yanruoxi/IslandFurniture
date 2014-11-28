package session.stateless;

import datamodel.SupplierWithPart;
import entity.Part;
import entity.Supplier;
import java.util.ArrayList;
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
public class SupplierSessionBean implements SupplierSessionBeanLocal {

    @EJB
    private PartSessionBeanLocal partSessionBean;

    @PersistenceContext
    private EntityManager em;

    // Retrieve all Suppliers
    @Override
    public List<Supplier> getAllSuppliers() {
        Query query = em.createQuery("SELECT s FROM Supplier s WHERE s.supplierDeleteStatus != 'yes'");
        return query.getResultList();
    }

    // Delete a Supplier
    @Override
    public boolean deleteSupplier(Long supplierId) {
        System.out.println("deleteSupplierSessionBean:" + supplierId);
        Supplier s = em.find(Supplier.class, supplierId);
        if (!contractExist(s)) {
            s.setSupplierDeleteStatus("yes");
            s.setParts(null);
            // Added
            s.setSupplierAcct(null);
            return true;
        } else {
            return false;
        }
    }

    // Retrieve a Supplier based on SupplierId
    @Override
    public Supplier getSupplier(Long selectedSupplierId) {
        Supplier gs = em.find(Supplier.class, selectedSupplierId);
        return gs;
    }

    // Check whether a Contract exist
    @Override
    public boolean contractExist(Supplier s) {
        Query q = em.createQuery("SELECT COUNT(c) FROM Contract c WHERE c.supplier = :s");
        q.setParameter("s", s);
        Long numOfContract = (Long) q.getSingleResult();
        if (numOfContract == 0) {
            return false;
        } else {
            return true;
        }
    }

    // Check whether a Contract exist for the same Supplier and Part
    @Override
    public boolean contractExistWithSupplierAndPart(Supplier s, Part pDB) {
        System.out.println("s:" + s);
        System.out.println("pDB:" + pDB.getPartName());
        Query q = em.createQuery("SELECT COUNT(c) FROM Contract c WHERE c.supplier = :s AND c.part = :pDB");
        q.setParameter("s", s);
        q.setParameter("pDB", pDB);
        Long numOfContract = (Long) q.getSingleResult();
        System.out.println("numOfContract:" + numOfContract);
        if (numOfContract == 0) {
            return false;
        } else {
            return true;
        }
    }

    // Retrieve a Supplier based on SupplierName
    @Override
    public List<Supplier> getSupplier(String supplierName) {
        Query query = em.createQuery("SELECT s FROM Supplier s WHERE s.supplierName = :inSupplierName");
        query.setParameter("inSupplierName", supplierName);
        List<Supplier> s = null;
        try {
            s = query.getResultList();
        } catch (NoResultException ex) {
            System.out.println("caught no result exception");
        }
        return s;
    }

    // Edit Supplier
    @Override
    public void editSupplier(Long supplierId, String supplierName, String contactPersonName, String mobileNum, String faxNum,
            String supplierEmailAddr, String telephoneNum) {
        Supplier s = getSupplier(supplierId);
        s.setContactPersonName(contactPersonName);
        s.setMobileNum(mobileNum);
        s.setFaxNum(faxNum);
        s.setSupplierEmailAddr(supplierEmailAddr);
        s.setTelephoneNum(telephoneNum);
        em.merge(s);
        em.flush();
    }

    // Add a new Supplier
    @Override
    public void addSupplier(String supplierName, String supplierAddress, String telephoneNum,
            String contactPersonName, String mobileNum, String faxNum, String supplierEmailAddr, String status) {
        Supplier s = new Supplier();

        s.setSupplierName(supplierName);
        s.setSupplierAddress(supplierAddress);
        s.setTelephoneNum(telephoneNum);
        s.setContactPersonName(contactPersonName);
        s.setMobileNum(mobileNum);
        s.setFaxNum(faxNum);
        s.setSupplierEmailAddr(supplierEmailAddr);
        s.setSupplierDeleteStatus(status);

        em.persist(s);
        em.flush();
    }

    @Override
    public int checkSupplier(String supplierName) {
        List<Supplier> s = getSupplier(supplierName);
        System.out.println(supplierName + s + "---------" + s.size());
        int size = s.size();

        if (size == 0) {
            return 1;
        } else {
            for (int i = 0; i < size; i++) {
                if (s.get(i).getSupplierDeleteStatus().equals("no")) // Any existing supplier's delete status = no
                {
                    return 2;
                }
            }
            return 3;
        }
    }

    @Override
    public Supplier getOneSupplierByName(String supplierName) {
        Query query = em.createQuery("SELECT s FROM Supplier s WHERE s.supplierName = :inSupplierName");
        query.setParameter("inSupplierName", supplierName);
        Supplier s = null;
        try {
            s = (Supplier) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("caught no result exception");
        }
        return s;
    }

    @Override
    public List<SupplierWithPart> getSuppliersForPart(Part p) {
        System.out.println("p partName:" + p.getPartName());
        Query query = em.createQuery("SELECT s, p FROM Part p, Supplier s WHERE p.partName = :p AND p.suppliers = s");
        query.setParameter("p", p.getPartName());
        List<Object[]> objs = query.getResultList();
        ArrayList<SupplierWithPart> arr = new ArrayList<SupplierWithPart>();
        for (Object[] obj : objs) {
            System.err.println("obj[0]: " + ((Supplier) obj[0]).getSupplierId());
            arr.add(new SupplierWithPart((Supplier) obj[0], (Part) obj[1]));
            System.out.println("(Supplier)obj[0], (Part)obj[1]" + (Supplier) obj[0] + "," + (Part) obj[1]);
        }
        return arr;
    }

    @Override
    public void editSupplier(Supplier supplierToEdit) {
        System.out.println("SupplierSessionBean: editSupplier()");
        Supplier dd = getSupplier(supplierToEdit.getSupplierId());
        dd.setContactPersonName(supplierToEdit.getContactPersonName());
        dd.setTelephoneNum(supplierToEdit.getTelephoneNum());
        dd.setSupplierEmailAddr(supplierToEdit.getSupplierAddress());
        dd.setFaxNum(supplierToEdit.getFaxNum());

        em.merge(dd);
        em.flush();
    }

}
