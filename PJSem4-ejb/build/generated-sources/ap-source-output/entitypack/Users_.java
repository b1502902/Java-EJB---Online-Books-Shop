package entitypack;

import entitypack.Orders;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-04T08:19:16")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> userAddress;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, String> userRealname;
    public static volatile SingularAttribute<Users, String> userPhone;
    public static volatile SingularAttribute<Users, String> userRule;
    public static volatile SingularAttribute<Users, String> userEmail;
    public static volatile CollectionAttribute<Users, Orders> ordersCollection;
    public static volatile SingularAttribute<Users, Integer> userID;
    public static volatile SingularAttribute<Users, String> username;

}