/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.dao;

import isib.ejb.entity.Evaluation;
import java.util.List;
import isib.ejb.tools.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LOIC
 */
public class EvaluationDAO implements IDAO<Evaluation> {

    private static final Logger log4j = Logger.getLogger(EvaluationDAO.class);

    private Session session;    
    private Transaction transaction;    
    private String tableName = "Evaluation";
    
    @Override
    public Evaluation create(Evaluation obj) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            session.save(obj);
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            log4j.error(ex);
            obj = null;
        }
        finally {
            session.close();
        }
        
        return obj;
    }

    @Override
    public Evaluation update(Evaluation obj) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            session.update(obj);
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            log4j.error(ex);
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
            log4j.error(ex);
        }
        finally {
            session.close();
            
            return get(id) == null;
        }
    }

    @Override
    public Evaluation get(int id) {
       Evaluation results = null;
       
       try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            results = (Evaluation)session.createQuery("from " + tableName + " where id = " + id).list().get(0);
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            log4j.error(ex);
        }
        finally {
            session.close();
        }
        
        return results;
    }

    @Override
    public List<Evaluation> getAll() {
       List<Evaluation> results = null;
       
       try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            results = session.createQuery("from " + tableName).list();
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            log4j.error(ex);
        }
        finally {
            session.close();
        }
            
       return results;
    }

    @Override
    public Evaluation createWithRelation(Evaluation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
