/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sbeanpack;

import entitypack.Categories;
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
public class ProductsFacade extends AbstractFacade<Products> implements ProductsFacadeLocal {
    @PersistenceContext(unitName = "PJSem4-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsFacade() {
        super(Products.class);
    }
    
    @Override
    public List<Products> showByCate(Categories pcateid){
        System.out.println("aa"+pcateid);
        Query query = em.createQuery("SELECT p FROM Products p WHERE p.cateID = :pcateId",Products.class);
        query.setParameter("pcateId", pcateid);
        List<Products> listProducts = query.getResultList();
        return listProducts;
    }

    @Override
    public List<Products> showByCate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Products> searchProducts(String keyword){
        Query query = em.createQuery("SELECT p FROM Products p WHERE p.productName LIKE :pkeyword",Products.class);
        query.setParameter("pkeyword", "%"+keyword+"%");
        List<Products> listProducts = query.getResultList();
        return listProducts;
    }
    
    @Override
    public List<Products> showLastedProducts(){
        Query query = em.createQuery("SELECT p FROM Products p ORDER BY p.productID DESC",Products.class);
        
        List<Products> listProducts = query.getResultList();
        return listProducts;
    }
    
    
}
