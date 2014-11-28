package entity;

import entity.Contract;
import entity.Part;
import entity.SupplierAcct;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(Supplier.class)
public class Supplier_ { 

    public static volatile SingularAttribute<Supplier, String> supplierName;
    public static volatile SingularAttribute<Supplier, String> supplierAddress;
    public static volatile SingularAttribute<Supplier, String> mobileNum;
    public static volatile SingularAttribute<Supplier, Long> supplierId;
    public static volatile SingularAttribute<Supplier, String> contactPersonName;
    public static volatile SingularAttribute<Supplier, String> telephoneNum;
    public static volatile SingularAttribute<Supplier, String> faxNum;
    public static volatile SingularAttribute<Supplier, String> supplierDeleteStatus;
    public static volatile SetAttribute<Supplier, Part> parts;
    public static volatile CollectionAttribute<Supplier, Contract> contracts;
    public static volatile SingularAttribute<Supplier, SupplierAcct> supplierAcct;
    public static volatile SingularAttribute<Supplier, String> supplierEmailAddr;

}