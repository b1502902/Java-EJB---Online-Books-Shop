package entitypack;

import entitypack.AlbumProduct;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-29T14:36:13")
@StaticMetamodel(Albums.class)
public class Albums_ { 

    public static volatile SingularAttribute<Albums, String> albumName;
    public static volatile SingularAttribute<Albums, Integer> albumID;
    public static volatile SingularAttribute<Albums, Integer> userID;
    public static volatile CollectionAttribute<Albums, AlbumProduct> albumProductCollection;

}