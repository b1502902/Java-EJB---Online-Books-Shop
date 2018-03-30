/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sbeanpack;

import entitypack.AlbumProduct;
import entitypack.Albums;
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
public class AlbumsFacade extends AbstractFacade<Albums> implements AlbumsFacadeLocal {
    @PersistenceContext(unitName = "PJSem4-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlbumsFacade() {
        super(Albums.class);
    }
    
    
    @Override
    public List<Albums> showAllAlbumsOfUser(Users user){
        Query query = em.createQuery("SELECT a FROM Albums a WHERE a.userID = :userid",Albums.class);
        query.setParameter("userid", user);
        return query.getResultList();
    }
}
