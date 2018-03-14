package entitypack;

import entitypack.Artists;
import entitypack.Products;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2018-03-14T07:54:35")
@StaticMetamodel(ProductArtist.class)
public class ProductArtist_ { 

    public static volatile SingularAttribute<ProductArtist, Products> productID;
    public static volatile SingularAttribute<ProductArtist, Integer> productArtistID;
    public static volatile SingularAttribute<ProductArtist, Artists> artistID;

}