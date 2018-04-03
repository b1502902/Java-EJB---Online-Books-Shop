package entitypack;

import entitypack.Albums;
import entitypack.Products;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-02T23:43:51")
@StaticMetamodel(AlbumProduct.class)
public class AlbumProduct_ { 

    public static volatile SingularAttribute<AlbumProduct, Products> productID;
    public static volatile SingularAttribute<AlbumProduct, Integer> albumProductID;
    public static volatile SingularAttribute<AlbumProduct, Albums> albumID;

}