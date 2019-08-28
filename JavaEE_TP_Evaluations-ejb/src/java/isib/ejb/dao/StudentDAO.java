/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isib.ejb.dao;

import isib.ejb.entity.Student;
import isib.ejb.entity.User;
import isib.ejb.tools.HibernateUtil;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LOIC
 */
public class StudentDAO implements IDAO<Student> {

    private static final Logger log4j = Logger.getLogger(StudentDAO.class);

    private Session session;
    private Transaction transaction;
    private String tableName = "Student";

    @Override
    public Student create(Student obj) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            session.save(obj);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            log4j.error(ex);
            obj = null;
        } finally {
            session.close();
        }

        return obj;
    }

    @Override
    public Student update(Student obj) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            session.update(obj);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            log4j.error(ex);
            obj = null;
        } finally {
            session.close();
        }

        return obj;
    }

    @Override
    public boolean delete(int id) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            session.delete(
                    session.createQuery("from " + tableName + " where id = " + id).list().get(0)
            );

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            log4j.error(ex);
        } finally {
            session.close();

            return get(id) == null;
        }
    }

    @Override
    public Student get(int id) {
        Student results = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            results = (Student) session.createQuery("from " + tableName + " where id = " + id).list().get(0);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            log4j.error(ex);
        } finally {
            session.close();
        }

        return results;
    }

    public Student get(String login, String password) {
        Student results = null;
        User user = new UserDAO().get(login, password);

        if (user == null) {
            return results;
        }

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            results = (Student) session.createQuery("from " + tableName + " where id_user = " + user.getId()).list().get(0);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            log4j.error(ex);
        } finally {
            session.close();
        }

        return results;
    }

    public Student get(String login, String password, boolean enabled) {
        Student results = null;
        User user = new UserDAO().get(login, password, enabled);

        if (user == null) {
            return results;
        }

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            results = (Student) session.createQuery("from " + tableName + " where id_user = " + user.getId()).list().get(0);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            log4j.error(ex);
        } finally {
            session.close();
        }

        return results;
    }

    public Student getIdUser(int id_user) {
        Student results = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            results = (Student) session.createQuery("from " + tableName + " where id_user = " + id_user).list().get(0);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            log4j.error(ex);
        } finally {
            session.close();
        }

        return results;
    }

    @Override
    public List<Student> getAll() {
        List<Student> results = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            results = session.createQuery("from " + tableName).list();

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            log4j.error(ex);
        } finally {
            session.close();
        }

        return results;
    }

    @Override
    public Student createWithRelation(Student obj) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            session.save(obj.getUser());
            session.save(obj);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            log4j.error(ex);
            obj = null;
        } finally {
            session.close();
        }

        return obj;
    }

}
