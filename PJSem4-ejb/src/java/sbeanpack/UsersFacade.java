/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sbeanpack;

import entitypack.Products;
import entitypack.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author salin_000
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeLocal {
    @PersistenceContext(unitName = "PJSem4-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    @Override
    public boolean checkLogin(String username, String password) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root c = cq.from(Users.class);
        cq.select(c);
        cq.where(cb.equal(c.get("username"), username),
                cb.equal(c.get("password"), password)
              );
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList().size() > 0;
    }
    
    @Override
    public int findIdByUsername(String username){
        Query query = em.createQuery("SELECT u.userID FROM Users u WHERE u.username = :username",Users.class);
        query.setParameter("username", username);
        System.out.println(query.getResultList());
        return (int) query.getSingleResult();
    }

    
}
