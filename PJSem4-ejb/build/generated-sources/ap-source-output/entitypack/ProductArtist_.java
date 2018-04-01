package entitypack;

import entitypack.Artists;
import entitypack.Products;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-01T12:04:36")
@StaticMetamodel(ProductArtist.class)
public class ProductArtist_ { 

    public static volatile SingularAttribute<ProductArtist, Products> productID;
    public static volatile SingularAttribute<ProductArtist, Integer> productArtistID;
    public static volatile SingularAttribute<ProductArtist, Artists> artistID;

}