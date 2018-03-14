package entitypack;

import entitypack.ProductArtist;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2018-03-14T07:51:13")
@StaticMetamodel(Artists.class)
public class Artists_ { 

    public static volatile SingularAttribute<Artists, String> artistImg;
    public static volatile CollectionAttribute<Artists, ProductArtist> productArtistCollection;
    public static volatile SingularAttribute<Artists, Integer> artistID;
    public static volatile SingularAttribute<Artists, String> artistName;
    public static volatile SingularAttribute<Artists, String> artistProfile;

}