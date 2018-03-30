/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbeanpack;

import entitypack.AlbumProduct;
import entitypack.Albums;
import entitypack.Products;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author salin_000
 */
@Local
public interface AlbumProductFacadeLocal {

    void create(AlbumProduct albumProduct);

    void edit(AlbumProduct albumProduct);

    void remove(AlbumProduct albumProduct);

    AlbumProduct find(Object id);

    List<AlbumProduct> findAll();

    List<AlbumProduct> findRange(int[] range);

    int count();

    /**
     *
     * @param alb
     * @return
     */
    public List<Products> listProductsByAlbum(Albums alb);
    
}
