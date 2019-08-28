/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.services;

import isib.ejb.dao.IDAO;
import isib.ejb.dao.StudentDAO;
import isib.ejb.entity.Student;
import isib.ejb.services.interfaces.IChildrenUserServices;
import isib.ejb.services.interfaces.IServices;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author LOIC
 */
@Stateless(name = "StudentEJB")
public class StudentsServices implements IChildrenUserServices<Student> {

    @Override
    public IDAO<Student> dao() {
        return new StudentDAO();
    }
    
    @Override
    public Student create(Student obj) {
        return dao().create(obj);
    }

    @Override
    public Student createWithRelation(Student obj) {
        return dao().createWithRelation(obj);
    }

    @Override
    public Student update(Student obj) {
        return dao().update(obj);
    }

    @Override
    public boolean delete(int id) {
        return dao().delete(id);
    }

    @Override
    public Student read(int id) {
        return dao().get(id);
    }

    @Override
    public Student readIdUser(int id) {
        return ((StudentDAO)dao()).getIdUser(id);
    }

    @Override
    public List<Student> readAll() {
        return dao().getAll();
    }

    @Override
    public Student read(String login, String password) {
        return ((StudentDAO)dao()).get(login, password);
    }

    @Override
    public Student read(String login, String password, boolean enabled) {
        return ((StudentDAO)dao()).get(login, password, enabled);
    }
}
