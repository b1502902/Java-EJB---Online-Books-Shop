/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sbeanpack;

import entitypack.Advertises;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author salin_000
 */
@Local
public interface AdvertisesFacadeLocal {

    void create(Advertises advertises);

    void edit(Advertises advertises);

    void remove(Advertises advertises);

    Advertises find(Object id);

    List<Advertises> findAll();

    List<Advertises> findRange(int[] range);

    int count();
    
}
