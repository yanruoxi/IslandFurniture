package entity;

import entity.Part;
import entity.Supplier;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T16:52:14")
@StaticMetamodel(Contract.class)
public class Contract_ { 

    public static volatile SingularAttribute<Contract, Double> unitPrice;
    public static volatile SingularAttribute<Contract, Date> endDate;
    public static volatile SingularAttribute<Contract, Supplier> supplier;
    public static volatile SingularAttribute<Contract, Part> part;
    public static volatile SingularAttribute<Contract, Long> contractId;
    public static volatile SingularAttribute<Contract, String> remark;
    public static volatile SingularAttribute<Contract, Date> startDate;
    public static volatile SingularAttribute<Contract, String> contractDeleteStatus;

}