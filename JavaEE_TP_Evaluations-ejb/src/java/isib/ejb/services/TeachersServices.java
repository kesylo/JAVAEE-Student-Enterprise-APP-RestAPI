/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.services;

import isib.ejb.dao.IDAO;
import isib.ejb.dao.TeacherDAO;
import isib.ejb.entity.Teacher;
import isib.ejb.services.interfaces.IChildrenUserServices;
import isib.ejb.services.interfaces.IServices;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author LOIC
 */
@Stateless(name = "TeacherEJB")
public class TeachersServices implements IChildrenUserServices<Teacher> {

    @Override
    public IDAO<Teacher> dao() {
        return new TeacherDAO();
    }
    
    @Override
    public Teacher create(Teacher obj) {
        return dao().create(obj);
    }

    @Override
    public Teacher createWithRelation(Teacher obj) {
        return dao().createWithRelation(obj);
    }

    @Override
    public Teacher update(Teacher obj) {
        return dao().update(obj);
    }

    @Override
    public boolean delete(int id) {
        return dao().delete(id);
    }

    @Override
    public Teacher read(int id) {
        return dao().get(id);
    }

    @Override
    public Teacher readIdUser(int id) {
        return ((TeacherDAO)dao()).getIdUser(id);
    }

    @Override
    public List<Teacher> readAll() {
        return dao().getAll();
    }

    @Override
    public Teacher read(String login, String password) {
        return ((TeacherDAO)dao()).get(login, password);
    }

    @Override
    public Teacher read(String login, String password, boolean enabled) {
        return ((TeacherDAO)dao()).get(login, password, enabled);
    }
}
