/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sbeanpack;

import entitypack.Orders;
import entitypack.OrdersDetail;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author salin_000
 */
@Stateless
public class OrdersDetailFacade extends AbstractFacade<OrdersDetail> implements OrdersDetailFacadeLocal {
    @PersistenceContext(unitName = "PJSem4-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersDetailFacade() {
        super(OrdersDetail.class);
    }
    
    @Override
    public List<OrdersDetail> listOrdersDetailByOrderID(Orders billid){
        Query query = em.createQuery("SELECT o FROM OrdersDetail o WHERE o.orderID = :billid",OrdersDetail.class);
        query.setParameter("billid", billid);
        return query.getResultList();
    }
}
