/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sbeanpack;

import entitypack.Users;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author salin_000
 */
@Local
public interface UsersFacadeLocal {

    void create(Users users);

    void edit(Users users);

    void remove(Users users);

    Users find(Object id);

    List<Users> findAll();

    List<Users> findRange(int[] range);

    int count();

    public boolean checkLogin(String username, String password);

    public int findIdByUsername(String username);

    
}
