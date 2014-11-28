package entity;

import entity.Supplier;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(SupplierAcct.class)
public class SupplierAcct_ { 

    public static volatile SingularAttribute<SupplierAcct, String> supplierPwd;
    public static volatile SingularAttribute<SupplierAcct, String> salt;
    public static volatile SingularAttribute<SupplierAcct, Boolean> isDeleted;
    public static volatile SingularAttribute<SupplierAcct, Supplier> supplier;
    public static volatile SingularAttribute<SupplierAcct, Long> supplierAcctId;
    public static volatile SingularAttribute<SupplierAcct, String> supplierUsername;
    public static volatile SingularAttribute<SupplierAcct, String> status;

}