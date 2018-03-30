/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbeanpack;

import entitypack.AlbumProduct;
import entitypack.Albums;
import entitypack.Products;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author salin_000
 */
@Stateless
public class AlbumProductFacade extends AbstractFacade<AlbumProduct> implements AlbumProductFacadeLocal {

    @EJB
    private ProductsFacadeLocal productsFacade;

    
    @PersistenceContext(unitName = "PJSem4-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlbumProductFacade() {
        super(AlbumProduct.class);
    }
    
    @Override
    public List<Products> listProductsByAlbum(Albums alb){
        Query query = em.createQuery("SELECT a.productID FROM AlbumProduct a WHERE a.albumID = :alb",AlbumProduct.class);
        query.setParameter("alb", alb);
        return query.getResultList();
    }
    
}
