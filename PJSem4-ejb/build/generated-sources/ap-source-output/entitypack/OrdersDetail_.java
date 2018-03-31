package entitypack;

import entitypack.Orders;
import entitypack.Products;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-31T15:12:56")
@StaticMetamodel(OrdersDetail.class)
public class OrdersDetail_ { 

    public static volatile SingularAttribute<OrdersDetail, Integer> quantity;
    public static volatile SingularAttribute<OrdersDetail, Products> productID;
    public static volatile SingularAttribute<OrdersDetail, Integer> ordersDetailID;
    public static volatile SingularAttribute<OrdersDetail, Orders> orderID;
    public static volatile SingularAttribute<OrdersDetail, BigDecimal> price;

}