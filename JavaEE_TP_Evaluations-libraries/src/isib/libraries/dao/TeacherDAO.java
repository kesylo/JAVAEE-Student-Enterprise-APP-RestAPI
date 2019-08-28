/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isib.libraries.dao;

import isib.libraries.entity.Teacher;
import isib.libraries.entity.User;
import isib.libraries.tools.HibernateUtil;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LOIC
 */
public class TeacherDAO implements IDAO<Teacher> {

    private Session session;
    private Transaction transaction;
    private String tableName = "Teacher";

    @Override
    public Teacher create(Teacher obj) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            session.save(obj);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Exception: " + ex);
            obj = null;
        } finally {
            session.close();
        }

        return obj;
    }

    @Override
    public Teacher update(Teacher obj) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            session.update(obj);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Exception: " + ex);
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
            System.out.println("Exception: " + ex);
        } finally {
            session.close();

            return get(id) == null;
        }
    }

    @Override
    public Teacher get(int id) {
        Teacher results = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            results = (Teacher) session.createQuery("from " + tableName + " where id = " + id).list().get(0);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Exception: " + ex);
        } finally {
            session.close();
        }

        return results;
    }

    public Teacher get(String login, String password) {
        Teacher results = null;
        User user = new UserDAO().get(login, password);

        if (user == null) {
            return results;
        }

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            results = (Teacher) session.createQuery("from " + tableName + " where id_user = " + user.getId()).list().get(0);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Exception: " + ex);
        } finally {
            session.close();
        }

        return results;
    }

    public Teacher getIdUser(int id_user) {
        Teacher results = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            results = (Teacher) session.createQuery("from " + tableName + " where id_user = " + id_user).list().get(0);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Exception: " + ex);
        } finally {
            session.close();
        }

        return results;
    }

    @Override
    public List<Teacher> getAll() {
        List<Teacher> results = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            results = session.createQuery("from " + tableName).list();

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Exception: " + ex);
        } finally {
            session.close();
        }

        return results;
    }

    @Override
    public Teacher createWithRelation(Teacher obj) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            session.save(obj.getUser());
            session.save(obj);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("Exception: " + ex);
            obj = null;
        } finally {
            session.close();
        }

        return obj;
    }

}
