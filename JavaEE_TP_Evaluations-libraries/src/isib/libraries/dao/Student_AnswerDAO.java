/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.libraries.dao;

import isib.libraries.entity.Student_Answer;
import java.util.List;
import isib.libraries.tools.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LOIC
 */
public class Student_AnswerDAO implements IDAO<Student_Answer> {

    private Session session;    
    private Transaction transaction;    
    private String tableName = "Student_Answer";
    
    @Override
    public Student_Answer create(Student_Answer obj) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            session.save(obj);
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            System.out.println("Exception: " + ex);
            obj = null;
        }
        finally {
            session.close();
        }
        
        return obj;
    }

    @Override
    public Student_Answer update(Student_Answer obj) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            session.update(obj);
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            System.out.println("Exception: " + ex);
            obj = null;
        }
        finally {
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
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            System.out.println("Exception: " + ex);
        }
        finally {
            session.close();
            
            return get(id) == null;
        }
    }

    @Override
    public Student_Answer get(int id) {
       Student_Answer results = null;
       
       try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            results = (Student_Answer)session.createQuery("from " + tableName + " where id = " + id).list().get(0);
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            System.out.println("Exception: " + ex);
        }
        finally {
            session.close();
        }
        
        return results;
    }

    @Override
    public List<Student_Answer> getAll() {
       List<Student_Answer> results = null;
       
       try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            results = session.createQuery("from " + tableName).list();
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            System.out.println("Exception: " + ex);
        }
        finally {
            session.close();
        }
            
       return results;
    }

    @Override
    public Student_Answer createWithRelation(Student_Answer obj) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            session.save(obj.getAnswer());
            session.save(obj.getStudent());
            session.save(obj);
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            System.out.println("Exception: " + ex);
            obj = null;
        }
        finally {
            session.close();
        }
        
        return obj;
    }
    
}
