package entitypack;

import entitypack.Albums;
import entitypack.Products;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2018-03-26T00:15:47")
@StaticMetamodel(AlbumProduct.class)
public class AlbumProduct_ { 

    public static volatile SingularAttribute<AlbumProduct, Products> productID;
    public static volatile SingularAttribute<AlbumProduct, Integer> albumProductID;
    public static volatile SingularAttribute<AlbumProduct, Albums> albumID;

}