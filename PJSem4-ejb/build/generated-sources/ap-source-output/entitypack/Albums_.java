package entitypack;

import entitypack.AlbumProduct;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2018-03-26T00:15:47")
@StaticMetamodel(Albums.class)
public class Albums_ { 

    public static volatile SingularAttribute<Albums, String> albumName;
    public static volatile SingularAttribute<Albums, Integer> albumID;
    public static volatile SingularAttribute<Albums, Integer> userID;
    public static volatile CollectionAttribute<Albums, AlbumProduct> albumProductCollection;

}