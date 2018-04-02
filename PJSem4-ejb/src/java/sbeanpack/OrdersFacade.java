/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sbeanpack;

import entitypack.Orders;
import entitypack.Users;
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
public class OrdersFacade extends AbstractFacade<Orders> implements OrdersFacadeLocal {
    @PersistenceContext(unitName = "PJSem4-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }
    
    @Override
    public List<Orders> listOrdersByUserID(Users userid){
        Query query = em.createQuery("SELECT o FROM Orders o WHERE o.userID = :userid",Orders.class);
        query.setParameter("userid", userid);
        return query.getResultList();
    }
}
