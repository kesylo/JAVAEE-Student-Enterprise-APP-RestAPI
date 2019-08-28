/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.apiRest.rest;

import isib.apiRest.bo.Notification;
import isib.apiRest.dao.IDAO;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author LOIC
 */
public interface IRest<T> {
    
    IDAO<T> dao();
    T create(MultivaluedMap<String, String> params);
    T update(MultivaluedMap<String, String> params);
    boolean delete(int id);
    T read(int id);
    List<T> readAll();
    
}
