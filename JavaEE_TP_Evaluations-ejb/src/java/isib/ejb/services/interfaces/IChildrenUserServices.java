/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.services.interfaces;

import isib.ejb.dao.IDAO;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author LOIC
 */
public interface IChildrenUserServices<T> {
    
    IDAO<T> dao();
    T create(T obj);
    T createWithRelation(T obj);
    T update(T obj);
    boolean delete(int id);
    T read(int id);
    T read(String login, String password);
    T read(String login, String password, boolean enabled);
    T readIdUser(int id);
    List<T> readAll();
    
}
