package entitypack;

import entitypack.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-01T12:04:36")
@StaticMetamodel(Albums.class)
public class Albums_ { 

    public static volatile SingularAttribute<Albums, String> albumName;
    public static volatile SingularAttribute<Albums, Integer> albumID;
    public static volatile SingularAttribute<Albums, Users> userID;

}