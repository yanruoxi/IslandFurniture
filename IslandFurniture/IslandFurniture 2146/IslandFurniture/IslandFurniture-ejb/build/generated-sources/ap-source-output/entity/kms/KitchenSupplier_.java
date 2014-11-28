package entity.kms;

import entity.kms.Ingredient;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(KitchenSupplier.class)
public class KitchenSupplier_ { 

    public static volatile SingularAttribute<KitchenSupplier, String> kmobileNum;
    public static volatile SingularAttribute<KitchenSupplier, String> kfaxNum;
    public static volatile SingularAttribute<KitchenSupplier, String> ksupplierEmailAddress;
    public static volatile SetAttribute<KitchenSupplier, Ingredient> ingredient;
    public static volatile SingularAttribute<KitchenSupplier, String> ksupplierName;
    public static volatile SingularAttribute<KitchenSupplier, Boolean> ksupplierDeleteStatus;
    public static volatile SingularAttribute<KitchenSupplier, Long> ksupplierId;
    public static volatile SingularAttribute<KitchenSupplier, String> ktelephone;
    public static volatile SingularAttribute<KitchenSupplier, String> kcontactPersonName;
    public static volatile SingularAttribute<KitchenSupplier, String> ksupplierAddress;

}