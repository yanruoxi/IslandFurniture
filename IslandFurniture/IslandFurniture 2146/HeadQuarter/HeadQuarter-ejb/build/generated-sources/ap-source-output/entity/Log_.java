package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-17T14:35:33")
@StaticMetamodel(Log.class)
public class Log_ { 

    public static volatile SingularAttribute<Log, String> activity;
    public static volatile SingularAttribute<Log, String> systemUser;
    public static volatile SingularAttribute<Log, Date> activityTime;
    public static volatile SingularAttribute<Log, Long> id;

}