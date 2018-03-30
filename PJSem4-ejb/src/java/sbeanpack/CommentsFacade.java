/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sbeanpack;

import entitypack.Comments;
import entitypack.Products;
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
public class CommentsFacade extends AbstractFacade<Comments> implements CommentsFacadeLocal {
    @PersistenceContext(unitName = "PJSem4-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentsFacade() {
        super(Comments.class);
    }
    
    @Override
    public List<Comments> listCmtByProductID(Products productID){
        Query query = em.createQuery("SELECT c FROM Comments c WHERE c.productID = :productID ORDER BY c.cmtID DESC",Comments.class);
        query.setParameter("productID", productID);
        return query.getResultList();
    } 
}
