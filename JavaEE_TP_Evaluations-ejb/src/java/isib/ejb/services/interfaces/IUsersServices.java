/*
 * Usero change this license header, choose License Headers in Project Properties.
 * Usero change this template file, choose Userools | Useremplates
 * and open the template in the editor.
 */

package isib.ejb.services.interfaces;

import isib.ejb.dao.IDAO;
import isib.ejb.dao.UserDAO;
import isib.ejb.entity.User;
import java.util.List;

/**
 *
 * @author LOIC
 */
public interface IUsersServices {
    
    UserDAO dao();
    User create(User obj);
    User createWithRelation(User obj);
    User update(User obj);
    boolean delete(int id);
    User read(int id);
    List<User> readAll();
    User read(String login, String password);
    User read(String login, String password, boolean enabled);
    
}
