/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.dao;

import java.util.List;

/**
 *
 * @author LOIC
 */
public interface IDAO<T> {
    
    T create(T obj);
    T createWithRelation(T obj);
    T update(T obj);
    boolean delete(int id);
    T get(int id);
    List<T> getAll();
    
}
