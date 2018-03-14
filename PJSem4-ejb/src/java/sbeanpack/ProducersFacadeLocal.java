/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sbeanpack;

import entitypack.Producers;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author salin_000
 */
@Local
public interface ProducersFacadeLocal {

    void create(Producers producers);

    void edit(Producers producers);

    void remove(Producers producers);

    Producers find(Object id);

    List<Producers> findAll();

    List<Producers> findRange(int[] range);

    int count();
    
}
