package entitypack;

import entitypack.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-04T08:19:16")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, String> orderAddress;
    public static volatile SingularAttribute<Orders, Integer> orderID;
    public static volatile SingularAttribute<Orders, String> orrderName;
    public static volatile SingularAttribute<Orders, String> orderNote;
    public static volatile SingularAttribute<Orders, String> orderStatus;
    public static volatile SingularAttribute<Orders, Date> orderDate;
    public static volatile SingularAttribute<Orders, String> orderPhone;
    public static volatile SingularAttribute<Orders, Users> userID;

}