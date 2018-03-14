/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sbeanpack;

import entitypack.ProductArtist;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author salin_000
 */
@Local
public interface ProductArtistFacadeLocal {

    void create(ProductArtist productArtist);

    void edit(ProductArtist productArtist);

    void remove(ProductArtist productArtist);

    ProductArtist find(Object id);

    List<ProductArtist> findAll();

    List<ProductArtist> findRange(int[] range);

    int count();
    
}
