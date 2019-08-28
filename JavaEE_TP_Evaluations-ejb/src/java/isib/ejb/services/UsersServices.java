/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.services;

import isib.ejb.services.interfaces.IUsersServices;
import isib.ejb.dao.IDAO;
import isib.ejb.dao.UserDAO;
import isib.ejb.entity.User;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author LOIC
 */
@Stateless(name = "UserEJB")
public class UsersServices implements IUsersServices {

    @Override
    public UserDAO dao() {
        return new UserDAO();
    }
    
    @Override
    public User create(User obj) {
        return dao().create(obj);
    }

    @Override
    public User createWithRelation(User obj) {
        return dao().createWithRelation(obj);
    }

    @Override
    public User update(User obj) {
        return dao().update(obj);
    }

    @Override
    public boolean delete(int id) {
        return dao().delete(id);
    }

    @Override
    public User read(int id) {
        return dao().get(id);
    }

    @Override
    public List<User> readAll() {
        return dao().getAll();
    }

    @Override
    public User read(String login, String password) {
        return dao().get(login, password);
    }

    @Override
    public User read(String login, String password, boolean enabled) {
        return dao().get(login, password, enabled);
    }
}
