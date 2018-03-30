package entitypack;

import entitypack.AlbumProduct;
import entitypack.Categories;
import entitypack.Comments;
import entitypack.OrdersDetail;
import entitypack.Producers;
import entitypack.ProductArtist;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-29T14:36:13")
@StaticMetamodel(Products.class)
public class Products_ { 

    public static volatile SingularAttribute<Products, Integer> productID;
    public static volatile SingularAttribute<Products, String> productImg;
    public static volatile CollectionAttribute<Products, ProductArtist> productArtistCollection;
    public static volatile SingularAttribute<Products, String> productTrailer;
    public static volatile SingularAttribute<Products, String> productName;
    public static volatile CollectionAttribute<Products, AlbumProduct> albumProductCollection;
    public static volatile SingularAttribute<Products, Integer> productQuantity;
    public static volatile SingularAttribute<Products, Categories> cateID;
    public static volatile SingularAttribute<Products, String> productContent;
    public static volatile CollectionAttribute<Products, OrdersDetail> ordersDetailCollection;
    public static volatile SingularAttribute<Products, Integer> artistsID;
    public static volatile SingularAttribute<Products, Producers> producerID;
    public static volatile CollectionAttribute<Products, Comments> commentsCollection;
    public static volatile SingularAttribute<Products, BigDecimal> productPrice;

}