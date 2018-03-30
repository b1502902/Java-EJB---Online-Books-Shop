package entitypack;

import entitypack.Products;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-29T14:36:13")
@StaticMetamodel(Producers.class)
public class Producers_ { 

    public static volatile SingularAttribute<Producers, String> producerImg;
    public static volatile CollectionAttribute<Producers, Products> productsCollection;
    public static volatile SingularAttribute<Producers, String> producerName;
    public static volatile SingularAttribute<Producers, String> producerProfile;
    public static volatile SingularAttribute<Producers, Integer> producerID;

}