package entitypack;

import entitypack.Products;
import entitypack.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-31T15:12:56")
@StaticMetamodel(Comments.class)
public class Comments_ { 

    public static volatile SingularAttribute<Comments, Integer> cmtID;
    public static volatile SingularAttribute<Comments, Date> cmtDate;
    public static volatile SingularAttribute<Comments, Products> productID;
    public static volatile SingularAttribute<Comments, String> cmtContent;
    public static volatile SingularAttribute<Comments, Users> userID;

}