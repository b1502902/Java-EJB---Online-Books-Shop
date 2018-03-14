package entitypack;

import entitypack.Orders;
import entitypack.Products;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2018-03-14T07:54:35")
@StaticMetamodel(OrdersDetail.class)
public class OrdersDetail_ { 

    public static volatile SingularAttribute<OrdersDetail, Integer> quantity;
    public static volatile SingularAttribute<OrdersDetail, Products> productID;
    public static volatile SingularAttribute<OrdersDetail, Integer> ordersDetailID;
    public static volatile SingularAttribute<OrdersDetail, Orders> orderID;
    public static volatile SingularAttribute<OrdersDetail, BigDecimal> price;

}