package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T14:35:33")
@StaticMetamodel(SystemUser.class)
public class SystemUser_ { 

    public static volatile SingularAttribute<SystemUser, String> employeeName;
    public static volatile SingularAttribute<SystemUser, String> password;
    public static volatile SingularAttribute<SystemUser, String> salt;
    public static volatile SingularAttribute<SystemUser, String> isDeleted;
    public static volatile SingularAttribute<SystemUser, String> accountType;
    public static volatile SingularAttribute<SystemUser, String> dateOfBirth;
    public static volatile SingularAttribute<SystemUser, Long> id;
    public static volatile SingularAttribute<SystemUser, String> userName;
    public static volatile SingularAttribute<SystemUser, String> email;
    public static volatile SingularAttribute<SystemUser, String> status;

}