/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sbeanpack;

import entitypack.Albums;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author salin_000
 */
@Local
public interface AlbumsFacadeLocal {

    void create(Albums albums);

    void edit(Albums albums);

    void remove(Albums albums);

    Albums find(Object id);

    List<Albums> findAll();

    List<Albums> findRange(int[] range);

    int count();
    
}
